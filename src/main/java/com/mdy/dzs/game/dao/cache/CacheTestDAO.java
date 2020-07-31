package com.mdy.dzs.game.dao.cache;

import java.util.Date;

import com.mdy.dzs.game.dao.TestDAO;
import com.mdy.dzs.game.domain.Test;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheTestDAO extends TestDAO {

	private static final String ID_KEY_PREFIX = "IK";
	private SafeCache safeCache;

	/*
	 * 注：执行resetScoutEnergy()后，缓存不能及时更新，只能等待时间过期
	 */
	public CacheTestDAO() {
		safeCache = new SafeCache(1800);
	}

	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource(cacheResource);
	}

	private String getKey() {
		int day = DateUtil.getDay(new Date());
		return ID_KEY_PREFIX + "/" + day;
	}

	/**
	 * 添加
	 * 
	 * @param Test
	 */
	public void add(Test t) {
		super.add(t);
		safeCache.removeFromCache(getKey());
	}

	/**
	 * 更新
	 * 
	 * @param Test
	 */
	public void update(Test t) {
		super.update(t);
		safeCache.removeFromCache(getKey());
	}

}
