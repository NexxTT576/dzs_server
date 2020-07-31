package com.mdy.dzs.game.domain.vip;

import java.io.Serializable;

public class VipDataVO implements Serializable{
	/*
	 * vipData:{
			level:当前等级
			curExp:当前经验值累计
			curExpLimit：当前经验值上限
		}
	*/
	private static final long serialVersionUID = 1L;
	
	/**当前等级*/
	private int level;
	/**当前经验值*/
	private int curExp;
	/**当前经验上限*/
	private int curExpLimit;
	
	public VipDataVO(int level, int curExp, int curExpLimit) {
		this.level = level;
		this.curExp = curExp;
		this.curExpLimit = curExpLimit;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getCurExp() {
		return curExp;
	}

	public void setCurExp(int curExp) {
		this.curExp = curExp;
	}
	
	public int getCurExpLimit(){
		return curExpLimit;
	}
	
	public void setCurExpLimit(int curExpLimit){
		this.curExpLimit = curExpLimit;
	}
}
