package com.mdy.dzs.data.domain.union;

import java.io.Serializable;

public class UnionShop2 implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8768972179620554170L;
	private int id;
	//商品类型
	private int type;
	//商品id
	private int itemId;
	//商品数量
	private int num;
	//兑换类型（1-个人每日兑换，2-个人总兑换）
	private int exchangeType;
	//可兑换总次数
	private int exchange;
	//花费货币id
	private int money;
	//花费数量
	private int cost;
	//开启所需商店等级
	private int openLevel;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getExchange() {
		return exchange;
	}
	public void setExchange(int exchange) {
		this.exchange = exchange;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getExchangeType() {
		return exchangeType;
	}
	public void setExchangeType(int exchangeType) {
		this.exchangeType = exchangeType;
	}
	public int getOpenLevel() {
		return openLevel;
	}
	public void setOpenLevel(int openLevel) {
		this.openLevel = openLevel;
	}
	
}
