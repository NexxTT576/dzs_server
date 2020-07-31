package com.mdy.dzs.game.domain.role;

import java.io.Serializable;

public class HeartOtherObjVO implements Serializable {
	/*otherObj:{
		serverTime:*,	//服务器时间戳	
		friend:*,		//好友是否有未处理信息  聊天||领取耐力||好友申请   0-无 1-有
	 }	*/
	private static final long serialVersionUID = 1L;
	
	private Long serverTime;
	private int friend;
	private int applyNum;
	
	public Long getServerTime() {
		return serverTime;
	}
	public void setServerTime(Long serverTime) {
		this.serverTime = serverTime;
	}
	public int getFriend() {
		return friend;
	}
	public void setFriend(int friend) {
		this.friend = friend;
	}
	public int getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}
	
}
