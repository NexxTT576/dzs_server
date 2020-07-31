/**
 * 
 */
package com.mdy.dzs.game.domain.arena;

import java.io.Serializable;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月16日  下午9:06:04
 */
public class RoleArenaShopItemVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**编号*/
	private int id;
	/**兑换上限*/
	private int num1;
	/**现有数量*/
	private int had;
	
	public RoleArenaShopItemVO(int id2, int num12, int had2) {
		this.id = id2;
		this.num1 = num12;
		this.had = had2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getHad() {
		return had;
	}
	public void setHad(int had) {
		this.had = had;
	}
	
	
}
