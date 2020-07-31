/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.robot.MoBan;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.SnatchAction;
import com.mdy.dzs.game.ao.RoleSnatchAO;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.snatch.BeSnatchCard;
import com.mdy.dzs.game.domain.snatch.BeSnatchRole;
import com.mdy.dzs.game.domain.snatch.ComparatorSnatchItem;
import com.mdy.dzs.game.domain.snatch.RoleSnatch;
import com.mdy.dzs.game.domain.snatch.SnatchItem;
import com.mdy.dzs.game.domain.snatch.SnatchTenVO;
import com.mdy.dzs.game.domain.snatch.ARoundVO;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleGongException;
import com.mdy.dzs.game.exception.SystemException;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月13日 下午2:51:35
 */
public class SnatchActionImpl extends ApplicationAwareAction implements SnatchAction {

	@Override
	public List<Serializable> querySnatchItems(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleSnatch rs = roleSnatchAO().query(doc.getId());

		int warFreeCnt = packetAO().getNumberByTypeId(doc, 7, Constants.snatchWarFreeLicense);
		int resisCnt = packetAO().getNumberByTypeId(doc, 7, Constants.snatchResisBill);

		int now = (int) (new Date().getTime() / 1000);
		int warFreeTime = rs.getWarFreeTime() - now;
		warFreeTime = warFreeTime > 0 ? warFreeTime : 0;

		Map<Integer, SnatchItem> neiItems = snatchAO().generateSnatchMap(doc, Packet.POS_IN_GONG_ITEM);
		Map<Integer, SnatchItem> waiItems = snatchAO().generateSnatchMap(doc, Packet.POS_OUT_GONG_ITEM);
		List<SnatchItem> neiList = new ArrayList<SnatchItem>(neiItems.values());
		List<SnatchItem> waiList = new ArrayList<SnatchItem>(waiItems.values());
		ComparatorSnatchItem compar = new ComparatorSnatchItem();
		Collections.sort(neiList, compar);
		Collections.sort(waiList, compar);

		Map<String, Object> infos = new HashMap<String, Object>();
		infos.put("gold", doc.getGold());
		infos.put("warFreeCnt", warFreeCnt);
		infos.put("warFreeTime", warFreeTime);
		infos.put("resis", doc.getResisVal());
		infos.put("resisCnt", resisCnt);

		return Arrays.asList((Serializable) infos, (Serializable) neiList, (Serializable) waiList);
	}

	@Override
	public List<Serializable> userItem(String acc, int type) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleSnatch rs = roleSnatchAO().query(doc.getId());

		Date now = new Date();
		int nowT = (int) (now.getTime() / 1000);
		int state = 2;
		int warFreeTime = 0;
		int enough = 0;
		do {
			boolean isAvoidTime = snatchAO().isAvoidTime(now);
			if (isAvoidTime) {
				state = 1;
				break;
			}
			switch (type) {
				case 1:// item
					packetAO().removeItemMustEnough(doc, 7, Constants.snatchWarFreeLicense, 1, RoleItemLog.SYS_武学争夺_免战牌,
							"");
					break;
				case 2:// gold
					packetAO().removeItemMustEnough(doc, 0, 1, Constants.snatchWarFreeGold, RoleItemLog.SYS_武学争夺_免战牌,
							"");
					break;
				default:
					throw BaseException.getException(SystemException.EXCE_PARAM_ERROR, "type error");
			}

			warFreeTime = rs.getWarFreeTime();
			if (warFreeTime > nowT) {
				warFreeTime += Constants.snatchWarFreeTime * 60 * 60;
			} else {
				warFreeTime = nowT + Constants.snatchWarFreeTime * 60 * 60;
			}
			enough = packetAO().getNumberByTypeId(doc, 7, Constants.snatchWarFreeLicense);
			rs.setWarFreeTime(warFreeTime);
			roleSnatchAO().update(rs);
		} while (false);
		return Arrays.asList(enough, warFreeTime - nowT, (Serializable) doc.getGold(), state);
	}

	@Override
	public List<Serializable> synthGong(String acc, int id, int type) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Item itemData = cacheManager().getExistValueByKey(Item.class, id);

		int removeCnt = 1;
		if (type != 1) {// 批量合成
			int min = Integer.MAX_VALUE;
			for (Integer itemId : itemData.getPara1()) {
				int count = packetAO().getNumberByTypeId(doc, 9, itemId);
				if (count < min)
					min = count;
			}
			removeCnt = min;
		}
		if (removeCnt < 1) {
			throw BaseException.getException(RoleGongException.EXCE_GONG_SYNTH_NOT_ENOUGH);
		}
		List<PacketExtend> pe = new ArrayList<PacketExtend>();
		int limit = doc.getBagLimit(4);
		List<Object> gongs = packetAO().getPacketListByType(doc.getId(), Packet.POS_GONG);
		if (removeCnt + gongs.size() > limit) {
			// 背包已满
			pe.add(packetAO().getPacketExtend(doc, gongs.size(), 4));
			removeCnt = 0;
		} else {
			for (Integer itemId : itemData.getPara1()) {
				Item data = cacheManager().getExistValueByKey(Item.class, itemId);
				packetAO().removeItemMustEnough(doc, data.getType(), itemId, removeCnt, RoleItemLog.SYS_武学争夺_合成,
						"" + data.getId());
			}
			packetAO().addItem(doc, itemData.getType(), itemData.getId(), removeCnt, RoleItemLog.SYS_武学争夺_合成, "");
		}

		return Arrays.asList((Serializable) id, removeCnt, pe.size() != 0, (Serializable) pe);
	}

	@Override
	public List<Serializable> querySnatchRoles(String acc, int itemId) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		List<Role> docs = new ArrayList<Role>();
		Date date = new Date();
		boolean isAvoidTime = snatchAO().isAvoidTime(date);
		RoleSnatch rs = roleSnatchAO().query(doc.getId());

		if (rs.getSnatched() == 1 && !isAvoidTime) {
			int min = Math.max(12, doc.getLevel() - 3);
			int max = Math.max(12, doc.getLevel() + 3);
			docs = roleAO().querySnatchRoles(acc, min, max, itemId);
		}
		// 如果本组的数量小于2 则将此角色删除
		Item chipData = cacheManager().getExistValueByKey(Item.class, itemId);
		for (Iterator<Role> iterator = docs.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			int cnt = 0;
			for (Integer id : chipData.getPara1()) {
				RoleItem rgis = packetAO().queryByAccItemId(role.getId(), id, Packet.POS_IN_GONG_ITEM);
				if (rgis != null) {
					cnt += rgis.getItemCnt();
				}
			}
			if (cnt < 2) {
				iterator.remove();
			}
		}
		List<BeSnatchRole> listAry = new ArrayList<BeSnatchRole>();

		// init role
		int cnt = Math.min(cacheManager().random(0, 3), docs.size());
		List<Integer> randAry = new ArrayList<Integer>();
		int easyType = 1; // 高
		while (randAry.size() < cnt) {
			int temp = cacheManager().random(0, docs.size() - 1);
			if (randAry.indexOf(temp) < 0) {
				randAry.add(temp);
			}
		}
		for (int i = 0; i < randAry.size(); i++) {
			Role tempData = docs.get(randAry.get(i));
			RoleSnatch tempRSData = roleSnatchAO().query(tempData.getId());
			BeSnatchRole tempObj = new BeSnatchRole();
			int now = (int) ((date.getTime()) / 1000);
			if (chipData.getQuality() >= 4) { // 紫 金
				easyType = 2; // 较高
			}
			tempObj.setAcc(tempData.getAccount());
			tempObj.setLv(tempData.getLevel());
			tempObj.setName(tempData.getName());
			tempObj.setType(BeSnatchRole.TYPE_ROLE); // 真实玩家
			tempObj.setWarFreeTime(tempRSData.getWarFreeTime() - now > 0 ? tempRSData.getWarFreeTime() - now : 0);
			tempObj.setVip(tempData.getVip());
			tempObj.setEasyType(easyType);
			List<BeSnatchCard> tempAry = tempObj.getCard();
			for (int indx = 1; indx < tempData.getFmtCardAry().size() - 1; indx++) {
				int cardId = tempData.getFmtCardAry().get(indx);
				RoleCard cardEle = roleCardAO().queryById(cardId);
				if (cardEle == null)
					continue;
				tempAry.add(new BeSnatchCard(cardEle));
			}
			listAry.add(tempObj);
		}
		// init npc
		MoBan npcData = cacheManager().getExistValueByKey(MoBan.class, doc.getLevel());
		easyType = 2; // 较高
		if (chipData.getQuality() == 4) // 紫
			easyType = 3; // 较低
		else if (chipData.getQuality() == 5) // 金
			easyType = 3; // 低

		for (int i = listAry.size(); i < 4; i++) {// 4个人
			listAry.add(snatchAO().createBeSnatchNPC(doc, easyType, npcData));
		}
		List<PacketExtend> checkObj = packetAO().checkBag(doc, 11);
		return Arrays.asList((Serializable) listAry,
				(Serializable) Arrays.asList(-2, doc.getResisVal(), doc.getPropLimitAry().get(1)), checkObj.size() != 0,
				(Serializable) checkObj);
	}

	@Override
	public List<Serializable> snatch(String acc, BeSnatchRole role, int itemId) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleSnatch rs = roleSnatchAO().query(doc.getId());
		packetAO().checkBagException(doc, 11);
		Item item = cacheManager().getExistValueByKey(Item.class, itemId);
		int toId = item.getPara3().get(0);
		boolean noCheck = Constants.snatchNeiAry.indexOf(toId) != -1;
		noCheck = noCheck || Constants.snatchWaiAry.indexOf(toId) != -1;
		if (!noCheck) {// 验证内外功碎片数量是否都为0
			int cnt = 0;
			for (Integer id : item.getPara1()) {
				RoleItem rgis = packetAO().queryByAccItemId(doc.getId(), id, Packet.POS_IN_GONG_ITEM);
				if (rgis != null) {
					cnt += rgis.getItemCnt();
				}
			}
			if (cnt == 0) {
				throw BaseException.getException(RoleGongException.EXCE_GONG_SYNTH_ITEMS_NULL);
			}
		}

		// 验证碎片是否已有
		if (packetAO().getNumberByTypeId(doc, 9, itemId) >= 1) {
			throw BaseException.getException(RoleGongException.EXCE_GONG_SYNTH_SELF_HAVE);
		}

		FighterInfo srcInfo = fightAO().createFighterInfoByRole(doc);
		FighterInfo tgtInfo = null;
		MoBan lvData = cacheManager().getExistValueByKey(MoBan.class, doc.getLevel());

		Role tgtDoc = null;
		switch (role.getType()) {
			case BeSnatchRole.TYPE_ROLE:
				tgtDoc = roleAO().queryExistAccount(role.getAcc());
				RoleSnatch tgtRS = roleSnatchAO().query(tgtDoc.getId());
				if (packetAO().getNumberByTypeId(tgtDoc, 9, itemId) < 1) {
					return Arrays.asList((Serializable) "", (Serializable) null, null, null, null, 0, 0, 0, 2);
				}
				long nowTime = (new Date().getTime()) / 1000;
				if (tgtRS.getWarFreeTime() - nowTime > 0) {// 对方处于免战时间不能抢夺
					return Arrays.asList((Serializable) "", (Serializable) null, null, null, null, 0, 0, 0, 3);
				}

				rs.setWarFreeTime(0);
				tgtInfo = fightAO().createFighterInfoByRole(tgtDoc);
				// 如果本组的数量小于2 则将此角色删除
				int cnt = 0;
				for (Integer id : item.getPara1()) {
					RoleItem rgis = packetAO().queryByAccItemId(tgtDoc.getId(), id, Packet.POS_IN_GONG_ITEM);
					if (rgis != null)
						cnt += rgis.getItemCnt();
				}
				if (cnt < 2) {
					return Arrays.asList((Serializable) "", (Serializable) null, null, null, null, 0, 0, 0, 2);
				}
				break;
			case BeSnatchRole.TYPE_NPC:
				tgtInfo = fightAO().createFighterInfoByCardIds(role.getCard(), lvData);
				break;
		}

		// 每次抢夺消耗2点耐力
		packetAO().removeItemMustEnough(doc, 0, 4, 2, RoleItemLog.SYS_武学争夺_争夺, "" + itemId);
		// fight
		FightMain main = new FightMain(srcInfo, tgtInfo, null);
		// ======================================设置战斗状态===============================//
		int state = -1;
		if (tgtDoc != null) {
			state = roleSnatchAO().queryState(tgtDoc.getId());// 查询状态
			if (state == RoleSnatchAO.FINGHTING) {
				throw BaseException.getException(RoleGongException.EXCE_GONG_SYNTH_FIGHT);
			} else if (state == RoleSnatchAO.END_FIGHT) {
				roleSnatchAO().updateState(tgtDoc.getId(), RoleSnatchAO.FINGHTING);
			}
		}
		// ==
		FightResult result = main.fight();

		// res
		int isGetItem = 0;
		List<ProbItem> coinAry = new ArrayList<ProbItem>();
		List<ProbItem> rtnAry = new ArrayList<ProbItem>();
		int oldLv = doc.getLevel();
		int oldExp = doc.getExp();
		Item itemData = cacheManager().getExistValueByKey(Item.class, itemId);

		if (result.getWin() == 1) {
			switch (role.getType()) {
				case BeSnatchRole.TYPE_ROLE:
					int deprob = lvData.getDedrop().get(itemData.getQuality() - 1);
					if (cacheManager().random(0, 10000) < deprob) {// 取得碎片
						packetAO().removeItemMustEnough(tgtDoc, 9, itemId, 1, RoleItemLog.SYS_武学争夺_争夺, "" + itemId);
						isGetItem = itemId;
						mailAO().snatchLoseMail(tgtDoc.getId(), doc, itemData);// 发给被抢人邮件通知
					}
					break;
				case BeSnatchRole.TYPE_NPC:
					int drop = lvData.getDrop().get(itemData.getQuality() - 1);
					if (cacheManager().random(0, 10000) < drop) {// 取得碎片
						isGetItem = itemId;
					}
					break;
			}
			if (rs.getSnatched() == 0 || rs.getSnatchFailList().get(item.getQuality() - 1) >= lvData.getMax()
					.get(item.getQuality() - 1)) {// 第一次夺宝 || 当前品质抢夺失败次数达到上限 ==>让他得到碎片
				isGetItem = itemId;
			}

			List<Integer> newFailList = rs.getSnatchFailList();
			if (isGetItem > 0) {// 得到碎片，则改品质失败计数清0
				newFailList.set(item.getQuality() - 1, 0);
				rs.setSnatchFailList(newFailList);
			} else {
				int failCnt = rs.getSnatchFailList().get(item.getQuality() - 1);
				newFailList.set(item.getQuality() - 1, failCnt + 1);
				rs.setSnatchFailList(newFailList);
			}

			if (isGetItem != 0) {
				packetAO().addItem(doc, itemData.getType(), itemId, 1, RoleItemLog.SYS_武学争夺_争夺, "");
			}
			packetAO().addItem(doc, 0, 2, doc.getLevel() * 100, RoleItemLog.SYS_武学争夺_争夺, "");// 银币
			packetAO().addItem(doc, 0, 6, doc.getLevel() * 2, RoleItemLog.SYS_武学争夺_争夺, "");// 经验
			coinAry.add(new ProbItem(0, 2, doc.getLevel() * 100));
			if (doc.getLevel() != oldLv || doc.getExp() != oldExp) {
				coinAry.add(new ProbItem(0, 6, doc.getLevel() * 2));
			}
			rtnAry.addAll(cacheManager().probGot(lvData.getProb()));// 获得掉落
			rtnAry.addAll(cacheManager().probGot(lvData.getProb()));
			rtnAry.addAll(cacheManager().probGot(lvData.getProb()));
			packetAO().addItem(doc, rtnAry.get(0), RoleItemLog.SYS_武学争夺_争夺, "");
		} else {
			packetAO().addItem(doc, 0, 2, doc.getLevel() * 20, RoleItemLog.SYS_武学争夺_争夺, "");// 银币
			packetAO().addItem(doc, 0, 6, doc.getLevel() * 1, RoleItemLog.SYS_武学争夺_争夺, "");// 经验
			coinAry.add(new ProbItem(0, 2, doc.getLevel() * 20));
			if (doc.getLevel() != oldLv || doc.getExp() != oldExp) {
				coinAry.add(new ProbItem(0, 6, doc.getLevel() * 1));
			}

		}
		if (rs.getSnatched() == 0)
			rs.setSnatched(1);

		roleSnatchAO().update(rs);
		int attack = 0;
		switch (role.getType()) {
			case BeSnatchRole.TYPE_ROLE:
				attack = tgtDoc.getAttack();
				break;
			case BeSnatchRole.TYPE_NPC:
				List<Card> list = new ArrayList<Card>();
				for (int i = 0; i < tgtInfo.getCards().size(); i++) {
					Card card = (Card) tgtInfo.getCards().get(i);
					list.add(card);
				}
				attack = roleAO().battleCalc(list);
				break;
		}
		// ========================================战斗结束=====================================//
		if (tgtDoc != null) {
			roleSnatchAO().updateState(tgtDoc.getId(), RoleSnatchAO.END_FIGHT);
		}
		// 任务
		missionExecAO().snatch(doc, 1);
		// ==
		return Arrays.asList((Serializable) Arrays.asList(result.getWin()),
				(Serializable) Arrays.asList(result.getMsg()), (Serializable) rtnAry, (Serializable) coinAry, isGetItem,
				2, attack, 1, doc.getLevel(), doc.getExp());

	}

	@Override
	public SnatchTenVO snatchTen(String acc, BeSnatchRole role, int itemId) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleSnatch player = roleSnatchAO().query(doc.getId());
		packetAO().checkBagException(doc, 11);

		if (packetAO().getNumberByTypeId(doc, 9, itemId) >= 1) {// 验证碎片是否已有
			throw BaseException.getException(RoleGongException.EXCE_GONG_SYNTH_SELF_HAVE);
		}
		if (doc.getResisVal() < 2 * 10) {// 验证耐力是否容许消耗10次
			throw BaseException.getException(RoleGongException.EXCE_GONG_RESIS_NOT_ENOUGH);
		}

		Item item = cacheManager().getExistValueByKey(Item.class, itemId);
		MoBan lvData = cacheManager().getExistValueByKey(MoBan.class, doc.getLevel());
		int drop = lvData.getDrop().get(item.getQuality() - 1);
		List<Integer> newFailList = player.getSnatchFailList();
		List<ARoundVO> rtnAry = new ArrayList<ARoundVO>();
		List<ProbItem> getProbAry = new ArrayList<ProbItem>();
		int isGetItem = 2;// 未获得
		int getIndex = 0;
		int level = doc.getLevel();
		int exp = doc.getExp();
		int removeResis = 0;
		int getSilver = 0;

		for (int i = 1; i <= 10; i++) {
			List<ProbItem> coinAry = new ArrayList<ProbItem>();
			// 每次抢夺消耗2点耐力
			removeResis += 2;
			getIndex = i;
			if (cacheManager().random(0, 10000) < drop) {// 取得碎片
				isGetItem = 1;
			}
			// 当前品质抢夺失败次数达到上限 ==>让他得到碎片
			if (player.getSnatchFailList().get(item.getQuality() - 1) >= lvData.getMax().get(item.getQuality() - 1)) {
				isGetItem = 1;
			}
			ProbItem probItem = cacheManager().probGot(lvData.getProb()).get(0);
			getProbAry.add(probItem);

			// 银币累积，经验累加
			getSilver += doc.getLevel() * 100;
			packetAO().addItem(doc, Packet.POS_ATTR, Packet.ATTR_经验, doc.getLevel() * 2, RoleItemLog.SYS_武学争夺_争夺, "");

			// 前端
			int addExp = 0;
			coinAry.add(new ProbItem(Packet.POS_ATTR, Packet.ATTR_银币, doc.getLevel() * 100));
			coinAry.add(new ProbItem(Packet.POS_ATTR, Packet.ATTR_耐力, -2));
			if (doc.getLevel() != level || doc.getExp() != exp) {// 未满级
				addExp = doc.getLevel() * 2;
			}
			coinAry.add(new ProbItem(Packet.POS_ATTR, Packet.ATTR_经验, addExp));
			rtnAry.add(new ARoundVO(probItem, coinAry));

			if (isGetItem == 1) {
				// 品质夺取上限更新
				newFailList.set(item.getQuality() - 1, 0);
				// 加碎片
				packetAO().addItem(doc, item.getType(), itemId, 1, RoleItemLog.SYS_武学争夺_争夺, "");
				break;
			}
			// 品质夺取上限更新，未获得碎片
			int failCnt = player.getSnatchFailList().get(item.getQuality() - 1);
			newFailList.set(item.getQuality() - 1, failCnt + 1);

			level = doc.getLevel();
			exp = doc.getExp();
		}
		// 更新player
		roleSnatchAO().update(player);
		// 任务
		missionExecAO().snatch(doc, getIndex);
		// 添加翻牌获得物品
		packetAO().addItem(doc, getProbAry, RoleItemLog.SYS_武学争夺_夺十次, "");
		// 加银币
		packetAO().addItem(doc, 0, 2, getSilver, RoleItemLog.SYS_武学争夺_夺十次, "");
		// 抢夺消耗耐力
		packetAO().removeItemMustEnough(doc, 0, 4, removeResis, RoleItemLog.SYS_武学争夺_夺十次, "" + itemId);

		return new SnatchTenVO(isGetItem, getIndex, doc.getLevel(), doc.getExp(), rtnAry, doc.getResisVal());
	}
}
