/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import com.mdy.dzs.game.dao.RoleBattleDAO;
import com.mdy.dzs.game.domain.battle.RoleBattle;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * 人物战斗数据缓存
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年11月14日  下午2:18:06
 */
public class CacheRoleBattleDAO extends RoleBattleDAO {

	private static final String ID_KEY_PREFIX ="RB";
	private SafeCache safeCache ;
	

	public CacheRoleBattleDAO() {
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
	public RoleBattle query(int roleId) {
		String key = getIdKey(roleId);
		RoleBattle rb = (RoleBattle)safeCache.loadFromCache( key );
		if(rb == null){
			rb = super.query(roleId);
			safeCache.putToCache(key, rb);
		}
		return rb;
	}
	
	@Override
	public void resetBattle(RoleBattle r) {
		super.resetBattle(r);
		safeCache.putToCache(getIdKey(r.getRoleId()),r);
	}
	
	@Override
	public void updateBattle(RoleBattle rb) {
		super.updateBattle(rb);
		safeCache.removeFromCache(getIdKey(rb.getRoleId()));
	}
}
