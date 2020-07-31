package com.mdy.dzs.game.ao;

import java.util.Iterator;
import java.util.List;

import com.mdy.dzs.game.domain.tournament.RoleTournament;
import com.mdy.dzs.game.domain.tournament.RoleTournamentEnemy;
import com.mdy.dzs.game.manager.CacheManager;

/**
 * 比武的仇人
 * 
 * @author zhou
 *
 */
public class RoleTournamentEnemyAO extends BaseAO {
	private CacheManager cacheManager;

	public RoleTournamentEnemyAO(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	//
	/**
	 * 查询
	 */
	public RoleTournamentEnemy query(int roleId) {
		return roleTournamentEnemyDAO().query(roleId);
	}

	/**
	 * 查询
	 */
	public RoleTournamentEnemy queryByEnemy(int roleId, int enemyId) {
		return roleTournamentEnemyDAO().queryByEnemy(roleId, enemyId);
	}

	/**
	 * 查询列表
	 */
	public List<RoleTournamentEnemy> queryList() {
		return roleTournamentEnemyDAO().queryList();
	}

	/**
	 * 查询某人的仇人列表
	 */
	public List<RoleTournamentEnemy> queryListByRoleId(int roleId, int number) {
		return roleTournamentEnemyDAO().queryListByRoleId(roleId, number);
	}

	/**
	 * 查询某人的仇人列表 去掉50以外 的
	 */
	public void removeOverEnemy(int roleId, int number, RoleTournament self) {
		List<RoleTournamentEnemy> enemyList = roleTournamentEnemyDAO().queryListByRoleId(roleId);
		StringBuilder enemyIs = new StringBuilder("(");
		for (Iterator<RoleTournamentEnemy> iterator = enemyList.iterator(); iterator.hasNext();) {
			RoleTournamentEnemy roleTournamentEnemy = (RoleTournamentEnemy) iterator.next();
			RoleTournament rt = null;
			if (self.getRoleId() == roleTournamentEnemy.getEnemyId()) {
				rt = self;
			} else {
				rt = roleTournamentDAO().query(roleTournamentEnemy.getEnemyId());
			}
			if (rt.getScore() < 1000) {
				enemyIs.append(enemyIs.length() == 1 ? "" : ",").append(roleTournamentEnemy.getEnemyId());
				iterator.remove();
			}
		}
		if (enemyList.size() > number) {
			for (int i = number; i < enemyList.size(); i++) {
				enemyIs.append(enemyIs.length() == 1 ? "" : ",").append(enemyList.get(i).getEnemyId());
			}
		}
		enemyIs.append(")");
		if (enemyIs.length() > 2) {
			roleTournamentEnemyDAO().delete(roleId, enemyIs);
		}
	}

	/**
	 * 添加
	 * 
	 * @param RoleTournamentEnemy
	 */
	public void add(RoleTournamentEnemy rte) {
		roleTournamentEnemyDAO().add(rte);
	}

	/**
	 * 更新
	 * 
	 * @param RoleTournamentEnemy
	 */
	public void update(RoleTournamentEnemy rte) {
		roleTournamentEnemyDAO().update(rte);
	}

	/**
	 * 删除所有
	 */
	public void deleteAll() {
		roleTournamentEnemyDAO().deleteAll();
	}

	/**
	 * 删除一个仇人
	 * 
	 * @param roleId
	 * @param enemyId
	 */
	public void delete(int roleId, int enemyId) {
		roleTournamentEnemyDAO().delete(roleId, enemyId);
	}
}