package com.mdy.dzs.game.domain.activity.monthsigngame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 月签模型
 * @author 白雪林
 *
 */
public class RoleActivityMonthSign implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**角色ID*/
	private int roleId;
	/**月登录天数累积*/
	private int loginCnt;
	/**已领取天列表*/
	private List<Integer> getList;
	/**刷新时间*/
	private Date dayRefreshTime;
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
	public int getLoginCnt(){
		return this.loginCnt;
	}
	public void setLoginCnt(int loginCnt){
		this.loginCnt=loginCnt;
	}
	public List<Integer> getGetList(){
		return this.getList;
	}
	public void setGetList(List<Integer> getList){
		this.getList=getList;
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