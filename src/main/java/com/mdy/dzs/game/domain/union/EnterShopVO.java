package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

public class EnterShopVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1248273921809769143L;
	private int jopType;
	private int unionCurrentMoney;
	private int lastContribute;
	private long surplusTime;
	private int shopLevel;
	private List<ShopInfo> shopListA;
	private List<PropInfo> shopListB;

	public int getShopLevel() {
		return shopLevel;
	}

	public void setShopLevel(int shopLevel) {
		this.shopLevel = shopLevel;
	}

	public List<ShopInfo> getShopListA() {
		return shopListA;
	}

	public void setShopListA(List<ShopInfo> shopListA) {
		this.shopListA = shopListA;
	}

	public List<PropInfo> getShopListB() {
		return shopListB;
	}

	public void setShopListB(List<PropInfo> shopListB) {
		this.shopListB = shopListB;
	}

	public int getJopType() {
		return jopType;
	}

	public void setJopType(int jopType) {
		this.jopType = jopType;
	}

	public int getUnionCurrentMoney() {
		return unionCurrentMoney;
	}

	public void setUnionCurrentMoney(int unionCurrentMoney) {
		this.unionCurrentMoney = unionCurrentMoney;
	}

	public int getLastContribute() {
		return lastContribute;
	}

	public void setLastContribute(int lastContribute) {
		this.lastContribute = lastContribute;
	}

	public long getSurplusTime() {
		return surplusTime;
	}

	public void setSurplusTime(long surplusTime) {
		this.surplusTime = surplusTime;
	}

}
