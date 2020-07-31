/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.RoleCardDAO;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * 人物卡牌缓存
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月26日  下午5:49:05
 */
public class CacheRoleCardDAO extends RoleCardDAO {

	private static final String ID_KEY_PREFIX ="RC";
	private SafeCache safeCache ;
	

	public CacheRoleCardDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getIdKey(int id){
		return ID_KEY_PREFIX+id;
	}
	private String getRoldCardListKey(int roleId){
		return ID_KEY_PREFIX+"_"+roleId;
	}
	
	@Override
	public void add(RoleCard rc) {
		super.add(rc);
		safeCache.removeFromCache(getRoldCardListKey(rc.getRoleId()));
	}
	
	@Override
	public RoleCard query(int id) {
		String key = getIdKey(id);
		RoleCard rcard = (RoleCard)safeCache.loadFromCache( key );
		if(rcard == null){
			rcard = super.query(id);
			safeCache.putToCache(key, rcard);
		}
		return rcard;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleCard> queryListByRoleId(int roleId) {
		String key = getRoldCardListKey(roleId);
		List<RoleCard> rcards = (List<RoleCard>)safeCache.loadFromCache( key );
		if(rcards == null){
			rcards = super.queryListByRoleId(roleId);
			safeCache.putToCache(key, rcards);
		}
		return rcards;
	}
	
	
	@Override
	public void update(RoleCard rc) {
		if(rc == null) return;
		super.update(rc);
		safeCache.removeFromCache(getRoldCardListKey(rc.getRoleId()));
		safeCache.removeFromCache(getIdKey(rc.getId()));
	}
	
	@Override
	public void updateLevel(RoleCard rc) {
		if(rc == null) return;
		super.updateLevel(rc);
		safeCache.removeFromCache(getRoldCardListKey(rc.getRoleId()));
		safeCache.removeFromCache(getIdKey(rc.getId()));
	}
	
	@Override
	public void updateLock(RoleCard rc) {
		if(rc == null) return;
		super.updateLock(rc);
		safeCache.removeFromCache(getRoldCardListKey(rc.getRoleId()));
		safeCache.removeFromCache(getIdKey(rc.getId()));
	}
	
	@Override
	public void delete(RoleCard rc) {
		super.delete(rc);
		safeCache.removeFromCache(getRoldCardListKey(rc.getRoleId()));
		safeCache.removeFromCache(getIdKey(rc.getId()));
	}
	
	
	@Override
	public void deleteCards(int roleId, List<RoleCard> rcList) {
		super.deleteCards(roleId, rcList);
		safeCache.removeFromCache(getRoldCardListKey(roleId));
		for (RoleCard rc : rcList) {
			safeCache.removeFromCache(getIdKey(rc.getId()));
		}
	}
	
	@Override
	public void update(RoleCard updateObj, int oldPos) {
		if(updateObj== null) return;
		super.update(updateObj, oldPos);
		safeCache.removeFromCache(getRoldCardListKey(updateObj.getRoleId()));
		safeCache.removeFromCache(getIdKey(updateObj.getId()));
	}
	
	@Override
	public void updateMainExp(int roleId,int cardId, int exp, int level) {
		super.updateMainExp(roleId,cardId, exp, level);
		safeCache.removeFromCache(getRoldCardListKey(roleId));
		safeCache.removeFromCache(getIdKey(cardId));
	}
	
	@Override
	public void updateBattle(List<Integer> battle ,int id ,int roleId){
		super.updateBattle(battle, id ,roleId);
		safeCache.removeFromCache(getRoldCardListKey(roleId));
		safeCache.removeFromCache(getIdKey(id));
	}
}
