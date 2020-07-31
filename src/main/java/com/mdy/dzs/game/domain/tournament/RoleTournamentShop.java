package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.Date;


/**
 * 比武的兑换物品记录
 * @author zhou
 *
 */
public class RoleTournamentShop implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int roleId;
	/***/
	private int itemId;
	/***/
	private int exchangeTimes;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
	/**兑换类型（1-总次数限定，2-本周次数限定,3-今日次数限定）*/
	private int type;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getItemId(){
		return this.itemId;
	}
	public void setItemId(int itemId){
		this.itemId=itemId;
	}
	public int getExchangeTimes(){
		return this.exchangeTimes;
	}
	public void setExchangeTimes(int exchangeTimes){
		this.exchangeTimes=exchangeTimes;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}