package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

public class BossHistoryVO implements Serializable{
	/*
		state:	1/2/3	今日挑战状态 【1-未开启 2-进行中 3-结束】
		jobType:0/1/2/3 玩家职务	 【0:普通人员1:长老2:副帮主3:帮主】
		templeLevel:*	青龙堂等级
		curUnionMoney:*	当前帮派资金
		createBossCost:*开启挑战需要资金
		dragonLevel：*  青龙等级
	*/
	
	private static final long serialVersionUID = 1L;
	
	private int state;
	private int jobType;
	private int templeLevel;
	private int curUnionMoney;
	private int createBossCost;
	private int dragonLevel;
	
	public BossHistoryVO(int state, int jobType, int templeLevel, int curUnionMoney, int createBossCost, int dragonLevel){
		this.state 			= state;
		this.jobType 		= jobType;
		this.templeLevel 	= templeLevel;
		this.curUnionMoney  = curUnionMoney;
		this.createBossCost = createBossCost;
		this.setDragonLevel(dragonLevel);
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getJobType() {
		return jobType;
	}
	public void setJobType(int jobType) {
		this.jobType = jobType;
	}
	public int getTempleLevel() {
		return templeLevel;
	}
	public void setTempleLevel(int templeLevel) {
		this.templeLevel = templeLevel;
	}
	public int getCurUnionMoney() {
		return curUnionMoney;
	}
	public void setCurUnionMoney(int curUnionMoney) {
		this.curUnionMoney = curUnionMoney;
	}

	public int getCreateBossCost() {
		return createBossCost;
	}

	public void setCreateBossCost(int createBossCost) {
		this.createBossCost = createBossCost;
	}

	public int getDragonLevel() {
		return dragonLevel;
	}

	public void setDragonLevel(int dragonLevel) {
		this.dragonLevel = dragonLevel;
	}
	
	
}
