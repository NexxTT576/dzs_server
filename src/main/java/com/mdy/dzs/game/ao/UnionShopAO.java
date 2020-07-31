package com.mdy.dzs.game.ao;


import com.mdy.dzs.game.domain.union.UnionShop;



public class UnionShopAO  extends BaseAO{
	
	/**
	 * 添加帮派商店信息
	 */
	public void createShopInfo(UnionShop unionShop) {
		unionShopDAO().createShopInfo(unionShop);
	}
	/**
	 * 修改帮派商店信息
	 */
	public void updateShopInfo(UnionShop unionShop) {
		unionShopDAO().updateShopInfo(unionShop);
	}
	/**
	 * 查询玩家商店信息
	 */
	public UnionShop queryShopInfoByUnionId(int unionId){
		return unionShopDAO().queryShopInfoByUnionId(unionId);
	}
}
