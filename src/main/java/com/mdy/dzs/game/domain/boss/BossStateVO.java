package com.mdy.dzs.game.domain.boss;

import java.io.Serializable;

public class BossStateVO implements Serializable{
	/**
	 * boss战状态
	 * {	name:*,      //boss名称
			level:*, 	 //boss等级
			life:*   	 //伤害血量
			lifeTotal:*   //当前总血量
			endTime:*	 //结束时间
		}
	 * */
	private static final long serialVersionUID = 1L;
	
	//boss名
	private String name;
	//boss等级
	private int level;
	//伤害血量
	private long life;
	//当前总血量
	private long lifeTotal;	
	//结束时间
	private int endTime;
	
	public BossStateVO(String name, int level, long life, long lifeTotal, int endTime){
		this.name 		= name;
		this.level 		= level;
		this.life 		= life;
		this.lifeTotal 	= lifeTotal;
		this.endTime 	= endTime;
	}
	
	public BossStateVO() {
		// TODO 自动生成的构造函数存根
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getLife() {
		return life;
	}
	public void setLife(long life) {
		this.life = life;
	}
	public long getLifeTotal() {
		return lifeTotal;
	}
	public void setLifeTotal(long lifeTotal) {
		this.lifeTotal = lifeTotal;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

}
