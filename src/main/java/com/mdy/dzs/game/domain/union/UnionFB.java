package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

import com.mdy.dzs.data.domain.union.UnionFBData;
import com.mdy.dzs.game.util.DateUtil;

public class UnionFB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5473820835133314430L;
	private int id;
	private int fbId;
	private int type;
	private int leftHp;
	private int state;
	private int unionId;
	private Date createTime;
	private Date updateTime;
	public static UnionFB ValueOf(UnionFBData unionFBData,int totalHp,int unionId){
		UnionFB unionFB = new UnionFB();
		unionFB.createTime = DateUtil.GetNowDateTime();
		unionFB.fbId = unionFBData.getFbId();
		unionFB.leftHp = totalHp;
		unionFB.state = 0;
		unionFB.type = unionFBData.getType();
		unionFB.unionId = unionId;
		unionFB.updateTime = DateUtil.GetNowDateTime();
		return unionFB;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFbId() {
		return fbId;
	}
	public void setFbId(int fbId) {
		this.fbId = fbId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLeftHp() {
		return leftHp;
	}
	public void setLeftHp(int leftHp) {
		this.leftHp = leftHp;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getUnionId() {
		return unionId;
	}
	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
