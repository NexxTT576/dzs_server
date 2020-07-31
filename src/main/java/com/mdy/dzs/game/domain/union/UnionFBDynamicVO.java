package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

public class UnionFBDynamicVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5160263596459480241L;
	private String name;
	private int total;
	private int type;
//	public static UnionFBDynamicVO ValueOf(UnionFBDynamic unionFBDynamic){
//		UnionFBDynamicVO
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
