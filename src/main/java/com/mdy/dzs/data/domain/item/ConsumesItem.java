/**
 * 
 */
package com.mdy.dzs.data.domain.item;

import java.io.Serializable;

/**
 * 消耗物品模型
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年3月16日  下午4:05:11
 */
public class ConsumesItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int type;
	private int id;
	private int num;
	private int need;
	
	/**
	 * 
	 */
	public ConsumesItem(ProbItem item) {
		this.type = item.getT();
		this.id = item.getId();
		this.need = item.getN();
	}
	
	public int getT() {
		return type;
	}
	public void setT(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getN1() {
		return num;
	}
	public void setN1(int num) {
		this.num = num;
	}
	public int getN2() {
		return need;
	}
	public void setN2(int num) {
		this.need = num;
	}
}
