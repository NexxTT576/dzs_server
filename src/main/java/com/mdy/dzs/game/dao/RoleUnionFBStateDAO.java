package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.union.RoleUnionFBState;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class RoleUnionFBStateDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<RoleUnionFBState> HANDLER = new ResultSetRowHandler<RoleUnionFBState>() {
		@Override
		public RoleUnionFBState handleRow(ResultSetRow row) throws Exception {
			RoleUnionFBState ruFB = new RoleUnionFBState();
			ruFB.setId(row.getInt("id"));
			ruFB.setCreateTime(row.getTimestamp("create_time"));
			ruFB.setRoleId(row.getInt("role_id"));
			ruFB.setUnionId(row.getInt("union_id"));
			ruFB.setUpdateTime(row.getTimestamp("update_time"));
			ruFB.setBugNum(row.getInt("buy_num"));
			ruFB.setTotalAttackNum(row.getInt("total_attack_num"));
			ruFB.setLeftAttackNum(row.getInt("left_attack_num"));
			ruFB.setTotalHurt(row.getInt("total_hurt"));
			ruFB.setRefreshTime(row.getTimestamp("refresh_time"));
			return ruFB;
		}
	};

	/**
	 * 查询玩家帮派副本状态
	 * 
	 * @param roleId
	 * @param unionId
	 * @return
	 */
	public RoleUnionFBState queryRoleFBStateByRoleIdUId(int roleId, int unionId) {

		return queryForObject("select * from t_role_union_fb_state  where role_id =? and union_id = ?", HANDLER, roleId,
				unionId);
	}

	/**
	 * 创建玩家帮派副本信息
	 * 
	 * @param roleUnionFBState
	 */
	public void createRoleUnionFBState(RoleUnionFBState roleUnionFBState) {
		String sql = "insert into t_role_union_fb_state (" + "create_time," + "role_id," + "union_id," + "buy_num,"
				+ "left_attack_num," + "total_hurt," + "refresh_time" + ")values(?,?,?,?,?,?,?)";
		execute(sql, roleUnionFBState.getCreateTime(), roleUnionFBState.getRoleId(), roleUnionFBState.getUnionId(),
				roleUnionFBState.getBugNum(), roleUnionFBState.getLeftAttackNum(), roleUnionFBState.getTotalHurt(),
				roleUnionFBState.getRefreshTime());
	}

	/**
	 * 更新每日帮派副本攻打次数
	 * 
	 * @param freeNum
	 * @param roleId
	 * @param unionId
	 */
	public void updateRoleUnionFBCount(int freeNum, int roleId, int unionId) {
		String sql = "update t_role_union_fb_state set " + " left_attack_num=? " + " where role_id=? and union_id =?";
		executeUpdate(sql, freeNum, roleId, unionId);
	}

	/**
	 * 根据帮派id查询帮派所有人副本状态
	 * 
	 * @param unionId
	 * @return
	 */
	public List<RoleUnionFBState> queryRoleUnionFBStateByUnionId(int unionId) {
		return queryForList("select * from t_role_union_fb_state  where union_id = ?", HANDLER, unionId);
	}

	/**
	 * 清除个人帮派副本状态
	 * 
	 * @param roleId
	 * @param unionId
	 */
	public void clearRoleUnionFBState(int roleId, int unionId) {
		String sql = "update t_role_union_fb_state set " + " union_id =0 and total_attack_num =0 and total_hurt =0"
				+ " where role_id=? and union_id =?";
		executeUpdate(sql, roleId, unionId);
	}

	/**
	 * 查询副本状态信息
	 * 
	 * @param roleId
	 * @return
	 */
	public RoleUnionFBState queryRoleFBStateByRoleId(int roleId) {
		return queryForObject("select * from t_role_union_fb_state  where role_id = ?", HANDLER, roleId);
	}

	/**
	 * 修改所属帮派信息
	 * 
	 * @param roleId
	 * @param unionId
	 */
	public void upateRoleUnionFBStateUId(int roleId, int unionId) {
		String sql = "update t_role_union_fb_state set " + " union_id =? " + " where role_id =?";
		executeUpdate(sql, unionId, roleId);
	}
}
