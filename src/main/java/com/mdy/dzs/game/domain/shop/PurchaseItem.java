package com.mdy.dzs.game.domain.shop;

import java.io.Serializable;

/**
 * 可购买数据
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月17日  下午9:32:50
 */
public class PurchaseItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * itemId
	 */
	private int id;
	/**
	 * 可购买数量
	 */
	private int cnt;
	/**
	 * 最大可购买总数
	 */
	private int max;
	private int hadCnt;
	private int hadBuy;
	
	public PurchaseItem() {
		// TODO 自动生成的构造函数存根
	}
	public PurchaseItem(int v1,int v2,int v3,int v4,int v5) {
		id = v1;
		cnt = v2;
		max = v3;
		hadCnt = v4;
		hadBuy = v5;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getHadCnt() {
		return hadCnt;
	}
	public void setHadCnt(int hadCnt) {
		this.hadCnt = hadCnt;
	}
	public int getHadBuy() {
		return hadBuy;
	}
	public void setHadBuy(int hadBuy) {
		this.hadBuy = hadBuy;
	}
			
	
	
}
