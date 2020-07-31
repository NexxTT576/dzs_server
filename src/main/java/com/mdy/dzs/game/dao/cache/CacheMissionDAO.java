/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import com.mdy.dzs.game.dao.MissionDAO;
import com.mdy.dzs.game.domain.mission.Mission;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2015年1月22日 下午9:47:22
 */
public class CacheMissionDAO extends MissionDAO {

	private static final String ACCEPT_MISSION_PROPERTY_PREFIX = "AMP";
	private SafeCache safeCache;

	public CacheMissionDAO() {
		safeCache = new SafeCache(1800);
	}

	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource(cacheResource);
	}

	private String getAccetpMissionByPropertyKey(int roleId, int property) {
		return ACCEPT_MISSION_PROPERTY_PREFIX + roleId + "_" + property;
	}

	@Override
	public Mission queryMissionByPropertyAccept(int roleId, int property) {
		String key = getAccetpMissionByPropertyKey(roleId, property);
		Mission m = (Mission) safeCache.loadFromCache(key);
		if (m == null) {
			m = super.queryMissionByPropertyAccept(roleId, property);
			m = m != null ? m : new Mission();
			safeCache.putToCache(key, m);
		}
		if (m.getRoleId() == 0) {
			m = null;
		}
		return m;
	}

	@Override
	public void addMission(Mission mission) {
		super.addMission(mission);
		safeCache.removeFromCache(getAccetpMissionByPropertyKey(mission.getRoleId(), mission.getMissionProperty()));
	}
}
