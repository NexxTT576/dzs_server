/**
 * 
 */
package com.mdy.dzs.game.domain.battle;

import java.io.Serializable;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  下午12:15:18
 */
public class LvNum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int lv;
	private int n;
	
	public LvNum(int lv,int n) {
		this.lv = lv;
		this.n = n;
	}
	public LvNum() {
	}
	
	
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	
}
