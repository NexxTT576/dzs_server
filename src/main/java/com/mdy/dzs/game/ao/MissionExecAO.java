package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.Prob;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.mission.ActivityMissionProperty;
import com.mdy.dzs.data.domain.mission.MissionDefine;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.battle.LvNum;
import com.mdy.dzs.game.domain.battle.RoleBattle;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.challenge.RoleEliteBattle;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.mission.Mission;
import com.mdy.dzs.game.domain.mission.RoleDailyMission;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleChannel;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.dzs.game.exception.MissionException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * 任务处理
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年12月24日 下午2:42:29
 */
public class MissionExecAO extends BaseAO {

	private static final int SYMBOL_NULL = 0;
	private static final int SYMBOL_ADD = 1;
	private static final int SYMBOL_REPLACE = 2;
	private static final int SYMBOL_MAX_REPLACE = 3;
	//
	private static Logger logger = LoggerFactory.get(MissionExecAO.class);
	//

	private MissionAO missionAO;
	private PacketAO packetAO;
	private List<Integer> nullParams;
	private CacheManager cacheManager;

	public MissionExecAO(MissionAO missionAO, PacketAO packetAO, CacheManager cacheManager) {
		this.missionAO = missionAO;
		this.packetAO = packetAO;
		this.cacheManager = cacheManager;
		nullParams = new ArrayList<Integer>();

	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 限时掉落
	 * 
	 * @param role
	 * @param probId
	 * @return
	 */
	public List<ProbItem> probGotExtra(Role role, int probId) {
		List<ProbItem> res = null;
		do {
			if (!missionAO.isAcceptActivityMissionByProperty(role.getId(), ActivityMissionProperty.限时掉落))
				break;
			Prob prob = cacheManager.getValueByKey(Prob.class, probId);
			if (prob == null)
				break;
			if (prob.getItemExtra() == null)
				break;
			res = cacheManager.probGot(prob.getItemExtra(), prob.getType(), probId);
		} while (false);
		return res;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	//

	/**
	 * 角色属性变更
	 * 
	 * @param role
	 * @param property
	 * @throws BizException
	 */
	public void changeRoleProperty(Role role, int property) throws BizException {
		if (property == MissionDefine.PROPERTY_主角_战斗力达到_MISSION || property == MissionDefine.PROPERTY_主角_等级达到_MISSION) {
			AddDetailCheckStatus(role, property);
		}
	}

	/**
	 * 球队升级时需要检验是否能接其他任务
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void lvUpRole(Role role) throws BizException {
		AcceptBeStartMission(role);
	}

	/**
	 * 经脉提升
	 * 
	 * @param role
	 * @param chen
	 * @throws BizException
	 */
	public void changeRoleChennel(Role role, RoleChannel chen) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_主角_经脉全部提升到_MISSION, SYMBOL_REPLACE,
				chen.getChanPt() == 8 ? chen.getChanLv() : chen.getChanLv() - 1);
	}

	/**
	 * 主角登陆
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void roleLogin(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_主角_登陆_MISSION, SYMBOL_ADD, 1);
	}

	/**
	 * 充值
	 * 
	 * @param role
	 * @param rmb
	 * @throws BizException
	 */
	public void roleChargeRMB(Role role, int rmb) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_主角_充值RMB_MISSION, SYMBOL_ADD, rmb);
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_主角_一次性充值_MISSION, SYMBOL_REPLACE, rmb);
		Date time = roleStatisticsDAO().queryLastChargeTime(role.getId());
		if (time == null || !DateUtil.isToday(time)) {
			AddDetailCheckStatus(role, MissionDefine.PROPERTY_主角_充值天数_MISSION, SYMBOL_ADD, 1);
		}
	}

	/**
	 * 消耗元宝
	 * 
	 * @param role
	 * @param gold
	 * @throws BizException
	 */
	public void roleGoldCost(Role role, int gold) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_主角_消耗元宝_MISSION, SYMBOL_ADD, gold);
	}

	/**
	 * 获得新卡
	 * 
	 * @param role
	 * @param card
	 * @throws BizException
	 */
	public void wineShopRoleCard(Role role, RoleCard rc) throws BizException {
		List<Integer> params = new ArrayList<Integer>();
		params.add(rc.getStar());
		AddDetailByParamsCheckStatus(role, MissionDefine.PROPERTY_侠客_抽卡获得_MISSION, params, SYMBOL_ADD, 1);
	}

	/**
	 * 卡片升级
	 * 
	 * @param role
	 * @param level
	 * @throws BizException
	 */
	public void lvUpRoleCard(Role role, RoleCard rc) throws BizException {
		List<Integer> params = new ArrayList<Integer>();
		params.add(rc.getStar());
		AddDetailByParamsCheckStatus(role, MissionDefine.PROPERTY_侠客_升级_MISSION, params, SYMBOL_MAX_REPLACE,
				rc.getLevel());
	}

	/**
	 * 卡片合成
	 * 
	 * @param role
	 * @param rc
	 * @throws BizException
	 */
	public void synthRoleCard(Role role, RoleCard rc) throws BizException {
		List<Integer> params = new ArrayList<Integer>();
		params.add(rc.getStar());
		AddDetailByParamsCheckStatus(role, MissionDefine.PROPERTY_侠客_合成_MISSION, params, SYMBOL_ADD, 1);
	}

	/**
	 * 获得新装备
	 * 
	 * @param role
	 * @param equip
	 * @throws BizException
	 */
	public void newRoleEquip(Role role, Equip equip) throws BizException {
		List<Integer> params = new ArrayList<Integer>();
		params.add(equip.getStar());
		AddDetailByParamsCheckStatus(role, MissionDefine.PROPERTY_装备_获得_MISSION, params, SYMBOL_ADD, 1);

		int recordNum = equipDAO().queryRecordEquipId(role.getId()).size();
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_装备_获得不同_MISSION, SYMBOL_REPLACE, recordNum);
	}

	/**
	 * 获得新卡
	 * 
	 * @param role
	 * @param card
	 * @throws BizException
	 */
	public void newRoleCard(Role role, RoleCard rc) throws BizException {
		List<Integer> params = new ArrayList<Integer>();
		params.add(rc.getStar());
		AddDetailByParamsCheckStatus(role, MissionDefine.PROPERTY_侠客_获得_MISSION, params, SYMBOL_ADD, 1);
	}

	/**
	 * 任意装备升级
	 * 
	 * @param role
	 * @param level
	 * @throws BizException
	 */
	public void lvUpRoleEquip(Role role, int level) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_装备_升级_MISSION, SYMBOL_MAX_REPLACE, level);
	}

	/**
	 * 装备洗练
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void changeRoleEquipProp(Role role, int cnt) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_装备_洗练_MISSION, SYMBOL_ADD, cnt);
	}

	/**
	 * 真元获得
	 * 
	 * @param role
	 * @param yuan
	 * @throws BizException
	 */
	public void newRoleYuan(Role role, RoleYuan yuan) throws BizException {
		List<Integer> params = new ArrayList<Integer>();
		params.add(yuan.getQuality());
		AddDetailByParamsCheckStatus(role, MissionDefine.PROPERTY_真气_获得_MISSION, params, SYMBOL_ADD, 1);
	}

	/**
	 * 聚气
	 * 
	 * @param role
	 * @param count
	 * @throws BizException
	 */
	public void collectRoleYuan(Role role, int count) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_真气_聚气_MISSION, SYMBOL_ADD, count);
	}

	/**
	 * 任意真气升级
	 * 
	 * @param role
	 * @param level
	 * @throws BizException
	 */
	public void lvUpRoleYuan(Role role, int level) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_真气_升级_MISSION, SYMBOL_MAX_REPLACE, level);
	}

	/**
	 * 任意内外功升级
	 * 
	 * @param role
	 * @param level
	 * @throws BizException
	 */
	public void lvUpRoleGong(Role role, int level) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_武学_升级_MISSION, SYMBOL_MAX_REPLACE, level);
	}

	public void useItem(Role role, int itemId, int count) throws BizException {
		List<Integer> params = new ArrayList<Integer>();
		params.add(itemId);
		AddDetailByParamsCheckStatus(role, MissionDefine.PROPERTY_道具_使用道具_MISSION, params, SYMBOL_ADD, count);
	}

	/**
	 * 副本战斗
	 * 
	 * @param role
	 * @param id
	 * @throws BizException
	 */
	public void battle(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_副本_通关_MISSION);
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_副本_星数达到_MISSION);
	}

	public void battleCnt(Role role, int cnt) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_副本_战斗_MISSION, SYMBOL_ADD, cnt);
	}

	/**
	 * 副本战斗
	 * 
	 * @param role
	 * @param id
	 * @throws BizException
	 */
	public void eliteBattle(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_精英副本_通关_MISSION);
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_精英副本_战斗_MISSION, SYMBOL_ADD, 1);
	}

	/**
	 * 竞技场挑战
	 * 
	 * @param role
	 * @param up
	 * @throws BizException
	 */
	public void changeRoleArena(Role role, boolean up) throws BizException {
		if (up)
			AddDetailCheckStatus(role, MissionDefine.PROPERTY_竞技场_排名达到_MISSION);
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_竞技场_挑战_MISSION, SYMBOL_ADD, 1);
	}

	/**
	 * 
	 * @param role
	 * @param floor
	 * @throws BizException
	 */
	public void swordFight(Role role, int floor) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_论剑_通关层_MISSION, SYMBOL_MAX_REPLACE, floor);
	}

	/**
	 * 重置论剑
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void resetSword(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_论剑_重置_MISSION, SYMBOL_ADD, 1);
	}

	/**
	 * 夺宝抢夺
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void snatch(Role role, int num) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_夺宝_抢夺_MISSION, SYMBOL_ADD, num);
	}

	/**
	 * 参加boss战
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void enterBoss(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_烛龙殿_参加_MISSION, SYMBOL_ADD, 1);
	}

	/**
	 * 击杀boss
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void killBoss(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_烛龙殿_击杀_MISSION, SYMBOL_ADD, 1);
	}

	/**
	 * 劫富济贫 伤害值达到
	 * 
	 * @param role
	 * @param damage
	 * @throws BizException
	 */
	public void jfjpDamage(Role role, int damage) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_劫富济贫_伤害达到_MISSION, SYMBOL_MAX_REPLACE, damage);
	}

	/**
	 * 江湖路 任意侠客升级
	 * 
	 * @param role
	 * @param level
	 * @throws BizException
	 */
	public void lvUpRoadCard(Role role, int level) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_江湖路_侠客等级_MISSION, SYMBOL_MAX_REPLACE, level);
	}

	/**
	 * 送礼
	 * 
	 * @param role
	 * @param count
	 * @throws BizException
	 */
	public void useGift(Role role, int count) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_江湖路_送礼_MISSION, SYMBOL_ADD, count);
	}

	/**
	 * 好友送耐力
	 * 
	 * @param role
	 * @param count
	 * @throws BizException
	 */
	public void friendSendGift(Role role, int count) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_好友_送耐力_MISSION, SYMBOL_ADD, count);
	}

	/**
	 * 添加好友
	 * 
	 * @param role
	 * @param count
	 * @throws BizException
	 */
	public void friendAdd(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_好友_加好友_MISSION);
	}

	/**
	 * 客栈睡觉
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void roleSleep(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_客栈_睡觉_MISSION, SYMBOL_ADD, 1);
	}

	/**
	 * 神秘商店
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void refreshShenMiShop(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_神秘商店_刷新_MISSION, SYMBOL_ADD, 1);
	}

	/**
	 * 活动副本战斗次数
	 * 
	 * @param role
	 * @param aid
	 * @throws BizException
	 */
	public void activityBattleCnt(Role role, int aid) throws BizException {
		List<Integer> params = new ArrayList<Integer>();
		params.add(aid);
		AddDetailByParamsCheckStatus(role, MissionDefine.PROPERTY_活动副本_战斗_MISSION, params, SYMBOL_ADD, 1);
	}

	/**
	 * 帮派捐赠
	 * 
	 * @param role
	 * @throws BizException
	 */
	public void unionDonate(Role role) throws BizException {
		AddDetailCheckStatus(role, MissionDefine.PROPERTY_帮派_捐赠_MISSION, SYMBOL_ADD, 1);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	//
	private List<Mission> AddDetailCheckStatus(Role role, int property) throws BizException {
		return AddDetailByParamsCheckStatus(role, property, nullParams, SYMBOL_NULL, 0);
	}

	//
	// private List<Mission> AddDetailByParamsCheckStatus(
	// Role role,
	// int property,
	// List<Integer> params) throws BizException {
	// return AddDetailByParamsCheckStatus(role,property,params,0,0);
	// }
	//
	private List<Mission> AddDetailCheckStatus(Role role, int property, int symbol, int val) throws BizException {
		return AddDetailByParamsCheckStatus(role, property, nullParams, symbol, val);
	}

	//
	private List<Mission> AddDetailByParamsCheckStatus(Role role, int property, List<Integer> params, int symbol,
			int val) throws BizException {
		int roleId = role.getId();
		List<Mission> res = new ArrayList<Mission>();
		int[] status = { Mission.STATUS_STARTED, Mission.STATUS_FINISHED };
		List<Mission> list = missionAO.queryMissionByPropertyStatus(roleId, property, status);
		MissionDefine missionDefine = null;
		for (Mission mission : list) {
			missionDefine = missionAO.queryMissionDefineById(mission.getMissionDefineId());
			if (missionDefine == null) {
				logger.warn("not found define mission:" + mission.getMissionDefineId());
				continue;
			}
			// 验证列表中属性值
			boolean isMeetParam = true;
			List<Integer> defPrams = missionDefine.getPrams();
			if (params.size() == 0) {

			} else if (defPrams.size() != params.size() + 1) {
				isMeetParam = false;
				logger.error("prams size is not match:property:" + property + ",mission:" + missionDefine.getId());
			} else {
				for (int i = 0; i < defPrams.size() - 1; i++) {
					if (defPrams.get(i) != 0 && params.get(i).intValue() != defPrams.get(i).intValue()) {
						isMeetParam = false;
						break;
					}
				}
			}
			if (!isMeetParam)
				continue;
			// 满足所有属性值 则处理值
			int detail = mission.getMissionDetail();
			switch (symbol) {
			case SYMBOL_ADD:
				detail += val;
				break;
			case SYMBOL_REPLACE:
				detail = val;
				break;
			case SYMBOL_MAX_REPLACE:
				detail = val > detail ? val : detail;
				break;
			default:
				break;
			}
			mission.setMissionDetail(detail);
			if (checkMissionStatus(role, mission, missionDefine)) {
				res.add(mission);
			}
		}
		return res;
	}
	//
	///////////////////////////////////////////////////////////////////////////////////////////////

	public boolean checkMissionStatus(Role role, Mission mission, MissionDefine missionDefine) throws BizException {
		boolean isFinish = false;
		if (missionDefine == null) {
			missionDefine = missionAO.queryExistMissionDefineById(mission.getMissionDefineId());
			if (missionDefine == null) {
				missionAO.checkExpireMission(mission);
				logger.warn("not found define mission:" + mission.getMissionDefineId());
				return false;
			}
		}
		int count = mission.getMissionDetail();
		// 目标数量 永远是 列表最后一位
		int target = missionAO.queryMissionDefineCountById(mission.getMissionDefineId());
		logger.info("checkMissionStatus:roleId" + role.getId() + "\n" + "mission:" + mission + "\n" + "define:"
				+ missionDefine);
		switch (mission.getMissionProperty()) {
		case MissionDefine.PROPERTY_主角_战斗力达到_MISSION:
			isFinish = role.getAttack() >= target;
			mission.setMissionDetail(role.getAttack());
			break;
		case MissionDefine.PROPERTY_主角_等级达到_MISSION:
			isFinish = role.getLevel() >= target;
			mission.setMissionDetail(role.getLevel());
			break;
		case MissionDefine.PROPERTY_副本_通关_MISSION:
			RoleBattle rb = roleBattleDAO().query(role.getId());
			int lvid = missionDefine.getPrams().get(0);
			isFinish = rb.getBattleLvID() > lvid;
			if (rb.getBattleLvID() == lvid) {
				LvNum ln = cacheManager.getLvNumBylv(rb.getBattleHis(), lvid);
				isFinish = (ln == null || ln.getN() > 0);
			}
			mission.setMissionDetail(isFinish ? 1 : 0);
			break;
		case MissionDefine.PROPERTY_副本_星数达到_MISSION:
			rb = roleBattleDAO().query(role.getId());
			isFinish = rb.getBattleTotalStars() >= target;
			mission.setMissionDetail(rb.getBattleTotalStars());
			break;
		case MissionDefine.PROPERTY_精英副本_通关_MISSION:
			RoleEliteBattle reb = roleChallengeBattleDAO().queryEliteBattleByRoleId(role.getId());
			isFinish = reb != null ? reb.getEliteBattleLv() >= missionDefine.getPrams().get(0) : false;
			mission.setMissionDetail(isFinish ? 1 : 0);
			break;
		case MissionDefine.PROPERTY_竞技场_排名达到_MISSION:
			RoleArena ra = roleArenaDAO().query(role.getId());
			isFinish = ra != null ? ra.getRank() <= target : false;
			mission.setMissionDetail(
					ra.getRank() <= mission.getMissionDetail() ? ra.getRank() : mission.getMissionDetail());
			break;
		case MissionDefine.PROPERTY_好友_加好友_MISSION:
			int opposNum = roleFriendDAO().queryCountByRoelId(role.getId());
			mission.setMissionDetail(opposNum >= mission.getMissionDetail() ? opposNum : mission.getMissionDetail());
			isFinish = mission.getMissionDetail() >= target;
			break;
		case MissionDefine.PROPERTY_主角_充值天数_MISSION:
			Date time = roleStatisticsDAO().queryLastChargeTime(role.getId());
			if (time != null && !DateUtil.isSameDay(new Date(), mission.getStartTime())
					&& !DateUtil.isYesterday(time)) {
				mission.setMissionDetail(1);
			}
			isFinish = mission.getMissionDetail() >= target;
			break;
		case MissionDefine.PROPERTY_主角_一次性充值_MISSION:
			isFinish = mission.getMissionDetail() == target;
			break;
		default:
			isFinish = count >= target;
			break;
		}
		if (mission.getStatus() == Mission.STATUS_STARTED && isFinish) {
			finishMission(role, mission);
			if (missionDefine.getAutoReward() == 1) {
				getMissionReward(role, mission, missionDefine);
			}
		}
		missionDAO().updateMission(mission);
		return isFinish;
	}

	/**
	 * 完成任务
	 * 
	 * @param team
	 * @param mission
	 * @throws BizException
	 */
	private void finishMission(Role role, Mission mission) throws BizException {
		if (mission == null) {
			return;
		}
		if (mission.getStatus() != Mission.STATUS_STARTED) {
			return;
		}
		mission.setEndTime(new Date());
		mission.setStatus(Mission.STATUS_FINISHED);
		mission.setRoleId(role.getId());
	}

	/**
	 * 接受可开始的任务
	 * 
	 * @param team
	 * @throws BizException
	 */
	public List<Mission> AcceptBeStartMission(Role role) throws BizException {
		int roleId = role.getId();
		List<Mission> allMission = missionAO.queryMissionList(roleId);
		List<Mission> acceptMission = new ArrayList<Mission>();
		Map<Integer, Mission> mapMissionStatus = new HashMap<Integer, Mission>();
		for (Mission m : allMission) {
			if (!missionAO.checkExpireMission(m)) {
				mapMissionStatus.put(m.getMissionDefineId(), m);
			}
		}
		Map<Integer, MissionDefine> mapMissionDeines = missionAO.queryMissionDefines();
		Integer defineId = null;
		MissionDefine md = null;
		for (Map.Entry<Integer, MissionDefine> entry : mapMissionDeines.entrySet()) {
			defineId = entry.getKey();
			md = entry.getValue();
			// 如果没接过此任务
			if (mapMissionStatus.get(defineId) != null)
				continue;

			// 验证等级
			if (md.getLevelLimit() != MissionDefine.LEVEL_UNLIMIT && md.getLevelLimit() > role.getLevel())
				continue;

			int limitMissionId = md.getLimitMissionId();
			Mission lm = mapMissionStatus.get(limitMissionId);
			Integer status = lm == null ? null : lm.getStatus();
			int defineDetail = lm == null ? 0 : lm.getMissionDetail();
			// 验证前置任务
			if (limitMissionId != 0 && (status == null
					// 已完成 领奖 就可接下一任务
					// || status < Mission.STATUS_FINISHED)) continue;
					|| status < Mission.STATUS_END))
				continue;

			// 时效任务
			if (!md.isStart() || md.isEnd()) {
				continue;
			}

			try {
				Mission m = missionAO.addMission(role, defineId, Mission.STATUS_STARTED, defineDetail);
				// 检查是否可自动完成
				checkMissionStatus(role, m, md);
				// 发送接受任务
				acceptMission.add(m);
				mapMissionStatus.put(defineId, m);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return acceptMission;
	}

	/**
	 * 领取任务奖励，领取奖励之前任务必须是 {@link Mission#STATUS_FINISHED} 状态， 领取以后任务状态会修改成
	 * {@link Mission#STATUS_END}状态，并且任务结束日期会 设置成当前时间
	 * 
	 * @param missionDefineId
	 * @return
	 * @throws MissionException
	 */
	public List<ProbItem> getMissionReward(Role role, int missionDefineId) throws BizException {
		Mission mission = missionAO.queryMissionByDefineId(role.getId(), missionDefineId);
		if (mission == null || mission.getStatus() < Mission.STATUS_FINISHED) {
			throw MissionException.getException(MissionException.EXCE_MISSION_NOT_FINISH, role.getId(),
					missionDefineId);
		}
		if (mission.getStatus() > Mission.STATUS_FINISHED) {
			throw MissionException.getException(MissionException.EXCE_MISSION_IS_REWARDED, role.getId(),
					missionDefineId);
		}

		MissionDefine missionDefine = missionAO.queryExistMissionDefineById(mission.getMissionDefineId());
		List<ProbItem> reward = getMissionReward(role, mission, missionDefine);
		missionDAO().updateMission(mission);
		return reward;
	}

	private List<ProbItem> getMissionReward(Role role, Mission mission, MissionDefine missionDefine)
			throws BizException {
		List<ProbItem> reward = missionAO.queryMissionRewardById(missionDefine.getId());
		packetAO.sendAward(role, reward, RoleItemLog.SYS_任务_领取奖励, missionDefine.getId() + "");

		if (missionDefine.getJifen() > 0) {
			RoleDailyMission rdm = missionAO.queryRoleDailyMission(role.getId());
			rdm.setJifen(rdm.getJifen() + missionDefine.getJifen());
			roleDailyMissionDAO().update(rdm);
		}
		mission.setFinishTime(new Date());
		mission.setStatus(Mission.STATUS_END);
		mission.setRoleId(role.getId());
		return reward;
	}

}
