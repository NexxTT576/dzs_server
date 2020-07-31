package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

public class CheckTimeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7989246355459898612L;
	private int isOver;
	private long leftTime;
	public int getIsOver() {
		return isOver;
	}
	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}
	public long getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(long leftTime) {
		this.leftTime = leftTime;
	}
	
}
