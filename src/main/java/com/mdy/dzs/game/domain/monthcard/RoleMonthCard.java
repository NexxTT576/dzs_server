package com.mdy.dzs.game.domain.monthcard;

import java.io.Serializable;
import java.util.Date;


/**
 * 角色月卡模型模型
 * @author 白雪林
 *
 */
public class RoleMonthCard implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**账号id*/
	private int roleId;
	/**领取时间*/
	private Date getTime;
	/**结束时间*/
	private Date overTime;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public Date getGetTime(){
		return this.getTime;
	}
	public void setGetTime(Date getTime){
		this.getTime=getTime;
	}
	public Date getOverTime(){
		return this.overTime;
	}
	public void setOverTime(Date overTime){
		this.overTime=overTime;
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