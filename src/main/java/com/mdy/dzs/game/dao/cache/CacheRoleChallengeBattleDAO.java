/**
 * 
 */
package com.mdy.dzs.game.dao.cache;

import com.mdy.dzs.game.dao.RoleChallengeBattleDAO;
import com.mdy.dzs.game.domain.challenge.RoleActivityBattle;
import com.mdy.dzs.game.domain.challenge.RoleEliteBattle;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.sharp.container.res.cache.CacheResource;

/**
 * 挑战副本缓存
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年11月14日  下午4:38:16
 */
public class CacheRoleChallengeBattleDAO extends RoleChallengeBattleDAO {

	private static final String ELITE_BATTLE_KEY_PREFIX ="REB";
	private static final String ACTIVITY_BATTLE_KEY_PREFIX ="RAB";
	
	private SafeCache safeCache ;
	

	public CacheRoleChallengeBattleDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}

	private String getEliteBattleKey(int roleId){
		return ELITE_BATTLE_KEY_PREFIX+roleId;
	}
	
	private String getActivityBattleKey(int roleId){
		return ACTIVITY_BATTLE_KEY_PREFIX+roleId;
	}
	
	/**
	 * 添加
	 * @param Role
	 */
	public void addEliteBattle(Role r){
		super.addEliteBattle(r);
		safeCache.removeFromCache(getEliteBattleKey(r.getId()));
	}
	/**
	 * 更新精英副本(不会更新当日相关次数)
	 * @param Role
	 */
	public void updateEliteBattle(RoleEliteBattle r){
		super.updateEliteBattle(r);
		safeCache.removeFromCache(getEliteBattleKey(r.getRoleId()));
	}
	/**
	 * 查询
	 * @param roleId
	 * @return
	 */
	public RoleEliteBattle queryEliteBattleByRoleId(int roleId){
		String key = getEliteBattleKey(roleId);
		RoleEliteBattle src = (RoleEliteBattle)safeCache.loadFromCache( key );
		if(src == null){
			src = super.queryEliteBattleByRoleId(roleId);
			safeCache.putToCache(key, src);
		}
		return src;
	}
	/**
	 * 重置精英副本 0点
	 * @param roldId
	 */
	public void resetEliteBattle(int roleId) {
		super.resetEliteBattle(roleId);
		safeCache.removeFromCache(getEliteBattleKey(roleId));
	}
	
	/**
	 * 进行一次精英副本
	 * 0点刷新
	 * @param elite
	 */
	public void useEliteBattleCnt(RoleEliteBattle elite){
		super.useEliteBattleCnt(elite);
		safeCache.removeFromCache(getEliteBattleKey(elite.getRoleId()));
	}
	/**
	 * 购买一次精英副本次数
	 * 0点刷新
	 * @param elite
	 */
	public void buyEliteBattleCnt(RoleEliteBattle elite){
		super.buyEliteBattleCnt(elite);
		safeCache.removeFromCache(getEliteBattleKey(elite.getRoleId()));
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 查询
	 * @param roleId
	 * @return
	 */
	public RoleActivityBattle queryActBattle(int roleId){
		String key = getActivityBattleKey(roleId);
		RoleActivityBattle src = (RoleActivityBattle)safeCache.loadFromCache( key );
		if(src == null){
			src = super.queryActBattle(roleId);
			safeCache.putToCache(key, src);
		}
		return src;
	}

			
	/**
	 * 重置活动副本 0点
	 * @param roldId
	 */
	public void resetActivityBattle(int roleId) {
		super.resetActivityBattle(roleId);
		safeCache.removeFromCache(getActivityBattleKey(roleId));
	}
	
	/**
	 * 更新活动副本(不会更新当日相关次数)
	 * @param Role
	 */
	public void updateActivityBattle(RoleActivityBattle r){
		super.updateActivityBattle(r);
		safeCache.removeFromCache(getActivityBattleKey(r.getRoleId()));
	}
	
	/**
	 * 添加
	 * @param Role
	 */
	public void addActivityBattle(Role r){
		super.addActivityBattle(r);
		safeCache.removeFromCache(getActivityBattleKey(r.getId()));
	}
}
