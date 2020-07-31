/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import java.util.Arrays;
import java.util.List;

import com.mdy.dzs.game.dao.PacketDAO;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.sharp.container.biz.BizException;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月26日  下午6:04:42
 */
public class CacheRolePacketDAO extends PacketDAO {

	//7,11,12
	//9,10
	private static final String ID_KEY_PREFIX ="RI";
	private static final String LIST_COUNT_PREFIX ="RI_LC_";
	private SafeCache safeCache ;
	
	private List<Integer> tItems = Arrays.asList(7,11,12);
	private List<Integer> tGongItems = Arrays.asList(9,10);
	

	public CacheRolePacketDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getListCountKey(int roleid,int type){
		if(tItems.indexOf(type) != -1) type = tItems.get(0);
		if(tGongItems.indexOf(type) != -1) type = tGongItems.get(0);
		return LIST_COUNT_PREFIX+type+"/"+roleid;
	}
	
	private String getResIdKey(int roleid,int itemid,int type){
		if(tItems.indexOf(type) != -1) type = tItems.get(0);
		if(tGongItems.indexOf(type) != -1) type = tGongItems.get(0);
		return ID_KEY_PREFIX+type+"/Rid"+roleid+"/Res"+itemid;
	}

	private String getRoldItemListKey(int roleIdount,int type) {
		if(tItems.indexOf(type) != -1) type = tItems.get(0);
		if(tGongItems.indexOf(type) != -1) type = tGongItems.get(0);
		return ID_KEY_PREFIX+type+"_"+roleIdount;
	}
	
	@Override
	public int add(RoleItem ri,int type) throws BizException {
		int id = super.add(ri,type);
		safeCache.removeFromCache(getRoldItemListKey(ri.getRoleId(),type));
		safeCache.removeFromCache(getListCountKey(ri.getRoleId(), type));
		return id;
	}
	
	@Override
	public void updateBoxPro(int roleid, int pronum, int itemid) {
		super.updateBoxPro(roleid, pronum, itemid);
		safeCache.removeFromCache(getResIdKey(roleid,itemid,Packet.POS_BAG));
	}

	@Override
	public void updateBoxProTo(int roleid, int pronum, int itemid) {
		super.updateBoxProTo(roleid, pronum, itemid);
		safeCache.removeFromCache(getResIdKey(roleid,itemid,Packet.POS_BAG));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleItem> queryListByAccount(int roleId,int t) throws BizException {
		String key = getRoldItemListKey(roleId,t);
		List<RoleItem> list = (List<RoleItem>)safeCache.loadFromCache( key );
		if(list == null){
			list = super.queryListByAccount(roleId,t);
			safeCache.putToCache(key, list);
		}
		return list;
	}
	
	@Override
	public RoleItem queryByAccItemId(int roleId, int itemId, int type)
			throws BizException {
		String key = getResIdKey(roleId, itemId, type);
		RoleItem res = (RoleItem)safeCache.loadFromCache( key );
		if(res == null){
			res = super.queryByAccItemId(roleId,itemId,type);
			safeCache.putToCache(key, res);
		}
		return res;
	}
	
	@Override
	public int queryCountByAccount(int roleId, int t) throws BizException {
		String key = getListCountKey(roleId, t);
		Integer res = (Integer)safeCache.loadFromCache( key );
		if(res == null){
			res = super.queryCountByAccount(roleId,t);
			safeCache.putToCache(key, res);
		}
		return res;
	}
	
	@Override
	public void delete(RoleItem ri,int t) throws BizException {
		super.delete(ri,t);
		safeCache.removeFromCache(getRoldItemListKey(ri.getRoleId(),t));
		safeCache.removeFromCache(getResIdKey(ri.getRoleId(),ri.getItemId(),t));
		safeCache.removeFromCache(getListCountKey(ri.getRoleId(), t));
	}

	@Override
	public void update(RoleItem ri,int t) throws BizException {
		super.update(ri,t);
		safeCache.removeFromCache(getRoldItemListKey(ri.getRoleId(),t));
		safeCache.removeFromCache(getResIdKey(ri.getRoleId(),ri.getItemId(),t));
	}
	
}
