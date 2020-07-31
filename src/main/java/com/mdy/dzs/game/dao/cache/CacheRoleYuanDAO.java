/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.RoleYuanDAO;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月27日  下午4:04:30
 */
public class CacheRoleYuanDAO extends RoleYuanDAO {

	private static final String ID_KEY_PREFIX ="RY";
	private SafeCache safeCache ;
	

	public CacheRoleYuanDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getIdKey(int id){
		return ID_KEY_PREFIX+id;
	}
	
	private String getRoldYuanListKey(int roleId){
		return ID_KEY_PREFIX+"_"+roleId;
	}
	
	private String getRoldYuanPosKey(int roleId,int pos){
		return ID_KEY_PREFIX+"_"+roleId+"_"+pos;
	}
	
	@Override
	public RoleYuan query(int id) {
		String key = getIdKey(id);
		RoleYuan equip = (RoleYuan)safeCache.loadFromCache( key ); 
		if(equip == null){
			equip = super.query(id);
			safeCache.putToCache(key, equip);
		}
		return super.query(id);
	}
	
	@Override
	public void addRtKey(RoleYuan e) {
		super.addRtKey(e);
		safeCache.removeFromCache(getRoldYuanListKey(e.getRoleId()));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleYuan> queryListByAccount(int roleId) {
		String key = getRoldYuanListKey(roleId);
		List<RoleYuan> gongs = (List<RoleYuan>)safeCache.loadFromCache( key );
		if(gongs == null){
			gongs = super.queryListByAccount(roleId);
			safeCache.putToCache(key, gongs);
		}
		return gongs;
	}
	
	
	@Override
	public void delete(RoleYuan e) {
		super.delete(e);
		safeCache.removeFromCache(getRoldYuanListKey(e.getRoleId()));
		safeCache.removeFromCache(getIdKey(e.getId()));
	}
	
	@Override
	public void update(RoleYuan e) {
		if(e== null) return;
		super.update(e);
		safeCache.removeFromCache(getRoldYuanListKey(e.getRoleId()));
		safeCache.removeFromCache(getIdKey(e.getId()));
		if(e.getPos() != 0) safeCache.removeFromCache(
				getRoldYuanPosKey(e.getRoleId(), e.getPos()));
	}
	
	@Override
	public void update(RoleYuan e, int oldPos) {
		if(e== null) return;
		update(e);
		if(oldPos != 0) safeCache.removeFromCache(
				getRoldYuanPosKey(e.getRoleId(), oldPos));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleYuan> queryListByAccPos(int roleId, int pos) {
		String key = getRoldYuanPosKey(roleId,pos);
		List<RoleYuan> res = (List<RoleYuan>)safeCache.loadFromCache( key );
		if(res == null){
			res =super.queryListByAccPos(roleId, pos);
			safeCache.putToCache(key, res);
		}
		return res;
	}
}
