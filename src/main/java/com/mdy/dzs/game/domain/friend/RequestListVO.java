package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;

public class RequestListVO implements Serializable{
/*{
		account : 2121212 ,
		name  : 小雪,
		battlepoint: 121212,  ---战斗力
		charm: 250, --魅力值
		isOnline: 1 ,--是否在线
		content: "你好，我是小雪小白小臭臭"
		resId:*	   --资源id
		cls：*	   --阶数
		level:*	   --等级
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
	//是否在线
	private int isOnline;
	//申请内容
	private String content;
	//资源id
	private int resId;
	//阶数
	private int cls;
	//等级
	private int level;
	
	public RequestListVO(){}
	public RequestListVO(String account,String name,int battlepoint,int charm,int isOnline,String content,int resId,int cls,int level){
		this.account 	 = account;
		this.name 		 = name;
		this.battlepoint = battlepoint;
		this.charm 		 = charm;
		this.isOnline 	 = isOnline;
		this.content 	 = content;		
		this.resId 		 = resId;
		this.cls		 = cls;
		this.level		 = level;
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
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
