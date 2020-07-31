package com.mdy.dzs.game.domain.arena;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
 * 竞技场商店模型
 * @author 房曈
 *
 */
public class RoleArenaShop implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**角色id*/
	private int roleId;
	/**日次数限定*/
	private Map<String, Integer> dayPurchased;
	/**总次数限定*/
	private Map<String, Integer> allPurchased;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	/**刷新时间点*/
	private Date dayRefreshTime;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public Map<String, Integer> getDayPurchased(){
		return this.dayPurchased;
	}
	public void setDayPurchased(Map<String, Integer> dayPurchased){
		this.dayPurchased=dayPurchased;
	}
	public Map<String, Integer> getAllPurchased(){
		return this.allPurchased;
	}
	public void setAllPurchased(Map<String, Integer> allPurchased){
		this.allPurchased=allPurchased;
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
	public Date getDayRefreshTime() {
		return dayRefreshTime;
	}
	public void setDayRefreshTime(Date dayRefreshTime) {
		this.dayRefreshTime = dayRefreshTime;
	}
}