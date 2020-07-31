package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.UnionDynamicDAO;
import com.mdy.dzs.game.domain.union.UnionDynamic;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheUnionDynamicDAO extends UnionDynamicDAO {

	private static final String ID_KEY_PREFIX = "UD";

	private SafeCache safeCache;

	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource(cacheResource);
	}

	public CacheUnionDynamicDAO() {
		safeCache = new SafeCache(1800);
	}

	private String getIdKey(int roleId) {
		return ID_KEY_PREFIX + roleId;
	}
	//添加动态
	@Override
	public void createDynmic(UnionDynamic unionDynamic) {
		super.createDynmic(unionDynamic);
		safeCache.removeFromCache(getIdKey(unionDynamic.getUnionId()));
	}
	//添加青龙堂动态
	@Override
	public void createDynmicDragon(UnionDynamic unionDynamic) {
		super.createDynmicDragon(unionDynamic);
		safeCache.removeFromCache(getIdKey(unionDynamic.getUnionId()));
	}
	//查询列表
	@SuppressWarnings("unchecked")
	@Override
	public List<UnionDynamic> queryDynamicList(int roleId) {
		String key = getIdKey(roleId);
		List<UnionDynamic> dynamicList = (List<UnionDynamic>) safeCache.loadFromCache(key);
		if (dynamicList == null) {
			dynamicList = super.queryDynamicList(roleId);
			safeCache.putToCache(key, dynamicList);
		}
		return dynamicList;
	}
	//删除动态
	@Override
	public void deleteDynamic(UnionDynamic unionDynamic){
		super.deleteDynamic(unionDynamic);
		safeCache.removeFromCache(getIdKey(unionDynamic.getUnionId()));
	}
}
