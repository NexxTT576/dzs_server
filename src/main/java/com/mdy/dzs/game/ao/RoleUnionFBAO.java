package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.union.RoleUnionFB;

public class RoleUnionFBAO extends BaseAO {

	/**
	 * 查询个人帮派副本信息
	 * 
	 * @param roleId
	 * @param unionId
	 * @return
	 */
	public List<RoleUnionFB> queryRoleUnionFBByRoleId(int roleId) {
		return roleUnionFBDAO().queryRoleUnionFBByRoleId(roleId);
	}

	/**
	 * 查询个人帮派副本的信息
	 * 
	 * @param roleId
	 * @param type
	 * @param FBId
	 * @return
	 */
	public RoleUnionFB queryRoleUnionFBByFBId(int roleId, int type, int FBId) {
		return roleUnionFBDAO().queryRoleUnionFBByFBId(roleId, type, FBId);
	}

	/**
	 * 修改帮派个人副本领奖状态
	 * 
	 * @param roleId
	 * @param type
	 * @param FBId
	 */
	public void updateUnionFBState(int roleId, int type, int FBId) {
		roleUnionFBDAO().updateUnionFBState(roleId, type, FBId);
	}

	/**
	 * 创建个人帮派副本信息
	 * 
	 * @param roleUnionFB
	 */
	public void createRoleUnionFB(RoleUnionFB roleUnionFB) {
		roleUnionFBDAO().createRoleUnionFB(roleUnionFB);
	}

	/**
	 * 清除副本信息
	 * 
	 * @param roleId
	 */
	public void clearRoleUnionFB(int roleId) {
		List<RoleUnionFB> roleUnionFBList = roleUnionFBDAO().queryRoleUnionFBByRoleId(roleId);
		for (RoleUnionFB roleUnionFB : roleUnionFBList) {
			roleUnionFBDAO().clearRoleUnionFB(roleId, roleUnionFB.getType(), roleUnionFB.getFbId());
		}

	}
}
