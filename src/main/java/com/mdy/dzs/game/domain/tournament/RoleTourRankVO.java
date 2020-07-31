package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;

public class RoleTourRankVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/**角色id*/
	private int roleId;
	/**积分*/
	private int score;
	/**排名*/
	private int rank;
	/**本周总挑战次数*/
	private int totalTimes;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getTotalTimes() {
		return totalTimes;
	}
	public void setTotalTimes(int totalTimes) {
		this.totalTimes = totalTimes;
	}
}
