package com.mdy.dzs.game.domain.prob;

import java.io.Serializable;
import java.util.Date;

import com.mdy.sharp.util.JSONUtil;


/**
 * 概率模型
 * @author 房曈
 *
 */
public class RolePseudo implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int roleId;
	/**pseudo类型*/
	private int pseudoType;
	/**当前使用概率id*/
	private int pseudoId;
	/**计数*/
	private int count;
	/**统计总数*/
	private int allCount;
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
	public int getPseudoType(){
		return this.pseudoType;
	}
	public void setPseudoType(int pseudoType){
		this.pseudoType=pseudoType;
	}
	public int getPseudoId(){
		return this.pseudoId;
	}
	public void setPseudoId(int pseudoId){
		this.pseudoId=pseudoId;
	}
	public int getCount(){
		return this.count;
	}
	public void setCount(int count){
		this.count=count;
	}
	public int getAllCount(){
		return this.allCount;
	}
	public void setAllCount(int allCount){
		this.allCount=allCount;
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

	@Override
	public String toString() {
		return JSONUtil.toJson(this);
	}
}