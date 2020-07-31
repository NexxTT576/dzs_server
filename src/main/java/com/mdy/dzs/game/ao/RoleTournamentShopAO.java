package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.tournament.RoleTournamentShop;
import com.mdy.dzs.game.manager.CacheManager;

/**
 * 比武的兑换
 * 
 * @author zhou
 *
 */
public class RoleTournamentShopAO extends BaseAO {
	private CacheManager cacheManager;

	public RoleTournamentShopAO(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	//
	/**
	 * 查询
	 */
	public RoleTournamentShop query(int id, int itemId) {
		return roleTournamentShopDAO().query(id, itemId);
	}

	/**
	 * 查询列表
	 */
	public List<RoleTournamentShop> queryList() {
		return roleTournamentShopDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param RoleTournamentShop
	 */
	public void add(RoleTournamentShop rts) {
		roleTournamentShopDAO().add(rts);
	}

	/**
	 * 更新
	 * 
	 * @param RoleTournamentShop
	 */
	public void update(RoleTournamentShop rts) {
		roleTournamentShopDAO().update(rts);
	}

	/**
	 * 查询出物品id
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleTournamentShop> queryListByRoleId(int roleId) {
		return roleTournamentShopDAO().queryListByRoleId(roleId);
	}

	/**
	 * 删除所有
	 */
	public void deleteAll(List<Integer> types) {
		roleTournamentShopDAO().deleteAll(types);
	}

	/**
	 * 删除一个人的数据
	 */
	public void deleteOne(List<Integer> types, int roleId) {
		roleTournamentShopDAO().deleteOne(types, roleId);
	}
}