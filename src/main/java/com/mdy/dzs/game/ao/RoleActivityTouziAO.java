package com.mdy.dzs.game.ao;

import com.mdy.dzs.game.domain.activity.touzigame.RoleActivityTouzi;

import java.util.List;

/**
 * 投资计划
 * 
 * @author 白雪林
 *
 */
public class RoleActivityTouziAO extends BaseAO {
	/**
	 * 查询并添加
	 */
	public RoleActivityTouzi queryAdd(int roleid) {
		RoleActivityTouzi rs = roleActivityTouziDAO().query(roleid);
		if (rs == null) {
			roleActivityTouziDAO().add(roleid);
			rs = roleActivityTouziDAO().query(roleid);
		}
		return rs;
	}

	/**
	 * 查询
	 */
	public RoleActivityTouzi query(int roleid) {
		return roleActivityTouziDAO().query(roleid);
	}

	/**
	 * 添加
	 */
	public void add(int roleid) {
		roleActivityTouziDAO().add(roleid);
	}

	// 置为已购买
	public void setHasBuy(int roleid) {
		roleActivityTouziDAO().setHasBuy(roleid);
	}

	// 更新已领取列表
	public void updateGetList(List<Integer> getList, int roleid) {
		roleActivityTouziDAO().updateGetList(getList, roleid);
	}

}