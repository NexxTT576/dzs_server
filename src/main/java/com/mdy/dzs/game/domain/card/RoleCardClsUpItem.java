package com.mdy.dzs.game.domain.card;

import java.io.Serializable;

/**
 * 升阶所需道具
 * {'itemId':itemId ,'itemType':itemData.type ,'numNeed':numNeed}
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月21日  上午11:36:56
 */
public class RoleCardClsUpItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int itemId;
	private int itemType;
	private int numNeed;
	
	public RoleCardClsUpItem() {
		// TODO 自动生成的构造函数存根
	}
	
	public RoleCardClsUpItem(int id,int type,int need) {
		itemId = id;
		itemType = type;
		numNeed = need;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public int getNumNeed() {
		return numNeed;
	}
	public void setNumNeed(int numNeed) {
		this.numNeed = numNeed;
	}
	
	
}
