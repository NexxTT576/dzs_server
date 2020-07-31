package com.mdy.dzs.game.dao;

import java.util.Iterator;
import java.util.List;

import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.tournament.OppVO;
import com.mdy.dzs.game.domain.tournament.RoleTourRankVO;
import com.mdy.dzs.game.domain.tournament.RoleTournament;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 比武的角色DAO
 * 
 * @author zhou
 *
 */
public class RoleTournamentDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleTournament> HANDLER = new ResultSetRowHandler<RoleTournament>() {
		@Override
		public RoleTournament handleRow(ResultSetRow row) throws Exception {
			RoleTournament rt = new RoleTournament();
			rt.setRoleId(row.getInt("role_id"));
			rt.setScore(row.getInt("score"));
			rt.setWinTimes(row.getInt("winTimes"));
			rt.setTotalTimes(row.getInt("totalTimes"));
			List<OppVO> enemyIds = JSONUtil.fromJsonList(row.getString("enemyIds"), OppVO.class);
			rt.setEnemyIds(enemyIds);
			rt.setNextFleshTime(row.getTimestamp("nextFleshTime"));
			rt.setChallengeTimes(row.getInt("challengeTimes"));
			rt.setBuyTimes(row.getInt("buyTimes"));
			rt.setCreateTime(row.getTimestamp("create_time"));
			rt.setUpdateTime(row.getTimestamp("update_time"));
			rt.setFleshTime(row.getTimestamp("flesh_time"));
			return rt;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleTournament
	 */
	public void add(RoleTournament rt) {
		String sql = "insert into t_role_tournament(" + "role_id," + "score," + "winTimes," + "totalTimes,"
				+ "enemyIds," + "nextFleshTime," + "challengeTimes," + "buyTimes," + "create_time," + "update_time,"
				+ "flesh_time" + ")values(?,?,?,?,?,?,?,?,now(),now(),now())";
		execute(sql, rt.getRoleId(), rt.getScore(), rt.getWinTimes(), rt.getTotalTimes(),
				JSONUtil.toJson(rt.getEnemyIds()), rt.getNextFleshTime(), rt.getChallengeTimes(), rt.getBuyTimes());
	}

	/**
	 * 更新
	 * 
	 * @param RoleTournament
	 */
	public void update(RoleTournament rt) {
		String sql = "update t_role_tournament set " + " update_time=now(), " + " score=?," + " winTimes=?,"
				+ " totalTimes=?," + " enemyIds=?," + " nextFleshTime=?," + " challengeTimes=?," + " buyTimes=?,"
				+ " flesh_time=?" + " where role_id=?";
		executeUpdate(sql, rt.getScore(), rt.getWinTimes(), rt.getTotalTimes(), JSONUtil.toJson(rt.getEnemyIds()),
				rt.getNextFleshTime(), rt.getChallengeTimes(), rt.getBuyTimes(), rt.getFleshTime(), rt.getRoleId());
	}

	/**
	 * 查询
	 */
	public RoleTournament query(int role_id) {
		return queryForObject("select * from t_role_tournament where role_id=?", HANDLER, role_id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleTournament> queryList() {
		return queryForList("select * from t_role_tournament", HANDLER);
	}

	/**
	 * 查询列表
	 */
	public List<RoleTournament> queryListByScoreDesc() {
		return queryForList("select * from t_role_tournament order by score desc", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_tournament where role_id=?", HANDLER, id);
	}

	/**
	 * 按照积分段进行查询
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public List<RoleTournament> queryListByScore(List<RoleTournament> roleList, RoleTournament self, int min, int max,
			List<Role> robotList) {
		StringBuilder s = new StringBuilder("(");
		for (Iterator iterator = roleList.iterator(); iterator.hasNext();) {
			RoleTournament role = (RoleTournament) iterator.next();
			s.append(role.getRoleId()).append(",");
		}
		for (Role role : robotList) {
			s.append(role.getId()).append(",");
		}
		s.append(self.getRoleId()).append(")");
		if (max > 0) {
			return queryForList("select * from t_role_tournament where score > ? and score <= ? "
					+ "and role_id not in " + s.toString(), HANDLER, min, max);
		} else {
			return queryForList(
					"select * from t_role_tournament where score > ? " + "and role_id not in " + s.toString(), HANDLER,
					min);
		}
	}

	/**
	 * 清空所有积分
	 * 
	 * @param number
	 */
	public void clearScore(int number) {
		executeUpdate("update t_role_tournament set score=?,winTimes=0,totalTimes=0", number);
	}

	public int updateScore(int roleId, int score, int challengeTimes, int winTimes, int totalTimes) {
		String sql = "update t_role_tournament set " + " score=?," + " challengeTimes=?," + " winTimes=?,"
				+ " totalTimes=?," + " update_time=now() " + " where role_id=?";
		executeUpdate(sql, score, challengeTimes, winTimes, totalTimes, roleId);
		return 0;
	}

	public List<RoleTourRankVO> getRankList() {
		// TODO 自动生成的方法存根
		return null;
	}
}