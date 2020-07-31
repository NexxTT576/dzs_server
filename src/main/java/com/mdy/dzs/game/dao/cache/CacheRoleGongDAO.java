/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.RoleGongDAO;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月27日  下午4:04:30
 */
public class CacheRoleGongDAO extends RoleGongDAO {

	private static final String ID_KEY_PREFIX ="RG";
	private SafeCache safeCache ;
	

	public CacheRoleGongDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getIdKey(int id){
		return ID_KEY_PREFIX+id;
	}
	
	private String getRoldGongListKey(int roleId){
		return ID_KEY_PREFIX+"_"+roleId;
	}
	
	private String getRoldGongPosKey(int roleId,int rid,int pos){
		return ID_KEY_PREFIX+"_"+roleId+"_"+rid+"_"+pos;
	}
	
	@Override
	public RoleGong query(int id) {
		String key = getIdKey(id);
		RoleGong equip = (RoleGong)safeCache.loadFromCache( key ); 
		if(equip == null){
			equip = super.query(id);
			safeCache.putToCache(key, equip);
		}
		return super.query(id);
	}
	
	@Override
	public void add(RoleGong e) {
		super.add(e);
		safeCache.removeFromCache(getRoldGongListKey(e.getRoleId()));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleGong> queryListByAccount(int roleId) {
		String key = getRoldGongListKey(roleId);
		List<RoleGong> gongs = (List<RoleGong>)safeCache.loadFromCache( key );
		if(gongs == null){
			gongs = super.queryListByAccount(roleId);
			safeCache.putToCache(key, gongs);
		}
		return gongs;
	}
	
	
	@Override
	public void delete(RoleGong e) {
		super.delete(e);
		safeCache.removeFromCache(getRoldGongListKey(e.getRoleId()));
		safeCache.removeFromCache(getIdKey(e.getId()));
	}
	
	@Override
	public void update(RoleGong e) {
		if(e== null) return;
		super.update(e);
		safeCache.removeFromCache(getRoldGongListKey(e.getRoleId()));
		safeCache.removeFromCache(getIdKey(e.getId()));
	}
	
	@Override
	public void update(RoleGong e, int oldPos) {
		if(e== null) return;
		update(e);
		if(oldPos != 0) safeCache.removeFromCache(
				getRoldGongPosKey(e.getRoleId(), e.getResId(), oldPos));
		if(e.getPos() != 0) safeCache.removeFromCache(
				getRoldGongPosKey(e.getRoleId(), e.getResId(), e.getPos()));
	}
	
	@Override
	public Integer queryGongByResIdPos(int roleId, int rid, int pos) {
		if(pos == 0) return null; 
		String key = getRoldGongPosKey(roleId,rid,pos);
		Integer res = (Integer)safeCache.loadFromCache( key );
		if(res == null){
			res =super.queryGongByResIdPos(roleId, rid, pos);
			if(res == null) res = -1;
			safeCache.putToCache(key, res);
		}
		return res == -1?null:res;
	}
}
