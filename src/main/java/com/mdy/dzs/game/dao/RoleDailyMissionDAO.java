package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.mission.RoleDailyMission;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 每日任务记录DAO
 * 
 * @author 房曈
 *
 */
public class RoleDailyMissionDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleDailyMission> HANDLER = new ResultSetRowHandler<RoleDailyMission>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleDailyMission handleRow(ResultSetRow row) throws Exception {
			RoleDailyMission rdm = new RoleDailyMission();
			rdm.setRoleId(row.getInt("role_id"));
			rdm.setJifen(row.getInt("jifen"));
			rdm.setReawrds(JSONUtil.fromJson(row.getString("reawrds"), List.class));
			rdm.setDayRefresh(row.getTimestamp("day_refresh"));
			rdm.setCreateTime(row.getTimestamp("create_time"));
			rdm.setUpdateTime(row.getTimestamp("update_time"));
			return rdm;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleDailyMission
	 */
	public void add(RoleDailyMission rdm) {
		String sql = "insert into t_role_daily_mission(" + "role_id," + "jifen," + "reawrds," + "day_refresh,"
				+ "create_time," + "update_time" + ")values(?,?,?,?,now(),now())";
		execute(sql, rdm.getRoleId(), rdm.getJifen(), JSONUtil.toJson(rdm.getReawrds()), rdm.getDayRefresh());
	}

	/**
	 * 更新
	 * 
	 * @param RoleDailyMission
	 */
	public void update(RoleDailyMission rdm) {
		String sql = "update t_role_daily_mission set " + " update_time=now(), " + " jifen=?," + " reawrds=?,"
				+ " day_refresh=? " + " where role_id=?";
		executeUpdate(sql, rdm.getJifen(), JSONUtil.toJson(rdm.getReawrds()), rdm.getDayRefresh(), rdm.getRoleId());
	}

	/**
	 * 查询
	 */
	public RoleDailyMission query(int roleId) {
		return queryForObject("select * from t_role_daily_mission where role_id=?", HANDLER, roleId);
	}

	/**
	 * 查询列表
	 */
	public List<RoleDailyMission> queryList() {
		return queryForList("select * from t_role_daily_mission", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int roleId) {
		execute("delete  from t_role_daily_mission where role_id=?", roleId);
	}

}