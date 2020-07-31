/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.RoleEquipDAO;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月27日  下午4:04:30
 */
public class CacheRoleEquipDAO extends RoleEquipDAO {

	private static final String ID_KEY_PREFIX ="RE";
	private SafeCache safeCache ;
	

	public CacheRoleEquipDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getIdKey(int id){
		return ID_KEY_PREFIX+id;
	}
	
	private String getRoldEquipListKey(int roleId){
		return ID_KEY_PREFIX+"_"+roleId;
	}
	
	private String getRoldEquipPosKey(int roleId,int rid,int pos){
		return ID_KEY_PREFIX+"_"+roleId+"_"+rid+"_"+pos;
	}
	
	@Override
	public Equip query(int id) {
		String key = getIdKey(id);
		Equip equip = (Equip)safeCache.loadFromCache( key );
		if(equip == null){
			equip = super.query(id);
			safeCache.putToCache(key, equip);
		}
		return super.query(id);
	}
	
	@Override
	public int add(Equip e) {
		int id = super.add(e);
		safeCache.removeFromCache(getRoldEquipListKey(e.getRoleId()));
		return id;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equip> queryListByAccount(int roleId) {
		String key = getRoldEquipListKey(roleId);
		List<Equip> equips = (List<Equip>)safeCache.loadFromCache( key );
		if(equips == null){
			equips = super.queryListByAccount(roleId);
			safeCache.putToCache(key, equips);
		}
		return equips;
	}
	
	
	@Override
	public void delete(Equip e) {
		super.delete(e);
		safeCache.removeFromCache(getRoldEquipListKey(e.getRoleId()));
		safeCache.removeFromCache(getIdKey(e.getId()));
		
	}
	
	@Override
	public void update(Equip e) {
		if(e== null) return;
		super.update(e);
		safeCache.removeFromCache(getRoldEquipListKey(e.getRoleId()));
		safeCache.removeFromCache(getIdKey(e.getId()));
	}
	
	@Override
	public void updateProps(Equip e) {
		super.updateProps(e);
		safeCache.removeFromCache(getRoldEquipListKey(e.getRoleId()));
		safeCache.removeFromCache(getIdKey(e.getId()));
		
	}
	
	@Override
	public void update(Equip e, int oldPos) {
		if(e== null) return;
		update(e);
		if(oldPos != 0) safeCache.removeFromCache(
					getRoldEquipPosKey(e.getRoleId(), e.getResId(), oldPos));
		if(e.getPos() != 0) safeCache.removeFromCache(
				getRoldEquipPosKey(e.getRoleId(), e.getResId(), e.getPos()));
	}
	
	@Override
	public Integer queryEquipByResIdPos(int roleId, int rid, int pos) {
		if(pos == 0) return null; 
		String key = getRoldEquipPosKey(roleId,rid,pos);
		Integer res = (Integer)safeCache.loadFromCache( key );
		if(res == null){
			res =super.queryEquipByResIdPos(roleId, rid, pos);
			if(res == null) res = -1;
			safeCache.putToCache(key, res);
		}
		return res == -1?null:res;
	}
}
