package com.mdy.dzs.game.domain.monthcard;

import java.io.Serializable;

public class MCardVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	{	isget：是否已领取			1-是 2-否
		days:月卡剩余天数
		cost:月卡花费
		getGold:立得元宝数量
		isCanBuy: 是否可购买月卡	1-是 2-否
	} 
	*/
	
	private int isget;
	private int days;
	private int cost;
	private int getGold;
	private int isCanBuy;
	
	public int getIsget() {
		return isget;
	}
	public void setIsget(int isget) {
		this.isget = isget;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getGetGold() {
		return getGold;
	}
	public void setGetGold(int getGold) {
		this.getGold = getGold;
	}
	public int getIsCanBuy() {
		return isCanBuy;
	}
	public void setIsCanBuy(int isCanBuy) {
		this.isCanBuy = isCanBuy;
	}
	
}
