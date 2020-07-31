package com.mdy.dzs.game.domain.vip;

import java.io.Serializable;
import java.util.Date;

public class RoleVipLevelGift implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;

	/**玩家id*/
	private int roleId;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
	/**等级*/
	private int lv;
	
	public int getLv(){
		return this.lv;
	}
	public void setLv(int lv){
		this.lv=lv;
	}
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	
}
