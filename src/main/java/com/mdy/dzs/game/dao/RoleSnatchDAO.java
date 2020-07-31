package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.snatch.RoleSnatch;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 角色的夺宝状态DAO
 * 
 * @author zhou
 *
 */
public class RoleSnatchDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleSnatch> HANDLER = new ResultSetRowHandler<RoleSnatch>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleSnatch handleRow(ResultSetRow row) throws Exception {
			RoleSnatch rs = new RoleSnatch();
			rs.setRoleId(row.getInt("role_id"));
			rs.setState(row.getInt("state"));
			rs.setSnatched(row.getInt("snatched"));
			rs.setWarFreeTime(row.getInt("war_free_time"));
			rs.setSnatchFailList(JSONUtil.fromJson(row.getString("snatch_fail_list"), List.class));
			rs.setCreateTime(row.getTimestamp("create_time"));
			rs.setUpdateTime(row.getTimestamp("update_time"));
			return rs;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleSnatch
	 */
	public void add(RoleSnatch rs) {
		String sql = "insert into t_role_snatch(" + "role_id," + "state," + "snatched," + "create_time," + "update_time"
				+ ")values(?,?,?,now(),now())";
		execute(sql, rs.getRoleId(), rs.getState(), rs.getSnatched());
	}

	/**
	 * 更新
	 * 
	 * @param RoleSnatch
	 */
	public void update(RoleSnatch rs) {
		String sql = "update t_role_snatch set " + " update_time=now(), " + " war_free_time=?," + " snatched=?, "
				+ " snatch_fail_list=?" + " where role_id=?";

		executeUpdate(sql, rs.getWarFreeTime(), rs.getSnatched(), JSONUtil.toJson(rs.getSnatchFailList()),
				rs.getRoleId());
	}

	/**
	 * 更新状态
	 * 
	 * @param role_id
	 * @param state
	 */
	public void updateState(int role_id, int state) {
		String sql = "update t_role_snatch set " + " update_time=now(), " + " state=?" + " where role_id=?";
		executeUpdate(sql, state, role_id);
	}

	/**
	 * 查询
	 */
	public RoleSnatch query(int role_id) {
		return queryForObject("select * from t_role_snatch where role_id=?", HANDLER, role_id);
	}

	/**
	 * 查询状态
	 */
	public Integer queryState(int role_id) {
		return queryForInteger("select * from t_role_snatch where role_id=? FOR UPDATE", role_id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleSnatch> queryList() {
		return queryForList("select * from t_role_snatch", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int role_id) {
		execute("delete  from t_role_snatch where role_id=?", role_id);
	}
}