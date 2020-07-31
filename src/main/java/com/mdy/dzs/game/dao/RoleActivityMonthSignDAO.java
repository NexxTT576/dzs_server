package com.mdy.dzs.game.dao;

import com.mdy.dzs.game.domain.activity.monthsigngame.RoleActivityMonthSign;

import java.util.List;

import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 月签DAO
 * 
 * @author 白雪林
 *
 */
public class RoleActivityMonthSignDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleActivityMonthSign> HANDLER = new ResultSetRowHandler<RoleActivityMonthSign>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleActivityMonthSign handleRow(ResultSetRow row) throws Exception {
			RoleActivityMonthSign ams = new RoleActivityMonthSign();
			ams.setRoleId(row.getInt("role_id"));
			ams.setLoginCnt(row.getInt("login_cnt"));
			ams.setGetList(JSONUtil.fromJson(row.getString("get_list"), List.class));
			ams.setDayRefreshTime(row.getTimestamp("day_refresh_time"));
			ams.setCreateTime(row.getTimestamp("create_time"));
			ams.setUpdateTime(row.getTimestamp("update_time"));
			return ams;
		}
	};

	/**
	 * 添加
	 * 
	 * @param activityMonthSign
	 */
	public void add(int roleId) {
		String sql = "insert into t_role_activity_month_sign(" + "role_id," +
		// "login_cnt," +
				"create_time," + "update_time" + ")values(?,now(),now())";
		execute(sql, roleId);
	}

	/**
	 * 查询
	 */
	public RoleActivityMonthSign query(int id) {
		return queryForObject("select * from t_role_activity_month_sign where role_id=?", HANDLER, id);
	}

	// 隔月重置
	public void resetMonthSignData(RoleActivityMonthSign rs) {
		execute("update t_role_activity_month_sign set " + "login_cnt=0, " + "get_list='[]'," + "update_time=now() "
				+ "where role_id=?", rs.getRoleId());
	}

	// 领取奖励更新
	public void updateGetList(List<Integer> getList, int roleId) {
		String sql = "update t_role_activity_month_sign set " + " update_time=now(), " + " day_refresh_time=now(), " +
		// " login_cnt=login_cnt+1, " +
				" get_list=?" + " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(getList), roleId);

	}

	// 兼容旧数据，只push进已领取列表，不更新Refresh_time
	public void updateGetListForOldData(List<Integer> getList, int roleId) {
		String sql = "update t_role_activity_month_sign set " + " update_time=now(), " +
		// " login_cnt=login_cnt+1, " +
				" get_list=?" + " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(getList), roleId);
	}
}