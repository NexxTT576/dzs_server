package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.DataAction;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.mission.ActivityMissionProperty;
import com.mdy.dzs.data.domain.mission.DailyMissionReward;
import com.mdy.dzs.data.domain.mission.MissionDefine;
import com.mdy.dzs.game.domain.mission.Mission;
import com.mdy.dzs.game.domain.mission.RoleDailyMission;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.MissionException;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * 任务
 * 
 * @author fangtong
 *
 */
public class MissionAO extends BaseAO {
	//
	private static Logger logger = LoggerFactory.get(MissionAO.class);
	private static final DataAction dataAction = Container.get().createRemote(DataAction.class,
			DataApplication.CLUSTER_DATA_SYSTEM);

	private Map<Integer, MissionDefine> missionDefineMap;
	private Map<Integer, Integer> missionDefineCountMap;
	private Map<Integer, List<ProbItem>> missionRewardMap;
	private Map<Integer, List<ProbItem>> dailyMissionRewardMap;
	private List<MissionDefine> extendsMissionDefines;

	public MissionAO() {
		// missionDefineMap = new HashMap<Integer, MissionDefine>();
		// missionDefineCountMap = new HashMap<Integer, Integer>();
	}

	/**
	 * 查询任务定义 列表
	 * 
	 * @return 任务定义列表
	 */
	public Map<Integer, MissionDefine> queryMissionDefines() {
		if (missionDefineMap == null || missionDefineMap.size() == 0) {
			initMissionDefine();
		}
		return missionDefineMap;

	}

	/**
	 * 通过ID得到任务定义
	 * 
	 * @param missionId
	 * @return
	 * @throws MissionException
	 */
	public MissionDefine queryExistMissionDefineById(int missionDefineId) throws BizException {
		MissionDefine missionDefine = missionDefineMap.get(missionDefineId);
		if (missionDefine != null) {
			return missionDefine;
		}
		throw MissionException.getException(MissionException.EXCE_CAN_NOT_FOUND_TASK_DEFINE, missionDefineId);
	}

	/**
	 * 查询已接对应属性任务对应值
	 * 
	 * @param roleId
	 * @param property
	 * @return
	 */
	public int getActivityMissionCntByProperty(int roleId, int property) {
		Mission m = missionDAO().queryMissionByPropertyAccept(roleId, property);
		int res = 1;
		if (m != null && !checkExpireMission(m)) {
			Integer cnt = missionDefineCountMap.get(m.getMissionDefineId());
			if (cnt != null)
				res = cnt;
		}
		return res;
	}

	/**
	 * 查询已接对应属性任务
	 * 
	 * @param roleId
	 * @param property
	 * @return
	 */
	public boolean isAcceptActivityMissionByProperty(int roleId, ActivityMissionProperty property) {
		Mission m = missionDAO().queryMissionByPropertyAccept(roleId, property.value());
		boolean res = false;
		if (m != null && !checkExpireMission(m)) {
			res = true;
		}
		return res;
	}

	/**
	 * 获取任务奖励
	 * 
	 * @param missionDefineId
	 * @return
	 */
	public List<ProbItem> queryMissionRewardById(int missionDefineId) {
		List<ProbItem> rewards = missionRewardMap.get(missionDefineId);
		if (rewards == null) {
			rewards = new ArrayList<ProbItem>();
		}
		return rewards;
	}

	/**
	 * 通过ID得到任务定义
	 * 
	 * @param missionId
	 * @return
	 */
	public MissionDefine queryMissionDefineById(int missionDefineId) {
		MissionDefine missionDefine = missionDefineMap.get(missionDefineId);
		return missionDefine;
	}

	/**
	 * 根据分类查询任务定义
	 * 
	 * @param category 任务分类
	 * @return
	 * @see MissionDefine#queryMissionDefineByCategory()
	 */
	public List<MissionDefine> queryMissionDefineByCategory(int category) {
		List<MissionDefine> result = new ArrayList<MissionDefine>();
		for (MissionDefine td : missionDefineMap.values()) {
			if (td.getCategory() == category) {
				result.add(td);
			}
		}
		return result;
	}

	/**
	 * 根据分类,级别查询任务定义
	 * 
	 * @param category 任务分类
	 * @return
	 * @see MissionDefine#queryMissionDefineByCategory()
	 */
	public List<MissionDefine> queryMissionDefineByCategoryAndGrade(int category, int grade) {
		List<MissionDefine> result = new ArrayList<MissionDefine>();
		for (MissionDefine td : missionDefineMap.values()) {
			if (td.getCategory() != category) {
				continue;
			}
			if (grade < td.getLevelLimit()) {
				continue;
			}
			result.add(td);
		}
		return result;
	}

	public Integer queryMissionDefineCountById(int defineId) {
		return missionDefineCountMap.get(defineId);
	}

	public void reloadMissionDefine() {
		List<MissionDefine> extendsList = missionDAO().queryMissionExtends();
		List<MissionDefine> newExtendsMissionDefines = new ArrayList<MissionDefine>();
		int count = 0;
		int paramSize = 0;
		for (MissionDefine define : extendsList) {
			if (!checkMissionDefine(define))
				continue;
			if (define.isEnd()) {
				if (missionDefineMap.containsKey(define.getId())) {
					missionDefineMap.remove(define.getId());
					missionDefineCountMap.remove(define.getId());
					missionRewardMap.remove(define.getId());
				}
				continue;
			}
			// 设置任务
			missionDefineMap.put(define.getId(), define);
			paramSize = define.getPrams() != null ? define.getPrams().size() : 0;
			// 目标数量 永远是 列表最后一位
			count = paramSize == 0 ? 0 : define.getPrams().get(paramSize - 1);
			missionDefineCountMap.put(define.getId(), count);

			// 设置奖励
			List<ProbItem> rewards = new ArrayList<ProbItem>();
			for (int i = 0; i < define.getRewardIds().size(); i++) {
				rewards.add(new ProbItem(define.getRewardTypes().get(i), define.getRewardIds().get(i),
						define.getRewardNums().get(i)));
			}
			missionRewardMap.put(define.getId(), rewards);
			newExtendsMissionDefines.add(define);
		}
		extendsMissionDefines = newExtendsMissionDefines;
		Collections.sort(extendsMissionDefines, new Comparator<MissionDefine>() {
			public int compare(MissionDefine obj1, MissionDefine obj2) {
				return obj1.getRenWuPaiXu() - obj2.getRenWuPaiXu();
			}
		});
	}

	// 初始化任务
	public List<MissionDefine> initMissionDefine() {
		missionDefineMap = Collections.synchronizedMap(new HashMap<Integer, MissionDefine>());
		missionDefineCountMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		missionRewardMap = Collections.synchronizedMap(new HashMap<Integer, List<ProbItem>>());
		List<MissionDefine> defineList = dataAction.queryMissionDefineDatas();

		int count = 0;
		int paramSize = 0;
		for (MissionDefine define : defineList) {
			if (!checkMissionDefine(define))
				continue;
			// 设置任务
			missionDefineMap.put(define.getId(), define);
			paramSize = define.getPrams() != null ? define.getPrams().size() : 0;
			// 目标数量 永远是 列表最后一位
			count = paramSize == 0 ? 0 : define.getPrams().get(paramSize - 1);
			missionDefineCountMap.put(define.getId(), count);

			// 设置奖励
			List<ProbItem> rewards = new ArrayList<ProbItem>();
			for (int i = 0; i < define.getRewardIds().size(); i++) {
				rewards.add(new ProbItem(define.getRewardTypes().get(i), define.getRewardIds().get(i),
						define.getRewardNums().get(i)));
			}
			missionRewardMap.put(define.getId(), rewards);
		}

		dailyMissionRewardMap = Collections.synchronizedMap(new HashMap<Integer, List<ProbItem>>());
		List<DailyMissionReward> drewards = dataAction.queryDailyMissionRewardDatas();
		for (DailyMissionReward dr : drewards) {
			// 设置奖励
			List<ProbItem> rewards = new ArrayList<ProbItem>();
			for (int i = 0; i < dr.getRewardIds().size(); i++) {
				rewards.add(
						new ProbItem(dr.getRewardTypes().get(i), dr.getRewardIds().get(i), dr.getRewardNums().get(i)));
			}
			dailyMissionRewardMap.put(dr.getId(), rewards);
		}
		return defineList;
	}

	/**
	 * 获取扩展任务
	 * 
	 * @return
	 */
	public List<MissionDefine> getExtendsMissionDefines() {
		return extendsMissionDefines;
	}

	public boolean checkMissionDefine(MissionDefine define) {
		int property = define.getProperty();
		Integer prams = MissionDefine.PARAMS_MATCH_NUM.get(property);
		boolean res = false;
		if (prams == null) {
			logger.warn("mission define not property " + property + ",defineId" + define.getId() + " name:"
					+ define.getName());
		} else {
			int curPrams = define.getPrams() == null ? 0 : define.getPrams().size();
			res = prams == curPrams;
			if (!res) {
				logger.warn("mission define pram size not match, " + " defineId:" + define.getId() + " name:"
						+ define.getName() + " property:" + property);
			}
		}
		return res;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 根据俱乐部ID查询任务列表
	 * 
	 * @param clubId
	 * @return
	 */
	public List<Mission> queryMissionListByCategory(int roleId, int category) {
		return missionDAO().queryMissionListByCategory(roleId, category);
	}

	/**
	 * 根据俱乐部ID查询任务列表
	 * 
	 * @param clubId
	 * @return
	 */
	public List<Mission> queryMissionList(int roleId) {
		return missionDAO().queryMissionList(roleId);
	}

	/**
	 * 根据俱乐部ID查询任务列表
	 * 
	 * @param clubId
	 * @return
	 */
	public List<Mission> queryAcceptMissionList(int roleId) {
		return missionDAO().queryAcceptMissionList(roleId);
	}

	// 根据属性 查询任务列表
	public List<Mission> queryMissionByPropertyStatus(int roleId, int property, int[] status) {
		return missionDAO().queryMissionByPropertyStatus(roleId, property, status);
	}
	// /**
	// * 根据状态查询任务列表
	// *
	// * @param club
	// * @param state
	// * @return
	// */
	// public int queryMissionCountByStatus(int roleId, int status) {
	// return missionDAO().queryMissionCountByStatus(roleId, status);
	// }

	// 根据任务定义ID查询任务
	public Mission queryMissionByDefineId(int roleId, int missionDefineId) {
		return missionDAO().queryMissionByDefine(roleId, missionDefineId);
	}

	// 更新任务
	public void updateMission(Mission t) {
		missionDAO().updateMission(t);
	}

	/**
	 * 删除任务
	 * 
	 * @param mission
	 */
	public void deleteMission(int roleId, int defineId) {
		missionDAO().deleteMissionByRoleId(roleId, defineId);
	}

	/**
	 * 添加任务
	 * 
	 * @param clubId
	 * @param missionId
	 * @throws MissionException
	 */
	public Mission addMission(Role role, int missionDefineId, int status, int define) throws BizException {
		MissionDefine missionDefine = queryExistMissionDefineById(missionDefineId);
		if (role.getLevel() < missionDefine.getLevelLimit()) {
			throw MissionException.getException(MissionException.EXCE_MISSION_LEVEL_ERROR,
					missionDefine.getLevelLimit(), role.getLevel());
		}
		Mission mission = missionDAO().queryMissionByDefine(role.getId(), missionDefineId);
		if (mission != null) {
			return mission;
		}
		// 验证是否需要变更默认计数
		if (missionDefine.getInherit() == 0) {
			define = 0;
		}
		mission = new Mission();
		mission.setRoleId(role.getId());
		mission.setLevel(role.getLevel());
		mission.setMissionCategory(missionDefine.getCategory());
		mission.setMissionProperty(missionDefine.getProperty());
		mission.setMissionDefineId(missionDefineId);
		mission.setMissionDetail(define);
		mission.setStatus(status);
		mission.setRefreshDaily(missionDefine.getRefreshDaily());
		if (Mission.STATUS_STARTED == status) {
			mission.setStartTime(new Date());
		}
		missionDAO().addMission(mission);
		return mission;
	}

	// /**
	// * 初始化活动任务
	// *
	// * @param club
	// * @return
	// * @return
	// * @throws MissionException
	// */
	// public void initActivityMission(Team team) throws BizException {
	// int roleId = team.getTeamId();
	// List<MissionDefine> missionDefineList = queryMissionDefineByCategory(
	// MissionDefine.CATEGORY_ACTIVITY_MISSION); //获取所有活动任务列表
	// List<Mission> missionList = queryMissionListByCategory(
	// roleId, MissionDefine.CATEGORY_ACTIVITY_MISSION ); //获取某俱乐部的活动列表
	// for (MissionDefine missionDefine : missionDefineList) {
	// int missionDefineId = missionDefine.getId();
	// if (missionDefine.isStart() && (!missionDefine.isEnd())) {
	// Mission mission = missionDAO().queryMissionByDefine(
	// roleId,missionDefineId );
	// if (mission == null) {
	// addMission(team,missionDefineId,Mission.STATUS_STARTED);
	// }
	// }else if(missionDefine.isEnd()){
	// if(missionList==null||missionList.isEmpty()){
	// continue;
	// }
	// for (Mission mission : missionList) {
	// if (mission.getMissionDefineId() == missionDefineId) {
	// if (mission.getStatus() == Mission.STATUS_STARTED) {
	// mission.setStatus(Mission.STATUS_FINISHED);
	// updateMission(mission);
	// }
	// }
	// }
	// }
	// }
	// }

	/**
	 * 删除每日任务
	 */
	public void dailyRefreshMissions(int roleId) {
		RoleDailyMission rdm = queryRoleDailyMission(roleId);
		if (!DateUtil.isToday(rdm.getDayRefresh())) {
			missionDAO().clearRoleDailyMissions(roleId);
			rdm.setJifen(0);
			rdm.setDayRefresh(new Date());
			rdm.setReawrds(new ArrayList<Integer>());
			roleDailyMissionDAO().update(rdm);
		}
	}

	public RoleDailyMission queryRoleDailyMissionFromDB(int roleId) {
		return roleDailyMissionDAO().query(roleId);
	}

	public RoleDailyMission queryRoleDailyMission(int roleId) {
		RoleDailyMission rdm = roleDailyMissionDAO().query(roleId);
		if (rdm == null) {
			Date now = new Date();
			rdm = new RoleDailyMission();
			rdm.setRoleId(roleId);
			rdm.setDayRefresh(now);
			rdm.setCreateTime(now);
			rdm.setUpdateTime(now);
			rdm.setReawrds(new ArrayList<Integer>());
			roleDailyMissionDAO().add(rdm);
		}
		return rdm;
	}

	//
	@Override
	public void start() {
		initMissionDefine();
		reloadMissionDefine();
	}

	/**
	 * @param id
	 * @return
	 */
	public List<ProbItem> getDailyMissionRewards(int id) {
		return dailyMissionRewardMap.get(id);
	}

	/**
	 * @param rdm
	 */
	public void updateRoleDailyMission(RoleDailyMission rdm) {
		roleDailyMissionDAO().update(rdm);
	}

	/**
	 * 检查过期任务
	 * 
	 * @param team
	 */
	public boolean checkExpireMission(Mission m) {
		MissionDefine md = queryMissionDefineById(m.getMissionDefineId());
		if (md == null || md.isEnd()) {
			deleteMission(m.getRoleId(), m.getMissionDefineId());
			return true;
		}
		return false;
	}
}
