package com.mdy.dzs.game.domain.union;

import java.io.Serializable;


public class UnionRoleShopInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1450635964395027040L;

	private int id;
	
	//商品数量
	private int exchange;
	
	public static UnionRoleShopInfo valueOf(int id,int num){
		UnionRoleShopInfo propInfo = new UnionRoleShopInfo();
		propInfo.id = id;
		propInfo.exchange = num;
		return propInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExchange() {
		return exchange;
	}

	public void setExchange(int exchange) {
		this.exchange = exchange;
	}
	
}
