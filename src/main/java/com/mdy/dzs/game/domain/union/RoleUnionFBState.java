package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

import com.mdy.dzs.game.util.DateUtil;

public class RoleUnionFBState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4329204480189871405L;
	private int id;
	private int roleId;
	private int totalHurt;
	private int leftAttackNum;
	private int bugNum;
	private int totalAttackNum;
	private int unionId;
	private Date createTime;
	private Date updateTime;
	private Date refreshTime;
	public static RoleUnionFBState ValueOf(RoleUnion roleUnion,int leftNum){
		RoleUnionFBState RUFBS = new RoleUnionFBState();
		RUFBS.roleId = roleUnion.getRoleId();
		RUFBS.totalHurt = 0;
		RUFBS.leftAttackNum = leftNum;
		RUFBS.bugNum = 0;
		RUFBS.unionId = roleUnion.getUnionId();
		RUFBS.createTime = DateUtil.GetNowDateTime();
		RUFBS.refreshTime = DateUtil.GetNowDateTime();
		return RUFBS;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getTotalHurt() {
		return totalHurt;
	}
	public void setTotalHurt(int totalHurt) {
		this.totalHurt = totalHurt;
	}
	public int getLeftAttackNum() {
		return leftAttackNum;
	}
	public void setLeftAttackNum(int leftAttackNum) {
		this.leftAttackNum = leftAttackNum;
	}
	public int getBugNum() {
		return bugNum;
	}
	public void setBugNum(int bugNum) {
		this.bugNum = bugNum;
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
	public Date getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}

	public int getTotalAttackNum() {
		return totalAttackNum;
	}

	public void setTotalAttackNum(int totalAttackNum) {
		this.totalAttackNum = totalAttackNum;
	}
	
}
