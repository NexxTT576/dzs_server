package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.UnionApplyDAO;
import com.mdy.dzs.game.domain.union.UnionApply;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheUnionApplyDAO extends UnionApplyDAO {
	private static final String ID_KEY_PREFIX = "UA";

	private SafeCache safeCache;
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource(cacheResource);
	}
	public CacheUnionApplyDAO() {
		safeCache = new SafeCache(1800);
	}
	private String getroleAppListKey(int roleId) {
		return ID_KEY_PREFIX + roleId;
	}
	private String getUnionAppListKey(int unionId) {
		return ID_KEY_PREFIX + "_" + unionId;
	}
	private String getRoleIdUnionIdkey(int roleId, int unionId) {
		return ID_KEY_PREFIX + "/" + unionId + "_" + roleId;
	}
	/**
	 * 查询用户申请列表
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnionApply> queryRoleApplyList(int roleId) {
		String key = getroleAppListKey(roleId);
		List<UnionApply> r = (List<UnionApply>) safeCache.loadFromCache(key);
		if (r == null) {
			r = super.queryRoleApplyList(roleId);
			safeCache.putToCache(key, r);
		}
		return r;
	}
	/**
	 * 查询帮派申请列表
	 * @param unionId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnionApply> queryApplyList(int unionId) {
		String key = getUnionAppListKey(unionId);
		List<UnionApply> r = (List<UnionApply>) safeCache.loadFromCache(key);
		if (r == null) {
			r = super.queryApplyList(unionId);
			safeCache.putToCache(key, r);
		}
		return r;
	}

	/**
	 * 查询某一条申请记录
	 * 
	 * @param roleId
	 * @param unionId
	 * @return
	 */
	@Override
	public UnionApply queryApply(int roleId, int unionId) {
		String key = getRoleIdUnionIdkey(roleId, unionId);
		UnionApply r = (UnionApply) safeCache.loadFromCache(key);
		if (r == null) {
			r = super.queryApply(roleId, unionId);
			safeCache.putToCache(key, r);
		}
		return r;
	}

	/**
	 * 创建用户申请
	 * 
	 * @param unionApply
	 */
	@Override
	public void createUnionApply(UnionApply unionApply) {
		super.createUnionApply(unionApply);
		safeCache.removeFromCache(getroleAppListKey(unionApply.getRoleId()));
		safeCache.removeFromCache(getUnionAppListKey(unionApply.getUnionId()));
		safeCache.removeFromCache(getRoleIdUnionIdkey(unionApply.getRoleId(), unionApply.getUnionId()));
	}
	/**
	 * 删除用户申请
	 * @param roleId
	 * @param unionId
	 */
	@Override
	public void deleteRoleApply(int roleId,int unionId){
		super.deleteRoleApply(roleId, unionId);
		safeCache.removeFromCache(getroleAppListKey(roleId));
		safeCache.removeFromCache(getUnionAppListKey(unionId));
		safeCache.removeFromCache(getRoleIdUnionIdkey(roleId, unionId));
	}
}
