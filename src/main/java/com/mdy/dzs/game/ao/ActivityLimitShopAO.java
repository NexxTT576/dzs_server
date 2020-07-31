package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.activity.limitshop.LimitShop;
import com.mdy.dzs.game.domain.activity.limitshop.LimitShopInfo;

public class ActivityLimitShopAO extends BaseAO {

	public LimitShop queryLimitShopInfo() {
		return activityLimitShopDAO().queryLimitShopInfo();
	}

	/**
	 * 更新商店
	 * 
	 * @param shopInfo
	 */
	public void updateLimitShop(List<LimitShopInfo> shopInfo) {

		activityLimitShopDAO().updateLimitShop(shopInfo);
	}

	/**
	 * 创建商店信息
	 */
	public void createLimitShop(List<LimitShopInfo> shopInfo) {
		activityLimitShopDAO().createLimitShop(shopInfo);
	}
}
