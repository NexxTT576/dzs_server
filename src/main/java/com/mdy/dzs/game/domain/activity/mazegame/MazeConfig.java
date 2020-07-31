package com.mdy.dzs.game.domain.activity.mazegame;

import java.io.Serializable;

public class MazeConfig implements Serializable{

	private static final long serialVersionUID = 1L;

	/**每日免费次数*/
	private int dayFreeTimes;
	/**初始价格*/
	private int initPrice;
	/**递增价格*/
	private int addPrice;
	/**刷新库价格*/
	private int refreshPrice;
	/**元宝挖宝上限*/
	private int digLimit;
	/**道具库id*/
	private int libId;
	/**道具概率id*/
	private int prob1;
	/**道具概率id*/
	private int prob2;
	/**道具概率id*/
	private int prob3;
	/**道具概率id*/
	private int prob4;
	/**道具概率id*/
	private int prob5;
	/**道具概率id*/
	private int prob6;
	/**道具概率id*/
	private int prob7;
	/**道具概率id*/
	private int prob8;
	
	
	
	public int getDayFreeTimes() {
		return dayFreeTimes;
	}
	public void setDayFreeTimes(int dayFreeTimes) {
		this.dayFreeTimes = dayFreeTimes;
	}
	public int getInitPrice() {
		return initPrice;
	}
	public void setInitPrice(int initPrice) {
		this.initPrice = initPrice;
	}
	public int getAddPrice() {
		return addPrice;
	}
	public void setAddPrice(int addPrice) {
		this.addPrice = addPrice;
	}
	public int getRefreshPrice() {
		return refreshPrice;
	}
	public void setRefreshPrice(int refreshPrice) {
		this.refreshPrice = refreshPrice;
	}
	public int getDigLimit() {
		return digLimit;
	}
	public void setDigLimit(int digLimit) {
		this.digLimit = digLimit;
	}
	public int getProb1() {
		return prob1;
	}
	public void setProb1(int prob1) {
		this.prob1 = prob1;
	}
	public int getProb2() {
		return prob2;
	}
	public void setProb2(int prob2) {
		this.prob2 = prob2;
	}
	public int getProb3() {
		return prob3;
	}
	public void setProb3(int prob3) {
		this.prob3 = prob3;
	}
	public int getProb5() {
		return prob5;
	}
	public void setProb5(int prob5) {
		this.prob5 = prob5;
	}
	public int getProb4() {
		return prob4;
	}
	public void setProb4(int prob4) {
		this.prob4 = prob4;
	}
	public int getProb7() {
		return prob7;
	}
	public void setProb7(int prob7) {
		this.prob7 = prob7;
	}
	public int getProb6() {
		return prob6;
	}
	public void setProb6(int prob6) {
		this.prob6 = prob6;
	}
	public int getProb8() {
		return prob8;
	}
	public void setProb8(int prob8) {
		this.prob8 = prob8;
	}
	public int getLibId() {
		return libId;
	}
	public void setLibId(int libId) {
		this.libId = libId;
	}
	public int getProbId(int i){
		int arr[] = {0,prob1,prob2,prob3,prob4,prob5,prob6,prob7,prob8};
		return arr[i];
	}
}
