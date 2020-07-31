package com.mdy.dzs.game.domain.activity.limitshop;

import java.io.Serializable;

public class ToolsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6554392562180138990L;
	private int id;
	//活动启动还是每日
	private int type;
	//总剩余量
	private int leftNum;
	//可购买数量
	private int canBuyNum;
	private int hasNum;
	public static ToolsVO valueOf(LimitShopInfo limitShopInfo,int canBuyNum,int hasCount){
		ToolsVO tv = new ToolsVO();
		tv.id = limitShopInfo.getId();
		tv.leftNum = limitShopInfo.getNum();
		tv.canBuyNum = canBuyNum;
		tv.hasNum = hasCount;
		return tv;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLeftNum() {
		return leftNum;
	}
	public void setLeftNum(int leftNum) {
		this.leftNum = leftNum;
	}
	public int getCanBuyNum() {
		return canBuyNum;
	}
	public void setCanBuyNum(int canBuyNum) {
		this.canBuyNum = canBuyNum;
	}
	public int getHasNum() {
		return hasNum;
	}
	public void setHasNum(int hasNum) {
		this.hasNum = hasNum;
	}
	
}
