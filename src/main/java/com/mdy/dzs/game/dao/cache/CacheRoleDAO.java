package com.mdy.dzs.game.dao.cache;

import com.mdy.dzs.game.dao.RoleDAO;
import com.mdy.dzs.game.dao.filter.RoleUpdateFilter;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheRoleDAO extends RoleDAO {

	private static final String ID_KEY_PREFIX = "R";
	private SafeCache safeCache;

	public CacheRoleDAO() {
		safeCache = new SafeCache(1800);
	}

	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource(cacheResource);
	}

	private String getIdKey(String acc) {
		return ID_KEY_PREFIX + acc;
	}

	private String getIdKey(int roleId) {
		return ID_KEY_PREFIX + roleId;
	}

	/**
	 * 更新战斗力及各个卡片位置的加成属性
	 * 
	 * @param r
	 */
	@Override
	public void updateAttackAndFmtPropPos(Role r) {
		super.updateAttackAndFmtPropPos(r);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新每日登陆
	 * 
	 * @param Role
	 */
	public void updateDayLogin(Role r) {
		super.updateDayLogin(r);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新包限制
	 * 
	 * @param doc
	 */
	public void updateBagLimitAry(Role doc) {
		super.updateBagLimitAry(doc);
		safeCache.removeFromCache(getIdKey(doc.getAccount()));
		safeCache.removeFromCache(getIdKey(doc.getId()));
	}

	public void updatePhyRss(Role doc) {
		super.updatePhyRss(doc);
		safeCache.removeFromCache(getIdKey(doc.getAccount()));
		safeCache.removeFromCache(getIdKey(doc.getId()));
	}

	/**
	 * 更新客栈休息时间
	 * 
	 * @param role
	 */
	public void updateSleepLastTime(Role role) {
		super.updateSleepLastTime(role);
		safeCache.removeFromCache(getIdKey(role.getAccount()));
		safeCache.removeFromCache(getIdKey(role.getId()));
	}

	/**
	 * 更新查看广播时间
	 * 
	 * @param r
	 */
	public void updateBroadcastViewTime(Role r) {
		super.updateBroadcastViewTime(r);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 跟新心跳时间
	 * 
	 * @param r
	 */
	public void updateHeartTimes(Role r) {
		super.updateHeartTimes(r);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新经验等级 限制
	 * 
	 * @param doc
	 */
	public void updateExp(Role doc) {
		super.updateExp(doc);
		safeCache.removeFromCache(getIdKey(doc.getAccount()));
		safeCache.removeFromCache(getIdKey(doc.getId()));
	}

	/**
	 * 更新金币
	 * 
	 * @param r
	 * @param gold
	 */
	public void updateGold(Role r, int changeVal) {
		super.updateGold(r, changeVal);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新银币
	 * 
	 * @param r
	 * @param gold
	 */
	public void updateSilver(Role r, int changeVal) {
		super.updateSilver(r, changeVal);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新侠魂
	 * 
	 * @param r
	 * @param soul
	 */
	public void updateSoul(Role r, int changeVal) {
		super.updateSoul(r, changeVal);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新vip等级
	 * 
	 * @param r
	 * @param vip
	 */
	public void updateVip(Role r, int changeVal) {
		super.updateVip(r, changeVal);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新整容
	 * 
	 * @param r
	 */
	public void updateLineup(Role r) {
		super.updateLineup(r);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));

	}

	public void updateMainCardValues(Role r) {
		super.updateMainCardValues(r);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	public void updateGuideStep(Role r) {
		super.updateGuideStep(r);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新当前聚元等级
	 * 
	 * @param doc
	 */
	public void updateCurCollLv(Role doc) {
		super.updateCurCollLv(doc);
		safeCache.removeFromCache(getIdKey(doc.getAccount()));
		safeCache.removeFromCache(getIdKey(doc.getId()));
	}

	/**
	 * 更新每日领取好友赠送的耐力计数
	 */
	public void updateGetFriendNailiCnt(Role doc) {
		super.updateGetFriendNailiCnt(doc);
		safeCache.removeFromCache(getIdKey(doc.getAccount()));
		safeCache.removeFromCache(getIdKey(doc.getId()));
	}

	/**
	 * 更新魅力值
	 */
	public void updateCharm(Role r, int changeVal) {
		super.updateCharm(r, changeVal);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新灵石
	 */
	public void updateLingShi(Role r, int changeVal) {
		super.updateLingShi(r, changeVal);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 更新荣誉
	 */
	public void updateHonor(Role r, int changeVal) {
		super.updateHonor(r, changeVal);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	/**
	 * 查询
	 */
	public Role queryByAccount(String account) {
		String key = getIdKey(account);
		Role r = (Role) safeCache.loadFromCache(key);
		if (r == null) {
			r = super.queryByAccount(account);
			safeCache.putToCache(key, r);
		}
		return r;
	}

	public Role queryById(int roleId) {
		String key = getIdKey(roleId);
		Role r = (Role) safeCache.loadFromCache(key);
		if (r == null) {
			r = super.queryById(roleId);
			safeCache.putToCache(key, r);
		}
		return r;
	}

	/**
	 * 是否存在此帐号及玩家名
	 * 
	 * @param account
	 * @param name
	 * @return
	 */
	// public Role queryIsExist(String account, String name)

	public void updateSetParam(Role doc) {
		super.updateSetParam(doc);
		safeCache.removeFromCache(getIdKey(doc.getAccount()));
		safeCache.removeFromCache(getIdKey(doc.getId()));
	}

	public void updateChat(Role doc) {
		super.updateChat(doc);
		safeCache.removeFromCache(getIdKey(doc.getAccount()));
		safeCache.removeFromCache(getIdKey(doc.getId()));
	}

	// 更新邮件查看到的时间
	public void updateMailLastTime(Role doc) {
		super.updateMailLastTime(doc);
		safeCache.removeFromCache(getIdKey(doc.getAccount()));
		safeCache.removeFromCache(getIdKey(doc.getId()));
	}

	public void updateByUpdateFilter(RoleUpdateFilter filter) {
		super.updateByUpdateFilter(filter);
		safeCache.removeFromCache(getIdKey(filter.getAccount()));
		safeCache.removeFromCache(getIdKey(filter.getRoleId()));
	}

	// public String queryServerKeyByAccount(String acc) {
	// return queryForString(
	// "select server_key from t_role where account=?",acc);
	// }
	//
	// public void updateServerKeyByAccount(String acc,String serverKey) {
	// super.updateServerKeyByAccount(acc, serverKey);
	// safeCache.removeFromCache(getIdKey(acc));
	// }

	public void updateAddPopualVal(Role r, int val) {
		super.updateAddPopualVal(r, val);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	public void updateAddHunYuVal(Role r, int val) {
		super.updateAddHunYuVal(r, val);
		safeCache.removeFromCache(getIdKey(r.getAccount()));
		safeCache.removeFromCache(getIdKey(r.getId()));
	}

	@Override
	public void updateLoginInfoByAccount(Role role, String serverKey) {
		super.updateLoginInfoByAccount(role, serverKey);
		safeCache.removeFromCache(getIdKey(role.getAccount()));
	}

	@Override
	public void updateVersionByAccount(String acc, String version) {
		super.updateVersionByAccount(acc, version);
		safeCache.removeFromCache(getIdKey(acc));
	}

	/**
	 * 修改用户帮派名
	 */
	@Override
	public void updateRoleFaction(Role role, String unionName) {
		super.updateRoleFaction(role, unionName);
		safeCache.removeFromCache(getIdKey(role.getAccount()));
		safeCache.removeFromCache(getIdKey(role.getId()));
	}
}
