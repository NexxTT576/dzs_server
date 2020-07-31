package com.mdy.dzs.data.domain.gift;

import java.io.Serializable;
import java.util.List;


/**
 * 首充，月卡赠送的物品模型
 * @author 白雪林
 *
 */
public class YueKaShouChongGift implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**类型 1-首充 2-礼包*/
	private int index;
	/**赠送道具类型数组*/
	private List<Integer> arrType;
	/**赠送道具id数组*/
	private List<Integer> arrItem;
	/**赠送道具数量数组*/
	private List<Integer> arrNum;

	public int getIndex(){
		return this.index;
	}
	public void setIndex(int index){
		this.index=index;
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
}