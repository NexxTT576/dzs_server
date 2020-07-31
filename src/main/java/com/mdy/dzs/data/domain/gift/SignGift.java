package com.mdy.dzs.data.domain.gift;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;


/**
 * 签到礼包模型
 * @author 房曈
 *
 */
public class SignGift implements Serializable,IGift{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**类型*/
	private List<Integer> arrType;
	/**物品*/
	private List<Integer> arrItem;
	/**数量*/
	private List<Integer> arrNum;

	private List<GiftItem> item;
	@JsonProperty(value = "day")
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
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