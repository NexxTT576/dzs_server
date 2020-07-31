package com.mdy.dzs.game.domain;

import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * 
 */
public class ModelCache {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.get(ModelCache.class);

	private static class InstanceHolder {
		static ModelCache instance = new ModelCache();
	}

	private ModelCache() {
	}

	public static ModelCache get() {
		return InstanceHolder.instance;
	}

}