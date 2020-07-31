package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mdy.sharp.util.JSONUtil;

public class UnionRoleShop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1400405886332269037L;
	private int id;
	// 商品信息
	private String goodsInfo;
	private Date refreshTime;
	// 玩家id
	private int roleId;

	public static UnionRoleShop valueOf(int roleId, List<UnionRoleShopInfo> plist, Date rTime) {
		UnionRoleShop us = new UnionRoleShop();
		us.roleId = roleId;
		us.goodsInfo = JSONUtil.toJson(plist);
		us.refreshTime = rTime;
		return us;
	}

	public static UnionRoleShop valueOfPropInfo(int roleId, List<UnionRoleShopInfo> plist, Date rTime) {
		UnionRoleShop us = new UnionRoleShop();
		us.roleId = roleId;
		us.goodsInfo = JSONUtil.toJson(plist);
		us.refreshTime = rTime;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setId(int id) {
		this.id = id;
	}
}
