package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.data.dao.datas.MissionDefineDAO;
import com.mdy.dzs.data.domain.mission.MissionDefine;
import com.mdy.dzs.game.domain.mission.Mission;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 任务DAO
 * 
 * @author fangtong
 */
public class MissionDAO extends ConnectionResourceDAO {
	/* 球队表名 */
	private static final String TABLE_NAME = "t_mission";
	/* 分表数 */
	public static final int TABLE_PART_NUM = 10;

	//
	private static String getTableName(int roleId) {
		return TABLE_NAME + "_" + roleId % TABLE_PART_NUM;
	}

	/*
	 * 转换Mission对象的RowHandler
	 */
	private static final ResultSetRowHandler<Mission> MISSION_ROW_HANDLER = new ResultSetRowHandler<Mission>() {
		@Override
		public Mission handleRow(ResultSetRow row) throws Exception {
			Mission t = new Mission();
			t.setMissionDefineId(row.getInt("mission_define_id"));
			t.setMissionCategory(row.getInt("mission_category_id"));
			t.setMissionProperty(row.getInt("mission_property"));
			t.setLevel(row.getInt("level"));
			t.setStatus(row.getInt("status"));
			t.setRefreshDaily(row.getInt("refresh_daily"));
			t.setRoleId(row.getInt("role_id"));
			t.setStartTime(row.getTimestamp("start_time"));
			t.setEndTime(row.getTimestamp("end_time"));
			t.setFinishTime(row.getTimestamp("finish_time"));
			t.setMissionDetail(row.getInt("mission_detail"));
			return t;
		}
	};

	/**
	 * 增加任务
	 * 
	 * @param mission
	 */
	public void addMission(Mission mission) {
		String sql = "insert into " + getTableName(mission.getRoleId()) + "(	role_id," + "level,"
				+ "mission_define_id," + "mission_category_id," + "status," + "mission_detail," + "refresh_daily,"
				+ "start_time," + "finish_time," + "end_time," + "mission_property )values(?,?,?,?,?,?,?,?,?,?,?);";
		execute(sql, mission.getRoleId(), mission.getLevel(), mission.getMissionDefineId(),
				mission.getMissionCategory(), mission.getStatus(), mission.getMissionDetail(),
				mission.getRefreshDaily(), mission.getStartTime(), mission.getFinishTime(), mission.getEndTime(),
				mission.getMissionProperty());
	}

	/**
	 * 根据俱乐部ID查询任务
	 * 
	 * @param roleId
	 * @param missionDefineId
	 * @return
	 */
	public Mission queryMissionByDefine(int roleId, int missionDefineId) {
		return queryForObject(" select * from " + getTableName(roleId) + " where role_id=? and mission_define_id=? ",
				MISSION_ROW_HANDLER, roleId, missionDefineId);
	}

	/**
	 * 根据球队ID 查询任务列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Mission> queryMissionList(int roleId) {
		String sql = "select * from " + getTableName(roleId) + " where role_id=? ";
		return queryForList(sql, MISSION_ROW_HANDLER, roleId);
	}

	/**
	 * 根据球队ID 查询任务列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Mission> queryAcceptMissionList(int roleId) {
		String sql = "select * from " + getTableName(roleId) + " where role_id=? and status<?";
		return queryForList(sql, MISSION_ROW_HANDLER, roleId, Mission.STATUS_END);
	}

	/**
	 * 根据球队ID和类型 查询任务列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Mission> queryMissionListByCategory(int roleId, int category) {
		String sql = "select * from " + getTableName(roleId) + " where role_id=? " + "and mission_category_id=? ";
		return queryForList(sql, MISSION_ROW_HANDLER, roleId, category);
	}

	/**
	 * 根据ID和属性 状态 查询任务列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Mission> queryMissionByPropertyStatus(int roleId, int property, int[] status) {
		String str = "";
		for (int i = 0; i < status.length; i++) {
			if (i != 0)
				str += ",";
			str += status[i];
		}
		String sql = "select * from " + getTableName(roleId) + " where role_id=? "
				+ "and mission_property=? and status in (" + str + ") ";
		return queryForList(sql, MISSION_ROW_HANDLER, roleId, property);
	}

	/**
	 * 查询已接对应属性任务
	 * 
	 * @param roleId
	 * @param property
	 * @return
	 */
	public Mission queryMissionByPropertyAccept(int roleId, int property) {
		String sql = "select * from " + getTableName(roleId) + " where role_id=? "
				+ "and mission_property=? and status =? ";
		return queryForObject(sql, MISSION_ROW_HANDLER, roleId, property, Mission.STATUS_STARTED);
	}

	// /**
	// * 根据类型和状态 查询任务列表
	// * @param roleId
	// * @param missionDefineId
	// * @return
	// */
	// public List<Mission> queryMissionByCategoryStatus(int roleId, int
	// category,int status) {
	// String sql = "select * from " + getTableName( roleId )+
	// " where role_id=? " + "and mission_category_id=? and status = ?";
	// return queryForList( sql, MISSION_ROW_HANDLER,
	// roleId , category ,status);
	// }

	// /**
	// * 根据状态 查询任务数量
	// * @param roleId
	// * @param status
	// * @return
	// */
	// public int queryMissionCountByStatus(int roleId, int status) {
	// return queryForInteger(
	// " select count(*) from "+getTableName(roleId)+
	// " where role_id = ? and status = ?" , roleId , status);
	// }

	/**
	 * 更新任务
	 * 
	 * @param mission 要更新的任务
	 */
	public void updateMission(Mission mission) {
		String sql = "update " + getTableName(mission.getRoleId()) + " set " + "status=?," + "mission_detail=?,"
				+ "start_time=?," + "finish_time=?," + "end_time=? where role_id=? and mission_define_id=? ";
		executeUpdate(sql, mission.getStatus(), mission.getMissionDetail(), mission.getStartTime(),
				mission.getFinishTime(), mission.getEndTime(), mission.getRoleId(), mission.getMissionDefineId());
	}

	/**
	 * 根据类型 删除任务
	 * 
	 * @param category
	 */
	public void deleteMissionInCategorys(String[] categorys) {
		String str = "";
		for (int i = 0; i < categorys.length; i++) {
			if (i != 0)
				str += ",";
			str += "?";
		}
		for (int i = 0; i < TABLE_PART_NUM; i++) {
			String sql = "delete from " + TABLE_NAME + "_" + i + " where mission_category_id in (" + str + ")";
			execute(sql);
		}

	}

	/**
	 * 根据类型 删除任务
	 * 
	 * @param category
	 */
	public void clearRoleDailyMissions(int roleId) {

		for (int i = 0; i < TABLE_PART_NUM; i++) {
			String sql = "delete from " + TABLE_NAME + "_" + i + " where refresh_daily = 1 and role_id = ?";
			execute(sql, roleId);
		}

	}

	/**
	 * 根据类型 删除任务
	 * 
	 * @param category
	 */
	public void deleteTeamMissionsInCategorys(int roleId, String[] categorys) {
		String str = "";
		for (int i = 0; i < categorys.length; i++) {
			if (i != 0)
				str += ",";
			str += categorys[i];
		}
		String sql = "delete from " + getTableName(roleId) + " where mission_category_id in (" + str
				+ ") and role_id = ?";
		execute(sql, roleId);
	}

	/**
	 * 删除球队 某任务
	 * 
	 * @param roleId
	 * @param defineId
	 */
	public void deleteMissionByRoleId(int roleId, int defineId) {
		execute("delete from " + getTableName(roleId) + " where role_id=? and mission_define_id=?", roleId, defineId);
	}

	/**
	 * 查询所有扩展任务
	 * 
	 * @return 任务定义列表
	 */
	public List<MissionDefine> queryMissionExtends() {
		String sql = "select * from t_mission_extends ";
		return queryForList(sql, MissionDefineDAO.MISSION_DEFINE_ROW_HANDLER);
	}

	/**
	 * @return
	 */
	public int queryMissionCompleteCount(int roleId) {
		String sql = "select count(0) from " + getTableName(roleId)
				+ " where role_id=?  and status =? and mission_category_id != ?";
		return queryForInteger(sql, roleId, Mission.STATUS_FINISHED, MissionDefine.CATEGORY_ACTIVITY_MISSION);
	}
}
