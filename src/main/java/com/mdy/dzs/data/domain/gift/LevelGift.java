package com.mdy.dzs.data.domain.gift;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;


/**
 * 等级礼包模型
 * @author 房曈
 *
 */
public class LevelGift implements Serializable,IGift{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**等级*/
	private int level;
	/**类型*/
	private List<Integer> arrType;
	/**道具组*/
	private List<Integer> arrItem;
	/**数量组*/
	private List<Integer> arrNum;
	
	private List<GiftItem> item;

	@JsonDeserialize
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	@JsonDeserialize
	public List<Integer> getArrType(){
		return this.arrType;
	}
	public void setArrType(List<Integer> arrType){
		this.arrType=arrType;
	}
	@JsonDeserialize
	public List<Integer> getArrItem(){
		return this.arrItem;
	}
	public void setArrItem(List<Integer> arrItem){
		this.arrItem=arrItem;
	}
	@JsonDeserialize
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