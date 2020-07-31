package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;
import java.util.List;

public class GetNaiLiAllVO implements Serializable{
	/*nailiNum:*	--领取到的耐力数量
	restNailiNum：*  --剩余领取次数
	friendList:
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
	*/
	private static final long serialVersionUID = 1L;
	
	//好友列表
	private List<FriendListVO> friendList;
	//领取耐力列表
	private List<NailiListVO> nailiList;
	//领取到的耐力数
	private int nailiNum;
	//剩余领取次数
	private int restNailiNum;
	
	public GetNaiLiAllVO(List<FriendListVO> friendList,List<NailiListVO> nailiList,int nailiNum,int restNailiNum){
		this.friendList   = friendList;
		this.nailiList    = nailiList;
		this.nailiNum     = nailiNum;
		this.restNailiNum = restNailiNum;
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
	public int getNailiNum() {
		return nailiNum;
	}
	public void setNailiNum(int nailiNum) {
		this.nailiNum = nailiNum;
	}

	public int getRestNailiNum() {
		return restNailiNum;
	}

	public void setRestNailiNum(int restNailiNum) {
		this.restNailiNum = restNailiNum;
	}
	
	
	
}
