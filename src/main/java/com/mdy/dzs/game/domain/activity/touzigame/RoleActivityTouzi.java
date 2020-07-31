package com.mdy.dzs.game.domain.activity.touzigame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 投资计划模型
 * @author 白雪林
 *
 */
public class RoleActivityTouzi implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**角色ID*/
	private int roleId;
	/**是否购买了投资计划*/
	private int hasBuy;
	/**已领取等级*/
	private List<Integer> getLevelList;
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
	public int getHasBuy(){
		return this.hasBuy;
	}
	public void setHasBuy(int hasBuy){
		this.hasBuy=hasBuy;
	}
	public List<Integer> getGetLevelList(){
		return this.getLevelList;
	}
	public void setGetLevelList(List<Integer> getLevelList){
		this.getLevelList=getLevelList;
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