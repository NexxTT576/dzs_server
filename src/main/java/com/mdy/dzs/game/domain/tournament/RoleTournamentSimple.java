package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;

public class RoleTournamentSimple implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/**角色id*/
	private int roleId;
	/**排名*/
	private int rank;
	/**积分*/
	private int score;
	/**挑战次数*/
	private int challengeTimes;
	/**帮派*/
	private String faction;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getChallengeTimes() {
		return challengeTimes;
	}
	public void setChallengeTimes(int challengeTimes) {
		this.challengeTimes = challengeTimes;
	}
	public String getFaction() {
		return faction;
	}
	public void setFaction(String faction) {
		this.faction = faction;
	}
}
