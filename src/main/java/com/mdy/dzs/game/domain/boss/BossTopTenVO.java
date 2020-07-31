package com.mdy.dzs.game.domain.boss;

import java.io.Serializable;

public class BossTopTenVO implements Serializable{
	/**
	 * {	rank:1,  	//排名
			acc:*, 	 	//账号
			name:*,  	//名字
			hurt:*   	//伤害血量
			lv:*	 	//等级
			roleId:* 	//id
			faction：* 	//帮派
		}	
	 */
	private static final long serialVersionUID = 1L;
	
	//排名
	private int rank;
	//账号
	private String acc;
	//名字
	private String name;
	//伤害血量
	private long hurt;
	//等级
	private int lv;
	//id
	private int id;
	//帮派
	private String faction;
	
	public BossTopTenVO(){};
	public BossTopTenVO(int rank, String acc, String name, long hurt, int lv, int id, String faction) {
		this.setRank(rank);
		this.setAcc(acc);
		this.setName(name);
		this.setHurt(hurt);
		this.setLv(lv);
		this.setId(id);
		this.setFaction(faction);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
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

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getFaction() {
		return faction;
	}
	public void setFaction(String faction) {
		this.faction = faction;
	}
	
}
