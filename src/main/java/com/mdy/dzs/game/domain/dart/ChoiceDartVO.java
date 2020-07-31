package com.mdy.dzs.game.domain.dart;

import java.io.Serializable;

public class ChoiceDartVO implements Serializable{
	/*{	
		detainTimes：今天可押镖次数
		robTimes：今天可劫镖次数
		refreshCost:刷新花费
		refreshTimes:刷新次数		【0-免费 其他】
		lastQuality：上次刷新品质	【1-4】
	}*/
	
	private static final long serialVersionUID = 1L;
	
	private int detainTimes;
	private int robTimes;
	private int refreshCost;
	private int refreshTimes;
	private int lastQuality;
	
	public ChoiceDartVO(int detainTimes,int robTimes,int refreshCost,int refreshTimes,int lastQuality){
		this.detainTimes = detainTimes;
		this.robTimes = robTimes;
		this.refreshCost = refreshCost;
		this.refreshTimes = refreshTimes;
		this.lastQuality = lastQuality;
	}
	
	public int getDetainTimes() {
		return detainTimes;
	}
	public void setDetainTimes(int detainTimes) {
		this.detainTimes = detainTimes;
	}
	public int getRobTimes() {
		return robTimes;
	}
	public void setRobTimes(int robTimes) {
		this.robTimes = robTimes;
	}
	public int getRefreshCost() {
		return refreshCost;
	}
	public void setRefreshCost(int refreshCost) {
		this.refreshCost = refreshCost;
	}
	public int getRefreshTimes() {
		return refreshTimes;
	}
	public void setRefreshTimes(int refreshTimes) {
		this.refreshTimes = refreshTimes;
	}
	public int getLastQuality() {
		return lastQuality;
	}
	public void setLastQuality(int lastQuality) {
		this.lastQuality = lastQuality;
	}
	
	
	
}
