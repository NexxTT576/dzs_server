package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;
import java.util.Date;


/**
 * 玩家限时兑换模型模型
 * @author zhou
 *
 */
public class RoleActivityExch implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	public RoleActivityExch(int roleId , int expId ,int exchId,int exchNum,int exchType,int refreshNum){
		this.roleId = roleId;
		this.expId = expId;
		this.exchId = exchId;
		this.exchNum = exchNum;
		this.exchType = exchType;
		this.refreshNum = refreshNum;
	}
	public RoleActivityExch(){
		
	}
	/***/
	private int id;
	/***/
	private int roleId;
	/**兑换的公式*/
	private int expId;
	/**兑换活动列表id*/
	private int exchId;
	/**兑换的数量*/
	private int exchNum;
	/**兑换的类型*/
	private int exchType;
	/**刷新的数量*/
	private int refreshNum;
	/**已使用免费刷新次数*/
	private int refFreeNum;
	/**刷新的时间*/
	private Date refreshTime;
	/***/
	private Date createTime;
	/***/
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
	public int getExpId(){
		return this.expId;
	}
	public void setExpId(int expId){
		this.expId=expId;
	}
	public int getExchId(){
		return this.exchId;
	}
	public void setExchId(int exchId){
		this.exchId=exchId;
	}
	public int getExchNum(){
		return this.exchNum;
	}
	public void setExchNum(int exchNum){
		this.exchNum=exchNum;
	}
	public int getExchType(){
		return this.exchType;
	}
	public void setExchType(int exchType){
		this.exchType=exchType;
	}
	public int getRefreshNum(){
		return this.refreshNum;
	}
	public void setRefreshNum(int refreshNum){
		this.refreshNum=refreshNum;
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
	public Date getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}
	public int getRefFreeNum() {
		return refFreeNum;
	}
	public void setRefFreeNum(int refFreeNum) {
		this.refFreeNum = refFreeNum;
	}
}