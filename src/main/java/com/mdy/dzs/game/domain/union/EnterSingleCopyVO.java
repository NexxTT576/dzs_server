package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

public class EnterSingleCopyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7724634233253503208L;
	private int attackNum;
	private int leftHp;
	private int allDamage;
	private int isDead;
	private List<UnionFBDynamicVO> UFBDList;
	public int getAttackNum() {
		return attackNum;
	}
	public void setAttackNum(int attackNum) {
		this.attackNum = attackNum;
	}
	public int getLeftHp() {
		return leftHp;
	}
	public void setLeftHp(int leftHp) {
		this.leftHp = leftHp;
	}
	public int getAllDamage() {
		return allDamage;
	}
	public void setAllDamage(int allDamage) {
		this.allDamage = allDamage;
	}
	public int getIsDead() {
		return isDead;
	}
	public void setIsDead(int isDead) {
		this.isDead = isDead;
	}
	public List<UnionFBDynamicVO> getUFBDList() {
		return UFBDList;
	}
	public void setUFBDList(List<UnionFBDynamicVO> uFBDList) {
		UFBDList = uFBDList;
	}
}
