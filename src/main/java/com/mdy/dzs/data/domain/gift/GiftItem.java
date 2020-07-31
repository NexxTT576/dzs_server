package com.mdy.dzs.data.domain.gift;

import java.io.Serializable;

/**
 * 奖励道具
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月17日  上午11:07:38
 */
public class GiftItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int type;
	private int id;
	private int num;
	
	public GiftItem() {
	}
	
	public GiftItem(int t,int id,int n) {
		this.type = t;
		this.id = id;
		this.num = n;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
	
}
