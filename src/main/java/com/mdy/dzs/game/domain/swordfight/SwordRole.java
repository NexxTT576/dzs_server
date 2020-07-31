package com.mdy.dzs.game.domain.swordfight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 论剑角色模型
 * @author zhou
 *
 */
public class SwordRole implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/***/
	private String selfAccount;
	/***/
	private String enemyAccount;
	/***/
	private int resId;
	/**当前到第几层*/
	private int floor;
	/**战斗力*/
	private int combat;
	/**重置过几次*/
	private int resetCnt;
	/**元宝重置过几次*/
	private int goldResetCnt;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;
	private Date enterTime;
	private List<Integer> awards;
	/**角色名字*/
	private String name;
	/**货币的比例系数（金币重置一次后变为0.5）*/
	private float scale;
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getSelfAccount(){
		return this.selfAccount;
	}
	public void setSelfAccount(String selfAccount){
		this.selfAccount=selfAccount;
	}
	public String getEnemyAccount(){
		return this.enemyAccount;
	}
	public void setEnemyAccount(String enemyAccount){
		this.enemyAccount=enemyAccount;
	}
	public int getResId(){
		return this.resId;
	}
	public void setResId(int resId){
		this.resId=resId;
	}
	public int getFloor(){
		return this.floor;
	}
	public void setFloor(int floor){
		this.floor=floor;
	}
	public int getCombat(){
		return this.combat;
	}
	public void setCombat(int combat){
		this.combat=combat;
	}
	public int getResetCnt(){
		return this.resetCnt;
	}
	public void setResetCnt(int resetCnt){
		this.resetCnt=resetCnt;
	}
	public int getGoldResetCnt(){
		return this.goldResetCnt;
	}
	public void setGoldResetCnt(int goldResetCnt){
		this.goldResetCnt=goldResetCnt;
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
	public Date getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	public List<Integer> getAwards() {
		return awards;
	}
	public void setAwards(List<Integer> awards) {
		if(awards == null) awards = new ArrayList<Integer>();
		this.awards = awards;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}

}