/**
 * 
 */
package com.mdy.dzs.game.domain.road;

import java.io.Serializable;

/**
 * 礼物
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月15日  下午8:09:57
 */
public class RoleRoadGift implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**itemId*/
	private int resId;
	/**数量*/
	private int num;
	/**金额*/
	private int price;
	
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
