package com.mdy.dzs.game.domain.item;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 道具模型
 * 
 * @author 房曈
 *
 */
public class RoleItem implements Serializable {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	/***/
	private int id;
	/***/
	private int roleId;
	/***/
	private int itemId;
	/***/
	private int itemCnt;
	/***/
	private int itemLimit;
	// 用为物品的优质概率当前用在宝箱上
	private int boxpro;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemCnt() {
		return this.itemCnt;
	}

	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}

	public int getItemLimit() {
		return this.itemLimit;
	}

	public void setItemLimit(int itemLimit) {
		this.itemLimit = itemLimit;
	}

	public int getBoxpro() {
		return boxpro;
	}

	public void setBoxpro(int boxpro) {
		this.boxpro = boxpro;
	}
}