package com.mdy.dzs.game.domain.boss;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * boss表结构模型
 * @author 白雪林
 *
 */
public class BossBattle implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**活动id*/
	private int actId;
	/**boss名*/
	private String name;
	/**boss等级*/
	private int level;
	/**boss总血量*/
	private int lifeTotal;
	/**boss当前血量*/
	private int life;
	/**击杀者*/
	private int killPlayer;
	/**是否已经发奖*/
	private int award;
	/**排名前十玩家*/
	private List<BossTopTenVO> topTen;
	/**活动结束时间*/
	private Date endTime;
	/**活动开始时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;

	public int getActId(){
		return this.actId;
	}
	public void setActId(int l){
		this.actId=l;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getLifeTotal(){
		return this.lifeTotal;
	}
	public void setLifeTotal(int lifeTotal){
		this.lifeTotal=lifeTotal;
	}
	public int getLife(){
		return this.life;
	}
	public void setLife(int life){
		this.life=life;
	}
	public int getKillPlayer(){
		return this.killPlayer;
	}
	public void setKillPlayer(int killPlayer){
		this.killPlayer=killPlayer;
	}
	public Date getEndTime(){
		return this.endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime=endTime;
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
	public List<BossTopTenVO> getTopTen() {
		return topTen;
	}
	public void setTopTen(List<BossTopTenVO> topTen) {
		this.topTen = topTen;
	}
	public int getAward() {
		return award;
	}
	public void setAward(int award) {
		this.award = award;
	}

}