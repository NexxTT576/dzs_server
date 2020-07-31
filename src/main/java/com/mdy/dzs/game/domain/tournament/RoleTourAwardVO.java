package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.gift.GiftItem;

public class RoleTourAwardVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	private int roleId;
	private int rank;
	private List<GiftItem> awards;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public List<GiftItem> getAwards() {
		return awards;
	}
	public void setAwards(List<GiftItem> awards) {
		this.awards = awards;
	}
}
