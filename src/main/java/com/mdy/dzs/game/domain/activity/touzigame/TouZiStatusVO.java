package com.mdy.dzs.game.domain.activity.touzigame;

import java.io.Serializable;
import java.util.List;

public class TouZiStatusVO implements Serializable{
	/*{	
		hasBuy: 1:已购买、0：未购买 		//是否已购买
		vip:数字						//当前vip值
		hasGet:[数字，数字...]			//已领取奖励数组
		lv:数字							//当前等级
	}*/
	
	private static final long serialVersionUID = 1L;

	//是否已购买
	private int hasBuy;
	//当前vip值
	private int vip;	
	//已领取奖励数组
	private List<Integer> hasGet;
	//当前等级
	private int lv;
	
	public TouZiStatusVO(int hasBuy, int vip, List<Integer> hasGet, int lv){
		this.hasBuy = hasBuy;
		this.vip 	= vip;
		this.hasGet = hasGet;
		this.lv 	= lv;
	}	
	
	public int getHasBuy() {
		return hasBuy;
	}
	public void setHasBuy(int hasBuy) {
		this.hasBuy = hasBuy;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public List<Integer> getHasGet() {
		return hasGet;
	}
	public void setHasGet(List<Integer> hasGet) {
		this.hasGet = hasGet;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	
}
