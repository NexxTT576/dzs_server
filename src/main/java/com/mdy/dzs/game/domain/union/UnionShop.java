package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.domain.union.UnionShop1;
import com.mdy.sharp.util.JSONUtil;

public class UnionShop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6123321987781208352L;
	private int id;
	// 商品信息
	private String goodsInfo;
	private Date refreshTime;
	// 帮派id
	private int unionId;

	public static UnionShop valueOf(int unionId, List<UnionShop1> plist, Date rTime) {
		UnionShop us = new UnionShop();
		us.unionId = unionId;
		us.goodsInfo = JSONUtil.toJson(plist);
		us.refreshTime = rTime;
		return us;
	}

	public static UnionShop valueOfShopInfo(int unionId, List<ShopInfo> plist, Date reTime) {
		UnionShop us = new UnionShop();
		us.unionId = unionId;
		us.goodsInfo = JSONUtil.toJson(plist);
		us.refreshTime = reTime;
		return us;
	}

	public int getId() {
		return id;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Date getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}

	public int getUnionId() {
		return unionId;
	}

	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}

	public void setId(int id) {
		this.id = id;
	}
}
