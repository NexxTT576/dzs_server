package com.mdy.dzs.game.ao;

import com.mdy.dzs.data.domain.charge.ChargeOrder;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleStatistics;

/**
 * 玩家统计
 * 
 * @author 房曈
 *
 */
public class RoleStatisticsAO extends BaseAO {
	//

	/**
	 * 添加
	 * 
	 * @param RoleStatistics
	 */
	public void add(Role role) {
		RoleStatistics rs = new RoleStatistics();
		rs.setRoleId(role.getId());
		rs.setAccount(role.getAccount());
		roleStatisticsDAO().add(rs);
	}

	/**
	 * 更新充值信息
	 * 
	 * @param RoleStatistics
	 */
	public void updateChargeInfo(Role role, ChargeOrder co) {
		int id = role.getId();
		float price = co.getPayPrice();
		roleStatisticsDAO().updateChargeInfo(id, price);
	}

	public RoleStatistics query(int roleid) {
		return roleStatisticsDAO().queryById(roleid);
	}
}