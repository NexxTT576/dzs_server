package com.mdy.dzs.data.domain.item;

import java.io.Serializable;

/**
 * 概率值
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月24日  下午5:36:32
 */
public class ProbVal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int GOT_TYPE_单个 = 1;
	public static final int GOT_TYPE_多个 = 2;
	
	private int t;
	private int minId;
	private int maxId;
	private int minNum;
	private int maxNum;
	private int prob;
	
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public int getMinId() {
		return minId;
	}
	public void setMinId(int minId) {
		this.minId = minId;
	}
	public int getMaxId() {
		return maxId;
	}
	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}
	public int getMinNum() {
		return minNum;
	}
	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getProb() {
		return prob;
	}
	public void setProb(int prob) {
		this.prob = prob;
	}

}
