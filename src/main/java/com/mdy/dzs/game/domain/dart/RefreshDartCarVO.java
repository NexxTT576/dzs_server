package com.mdy.dzs.game.domain.dart;

import java.io.Serializable;

public class RefreshDartCarVO implements Serializable{
	/*{	
		gold:当前金币
		refreshTimes:刷新次数		【0-免费 其他】
		quality：镖车品质			【1-4 ：1-绿，2-蓝，3-紫，4-金】
	}*/
	
	private static final long serialVersionUID = 1L;
	
	private int gold;
	private int refreshTimes;
	private int quality;
	
	public RefreshDartCarVO(int gold, int refreshTimes, int quality){
		this.gold 			= gold;
		this.refreshTimes 	= refreshTimes;
		this.quality 		= quality;
	}
	
	public int getRefreshTimes() {
		return refreshTimes;
	}
	public void setRefreshTimes(int refreshTimes) {
		this.refreshTimes = refreshTimes;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	
	
	
}
