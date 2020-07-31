package com.mdy.dzs.game.domain.boss;

import java.io.Serializable;

public class RankList  implements Serializable{
	/**
	 * list:[{	
				acc:账号
				totalHurt:总伤害
				name:名字
				lv:等级
				roleId:id
				faction:帮派
			}]
	 * */
	private static final long serialVersionUID = 1L;
	
	//账号
	private String acc;
	//总伤害
	private int totalHurt;
	//名字
	private String name;
	//等级
	private int lv;
	//id
	private int id;
	//帮派
	private String faction;
		
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public int getTotalHurt() {
		return totalHurt;
	}
	public void setTotalHurt(int totalHurt) {
		this.totalHurt = totalHurt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
