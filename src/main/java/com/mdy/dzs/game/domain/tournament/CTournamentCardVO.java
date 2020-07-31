package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;

public class CTournamentCardVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	private int cardId;
	/**卡牌类型id*/
	private int resId;
	/**等级*/
	private int level;
	/**阶数*/
	private int cls;
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
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
}
