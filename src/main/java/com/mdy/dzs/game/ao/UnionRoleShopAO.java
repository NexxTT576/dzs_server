package com.mdy.dzs.game.ao;

import com.mdy.dzs.game.domain.union.UnionRoleShop;

public class UnionRoleShopAO extends BaseAO {
	
	
	/**
	 * 添加帮派个人商店信息
	 */
	public void createUnionRoleShopInfo(UnionRoleShop unionRoleShop) {
		unionRoleShopDAO().createUnionRoleShopInfo(unionRoleShop);
	}
	/**
	 * 修改帮派个人商店信息
	 */
	public void updateUnionRoleShopInfo(UnionRoleShop unionRoleShop) {
		unionRoleShopDAO().updateUnionRoleShopInfo(unionRoleShop);
	}
	/**
	 * 查询玩家商店信息
	 */
	public UnionRoleShop queryShopInfoByRoleId(int roleId){
		return unionRoleShopDAO().queryShopInfoByRoleId(roleId);
	}

}
