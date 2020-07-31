package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.Date;

/**
 * 比武的仇人模型
 * @author zhou
 *
 */
public class RoleTournamentEnemy implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int roleId;
	/***/
	private int enemyId;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getEnemyId(){
		return this.enemyId;
	}
	public void setEnemyId(int enemyId){
		this.enemyId=enemyId;
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