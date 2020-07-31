/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import com.mdy.dzs.game.dao.RoleCardEffectDAO;
import com.mdy.dzs.game.domain.card.RoleCardEffect;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月27日  下午4:23:30
 */
public class CacheRoleCardEffectDAO extends RoleCardEffectDAO {

	private static final String ID_KEY_PREFIX ="RCE";
	private SafeCache safeCache ;
	
	public CacheRoleCardEffectDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getIdKey(int roleId,int resId){
		return ID_KEY_PREFIX+roleId+"/"+resId;
	}
	
	@Override
	public void add(RoleCardEffect rce) {
		super.add(rce);
		safeCache.removeFromCache(getIdKey(rce.getRoleId(),rce.getCardId()));
	}
	
	@Override
	public RoleCardEffect queryByRoleIdCardId(int roleId, int cardId) {
		String key = getIdKey(roleId,cardId);
		RoleCardEffect effect = (RoleCardEffect)safeCache.loadFromCache( key );
		if(effect == null){
			effect = super.queryByRoleIdCardId(roleId, cardId);
			safeCache.putToCache(key, effect);
		}
		return effect;
	}
	
	@Override
	public void update(RoleCardEffect rce) {
		super.update(rce);
		safeCache.removeFromCache(getIdKey(rce.getRoleId(),rce.getCardId()));
	}
}
