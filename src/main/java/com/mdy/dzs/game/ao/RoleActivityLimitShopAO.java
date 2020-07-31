package com.mdy.dzs.game.ao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.activity.limitshop.LimitShopInfo;
import com.mdy.dzs.game.domain.activity.limitshop.RoleLimitShop;

public class RoleActivityLimitShopAO extends BaseAO {

	/**
	 * 查询单个用户的兑换记录
	 * 
	 * @param roleId
	 * @return
	 */
	public RoleLimitShop queryShopInfoByRoleId(int roleId) {
		return roleActivityLimitShopDAO().queryShopInfoByRoleId(roleId);
	}

	public void createRoleActLimitShop(int roleId, List<LimitShopInfo> shopInfo) {
		roleActivityLimitShopDAO().createRoleActLimitShop(roleId, shopInfo);
	}

	public void updateRoleLShop(int roleId, List<LimitShopInfo> shopInfo, Date rTime) {
		roleActivityLimitShopDAO().updateRoleLShop(roleId, shopInfo, rTime);
	}
}
