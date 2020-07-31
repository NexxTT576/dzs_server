package com.mdy.dzs.game.ao;

import com.mdy.dzs.game.domain.battle.RoleBattle;
import com.mdy.dzs.game.util.DateUtil;

/**
 * 副本
 * 
 * @author 房曈
 *
 */
public class RoleBattleAO extends BaseAO {
	//
	/**
	 * 添加
	 */
	public void add(int roleId) {
		roleBattleDAO().add(roleId);
	}

	/**
	 * 查询
	 */
	public RoleBattle queryByRoleId(int id) {
		RoleBattle rb = roleBattleDAO().query(id);
		if (!DateUtil.isToday(rb.getBattleDayRefresh())) {
			roleBattleDAO().resetBattle(rb);
		}
		return rb;
	}

	/**
	 * 更新
	 * 
	 * @param RoleBattle
	 */
	public void update(RoleBattle rb) {
		roleBattleDAO().updateBattle(rb);
	}

}