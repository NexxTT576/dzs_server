package com.mdy.dzs.game.fight.domain.fightskill;

import java.io.Serializable;

public class FightSkillAngerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//side,目标方(1战斗发起方,2应战方)
	private int s;
	//pos,目标位置(1-6)
	private int p;
	//怒气值
	private int a;
	
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
}
