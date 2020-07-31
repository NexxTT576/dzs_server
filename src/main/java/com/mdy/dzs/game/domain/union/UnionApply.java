package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

import com.mdy.dzs.game.util.DateUtil;


public class UnionApply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5342334233086916007L;
	
	/**id*/
	private int id ;
	/**帮派id*/
	private int unionId;
	/**玩家id*/
	private int roleId;
    /**申请时间*/
	private Date createTime;
	/**状态*/
	private int state;
	/**玩家等级*/
	private int level;
	/**竞技场排名*/
	private int rank;
	
	/**
	 * 初始化
	 * @param unionId
	 * @param roleId
	 * @return
	 */
	public static UnionApply valueOf(int unionId,int roleId,int level,int rank){
		UnionApply unionApply = new UnionApply();
		unionApply.unionId = unionId;
		unionApply.roleId = roleId;
		unionApply.createTime = DateUtil.GetNowDateTime();
		unionApply.state = 0;
		unionApply.level = level;
		unionApply.rank = rank;
		return unionApply;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUnionId() {
		return unionId;
	}
	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
