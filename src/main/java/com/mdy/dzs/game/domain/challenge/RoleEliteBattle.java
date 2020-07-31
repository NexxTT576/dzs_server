/**
 * 
 */
package com.mdy.dzs.game.domain.challenge;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 玩家精英副本状态
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月7日  下午8:04:08
 */
public class RoleEliteBattle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**玩家id*/
	private int roleId;
	/**精英副本最大关卡数*/
	private int eliteBattleLv;
	/**精英副本当前攻打关卡id*/
	private int eliteNxtLv;
	/**未通关副本状态*/
	private int eliteNxtLvState;
	/**未通关副本，是否需要复活*/
	private int eliteNxtLvRevive;
	/**下次战斗,下阵卡牌位置组*/
	private List<Integer> eliteNxtLvDPos;
	/**精英副本今日攻打关卡次数*/
	private int eliteTodayCnt;
	/**今日已购买次数*/
	private int eliteBuyCnt;
	
	private Date createTime;
	private Date updateTime;
	
	private Date eliteDayRefresh;
	
	public Date getEliteDayRefresh() {
		return eliteDayRefresh;
	}
	public int getEliteBattleLv(){
		return this.eliteBattleLv;
	}
	public void setEliteBattleLv(int eliteBattleLv){
		this.eliteBattleLv=eliteBattleLv;
	}
	public int getEliteNxtLv(){
		return this.eliteNxtLv;
	}
	public void setEliteNxtLv(int eliteNxtLv){
		this.eliteNxtLv=eliteNxtLv;
	}
	public int getEliteNxtLvState(){
		return this.eliteNxtLvState;
	}
	public void setEliteNxtLvState(int eliteNxtLvState){
		this.eliteNxtLvState=eliteNxtLvState;
	}
	public int getEliteNxtLvRevive(){
		return this.eliteNxtLvRevive;
	}
	public void setEliteNxtLvRevive(int eliteNxtLvRevive){
		this.eliteNxtLvRevive=eliteNxtLvRevive;
	}
	public List<Integer> getEliteNxtLvDPos(){
		return this.eliteNxtLvDPos;
	}
	public void setEliteNxtLvDPos(List<Integer> eliteNxtLvDPos){
		this.eliteNxtLvDPos=eliteNxtLvDPos;
	}
	public int getEliteTodayCnt(){
		return this.eliteTodayCnt;
	}
	public void setEliteTodayCnt(int eliteTodayCnt){
		this.eliteTodayCnt=eliteTodayCnt;
	}
	public int getEliteBuyCnt(){
		return this.eliteBuyCnt;
	}
	public void setEliteBuyCnt(int eliteBuyCnt){
		this.eliteBuyCnt=eliteBuyCnt;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setEliteDayRefresh(Date eliteDayRefresh) {
		this.eliteDayRefresh = eliteDayRefresh;
		
	}
}
