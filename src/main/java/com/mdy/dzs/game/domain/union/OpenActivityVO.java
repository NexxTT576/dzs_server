package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

public class OpenActivityVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -960676695149993040L;
	private int unionCurrentMoney;
	
	private long surplusTime;

	public int getUnionCurrentMoney() {
		return unionCurrentMoney;
	}

	public void setUnionCurrentMoney(int unionCurrentMoney) {
		this.unionCurrentMoney = unionCurrentMoney;
	}

	public long getSurplusTime() {
		return surplusTime;
	}

	public void setSurplusTime(long surplusTime) {
		this.surplusTime = surplusTime;
	}
	
}
