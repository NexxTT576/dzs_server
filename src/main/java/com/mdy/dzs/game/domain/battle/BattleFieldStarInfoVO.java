package com.mdy.dzs.game.domain.battle;

import java.io.Serializable;

/**
 * 
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  上午11:49:40
 */
public class BattleFieldStarInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 0未打/1已打简单/2已打中等/3已打困难
	 */
	private int star;
	/**
	 * 剩余次数
	 */
	private int cnt;
	/**
	 * 最大次数
	 */
	private int max;
	
	public BattleFieldStarInfoVO() {
		
	}
	
	public BattleFieldStarInfoVO(int star,int cnt,int max) {
		this.star = star;
		this.cnt = cnt;
		this.max = max;
	}
	
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	
}
