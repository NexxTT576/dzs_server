package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.RoleLineupAid.RoleLineupCardAid;

/**
 * 用户辅助阵型卡牌
 * 
 * @author zhou
 *
 */
public class RoleLineupCardAidAO extends BaseAO {
	//
	/**
	 * 查询
	 */
	public RoleLineupCardAid query(int id) {
		return roleLineupCardAidDAO().query(id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleLineupCardAid> queryList() {
		return roleLineupCardAidDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param RoleLineupCardAid
	 */
	public void add(RoleLineupCardAid rlca) {
		roleLineupCardAidDAO().add(rlca);
	}

	/**
	 * 更新
	 * 
	 * @param RoleLineupCardAid
	 */
	public void update(RoleLineupCardAid rlca) {
		roleLineupCardAidDAO().update(rlca);
	}
}