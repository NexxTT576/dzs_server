package com.mdy.dzs.game.domain.shop;

import java.io.Serializable;
import java.util.Date;


/**
 * 商城模型
 * @author 房曈
 *
 */
public class Shop implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**物品类型（1-card，2-item)*/
	private int type;
	/**物品id*/
	private int itemId;
	/**货币类型*/
	private int coinType;
	/**初始单价*/
	private int price;
	/**递增单价*/
	private int addPrice;
	/**开关（1-开启，0-关闭）*/
	private int open;
	/**服务器id（数组；）*/
	private int server;
	/**开启时间*/
	private Date begin;
	/**关闭时间*/
	private Date stop;
	/**最大递增次数*/
	private int maxN;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getItemId(){
		return this.itemId;
	}
	public void setItemId(int itemId){
		this.itemId=itemId;
	}
	public int getCoinType(){
		return this.coinType;
	}
	public void setCoinType(int coinType){
		this.coinType=coinType;
	}
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public int getAddPrice(){
		return this.addPrice;
	}
	public void setAddPrice(int addPrice){
		this.addPrice=addPrice;
	}
	public int getOpen(){
		return this.open;
	}
	public void setOpen(int open){
		this.open=open;
	}
	public int getServer(){
		return this.server;
	}
	public void setServer(int server){
		this.server=server;
	}
	public Date getBegin(){
		return this.begin;
	}
	public void setBegin(Date begin){
		this.begin=begin;
	}
	public Date getStop(){
		return this.stop;
	}
	public void setStop(Date stop){
		this.stop=stop;
	}
	public int getMaxN() {
		return maxN;
	}
	public void setMaxN(int maxN) {
		this.maxN = maxN;
	}
}