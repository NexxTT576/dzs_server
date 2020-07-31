package com.mdy.dzs.game.domain.vip;

import java.io.Serializable;

public class YueDataVO implements Serializable {
	/*yueData:{
		cost:月卡售价
		goldget:立得金币
		type:消费类型
		days:月卡剩余天数
		}
	*/
	
	/**index*/
	private int cost;
	/**充值次数*/
	private int goldget;
	/**消费类型*/
	private int type;
	/**可否购买*/
	private int isCanBuy;
	/**月卡剩余天数*/
	private int days;
	
	public YueDataVO(int cost, int goldget, int type, int isCanBuy, int days) {
		this.cost 	 = cost;
		this.goldget = goldget;
		this.setType(type);
		this.setDays(days);
		this.setIsCanBuy(isCanBuy);
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getGoldget() {
		return goldget;
	}

	public void setGoldget(int goldget) {
		this.goldget = goldget;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsCanBuy() {
		return isCanBuy;
	}

	public void setIsCanBuy(int isCanBuy) {
		this.isCanBuy = isCanBuy;
	}
	
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
}
