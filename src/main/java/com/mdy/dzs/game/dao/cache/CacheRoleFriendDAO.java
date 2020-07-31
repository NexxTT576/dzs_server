package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.RoleFriendDAO;
import com.mdy.dzs.game.domain.friend.RoleFriend;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheRoleFriendDAO extends RoleFriendDAO {
	
	private static final String ID_KEY_PREFIX ="ROLEFRIEND";
	private SafeCache safeCache ;
	
	//时效
	public CacheRoleFriendDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}
	
	
	//好友列表key
	private String getFriendListKey(int roleId) {
		return ID_KEY_PREFIX+roleId+"friendList";
	}
	//好友数key
	private String getFriendNumKey(int roleId){
		return ID_KEY_PREFIX+roleId+"friendNum";
	}
	
	//查询好友列表
	@SuppressWarnings("unchecked")
	public List<RoleFriend> queryFriendList(int roleId){
		String friendListKey        = getFriendListKey(roleId);		
		List<RoleFriend> friendList = (List<RoleFriend>) safeCache.loadFromCache( friendListKey );
		if(friendList == null){
			friendList = super.queryFriendList(roleId);
			safeCache.putToCache(friendListKey, friendList);
		}
		return friendList;	
	}
	
	//查询好友数
	public int queryCountByRoelId(int roleId){
		String friendNumKey = getFriendNumKey(roleId);
		Object friendNum 	= safeCache.loadFromCache( friendNumKey );
		if(friendNum == null){
			friendNum = super.queryCountByRoelId(roleId);
			safeCache.putToCache(friendNumKey, friendNum);
		}
		return (Integer) friendNum;
	}
	//更新好友状态 
	public void updateFriendStatus(int roleId, int friendId, String content, int status){
		super.updateFriendStatus(roleId, friendId, content, status);
		//同意好友||断交 -- 影响好友列表 和好友数目
		if(status == RoleFriend.FRIEND_STATUS_AGREE || status == RoleFriend.FRIEND_STATUS_BREAK){
			safeCache.removeFromCache(getFriendListKey(roleId));
			safeCache.removeFromCache(getFriendNumKey(roleId));
		}
	}
	//添加记录
	public void add(int roleId, int friendId, String content, int status){
		super.add(roleId, friendId, content, status);
		if(status == RoleFriend.FRIEND_STATUS_AGREE ){
			safeCache.removeFromCache(getFriendListKey(roleId));
			safeCache.removeFromCache(getFriendNumKey(roleId));
		}		
	}
	//更新已赠送时间
	public void updataSendNailiTime(RoleFriend curData) {
		super.updataSendNailiTime(curData);
		safeCache.removeFromCache(getFriendListKey(curData.getRoleId()));
	}
	
}
