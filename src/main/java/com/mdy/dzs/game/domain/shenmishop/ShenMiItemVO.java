package com.mdy.dzs.game.domain.shenmishop;

import java.io.Serializable;

public class ShenMiItemVO implements Serializable{

	/*
		id:		0,				//编号
		itemId:	0,				//物品id
		upLimit:0,				//兑换上限
		type:	0,				//物品类型
		num:	0,				//一次兑换获得数量
		money:	0,				//货币类型
		price:	0,				//价格
	*/
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	/**编号*/
	private int id;
	/**物品id*/
	private int itemId;
	/**兑换上限*/
	private int upLimit;
	/**物品类型*/
	private int type;
	/**一次兑换数量*/
	private int num;
	/**货币类型*/
	private int money;
	/**价格*/
	private int price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getUpLimit() {
		return upLimit;
	}
	public void setUpLimit(int upLimit) {
		this.upLimit = upLimit;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
