package com.mdy.dzs.game.fight.domain.fightskill;

import java.io.Serializable;

public class FightSkillBuffVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//side,目标方(1战斗发起方,2应战方)
	private int s;
	//pos,目标位置(1-6)
	private int p;
	//buff_id
	private int b;
	//continue,持续回合数
	private int c = 1;
	
	private int replaceId;
	//是否被免疫了
	private int isMian;
	
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
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getReplaceId() {
		return replaceId;
	}
	public void setReplaceId(int replaceId) {
		this.replaceId = replaceId;
	}
	public int getIsMian() {
		return isMian;
	}
	public void setIsMian(int isMian) {
		this.isMian = isMian;
	}
	
	
}
