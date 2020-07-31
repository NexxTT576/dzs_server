package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.util.DateUtil;

public class MemberVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 910548064962013810L;
	
	private  String roleName;
	private  int roleLevel;
	private  int attack;
	private  int hertLastTime;
	private  int rank;
	private  Date createTime;
	private  int jopType;
	private  int roleId;
	private  int rolecls;
	private  String roleAcc;
	private  int resId;
	private  int totalContribute;
	private  int lastContribute;
	private  Date lastContributionTime;
	private  int defenseNum;
	private  int todayIsCon;
	private  int conType;
	private  int costMoney;
	private int isFriend;
	public static MemberVO  valueOf(Role role,UnionApply unionApply){
		MemberVO roleApp = new MemberVO();
		roleApp.roleName = role.getName();
		roleApp.roleId = role.getId();
		roleApp.rolecls = role.getCls();
		roleApp.roleLevel = role.getLevel();
		roleApp.attack = role.getAttack();
		roleApp.hertLastTime = role.getHeartLastTime();
		roleApp.resId = role.getResId();
		roleApp.roleAcc = role.getAccount();
		roleApp.rank = unionApply.getRank();
		roleApp.createTime = unionApply.getCreateTime();
		return roleApp;
		
	}
	public static MemberVO  memberValueOf(Role role,RoleUnion roleUnion,int isFriend){
		MemberVO member = new MemberVO();
		member.roleId = role.getId();
		member.roleName = role.getName();
		member.resId = role.getResId();
		member.roleAcc = role.getAccount();
		member.rolecls = role.getCls();
		member.roleLevel = role.getLevel();
		member.attack = role.getAttack();
		member.hertLastTime = role.getHeartLastTime();
		member.rank = roleUnion.getRank();
		member.totalContribute = roleUnion.getTotalContribute();
		member.lastContribute = roleUnion.getLastContribute();
		member.lastContributionTime = roleUnion.getLastContributionTime();
		member.jopType = roleUnion.getJopType();
		member.defenseNum = roleUnion.getDefenseNum();
		member.isFriend = isFriend;
		if(roleUnion.getConType() !=0&&DateUtil.isToday(roleUnion.getLastContributionTime())){
			member.todayIsCon =1;
		}else{
			member.todayIsCon = 0;
		}
		member.costMoney = roleUnion.getCostMoney();
		if(roleUnion.getConType() ==1){
			member.conType = 0;
		}else if(roleUnion.getConType() ==2||roleUnion.getConType() ==3){
			member.conType = 1;
		}
		return member;
		
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getHertLastTime() {
		return hertLastTime;
	}
	public void setHertLastTime(int hertLastTime) {
		this.hertLastTime = hertLastTime;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getJopType() {
		return jopType;
	}
	public void setJopType(int jopType) {
		this.jopType = jopType;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getRolecls() {
		return rolecls;
	}
	public void setRolecls(int rolecls) {
		this.rolecls = rolecls;
	}
	public int getTotalContribute() {
		return totalContribute;
	}
	public void setTotalContribute(int totalContribute) {
		this.totalContribute = totalContribute;
	}
	public int getLastContribute() {
		return lastContribute;
	}
	public void setLastContribute(int lastContribute) {
		this.lastContribute = lastContribute;
	}
	public Date getLastContributionTime() {
		return lastContributionTime;
	}
	public void setLastContributionTime(Date lastContributionTime) {
		this.lastContributionTime = lastContributionTime;
	}
	public int getDefenseNum() {
		return defenseNum;
	}
	public void setDefenseNum(int defenseNum) {
		this.defenseNum = defenseNum;
	}
	public int getTodayIsCon() {
		return todayIsCon;
	}
	public void setTodayIsCon(int todayIsCon) {
		this.todayIsCon = todayIsCon;
	}
	public int getConType() {
		return conType;
	}
	public void setConType(int conType) {
		this.conType = conType;
	}
	public int getCostMoney() {
		return costMoney;
	}
	public void setCostMoney(int costMoney) {
		this.costMoney = costMoney;
	}
	public String getRoleAcc() {
		return roleAcc;
	}
	public void setRoleAcc(String roleAcc) {
		this.roleAcc = roleAcc;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getIsFriend() {
		return isFriend;
	}
	public void setIsFriend(int isFriend) {
		this.isFriend = isFriend;
	}
}
