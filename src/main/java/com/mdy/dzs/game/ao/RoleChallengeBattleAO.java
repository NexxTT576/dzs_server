package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.challengebattle.ActivityBattle;
import com.mdy.dzs.data.domain.challengebattle.ActivityBattleJFJP;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.mission.ActivityMissionProperty;
import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.game.domain.BuyCntVO;
import com.mdy.dzs.game.domain.challenge.RoleActivityBattle;
import com.mdy.dzs.game.domain.challenge.RoleEliteBattle;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.ChallengeBattleException;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 挑战副本（精英副本，活动副本）
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月7日 下午8:31:37
 */
public class RoleChallengeBattleAO extends BaseAO {

	private CacheManager cacheManager;
	private PacketAO packAO;
	private MissionAO missionAO;
	private MissionExecAO missionExecAO;

	public RoleChallengeBattleAO(CacheManager cacheManager, PacketAO packAO, MissionAO missionAO,
			MissionExecAO missionExecAO) {
		this.cacheManager = cacheManager;
		this.packAO = packAO;
		this.missionAO = missionAO;
		this.missionExecAO = missionExecAO;
	}

	//
	/**
	 * 查询
	 */
	public RoleEliteBattle queryEliteBattleByRoleId(Role role) throws BizException {
		RoleEliteBattle elite = roleChallengeBattleDAO().queryEliteBattleByRoleId(role.getId());
		if (elite == null) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ELITE_BATTLE_NOT_EXIST, role.getId());
		}
		if (!DateUtil.isToday(elite.getEliteDayRefresh())) {
			roleChallengeBattleDAO().resetEliteBattle(role.getId());
			elite = roleChallengeBattleDAO().queryEliteBattleByRoleId(role.getId());
		}
		return elite;
	}

	/**
	 * 更新
	 * 
	 * @param RoleEliteBattle
	 */
	public void updateEliteBattle(RoleEliteBattle elite) {
		roleChallengeBattleDAO().updateEliteBattle(elite);
	}

	/**
	 * 获取精英副本剩余次数
	 * 
	 * @param elite
	 * @return
	 */
	public int getEliteBattleSurplusCnt(RoleEliteBattle elite) {
		Calendar now = Calendar.getInstance();
		int curDay = now.get(Calendar.DAY_OF_WEEK) - 1;
		if (curDay == 0)
			curDay = 7;
		int limitDay = Constants.eliteCnt.get(curDay - 1);
		int add = missionAO.getActivityMissionCntByProperty(elite.getRoleId(),
				ActivityMissionProperty.精英副本_次数翻倍.value());
		limitDay *= add;
		return (elite.getEliteBuyCnt() + limitDay) - elite.getEliteTodayCnt();
	}

	public int getEliteBattleBuySpend(RoleEliteBattle elite) {
		return (elite.getEliteBuyCnt() + 1) * Constants.eliteBattleSpendGold;
	}

	public void clearEliteBattleStatus(RoleEliteBattle elite, int curLvId) {
		elite.setEliteNxtLvState(0);
		elite.setEliteNxtLvRevive(0);
		elite.setEliteNxtLvDPos(new ArrayList<Integer>());
		elite.setEliteNxtLv(curLvId);
	}

	public void useEliteBattleCnt(RoleEliteBattle elite) {
		elite.setEliteTodayCnt(elite.getEliteTodayCnt() + 1);
		roleChallengeBattleDAO().useEliteBattleCnt(elite);
	}

	/////////////////////////////////////////////////////////////////////////

	public RoleActivityBattle queryActivityBattleByRoleId(Role role) throws BizException {
		RoleActivityBattle actBattle = roleChallengeBattleDAO().queryActBattle(role.getId());
		if (actBattle == null) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ACTIVITY_BATTLE_NOT_EXIST, role.getId());
		}
		if (!DateUtil.isToday(actBattle.getActDayRefresh())) {
			roleChallengeBattleDAO().resetActivityBattle(role.getId());
			actBattle = roleChallengeBattleDAO().queryActBattle(role.getId());
		}
		return actBattle;
	}

	/**
	 * 获取各个活动副本剩余此书
	 * 
	 * @param actBattle
	 * @return
	 */
	public int getActivityBattleSurplusCnt(Role doc, RoleActivityBattle actBattle, int aid) throws BizException {
		ActivityBattle hdData = cacheManager.getExistValueByKey(ActivityBattle.class, aid);
		int limitVal = getActivityBattleOpencCnt(doc, hdData);
		String strAid = aid + "";
		Integer useCnt = actBattle.getActPveCnts().get(strAid);
		Integer buyCnt = actBattle.getActBuyCnts().get(strAid);
		buyCnt = buyCnt == null ? 0 : buyCnt;
		Integer useItemCnt = actBattle.getActUseItemCnts().get(strAid);
		useItemCnt = useItemCnt == null ? 0 : useItemCnt;
		int leftCnt = buyCnt + useItemCnt + limitVal - (useCnt == null ? 0 : useCnt);
		leftCnt = leftCnt > 0 ? leftCnt : 0;
		return leftCnt;
	}

	/**
	 * 获取各个活动副本剩余此书
	 * 
	 * @param actBattle
	 * @return
	 * @throws BizException
	 */
	public Map<String, BuyCntVO> getActivityBattleBuyCnts(Role doc, RoleActivityBattle actBattle) throws BizException {
		Map<String, BuyCntVO> buyCntMap = new HashMap<String, BuyCntVO>();
		Map<Integer, ActivityBattle> activityBattles = cacheManager.getValues(ActivityBattle.class);
		for (ActivityBattle hdData : activityBattles.values()) {
			BuyCntVO vo = packetCntInfo(doc, actBattle, hdData.getId());
			if (vo.getItemId() != 0) {
				vo.setNum(packAO.getNumberByTypeId(doc, Packet.POS_BAG, vo.getItemId()));
			}
			vo.setOpenCnt(getActivityBattleOpencCnt(doc, hdData));
			buyCntMap.put(hdData.getId() + "", vo);
		}
		return buyCntMap;
	}

	/**
	 * 获取活动每日初始次数
	 * 
	 * @param doc
	 * @param aid
	 * @return
	 * @throws BizException
	 */
	public int getActivityBattleOpencCnt(Role doc, ActivityBattle hdData) throws BizException {
		Calendar now = Calendar.getInstance();
		int curDay = now.get(Calendar.DAY_OF_WEEK) - 1;
		if (curDay == 0)
			curDay = 7;
		int limitVal = hdData.getArrNum().get(curDay - 1);
		Open open = cacheManager.getValueByKey(Open.class, hdData.getOpenSysId());
		if (open != null && doc.getLevel() < open.getLevel().get(0)) {
			limitVal = 0;
		} else {
			ActivityMissionProperty p = null;
			switch (hdData.getId()) {
			case ActivityBattle.AID_劫富济贫:
				p = ActivityMissionProperty.劫富济贫_次数翻倍;
				break;
			case ActivityBattle.AID_行侠仗义:
				p = ActivityMissionProperty.行侠仗义_次数翻倍;
				break;
			case ActivityBattle.AID_除暴安良:
				p = ActivityMissionProperty.除暴安良_次数翻倍;
				break;
			case ActivityBattle.AID_黑风寨:
				p = ActivityMissionProperty.黑风寨;
				break;
			case ActivityBattle.AID_狼居胥:
				p = ActivityMissionProperty.狼居胥;
				break;
			default:
				break;
			}
			int add = missionAO.getActivityMissionCntByProperty(doc.getId(), p.value());
			limitVal *= add;
		}

		return limitVal;
	}

	public Map<String, List<PacketExtend>> checkActivityBattleBags(Role doc) throws BizException {
		Map<String, List<PacketExtend>> checkMap = new HashMap<String, List<PacketExtend>>();
		Map<Integer, ActivityBattle> activityBattles = cacheManager.getValues(ActivityBattle.class);
		for (ActivityBattle hdData : activityBattles.values()) {
			String aid = hdData.getId() + "";
			List<PacketExtend> list = packAO.checkBag(doc, hdData.getOpenSysId());
			checkMap.put(aid, list);
		}
		return checkMap;
	}

	public List<List<ProbItem>> activityBattleAwards(Role doc, FightResult result, ActivityBattle hdData, int npcInd,
			int npcLv, int npcLen) throws BizException {
		List<ProbItem> awardAry = new ArrayList<ProbItem>();
		List<ProbItem> coinAry = new ArrayList<ProbItem>();
		int aid = hdData.getId();
		switch (aid) {
		case ActivityBattle.AID_劫富济贫:
			// awardAry = cacheManager.probGot(hdData.getProbid().get(0));
			// //限时掉落
			// List<ProbItem> extra =
			// missionExecAO.probGotExtra(doc,hdData.getProbid().get(0));
			// if(extra != null) awardAry.addAll(extra);
			// packAO.sendAward(doc, awardAry,RoleItemLog.SYS_挑战_活动副本,"1");
			ActivityBattleJFJP lastItem = null;
			int awardSilver = 0;
			List<ActivityBattleJFJP> list = cacheManager.getJiefujipinList();
			int damageTotal = result.getStatistic().getdSum();
			lastItem = list.get(0);
			for (int i = 0; i < list.size(); i++) {
				ActivityBattleJFJP jfjp = list.get(i);
				if (jfjp.getDamage() < damageTotal)
					lastItem = jfjp;
				else
					break;
			}
			if (lastItem != null) {
				float dSilver = (damageTotal - lastItem.getDamage()) * lastItem.getPer();
				awardSilver = lastItem.getSumsilver() + (int) Math.round(dSilver);
				coinAry.add(new ProbItem(0, 2, awardSilver));
				packAO.addItem(doc, Packet.POS_ATTR, Packet.ATTR_银币, awardSilver, RoleItemLog.SYS_挑战_活动副本, "1");
			}
			break;
		default:

			if (result.getWin() == 1 && npcInd == npcLen) {
				awardAry = cacheManager.probGot(hdData.getProbid().get(npcLv - 1));
				// 限时掉落
				List<ProbItem> extra = missionExecAO.probGotExtra(doc, hdData.getProbid().get(npcLv - 1));
				if (extra != null)
					awardAry.addAll(extra);
				packAO.sendAward(doc, awardAry, RoleItemLog.SYS_挑战_活动副本, "3");
			}
			break;
		}
		// List<ProbItem> items = new ArrayList<ProbItem>();
		// for (ProbItem probItem : awardAry) {
		// probItem.setN(1);
		// for (int i = 0; i< probItem.getN();i++) {
		// items.add(probItem);
		// }
		// }
		return Arrays.asList(awardAry, coinAry);
	}

	/**
	 * 更新活动副本使用次数
	 * 
	 * @param role
	 * @param aid
	 */
	public void useActivityBattleCnt(Role role, String aid) {
		RoleActivityBattle actBattle = roleChallengeBattleDAO().queryActBattle(role.getId());
		Integer cnt = actBattle.getActPveCnts().get(aid);
		cnt = cnt == null ? 1 : cnt + 1;
		actBattle.getActPveCnts().put(aid, cnt);
		roleChallengeBattleDAO().updateActivityBattle(actBattle);
	}

	public void updateActivityBattle(RoleActivityBattle actBattle) {
		roleChallengeBattleDAO().updateActivityBattle(actBattle);
	}

	/////////////////////////////////////////////////////////////////////////
	/**
	 * 创建挑战数据
	 * 
	 * @param role
	 */
	public void addChallenges(Role role) {
		roleChallengeBattleDAO().addEliteBattle(role);
		roleChallengeBattleDAO().addActivityBattle(role);
	}

	public int getActivityBattleLimit(Role doc, int aid) throws BizException {
		int limit = 0;
		int vipLimit = 0;
		switch (aid) {
		case ActivityBattle.AID_劫富济贫:
			vipLimit = Vip.SYSTEM_劫富济贫购买次数;
			break;
		case ActivityBattle.AID_行侠仗义:
			vipLimit = Vip.SYSTEM_行侠仗义购买次数;
			break;
		case ActivityBattle.AID_除暴安良:
			vipLimit = Vip.SYSTEM_除暴安良购买次数;
			break;
		case ActivityBattle.AID_黑风寨:
			vipLimit = Vip.SYSTEM_黑风寨购买次数;
			break;
		case ActivityBattle.AID_狼居胥:
			vipLimit = Vip.SYSTEM_狼居胥购买次数;
			break;
		default:
			break;
		}
		if (vipLimit != 0) {
			Vip vipData = cacheManager.getExistValueByKey(Vip.class, vipLimit);
			limit = vipData.getVipByLevel(doc.getVip());
		}
		return limit;
	}

	public BuyCntVO packetCntInfo(Role doc, RoleActivityBattle actBattle, int aid) throws BizException {
		String strAid = aid + "";
		int surplusCnt = getActivityBattleSurplusCnt(doc, actBattle, aid);

		int itemId = Constants.activityBattleSpendItem[aid];
		int num = 0;
		int limit = getActivityBattleLimit(doc, aid);
		Integer buyCnt = actBattle.getActBuyCnts().get(strAid);
		buyCnt = buyCnt == null ? 0 : buyCnt;
		int spend = 0;
		ActivityBattle hdData = cacheManager.getExistValueByKey(ActivityBattle.class, aid);
		if (aid == 1) {
			spend = (buyCnt + 1) * Constants.activityBattleSpendGold[aid];
		} else if (aid != 1 && hdData.getIsbuy() == 1 && buyCnt < limit) {
			spend = hdData.getBuyPrice().get(buyCnt);
		}
		return new BuyCntVO(surplusCnt, buyCnt, limit, itemId, num, spend, doc.getGold());
	}

}