package com.mdy.dzs.game.domain.activity.happygame;

import java.io.Serializable;
import java.util.List;

public class ActivityHappyStatusVO implements Serializable{
	/**
	 * {	
			hasGet:[数字，数字...]									//已领取天数数组
			days:数字											//已登录天数
			activeStartTime：["YYYY-MM-DD HH:SS","YYYY-MM-DD HH:SS"]	//活动开始时间
		}
	 * */
	
	private static final long serialVersionUID = 1L;
	
	//已领取天数数组
	private List<Integer> hasGet;
	//已登录天数
	private int days;
	//活动时段
	private String[] activeTime;
	
	public ActivityHappyStatusVO( List<Integer> hasGet, int days, String[] activeTime) {
		this.hasGet 	 = hasGet;
		this.days 		 = days;
		this.activeTime  = activeTime;
	}
	
	public List<Integer> getHasGet() {
		return hasGet;
	}
	public void setHasGet(List<Integer> hasGet) {
		this.hasGet = hasGet;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String[] getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(String[] activeTime) {
		this.activeTime = activeTime;
	}	
}
