package com.mdy.dzs.game.domain;

import java.io.Serializable;

public class Prop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idx = 0;
	private int val;
	
	public Prop() {
		// TODO 自动生成的构造函数存根
	}
	
	public Prop(int idx,int val) {
		this.idx = idx;
		this.val = val;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}

}
