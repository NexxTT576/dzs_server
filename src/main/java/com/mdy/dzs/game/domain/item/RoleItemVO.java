package com.mdy.dzs.game.domain.item;

import java.io.Serializable;

/**
 * itemvo
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月16日  上午10:42:14
 */
public class RoleItemVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int itemId;
	private int itemCnt;
	
	public RoleItemVO(int id,int cnt){
		itemId = id;
		itemCnt = cnt;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemCnt() {
		return itemCnt;
	}
	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}


	

	
}
