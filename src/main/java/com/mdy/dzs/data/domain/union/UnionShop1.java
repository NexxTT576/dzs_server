package com.mdy.dzs.data.domain.union;

import java.io.Serializable;

public class UnionShop1  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6050326168225709882L;
	private int id;
	//商品类型
	private int type;
	//商品id
	private int itemId;
	//商品数量
	private int num;
	//帮派可兑换总次数
	private int exchange;
	//花费货币id
	private int money;
	//花费数量
	private int cost;
	//开启所需商店等级
	private int openLevel;
	//随机权重
	private int weight;
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getOpenLevel() {
		return openLevel;
	}
	public void setOpenLevel(int openLevel) {
		this.openLevel = openLevel;
	}
	
}
