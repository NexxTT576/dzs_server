package com.mdy.dzs.game.domain.boss;

import java.io.Serializable;

public class BattleResultVO implements Serializable{
	/**
	 * boss结果
			{	kill:'',    //击杀者名称,为空时为未击杀
				hurt:*, 	//总攻击值
				rank:*,  	//伤害排名
				endTime:*	//结束时间
				bossLife：*	//boss当前血量
				lifeTotal:*  //当前总血量
			}
	 * */
	
	private static final long serialVersionUID = 1L;
	//击杀者名称,为空时为未击杀
	private String kill;
	//总攻击值
	private int hurt;
	//伤害排名
	private int rank;
	//结束时间
	private int endTime;
	//boss当前血量
	private int bossLife;
	//当前总血量
	private int lifeTotal;
	
	public String getKill() {
		return kill;
	}
	public void setKill(String kill) {
		this.kill = kill;
	}
	public int getHurt() {
		return hurt;
	}
	public void setHurt(int hurt) {
		this.hurt = hurt;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getBossLife() {
		return bossLife;
	}
	public void setBossLife(int bossLife) {
		this.bossLife = bossLife;
	}
	public int getLifeTotal() {
		return lifeTotal;
	}
	public void setLifeTotal(int lifeTotal) {
		this.lifeTotal = lifeTotal;
	}
	
}
