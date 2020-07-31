package com.mdy.dzs.game.domain.activity.monthsigngame;

import java.io.Serializable;
import java.util.List;

public class MonthSignStatusVO implements Serializable{
	/*{	
		month:数字						//当前月份值
		hasGet:[数字，数字...]				//已领取奖励数组 length为累积签到天数
		dayCnt:数字						//累积登录天数
		vip:   数字						//VIP等级
	}*/
	
	private static final long serialVersionUID = 1L;
	
	//当前月份
	private int month;
	//已领取奖励数组
	private List<Integer> hasGet;
	//累积登录天数
	private int dayCnt;
	//VIP等级
	private int vip;
	
	public MonthSignStatusVO(int month, List<Integer> hasGet, int dayCnt, int vip){
		this.month  = month;
		this.hasGet = hasGet;
		this.dayCnt = dayCnt;
		this.vip	= vip;
	}
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public List<Integer> getHasGet() {
		return hasGet;
	}
	public void setHasGet(List<Integer> hasGet) {
		this.hasGet = hasGet;
	}
	public int getDayCnt() {
		return dayCnt;
	}
	public void setDayCnt(int dayCnt) {
		this.dayCnt = dayCnt;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}
	
}
