package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleDartAction;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.dart.ChoiceDartVO;
import com.mdy.dzs.game.domain.dart.DartCarVO;
import com.mdy.dzs.game.domain.dart.DartDataRtnVO;
import com.mdy.dzs.game.domain.dart.DartDataVO;
import com.mdy.dzs.game.domain.dart.DartLineUpVO;
import com.mdy.dzs.game.domain.dart.EnterFaceVO;
import com.mdy.dzs.game.domain.dart.RefreshDartCarVO;
import com.mdy.dzs.game.domain.dart.RoleDart;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.DartException;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.sharp.container.biz.BizException;

public class RoleDartActionImpl extends ApplicationAwareAction implements RoleDartAction {

	@Override
	public EnterFaceVO enterFace(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);

		Open openData = cacheManager().getExistValueByKey(Open.class, 56);// key:system
		if (doc.getLevel() < openData.getLevel().get(0)) {
			throw BaseException.getException(DartException.EXCE_DART_LEVEL_CONTROL);
		}

		RoleDart dart = roleDartAO().queryAndAdd(doc.getId());
		Date now = new Date();
		int selfState = roleDartAO().getselfState(dart, now);
		List<GiftItem> getCoin = new ArrayList<GiftItem>();
		DartCarVO selfDartCar = new DartCarVO();
		List<DartCarVO> otherDartCar = new ArrayList<DartCarVO>();
		int speedUpCost = cacheManager().getYaBiaoConfig("endtime").get(0);
		int lastTime = cacheManager().getYaBiaoConfig("escorttime").get(0);

		// 整理返回数据
		if (selfState == RoleDart.DART_STATE_FINISH) {// 押镖结束 获得奖励
			getCoin = roleDartAO().getJiangli(dart, doc);
			roleDartAO().updateCurState(dart);
		} else if (selfState == RoleDart.DART_STATE_DARTING) {// 压镖中 自己镖车状态
			selfDartCar = roleDartAO().getDartCar(doc, dart);
		}
		// 其他镖车不刷新--查已有列表，删掉已完成镖车，补充
		otherDartCar = roleDartAO().replenishCarList(dart, true, new ArrayList<Integer>());

		int refreshTime = roleDartAO().getRefreshTime(dart, now);
		return new EnterFaceVO(selfState, getCoin, selfDartCar, otherDartCar, refreshTime, speedUpCost, lastTime);
	}

	@Override
	public List<DartCarVO> refreshOthers(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleDart dart = roleDartAO().queryAndAdd(doc.getId());
		List<DartCarVO> otherDartCar = new ArrayList<DartCarVO>();
		Date now = new Date();
		if (dart.getNextRefreshTime().after(now)) {
			throw BaseException.getException(DartException.EXCE_DART_REFRESH_OTHERCAR_TIME_ERROE);
		}
		dart.getShowMap().clear();
		otherDartCar = roleDartAO().getOtherDartCarList(dart);
		roleDartAO().updateOtherRoleList(dart, otherDartCar, true);
		return otherDartCar;
	}

	@Override
	public List<DartCarVO> repairOthers(String acc, List<Integer> repairIds) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleDart dart = roleDartAO().queryAndAdd(doc.getId());
		List<DartCarVO> otherDartCar = roleDartAO().replenishCarList(dart, false, repairIds);
		return otherDartCar;
	}

	@Override
	public ChoiceDartVO choiceDart(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleDart dart = roleDartAO().queryAndAdd(doc.getId());
		int detainTimes = cacheManager().getYaBiaoConfig("escortlimit").get(0) - dart.getTodayDartNum();
		int robTimes = cacheManager().getYaBiaoConfig("roblimit").get(0) - dart.getTodayRobNum();
		int refreshCost = cacheManager().getYaBiaoConfig("randomberob").get(0);
		int refreshTimes = dart.getDartRefreshCnt();
		int lastQuality = dart.getDartCarQuality();

		return new ChoiceDartVO(detainTimes, robTimes, refreshCost, refreshTimes, lastQuality);
	}

	@Override
	public RefreshDartCarVO refreshDart(String acc, int type) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleDart dart = roleDartAO().queryAndAdd(doc.getId());
		int quality = 0;
		if (dart.getDartCarQuality() == RoleDart.DART_CAR_QUALITY_GOLDEN) {
			throw BaseException.getException(DartException.EXCE_DART_CAR_TOP);
		}

		if (type == RoleDart.DART_CHOICE_TYPE_CALL) {// 召唤
			quality = RoleDart.DART_CAR_QUALITY_GOLDEN;
			roleDartAO().updateQualityAndRefreshtimes(dart, quality);
			int cost = cacheManager().getYaBiaoConfig("summongold").get(0);
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, cost, RoleItemLog.SYS_押镖_召唤金品质镖车, "");
		} else {
			quality = roleDartAO().getProbQuality(dart.getDartRefreshCnt());
			roleDartAO().updateQualityAndRefreshtimes(dart, quality);
			int cost = cacheManager().getYaBiaoConfig("randomberob").get(0);
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, cost, RoleItemLog.SYS_押镖_刷新镖车, "");
		}
		return new RefreshDartCarVO(doc.getGold(), dart.getDartRefreshCnt() + 1, quality);
	}

	@Override
	public DartDataRtnVO dartData(String acc, int roleId, int dartkey) throws BizException {
		Role doc = roleAO().queryExistId(roleId);
		RoleDart dart = roleDartAO().queryAndAdd(doc.getId());
		Date now = new Date();
		if (dart.getTodayDartNum() != dartkey || now.after(dart.getEndTime())) {
			return new DartDataRtnVO(RoleDart.DART_STATE_END, new DartDataVO());
		}
		List<GiftItem> getCoin = roleDartAO().getCoin(doc, dart, false);
		List<DartLineUpVO> cardData = new ArrayList<DartLineUpVO>();

		for (int indx = 1; indx <= 3; indx++) {
			int cardId = doc.getFmtCardAry().get(indx);
			RoleCard cardEle = roleCardAO().queryById(cardId);
			if (cardEle == null)
				continue;
			cardData.add(new DartLineUpVO(cardEle.getResId(), cardEle.getCls()));
		}
		return new DartDataRtnVO(RoleDart.DART_STATE_DARTING, new DartDataVO(doc.getLevel(), doc.getName(),
				doc.getAttack(), dart.getDartCarQuality(), dart.getBeRobNum(), getCoin, cardData));
	}

	@Override
	public List<GiftItem> speedUp(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleDart dart = roleDartAO().queryAndAdd(doc.getId());
		List<GiftItem> getCoin = new ArrayList<GiftItem>();

		if (dart.getCurState() != RoleDart.DART_STATE_DARTING) {// 不在压镖中
			throw BaseException.getException(DartException.EXCE_DART_CAN_NOT_SPEED_UP);
		}

		int cost = cacheManager().getYaBiaoConfig("endtime").get(0);
		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, cost, RoleItemLog.SYS_押镖_加速运镖消耗, "");

		getCoin = roleDartAO().getJiangli(dart, doc);
		roleDartAO().updateSpeedUpEnd(dart);

		return getCoin;
	}

	@Override
	public Map<String, Integer> start(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleDart dart = roleDartAO().queryAndAdd(doc.getId());
		Map<String, Integer> result = new HashMap<String, Integer>();

		if (dart.getCurState() == RoleDart.DART_STATE_DARTING) {// 在压镖中
			throw BaseException.getException(DartException.EXCE_DART_CAN_NOT_RESTART);
		}
		if (dart.getTodayDartNum() >= cacheManager().getYaBiaoConfig("escortlimit").get(0)) {// 押镖数上限
			throw BaseException.getException(DartException.EXCE_DART_NUM_CONTROLL);
		}
		roleDartAO().updateStart(dart);

		result.put("result", 1);
		result.put("dartKey", dart.getTodayDartNum() + 1);
		return result;
	}

	@Override
	public List<Serializable> robDart(String acc, int otherID, int dartkey) throws BizException {
		Role mDoc = roleAO().queryExistAccount(acc);
		RoleDart mDart = roleDartAO().queryAndAdd(mDoc.getId());

		Role oDoc = roleAO().queryExistId(otherID);
		RoleDart oDart = roleDartAO().queryAndAdd(oDoc.getId());
		Date now = new Date();
		if (mDart.getTodayRobNum() >= cacheManager().getYaBiaoConfig("roblimit").get(0)) {// 劫镖数上限
			throw BaseException.getException(DartException.EXCE_DART_SELF_ROB_CONTROLL);
		}
		if (oDart.getTodayDartNum() != dartkey || now.after(oDart.getEndTime())) {
			throw BaseException.getException(DartException.EXCE_DART_END);
		}
		roleDartAO().updateRobTimesAdd(mDart); // 劫镖人 劫镖次数累加

		// 战斗数据
		FighterInfo srcInfo = fightAO().createFighterInfoByRole(mDoc);
		FighterInfo tgtInfo = fightAO().createFighterInfoByRole(oDoc);
		// start battle
		FightMain main = new FightMain(srcInfo, tgtInfo, null);
		FightResult result = main.fight();
		// 对方被抢夺次数
		int beRobNum = roleDartAO().queryBeRobNum(otherID);// 查询状态
		if (beRobNum >= cacheManager().getYaBiaoConfig("beroblimit").get(0)) {// 被劫镖数上限
			throw BaseException.getException(DartException.EXCE_DART_OPPS_ROB_CONTROLL);
		}

		if (result.getWin() == 1) {// 劫镖胜利
			roleDartAO().updateBeRobTimesAdd(oDart);// 被抢的人 被劫次数累加
		}
		List<GiftItem> coinAry = roleDartAO().getCoin(oDoc, oDart, true);

		return Arrays.asList((Serializable) Arrays.asList(result.getWin()),
				(Serializable) Arrays.asList(result.getMsg()), (Serializable) Arrays.asList(), (Serializable) coinAry);
	}

	@Override
	public List<GiftItem> acceptAward(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleDart dart = roleDartAO().queryAndAdd(doc.getId());
		Date now = new Date();

		if (now.getTime() < dart.getEndTime().getTime()) {// 押镖未结束
			throw BaseException.getException(DartException.EXCE_DART_NOT_END);
		}

		List<GiftItem> getCoin = roleDartAO().getJiangli(dart, doc);
		roleDartAO().updateCurState(dart);
		return getCoin;
	}

}
