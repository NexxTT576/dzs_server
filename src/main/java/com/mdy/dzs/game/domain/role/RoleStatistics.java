package com.mdy.dzs.game.domain.role;

import java.io.Serializable;
import java.util.Date;


/**
 * 玩家统计模型
 * @author 房曈
 *
 */
public class RoleStatistics implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int roleId;
	/***/
	private String account;
	
	/**真金*/
	private int curGold;
	/**赠金*/
	private int curDonate;
	/**当前绑定币*/
	private int curBind;
	/**总金币*/
	private int totalGold;
	/**总获得赠金*/
	private int totalDonate;
	/**获得总绑金*/
	private int totalBind;

	
	/**充值花费钱数*/
	private float chargePrice;
	/**充值次数*/
	private int chargeCnt;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;

	/**
	 * @param rs
	 */
	public RoleStatistics(RoleStatistics rs) {
		curBind = rs.getCurBind();
		curDonate = rs.getCurDonate();
		curGold = rs.getCurGold();
		
		totalBind = rs.getTotalBind();
		totalDonate = rs.getTotalDonate();
		totalGold = rs.getTotalGold();
	}
	
	/**
	 * 
	 */
	public RoleStatistics() {
	}
	
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public String getAccount(){
		return this.account;
	}
	public void setAccount(String account){
		this.account=account;
	}
	public int getCurGold() {
		return curGold;
	}
	public void setCurGold(int curGold) {
		this.curGold = curGold;
	}
	public int getCurDonate() {
		return curDonate;
	}
	public void setCurDonate(int curDonate) {
		this.curDonate = curDonate;
	}
	public int getCurBind() {
		return curBind;
	}
	public void setCurBind(int curBind) {
		this.curBind = curBind;
	}
	public int getTotalGold() {
		return totalGold;
	}
	public void setTotalGold(int totalGold) {
		this.totalGold = totalGold;
	}
	public int getTotalDonate() {
		return totalDonate;
	}
	public void setTotalDonate(int totalDonate) {
		this.totalDonate = totalDonate;
	}
	public int getTotalBind() {
		return totalBind;
	}
	public void setTotalBind(int totalBind) {
		this.totalBind = totalBind;
	}

	public float getChargePrice(){
		return this.chargePrice;
	}
	public void setChargePrice(float chargePrice){
		this.chargePrice=chargePrice;
	}
	public int getChargeCnt(){
		return this.chargeCnt;
	}
	public void setChargeCnt(int chargeCnt){
		this.chargeCnt=chargeCnt;
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