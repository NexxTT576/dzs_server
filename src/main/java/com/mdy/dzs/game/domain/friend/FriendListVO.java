package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;

public class FriendListVO implements Serializable{
	/*{
		account : 2121212 ,
		name  : 小雪,
		battlepoint: 121212,  ---战斗力
		charm: 250 --魅力值
		level：*   --等级
		isSendNaili:*	是否已赠送耐力	0-未赠送 1-已赠送 
		resId:*	   --资源id
		cls：*	   --阶数
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
	//等级
	private int level;
	//是否已赠送耐力
	private int isSendNaili;
	//资源id
	private int resId;
	//阶数
	private int cls;
	
	public FriendListVO(){}
	public FriendListVO(String account,String name,int battlepoint,int charm,int level,int isSendNaili,int resId,int cls){
		this.account 		= account;
		this.name 			= name;
		this.battlepoint 	= battlepoint;
		this.charm 			= charm;
		this.level 			= level;
		this.isSendNaili 	= isSendNaili;
		this.resId			= resId;
		this.cls			= cls;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getIsSendNaili() {
		return isSendNaili;
	}
	public void setIsSendNaili(int isSendNaili) {
		this.isSendNaili = isSendNaili;
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
	

}
