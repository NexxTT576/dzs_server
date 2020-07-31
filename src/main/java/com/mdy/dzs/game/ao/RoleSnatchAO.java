package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.snatch.RoleSnatch;

/**
 * 夺宝
 * 
 * @author zhou
 *
 */
public class RoleSnatchAO extends BaseAO {
	public static final int FINGHTING = 0;
	public static final int END_FIGHT = 1;

	//
	/**
	 * 查询
	 */
	public RoleSnatch query(int roleId) {
		RoleSnatch rs = roleSnatchDAO().query(roleId);
		if (rs == null) {
			rs = new RoleSnatch();
			rs.setRoleId(roleId);
			rs.setState(RoleSnatchAO.END_FIGHT);
			add(rs);
		}
		return rs;
	}

	/**
	 * 查询状态
	 */
	public Integer queryState(int role_id) {
		return roleSnatchDAO().queryState(role_id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleSnatch> queryList() {
		return roleSnatchDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param RoleSnatch
	 */
	public void add(RoleSnatch rs) {
		roleSnatchDAO().add(rs);
	}

	/**
	 * 更新
	 * 
	 * @param RoleSnatch
	 */
	public void update(RoleSnatch rs) {
		roleSnatchDAO().update(rs);
	}

	public void updateState(int role_id, int state) {
		roleSnatchDAO().updateState(role_id, state);
	}
}