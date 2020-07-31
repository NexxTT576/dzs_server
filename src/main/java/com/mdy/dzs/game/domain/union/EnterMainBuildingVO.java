package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

public class EnterMainBuildingVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8444709813115928853L;

	
	private List<Object> dynamciList;
	private int isCon;
	private int currentUnionMoney;
	private int unionLevel;
	private int roleMaxNum;
	private int contributed;
	private int viplevel;
	public List<Object> getDynamciList() {
		return dynamciList;
	}
	public void setDynamciList(List<Object> dynamciList) {
		this.dynamciList = dynamciList;
	}
	public int getCurrentUnionMoney() {
		return currentUnionMoney;
	}
	public void setCurrentUnionMoney(int currentUnionMoney) {
		this.currentUnionMoney = currentUnionMoney;
	}
	public int getUnionLevel() {
		return unionLevel;
	}
	public void setUnionLevel(int unionLevel) {
		this.unionLevel = unionLevel;
	}
	public int getIsCon() {
		return isCon;
	}
	public void setIsCon(int isCon) {
		this.isCon = isCon;
	}
	public int getRoleMaxNum() {
		return roleMaxNum;
	}
	public void setRoleMaxNum(int roleMaxNum) {
		this.roleMaxNum = roleMaxNum;
	}
	public int getContributed() {
		return contributed;
	}
	public void setContributed(int contributed) {
		this.contributed = contributed;
	}
	public int getViplevel() {
		return viplevel;
	}
	public void setViplevel(int viplevel) {
		this.viplevel = viplevel;
	}
}
