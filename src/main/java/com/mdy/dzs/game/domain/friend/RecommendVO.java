package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;

public class RecommendVO implements Serializable{
	/*{
		account : 2121212 ,
		name  : 小雪,
		battlepoint: 121212,  ---战斗力
		charm: 250, --魅力值
		isOnline: 1 --是否在线
		isApply
		level：*	
		resId:*	   --资源id
		cls：*	   --阶数
	}*/
	
	private static final long serialVersionUID = 1L;
	
	private String account;
	private String name;
	private int battlepoint;
	private int charm;
	private int isOnline;
	private int level;
	private int isApply;
	private int resId;
	private int cls;
	
	public RecommendVO(String account,String name,int battlepoint,int charm,int isOnline,int level,int isApply,int resId,int cls){
		this.account 	 = account;
		this.name		 = name;
		this.battlepoint = battlepoint;
		this.charm 		 = charm;
		this.isOnline 	 = isOnline;
		this.level 		 = level;
		this.isApply 	 = isApply;
		this.resId		 = resId;
		this.cls		 = cls;
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
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
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

	public int getIsApply() {
		return isApply;
	}

	public void setIsApply(int isApply) {
		this.isApply = isApply;
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
