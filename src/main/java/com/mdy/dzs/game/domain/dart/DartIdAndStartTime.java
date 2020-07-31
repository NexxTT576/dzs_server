package com.mdy.dzs.game.domain.dart;

import java.io.Serializable;
import java.util.Date;

public class DartIdAndStartTime implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	/**角色ID*/
	private int roleId;
	/**押镖开始时间*/
	private Date startTime;
	

	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}
