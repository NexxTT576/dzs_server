package com.mdy.dzs.data.domain.gift;

import java.io.Serializable;
import java.util.List;


/**
 * 在线礼包模型
 * @author 房曈
 *
 */
public class OnlineGift implements Serializable,IGift{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**时间*/
	private int time;
	/**类型(1-item,2-card)*/
	private List<Integer> arrType;
	/**物品组*/
	private List<Integer> arrItem;
	/**数量组*/
	private List<Integer> arrNum;
	
	private List<GiftItem> item;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getTime(){
		return this.time;
	}
	public void setTime(int time){
		this.time=time;
	}
	public List<Integer> getArrType(){
		return this.arrType;
	}
	public void setArrType(List<Integer> arrType){
		this.arrType=arrType;
	}
	public List<Integer> getArrItem(){
		return this.arrItem;
	}
	public void setArrItem(List<Integer> arrItem){
		this.arrItem=arrItem;
	}
	public List<Integer> getArrNum(){
		return this.arrNum;
	}
	public void setArrNum(List<Integer> arrNum){
		this.arrNum=arrNum;
	}
	public List<GiftItem> getItem() {
		return item;
	}
	public void setItem(List<GiftItem> item) {
		this.item = item;
	}
}