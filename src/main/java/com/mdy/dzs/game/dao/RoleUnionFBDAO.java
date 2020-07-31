package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.union.RoleUnionFB;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class RoleUnionFBDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<RoleUnionFB> HANDLER = new ResultSetRowHandler<RoleUnionFB>() {
		@Override
		public RoleUnionFB handleRow(ResultSetRow row) throws Exception {
			RoleUnionFB ruFB = new RoleUnionFB();
			ruFB.setId(row.getInt("id"));
			ruFB.setAttackHp(row.getInt("attack_hp"));
			ruFB.setAttackNum(row.getInt("attack_num"));
			ruFB.setCreateTime(row.getTimestamp("create_time"));
			ruFB.setFbId(row.getInt("fb_id"));
			ruFB.setRewardState(row.getInt("reward_state"));
			ruFB.setRoleId(row.getInt("role_id"));
			ruFB.setType(row.getInt("type"));
			ruFB.setUpdateTime(row.getTimestamp("update_time"));
			return ruFB;
		}
	};

	/**
	 * 查询玩家帮派副本列表
	 * 
	 * @param roleId
	 * @param unionId
	 * @return
	 */
	public List<RoleUnionFB> queryRoleUnionFBByRoleId(int roleId) {
		return queryForList("select * from t_role_union_fb where role_id=? ", HANDLER, roleId);
	}

	/**
	 * 根据fBid 查询玩家单个副本
	 * 
	 * @param roleId
	 * @param type
	 * @param FBId
	 * @return
	 */
	public RoleUnionFB queryRoleUnionFBByFBId(int roleId, int type, int FBId) {
		return queryForObject("select * from t_role_union_fb where role_id=? and type =? and fb_id =? ", HANDLER,
				roleId, type, FBId);
	}

	/**
	 * 修改帮派副本领奖状态
	 * 
	 * @param roleId
	 * @param type
	 * @param FBId
	 */
	public void updateUnionFBState(int roleId, int type, int FBId) {
		String sql = "update t_role_union_fb set " + " reward_state=1 and update_time =now()"
				+ " where role_id=? and type =? and fb_id =?";
		executeUpdate(sql, roleId, type, FBId);
	}

	/**
	 * 创建玩家副本信息
	 * 
	 * @param roleUnionFB
	 */
	public void createRoleUnionFB(RoleUnionFB roleUnionFB) {
		String sql = "insert into t_role_union_fb (" + "attack_hp," + "attack_num," + "create_time," + "fb_id,"
				+ "reward_state," + "role_id," + "type," + "update_time" + ")values(?,?,?,?,?,?,?,?,?)";
		execute(sql, roleUnionFB.getAttackHp(), roleUnionFB.getAttackNum(), roleUnionFB.getCreateTime(),
				roleUnionFB.getFbId(), roleUnionFB.getRewardState(), roleUnionFB.getRoleId(), roleUnionFB.getType(),
				roleUnionFB.getUpdateTime());
	}

	/**
	 * 清除副本信息
	 * 
	 * @param roleId
	 * @param unionId
	 * @param type
	 * @param FBId
	 */
	public void clearRoleUnionFB(int roleId, int type, int FBId) {
		String sql = "update t_role_union_fb set " + " attack_hp =0 and attack_num =0  and update_time =now()"
				+ " where role_id=? and type =? and fb_id =?";
		executeUpdate(sql, roleId, type, FBId);
	}
}
