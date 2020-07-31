package com.mdy.dzs.game.domain.activity.happygame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 累积登录礼包模型
 * @author 白雪林
 *
 */
public class RoleActivityHappy implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**角色id*/
	private int roleId;
	/**已领取天的list列表*/
	private List<Integer> getList;
	/**活动期间累积登录天数*/
	private int loginCnt;
	/**创建时间*/
	private Date createTime;
	/**领取时间点*/
	private Date getTime;
	/**更新时间点*/
	private Date updateTime;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public List<Integer> getGetList(){
		return this.getList;
	}
	public void setGetList(List<Integer> getList){
		this.getList=getList;
	}
	public int getLoginCnt(){
		return this.loginCnt;
	}
	public void setLoginCnt(int loginCnt){
		this.loginCnt=loginCnt;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getGetTime(){
		return this.getTime;
	}
	public void setGetTime(Date getTime){
		this.getTime=getTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
}