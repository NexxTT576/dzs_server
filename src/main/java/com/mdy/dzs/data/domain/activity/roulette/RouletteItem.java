package com.mdy.dzs.data.domain.activity.roulette;

import java.io.Serializable;
import java.util.List;


/**
 * 皇宫探宝圆盘物品模型
 * @author 白雪林
 *
 */
public class RouletteItem implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/***/
	private int itemDisplay;
	/***/
	private List<Integer> itemType;
	/***/
	private List<Integer> itemId;
	/***/
	private List<Integer> itemCnt;
	/**概率*/
	private List<Integer> itemProb;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getItemDisplay(){
		return this.itemDisplay;
	}
	public void setItemDisplay(int itemDisplay){
		this.itemDisplay=itemDisplay;
	}
	public List<Integer> getItemType(){
		return this.itemType;
	}
	public void setItemType(List<Integer> itemType){
		this.itemType=itemType;
	}
	public List<Integer> getItemId(){
		return this.itemId;
	}
	public void setItemId(List<Integer> itemId){
		this.itemId=itemId;
	}
	public List<Integer> getItemCnt(){
		return this.itemCnt;
	}
	public void setItemCnt(List<Integer> itemCnt){
		this.itemCnt=itemCnt;
	}
	public List<Integer> getItemProb() {
		return itemProb;
	}
	public void setItemProb(List<Integer> itemProb) {
		this.itemProb = itemProb;
	}
}