package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.packet.Bag;
import com.mdy.dzs.data.domain.shop.Pseudo;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.PacketAction;
import com.mdy.dzs.game.domain.calcattack.CalcRoleEquip;
import com.mdy.dzs.game.domain.calcattack.CalcRoleGong;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.packet.PacketListVO;
import com.mdy.dzs.game.domain.prob.RolePseudo;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.dzs.game.exception.PacketException;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

public class PacketActionImpl extends ApplicationAwareAction implements PacketAction {
	final static Logger logger = LoggerFactory.get(ShopActionImpl.class);

	@Override
	public PacketListVO list(String acc, int t) throws BizException {

		Role doc = roleAO().queryExistAccount(acc);
		List<Object> rtnAry = packetAO().getPacketListByType(doc.getId(), t);

		if (t == 1 || t == 4 || t == 6) {// 装备 内外功 精元
			updateCid(doc, rtnAry);
		}
		;
		if (t == 8) {
			packetAO().sortCardList(doc, rtnAry);
		}

		String cardNameAry[] = { "", "", "", "", "", "", "" };
		cardNameAry[0] = doc.getName();
		for (int i = 2; i < doc.getFmtCardAry().size(); i++) {
			Integer cardId = doc.getFmtCardAry().get(i);
			RoleCard rcard = roleCardAO().queryById(cardId);
			if (rcard == null)
				continue;
			Card cardData = cacheManager().getExistValueByKey(Card.class, rcard.getResId());
			cardNameAry[i - 1] = cardData.getName();
		}

		PacketExtend extend = packetAO().getPacketExtend(doc, 0, t);

		PacketListVO vo = new PacketListVO();
		vo.setErr("");
		vo.setItemAry(rtnAry);
		vo.setMember(rtnAry.size());
		vo.setDenominator(extend.getLimit());
		vo.setCost(extend.getCost());
		vo.setSize(extend.getSize());
		vo.cardNameAry = cardNameAry;
		return vo;
	}

	private void updateCid(Role doc, List<Object> itemAry) {
		int cardIdAry[] = { 0, 0, 0, 0, 0, 0 };
		List<Integer> fmtCardAry = doc.getFmtCardAry();
		for (int i = 1; i < fmtCardAry.size(); i++) {
			RoleCard temp = roleCardAO().queryById(fmtCardAry.get(i));
			if (temp != null)
				cardIdAry[i - 1] = temp.getResId();
		}
		for (Object item : itemAry) {
			if (item instanceof Equip) {
				Equip ritem = (Equip) item;
				if (ritem.getPos() > 0)
					ritem.setCid(cardIdAry[ritem.getPos() - 1]);
				else
					ritem.setCid(0);

				CalcRoleEquip calc = calcManager().getCalcEquip(ritem.getResId());
				ritem.setViewBase(calc.getBaseView(ritem.getLevel(), ritem.getProps()));
			} else if (item instanceof RoleGong) {
				RoleGong ritem = (RoleGong) item;
				if (ritem.getPos() > 0)
					ritem.setCid(cardIdAry[ritem.getPos() - 1]);
				else
					ritem.setCid(0);

				CalcRoleGong calc = calcManager().getCalcGong(ritem.getResId());
				ritem.setBaseViewRate(calc.getBaseRate(ritem.getLevel()));
			} else if (item instanceof RoleYuan) {
				RoleYuan ritem = (RoleYuan) item;
				if (ritem.getPos() > 0)
					ritem.setCid(cardIdAry[ritem.getPos() - 1]);
				else
					ritem.setCid(0);
			}
		}
	}

	/*
	 * t: 0货币 id： 1:金币2:银币3:体力4:耐力5:声望6:主角经验7:侠魂8:公会贡献 n: 数量
	 * 
	 * t: 1:装备 2:时装 3:装备碎片 4:内外功 5:武将碎片 6:精元 7:背包8:武将9:内功碎片10:外功碎片 id： id n: 数量
	 */
	@Override
	public void gmAdd(String acc, int type, int id, int cnt) throws BizException {
		packetAO().addItem(acc, type, id, cnt, RoleItemLog.SYS_GM_操作, "");
	}

	@Override
	public List<Serializable> use(String acc, int id, int num) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Item itemData = cacheManager().getExistValueByKey(Item.class, id);
		// additem list
		List<ProbItem> rtnAry = new ArrayList<ProbItem>();
		List<PacketExtend> bagData = null;
		Serializable nullArr[] = {};
		List<Serializable> res;
		boolean isFull = false;

		int para1 = itemData.getPara1().size() != 0 ? itemData.getPara1().get(0) : 0;
		int para2 = itemData.getPara2().size() != 0 ? itemData.getPara2().get(0) : 0;
		int para3 = itemData.getPara3().size() != 0 ? itemData.getPara3().get(0) : 0;

		// 获得道具
		switch (itemData.getEffecttype()) {
			case 0:
				rtnAry.add(new ProbItem(0, para1, para2 * num));
				break;
			case 1:// 获得物品
				Item newData = cacheManager().getExistValueByKey(Item.class, para1);
				rtnAry.add(new ProbItem(newData.getType(), para1, para2 * num));
				break;
			case 2:// 获得武将
				rtnAry.add(new ProbItem(8, para1, para2 * num));
				break;
			case 3:// 开启宝箱
				for (int i = 0; i < num; i++) {
					List<ProbItem> items = cacheManager().probGot(para1);
					rtnAry.addAll(items);
				}
				break;
			case 4:
				for (int i = 0; i < num; i++) {
					rtnAry.add(new ProbItem(6, para1, para2));
				}
				break;
			case 5:
				break;
			case 7:// 开启宝箱
				List<Object> equips = packetAO().getPacketListByType(doc.getId(), Packet.POS_EQUIP);
				List<Object> cards = packetAO().getPacketListByType(doc.getId(), Packet.POS_CARD);
				if (itemData.getId() == 4001 || itemData.getId() == 4002)// 金箱子或则金钥匙进入
				{
					RoleItem statItem = rolePseudoAO().checkBoxPro(doc.getId());
					int boxpro = statItem.getBoxpro(); // 信用数
					int boxnum = statItem.getItemCnt();// 箱子数
					for (int i = 0; i < num; i++) {
						int pseudoType = 0;
						if (boxnum == boxpro ? true : (boxpro > 0 && boxnum % 2 == 0)) {
							boxpro--;
							pseudoType = Pseudo.TYPE_使用道具_金箱子;

						} else {
							pseudoType = Pseudo.TYPE_使用道具_假箱子;
						}
						boxnum--;
						RolePseudo rp = rolePseudoAO().query(doc.getId(), pseudoType);
						rtnAry.addAll(rolePseudoAO().getRewards(rp, false, equips, cards, rtnAry));
					}
					packetAO().updateBoxProServiceTo(doc.getId(), boxpro, 4001);
				} else {
					int pseudoType = rolePseudoAO().getPseudoType(itemData);
					RolePseudo rp = rolePseudoAO().query(doc.getId(), pseudoType);
					for (int i = 0; i < num; i++) {
						rtnAry.addAll(rolePseudoAO().getRewards(rp, false, equips, cards, rtnAry));
					}
				}
				break;
		}

		if (itemData.getEffecttype() == 5) {
			// 消耗物品
			packetAO().removeItemMustEnough(doc, itemData.getType(), id, para1, RoleItemLog.SYS_背包_使用道具,
					"" + itemData.getId());
			// 根据结果添加武将、装备
			List<Object> items = packetAO().addItem(doc, para2, para3, 1, RoleItemLog.SYS_背包_使用道具,
					"" + itemData.getId());
			if (items.get(0) instanceof RoleCard) {
				missionExecAO().synthRoleCard(doc, (RoleCard) items.get(0));
			}
			res = Arrays.asList((Serializable) items);
		} else {
			// 消耗物品
			packetAO().removeItemMustEnough(doc, itemData.getType(), id, num, RoleItemLog.SYS_背包_使用道具,
					"" + itemData.getId());
			if (itemData.getExpend().size() > 0) {
				Item expendItem = cacheManager().getExistValueByKey(Item.class, itemData.getExpend().get(0));
				packetAO().removeItemMustEnough(doc, expendItem.getType(), expendItem.getId(),
						itemData.getExpend().get(1) * num, RoleItemLog.SYS_背包_使用道具, "" + itemData.getId());
				missionExecAO().useItem(doc, id, num);
				missionExecAO().useItem(doc, expendItem.getId(), num);
			}
			// 根据结果添加属性等
			for (ProbItem item : rtnAry) {
				packetAO().addItem(doc, item.getT(), item.getId(), item.getN(), RoleItemLog.SYS_背包_使用道具,
						"" + itemData.getId());
			}
			List<Object> itemAry = packetAO().getPacketListByType(doc.getId(), 7);
			res = Arrays.asList((Serializable) itemAry, (ArrayList<ProbItem>) rtnAry, doc.getGold(), doc.getSilver(),
					isFull, (ArrayList<PacketExtend>) bagData);
		}
		return res;
	}

	@Override
	public List<Serializable> extend(String acc, int type) throws BizException {
		List<Serializable> res;

		Bag bagData = cacheManager().getExistValueByKey(Bag.class, type);

		if (bagData.getNum() == bagData.getMax()) {
			throw PacketException.getException(PacketException.EXCE_NOT_EXTEND, type);
		}
		Role doc = roleAO().queryExistAccount(acc);
		int limit = doc.getBagLimit(type);

		if (limit == bagData.getMax()) {
			throw PacketException.getException(PacketException.EXCE_NOT_EXTEND, type);
		} else {
			PacketExtend extend = packetAO().getPacketExtend(doc, 0, type);
			int gold = extend.getCost();
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, gold, RoleItemLog.SYS_背包_扩展, "");
			roleAO().updateBagLimit(doc, 5, type, true);
			extend = packetAO().getPacketExtend(doc, 0, type);
			res = Arrays.asList(doc.getBagLimit(type), gold, doc.getGold(), (Serializable) extend.getCost(),
					extend.getSize());
		}
		return res;
	}

	@Override
	public List<Serializable> sell(String acc, List<Integer> ids) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		int tSilver = 0;
		for (int index = 0; index < ids.size(); index++) {
			int pid = ids.get(index);
			RoleItem rItem = packetAO().queryByAccItemId(doc.getId(), pid, 7);
			if (rItem == null)
				continue;
			int itemId = rItem.getItemId();
			Item itemData = cacheManager().getExistValueByKey(Item.class, itemId);
			if (itemData == null)
				continue;
			if (itemData.getSale() == 0) {
				throw PacketException.getException(PacketException.EXCE_ITEM_NOT_SELL, itemId);
			}
			if (itemData.getPrice() > 0) {
				int cnt = rItem.getItemLimit();
				cnt = cnt == 0 ? rItem.getItemCnt() : cnt;
				packetAO().removeItemMustEnough(doc, 7, itemId, cnt, RoleItemLog.SYS_背包_出售, "");
				tSilver += itemData.getPrice() * cnt;
			}

		}
		packetAO().addItem(doc, Packet.POS_ATTR, Packet.ATTR_银币, tSilver, RoleItemLog.SYS_背包_出售, "");
		return Arrays.asList(tSilver, (Serializable) doc.getSilver());
	}
}
