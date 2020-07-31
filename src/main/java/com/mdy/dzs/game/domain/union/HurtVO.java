package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

import com.mdy.dzs.game.domain.role.Role;

public class HurtVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6393331022771282587L;
	private String name;
	private String acc;
	private int roleLevel;
	private int attackNum;
	private int attackHp;
	private Date createTime;
	public static HurtVO ValueOf(RoleUnionFBState RUFBS,Role role){
		HurtVO hurt = new HurtVO();
		hurt.acc = role.getAccount();
		hurt.name = role.getName();
		hurt.roleLevel = role.getLevel();
		hurt.attackNum = RUFBS.getTotalAttackNum();
		hurt.attackHp = RUFBS.getTotalHurt();
		hurt.createTime = RUFBS.getCreateTime();
		return hurt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public int getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
