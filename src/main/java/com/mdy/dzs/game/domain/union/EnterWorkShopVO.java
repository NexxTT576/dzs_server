package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;

public class EnterWorkShopVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8444709813115928853L;

	
	private int currentUnionMoney;
	private int workShopLevel;
	private int gold;
	private int freeCount;
	private int isWork;
	private long surplusTime;
	private int overtimeflag;
	private int extGoldNum;
	private int workType;
	private int extNum;
	private List<WorkShopVO> typeList;
	private List<ProbItem> rewardList;
	public int getCurrentUnionMoney() {
		return currentUnionMoney;
	}
	public void setCurrentUnionMoney(int currentUnionMoney) {
		this.currentUnionMoney = currentUnionMoney;
	}
	public int getWorkShopLevel() {
		return workShopLevel;
	}
	public void setWorkShopLevel(int workShopLevel) {
		this.workShopLevel = workShopLevel;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getFreeCount() {
		return freeCount;
	}
	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}
	public int getIsWork() {
		return isWork;
	}
	public void setIsWork(int isWork) {
		this.isWork = isWork;
	}
	public long getSurplusTime() {
		return surplusTime;
	}
	public void setSurplusTime(long surplusTime) {
		this.surplusTime = surplusTime;
	}
	public int getWorkType() {
		return workType;
	}
	public void setWorkType(int workType) {
		this.workType = workType;
	}
	public int getExtNum() {
		return extNum;
	}
	public void setExtNum(int extNum) {
		this.extNum = extNum;
	}
	public List<WorkShopVO> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<WorkShopVO> typeList) {
		this.typeList = typeList;
	}
	public int getOvertimeflag() {
		return overtimeflag;
	}
	public void setOvertimeflag(int overtimeflag) {
		this.overtimeflag = overtimeflag;
	}
	public int getExtGoldNum() {
		return extGoldNum;
	}
	public void setExtGoldNum(int extGoldNum) {
		this.extGoldNum = extGoldNum;
	}
	public List<ProbItem> getRewardList() {
		return rewardList;
	}
	public void setRewardList(List<ProbItem> rewardList) {
		this.rewardList = rewardList;
	}
}
