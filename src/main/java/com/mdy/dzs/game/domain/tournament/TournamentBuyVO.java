package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;

public class TournamentBuyVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/**角色id*/
	private int roleId;
	/**金币*/
	private int gold;
	/**剩余次数*/
	private int times;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
}
