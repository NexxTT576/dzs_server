package com.mdy.dzs.game.dao;

import com.mdy.dzs.game.domain.activity.touzigame.RoleActivityTouzi;

import java.util.List;

import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 投资计划DAO
 * 
 * @author 白雪林
 *
 */
public class RoleActivityTouziDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleActivityTouzi> HANDLER = new ResultSetRowHandler<RoleActivityTouzi>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleActivityTouzi handleRow(ResultSetRow row) throws Exception {
			RoleActivityTouzi rat = new RoleActivityTouzi();
			rat.setRoleId(row.getInt("role_id"));
			rat.setHasBuy(row.getInt("has_buy"));
			rat.setGetLevelList(JSONUtil.fromJson(row.getString("get_level_list"), List.class));
			rat.setCreateTime(row.getTimestamp("create_time"));
			rat.setUpdateTime(row.getTimestamp("update_time"));
			return rat;
		}
	};

	/**
	 * 添加
	 */
	public void add(int roleid) {
		String sql = "insert into t_role_activity_touzi(" + "role_id," + "create_time," + "update_time"
				+ ")values(?,now(),now())";
		execute(sql, roleid);
	}

	/**
	 * 查询
	 */
	public RoleActivityTouzi query(int roleid) {
		return queryForObject("select * from t_role_activity_touzi where role_id=?", HANDLER, roleid);
	}

	// 更新为已购买
	public void setHasBuy(int roleid) {
		execute("update t_role_activity_touzi set has_buy=1 , update_time=now() where role_id=?", roleid);
	}

	// 更新已领取列表
	public void updateGetList(List<Integer> getList, int roleid) {
		String sql = "update t_role_activity_touzi set " + " update_time=now(), " + " get_level_list=?"
				+ " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(getList), roleid);
	}
}