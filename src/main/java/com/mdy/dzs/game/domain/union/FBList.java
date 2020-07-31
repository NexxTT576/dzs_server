package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

public class FBList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2404181726223681945L;
	private int fbid ;
	private int state;
	private int rewardState;
	private int leftHp;
	public static  FBList ValueOf(UnionFB unionFB,int isGet){
		FBList fbList = new FBList();
		fbList.fbid = unionFB.getFbId();
		fbList.leftHp = unionFB.getLeftHp();
		fbList.rewardState = isGet;
		fbList.state = unionFB.getState();
		return fbList;
	}
	public int getFbid() {
		return fbid;
	}

	public void setFbid(int fbid) {
		this.fbid = fbid;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getRewardState() {
		return rewardState;
	}
	public void setRewardState(int rewardState) {
		this.rewardState = rewardState;
	}
	public int getLeftHp() {
		return leftHp;
	}
	public void setLeftHp(int leftHp) {
		this.leftHp = leftHp;
	}
	
}
