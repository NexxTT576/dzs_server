package com.mdy.dzs.game.domain.swordfight;

import java.io.Serializable;

import com.mdy.dzs.game.domain.card.RoleCard;

public class SwordCard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**卡牌的自增id*/
	private int id;
	/**卡牌的顺序*/
	private int order;
	/**卡牌的位置*/
	private int pos;
	/**卡牌*/
	private RoleCard rc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public RoleCard getRc() {
		return rc;
	}
	public void setRc(RoleCard rc) {
		this.rc = rc;
	}
	
}
