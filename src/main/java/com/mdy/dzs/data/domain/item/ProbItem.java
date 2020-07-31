package com.mdy.dzs.data.domain.item;

import java.io.Serializable;

/**
 * 概率产出
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月17日 上午11:07:38
 */
public class ProbItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int type;
	private int id;
	private int num;

	private Integer objId;

	public ProbItem() {
	}

	public ProbItem(int t, int id, int n) {
		this.type = t;
		this.id = id;
		this.num = n;
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

	public int getN() {
		return num;
	}

	public void setN(int num) {
		this.num = num;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	@Override
	public String toString() {
		return "ProbItem [id=" + id + ", type=" + type + ", num=" + num + "]";
	}
}
