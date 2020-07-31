package com.mdy.dzs.game.dao;

import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.dart.DartIdAndStartTime;
import com.mdy.dzs.game.domain.dart.RoleDart;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 押镖表结构DAO
 * 
 * @author 白雪林
 *
 */
public class RoleDartDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleDart> HANDLER = new ResultSetRowHandler<RoleDart>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleDart handleRow(ResultSetRow row) throws Exception {
			RoleDart d = new RoleDart();
			d.setRoleId(row.getInt("role_id"));
			d.setCurState(row.getInt("cur_state"));
			d.setDartRefreshCnt(row.getInt("dart_refresh_cnt"));
			d.setTodayDartNum(row.getInt("today_dart_num"));
			d.setTodayRobNum(row.getInt("today_rob_num"));
			d.setStartTime(row.getTimestamp("start_time"));
			d.setEndTime(row.getTimestamp("end_time"));
			d.setOtherRoleList(JSONUtil.fromJson(row.getString("other_role_list"), List.class));
			d.setCreateTime(row.getTimestamp("create_time"));
			d.setUpdateTime(row.getTimestamp("update_time"));
			d.setNextRefreshTime(row.getTimestamp("next_refresh_time"));
			d.setBeRobNum(row.getInt("be_rob_num"));
			d.setDartCarQuality(row.getInt("dart_car_quality"));
			d.setDayRefreshTime(row.getTimestamp("day_refresh_time"));
			d.setShowMap(JSONUtil.fromJson(row.getString("show_map"), Map.class));
			return d;
		}
	};

	// 添加
	public void add(int roleId) {
		String sql = "insert into t_role_dart(" + "role_id," + "next_refresh_time," + "create_time," + "update_time"
				+ ")values(?,now(),now(),now())";
		execute(sql, roleId);
	}

	// 更新界面可见其他镖车roleId列表,下次刷新时间
	public void updateOtherRoleList(RoleDart dart, List<Integer> roleIdList, int seconds, Boolean isRefreshTime) {
		String sql = "update t_role_dart set " + "update_time = now(), ";
		if (isRefreshTime) {
			sql += "next_refresh_time=DATE_ADD(NOW(),INTERVAL ? SECOND),";
		}
		sql += "other_role_list=?," + "show_map=? " + "where role_id=? ";
		if (isRefreshTime) {
			execute(sql, seconds, JSONUtil.toJson(roleIdList), JSONUtil.toJson(dart.getShowMap()), dart.getRoleId());
		} else {
			execute(sql, JSONUtil.toJson(roleIdList), JSONUtil.toJson(dart.getShowMap()), dart.getRoleId());
		}
	}

	// 查询其他镖车
	public List<RoleDart> queryDarts(String dartRoleIdList) {
		return queryForList("select * from t_role_dart where role_id in " + dartRoleIdList, HANDLER);
	}

	private static ResultSetRowHandler<DartIdAndStartTime> ROLEID_HANDLER = new ResultSetRowHandler<DartIdAndStartTime>() {
		@Override
		public DartIdAndStartTime handleRow(ResultSetRow row) throws Exception {
			DartIdAndStartTime d = new DartIdAndStartTime();
			d.setRoleId(row.getInt("role_id"));
			d.setStartTime(row.getTimestamp("start_time"));
			return d;
		}
	};

	// 查询列表中已经完成的id
	public List<DartIdAndStartTime> queryOverListId(String roleIdString) {
		return queryForList(
				"select role_id,start_time from t_role_dart where end_time < DATE_ADD(now(),INTERVAL 5 SECOND) and role_id in "
						+ roleIdString,
				ROLEID_HANDLER);
	}

	// 查询其他镖车roleId
	public List<DartIdAndStartTime> queryDartIds(int roleId, String findString) {
		String sql = "select role_id,start_time from t_role_dart where " + " role_id not in " + findString
				+ " and start_time > DATE_SUB(now(),INTERVAL 28 MINUTE) "
				+ " and end_time > DATE_ADD(now(),INTERVAL 15 SECOND)" + " and cur_state = ? " + " ORDER BY role_id ";

		return queryForList(sql, ROLEID_HANDLER, 2);
	}

	// 更新刷新次数和镖车品质
	public void updateQualityAndRefreshtimes(int roleId, int quality) {
		String sql = "update t_role_dart set " + "update_time = now(), " + "dart_car_quality=?, "
				+ "dart_refresh_cnt=dart_refresh_cnt+1 " + "where role_id=? ";

		execute(sql, quality, roleId);
	}

	// 更新状态 已完成
	public void updateCurState(RoleDart dart) {
		String sql = "update t_role_dart set " + "update_time = now(), " + "dart_refresh_cnt=0, "
				+ "dart_car_quality=0, " + "be_rob_num = 0, " + "other_role_list = '[]', "
				+ "cur_state=? where role_id=? ";

		execute(sql, RoleDart.DART_STATE_END, dart.getRoleId());
	}

	// 更新已完成时间
	public void updateSpeedUpEnd(RoleDart dart) {
		String sql = "update t_role_dart set update_time = now(), " + "dart_refresh_cnt=0, " + "dart_car_quality=0, "
				+ "be_rob_num = 0, " + "other_role_list = '[]', " + "end_time=now(), " + "cur_state=? where role_id=? ";
		execute(sql, RoleDart.DART_STATE_END, dart.getRoleId());
	}

	// 更新押镖开始数据
	public void updateStart(RoleDart dart, int minute) {
		String sql = "update t_role_dart set " + "today_dart_num=today_dart_num+1, " + "cur_state = ?, "
				+ "update_time = now(), " + "start_time=now(), " + "end_time=DATE_ADD(NOW(),INTERVAL ? MINUTE) "
				+ "where role_id=? ";

		execute(sql, RoleDart.DART_STATE_DARTING, minute, dart.getRoleId());
	}

	// 被劫镖次数累加
	public void updateBeRobTimesAdd(RoleDart dart) {
		String sql = "update t_role_dart set " + "update_time = now(), " + "be_rob_num=be_rob_num+1 "
				+ "where role_id=? ";
		execute(sql, dart.getRoleId());
	}

	// 劫镖次数累加
	public void updateRobTimesAdd(RoleDart dart) {
		String sql = "update t_role_dart set " + "update_time = now(), " + "today_rob_num=today_rob_num+1 "
				+ "where role_id=? ";
		execute(sql, dart.getRoleId());
	}

	// 被劫镖次数查询
	public int queryBeRobNum(int otherID) {
		return queryForInteger("select be_rob_num from t_role_dart where role_id=?", otherID);
	}

	// 查询一条玩家数据
	public RoleDart query(int roleId) {
		return queryForObject("select * from t_role_dart where role_id=?", HANDLER, roleId);
	}

	// 每日重置
	public void updateDayRefresh(int roleId) {
		String sql = "update t_role_dart set " + " today_dart_num=0," + " today_rob_num=0," + " day_refresh_time=now() "
				+ " where role_id=?";
		executeUpdate(sql, roleId);
	}

}