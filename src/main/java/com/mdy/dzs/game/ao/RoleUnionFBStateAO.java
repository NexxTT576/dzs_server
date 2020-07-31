package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.union.RoleUnionFBState;

public class RoleUnionFBStateAO extends BaseAO {

	/**
	 * 查询玩家副本状态
	 * 
	 * @param roleId
	 * @param unionId
	 * @return
	 */
	public RoleUnionFBState queryRoleUnionFBStateByRoleIdUId(int roleId, int unionId) {
		return roleUnionFBStateDAO().queryRoleFBStateByRoleIdUId(roleId, unionId);
	}

	/**
	 * 创建玩家副本信息
	 * 
	 * @param roleUnionFBState
	 */
	public void createRoleUnionFBState(RoleUnionFBState roleUnionFBState) {
		roleUnionFBStateDAO().createRoleUnionFBState(roleUnionFBState);
	}

	/**
	 * 每日更新玩家攻打副本次数
	 * 
	 * @param freeCount
	 * @param roleId
	 * @param unionId
	 */
	public void updateRoleUnionFBCount(int freeCount, int roleId, int unionId) {
		roleUnionFBStateDAO().updateRoleUnionFBCount(freeCount, roleId, unionId);
	}

	/**
	 * 根据帮派id 查询个人总伤害
	 * 
	 * @param unionId
	 */
	public List<RoleUnionFBState> queryRoleUnionFBStateByUnionId(int unionId) {
		return roleUnionFBStateDAO().queryRoleUnionFBStateByUnionId(unionId);
	}

	/**
	 * 清除个人帮派副本状态
	 * 
	 * @param roleId
	 * @param unionId
	 */
	public void clearRoleUnionFBState(int roleId, int unionId) {
		roleUnionFBStateDAO().clearRoleUnionFBState(roleId, unionId);
	}

	/**
	 * 添加帮派信息
	 * 
	 * @param roleId
	 * @param unionId
	 */
	public void updateRoleUnionFB(int roleId, int unionId) {
		RoleUnionFBState roleUnionFB = roleUnionFBStateDAO().queryRoleFBStateByRoleId(roleId);
		if (roleUnionFB != null) {
			roleUnionFBStateDAO().upateRoleUnionFBStateUId(roleId, unionId);
		}
	}
}
