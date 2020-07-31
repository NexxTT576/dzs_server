package com.mdy.dzs.game.domain.activity.limitcard;

import java.io.Serializable;
import java.util.Date;

public class LimitCardBean implements Serializable
{
	private static final long serialVersionUID = 5715823391477942118L;
	private int id;
	private int role_id;
	private String rolename;
	private int getcount;
	private int lucknum;
	private int score;
	private int rank;
	private int problucknum;
	private Date freegettime;
	private Date lastgettime;
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getProblucknum() {
		return problucknum;
	}
	public void setProblucknum(int problucknum) {
		this.problucknum = problucknum;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public int getGetcount() {
		return getcount;
	}
	public void setGetcount(int getcount) {
		this.getcount = getcount;
	}
	public int getLucknum() {
		return lucknum;
	}
	public void setLucknum(int lucknum) {
		this.lucknum = lucknum;
	}
	public Date getFreegettime() {
		return freegettime;
	}
	public void setFreegettime(Date freegettime) {
		this.freegettime = freegettime;
	}
	public Date getLastgettime() {
		return lastgettime;
	}
	public void setLastgettime(Date lastgettime) {
		this.lastgettime = lastgettime;
	}
}
