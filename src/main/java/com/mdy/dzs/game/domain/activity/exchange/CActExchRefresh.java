package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;

public class CActExchRefresh implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/**兑换id*/
	private int exchangeId;
	/**下次刷新金币*/
	private int refGold;
	/**兑换公式*/
	private CActExchExp exchExp;
	/**免费次数*/
	private int freeNum;

	public CActExchExp getExchExp() {
		return exchExp;
	}

	public void setExchExp(CActExchExp exchExp) {
		this.exchExp = exchExp;
	}

	public int getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(int exchangeId) {
		this.exchangeId = exchangeId;
	}

	public int getRefGold() {
		return refGold;
	}

	public void setRefGold(int refGold) {
		this.refGold = refGold;
	}

	public int getFreeNum() {
		return freeNum;
	}

	public void setFreeNum(int freeNum) {
		this.freeNum = freeNum;
	}
}
