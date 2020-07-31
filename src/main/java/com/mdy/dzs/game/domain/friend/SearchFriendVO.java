package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;
import java.util.List;

public class SearchFriendVO implements Serializable{
	/*searchList:
	{
		flag:*,
		{
			account : 2121212 ,
			name  : 小雪,
			battlepoint: 121212,  ---战斗力
			charm: 250, --魅力值
			isOnline: 1 --是否在线
			resId:*	   --资源id
			cls：*	   --阶数
		}
		....
	}*/
	private static final long serialVersionUID = 1L;
	
	private int flag;
	private List<RecommendVO> searchList;
	
	public SearchFriendVO(int flag, List<RecommendVO> searchList){
		this.flag = flag;
		this.searchList = searchList;
	}
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public List<RecommendVO> getSearchList() {
		return searchList;
	}
	public void setSearchList(List<RecommendVO> searchList) {
		this.searchList = searchList;
	}
}
