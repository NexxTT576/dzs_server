package com.mdy.dzs.data.domain.activity.limitshop;

import java.io.Serializable;
import java.util.List;

public class LimitShopData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8703073651884329250L;
	private int id ;
	private int sale;
	private int type;
	private int itemid;
	private int num;
	private int sum1;
	private List<Integer> arr_sum2;
	private int vip;
	private int cointype;
	private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
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
	public int getCointype() {
		return cointype;
	}
	public void setCointype(int cointype) {
		this.cointype = cointype;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getSum1() {
		return sum1;
	}
	public void setSum1(int sum1) {
		this.sum1 = sum1;
	}
	public List<Integer> getArr_sum2() {
		return arr_sum2;
	}
	public void setArr_sum2(List<Integer> arr_sum2) {
		this.arr_sum2 = arr_sum2;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
}
