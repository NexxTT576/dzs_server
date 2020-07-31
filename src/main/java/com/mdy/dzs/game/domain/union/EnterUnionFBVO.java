package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

public class EnterUnionFBVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3218944647818576270L;

	private int copyLevel;
	private int jopType;
	private int currentUnionMoney;
	private int type;
	private int leftCount;
	private int canBuyCount;
	private int vipLevel;
	private List<FBList> fbList;
	public int getCopyLevel() {
		return copyLevel;
	}
	public void setCopyLevel(int copyLevel) {
		this.copyLevel = copyLevel;
	}
	public int getJopType() {
		return jopType;
	}
	public void setJopType(int jopType) {
		this.jopType = jopType;
	}
	public int getCurrentUnionMoney() {
		return currentUnionMoney;
	}
	public void setCurrentUnionMoney(int currentUnionMoney) {
		this.currentUnionMoney = currentUnionMoney;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLeftCount() {
		return leftCount;
	}
	public void setLeftCount(int leftCount) {
		this.leftCount = leftCount;
	}
	public int getCanBuyCount() {
		return canBuyCount;
	}
	public void setCanBuyCount(int canBuyCount) {
		this.canBuyCount = canBuyCount;
	}
	public int getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}
	public List<FBList> getFbList() {
		return fbList;
	}
	public void setFbList(List<FBList> fbList) {
		this.fbList = fbList;
	}
}
