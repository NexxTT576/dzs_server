/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.SwordRoleCardDAO;
import com.mdy.dzs.game.domain.swordfight.SwordRoleCard;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * 论剑玩家卡牌数据缓存
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年11月14日  下午3:12:34
 */
public class CacheSwordRoleCardDAO extends SwordRoleCardDAO {

	private static final String ID_KEY_PREFIX ="SWRC";
	private SafeCache safeCache ;
	

	public CacheSwordRoleCardDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getIdKey(int id){
		return ID_KEY_PREFIX+id;
	}
	private String getCardIdTypeKey(int id,int type){
		return ID_KEY_PREFIX+id+"_t_"+type;
	}
	private String getAccTypeKey(String acc,int type){
		return ID_KEY_PREFIX+"_a_"+acc+"_t_"+type;
	}
	
	private String getSwordRoleIdKey(int swordRoleId){
		return ID_KEY_PREFIX+"_SRID_"+swordRoleId;
	}
	
	
	@Override
	public SwordRoleCard queryByRoleCardId(int queryByRoleCardId, int type) {
		String key = getCardIdTypeKey(queryByRoleCardId, type);
		SwordRoleCard src = (SwordRoleCard)safeCache.loadFromCache( key );
		if(src == null){
			src = super.queryByRoleCardId(queryByRoleCardId,type);
			safeCache.putToCache(key, src);
		}
		return src;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SwordRoleCard> queryBySwordRoleId(int swordRoleId) {
		String key = getSwordRoleIdKey(swordRoleId);
		List<SwordRoleCard> src = (List<SwordRoleCard>)safeCache.loadFromCache( key );
		if(src == null){
			src = super.queryBySwordRoleId(swordRoleId);
			safeCache.putToCache(key, src);
		}
		return src;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SwordRoleCard> queryByAccount(String account, int type) {
		String key = getAccTypeKey(account, type);
		List<SwordRoleCard> src = (List<SwordRoleCard>)safeCache.loadFromCache( key );
		if(src == null){
			src = super.queryByAccount(account,type);
			safeCache.putToCache(key, src);
		}
		return src;
	}
	
	@Override
	public SwordRoleCard query(int id) {
		String key = getIdKey(id);
		SwordRoleCard src = (SwordRoleCard)safeCache.loadFromCache( key );
		if(src == null){
			src = super.query(id);
			safeCache.putToCache(key, src);
		}
		return src;
	}
	
	@Override
	public void update(SwordRoleCard src) {
		super.update(src);
		safeCache.removeFromCache(getIdKey(src.getId()));
		safeCache.removeFromCache(getSwordRoleIdKey(src.getSword_role_id()));
		safeCache.removeFromCache(getCardIdTypeKey(src.getRole_card_id(),src.getType()));
		safeCache.removeFromCache(getAccTypeKey(src.getAccount(),src.getType()));
	}
	
	@Override
	public void updateLifeAnger(SwordRoleCard src) {
		super.updateLifeAnger(src);
		safeCache.removeFromCache(getIdKey(src.getId()));
		safeCache.removeFromCache(getSwordRoleIdKey(src.getSword_role_id()));
		safeCache.removeFromCache(getCardIdTypeKey(src.getRole_card_id(),src.getType()));
		safeCache.removeFromCache(getAccTypeKey(src.getAccount(),src.getType()));
	}
	
	@Override
	public void delete(SwordRoleCard src) {
		super.delete(src);
		safeCache.removeFromCache(getIdKey(src.getId()));
		safeCache.removeFromCache(getSwordRoleIdKey(src.getSword_role_id()));
		safeCache.removeFromCache(getCardIdTypeKey(src.getRole_card_id(),src.getType()));
		safeCache.removeFromCache(getAccTypeKey(src.getAccount(),src.getType()));
	}
	
	@Override
	public void add(SwordRoleCard src) {
		super.add(src);
		safeCache.removeFromCache(getSwordRoleIdKey(src.getSword_role_id()));
		safeCache.removeFromCache(getCardIdTypeKey(src.getRole_card_id(),src.getType()));
		safeCache.removeFromCache(getAccTypeKey(src.getAccount(),src.getType()));
	}
}
