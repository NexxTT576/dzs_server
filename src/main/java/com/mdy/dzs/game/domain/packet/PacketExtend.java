package com.mdy.dzs.game.domain.packet;

import java.io.Serializable;

/**
 * 背包扩展花费
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月17日  上午10:41:24
 */
public class PacketExtend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type;
	private int cost;
	private int size;
	private int id;
	private int curCnt;
	private int limit;
	
	public PacketExtend(int t,int c,int cnt,int l,int s,int id) {
		this.type = t;
		this.cost = c;
		this.curCnt = cnt;
		this.limit = l;
		this.size = s;
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCurCnt() {
		return curCnt;
	}

	public void setCurCnt(int curCnt) {
		this.curCnt = curCnt;
	}
}
