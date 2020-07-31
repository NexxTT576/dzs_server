package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

import com.mdy.dzs.data.domain.union.UnionShop1;

public class ShopInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8293793440695823219L;
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
	//是否已购买
	private int hasBuy;
	//当前拥有数量
	private int had;
	public static ShopInfo valeOf(UnionShop1 unionShop,int hasBuy,int had){
		ShopInfo shopInfo = new ShopInfo();
		shopInfo.cost = unionShop.getCost();
		shopInfo.exchange = unionShop.getExchange();
		shopInfo.hasBuy = hasBuy;
		shopInfo.id = unionShop.getId();
		shopInfo.itemId = unionShop.getItemId();
		shopInfo.money = unionShop.getMoney();
		shopInfo.num = unionShop.getNum();
		shopInfo.openLevel = unionShop.getOpenLevel();
		shopInfo.type = unionShop.getType();
		shopInfo.had = had;
		return shopInfo;
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
	public int getHasBuy() {
		return hasBuy;
	}
	public void setHasBuy(int hasBuy) {
		this.hasBuy = hasBuy;
	}
	public int getHad() {
		return had;
	}
	public void setHad(int had) {
		this.had = had;
	}
}
