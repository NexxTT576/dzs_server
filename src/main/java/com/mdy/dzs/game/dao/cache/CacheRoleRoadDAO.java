/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import com.mdy.dzs.game.dao.RoleRoadDAO;
import com.mdy.dzs.game.domain.road.RoleRoad;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * 名将之路缓存
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年11月14日  下午2:23:54
 */
public class CacheRoleRoadDAO extends RoleRoadDAO {

	private static final String ID_KEY_PREFIX ="RR";
	private SafeCache safeCache ;
	

	public CacheRoleRoadDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getIdKey(int id){
		return ID_KEY_PREFIX+id;
	}
	
	@Override
	public void add(int roleId) {
		super.add(roleId);
		safeCache.removeFromCache(getIdKey(roleId));
	}
	
	@Override
	public RoleRoad query(int roleId) {
		String key = getIdKey(roleId);
		RoleRoad rr = (RoleRoad)safeCache.loadFromCache( key );
		if(rr == null){
			rr = super.query(roleId);
			safeCache.putToCache(key, rr);
		}
		return rr;
	}
	
	@Override
	public void update(RoleRoad rr) {
		super.update(rr);
		safeCache.removeFromCache(getIdKey(rr.getRoleId()));
	}
}
