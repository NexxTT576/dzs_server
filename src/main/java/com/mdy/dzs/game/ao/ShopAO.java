package com.mdy.dzs.game.ao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.DataAction;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.game.domain.shop.RoleShop;
import com.mdy.dzs.game.domain.shop.Shop;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.Container;

/**
 * 商城
 * 
 * @author 房曈
 *
 */
public class ShopAO extends BaseAO {
	//
	private Map<Integer, Vip> vipDatas;

	public ShopAO() {
	}

	@Override
	public void start() {
		DataAction dataAction = Container.get().createRemote(DataAction.class, DataApplication.CLUSTER_DATA_SYSTEM);
		vipDatas = new HashMap<Integer, Vip>();
		List<Vip> vips = dataAction.queryVipDatas();
		for (Vip v : vips) {
			if (v.getSystem() == 1) {
				vipDatas.put(v.getShopid(), v);
			}
		}
		super.start();
	}

	/**
	 * 获取商城道具相关vip数据
	 * 
	 * @param itemId
	 * @return
	 */
	public Vip getVipData(int itemId) {
		return vipDatas.containsKey(itemId) ? vipDatas.get(itemId) : null;
	}

	/**
	 * 查询
	 */
	public Shop query(int id) {
		return shopDAO().query(id);
	}

	/**
	 * 查询列表
	 */
	public List<Shop> queryOpenListByTime(Date time) {
		return shopDAO().queryOpenListByTime(time);
	}

	/**
	 * 查询单个
	 */
	public Shop queryOpenItemByTime(Date time, int itemId) {
		return shopDAO().queryOpenItemByTime(time, itemId);
	}

	/**
	 * 添加
	 * 
	 * @param Shop
	 */
	public void add(Shop s) {
		shopDAO().add(s);
	}

	/**
	 * 更新
	 * 
	 * @param Shop
	 */
	public void update(Shop s) {
		shopDAO().update(s);
	}

	/////////////////////////////////////////////////////////////////////////////////////
	public RoleShop queryRoleShopByRoleId(int roleId) {
		RoleShop rShop = roleShopDAO().query(roleId);
		if (!DateUtil.isToday(rShop.getShopDayRefresh())) {
			rShop.setComShopPurchased(new HashMap<String, Integer>()); // 重置当日已购买商店限购计数数组
			rShop.setShopDayRefresh(new Date());
			roleShopDAO().update(rShop);
		}
		return rShop;
	}

	public void updateRoleShop(RoleShop rs) {
		roleShopDAO().update(rs);
	}

	// 获取购买需要的花费
	public int getNeedCoin(Shop item, int purchaseData, int n) {
		int needCoin = 0;// purchaseData 已购买数
		if (purchaseData > item.getMaxN() - 1) {
			needCoin = (item.getPrice() + item.getMaxN() * item.getAddPrice()) * n;
		} else if (purchaseData + n < item.getMaxN() + 2) {
			int nowPrice = Math.min(item.getPrice() + purchaseData * item.getAddPrice(),
					item.getPrice() + item.getMaxN() * item.getAddPrice());
			needCoin = n * nowPrice + n * (n - 1) * item.getAddPrice() / 2;
		} else {
			needCoin = (item.getMaxN() - purchaseData + 1) * (item.getPrice() + purchaseData * item.getAddPrice())
					+ (item.getMaxN() - purchaseData + 1) * (item.getMaxN() - purchaseData) * item.getAddPrice() / 2
					+ (purchaseData + n - item.getMaxN() - 1) * (item.getPrice() + item.getMaxN() * item.getAddPrice());
		}

		return needCoin;
	}

}