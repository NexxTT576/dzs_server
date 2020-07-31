package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;

public class NailiListVO implements Serializable{
	/*{
		account : 2121212 ,
		name  : 小雪,
		battlepoint: 121212,  ---战斗力
		charm: 250, --魅力值
		time: 30, 	--几天前
		nailiNum: 1  --不传的话默认为1
		resId:*	   --资源id
		cls：*	   --阶数
		level:*	
	}*/
	
	private static final long serialVersionUID = 1L;
	
	//账号
	private String account;
	//名字
	private String name;
	//战斗力
	private int battlepoint;
	//魅力值
	private int charm;
	//几天前
	private int time;
	//耐力数
	private int nailiNum;
	//资源id
	private int resId;
	//阶数
	private int cls;	
	//等级
	private int level;
	
	public NailiListVO(){}
	public NailiListVO(String account,String name,int battlepoint,int charm,int time,int nailiNum,int resId,int cls,int level){
		this.account 		= account;
		this.name 			= name;
		this.battlepoint 	= battlepoint;
		this.charm 			= charm;
		this.time 			= time;
		this.nailiNum 		= nailiNum;
		this.resId 		 	= resId;
		this.cls			= cls;
		this.level          = level;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBattlepoint() {
		return battlepoint;
	}
	public void setBattlepoint(int battlepoint) {
		this.battlepoint = battlepoint;
	}
	public int getCharm() {
		return charm;
	}
	public void setCharm(int charm) {
		this.charm = charm;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getNailiNum() {
		return nailiNum;
	}
	public void setNailiNum(int nailiNum) {
		this.nailiNum = nailiNum;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	

}
