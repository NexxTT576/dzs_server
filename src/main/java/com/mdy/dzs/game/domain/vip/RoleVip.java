package com.mdy.dzs.game.domain.vip;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class RoleVip implements Serializable{
	
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	/**account*/
	private int role_id;
	/**首冲礼包是否发放*/
	private int firstGift;
	/**各价位购买次数统计数组*/
	private Map<String, Integer> buyCountMap;
	/**当前VIP经验值*/
	private int curExp;
	/**已经领取的VIP等级礼包*/
	private List<Integer> getlevelgift;
	/**vip每日福利领取时间*/
	private Date vipdaygift;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	
	public int getRole_id(){
		return this.role_id;
	}
	public void setRole_id(int role_id){
		this.role_id=role_id;
	}
	
	public int getFirstGift(){
		return this.firstGift;
	}
	public void setFirstGift(int firstGift){
		this.firstGift=firstGift;
	}

	public Map<String, Integer> getBuyCountMap(){
		return this.buyCountMap;
	}
	public void setBuyCountMap(Map<String, Integer> buyCountMap){
		this.buyCountMap=buyCountMap;
	}
	
	public int getCurExp(){
		return this.curExp;
	}
	public void setCurExp(int curExp){
		this.curExp=curExp;
	}

	public Date getVipdaygift() {
		return vipdaygift;
	}
	public void setVipdaygift(Date vipdaygift) {
		this.vipdaygift = vipdaygift;
	}
	public List<Integer> getGetlevelgift() {
		return getlevelgift;
	}
	public void setGetlevelgift(List<Integer> getlevelgift) {
		this.getlevelgift = getlevelgift;
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
