package com.mdy.dzs.game.domain.equip;

import java.io.Serializable;

public class EquipLevelUPItemVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int lv;
	private int coin;

	public EquipLevelUPItemVO() {
	}
	public EquipLevelUPItemVO(int lv,int coin) {
		this.lv = lv;
		this.coin =coin;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	
	
}
