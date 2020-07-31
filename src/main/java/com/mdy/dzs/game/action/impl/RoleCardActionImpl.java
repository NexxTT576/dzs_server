package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.card.CardClsLimit;
import com.mdy.dzs.data.domain.card.Carden;
import com.mdy.dzs.data.domain.card.ShangXianSheDing;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.shentong.ShenTong;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleCardAction;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.card.RoleCardClsUpInfo;
import com.mdy.dzs.game.domain.card.RoleCardClsUpItem;
import com.mdy.dzs.game.domain.card.RoleCardClsUpItemInfo;
import com.mdy.dzs.game.domain.card.RoleCardListVO;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleCardException;
import com.mdy.dzs.game.exception.RoleException;
import com.mdy.dzs.game.exception.SystemException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

public class RoleCardActionImpl extends ApplicationAwareAction implements RoleCardAction {

	final static Logger logger = LoggerFactory.get(RoleCardActionImpl.class);

	@Override
	public RoleCardListVO queryList(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		List<RoleCard> cardAry = roleCardAO().queryListByRoleId(role.getId());
		cardAry = packetAO().sortCardList(role, cardAry);
		RoleCardListVO vo = new RoleCardListVO();
		vo.setList(cardAry);
		vo.setLimit(role.getBagLimit(8));
		return vo;
	}

	@Override
	public List<Serializable> msg(String acc, int cid) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleCard curCard = roleCardAO().queryExistCardById(doc, cid);
		packetAO().setCardValues(doc, curCard, true);
		return Arrays.asList(curCard, doc.getLevel());
	}

	@Override
	public List<Serializable> clsUp(String acc, int id, int opAct) throws BizException {
		if (opAct != 1 && opAct != 2) {
			throw BaseException.getException(SystemException.EXCE_PARAM_ERROR, "op val error");
		}
		Role doc = roleAO().queryExistAccount(acc);
		RoleCard mCard = roleCardAO().queryExistCardById(doc, id);
		Card mcardData = cacheManager().getExistValueByKey(Card.class, mCard.getResId());
		// if(mCard.cls==mcardData.silver.length) return cb();
		if (mcardData.getAdvance() == 0) {
			throw BaseException.getException(RoleCardException.EXCE_CARD_CLSUP_NOTALLOW);
		}

		List<RoleCardClsUpItemInfo> itemInfoAry = new ArrayList<RoleCardClsUpItemInfo>();
		int bHas = 1;
		ShangXianSheDing shangxian = cacheManager().getShangXian();
		int costSilver = 0;
		int curPt = 0;
		int cls = mCard.getCls();
		int nextStar = 0;
		// var updateObj = {'$set':{},'$inc':{}};
		List<Integer> cards = null;
		if (opAct == 2) {

			if (mCard.getId() == doc.getFmtMainCardID()) {
				if (cls >= shangxian.getZhujuejinjie())
					bHas = 0;
			} else if (cls >= shangxian.getKapaijinjiecishu()) {
				bHas = 0;
			}
			if (bHas == 0) {
				throw BaseException.getException(RoleCardException.EXCE_CARD_CLSUP_MAX);
			}

			// 验证限制等级
			CardClsLimit limit = cacheManager().getExistValueByKey(CardClsLimit.class, cls + 1);
			if (mCard.getId() == doc.getFmtMainCardID()) {
				if (doc.getLevel() < limit.getLevel().get(0)) {
					bHas = 0;
				}
			} else {
				if (mCard.getLevel() < limit.getLevel().get(1)) {
					bHas = 0;
				}
			}
			if (bHas == 0) {
				throw BaseException.getException(RoleCardException.EXCE_CARD_CLSUP_LEVEL_LIMIT);
			}

			// 获得本次进阶所需数据
			// {'err':reErr ,'itemInfos':gItemInfoAry , 'cost':gCostSilver ,'items':gItems ,
			// 'gongs':gGongs , 'cards':gCards};
			RoleCardClsUpInfo needData = getClsData(mCard, doc);
			if (needData.getErr() != null) {// 返回错误信息
				throw needData.getErr();
			}
			// 可以进行进阶
			List<RoleCardClsUpItem> items = needData.getItems();
			List<RoleGong> gongs = needData.getGongs();
			cards = needData.getCards();

			// ==========================进行数据修改
			// 删除卡
			for (Integer cid : cards) {
				packetAO().removeCardEquipGongYuanById(doc, Packet.POS_CARD, cid, RoleItemLog.SYS_侠客_进阶);
			}
			// 删除材料
			for (RoleCardClsUpItem roleCardClsUpItem : items) {
				packetAO().removeItemMustEnough(doc, Packet.POS_BAG, roleCardClsUpItem.getItemId(),
						roleCardClsUpItem.getNumNeed(), RoleItemLog.SYS_侠客_进阶, "" + roleCardClsUpItem.getItemId());
			}
			// 删除内外功
			for (RoleGong roleGong : gongs) {
				packetAO().removeCardEquipGongYuanById(doc, Packet.POS_GONG, roleGong.getId(), RoleItemLog.SYS_侠客_进阶);
			}

			// 本次进阶消耗的银两
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_银币, mcardData.getSilver().get(cls),
					RoleItemLog.SYS_侠客_进阶, "");

			cls++;
			mCard.setCls(cls);
			if (id == doc.getFmtMainCardID()) {
				doc.setCls(cls);
			}
			curPt = mcardData.getArrPoint().get(cls - 1);
			mCard.setShenPt(mCard.getShenPt() + curPt);
			mCard.setStar(mcardData.getStar().get(cls));

			// 刷新神通
			for (int i = 0; i < mcardData.getTalent().size(); i++) {
				if (mCard.getShenLvAry().get(i) != 0) {
					continue;
				}
				int talentGrpId = mcardData.getTalent().get(i);
				if (talentGrpId == 0)
					continue;
				ShenTong talentData = cacheManager().getExistValueByKey(ShenTong.class, talentGrpId);
				if (mCard.getCls() >= talentData.getArrCond().get(0)) {
					mCard.getShenLvAry().set(i, 1);
				}
			}

			roleAO().updateMainCardValues(doc);
			roleCardAO().update(mCard);

			if (doc.getFmtCardAry().indexOf(mCard.getId()) != -1) {
				roleAO().fmtPropUpdate(doc);
			}
			if (mCard.getCls() >= 6) {
				Card cardData = cacheManager().getExistValueByKey(Card.class, mCard.getResId());
				broadcastAO().cardClsBroad(doc, cardData.getName(), mCard.getStar(), mCard.getCls());
			}
		}

		if (mCard.getId() == doc.getFmtMainCardID()) {
			if (cls >= shangxian.getZhujuejinjie())
				bHas = 0;
		} else if (cls >= shangxian.getKapaijinjiecishu()) {
			bHas = 0;
		}
		if (bHas == 1) {
			RoleCardClsUpInfo needdata = getClsData(mCard, doc);
			costSilver = needdata.getCost();// 下次进阶所需银两
			itemInfoAry = needdata.getItemInfos();// 下次进阶所需数据
			nextStar = mcardData.getStar().get(cls + 1);
		}

		int index = doc.getFmtCardAry().indexOf(mCard.getId());
		Map<Integer, Integer> props = roleAO().calcCardProps(doc, mCard, index);
		Card card = cacheManager().getValueByKey(Card.class, mCard.getResId());
		List<Integer> base = roleAO().battleCalcCard(doc, mCard).get(0);
		// addValAry.set(0, base.get(0) +
		// (int)Math.round(mcardData.getAdvLife().get(cls)*(0.5+mCard.getLead().get(0)/100.0)));

		// console.log(updateObj);

		Map<String, Object> obj1 = new HashMap<String, Object>();
		obj1.put("resId", mcardData.getId());
		obj1.put("lv", mCard.getLevel());
		obj1.put("cls", mCard.getCls());
		obj1.put("star", mCard.getStar());
		obj1.put("base", base);

		Map<String, Object> obj2 = new HashMap<String, Object>();
		if (bHas == 1) {

			List<Integer> obase = Arrays.asList(0, 0, 0, 0);
			obase.set(0, card.getBase().get(0) + mcardData.getAdvLife().get(cls));
			obase.set(1, card.getBase().get(1) + mcardData.getAdvAttack().get(cls));
			obase.set(2, card.getBase().get(2) + mcardData.getAdvDefense().get(cls));
			obase.set(3, card.getBase().get(3) + mcardData.getAdvDefenseM().get(cls));
			List<Integer> addValAry = calcManager().getCardViewBase(obase, card.getLead(), props).get(0);

			obj2.put("resId", mcardData.getId());
			obj2.put("lv", mCard.getLevel());
			obj2.put("cls", mCard.getCls() + 1);
			obj2.put("star", nextStar);
			obj2.put("base", addValAry);
		}

		return Arrays.asList(bHas, (Serializable) obj1, (Serializable) obj2, (Serializable) itemInfoAry, costSilver,
				doc.getAttack(), curPt, (Serializable) cards);
	}

	/**
	 * mcardData:卡牌的item信息 ,cls卡牌进阶等级 ,doc数据库数据 return {'err':reErr
	 * ,'itemInfos':gItemInfoAry , 'cost':gCostSilver ,'items':gItems ,
	 * 'gongs':gGongs , 'cards':gCards};// [ 错误信息 ， 所需物品数组，银两 ，材料数组，内外功数组，卡牌数组 ]
	 * 
	 * @return
	 * @throws BizException
	 */
	RoleCardClsUpInfo getClsData(RoleCard rCard, Role doc) throws BizException {
		String acc = doc.getAccount();
		Card gCardData = cacheManager().getExistValueByKey(Card.class, rCard.getResId());
		int gCls = rCard.getCls();
		BizException reErr = null;
		List<RoleCardClsUpItem> gItems = new ArrayList<RoleCardClsUpItem>();
		;
		List<RoleGong> gGongs = new ArrayList<RoleGong>();
		List<Integer> gCardIds = new ArrayList<Integer>();
		List<RoleCardClsUpItemInfo> gItemInfoAry = new ArrayList<RoleCardClsUpItemInfo>();
		List<List<Integer>> items = Arrays.asList(null, gCardData.getItem1(), gCardData.getItem2(),
				gCardData.getItem3());
		List<List<Integer>> numbers = Arrays.asList(null, gCardData.getNumber1(), gCardData.getNumber2(),
				gCardData.getNumber3());
		for (int i = 1; i <= 3; i++) {
			List<Integer> curItemAry = items.get(i);
			if (curItemAry.size() == 0)
				continue;
			Integer itemId = curItemAry.get(gCls);
			if (itemId == null || itemId == 0)
				continue;
			Item itemData = cacheManager().getExistValueByKey(Item.class, itemId);
			int numNeed = numbers.get(i).get(gCls);
			int numHas = 0;
			if (itemData.getType() != 4) {// 材料
				numHas = packetAO().getNumberByTypeId(doc, itemData.getType(), itemId);
			} else {// 内外功
				List<RoleGong> gongAry = packetAO().queryGongListByAccount(doc.getId());
				for (int k = 0; k < gongAry.size(); k++) {
					RoleGong rg = gongAry.get(k);
					if (itemId == rg.getResId() && rg.getPos() == 0 && rg.getLevel() == 0) {
						numHas++;
						if (numHas <= numNeed)
							gGongs.add(rg);
					}
				}
			}
			if (numHas < numNeed) { // 所需材料或者内外功不足
				if (itemData.getType() != 4)// item
					reErr = BaseException.getException(RoleCardException.EXCE_CARD_CLSUP_NOITEM);
				else {// gong
					reErr = BaseException.getException(RoleCardException.EXCE_CARD_CLSUP_NOGONG);
				}
			} else {
				if (itemData.getType() != 4) {
					gItems.add(new RoleCardClsUpItem(itemId, itemData.getType(), numNeed));
				}
			}
			gItemInfoAry.add(new RoleCardClsUpItemInfo(itemId, itemData.getType(), numNeed, numHas));
		}
		Integer cardIdNeed = gCardData.getCard().get(gCls);
		if (cardIdNeed != null && cardIdNeed != 0) {
			List<RoleCard> cardAry = roleCardAO().querySameResCard(doc.getId(), cardIdNeed);
			int cardNeedCnt = gCardData.getNumber().get(gCls);
			int cardHasCnt = 0;
			for (int j = 0; j < cardAry.size(); j++) {
				RoleCard rc = cardAry.get(j);
				if (rc.getResId() == cardIdNeed && !rc.IsLock()// 不能锁住
						&& rc.getBattle().size() == 0// 不能在其他阵上
						&& rc.getPos() == 0 && rc.getCls() == 0 && rc.getId() != rCard.getId()) {
					cardHasCnt++;
					if (cardHasCnt <= cardNeedCnt)
						gCardIds.add(rc.getId());
				}
			}
			if (cardHasCnt < cardNeedCnt) {
				reErr = BaseException.getException(RoleCardException.EXCE_CARD_CLSUP_NOCARD);
			}
			gItemInfoAry.add(new RoleCardClsUpItemInfo(cardIdNeed, 8, cardNeedCnt, cardHasCnt));
		}
		int gCostSilver = gCardData.getSilver().get(gCls);
		if (gCostSilver > doc.getSilver()) {
			reErr = BaseException.getException(RoleException.EXCE_SILVER_NOT_ENOUGH);
		}
		RoleCardClsUpInfo res = new RoleCardClsUpInfo();
		res.setErr(reErr);
		res.setItemInfos(gItemInfoAry);
		res.setCost(gCostSilver);
		res.setItems(gItems);
		res.setGongs(gGongs);
		res.setCards(gCardIds);
		return res;
	}

	@Override
	public List<Serializable> levelUp(String acc, List<Integer> idAry, int opAct) throws BizException {
		if (opAct != 1 && opAct != 2) {
			throw BaseException.getException(SystemException.EXCE_PARAM_ERROR, "op val error");
		}
		Role doc = roleAO().queryExistAccount(acc);

		int expSum = 0;
		RoleCard mCard = null;
		Card mcardData = null;
		int mcStar = 0;
		int mcExp = 0;
		int mcLv = 0;
		int oldExp = 0;
		int leftExp = 0;

		Map<Integer, Carden> cardenMap = cacheManager().getValues(Carden.class);

		int selfId = idAry.get(0);
		for (int i = 0; i < idAry.size(); i++) {
			int rcid = idAry.get(i);
			RoleCard rc = roleCardAO().queryExistCardById(doc, rcid);
			if (i > 0 && rc.getPos() > 0) {
				throw BaseException.getException(RoleCardException.EXCE_CARD_IN_LINEUP, rcid);
			}
			if (i != 0 && rcid == selfId) {
				throw BaseException.getException(RoleCardException.EXCE_CARD_IN_LINEUP, rcid);
			}
			Carden carden = cardenMap.get(rc.getLevel());

			List<Integer> levelExp = carden.getSumexp();
			Card itemData = cacheManager().getExistValueByKey(Card.class, rc.getResId());
			int initExp = itemData.getExp();
			int cardExp = levelExp.get(rc.getStar() - 1) + rc.getCurExp() + initExp;
			if (i > 0) {
				expSum += cardExp;
			} else {
				mCard = rc;
				mcStar = rc.getStar();
				mcLv = rc.getLevel();
				mcExp = rc.getCurExp() + levelExp.get(rc.getStar() - 1);
				oldExp = rc.getCurExp();
			}
		}

		mcardData = cacheManager().getExistValueByKey(Card.class, mCard.getResId());
		if (mCard.getId() == doc.getFmtMainCardID()) {
			throw BaseException.getException(RoleCardException.EXCE_CARD_NOTALLOW_MAIN);
		}
		ShangXianSheDing shangxian = cacheManager().getShangXian();

		if (mCard.getLevel() >= shangxian.getKapaidengji()) {
			throw BaseException.getException(RoleCardException.EXCE_CARD_LEVELUP_MAXLV);
		}
		if (opAct == 2 && mCard.getLevel() >= doc.getLevel()) {
			throw BaseException.getException(RoleCardException.EXCE_CARD_LEVELUP_MAXROLELV);
		}

		// console.log('expSum:', expSum,mcStar,mcLv,mcExp);
		mcExp += expSum;
		List<Integer> addAry = Arrays.asList(0, 0, 0, 0);
		int endLv = mcLv;
		int curLimit = 0;
		leftExp = expSum;
		for (int j = (mcLv - 1); j < cardenMap.size() - 1; j++) {
			Carden carden = cardenMap.get(j + 1);
			int lvSumExp = carden.getSumexp().get(mcStar - 1);
			int nxLvExp = carden.getExp().get(mcStar - 1);
			// console.log('lvSumExp/nxLvExp:',lvSumExp,nxLvExp);
			if ((lvSumExp + nxLvExp) > mcExp || (j + 1) >= doc.getLevel() || (j + 1) >= shangxian.getKapaidengji()) {
				if (mCard.getLevel() < (j + 1)) { // 要升的等级比当前大
					List<Integer> addDataAry = mcardData.getLevelAdd();
					// console.log('nx lv:',j, addDataAry);
					for (int i = 0; i < 4; i++) {
						addAry.set(i, addAry.get(i) + addDataAry.get(i) * (1 + j - mCard.getLevel()));
					}
					endLv = j + 1;
					curLimit = cardenMap.get(endLv).getExp().get(mcStar - 1);
					leftExp = mcExp - lvSumExp;
					if (leftExp >= curLimit) {
						leftExp = curLimit - 1;
					}
					if (opAct == 2) {
						mCard.setLevel(j + 1);
						mCard.setCurExp(leftExp);
					}
				} else { // 等级不升 加经验
					leftExp = mcExp - lvSumExp;
					curLimit = cardenMap.get(endLv).getExp().get(mcStar - 1);
					if (opAct == 2) {
						mCard.setCurExp(mcExp - lvSumExp);
					}
				}
				break;
			}
		}

		if (opAct == 2) {
			if (doc.getFmtCardAry().indexOf(mCard.getId()) != -1) {
				roleAO().fmtPropUpdate(doc);
			}
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_银币, expSum, RoleItemLog.SYS_侠客_强化, "");
			roleCardAO().updateLevel(mCard);
			// 消耗卡牌
			for (int i = 1; i < idAry.size(); i++) {
				packetAO().removeCardEquipGongYuanById(doc, Packet.POS_CARD, idAry.get(i), RoleItemLog.SYS_侠客_强化);
			}
			;
			// TASK
			if (mcLv != mCard.getLevel()) {
				missionExecAO().lvUpRoleCard(doc, mCard);
			}
		}

		Map<String, Object> info = new HashMap<String, Object>();
		info.put("resId", mcardData.getId());
		info.put("lv", opAct == 1 ? endLv : mCard.getLevel());
		info.put("curLv", mCard.getLevel());
		info.put("cls", mCard.getCls());
		info.put("star", mCard.getStar());
		info.put("base", roleAO().battleCalcCard(doc, mCard).get(0));
		info.put("exp", opAct == 1 ? leftExp : mCard.getCurExp());
		info.put("curExp", opAct == 1 ? oldExp : mCard.getCurExp());
		info.put("limit", curLimit);
		info.put("add", addAry);
		info.put("cost", expSum);
		return Arrays.asList((Serializable) info, doc.getSilver(), endLv, doc.getAttack());
	}

	@Override
	public List<Serializable> soulUp(String acc, int id, int opAct, int levelLimit) throws BizException {
		if (opAct != 1 && opAct != 2) {
			throw BaseException.getException(SystemException.EXCE_PARAM_ERROR, "op val error");
		}

		if (levelLimit != 1 && levelLimit != 5) {
			throw BaseException.getException(RoleCardException.EXCE_CARD_SOULUP_LIMIT);
		}
		Role doc = roleAO().queryExistAccount(acc);
		RoleCard mCard = roleCardAO().queryExistCardById(doc, id);
		ShangXianSheDing shangxian = cacheManager().getShangXian();
		if (mCard.getLevel() >= shangxian.getKapaidengji()) {
			throw BaseException.getException(RoleCardException.EXCE_CARD_LEVELUP_MAXLV);
		}
		if (opAct == 2 && mCard.getLevel() >= doc.getLevel()) {
			throw BaseException.getException(RoleCardException.EXCE_CARD_LEVELUP_MAXROLELV);
		}
		int mcStar = mCard.getStar();
		int mcExp = mCard.getCurExp();
		int mcLv = mCard.getLevel();
		int allSoul = mcExp + doc.getSoul();

		int oldExp = mcExp;
		int oldLv = mcLv;
		// mcExp+=doc.getSoul();
		// console.log('expSum:',mcStar,mcLv,mcLv+levelLimit);
		Card mcardData = cacheManager().getExistValueByKey(Card.class, mCard.getResId());
		int addAry[] = { 0, 0, 0, 0 };
		int nxLvExpVal = 0;
		// var nxLvExpVal = designData['carden'][mcLv].exp[mcStar-1];
		List<Integer> addDataAry = mcardData.getLevelAdd();
		int endLv = mcLv + levelLimit;
		int silver = doc.getSilver();
		Map<Integer, Carden> cardenMap = cacheManager().getValues(Carden.class);
		for (int j = mcLv; j < (mcLv + levelLimit); j++) {
			int nxLvExp = cardenMap.get(j).getExp().get(mcStar - 1);
			// console.log('lvSumExp/nxLvExp:',lvSumExp,nxLvExp);
			// not enough exp or over limit
			if (nxLvExp > allSoul || (j + 1) > doc.getLevel() || j >= cardenMap.size() || silver - nxLvExp < 0) {
				endLv = j;
				break;
			} else {
				allSoul -= nxLvExp;
				silver -= nxLvExp;
				nxLvExpVal += nxLvExp;
				if (opAct == 2) {
					mCard.setLevel(mCard.getLevel() + 1);
				}
			}
		}

		nxLvExpVal -= mcExp;

		if (opAct == 2) {
			if (nxLvExpVal <= 0) {
				throw BaseException.getException(RoleCardException.EXCE_CARD_SOULUP_LIMIT);
			}
			int itemLog = RoleItemLog.SYS_侠客_侠魂强化;
			if (levelLimit == 5) {
				itemLog = RoleItemLog.SYS_侠客_侠魂强化5次;
			}
			mCard.setCurExp(0);
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_银币, nxLvExpVal, itemLog, id + "");
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_侠魂, nxLvExpVal, itemLog, id + "");
			roleCardAO().updateLevel(mCard);
			if (doc.getFmtCardAry().indexOf(mCard.getId()) != -1) {
				roleAO().fmtPropUpdate(doc);
			}
			nxLvExpVal = 0;
			// TASK
			if (oldLv != mCard.getLevel()) {
				missionExecAO().lvUpRoleCard(doc, mCard);
			}
		}

		for (int i = 0; i < 4; i++) {
			addAry[i] = addDataAry.get(i) * (1);
		}

		if (nxLvExpVal <= 0) {
			nxLvExpVal = cardenMap.get(mCard.getLevel()).getExp().get(mcStar - 1) - mCard.getCurExp();
		}

		Map<String, Object> info = new HashMap<String, Object>();
		info.put("resId", mcardData.getId());
		info.put("lv", mCard.getLevel());
		info.put("cls", mCard.getCls());
		info.put("star", mCard.getStar());
		info.put("base", roleAO().battleCalcCard(doc, mCard).get(0));
		info.put("exp", nxLvExpVal);
		info.put("curExp", opAct == 1 ? oldExp : nxLvExpVal);
		info.put("curLv", opAct == 1 ? oldLv : mCard.getLevel());
		info.put("add", addAry);
		info.put("cost", nxLvExpVal);
		info.put("hun", Arrays.asList(doc.getSoul(), nxLvExpVal));

		return Arrays.asList((Serializable) info, doc.getSilver(), endLv, doc.getAttack());
	}

	@Override
	public List<Serializable> shenUp(String acc, int id, int indVal) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleCard mCard = roleCardAO().queryExistCardById(doc, id);// =====================================本张卡的数据
		Card mcardData = cacheManager().getExistValueByKey(Card.class, mCard.getResId());// =============本张卡的资源数据

		List<Integer> talentAry = mcardData.getTalent();
		int talentGrpId = talentAry.get(indVal);
		ShenTong talentData = cacheManager().getExistValueByKey(ShenTong.class, talentGrpId);

		int curLv = mCard.getShenLvAry().get(indVal);
		int nxtCls = talentData.getArrCond().get(curLv);// ===============神通升级的阶级条件(按照索引 取下一级的所需等级)
		int nxtPt = talentData.getArrPoint().get(curLv - 1);// ===============神通升级所需点数（按照索引 取本级升级 所需的点数）
		int nxtID = talentData.getArrTalent().get(curLv);
		;// ===============神通各级id组（按照索引取本级的神通效果ID）

		ShangXianSheDing shangxian = cacheManager().getShangXian();
		if (curLv >= shangxian.getShentongdengji()) {
			throw BaseException.getException(RoleCardException.EXCE_CARD_SHENUP_MAXLV);
		}
		if (mCard.getCls() < nxtCls) {// 判断能否升级
			throw BaseException.getException(RoleCardException.EXCE_CARD_SHENUP_NOTCLS);
		}
		if (mCard.getShenPt() < nxtPt) {
			throw BaseException.getException(RoleCardException.EXCE_CARD_SHENUP_NOTPT);
		}
		mCard.getShenLvAry().set(indVal, mCard.getShenLvAry().get(indVal) + 1);
		mCard.getShenIDAry().set(indVal, nxtID);
		mCard.setShenPt(mCard.getShenPt() - nxtPt);
		roleCardAO().update(mCard);
		if (mCard.getPos() > 0) {
			roleAO().fmtPropUpdate(doc);
		}
		return Arrays.asList(nxtID, (Serializable) mCard.getShenLvAry().get(indVal), mCard.getShenPt());
	}

	@Override
	public List<Serializable> shenReset(String acc, int id) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleCard mCard = roleCardAO().queryExistCardById(doc, id);

		Card mcardData = cacheManager().getExistValueByKey(Card.class, mCard.getResId());
		List<Integer> talentAry = mcardData.getTalent();
		packetAO().removeItemMustEnough(doc, 0, 1, Constants.shenResetGold, RoleItemLog.SYS_侠客_神通洗点, "");
		int sumPt = 0;
		for (int i = 0; i < talentAry.size(); i++) {
			int talentGrpId = talentAry.get(i);
			ShenTong talentData = cacheManager().getExistValueByKey(ShenTong.class, talentGrpId);
			int curLv = mCard.getShenLvAry().get(i);
			for (int j = 1; j < curLv; j++) {
				sumPt += talentData.getArrPoint().get(j - 1);
			}
			int shenId = talentData.getArrTalent().get(0);
			int cond = talentData.getArrCond().get(0);
			mCard.getShenLvAry().set(i, mCard.getCls() >= cond ? 1 : 0);
			mCard.getShenIDAry().set(i, shenId);
		}
		mCard.setShenPt(mCard.getShenPt() + sumPt);
		roleCardAO().update(mCard);
		if (mCard.getPos() > 0) {
			roleAO().fmtPropUpdate(doc);
		}
		return Arrays.asList((Serializable) mCard.getShenIDAry(), (Serializable) mCard.getShenLvAry(),
				mCard.getShenPt());
	}

	@Override
	public List<Serializable> sellCard(String acc, List<Integer> idAry) throws BizException {
		Role role = roleAO().queryExistAccount(acc);

		int tSilver = 0;
		List<Card> list = new ArrayList<Card>();
		List<RoleCard> rcList = new ArrayList<RoleCard>();
		for (int ind = 0; ind < idAry.size(); ind++) {
			int cid = idAry.get(ind);
			RoleCard rc = roleCardAO().queryExistCardById(role, cid);
			int cardResId = rc.getResId();
			Card cardData = cacheManager().getExistValueByKey(Card.class, cardResId);
			list.add(cardData);
			rcList.add(rc);
			tSilver += cardData.getPrice();
		}
		roleCardAO().removeCards(role, rcList, list, RoleItemLog.SYS_侠客_出售);
		packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_银币, tSilver, RoleItemLog.SYS_侠客_出售, "");
		return Arrays.asList((Serializable) Arrays.asList(tSilver, role.getSilver()));
	}

	@Override
	public void lockCard(String acc, int id, int lock) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleCard rc = roleCardAO().queryExistCardById(role, id);
		roleCardAO().lockCard(role, rc, lock);
	}

}
