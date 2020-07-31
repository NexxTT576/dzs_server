/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.battle.Battle;
import com.mdy.dzs.data.domain.battle.Npc;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.shentong.ShenTong;
import com.mdy.dzs.data.domain.world.Field;
import com.mdy.dzs.data.domain.world.World;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.BattleAction;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.battle.BattleFieldInfoVO;
import com.mdy.dzs.game.domain.battle.BattleFieldStarInfoVO;
import com.mdy.dzs.game.domain.battle.BattleFieldVO;
import com.mdy.dzs.game.domain.battle.BattleWorldVO;
import com.mdy.dzs.game.domain.battle.LvNum;
import com.mdy.dzs.game.domain.battle.RoleBattle;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.BattleException;
import com.mdy.dzs.game.exception.RoleException;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.main.FightCfg;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日 下午12:00:22
 */
public class BattleActionImpl extends ApplicationAwareAction implements BattleAction {

	final static Logger logger = LoggerFactory.get(BattleActionImpl.class);

	@Override
	public BattleWorldVO queryWorldInfo(String acc, int id) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());

		if (id == 0)
			id = rb.getBattleWorldID();
		World worldItem = cacheManager().getExistValueByKey(World.class, id);

		List<Integer> fieldAry = worldItem.getArrField();
		Map<Integer, Integer> fieldStarAry = new HashMap<Integer, Integer>();
		boolean fullField = true;
		boolean fullLv = true;
		for (Integer curField : fieldAry) {
			if (rb.getBattleFieldID() < curField) {
				fieldStarAry.put(curField, 0);
				fullField = false;
			} else {
				Field fieldItem = cacheManager().getExistValueByKey(Field.class, curField);
				List<Integer> lvAry = fieldItem.getArrBattle();
				int starSum = 0;
				for (Integer curLv : lvAry) {
					if (curLv > rb.getBattleLvID()) {
						fullLv = false;
						break;
					}
					LvNum lvItem = cacheManager().getLvNumBylv(rb.getBattleHis(), curLv);
					if (lvItem != null) {
						if (lvItem.getN() == 0)
							fullLv = false;
						starSum += lvItem.getN();
					} else {
						Battle lvData = cacheManager().getExistValueByKey(Battle.class, curLv);
						// console.log(curLv, lvData);
						starSum += lvData.getStar();
					}
				}
				;
				fieldStarAry.put(curField, starSum);
			}
		}
		;
		// 自动开启新副本
		if (fullField && fullLv) {
			logger.info("check lv is not up! reset!" + rb.getBattleWorldID() + "," + rb.getBattleFieldID() + ","
					+ rb.getBattleLvID());
			World nxWorldData = cacheManager().getValueByKey(World.class, rb.getBattleWorldID() + 1);
			if (nxWorldData != null) {
				// console.log(nxWorldData);
				rb.setBattleWorldID(nxWorldData.getId());
				rb.setBattleFieldID(nxWorldData.getArrField().get(0));
				;
				Field nxLvData = cacheManager().getExistValueByKey(Field.class, rb.getBattleFieldID());
				// console.log(nxLvData)
				rb.setBattleLvID(nxLvData.getArrBattle().get(0));
				rb.getBattleHis().add(new LvNum(rb.getBattleLvID(), 0));
				roleBattleAO().update(rb);
			}
		}

		BattleWorldVO vo = new BattleWorldVO();
		vo.setBattleWorldID(rb.getBattleWorldID());
		vo.setBattleFieldID(rb.getBattleFieldID());
		vo.setBattleLvID(rb.getBattleLvID());
		vo.setFieldStarAry(fieldStarAry);
		return vo;
	}

	@Override
	public BattleFieldVO queryFieldInfo(String acc, int id) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());
		Field fieldItem = cacheManager().getExistValueByKey(Field.class, id);
		Map<Integer, BattleFieldStarInfoVO> rtnAry = new HashMap<Integer, BattleFieldStarInfoVO>();
		int starCnt = 0;
		List<Integer> battleAry = fieldItem.getArrBattle();
		for (Integer curLv : battleAry) {
			BattleFieldStarInfoVO lvObj = new BattleFieldStarInfoVO();
			if (rb.getBattleLvID() < curLv)
				lvObj.setStar(0);
			else {
				LvNum curLvItem = cacheManager().getLvNumBylv(rb.getBattleHis(), curLv);
				Battle lvData = cacheManager().getExistValueByKey(Battle.class, curLv);
				int lianzhan = lvData.getLianzhan();
				int curStar = curLvItem != null ? curLvItem.getN() : lvData.getStar();
				lvObj.setStar(curStar);
				starCnt += curStar;
				LvNum curStatLvItem = cacheManager().getLvNumBylv(rb.getBattleDayHitStat(), curLv);
				if (curStatLvItem != null) {
					lvObj.setCnt(lvData.getNumber() - curStatLvItem.getN());
					lvObj.setMax(lvObj.getCnt() < lianzhan ? lvObj.getCnt() : lianzhan);
				} else {
					lvObj.setCnt(lvData.getNumber());
					lvObj.setMax(lianzhan);
				}
			}
			rtnAry.put(curLv, lvObj);
		}
		;

		// 设置礼包可领取状态
		LvNum boxItem = cacheManager().getLvNumBylv(rb.getBattleBox(), id);
		int boxAry[] = { 0, 0, 0 };// 1未领取不可领/2未领取可领/3已领取
		int starLimitAry[] = { fieldItem.getStar1(), fieldItem.getStar2(), fieldItem.getStar3() };
		if (boxItem != null) {
			// 设置3已领取
			int s1 = (int) Math.floor(boxItem.getN() % 10);
			int s2 = (int) Math.floor(boxItem.getN() % 100 / 10);
			int s3 = (int) Math.floor(boxItem.getN() % 1000 / 100);
			int sAry[] = { s1, s2, s3 };
			for (int j = 0; j < sAry.length; j++) {
				if (sAry[j] != 0)
					boxAry[j] = 3;
			}
		}
		for (int i = 0; i < starLimitAry.length; i++) {
			int v = starLimitAry[i];
			// 3已领取
			if (boxAry[i] == 3)
				continue;
			//// 1未领取不可领/2未领取可领/
			if (v != 0) {
				boxAry[i] = (v <= starCnt) ? 2 : 1;
			} else {
				boxAry[i] = 1;
			}
		}

		int tNow = (int) Math.floor(new Date().getTime() / 1000);
		int nxtHitTime = (int) Math.floor(rb.getBattleNHitTime().getTime() / 1000);

		BattleFieldInfoVO infoVo = new BattleFieldInfoVO();
		infoVo.setBox(parseIntToInteger(boxAry));
		infoVo.setStars(starCnt);
		infoVo.setSecWait(((tNow > nxtHitTime) || (nxtHitTime == 0)) ? 0 : (nxtHitTime - tNow));
		infoVo.setCdClrCnt((rb.getBattleDayNHitClearCnt() + 1) * Constants.battleClearCDSpend);

		BattleFieldVO vo = new BattleFieldVO();
		vo.setStarInfoVOs(rtnAry);
		vo.setInfoVO(infoVo);
		vo.setBagVO(packetAO().checkBag(doc, 7));
		return vo;
	}

	public Map<String, Integer> buyBattleCnt(String acc, int id, int act) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());
		int buyCnt = rb.getBattleDayBuyCnt();
		LvNum indStat = cacheManager().getLvNumBylv(rb.getBattleDayHitStat(), id);
		Battle lvData = cacheManager().getExistValueByKey(Battle.class, id);
		if (indStat == null) {
			throw BaseException.getException(BattleException.EXCE_BATTLES_CAN_NOT_BUY);
		}
		int itemId = Constants.battleSpendItem;
		int num = packetAO().getNumberByTypeId(doc, Packet.POS_BAG, itemId);
		if (act == 2 && indStat.getN() == lvData.getNumber()) {// 购买
			indStat.setN(0);
			if (num > 0) {
				num -= 1;
				packetAO().removeItemMustEnough(doc, Packet.POS_BAG, itemId, 1, RoleItemLog.SYS_副本_购买次数,
						String.valueOf(id));
			} else {
				buyCnt += 1;
				packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, 10 + buyCnt * 10,
						RoleItemLog.SYS_副本_购买次数, String.valueOf(id));
			}
			rb.setBattleDayBuyCnt(buyCnt);
			roleBattleAO().update(rb);
		}

		Map<String, Integer> cntInfo = new HashMap<String, Integer>();
		cntInfo.put("id", id);
		cntInfo.put("surplusCnt", lvData.getNumber() - indStat.getN());// 剩余次数
		cntInfo.put("buyCnt", buyCnt);// 已购买次数
		cntInfo.put("itemId", itemId);// 购买次数所需花费道具
		cntInfo.put("num", num);// 购买次数所需花费道具数量
		cntInfo.put("spend", 10 + (buyCnt + 1) * Constants.battleSpendGold);// 购买次数所需花费金钱
		cntInfo.put("gold", doc.getGold());// 人物金钱
		return cntInfo;
	}

	@Override
	public List<Serializable> battle(String acc, int id, int type) throws BizException {

		Role doc = roleAO().queryExistAccount(acc);
		packetAO().checkBagException(doc, 7);
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());

		Battle lvData = cacheManager().getExistValueByKey(Battle.class, id);
		if (doc.getPhysVal() < lvData.getPower()) {
			throw BaseException.getException(RoleException.EXCE_PHYVAL_NOT_ENOUGH, doc.getPhysVal(), lvData.getPower());
		}
		// find field
		Field fieldData = cacheManager().getExistValueByKey(Field.class, lvData.getField());
		List<Integer> lvAry = fieldData.getArrBattle();
		if (lvData.getField() > rb.getBattleFieldID()) {
			throw BaseException.getException(BattleException.EXCE_BATTLE_FIELD_NOT_OPEN);
		} else if (rb.getBattleLvID() < id) {
			throw BaseException.getException(BattleException.EXCE_BATTLE_LEVEL_NOT_OPEN);
		}

		// 验证剩余挑战次数
		LvNum indStat = cacheManager().getLvNumBylv(rb.getBattleDayHitStat(), id);
		if (indStat != null) {
			if (lvData.getNumber() <= indStat.getN()) {
				throw BaseException.getException(BattleException.EXCE_BATTLE_NUM_NOTENOUTH);
			}
		}

		// find world
		World worldData = cacheManager().getExistValueByKey(World.class, fieldData.getWorld());
		// type, hisData
		LvNum hisData = cacheManager().getLvNumBylv(rb.getBattleHis(), id);
		int nid = 0;
		switch (type) {
			case 1:
				nid = lvData.getNpc1();
				break;
			case 2:
				nid = lvData.getNpc2();
				break;
			case 3:
				nid = lvData.getNpc3();
				break;
		}
		// =======剧情战=========//
		int movieBattle = 0;
		if (lvData.getSbattle() == 2) {
			// ==================判定此剧情战是否打过===================//
			int index = rb.getOverMBattles().indexOf(id);

			if (-1 == index) { // 没有打过
				// TODO 没有打过 如果要开通剧情战斗 这里注释取消掉
				nid = lvData.getNpc4();
				movieBattle = 1;
			}
		}

		Npc npcData = cacheManager().getExistValueByKey(Npc.class, nid);
		// console.log('hisData:', hisData, lvData);
		if (hisData != null && (type - hisData.getN()) > 1) {
			throw BaseException.getException(BattleException.EXCE_BATTLE_TYPE_NOT_OPEN);
		}
		// ----------------------------------------
		// init movie—battle
		List<Object> bCards = Arrays.asList(null, null, null, null, null, null);
		List<Object> npcAry = Arrays.asList(null, null, null, null, null, null);
		List<List<Prop>> opt1 = Arrays.asList(null, null, null, null, null, null);
		List<List<Prop>> opt2 = Arrays.asList(null, null, null, null, null, null);
		RoleCard mainCard = roleCardAO().queryExistCardById(doc, doc.getFmtMainCardID());
		int sPos = mainCard.getPos();
		if (lvData.getSbattle() == 2 && movieBattle == 1) {
			List<Integer> cardIds = lvData.getArrCard();
			List<Integer> cardPoss = lvData.getArrPos();
			List<Integer> natures = lvData.getArrNature();
			List<Integer> nums = lvData.getArrNum();
			if (cardIds.size() > 0 && cardPoss.size() > 0) {
				for (int i = 0; i < cardIds.size(); i++) {
					// 剧情战 助阵npc初始化
					Card cardData = cacheManager().getValueByKey(Card.class, cardIds.get(i));
					RoleCard npcCard = null;
					if (cardData != null) {
						npcCard = new RoleCard();
						npcCard.setResId(cardData.getId());
						// curCard.baseMod = cardData.baseMod.concat();
						npcCard.setStar(cardData.getStar().get(0));
						npcCard.setShenIDAry(new ArrayList<Integer>());
						for (int j = 0; j < cardData.getTalent().size(); j++) {
							int talentGrpId = cardData.getTalent().get(j);
							ShenTong talentData = cacheManager().getExistValueByKey(ShenTong.class, talentGrpId);
							npcCard.getShenIDAry().add(talentData.getArrTalent().get(0));
						}
						npcCard.setShenLvAry(Arrays.asList(1, 1, 1));
					}

					bCards.set(cardPoss.get(i) - 1, npcCard);

				}
				int lastPos = cardPoss.get(cardPoss.size() - 1) - 1;
				bCards.set(lastPos, mainCard);
				sPos = lastPos;
				opt1.set(lastPos, new ArrayList<Prop>());
				List<Prop> op = opt1.get(lastPos);
				List<Prop> props = RoleAttackCalcManager.convProps(roleAO().calcCardProps(doc, mainCard));
				op.addAll(props);
				for (int j = 0; j < natures.size(); j++) {
					op.add(new Prop(natures.get(j), nums.get(j)));
				}
				// console.log('opt1:', opt1);
			} else {
				throw BaseException.getException(BattleException.EXCE_BATTLE_MOVIEDATA_ERROR, id);
			}

		} else {
			for (int i = 1; i <= 6; i++) {
				// card
				RoleCard cardItem = roleCardAO().queryByRoleIdPos(doc.getId(), i);
				bCards.set(i - 1, cardItem);
				if (cardItem != null) {
					List<Prop> props = RoleAttackCalcManager.convProps(roleAO().calcCardProps(doc, cardItem));
					opt1.set(i - 1, props);
				}
			}
		}
		int npcs[] = { 0, npcData.getNpc1(), npcData.getNpc2(), npcData.getNpc3(), npcData.getNpc4(), npcData.getNpc5(),
				npcData.getNpc6() };
		int dPos = 0;
		for (int i = 1; i <= 6; i++) {
			// npc
			if (npcs[i] != 0) {
				Card cardData = cacheManager().getValueByKey(Card.class, npcs[i]);
				if (cardData != null) {
					npcAry.set(i - 1, cardData);
					if (dPos != 0)
						dPos = i;
				}
			}
		}
		FightCfg cfg = new FightCfg();
		cfg.setsPos(sPos);
		cfg.setdPos(dPos);
		cfg.setNpcid(nid);

		FightMain main = new FightMain(bCards, npcAry, opt1, cfg);
		FightResult result = main.fight();
		Map<String, Object> rst = result.getMsg();

		// rtn:[1/2,recs]
		List<ProbItem> rtnItemAry = null;
		List<ProbItem> rstAry = new ArrayList<ProbItem>();
		if (result.getWin() == 1) {
			int itemLogType = RoleItemLog.SYS_副本_简单副本_1次;
			if (type == 2)
				itemLogType = RoleItemLog.SYS_副本_困难副本_1次;
			if (type == 3)
				itemLogType = RoleItemLog.SYS_副本_困难副本_1次;

			packetAO().removeItemMustEnough(doc, 0, 3, lvData.getPower(), itemLogType, String.valueOf(id));

			List<Integer> coinType = lvData.getCoin(type);
			List<Integer> gotNum = lvData.getNum(type);
			// console.log('pve:', coinType, gotNum);
			packetAO().addItem(doc, Packet.POS_ATTR, coinType.get(0), gotNum.get(0), itemLogType, String.valueOf(id));
			packetAO().addItem(doc, Packet.POS_ATTR, coinType.get(1), gotNum.get(1), itemLogType, String.valueOf(id));
			// 掉落
			rtnItemAry = cacheManager().probGot(lvData.getDrop());
			// 限时掉落
			List<ProbItem> extra = missionExecAO().probGotExtra(doc, lvData.getDrop());
			if (extra != null)
				rtnItemAry.addAll(extra);

			// console.log('pve:', rtnItemAry);
			for (ProbItem item : rtnItemAry) {
				packetAO().addItem(doc, item.getT(), item.getId(), item.getN(), itemLogType, String.valueOf(id));
			}
			int sumExp = 10 * doc.getLevel();
			int oldLv = doc.getLevel();
			int oldExp = doc.getExp();
			packetAO().addItem(doc, Packet.POS_ATTR, Packet.ATTR_经验, sumExp, itemLogType, String.valueOf(id));
			rstAry.add(new ProbItem(0, coinType.get(0), gotNum.get(0)));
			rstAry.add(new ProbItem(0, coinType.get(1), gotNum.get(1)));
			if (doc.getLevel() != oldLv || doc.getExp() != oldExp) {
				rstAry.add(new ProbItem(0, 6, sumExp));
			}
			if (lvData.getSbattle() == 2 && movieBattle == 1) {
				// 将打赢的battleId放入已经打过的数组中
				rb.getOverMBattles().add(id);
			}

			// update dayStat
			// 赢了 增加已经完成的挑战次数
			if (indStat != null) {
				indStat.setN(indStat.getN() + 1);
			} else {
				LvNum statObj = new LvNum(id, 1);
				rb.getBattleDayHitStat().add(statObj);
			}

			// update level data
			if (hisData != null && hisData.getN() <= type) {
				if (type > hisData.getN()) {
					hisData.setN(type);
					rb.setBattleTotalStars(rb.getBattleTotalStars() + 1);
				}
				if (hisData.getN() == lvData.getStar()) {
					rb.getBattleHis().remove(hisData);
					// console.log('splice:', indHis[0],indHis);
				}
			}
			if (rb.getBattleWorldID() == fieldData.getWorld() && lvData.getField() == rb.getBattleFieldID()
					&& rb.getBattleLvID() == id && type == 1) {
				// push new level
				// console.log('lvAry:', lvAry);
				int lvPos = lvAry.indexOf(rb.getBattleLvID());
				if (lvPos < (lvAry.size() - 1)) {
					rb.setBattleLvID(lvAry.get(lvPos + 1));
					rb.getBattleHis().add(new LvNum(rb.getBattleLvID(), 0));
				} else {
					// push new field
					List<Integer> arrField = worldData.getArrField();
					Field nxLvData;
					int fieldPos = arrField.indexOf(rb.getBattleFieldID());
					if (fieldPos < (arrField.size() - 1)) {
						rb.setBattleFieldID(arrField.get(fieldPos + 1));
						nxLvData = cacheManager().getExistValueByKey(Field.class, rb.getBattleFieldID());
						rb.setBattleLvID(nxLvData.getArrBattle().get(0));
						rb.getBattleHis().add(new LvNum(rb.getBattleLvID(), 0));
					} else {
						// push new world
						World nxWorldData = cacheManager().getValueByKey(World.class, worldData.getId() + 1);
						if (nxWorldData != null) {
							// console.log(nxWorldData);
							rb.setBattleWorldID(nxWorldData.getId());
							rb.setBattleFieldID(nxWorldData.getArrField().get(0));
							;
							nxLvData = cacheManager().getExistValueByKey(Field.class, rb.getBattleFieldID());
							// console.log(nxLvData)
							rb.setBattleLvID(nxLvData.getArrBattle().get(0));
							rb.getBattleHis().add(new LvNum(rb.getBattleLvID(), 0));
						}

					}
				}
			}
		}
		roleBattleAO().update(rb);
		// TASK
		missionExecAO().battle(doc);
		missionExecAO().battleCnt(doc, 1);

		int aa[] = { result.getWin() };
		return Arrays.asList(aa, (Serializable) Arrays.asList(rst), (Serializable) rtnItemAry, (Serializable) rstAry,
				doc.getLevel(), doc.getExp(), movieBattle);
	}

	@Override
	public int clearCD(String acc, int t) throws BizException {

		Role doc = roleAO().queryExistAccount(acc);
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());

		switch (t) {
			case 1:
				// packetAO().removeItemMustEnough(doc,
				// Packet.POS_BAG,Constants.battleCDClearItem, 1);
				break;
			case 2:
				int money = (rb.getBattleDayNHitClearCnt() + 1) * Constants.battleClearCDSpend;
				packetAO().removeItemMustEnough(doc, 0, 1, money, RoleItemLog.SYS_副本_清除CD, "");
				rb.setBattleDayNHitClearCnt(rb.getBattleDayNHitClearCnt() + 1);
				break;
		}
		rb.setBattleNHitTime(new Date());
		roleBattleAO().update(rb);
		return doc.getGold();
	}

	@Override
	public List<Serializable> award(String acc, int id, int t) throws BizException {
		Field fieldItem = cacheManager().getExistValueByKey(Field.class, id);

		List<Integer> packetAry = null;
		List<Integer> numAry = null;
		int starLimit = 0;
		switch (t) {
			case 1:
				packetAry = fieldItem.getArrReward1();
				numAry = fieldItem.getArrNum1();
				starLimit = fieldItem.getStar1();
				break;
			case 2:
				packetAry = fieldItem.getArrReward2();
				numAry = fieldItem.getArrNum2();
				starLimit = fieldItem.getStar2();
				break;
			case 3:
				packetAry = fieldItem.getArrReward3();
				numAry = fieldItem.getArrNum3();
				starLimit = fieldItem.getStar3();
				break;
		}
		if (packetAry == null || numAry == null) {
			throw BaseException.getException(BattleException.EXCE_BATTLE_BOX_AWARD_TYPE_ERROR);
		}

		Role doc = roleAO().queryExistAccount(acc);
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());
		int starCnt = 0;
		List<Integer> battleAry = fieldItem.getArrBattle();
		for (Integer curLv : battleAry) {
			if (rb.getBattleLvID() < curLv)
				break;
			LvNum curLvItem = cacheManager().getLvNumBylv(rb.getBattleHis(), curLv);
			Battle lvData = cacheManager().getExistValueByKey(Battle.class, curLv);
			int curStar = curLvItem != null ? curLvItem.getN() : lvData.getStar();
			starCnt += curStar;
		}

		if (starLimit > starCnt) {
			throw BaseException.getException(BattleException.EXCE_BATTLE_BOX_AWARD_START_NOT_ENOUGH);
		}

		LvNum boxItem = cacheManager().getLvNumBylv(rb.getBattleBox(), id);
		int sValAry[] = { 0, 1, 10, 100 };
		if (boxItem != null) {
			int s1 = (int) Math.floor(boxItem.getN() % 10);
			int s2 = (int) Math.floor(boxItem.getN() % 100 / 10);
			int s3 = (int) Math.floor(boxItem.getN() % 1000 / 100);
			int sAry[] = { 0, s1, s2, s3 };
			if (sAry[t] != 0) {
				throw BaseException.getException(BattleException.EXCE_BATTLE_BOX_AWARD_BEGOT);
			} else {
				boxItem.setN(boxItem.getN() + sValAry[t]);
			}
		} else {
			// add boxItem
			int curVal = sValAry[t];
			rb.getBattleBox().add(new LvNum(id, curVal));
		}
		for (int ind = 0; ind < packetAry.size(); ind++) {
			int pid = packetAry.get(ind);
			Item itemData = cacheManager().getExistValueByKey(Item.class, pid);
			packetAO().addItem(doc, itemData.getType(), pid, numAry.get(ind), RoleItemLog.SYS_副本_星级奖励, "");

		}
		roleBattleAO().update(rb);
		return Arrays.asList((Serializable) packetAry, (Serializable) numAry);
	}

	@Override
	public List<Serializable> battles(String acc, int id, int type, int n) throws BizException {
		if (type < 1 || type > 3) {
			throw BaseException.getException(BattleException.EXCE_BATTLE_TYPE_ERROR);
		}

		// find battle
		Battle lvData = cacheManager().getExistValueByKey(Battle.class, id);
		Role doc = roleAO().queryExistAccount(acc);
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());

		int sumExp = 0;

		// find field
		if (lvData.getField() > rb.getBattleFieldID())
			throw BaseException.getException(BattleException.EXCE_BATTLE_FIELD_NOT_OPEN);
		else if (rb.getBattleLvID() < id) {
			throw BaseException.getException(BattleException.EXCE_BATTLE_LEVEL_NOT_OPEN);
		}
		long tNow = new Date().getTime();
		if (rb.getBattleNHitTime().getTime() > tNow) {
			throw BaseException.getException(BattleException.EXCE_BATTLES_TIME_NOTALLOW);
		}

		// 验证是否开放
		LvNum hisData = cacheManager().getLvNumBylv(rb.getBattleHis(), id);
		if (hisData != null && type > hisData.getN()) {
			throw BaseException.getException(BattleException.EXCE_BATTLE_TYPE_NOT_OPEN);
		}
		// find max allow num
		int maxCnt = 0;
		LvNum curStatLvItem = cacheManager().getLvNumBylv(rb.getBattleDayHitStat(), id);
		if (curStatLvItem != null) {
			maxCnt = ((lvData.getNumber() - curStatLvItem.getN()) < lvData.getLianzhan())
					? (lvData.getNumber() - curStatLvItem.getN())
					: lvData.getLianzhan();
		} else {
			maxCnt = lvData.getLianzhan();
		}
		// console.log('maxCnt:', maxCnt)
		if (n != maxCnt) {
			throw BaseException.getException(BattleException.EXCE_BATTLES_N_MUST_MAX);
		}

		packetAO().removeItemMustEnough(doc, 0, 3, lvData.getPower() * maxCnt, RoleItemLog.SYS_副本_简单副本_5次, "" + id);

		List<List<ProbItem>> allRstAry = new ArrayList<List<ProbItem>>();
		int lastLv = doc.getLevel();
		int lastExp = doc.getExp();
		int oldLv = doc.getLevel();
		int oldExp = doc.getExp();
		List<Map<String, Integer>> rstLvAry = new ArrayList<Map<String, Integer>>();

		int itemLogType = RoleItemLog.SYS_副本_简单副本_5次;
		if (type == 2)
			itemLogType = RoleItemLog.SYS_副本_普通副本_5次;
		if (type == 3)
			itemLogType = RoleItemLog.SYS_副本_困难副本_5次;

		List<ProbItem> award = new ArrayList<ProbItem>();
		for (int i = 0; i < maxCnt; i++) {
			List<Integer> coinType = lvData.getCoin(type);
			List<Integer> gotNum = lvData.getNum(type);
			// console.log('pve:', coinType, gotNum);

			award.add(new ProbItem(0, coinType.get(0), gotNum.get(0)));
			award.add(new ProbItem(0, coinType.get(1), gotNum.get(1)));

			List<ProbItem> rtnItemAry = cacheManager().probGot(lvData.getDrop());
			// 限时掉落
			List<ProbItem> extra = missionExecAO().probGotExtra(doc, lvData.getDrop());
			if (extra != null)
				rtnItemAry.addAll(extra);
			// console.log('pve:', rtnItemAry);
			for (ProbItem item : rtnItemAry) {
				award.add(item);
			}
			;
			sumExp = 10 * doc.getLevel();

			oldLv = doc.getLevel();
			oldExp = doc.getExp();

			packetAO().addItem(doc, Packet.POS_ATTR, Packet.ATTR_经验, sumExp, itemLogType, String.valueOf(id));

			lastLv = doc.getLevel();
			lastExp = doc.getExp();
			int curLimit = doc.getPropLimitAry().get(2);

			Map<String, Integer> lvExpLimit = new HashMap<String, Integer>();
			lvExpLimit.put("lv", lastLv);
			lvExpLimit.put("exp", lastExp);
			lvExpLimit.put("limit", curLimit);
			rstLvAry.add(lvExpLimit);

			rtnItemAry.add(new ProbItem(0, coinType.get(0), gotNum.get(0)));
			rtnItemAry.add(new ProbItem(0, coinType.get(1), gotNum.get(1)));
			if (lastLv != oldLv || lastExp != oldExp) {
				rtnItemAry.add(new ProbItem(0, 6, sumExp));
			}
			allRstAry.add(rtnItemAry);
		}
		packetAO().addItem(doc, award, itemLogType, String.valueOf(id));
		// update dayStat

		if (curStatLvItem != null) {
			curStatLvItem.setN(curStatLvItem.getN() + maxCnt);
		} else {
			curStatLvItem = new LvNum(id, maxCnt);
			rb.getBattleDayHitStat().add(curStatLvItem);
		}
		rb.setBattleNHitTime(new Date(new Date().getTime() + Constants.battleNHitCDSec * 1000));
		roleBattleAO().update(rb);

		missionExecAO().battleCnt(doc, maxCnt);
		return Arrays.asList(maxCnt, (Serializable) allRstAry, (Serializable) rstLvAry, doc.getLevel(), doc.getExp());
	}
}
