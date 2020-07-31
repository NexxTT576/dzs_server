/**
 * 
 */
package com.mdy.dzs.game.fight.domain;

import java.io.Serializable;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  下午8:29:47
 */
public class FightEndVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**type,战斗出手类型*/
	private int t=9;
	/**count,回合计数*/
	private int n;
	/**win,获胜方(1战斗发起方,2应战方)*/
	private int win;
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	
	
}
