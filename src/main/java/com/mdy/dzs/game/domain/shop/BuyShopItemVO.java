package com.mdy.dzs.game.domain.shop;

import java.io.Serializable;

public class BuyShopItemVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Integer price;
	private int hadBuy;
	private Integer cnt;
	private Integer max;
	
	public BuyShopItemVO() {
		// TODO 自动生成的构造函数存根
	}
	
	public BuyShopItemVO(int i,int price,int h,int cnt,int max) {
		this.id = i;
		this.price = price;
		this.hadBuy = h;
		this.cnt = cnt;
		this.max = max;
		if(max == 0){
			this.cnt = null;
			this.max = null;
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public int getHadBuy() {
		return hadBuy;
	}
	public void setHadBuy(int hadBuy) {
		this.hadBuy = hadBuy;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	
	


}
