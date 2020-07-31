package com.mdy.dzs.game.domain.activity.limitcard;

import java.io.Serializable;

public class RankListBean implements Serializable
{
	private static final long serialVersionUID = 1407891144762373945L;
	
	//名次
	private int runkid;
	
	//用户名称
	private String username;
	
	//积分
	private int score;
	
	//角色id
	private int role_id;

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getRunkid() {
		return runkid;
	}

	public void setRunkid(int runkid) {
		this.runkid = runkid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
