package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;

public class CExchangeListVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/**物品id*/
	private int itemId;
	/**剩余次数*/
	private int number;
	/**当前数量*/
	private int had;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getHad() {
		return had;
	}
	public void setHad(int had) {
		this.had = had;
	}
}
