package com.mdy.dzs.game.domain.card;

import java.io.Serializable;

/**
 * 升级下一阶段所需信息
 * {id:cardIdNeed, t:8, n1: cardNeedCnt, n2:cardHasCnt}
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月21日  上午11:39:50
 */
public class RoleCardClsUpItemInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int t;
	private int n1;
	private int n2;
	
	public RoleCardClsUpItemInfo() {
		// TODO 自动生成的构造函数存根
	}
	
	public RoleCardClsUpItemInfo(int id,int t,int n1,int n2) {
		this.id = id;
		this.t = t;
		this.n1= n1;
		this.n2 = n2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

}
