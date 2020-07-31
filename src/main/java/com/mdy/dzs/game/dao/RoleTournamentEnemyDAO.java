package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.tournament.RoleTournamentEnemy;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 比武的仇人DAO
 * 
 * @author zhou
 *
 */
public class RoleTournamentEnemyDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleTournamentEnemy> HANDLER = new ResultSetRowHandler<RoleTournamentEnemy>() {
		@Override
		public RoleTournamentEnemy handleRow(ResultSetRow row) throws Exception {
			RoleTournamentEnemy rte = new RoleTournamentEnemy();
			rte.setRoleId(row.getInt("role_id"));
			rte.setEnemyId(row.getInt("enemy_id"));
			rte.setCreateTime(row.getTimestamp("create_time"));
			rte.setUpdateTime(row.getTimestamp("update_time"));
			return rte;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleTournamentEnemy
	 */
	public void add(RoleTournamentEnemy rte) {
		String sql = "insert into t_role_tournament_enemy(" + "role_id," + "enemy_id," + "create_time," + "update_time"
				+ ")values(?,?,now(),now())";
		execute(sql, rte.getRoleId(), rte.getEnemyId());
	}

	/**
	 * 更新
	 * 
	 * @param RoleTournamentEnemy
	 */
	public void update(RoleTournamentEnemy rte) {
		String sql = "update t_role_tournament_enemy set " + " update_time=now() " + " where role_id=? and enemy_id=?";
		executeUpdate(sql, rte.getRoleId(), rte.getEnemyId());
	}

	/**
	 * 查询
	 */
	public RoleTournamentEnemy query(int roleId) {
		return queryForObject("select * from t_role_tournament_enemy where role_id=?", HANDLER, roleId);
	}

	/**
	 * 查询敌人
	 * 
	 * @param roleId
	 * @param enemyId
	 * @return
	 */
	public RoleTournamentEnemy queryByEnemy(int roleId, int enemyId) {
		return queryForObject("select * from t_role_tournament_enemy where role_id=? and enemy_id=?", HANDLER, roleId,
				enemyId);
	}

	/**
	 * 查询列表
	 */
	public List<RoleTournamentEnemy> queryList() {
		return queryForList("select * from t_role_tournament_enemy", HANDLER);
	}

	/**
	 * 查询某人的仇人列表
	 */
	public List<RoleTournamentEnemy> queryListByRoleId(int roleId, int number) {
		return queryForList("select * from t_role_tournament_enemy where role_id=? order by update_time desc limit ? ",
				HANDLER, roleId, number);
	}

	/**
	 * 查询某人的仇人列表
	 */
	public List<RoleTournamentEnemy> queryListByRoleId(int roleId) {
		return queryForList("select * from t_role_tournament_enemy where role_id=? order by update_time desc", HANDLER,
				roleId);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_tournament_enemy where role_id=?", id);
	}

	/**
	 * 删除所有数据
	 */
	public void deleteAll() {
		execute("delete  from t_role_tournament_enemy");
	}

	/**
	 * 删除一个对手
	 * 
	 * @param roleId
	 * @param enemyId
	 */
	public void delete(int roleId, int enemyId) {
		execute("delete  from t_role_tournament_enemy where role_id=? and enemy_id=?", roleId, enemyId);
	}

	/**
	 * 删除多个对手
	 * 
	 * @param roleId
	 * @param enemyIds
	 */
	public void delete(int roleId, StringBuilder enemyIds) {
		execute("delete  from t_role_tournament_enemy where role_id=? and enemy_id in " + enemyIds.toString(), roleId);
	}
}