package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

import com.mdy.dzs.game.util.DateUtil;

public class RoleUnionFB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7817452043910784095L;
	private int id;
	private int fbId;
	private int type;
	private int attackNum;
	private int attackHp;
	private int rewardState;
	private int unionId;
	private int roleId;
	private Date createTime;
	private Date updateTime;
	public static RoleUnionFB CreateRUFB(RoleUnion roleUnion,int type,int fbId){
		RoleUnionFB roleUnionFB = new RoleUnionFB();
		roleUnionFB.fbId = fbId;
		roleUnionFB.type =type;
		roleUnionFB.attackNum = 0;
		roleUnionFB.attackHp = 0;
		roleUnionFB.rewardState = 1;
		roleUnionFB.roleId = roleUnion.getRoleId();
		roleUnionFB.unionId = roleUnion.getUnionId();
		roleUnionFB.createTime = DateUtil.GetNowDateTime();
		roleUnionFB.updateTime = DateUtil.GetNowDateTime();
		return roleUnionFB;
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
	public int getAttackNum() {
		return attackNum;
	}
	public void setAttackNum(int attackNum) {
		this.attackNum = attackNum;
	}
	public int getAttackHp() {
		return attackHp;
	}
	public void setAttackHp(int attackHp) {
		this.attackHp = attackHp;
	}
	public int getRewardState() {
		return rewardState;
	}
	public void setRewardState(int rewardState) {
		this.rewardState = rewardState;
	}
	public int getUnionId() {
		return unionId;
	}
	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
