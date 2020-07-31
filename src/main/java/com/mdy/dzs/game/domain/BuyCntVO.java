package com.mdy.dzs.game.domain;

import java.io.Serializable;

/**
 * 购买次数vo
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年11月5日  下午10:05:55
 */
public class BuyCntVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**开启初始次数*/
	private int openCnt;
	/**剩余次数*/
	private int surplusCnt;
	/**已购买次数*/
	private int buyCnt;
	/**限制购买次数*/
	private int limit;
	/**购买次数所需花费道具*/
	private int itemId;
	/**购买次数所需花费道具数量*/
	private int num;
	/**购买次数所需花费金钱*/
	private int spend;
	/**人物金钱*/
	private int gold;
	
	public BuyCntVO(int surplusCnt,int buyCnt,int limit,
			int itemId,int num, int spend,int gold) {
		this.surplusCnt = surplusCnt;
		this.buyCnt = buyCnt;
		this.limit = limit;
		this.itemId = itemId;
		this.num = num;
		this.spend = spend;
		this.gold = gold;
	}
	
	
	public int getSurplusCnt() {
		return surplusCnt;
	}
	public void setSurplusCnt(int surplusCnt) {
		this.surplusCnt = surplusCnt;
	}
	public int getBuyCnt() {
		return buyCnt;
	}
	public void setBuyCnt(int buyCnt) {
		this.buyCnt = buyCnt;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSpend() {
		return spend;
	}
	public void setSpend(int spend) {
		this.spend = spend;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}


	public int getOpenCnt() {
		return openCnt;
	}


	public void setOpenCnt(int openCnt) {
		this.openCnt = openCnt;
	}

	
	
	
}
