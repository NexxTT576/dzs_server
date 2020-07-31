package com.mdy.dzs.game.domain.card;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 卡片附加效果模型
 * @author 房曈
 *
 */
public class RoleCardEffect implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int roleId;
	/**卡牌id*/
	private int cardId;
	/**生效羁绊*/
	private List<Integer> effectCardRelation;
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
	public int getCardId(){
		return this.cardId;
	}
	public void setCardId(int cardId){
		this.cardId=cardId;
	}
	public List<Integer> getEffectCardRelation(){
		return this.effectCardRelation;
	}
	public void setEffectCardRelation(List<Integer> effectCardRelation){
		this.effectCardRelation=effectCardRelation;
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