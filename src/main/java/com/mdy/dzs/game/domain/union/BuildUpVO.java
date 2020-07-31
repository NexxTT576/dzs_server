package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

public class BuildUpVO implements Serializable {

	/**
	 * 建筑升级模型
	 */
	private static final long serialVersionUID = -1035327107344002956L;
	private int currentUnionMoney;
	private int buildLevel;
	private List<WorkShopVO> typeList;
	private int roleMaxNum;
	public int getCurrentUnionMoney() {
		return currentUnionMoney;
	}
	public void setCurrentUnionMoney(int currentUnionMoney) {
		this.currentUnionMoney = currentUnionMoney;
	} 
	public int getBuildLevel() {
		return buildLevel;
	}
	public void setBuildLevel(int buildLevel) {
		this.buildLevel = buildLevel;
	}
	public List<WorkShopVO> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<WorkShopVO> typeList) {
		this.typeList = typeList;
	}
	public int getRoleMaxNum() {
		return roleMaxNum;
	}
	public void setRoleMaxNum(int roleMaxNum) {
		this.roleMaxNum = roleMaxNum;
	}
}
