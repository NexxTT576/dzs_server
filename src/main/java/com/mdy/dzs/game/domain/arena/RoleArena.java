package com.mdy.dzs.game.domain.arena;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 竞技场模型
 * @author 房曈
 *
 */
public class RoleArena implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**排行*/
	private int rank;
	/**玩家id*/
	private int roleId;
	/**奖励id*/
	private List<Integer> awardIds;
	
	private int awardSilver;
	private int awardPopual;
	
	
	/***/
	private Date createTime;
	/***/
	private Date updateTime;

	public int getRank(){
		return this.rank;
	}
	public void setRank(int rank){
		this.rank=rank;
	}
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public List<Integer> getAwardIds(){
		return this.awardIds;
	}
	public void setAwardIds(List<Integer> awardIds){
		this.awardIds=awardIds;
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
	public int getAwardSilver() {
		return awardSilver;
	}
	public void setAwardSilver(int awardSilver) {
		this.awardSilver = awardSilver;
	}
	public int getAwardPopual() {
		return awardPopual;
	}
	public void setAwardPopual(int awardPopual) {
		this.awardPopual = awardPopual;
	}
	
}