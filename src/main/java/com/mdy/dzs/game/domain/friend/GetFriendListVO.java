package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;
import java.util.List;

public class GetFriendListVO implements Serializable{
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



	nailiList:  --领取耐力列表
	{
		{
			account : 2121212 ,
			name  : 小雪,
			battlepoint: 121212,  ---战斗力
			charm: 250, --魅力值
			time: 30, 	--几天前
			nailiNum: 1  --不传的话默认为1
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
	}
	recommendList: --推荐好友列表
		{
			{
				account : 2121212 ,
				name  : 小雪,
				battlepoint: 121212,  ---战斗力
				charm: 250, --魅力值
				isOnline: 1 --是否在线	1-在线 0 -离线
				level：*	
				isApply:*   --是否已发送 1-已发送 0-未发送
			}
			....
		} 

	restNailiNum:30,  --今日还可以领多少次耐力*/
	
	private static final long serialVersionUID = 1L;
	//好友列表
	private List<FriendListVO> friendList ;
	//领取耐力列表
	private List<NailiListVO> nailiList;
	//申请好友列表
	private List<RequestListVO> requestList;
	//推荐好友列表
	private List<RecommendVO> recommendList;
	//今日还可以领多少次耐力
	private int restNailiNum;
	
	public GetFriendListVO(List<FriendListVO> friendList,List<NailiListVO> nailiList,List<RequestListVO> requestList,int restNailiNum,List<RecommendVO> recommendList){
		this.friendList   = friendList;
		this.nailiList 	  = nailiList;
		this.requestList  = requestList;
		this.restNailiNum = restNailiNum;
		this.recommendList= recommendList; 
	}
	
	public List<FriendListVO> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<FriendListVO> friendList) {
		this.friendList = friendList;
	}
	public List<NailiListVO> getNailiList() {
		return nailiList;
	}
	public void setNailiList(List<NailiListVO> nailiList) {
		this.nailiList = nailiList;
	}
	public List<RequestListVO> getRequestList() {
		return requestList;
	}
	public void setRequestList(List<RequestListVO> requestList) {
		this.requestList = requestList;
	}
	public int getRestNailiNum() {
		return restNailiNum;
	}
	public void setRestNailiNum(int restNailiNum) {
		this.restNailiNum = restNailiNum;
	}

	public List<RecommendVO> getRecommendList() {
		return recommendList;
	}

	public void setRecommendList(List<RecommendVO> recommendList) {
		this.recommendList = recommendList;
	}
	
	
}
