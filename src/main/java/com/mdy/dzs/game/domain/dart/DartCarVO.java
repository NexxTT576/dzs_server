package com.mdy.dzs.game.domain.dart;

import java.io.Serializable;

public class DartCarVO  implements Serializable{
	/*{		
		roleId：	角色ID
		name： 		名字
		lv：   		等级
		quality：	镖车品质	【1-4 ：1-绿，2-蓝，3-紫，4-金】
		arriveTime：倒记到达时间	
		dartkey:	战斗时发送
	}*/
	
	private static final long serialVersionUID = 1L;
	
	private int roleId;
	private String name;
	private int lv;
	private int quality;
	private int arriveTime;
	private int dartkey;
	
	public DartCarVO(){ }
	
	public DartCarVO(int roleId, String name, int lv, int quality, int arriveTime, int dartkey){
		this.roleId 	= roleId;
		this.name 		= name;
		this.lv 		= lv;
		this.quality 	= quality;
		this.arriveTime = arriveTime;
		this.dartkey	= dartkey;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getDartkey() {
		return dartkey;
	}

	public void setDartkey(int dartkey) {
		this.dartkey = dartkey;
	}
	
}

