package com.mdy.dzs.game.domain.activity.limitshop;

import java.io.Serializable;

import com.mdy.dzs.data.domain.activity.limitshop.LimitShopData;

public class LimitShopInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5600733559798333303L;
	public static final int ALL_LIMIT = 1;
	public static final int DAY_LIMIT = 2;
	private int id;
	private int num;
	
	public static LimitShopInfo ValueOf(LimitShopData limitShopData){
		LimitShopInfo lsi = new LimitShopInfo();
		lsi.id = limitShopData.getId();
		lsi.num = limitShopData.getSum1();
		return lsi;
	}
	public static LimitShopInfo ValueOfRole(int id,int count){
		LimitShopInfo lsi = new LimitShopInfo();
		lsi.id = id;
		lsi.num = count;
		return lsi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	

}
