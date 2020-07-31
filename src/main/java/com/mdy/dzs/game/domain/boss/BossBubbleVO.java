package com.mdy.dzs.game.domain.boss;

import java.io.Serializable;

public class BossBubbleVO implements Serializable{
	/*
	 * {
			name:名字
			hurt:伤害
			timestamp:时间戳
	   }
	*/
	private static final long serialVersionUID = 1L;
	
	/**名字*/
	private String name;
	/**伤害*/
	private long hurt;
	/**时间戳*/
	private long timestamp;
	/**角色id*/
	private int roleId;
	
	public BossBubbleVO(String name, long hurt, long timestamp, int roleId) {
		this.setRoleId(roleId);
		this.setName(name);
		this.setHurt(hurt);
		this.setTimestamp(timestamp);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getHurt() {
		return hurt;
	}


	public void setHurt(long hurt) {
		this.hurt = hurt;
	}


	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
