package com.mdy.dzs.game.domain.vip;

import java.io.Serializable;

public class VipListVO implements Serializable {
	/**
	 * list:[{	index:编号
				cnt:充值次数
				type:消费类型
				coinnum:售价
			}]
	 */
	private static final long serialVersionUID = 1L;
	
	/**index*/
	private int index;
	/**充值次数*/
	private int cnt;
	/**消费类型*/
	private int type;
	/**售价*/
	private int coinnum;
	
	public VipListVO(int index, int cnt, int type, int coinnum) {
		this.index = index;
		this.cnt = cnt;
		this.setType(type);
		this.setCoinnum(coinnum);
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCoinnum() {
		return coinnum;
	}

	public void setCoinnum(int coinnum) {
		this.coinnum = coinnum;
	}
}
