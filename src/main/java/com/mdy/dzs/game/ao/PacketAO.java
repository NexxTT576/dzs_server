package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.gong.Refine;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.packet.Bag;
import com.mdy.dzs.data.domain.packet.PanDuanXiTongChanChu;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.activity.roulettegame.Roulette;
import com.mdy.dzs.game.domain.card.ComparatorRoleCard;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.card.RoleCardEffect;
import com.mdy.dzs.game.domain.equip.ComparatorRoleEquip;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.gong.ComparatorRoleGong;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.dzs.game.domain.item.ComparatorBagItem;
import com.mdy.dzs.game.domain.item.ComparatorItemPieces;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.item.RoleItemVO;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleStatistics;
import com.mdy.dzs.game.domain.union.RoleUnion;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.PacketException;
import com.mdy.dzs.game.exception.RoleCardException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.BehaviorLogger;
import com.mdy.dzs.game.util.BehaviorLogger.Action;
import com.mdy.dzs.game.util.BehaviorLogger.GoldType;
import com.mdy.dzs.game.util.BehaviorLogger.Params;
import com.mdy.dzs.game.util.BehaviorLogger.Type;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * 背包
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月16日 上午10:26:48
 */
public class PacketAO extends BaseAO {
	final static Logger logger = LoggerFactory.get(PacketAO.class);

	private CacheManager cacheManager;
	private RoleAO roleAO;
	private BroadcastAO broadcastAO;
	private RoleCardAO roleCardAO;
	private MissionExecAO missionExecAO;
	private RoleUnionAO roleUnionAO;
	private RoleActivityRouletteAO roleActivityRouletteAO;

	public PacketAO(CacheManager cacheManager, RoleAO roleAO, BroadcastAO broadcastAO, RoleCardAO roleCardAO,
			RoleUnionAO roleUnionAO, RoleActivityRouletteAO roleActivityRouletteAO) {
		this.cacheManager = cacheManager;
		this.roleAO = roleAO;
		this.broadcastAO = broadcastAO;
		this.roleCardAO = roleCardAO;
		this.roleUnionAO = roleUnionAO;
		this.roleActivityRouletteAO = roleActivityRouletteAO;
	}

	/**
	 * 打包item类型
	 * 
	 * @param itemAry
	 * @return
	 */
	private List<RoleItemVO> packetItemList(List<Object> itemAry, int t) {
		if (itemAry.size() == 0 || !(itemAry.get(0) instanceof RoleItem))
			return new ArrayList<RoleItemVO>();

		List<RoleItemVO> rtnAry = new ArrayList<RoleItemVO>();
		List<RoleItemVO> comAry = new ArrayList<RoleItemVO>();
		List<RoleItemVO> partAry = new ArrayList<RoleItemVO>();
		int itemLimit = 0;
		int itemId = 0;
		int itemCnt = 0;
		RoleItem item = null;
		if (t == 7 || t == 9 || t == 10) {
			comAry = rtnAry;
			partAry = rtnAry;
		}

		if (t == 7) {// 背包道具
			Map<Integer, Item> map = cacheManager.getValues(Item.class);
			ComparatorBagItem compar = new ComparatorBagItem(map);
			Collections.sort(itemAry, compar);
		}
		if (t == 7 || t == 9 || t == 10 || t == 3 || t == 5) {// 背包道具及内外功碎片

			for (int i = 0; i < itemAry.size(); i++) {
				item = (RoleItem) itemAry.get(i);
				itemLimit = item.getItemLimit();
				itemCnt = item.getItemCnt();
				itemId = item.getItemId();
				// //if(item.itemId == 4014) debugger;
				// if(itemLimit != 0 && itemCnt >= itemLimit) {
				// int num = (int)Math.floor(itemCnt / itemLimit);
				// for(int ii = 0; ii < num; ii++){
				// comAry.add(new RoleItemVO(itemId,itemLimit));
				// }
				// if(itemCnt % itemLimit > 0){
				// partAry.add(new RoleItemVO(itemId,itemCnt - itemLimit * num));
				// }
				// }else{
				partAry.add(new RoleItemVO(itemId, itemCnt));
				// }
			}
		}
		if (t == 3 || t == 5) {// 装备碎片 武将碎片
			Map<Integer, Item> map = cacheManager.getValues(Item.class);
			ComparatorItemPieces compar = new ComparatorItemPieces(map);
			if (comAry.size() > 0) {
				Collections.sort(comAry, compar);
			}
			if (partAry.size() > 0) {
				Collections.sort(partAry, compar);
			}
			rtnAry.addAll(comAry);
			rtnAry.addAll(partAry);
		}
		;
		return rtnAry;
	}

	/**
	 * 打包gong类型
	 * 
	 * @param itemAry
	 * @return
	 */
	private List<Object> sortGongList(List<Object> itemAry) {
		if (itemAry.size() == 0 || !(itemAry.get(0) instanceof RoleGong))
			return new ArrayList<Object>();
		RoleGong gong = null;
		for (int i = 0; i < itemAry.size(); i++) {
			gong = (RoleGong) itemAry.get(i);
			Item itemData = cacheManager.getValueByKey(Item.class, gong.getResId());
			gong.setOrder(itemData.getPos());
		}

		ComparatorRoleGong compar = new ComparatorRoleGong();
		Collections.sort(itemAry, compar);
		return itemAry;
	}

	private List<Object> sortEquiplist(List<Object> itemAry) {
		if (itemAry.size() == 0 || !(itemAry.get(0) instanceof Equip))
			return new ArrayList<Object>();
		ComparatorRoleEquip compar = new ComparatorRoleEquip();
		Collections.sort(itemAry, compar);
		return itemAry;
	}

	/**
	 * 对card进行排序
	 */
	public <T> List<T> sortCardList(Role doc, List<T> itemAry) {
		if (itemAry.size() == 0 || !(itemAry.get(0) instanceof RoleCard))
			return new ArrayList<T>();
		RoleCard mainCard = (RoleCard) itemAry.get(0);
		mainCard.setName(doc.getName());
		Map<Integer, RoleCard> resCards = new HashMap<Integer, RoleCard>();
		List<Integer> fmtRelation = new ArrayList<Integer>();
		Map<Integer, Integer> fmtCardResIds = new HashMap<Integer, Integer>();
		for (int i = 0; i < itemAry.size(); i++) {
			RoleCard curCard = (RoleCard) itemAry.get(i);
			boolean inFmt = doc.getFmtCardAry().indexOf(curCard.getId()) != -1;
			if (inFmt)
				fmtCardResIds.put(curCard.getResId(), 1);
			setCardValues(doc, curCard, inFmt);
			fmtRelation.addAll(curCard.getRelation());
			if (!resCards.containsKey(curCard.getResId()) && !inFmt) {
				resCards.put(curCard.getResId(), curCard);
			}
		}
		// 设置情缘
		Map<Integer, List<Integer>> fmtRelationCardIds = roleCardAO.getRelationCardIds(fmtRelation);
		for (Entry<Integer, List<Integer>> entry : fmtRelationCardIds.entrySet()) {
			if (resCards.containsKey(entry.getKey()) && !fmtCardResIds.containsKey(entry.getKey())) {
				resCards.get(entry.getKey()).setRelation(entry.getValue());
			}
		}

		List<Integer> fmtCardAry = doc.getFmtCardAry();
		ComparatorRoleCard compar = new ComparatorRoleCard(mainCard.getId(), fmtCardAry);
		Collections.sort(itemAry, compar);
		return itemAry;
	}

	public void setCardValues(Role doc, RoleCard curCard, boolean setRelation) {
		List<List<Integer>> baseLeadAry = roleAO.battleCalcCard(doc, curCard);
		curCard.setViewBase(baseLeadAry.get(0));
		curCard.setViewLead(baseLeadAry.get(1));
		if (!setRelation)
			return;
		RoleCardEffect effect = roleCardEffectDAO().queryByRoleIdCardId(doc.getId(), curCard.getResId());
		if (effect != null) {
			curCard.getRelation().addAll(effect.getEffectCardRelation());
		}
	}

	/**
	 * 获取对应背包列表并打包处理
	 * 
	 * @param t
	 * @return
	 * @throws BizException
	 */
	// t=1装备/2时装/3装备碎片/4内外功/5武将碎片/6精元/7背包/8武将/9内功碎片/10外功碎片
	public List<Object> getPacketListByType(int roleId, int t) throws BizException {
		List<Object> res = new ArrayList<Object>();
		switch (t) {
		case 1: {
			// 装备
			res.addAll(equipDAO().queryListByAccount(roleId));
		}
			break;
		case 2: {
			// 时装
		}
			break;
		case 3:
		case 5:
		case 9:
		case 10: {
			// 装备碎片
			// 武将碎片
			// 内外功碎片
			res.addAll(packetDAO().queryListByAccount(roleId, t));
		}
			break;
		case 4: {
			// 内外功
			res.addAll(roleGongDAO().queryListByAccount(roleId));
		}
			break;

		case 6: {
			// 精元
			res.addAll(roleYuanDAO().queryListByAccount(roleId));
		}
			break;
		case 8: {
			// 武将
			res.addAll(roleCardDAO().queryListByRoleId(roleId));
		}
			break;
		default:
			// 背包
			res.addAll(packetDAO().queryListByAccount(roleId, 7));
		}

		return packetList(res, t);
	}

	/**
	 * 添加 装备/卡片/内外功碎片 道具
	 * 
	 * @param acc
	 * @param type
	 * @param itemData
	 * @param cnt
	 */
	public RoleItem packetAddItem(int roleId, Item itemData, int cnt) throws BizException {
		int type = itemData.getType();
		RoleItem statItem = packetDAO().queryByAccItemId(roleId, itemData.getId(), type);
		if (statItem != null) {// find
			statItem.setItemCnt(statItem.getItemCnt() + cnt);
			packetDAO().update(statItem, type);
		} else {// add
			statItem = new RoleItem();
			statItem.setItemId(itemData.getId());
			statItem.setItemCnt(cnt);
			statItem.setRoleId(roleId);
			statItem.setItemLimit(itemData.getOverlay());
			int id = packetDAO().add(statItem, type);
			statItem.setId(id);
		}
		return statItem;
	};

	public void updateBoxProService(int roleid, int pronum, int itemid) {
		packetDAO().updateBoxPro(roleid, pronum, itemid);
	}

	public void updateBoxProServiceTo(int roleid, int pronum, int itemid) {
		packetDAO().updateBoxProTo(roleid, pronum, itemid);
	}

	/**
	 * 添加一件装备
	 * 
	 * @param acc
	 * @param equipId
	 * @return
	 * @throws BizException
	 */
	public Equip packetAddEquip(Role role, int equipId) throws BizException {

		Item equipData = cacheManager.getValueByKey(Item.class, equipId);
		Equip e = new Equip();
		e.setRoleId(role.getId());
		e.setResId(equipId);
		e.setType(equipData.getType());
		e.setStar(equipData.getQuality());
		e.setSilver(equipData.getPrice());
		e.setSubpos(equipData.getPos());
		e.setProps(new ArrayList<Prop>());
		e.setPropsWait(new ArrayList<Prop>());
		e.setRelation(new ArrayList<Integer>());
		int id = equipDAO().add(e);
		e.setId(id);
		equipDAO().addRecord(e);
		missionExecAO.newRoleEquip(role, e);
		return e;
	}

	/**
	 * 添加内外功
	 * 
	 * @param acc
	 * @param gongId
	 * @return
	 */
	public RoleGong packetAddGong(int roleId, int gongId) {
		Item gongData = cacheManager.getValueByKey(Item.class, gongId);
		RoleGong e = new RoleGong();
		e.setResId(gongId);
		e.setType(gongData.getType());
		e.setStar(gongData.getQuality());
		e.setSubpos(gongData.getPos());
		e.setProps(new ArrayList<Prop>());
		e.setRelation(new ArrayList<Integer>());
		e.setRoleId(roleId);

		Refine gongItemData = cacheManager.getValueByKey(Refine.class, gongId);
		if (gongItemData != null && gongItemData.getRefine() != 0) {
			List<Integer> rNatures2 = gongItemData.getArrNature2();
			for (int i = 0; i < rNatures2.size(); i++)
				e.getProps().add(new Prop(rNatures2.get(i), 0));
		}
		roleGongDAO().add(e);
		roleGongDAO().addRecord(e);
		return e;
	}

	/**
	 * 添加精元
	 * 
	 * @param acc
	 * @param gongId
	 * @return
	 * @throws BizException
	 */
	public RoleYuan packetAddYuan(Role role, int id) throws BizException {
		Item yuanData = cacheManager.getValueByKey(Item.class, id);
		RoleYuan e = new RoleYuan();
		e.setResId(id);
		e.setPos(0);
		e.setSubpos(0);
		e.setLevel(0);
		e.setCurExp(0);
		e.setQuality(yuanData.getQuality());
		e.setType(yuanData.getType());
		e.setVariety(yuanData.getPos());
		e.setProps(new ArrayList<Prop>());
		e.setRoleId(role.getId());

		List<Integer> natures = yuanData.getArrNature();
		List<Integer> values = yuanData.getArrValue();
		for (int i = 0; i < values.size(); i++) {
			e.getProps().add(new Prop(natures.get(i), values.get(i)));
		}
		roleYuanDAO().addRtKey(e);
		missionExecAO.newRoleYuan(role, e);
		return e;
	}

	/**
	 * 打包排序处理 //t=1装备/2时装/3装备碎片/4内外功/5武将碎片/6精元/7背包/8武将/9内功碎片/10外功碎片
	 * 
	 * @param itemAry
	 * @param t
	 * @return
	 */
	private List<Object> packetList(List<Object> itemAry, int t) {
		List<Object> orderAry = itemAry;

		if (t == 3 || t == 5 || t == 7 || t == 9 || t == 10) {
			orderAry = new ArrayList<Object>(packetItemList(itemAry, t));
		}

		if (4 == t) { // 内外功进行排序
			orderAry = sortGongList(itemAry);
		}
		if (1 == t) {
			orderAry = sortEquiplist(itemAry);
		}
		if (6 == t) {
			// orderAry = sortEquiplist(itemAry);
		}
		return orderAry;
	};

	// 背包检查
	// id:panduanxitongchanchu['id']
	// [type = 1装备/2时装/3装备碎片/4内外功/5武将碎片/6精元/7背包/8武将]
	/**
	 * 检查系统产出
	 * 
	 * @param doc
	 * @param panduanId
	 * @return
	 * @throws BizException
	 */
	public List<PacketExtend> checkBag(Role doc, int panduanId) throws BizException {
		PanDuanXiTongChanChu checkData = cacheManager.getValueByKey(PanDuanXiTongChanChu.class, panduanId);
		if (checkData == null || checkData.getDecide() == 2)
			return new ArrayList<PacketExtend>();
		List<Integer> idAry = checkData.getBag();
		return checkBag(doc, idAry);
	}

	/**
	 * 根据各个包裹id
	 * 
	 * @param doc
	 * @param idAry
	 * @return
	 * @throws BizException
	 */
	public List<PacketExtend> checkBag(Role doc, List<Integer> idAry) throws BizException {
		boolean tempFull = false;
		List<PacketExtend> bageData = new ArrayList<PacketExtend>();

		Integer id = null;
		int limit = 0;
		for (int i = 0; i < idAry.size(); i++) {
			id = idAry.get(i);
			limit = doc.getBagLimit(id);
			if (id == null || id == 0)
				continue;
			if (limit == 0)
				continue;
			tempFull = false;
			int count = getPacketCountByType(doc, id);
			if (count >= limit) {
				tempFull = true;
			}

			if (tempFull) {
				bageData.add(getPacketExtend(doc, count, id));
			}
		}

		return bageData;
	};

	/**
	 * 返回背包数量
	 * 
	 * @param doc
	 * @param id
	 * @return
	 * @throws BizException
	 */
	private int getPacketCountByType(Role doc, int t) throws BizException {
		String acc = doc.getAccount();
		int roleId = doc.getId();
		int res = 0;
		switch (t) {
		case 1: {
			// 装备
			res = equipDAO().queryCountByAccount(roleId);
		}
			break;
		case 2: {
			// 时装
		}
			break;
		case 3:
		case 5:
		case 9:
		case 10: {
			// 装备碎片
			// 武将碎片
			// 内外功碎片
			res = packetDAO().queryCountByAccount(roleId, t);
		}
			break;
		case 4: {
			// 内外功
			res = roleGongDAO().queryCountByAccount(roleId);
		}
			break;

		case 6: {
			// 精元
			res = roleYuanDAO().queryCountByAccount(roleId);
		}
			break;
		case 8: {
			// 武将
			res = roleCardDAO().queryCountByAccount(roleId);
		}
			break;
		default:
			// 背包
			res = packetDAO().queryCountByAccount(roleId, 7);
		}
		return res;
	}

	/**
	 * 获取背包扩展花费及当前/最大数
	 * 
	 * @param doc
	 * @param packedId
	 * @return
	 */
	public PacketExtend getPacketExtend(Role doc, int curCnt, int packedId) {
		int cost;
		int size;
		int limit = doc.getBagLimit(packedId);
		Bag bagData = cacheManager.getValueByKey(Bag.class, packedId);
		if (bagData != null && bagData.getNum() != bagData.getMax() && limit != bagData.getMax()) {// 能扩展
			size = 5;
			cost = (limit - bagData.getNum() + 5) / 5 * bagData.getAdd();
		} else {
			size = 0;
			cost = -1;
		}
		return new PacketExtend(packedId, cost, curCnt, limit, size, Constants.packetExtendMoneyId);
	}

	public List<Object> addItem(String acc, int type, int id, int cnt, int sysType, String comments)
			throws BizException {
		Role doc = roleAO.queryExistAccount(acc);
		return addItem(doc, type, id, cnt, sysType, comments);
	}

	public List<Object> addItem(Role doc, List<ProbItem> items, int sysType, String comments) throws BizException {
		List<Object> list = new ArrayList<Object>();

		// 合并重复项
		Map<String, ProbItem> itemMap = new HashMap<String, ProbItem>();
		for (ProbItem item : items) {
			String key = item.getT() + "_" + item.getId();
			if (!itemMap.containsKey(key)) {
				itemMap.put(key, new ProbItem(item.getT(), item.getId(), 0));
			}
			ProbItem pItem = itemMap.get(key);
			pItem.setN(pItem.getN() + item.getN());
		}
		// 发放奖励
		for (ProbItem item : itemMap.values()) {
			list.add(addItem(doc, item.getT(), item.getId(), item.getN(), sysType, comments));
		}
		return list;
	}

	public List<Object> addItem(Role doc, ProbItem item, int sysType, String comments) throws BizException {
		return addItem(doc, item.getT(), item.getId(), item.getN(), sysType, comments);
	}

	/**
	 * 添加道具
	 * 
	 * @param acc
	 * @param type
	 * @param id
	 * @param cnt
	 * @throws BizException
	 */
	public List<Object> addItem(Role doc, int type, int id, int cnt, int sysType, String comments) throws BizException {
		if (cnt == 0)
			return null;
		String acc = doc.getAccount();
		int roleId = doc.getId();
		List<Object> res = new ArrayList<Object>();
		int newNum = cnt;
		if (type == 0) {// coin
			newNum = updateRoleProperty(doc, id, cnt);
		} else if (type == 1) {// equip

			for (int i = 0; i < cnt; i++) {
				Item equipData = cacheManager.getValueByKey(Item.class, id);
				if (equipData.getQuality() == 5)
					broadcastAO.equipGetBroad(doc, equipData);
				res.add(packetAddEquip(doc, id));
			}
		} else if (type == 2) {
		} else if (type == 4) {
			for (int i = 0; i < cnt; i++) {

				Item gongData = cacheManager.getValueByKey(Item.class, id);
				if (acc != null && gongData.getQuality() == 5)
					broadcastAO.equipGetBroad(doc, gongData);
				res.add(packetAddGong(roleId, id));
			}
		} else if (type == 6) {
			for (int i = 0; i < cnt; i++) {
				res.add(packetAddYuan(doc, id));
			}
		} else if (type == 3 || type == 5 || type == 7 || type == 9 || type == 10 || type == 11 || type == 12) {// packet
			Item itemData = cacheManager.getValueByKey(Item.class, id);

			if (itemData.getAuto() != null && itemData.getAuto().compareTo("1") == 0) {
				if (itemData.getEffecttype() == 0) {
					newNum = updateRoleProperty(doc, itemData.getPara1().get(0), itemData.getPara2().get(0) * cnt);
				} else if (itemData.getEffecttype() == 2) {// 卡片
					for (int i = 0; i < cnt * itemData.getPara2().get(0); i++) {
						res.add(roleCardAO.addCard(doc, itemData.getPara1().get(0), 0));
					}
				}
			} else {
				RoleItem item = packetAddItem(roleId, itemData, cnt);
				newNum = item.getItemCnt();
				res.add(item);
			}
		} else if (type == 8) {// card
			for (int i = 0; i < cnt; i++) {
				res.add(roleCardAO.addCard(doc, id, 0));
			}
		}
		// 添加日志
		RoleItemLog log = new RoleItemLog(doc, sysType, type, id, cnt, newNum, comments);
		roleItemLogDAO().add(log);
		// 添加元宝
		if (id == Packet.ATTR_金币 && (type == Packet.POS_ATTR || type == Packet.POS_BAG)) {
			switch (sysType) {
			case RoleItemLog.SYS_充值_基础获得:
				roleStatisticsDAO().addGold(doc.getId(), cnt);
				BehaviorLogger.log4GoldGet(doc, sysType, GoldType.RECHARGE_GOLD, cnt);
				break;
			// case RoleItemLog.SYS_充值_赠送获得:
			// case RoleItemLog.SYS_充值_首冲赠送获得:
			// case RoleItemLog.SYS_充值_月卡获得:
			// roleStatisticsDAO().addDonate(doc.getId(),cnt);
			// break;
			// default:
			// roleStatisticsDAO().addBind(doc.getId(),cnt);
			default:
				roleStatisticsDAO().addDonate(doc.getId(), cnt);
				BehaviorLogger.log4GoldGet(doc, sysType, GoldType.SYS_GOLD, cnt);
			}
		} else {
			// 道具添加
			BehaviorLogger.log4Platform(doc, Type.GOODS_GET, Action.ITEM_GET, Params.valueOf("wpnum", cnt),
					Params.valueOf("wpid", id), Params.valueOf("wptype", type), Params.valueOf("source", sysType),
					Params.valueOf("level", doc.getLevel()), Params.valueOf("vip", doc.getVip()));
		}
		return res;
	}

	// public void removeItemMustEnough(String acc, int type, int id, int cnt)
	// throws BizException{
	// Role doc = roleAO.queryExistAccount(acc);
	// removeItemMustEnough(doc, type, id, cnt);
	// }

	public void removeItemMustEnough(Role doc, int type, int resId, int cnt, int sysType, String comments)
			throws BizException {
		String acc = doc.getAccount();
		int roleId = doc.getId();
		int newNum = 0;
		if (type == 0) {
			newNum = updateRoleProperty(doc, resId, -cnt);

		} else if (type == 3 || type == 5 || type == 7 || type == 9 || type == 10 || type == 11 || type == 12) {// packet

			RoleItem curItem = packetDAO().queryByAccItemId(roleId, resId, type);
			if (curItem != null) {
				if (curItem.getItemCnt() - cnt > 0) {
					curItem.setItemCnt(curItem.getItemCnt() - cnt);
					packetDAO().update(curItem, type);
					newNum = curItem.getItemCnt();
				} else if (curItem.getItemCnt() - cnt == 0) {
					packetDAO().delete(curItem, type);
				} else
					throw BaseException.getException(PacketException.EXCE_ITEM_NOT_ENOUGH, resId, cnt);
			} else {
				throw BaseException.getException(PacketException.EXCE_ITEM_NOT_ENOUGH, resId, cnt);
			}
		}
		removeCardEquipGongYuanByResId(doc, type, resId, cnt, sysType, false);
		// 道具消耗
		if (type != 0) {
			BehaviorLogger.log4Platform(doc, Type.GOODS_COST, Action.ITEM_COST, Params.valueOf("wpnum", cnt),
					Params.valueOf("wpid", resId), Params.valueOf("wptype", type), Params.valueOf("source", sysType),
					Params.valueOf("level", doc.getLevel()), Params.valueOf("vip", doc.getVip()));
		}
		// 添加日志
		RoleItemLog log = new RoleItemLog(doc, sysType, type, resId, -cnt, newNum, comments);
		roleItemLogDAO().add(log);
		// 扣除元宝
		if (resId == Packet.ATTR_金币 && (type == Packet.POS_ATTR || type == Packet.POS_BAG)) {
			RoleStatistics rs = roleStatisticsDAO().queryById(doc.getId());
			if (rs == null) {
				rs = new RoleStatistics();
				rs.setRoleId(doc.getId());
				rs.setAccount(doc.getAccount());
				roleStatisticsDAO().add(rs);
			}
			RoleStatistics old = new RoleStatistics(rs);
			int val = cnt;
			rs.setCurBind(rs.getCurBind() - val);
			if (rs.getCurBind() < 0) {
				val = -rs.getCurBind();
				rs.setCurBind(0);
				rs.setCurDonate(rs.getCurDonate() - val);
			}
			if (rs.getCurDonate() < 0) {
				val = -rs.getCurDonate();
				rs.setCurDonate(0);
				rs.setCurGold(rs.getCurGold() - val);
			}
			if (rs.getCurGold() < 0)
				rs.setCurGold(0);
			roleStatisticsDAO().updateGolds(rs);
			if (sysType / 10 != 11000) {
				BehaviorLogger.log4GoldCost(doc, sysType, old, rs, null);
			}
		}
	}

	/**
	 * 删除卡片 装备 内外功 精元 根据唯一id
	 * 
	 * @param doc
	 * @param type
	 * @param id
	 * @throws BizException
	 */
	public void removeCardEquipGongYuanById(Role doc, int type, int id, int sysType) throws BizException {
		String acc = doc.getAccount();
		int roleId = doc.getId();
		String logResId = "";
		if (type == Packet.POS_YUAN) {// 精元
			RoleYuan curYuan = roleYuanDAO().query(id);
			if (curYuan != null && roleId == curYuan.getRoleId()) {
				roleYuanDAO().delete(curYuan);
			} else {
				throw BaseException.getException(PacketException.EXCE_YUAN_NOT_ENOUGH, id, 1);
			}
			logResId = "" + curYuan.getResId();
		} else if (type == Packet.POS_CARD) {
			RoleCard curCard = roleCardDAO().query(id);
			if (curCard != null && roleId == curCard.getRoleId()) {
				if (curCard.IsLock()) {
					throw BaseException.getException(RoleCardException.EXCE_CARD_IS_LOCK, id);
				}
				roleCardAO.delete(curCard);
			} else {
				throw BaseException.getException(PacketException.EXCE_CARD_NOT_ENOUGH, id, 1);
			}
			logResId = "" + curCard.getResId();
		} else if (type == Packet.POS_GONG) {
			RoleGong curGong = roleGongDAO().query(id);
			if (curGong != null && roleId == curGong.getRoleId()) {
				roleGongDAO().delete(curGong);
			} else {
				throw BaseException.getException(PacketException.EXCE_GONG_NOT_ENOUGH, id, 1);
			}
			logResId = "" + curGong.getResId();
		} else if (type == Packet.POS_EQUIP) {
			Equip curEquip = equipDAO().query(id);
			if (curEquip != null && roleId == curEquip.getRoleId()) {
				equipDAO().delete(curEquip);
			} else {
				throw BaseException.getException(PacketException.EXCE_EQUIP_NOT_ENOUGH, id, 1);
			}
			logResId = "" + curEquip.getResId();
		}
		// 道具消耗
		BehaviorLogger.log4Platform(doc, Type.GOODS_COST, Action.ITEM_COST, Params.valueOf("wpnum", 1),
				Params.valueOf("wpid", logResId), Params.valueOf("wptype", type), Params.valueOf("id", id),
				Params.valueOf("source", sysType), Params.valueOf("level", doc.getLevel()),
				Params.valueOf("vip", doc.getVip()));

		// 添加日志
		RoleItemLog log = new RoleItemLog(doc, sysType, type, id, -1, 0, logResId);
		roleItemLogDAO().add(log);
	}

	public void removeCardEquipGongYuanByResId(Role doc, int type, int resId, int cnt, int sysType)
			throws BizException {
		removeCardEquipGongYuanByResId(doc, type, resId, cnt, sysType, true);
	}

	/**
	 * 删除卡片 装备 内外功 精元 根据唯一id
	 * 
	 * @param doc
	 * @param type
	 * @param id
	 * @throws BizException
	 */
	public void removeCardEquipGongYuanByResId(Role doc, int type, int resId, int cnt, int sysType, boolean addlog)
			throws BizException {
		String acc = doc.getAccount();
		int roleId = doc.getId();
		String logResId = "";
		int newNum = 0;
		if (type == 6) {// 精元
			List<RoleYuan> curYuans = roleYuanDAO().querySameResYuanByPos(roleId, resId);
			if (curYuans.size() >= cnt) {
				int dnum = 0;
				while (dnum < cnt && curYuans.size() != 0) {
					roleYuanDAO().delete(curYuans.get(0));
					curYuans.remove(0);
					dnum++;
				}
			} else {
				throw BaseException.getException(PacketException.EXCE_YUAN_NOT_ENOUGH, resId, cnt);
			}
		} else if (type == 8) {
			List<RoleCard> curCards = roleCardDAO().querySameResCardByLevel(roleId, resId);
			if (curCards.size() >= cnt) {
				int dnum = 0;
				while (dnum < cnt && curCards.size() != 0) {
					roleCardAO.delete(curCards.get(0));
					curCards.remove(0);
					dnum++;
				}
			} else {
				throw BaseException.getException(PacketException.EXCE_CARD_NOT_ENOUGH, resId, cnt);
			}
		} else if (type == 4) {
			List<RoleGong> curGong = roleGongDAO().querySameResGongLevel(roleId, resId);
			if (curGong.size() >= cnt) {
				int dnum = 0;
				while (dnum < cnt && curGong.size() != 0) {
					roleGongDAO().delete(curGong.get(0));
					curGong.remove(0);
					dnum++;
				}
			} else {
				throw BaseException.getException(PacketException.EXCE_GONG_NOT_ENOUGH, resId, cnt);
			}
		} else if (type == 1) {
			List<Equip> equip = equipDAO().querySameResEquipByLevel(roleId, resId);
			if (equip.size() >= cnt) {
				int dnum = 0;
				while (dnum < cnt && equip.size() != 0) {
					equipDAO().delete(equip.get(0));
					equip.remove(0);
					dnum++;
				}
			} else {
				throw BaseException.getException(PacketException.EXCE_EQUIP_NOT_ENOUGH, resId, cnt);
			}
		}
		if (!addlog)
			return;

		// 道具消耗
		BehaviorLogger.log4Platform(doc, Type.GOODS_COST, Action.ITEM_COST, Params.valueOf("wpnum", cnt),
				Params.valueOf("wpid", resId), Params.valueOf("wptype", type), Params.valueOf("source", sysType),
				Params.valueOf("level", doc.getLevel()), Params.valueOf("vip", doc.getVip()));

		// 添加日志
		RoleItemLog log = new RoleItemLog(doc, sysType, type, resId, -cnt, newNum, "");
		roleItemLogDAO().add(log);
	}

	/**
	 * 检验数值
	 * 
	 * @param doc
	 * @param type
	 * @param id
	 * @param cnt
	 * @return
	 * @throws BizException
	 */
	public int getNumberByTypeId(Role doc, int type, int id) throws BizException {
		String acc = doc.getAccount();
		int roleId = doc.getId();
		int res = 0;
		if (type == 0) {
			int docPropAry[] = { 0, doc.getGold(), doc.getSilver(), doc.getPhysVal(), doc.getResisVal(),
					doc.getPopual(), doc.getExp(), doc.getSoul(), doc.getSocial(), doc.getZhenQiVal(),
					doc.getHunYuVal() };
			res = docPropAry[id];
		} else if (type == 1) {// 装备
			List<Equip> curEquip = equipDAO().querySameResEquip(roleId, id);
			res = curEquip.size();
		} else if (type == 3 || type == 5 || type == 7 || type == 9 || type == 10 || type == 11 || type == 12) {// packet

			RoleItem curItem = packetDAO().queryByAccItemId(roleId, id, type);
			if (curItem != null) {
				res = curItem.getItemCnt();
			}
		} else if (type == 6) {// 精元
			List<RoleYuan> curYuan = roleYuanDAO().querySameResYuan(roleId, id);
			res = curYuan.size();
		} else if (type == 8) {
			List<RoleCard> curCards = roleCardDAO().querySameResCard(roleId, id);
			res = curCards.size();
		} else if (type == 4) {
			List<RoleGong> curGong = roleGongDAO().querySameResGong(roleId, id);
			res = curGong.size();

		}
		return res;
	}

	/**
	 * 检验数值 根据id和等级
	 * 
	 * @param doc
	 * @param type
	 * @param id
	 * @param cnt
	 * @return
	 * @throws BizException 武学： 被穿戴在侠客身上、强化等级 >=1、精炼等于 >= 0//（现在没有）时，不计入材料 装备：
	 *                      被穿戴在侠客身上、强化等级 >=1时，不计入材料 侠客： 已上阵、等级 > 1、已加锁的侠客不计入材料
	 */
	public int getNumberByTypeIdLevel(Role doc, int type, int id) throws BizException {
		String acc = doc.getAccount();
		int roleId = doc.getId();
		int res = 0;
		if (type == 0) {
			int docPropAry[] = { 0, doc.getGold(), doc.getSilver(), doc.getPhysVal(), doc.getResisVal(),
					doc.getPopual(), doc.getExp(), doc.getSoul(), doc.getSocial(), doc.getZhenQiVal(),
					doc.getHunYuVal() };
			res = docPropAry[id];
		} else if (type == 3 || type == 5 || type == 7 || type == 9 || type == 10 || type == 11 || type == 12) {// packet

			RoleItem curItem = packetDAO().queryByAccItemId(roleId, id, type);
			if (curItem != null) {
				res = curItem.getItemCnt();
			}
		} else if (type == 6) {// 精元
			List<RoleYuan> curYuan = roleYuanDAO().querySameResYuanByPos(roleId, id);
			res = curYuan.size();
		} else if (type == 1) {// 装备
			List<Equip> curEquip = equipDAO().querySameResEquipByLevel(roleId, id);
			res = curEquip.size();
		} else if (type == 8) {
			List<RoleCard> curCards = roleCardDAO().querySameResCardByLevel(roleId, id);
			res = curCards.size();
		} else if (type == 4) {
			List<RoleGong> curGong = roleGongDAO().querySameResGongLevel(roleId, id);
			res = curGong.size();
		}
		return res;
	}

	// private void updateRoleProperty(Role doc,String str,int cnt) throws
	// BizException{
	// List<String> docPropAry = Arrays.asList(null, "gold", "silver", "physVal",
	// "resisVal", "popual", "exp", "soul", "social","zhenQiVal","hunYuVal");
	// int id = docPropAry.indexOf(str);
	// updateRoleProperty(doc, id, cnt);
	// }

	private int updateRoleProperty(Role doc, int id, int cnt) throws BizException {
		int res = 0;
		switch (id) {
		case Packet.ATTR_金币:
			res = roleAO.updateGold(doc, cnt);
			if (cnt < 0) {
				missionExecAO.roleGoldCost(doc, Math.abs(cnt));
				// 探宝
				roleActivityRouletteAO.updateDayAdd(Math.abs(cnt), doc, Roulette.消费返利);
			}
			break;
		case Packet.ATTR_银币:
			res = roleAO.updateSilver(doc, cnt);
			break;
		case Packet.ATTR_体力:
			res = roleAO.updateAddPhysVal(doc, cnt);
			break;
		case Packet.ATTR_耐力:
			res = roleAO.updateAddResisVal(doc, cnt);
			break;
		case Packet.ATTR_声望:
			res = roleAO.updateAddPopualVal(doc, cnt);
			break;
		case Packet.ATTR_经验:
			roleAO.addExp(doc, cnt);
			res = doc.getExp();
			break;
		case Packet.ATTR_侠魂:
			res = roleAO.updateSoul(doc, cnt);
			break;
		case Packet.ATTR_贡献:
			RoleUnion roleUnion = roleUnionAO.queryRoleUnionById(doc.getId());
			res = roleUnionAO.updateContribute(roleUnion, cnt);
			break;
		// case 9:
		// doc.setZhenQiVal(doc.getZhenQiVal()+cnt);
		// break;
		case Packet.ATTR_魂玉:
			res = roleAO.updateAddHunYuVal(doc, cnt);
			break;
		case Packet.ATTR_灵石:
			res = roleAO.updateLingShi(doc, cnt);
			break;
		case Packet.ATTR_魅力:
			res = roleAO.updateCharm(doc, cnt);
			break;
		case Packet.ATTR_荣誉:
			res = roleAO.updateHonor(doc, cnt);
			res = doc.getHonor();
			break;
		}
		// roleDAO().gmUpdateValues(doc);
		return res;
	}

	public RoleItem queryByAccItemId(int roleId, int itemId, int type) throws BizException {
		return packetDAO().queryByAccItemId(roleId, itemId, type);
	}

	public List<RoleItem> queryListByAccount(int roleId, int type) throws BizException {
		return packetDAO().queryListByAccount(roleId, type);
	}

	public List<Equip> queryEquipListByAccount(int roleId) {
		return equipDAO().queryListByAccount(roleId);
	}

	public List<RoleGong> queryGongListByAccount(int roleId) {
		return roleGongDAO().queryListByAccount(roleId);
	}

	/**
	 * 发放奖励
	 * 
	 * @param doc
	 * @param awardAry
	 * @throws BizException
	 */
	public void sendAward(Role doc, List<ProbItem> awardAry, int sys, String str) throws BizException {
		for (ProbItem probItem : awardAry) {
			addItem(doc, probItem.getT(), probItem.getId(), probItem.getN(), sys, str);
		}
	}

	// 1装备/2时装/3装备碎片/4内外功/5武将碎片/6精元/7背包/8武将]
	public void checkBagException(Role doc, int panduanId) throws BizException {
		List<PacketExtend> pes = checkBag(doc, panduanId);
		if (pes.size() > 0) {
			PacketExtend pe = pes.get(0);
			switch (pe.getType()) {
			case Packet.POS_EQUIP:
				throw BaseException.getException(PacketException.EXCE_EQUIP_PACKET_FULL);
			case Packet.POS_BAG:
				throw BaseException.getException(PacketException.EXCE_ITEM_PACKET_FULL);
			case Packet.POS_CARD:
				throw BaseException.getException(PacketException.EXCE_CARD_PACKET_FULL);
			case Packet.POS_GONG:
				throw BaseException.getException(PacketException.EXCE_GONG_PACKET_FULL);
			case Packet.POS_YUAN:
				throw BaseException.getException(PacketException.EXCE_YUAN_PACKET_FULL);
			default:
				throw BaseException.getException(PacketException.EXCE_PACKET_FULL);
			}
		}
	}

	public MissionExecAO getMissionExecAO() {
		return missionExecAO;
	}

	public void setMissionExecAO(MissionExecAO missionExecAO) {
		this.missionExecAO = missionExecAO;
	}

	// public Map<Integer,Integer> getMaxGongItem(String sqls,int roleid)
	// {
	// Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	// List<RoleItem> rilist = packetDAO().getMaxGongItem(sqls,roleid);
	// for(RoleItem ri : rilist)
	// {
	// map.put(ri.getItemId(), ri.getItemCnt());
	// }
	// return map;
	// }
}