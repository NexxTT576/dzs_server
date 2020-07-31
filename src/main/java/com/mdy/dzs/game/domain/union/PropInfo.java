package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

import com.mdy.dzs.data.domain.union.UnionShop2;

public class PropInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6741271921596550008L;
	private int id;
	//商品类型
	private int type;
	//商品id
	private int itemId;
	//商品数量
	private int num;
	//兑换类型（1-个人每日兑换，2-个人总兑换）
	private int exchangeType;
	//个人可兑换总次数
	private int exchange;
	//花费货币id
	private int money;
	//花费数量
	private int cost;
	//开启所需商店等级
	private int openLevel;
	//当前用户数量
	private int had;
	public static PropInfo valueOf(UnionShop2 unionShop,int had,int exchange){
		PropInfo propInfo = new PropInfo();
		propInfo.cost = unionShop.getCost();
		propInfo.exchange = exchange;
		propInfo.exchangeType = unionShop.getExchangeType();
		propInfo.had = had;
		propInfo.id = unionShop.getId();
		propInfo.itemId = unionShop.getItemId();
		propInfo.money = unionShop.getMoney();
		propInfo.num = unionShop.getNum();
		propInfo.openLevel = unionShop.getOpenLevel();
		propInfo.type = unionShop.getType();
		return propInfo;
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
	public int getHad() {
		return had;
	}
	public void setHad(int had) {
		this.had = had;
	}
}
