package com.mdy.dzs.game.cache;

import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.container.res.cache.CacheException;
import com.mdy.sharp.container.res.cache.CacheResource;
import com.mdy.sharp.container.res.cache.RedisResource;

/**
 * 缓存管理器
 */
public class MemcachedManager {
	//
	private static Logger logger = LoggerFactory.get(MemcachedManager.class);

	// 缓存对象
	private CacheResource cacheResource;

	//
	private static class InstanceHolder {
		static MemcachedManager instance = new MemcachedManager();
	}

	public static MemcachedManager get() {
		return InstanceHolder.instance;
	}

	private MemcachedManager() {
		cacheResource = (CacheResource) new RedisResource();
	}

	/**
	 * 从缓存获取数据
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		try {
			return (T) cacheResource.get(key);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 放入缓存，指定数据过期秒数
	 * 
	 * @param key
	 * @param v
	 * @param expireSecond
	 */
	public void set(String key, Object v, int expireSecond) {
		if (null == key || null == v) {
			return;
		}
		try {
			cacheResource.set(key, expireSecond, v);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 放入数据到缓存
	 * 
	 * @param key
	 * @param value
	 * @param expireSecond
	 */
	public void add(String key, Object value, int expireSecond) {
		if (null == key || null == value) {
			return;
		}
		try {
			cacheResource.add(key, expireSecond, value);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 移除缓存
	 * 
	 * @param key
	 */
	public void delete(String key) {
		try {
			cacheResource.delete(key);
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void replace(String key, Object value, int expireSecond) {
		delete(key);
		add(key, value, expireSecond);
	}
}
