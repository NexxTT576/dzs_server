package com.mdy.dzs.game.domain.boss;

import java.io.Serializable;
import java.util.Date;


/**
 * boss战参与玩家模型
 * @author 白雪林
 *
 */
public class BossBattlePlayer implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**玩家id*/
	private int roleId;
	/**账号*/
	private String acc;
	/**昵称*/
	private String roleName;
	/**玩家总伤害*/
	private int totalHurt;
	/**伤害加成*/
	private int hurtAdd;
	/**复活次数*/
	private int liveCnt;
	/**攻击次数*/
	private int battleCnt;
	/**攻击等待开始时间*/
	private Date battleWait;
	/**银币鼓舞等待时间*/
	private Date silverWait;
	/**银币鼓舞次数*/
	private int silverCnt;
	/**创建时间*/
	private Date createTime;
	/***/
	private Date updateTime;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public String getRoleName(){
		return this.roleName;
	}
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	public int getTotalHurt(){
		return this.totalHurt;
	}
	public void setTotalHurt(int totalHurt){
		this.totalHurt=totalHurt;
	}
	public int getHurtAdd(){
		return this.hurtAdd;
	}
	public void setHurtAdd(int hurtAdd){
		this.hurtAdd=hurtAdd;
	}
	public int getLiveCnt(){
		return this.liveCnt;
	}
	public void setLiveCnt(int liveCnt){
		this.liveCnt=liveCnt;
	}
	public Date getBattleWait(){
		return this.battleWait;
	}
	public void setBattleWait(Date battleWait){
		this.battleWait=battleWait;
	}
	public Date getSilverWait(){
		return this.silverWait;
	}
	public void setSilverWait(Date silverWait){
		this.silverWait=silverWait;
	}
	public int getSilverCnt(){
		return this.silverCnt;
	}
	public void setSilverCnt(int silverCnt){
		this.silverCnt=silverCnt;
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
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public int getBattleCnt() {
		return battleCnt;
	}
	public void setBattleCnt(int battleCnt) {
		this.battleCnt = battleCnt;
	}

}