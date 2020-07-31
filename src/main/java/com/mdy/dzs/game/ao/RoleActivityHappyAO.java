package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.activity.happygame.RoleActivityHappy;

/**
 * 累积登录礼包
 * 
 * @author 白雪林
 *
 */
public class RoleActivityHappyAO extends BaseAO {
	// 查询添加
	public RoleActivityHappy queryAdd(int roleId) {
		RoleActivityHappy player = roleActivityHappyDAO().query(roleId);
		if (player == null) {
			roleActivityHappyDAO().add(roleId);
			roleActivityHappyDAO().updateLoginCnt(roleId);
			player = roleActivityHappyDAO().query(roleId);
		}
		return player;
	}

	/**
	 * 查询
	 */
	public RoleActivityHappy query(int id) {
		return roleActivityHappyDAO().query(id);
	}

	/**
	 * 添加
	 * 
	 * @param RoleActivityHappy
	 */
	public void add(int roleId) {
		roleActivityHappyDAO().add(roleId);
	}

	public void updateGetList(List<Integer> getList, int roleId) {
		roleActivityHappyDAO().updateGetList(getList, roleId);
	}

	// 更新登录累积天数
	public void updateLoginCnt(int roleId) {
		RoleActivityHappy player = roleActivityHappyDAO().query(roleId);
		if (player == null) {
			roleActivityHappyDAO().add(roleId);
			player = roleActivityHappyDAO().query(roleId);
		}
		roleActivityHappyDAO().updateLoginCnt(roleId);
	}

}