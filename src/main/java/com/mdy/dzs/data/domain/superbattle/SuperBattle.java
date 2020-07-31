package com.mdy.dzs.data.domain.superbattle;

import java.io.Serializable;

/**
 * 系统布阵条件模型
 * @author zhou
 *
 */
public class SuperBattle implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**系统id（1-帮派副本）*/
	private int sysId;
	/**系统名*/
	private String sysName;
	/**男女条件(0-无条件；1-男；2-女）*/
	private int sex;
	/**等级条件*/
	private int level;
	/**是否记忆血量怒气（0-否；1-是）*/
	private int remHealth;
	/**是否已使用过不允许上阵（0-否；1-是）*/
	private int remUsed;
	/**是否已死亡不允许上阵（0-否；1-是）*/
	private int remDead;
	/**是否记忆阵容（0-否；1-是）*/
	private int remBattle;
	/***/
	private int initBattle;
	/**重置条件（1-每日）*/
	private int resetCond;
	/**重置参数*/
	private String resetPara;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getSysId(){
		return this.sysId;
	}
	public void setSysId(int sysId){
		this.sysId=sysId;
	}
	public String getSysName(){
		return this.sysName;
	}
	public void setSysName(String sysName){
		this.sysName=sysName;
	}
	public int getSex(){
		return this.sex;
	}
	public void setSex(int sex){
		this.sex=sex;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getRemHealth(){
		return this.remHealth;
	}
	public void setRemHealth(int remHealth){
		this.remHealth=remHealth;
	}
	public int getRemUsed(){
		return this.remUsed;
	}
	public void setRemUsed(int remUsed){
		this.remUsed=remUsed;
	}
	public int getRemDead(){
		return this.remDead;
	}
	public void setRemDead(int remDead){
		this.remDead=remDead;
	}
	public int getRemBattle(){
		return this.remBattle;
	}
	public void setRemBattle(int remBattle){
		this.remBattle=remBattle;
	}
	public int getInitBattle(){
		return this.initBattle;
	}
	public void setInitBattle(int initBattle){
		this.initBattle=initBattle;
	}
	public int getResetCond(){
		return this.resetCond;
	}
	public void setResetCond(int resetCond){
		this.resetCond=resetCond;
	}
	public String getResetPara(){
		return this.resetPara;
	}
	public void setResetPara(String resetPara){
		this.resetPara=resetPara;
	}
}