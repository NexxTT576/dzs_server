package com.mdy.dzs.game.domain.RoleLineupAid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 用户辅助阵型模型
 * @author zhou
 *
 */
public class RoleLineupAid implements Serializable{
	
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	public RoleLineupAid(int roleId ,int sysId ,Date date){
		this.roleId = roleId;
		this.sysId = sysId;
		this.refreshTime = date;
		setCurLineup(new ArrayList<List<Integer>>());
	}
	public RoleLineupAid(){
		
	}
	/***/
	private int id;
	/***/
	private int roleId;
	/**系统id*/
	private int sysId;
	/**当前阵容*/
	private List<List<Integer>> curLineup;
	/**刷新时的时间*/
	private Date refreshTime;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getSysId(){
		return this.sysId;
	}
	public void setSysId(int sysId){
		this.sysId=sysId;
	}
	public Date getRefreshTime(){
		return this.refreshTime;
	}
	public void setRefreshTime(Date refreshTime){
		this.refreshTime=refreshTime;
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
	public List<List<Integer>> getCurLineup() {
		return curLineup;
	}
	public void setCurLineup(List<List<Integer>> curLineup) {
		this.curLineup = curLineup;
	}
}
