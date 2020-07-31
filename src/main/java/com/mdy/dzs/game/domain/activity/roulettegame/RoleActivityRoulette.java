package com.mdy.dzs.game.domain.activity.roulettegame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 皇宫探宝数据表模型
 * @author 白雪林
 *
 */
public class RoleActivityRoulette implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**角色id*/
	private int roleId;
	/**积分累计*/
	private int credit;
	/**剩余次数*/
	private int surTimes;
	/**探宝次数*/
	private int rouletteTimes;
	/**今日累积充值或消耗*/
	private int dayAdd;
	/**已领取的积分箱子*/
	private List<Integer> getBox;
	/**免费次数*/
	private int freeTimes;
	/**刷新时间*/
	private Date refreshTime;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	/**总探宝次数*/
	private int allRouletteCnt;
	/**position计数*/
	private int positionAdd;
	/**应用prob行数*/
	private int nextPropLine;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getCredit(){
		return this.credit;
	}
	public void setCredit(int credit){
		this.credit=credit;
	}
	public int getSurTimes(){
		return this.surTimes;
	}
	public void setSurTimes(int surTimes){
		this.surTimes=surTimes;
	}
	public int getRouletteTimes(){
		return this.rouletteTimes;
	}
	public void setRouletteTimes(int rouletteTimes){
		this.rouletteTimes=rouletteTimes;
	}
	public int getDayAdd(){
		return this.dayAdd;
	}
	public void setDayAdd(int dayAdd){
		this.dayAdd=dayAdd;
	}
	public List<Integer> getGetBox(){
		return this.getBox;
	}
	public void setGetBox(List<Integer> getBox){
		this.getBox=getBox;
	}
	public int getFreeTimes(){
		return this.freeTimes;
	}
	public void setFreeTimes(int freeTimes){
		this.freeTimes=freeTimes;
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
	public int getAllRouletteCnt() {
		return allRouletteCnt;
	}
	public void setAllRouletteCnt(int allRouletteCnt) {
		this.allRouletteCnt = allRouletteCnt;
	}
	public int getNextPropLine() {
		return nextPropLine;
	}
	public void setNextPropLine(int nextPropLine) {
		this.nextPropLine = nextPropLine;
	}
	public int getPositionAdd() {
		return positionAdd;
	}
	public void setPositionAdd(int positionAdd) {
		this.positionAdd = positionAdd;
	}
}