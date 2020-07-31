/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.SwordRoleDAO;
import com.mdy.dzs.game.domain.swordfight.SwordRole;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * 论剑人物缓存
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年11月14日  下午3:52:45
 */
public class CacheSwordRoleDAO extends SwordRoleDAO {

	private static final String ID_KEY_PREFIX ="SWR";
	private static final String EMEMIES_KEY_PREFIX ="SWR_ESK";
	private static final String EMEMIE_KEY_PREFIX ="SWR_EK";
	private static final String EMEMIE_FLOOR_KEY_PREFIX ="SWR_EFK";
	
	private SafeCache safeCache ;
	

	public CacheSwordRoleDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getIdKey(int id){
		return ID_KEY_PREFIX+id;
	}
	
	private String getEmemiesKey(String selfAcc){
		return EMEMIES_KEY_PREFIX+selfAcc;
	}
	
	private String getEmemieKey(String selfAcc, String enemyAcc){
		return EMEMIE_KEY_PREFIX+selfAcc+"_"+enemyAcc;
	}
	
	private String getEmemieFloorKey(String selfAcc, int floor){
		return EMEMIE_FLOOR_KEY_PREFIX+selfAcc+"_"+floor;
	}
	
	@Override
	public SwordRole query(int id) {
		String key = getIdKey(id);
		SwordRole src = (SwordRole)safeCache.loadFromCache( key );
		if(src == null){
			src = super.query(id);
			safeCache.putToCache(key, src);
		}
		return src;
	}
	
	@Override
	public SwordRole queryByFloor(String selfAcc, int floor) {
		String key = getEmemieFloorKey(selfAcc, floor);
		SwordRole src = (SwordRole)safeCache.loadFromCache( key );
		if(src == null){
			src = super.queryByFloor(selfAcc, floor);
			safeCache.putToCache(key, src);
		}
		return src;
	}
	@Override
	public SwordRole queryBySelfAccount(String selfAcc, String enemyAcc) {
		String key = getEmemieKey(selfAcc, enemyAcc);
		SwordRole src = (SwordRole)safeCache.loadFromCache( key );
		if(src == null){
			src = super.queryBySelfAccount(selfAcc, enemyAcc);
			safeCache.putToCache(key, src);
		}
		return src;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SwordRole> queryEmemies(String selfAcc) {
		String key = getEmemiesKey(selfAcc);
		List<SwordRole> src = (List<SwordRole>)safeCache.loadFromCache( key );
		if(src == null){
			src = super.queryEmemies(selfAcc);
			safeCache.putToCache(key, src);
		}
		return src;
	}
	
	@Override
	public void update(SwordRole sr) {
		super.update(sr);
		safeCache.removeFromCache(getIdKey(sr.getId()));
		safeCache.removeFromCache(getEmemiesKey(sr.getSelfAccount()));
		safeCache.removeFromCache(getEmemieFloorKey(sr.getSelfAccount(), sr.getFloor()));
		safeCache.removeFromCache(getEmemieKey(sr.getSelfAccount(), sr.getEnemyAccount()));
	}
	@Override
	public void updateAwards(SwordRole sr) {
		super.updateAwards(sr);
		safeCache.removeFromCache(getIdKey(sr.getId()));
		safeCache.removeFromCache(getEmemiesKey(sr.getSelfAccount()));
		safeCache.removeFromCache(getEmemieFloorKey(sr.getSelfAccount(), sr.getFloor()));
		safeCache.removeFromCache(getEmemieKey(sr.getSelfAccount(), sr.getEnemyAccount()));
	}
	@Override
	public void updateFloor(SwordRole sr) {
		super.updateFloor(sr);
		safeCache.removeFromCache(getIdKey(sr.getId()));
		safeCache.removeFromCache(getEmemiesKey(sr.getSelfAccount()));
		safeCache.removeFromCache(getEmemieFloorKey(sr.getSelfAccount(), sr.getFloor()));
		safeCache.removeFromCache(getEmemieKey(sr.getSelfAccount(), sr.getEnemyAccount()));
	}
	
	@Override
	public void add(SwordRole sr) {
		super.add(sr);
		safeCache.removeFromCache(getEmemiesKey(sr.getSelfAccount()));
		safeCache.removeFromCache(getEmemieFloorKey(sr.getSelfAccount(), sr.getFloor()));
		safeCache.removeFromCache(getEmemieKey(sr.getSelfAccount(), sr.getEnemyAccount()));
	}
	
	@Override
	public void delete(SwordRole sr) {
		super.delete(sr);
		safeCache.removeFromCache(getIdKey(sr.getId()));
		safeCache.removeFromCache(getEmemiesKey(sr.getSelfAccount()));
		safeCache.removeFromCache(getEmemieFloorKey(sr.getSelfAccount(), sr.getFloor()));
		safeCache.removeFromCache(getEmemieKey(sr.getSelfAccount(), sr.getEnemyAccount()));
	}
}
