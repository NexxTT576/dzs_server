package com.mdy.dzs.game.domain.activity.guessgame;

import java.io.Serializable;

public class ActivityGuessBuyVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int gold;
	private int spend;
	
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getSpend() {
		return spend;
	}
	public void setSpend(int spend) {
		this.spend = spend;
	}
	
	
	
}
