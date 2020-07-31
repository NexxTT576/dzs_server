package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 比武人的排行
 * @author Administrator
 *
 */
public class RoleTournament implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/***/
	private int roleId;
	/**积分*/
	private int score;
	/**胜利的次数*/
	private int winTimes;
	/**总共次数*/
	private int totalTimes;
	/**敌人ids*/
	private List<OppVO> enemyIds;
	/**下次刷新的时间戳*/
	private Date nextFleshTime;
	/**已经挑战的次数*/
	private int challengeTimes;
	/**购买的次数*/
	private int buyTimes;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
	/**刷新挑战次数和交易记录的时间*/
	private Date fleshTime;
	
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getScore(){
		return this.score;
	}
	public void setScore(int score){
		this.score=score;
	}
	public int getWinTimes(){
		return this.winTimes;
	}
	public void setWinTimes(int winTimes){
		this.winTimes=winTimes;
	}
	public int getTotalTimes(){
		return this.totalTimes;
	}
	public void setTotalTimes(int totalTimes){
		this.totalTimes=totalTimes;
	}
	public Date getNextFleshTime(){
		return this.nextFleshTime;
	}
	public void setNextFleshTime(Date nextFleshTime){
		this.nextFleshTime=nextFleshTime;
	}
	public int getChallengeTimes(){
		return this.challengeTimes;
	}
	public void setChallengeTimes(int challengeTimes){
		this.challengeTimes=challengeTimes;
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
	public int getBuyTimes() {
		return buyTimes;
	}
	public void setBuyTimes(int buyTimes) {
		this.buyTimes = buyTimes;
	}
	public List<OppVO> getEnemyIds() {
		return enemyIds;
	}
	public void setEnemyIds(List<OppVO> enemyIds) {
		this.enemyIds = enemyIds;
	}
	public Date getFleshTime() {
		return fleshTime;
	}
	public void setFleshTime(Date fleshTime) {
		this.fleshTime = fleshTime;
	}
}
