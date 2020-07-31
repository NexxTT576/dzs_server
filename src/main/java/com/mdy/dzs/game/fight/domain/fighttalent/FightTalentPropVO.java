package com.mdy.dzs.game.fight.domain.fighttalent;

import java.io.Serializable;

public class FightTalentPropVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int s;
	private int p;
	private int idx;
	private int val;
	/**生命值*/
	private int l;
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
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}

	
}
