/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import com.mdy.dzs.game.dao.RoleChannelDAO;
import com.mdy.dzs.game.domain.role.RoleChannel;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * 经脉dao缓存
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年11月14日  下午2:39:03
 */
public class CacheRoleChannelDAO extends RoleChannelDAO {

	private static final String ID_KEY_PREFIX ="RCH";
	private SafeCache safeCache ;
	

	public CacheRoleChannelDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getIdKey(int id){
		return ID_KEY_PREFIX+id;
	}
	
	@Override
	public void add(RoleChannel rc) {
		super.add(rc);
		safeCache.removeFromCache(getIdKey(rc.getRoleId()));
	}
	
	@Override
	public RoleChannel queryByAccount(int roleId) {
		String key = getIdKey(roleId);
		RoleChannel rch = (RoleChannel)safeCache.loadFromCache( key );
		if(rch == null){
			rch = super.queryByAccount(roleId);
			safeCache.putToCache(key, rch);
		}
		return rch;
	}
	
	@Override
	public void update(RoleChannel rc) {
		super.update(rc);
		safeCache.removeFromCache(getIdKey(rc.getRoleId()));
	}
}
