package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;
import java.util.List;

public class AcceptAllVO implements Serializable{
	/*friendList:
	{
		{
			account : 2121212 ,
			name  : 小雪,
			battlepoint: 121212,  ---战斗力
			charm: 250 --魅力值
			level：*   --等级
			isSendNaili:*	是否已赠送耐力	0-未赠送 1-已赠送 
			resId:*	   --资源id
			cls：*	   --阶数
		}
		....
	},
	requestList: --申请好友列表
	{
		{
			account : 2121212 ,
			name  : 小雪,
			battlepoint: 121212,  ---战斗力
			charm: 250, --魅力值
			isOnline: 1 ,--是否在线
			content: "你好，我是小雪小白小臭臭"
			resId:*	   --资源id
			cls：*	   --阶数
		}
		....	
		firendLimitType:* 0-正常 1-自己好友已满 2-对方好友已满
	}*/
	
	private static final long serialVersionUID = 1L;
	//好友列表
	private List<FriendListVO> friendList ;
	//申请好友列表
	private List<RequestListVO> requestList;
	//好友已满提示
	private int firendLimitType;
	
	public AcceptAllVO(List<FriendListVO> friendList,List<RequestListVO> requestList,int firendLimitType){
		this.friendList   	 = friendList;
		this.requestList  	 = requestList;
		this.firendLimitType = firendLimitType;
	}
	
	public List<FriendListVO> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<FriendListVO> friendList) {
		this.friendList = friendList;
	}
	public List<RequestListVO> getRequestList() {
		return requestList;
	}
	public void setRequestList(List<RequestListVO> requestList) {
		this.requestList = requestList;
	}

	public int getFirendLimitType() {
		return firendLimitType;
	}

	public void setFirendLimitType(int firendLimitType) {
		this.firendLimitType = firendLimitType;
	}
}
