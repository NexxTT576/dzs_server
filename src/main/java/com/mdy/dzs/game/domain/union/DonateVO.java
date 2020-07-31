package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

public class DonateVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6670410087220540304L;
	private int surplusSliver;
	private int surplusGod;
	public int getSurplusSliver() {
		return surplusSliver;
	}
	public void setSurplusSliver(int surplusSliver) {
		this.surplusSliver = surplusSliver;
	}
	public int getSurplusGod() {
		return surplusGod;
	}
	public void setSurplusGod(int surplusGod) {
		this.surplusGod = surplusGod;
	}
	
}
