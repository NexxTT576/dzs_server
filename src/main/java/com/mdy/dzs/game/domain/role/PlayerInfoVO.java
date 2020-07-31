package com.mdy.dzs.game.domain.role;

import java.io.Serializable;

public class PlayerInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int level;
	private int cls;
	private int vip;
	private int gold;
	private int silver;
	private int attack;
	private int[] physVal;
	private int[] resisVal;
	private int[] exp;
	
	public PlayerInfoVO(Role role){
		level = role.level;
		cls = role.cls;
		vip = role.vip;
		gold = role.gold;
		silver = role.silver;
		attack = role.attack;
		int tmp[] = {role.physVal,role.propLimitAry.get(0)} ;
		physVal = tmp;
		int tmp1[] = {role.resisVal,role.propLimitAry.get(1)} ;
		resisVal = tmp1;
		int tmp2[] = {role.exp,role.propLimitAry.get(2)} ;
		exp = tmp2;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getSilver() {
		return silver;
	}
	public void setSilver(int silver) {
		this.silver = silver;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int[] getPhysVal() {
		return physVal;
	}

	public void setPhysVal(int[] physVal) {
		this.physVal = physVal;
	}

	public int[] getResisVal() {
		return resisVal;
	}

	public void setResisVal(int[] resisVal) {
		this.resisVal = resisVal;
	}

	public int[] getExp() {
		return exp;
	}

	public void setExp(int[] exp) {
		this.exp = exp;
	}
	
	
}
