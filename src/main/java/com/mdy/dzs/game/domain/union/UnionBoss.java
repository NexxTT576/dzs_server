package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.boss.BossTopTenVO;


/**
 * 青龙殿boss战模型
 * @author 白雪林
 *
 */
public class UnionBoss implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**青龙殿活动id*/
	private int actId;
	/**帮派id*/
	private int unionId;
	/**boss名*/
	private String name;
	/**等级*/
	private int level;
	/**boss总血量*/
	private int lifeTotal;
	/**boss当前血量*/
	private int life;
	/**击杀者*/
	private int killer;
	/**排行前十*/
	private List<BossTopTenVO> topTen;
	/**结束时间*/
	private Date endTime;
	/**是否已发奖 0-未发 1-已发*/
	private int award;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;

	public int getActId(){
		return this.actId;
	}
	public void setActId(int actId){
		this.actId=actId;
	}
	public int getUnionId(){
		return this.unionId;
	}
	public void setUnionId(int unionId){
		this.unionId=unionId;
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
	public int getKiller(){
		return this.killer;
	}
	public void setKiller(int killer){
		this.killer=killer;
	}
	public List<BossTopTenVO> getTopTen(){
		return this.topTen;
	}
	public void setTopTen(List<BossTopTenVO> topTen){
		this.topTen=topTen;
	}
	public Date getEndTime(){
		return this.endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}
	public int getAward(){
		return this.award;
	}
	public void setAward(int award){
		this.award=award;
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