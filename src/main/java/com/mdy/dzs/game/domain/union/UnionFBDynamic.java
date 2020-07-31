package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

public class UnionFBDynamic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5537211379872987114L;
	private int id;
	private int type;
	private String des;
	private int unionId;
	private int fbType;
	private int fbId;
	private Date creatTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getUnionId() {
		return unionId;
	}
	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}
	public int getFbType() {
		return fbType;
	}
	public void setFbType(int fbType) {
		this.fbType = fbType;
	}
	public int getFbId() {
		return fbId;
	}
	public void setFbId(int fbId) {
		this.fbId = fbId;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

}
