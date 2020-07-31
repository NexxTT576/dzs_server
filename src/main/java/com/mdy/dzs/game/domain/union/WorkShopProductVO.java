package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

public class WorkShopProductVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3031179518364787265L;
	private int surplusGold;
	private int freeCount;
	private int extCount;
	private int extCostGold;
	private int surplusTime;
	public int getSurplusGold() {
		return surplusGold;
	}
	public void setSurplusGold(int surplusGold) {
		this.surplusGold = surplusGold;
	}
	public int getFreeCount() {
		return freeCount;
	}
	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}
	public int getExtCount() {
		return extCount;
	}
	public void setExtCount(int extCount) {
		this.extCount = extCount;
	}
	public int getExtCostGold() {
		return extCostGold;
	}
	public void setExtCostGold(int extCostGold) {
		this.extCostGold = extCostGold;
	}
	public int getSurplusTime() {
		return surplusTime;
	}
	public void setSurplusTime(int surplusTime) {
		this.surplusTime = surplusTime;
	}
}
