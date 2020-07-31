package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.activity.ActivityConfig;
import com.mdy.dzs.game.domain.system.SystemStatus;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 活动配置DAO
 * 
 * @author 房曈
 *
 */
public class ActivityConfigDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<ActivityConfig> HANDLER = new ResultSetRowHandler<ActivityConfig>() {
		@Override
		public ActivityConfig handleRow(ResultSetRow row) throws Exception {
			ActivityConfig ac = new ActivityConfig();
			ac.setActivityId(row.getInt("activity_id"));
			ac.setActivityConfig(row.getString("activity_config"));
			ac.setOpen(row.getInt("open"));
			ac.setTimeType(row.getInt("time_type"));
			ac.setTimeParam(row.getString("time_param"));
			ac.setUpdateTime(row.getTimestamp("update_time"));
			return ac;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ActivityConfig
	 */
	public void add(ActivityConfig ac) {
		String sql = "insert into t_activity_config(" + "activity_id," + "activity_config," + "open," + "time_type,"
				+ "time_param," + "update_time" + ")values(?,?,?,?,?,now())";
		execute(sql, ac.getActivityId(), ac.getActivityConfig(), ac.getOpen(), ac.getTimeType(), ac.getTimeParam());
	}

	/**
	 * 更新
	 * 
	 * @param ActivityConfig
	 */
	public void update(ActivityConfig ac) {
		String sql = "update t_activity_config set " + " update_time=now(), " + " activity_config=?," + " open=?,"
				+ " time_type=?," + " time_param=? " + " where activity_id=?";
		executeUpdate(sql, ac.getActivityConfig(), ac.getOpen(), ac.getTimeType(), ac.getTimeParam(),
				ac.getActivityId());
	}

	/**
	 * 查询
	 */
	public ActivityConfig query(int id) {
		return queryForObject("select * from t_activity_config where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ActivityConfig> queryList() {
		return queryForList("select * from t_activity_config", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_activity_config where id=?", HANDLER, id);
	}

	////////////////////////////////////////////////////////////////////////////
	private static ResultSetRowHandler<SystemStatus> SYSTEM_HANDLER = new ResultSetRowHandler<SystemStatus>() {
		@Override
		public SystemStatus handleRow(ResultSetRow row) throws Exception {
			SystemStatus ac = new SystemStatus();
			ac.setSystemId(row.getInt("system_id"));
			ac.setOpen(row.getInt("open"));
			return ac;
		}
	};

	/**
	 * 查询列表
	 */
	public List<SystemStatus> querySystemStatusList() {
		return queryForList("select * from t_system", SYSTEM_HANDLER);
	}
}