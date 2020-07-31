package com.mdy.dzs.game.dao.cache;

import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.container.res.cache.CacheException;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * 
 */
public class SafeCache {
	private static Logger logger = LoggerFactory.get(SafeCache.class);
	private int expireTime;
	private CacheResource cacheResource;

	public SafeCache(int expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * @param cacheResource the cacheResource to set
	 */
	public void setCacheResource(CacheResource cacheResource) {
		this.cacheResource = cacheResource;
	}

	//
	public Object loadFromCache(String key) {
		if (cacheResource == null)
			return null;
		try {
			return cacheResource.get(key);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	//
	public void putToCache(String key, Object v) {
		if (v == null || cacheResource == null) {
			return;
		}
		try {
			cacheResource.set(key, expireTime, v);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
		}
	}

	//
	public void putToCache(String key, Object v, int time) {
		if (v == null || cacheResource == null) {
			return;
		}
		try {
			cacheResource.set(key, time, v);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
		}
	}

	//
	public void removeFromCache(String key) {
		if (cacheResource == null)
			return;
		try {
			cacheResource.delete(key);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
