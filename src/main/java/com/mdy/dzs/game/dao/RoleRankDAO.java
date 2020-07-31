package com.mdy.dzs.game.dao;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.domain.rank.RankInfo;
import com.mdy.dzs.game.domain.rank.RoleRank;
import com.mdy.dzs.game.filter.RankFilter;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 排行DAO
 * 
 * @author 房曈
 *
 */
public class RoleRankDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleRank> RANK_ROW_HANDLER = new ResultSetRowHandler<RoleRank>() {
		@Override
		public RoleRank handleRow(ResultSetRow row) throws Exception {
			RoleRank rr = new RoleRank();
			rr.setRoleId(row.getInt("role_id"));
			rr.setAccount(row.getString("account"));
			rr.setName(row.getString("name"));
			rr.setResId(row.getInt("res_id"));
			rr.setAttackRank(row.getInt("attack_rank"));
			rr.setGradeRank(row.getInt("grade_rank"));
			rr.setBattleRank(row.getInt("battle_rank"));
			rr.setArenaRank(row.getInt("arena_rank"));
			rr.setAttack(row.getInt("attack"));
			rr.setGrade(row.getInt("grade"));
			rr.setPrestige(row.getInt("prestige"));
			rr.setBattleStars(row.getInt("battle_stars"));
			rr.setBattleId(row.getInt("battle_id"));
			rr.setLevelUpLastTime(row.getTimestamp("level_up_last_time"));
			rr.setStarHasLastTime(row.getTimestamp("star_has_last_time"));
			rr.setCreateTime(row.getTimestamp("create_time"));
			rr.setUpdateTime(row.getTimestamp("update_time"));
			return rr;
		}
	};

	private static ResultSetRowHandler<RoleRank> ARENA_RANK_ROW_HANDLER = new ResultSetRowHandler<RoleRank>() {
		@Override
		public RoleRank handleRow(ResultSetRow row) throws Exception {
			RoleRank rr = new RoleRank();
			rr.setRoleId(row.getInt("role_id"));
			rr.setAccount(row.getString("account"));
			rr.setName(row.getString("name"));
			rr.setResId(row.getInt("resId"));
			rr.setArenaRank(row.getInt("arena_rank"));
			rr.setPrestige(row.getInt("popual"));
			return rr;
		}
	};
	private static ResultSetRowHandler<RoleRank> BATTLE_RANK_ROW_HANDLER = new ResultSetRowHandler<RoleRank>() {
		@Override
		public RoleRank handleRow(ResultSetRow row) throws Exception {
			RoleRank rr = new RoleRank();
			rr.setRoleId(row.getInt("role_id"));
			rr.setAccount(row.getString("account"));
			rr.setName(row.getString("name"));
			rr.setResId(row.getInt("resId"));
			rr.setBattleId(row.getInt("battle_lv_id"));
			rr.setBattleStars(row.getInt("battle_total_stars"));
			rr.setStarHasLastTime(row.getTimestamp("battle_star_has_last_time"));
			return rr;
		}
	};
	private static ResultSetRowHandler<RoleRank> ROLE_RANK_ROW_HANDLER = new ResultSetRowHandler<RoleRank>() {
		@Override
		public RoleRank handleRow(ResultSetRow row) throws Exception {
			RoleRank rr = new RoleRank();
			rr.setRoleId(row.getInt("id"));
			rr.setAccount(row.getString("account"));
			rr.setName(row.getString("name"));
			rr.setResId(row.getInt("resId"));
			rr.setAttack(row.getInt("attack"));
			rr.setGrade(row.getInt("level"));
			rr.setLevelUpLastTime(row.getTimestamp("level_up_last_time"));
			return rr;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleRank
	 */
	public void add(RoleRank rr) {
		String sql = "insert into t_role_rank(" + "role_id," + "account," + "name," + "res_id," + "attack_rank,"
				+ "grade_rank," + "battle_rank," + "arena_rank," + "attack," + "grade," + "prestige," + "battle_stars,"
				+ "battle_id," + "level_up_last_time," + "star_has_last_time," + "create_time," + "update_time"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now())";
		execute(sql, rr.getRoleId(), rr.getAccount(), rr.getName(), rr.getResId(), rr.getAttackRank(),
				rr.getGradeRank(), rr.getBattleRank(), rr.getArenaRank(), rr.getAttack(), rr.getGrade(),
				rr.getPrestige(), rr.getBattleStars(), rr.getBattleId(), rr.getLevelUpLastTime(),
				rr.getStarHasLastTime());
	}

	/**
	 * 更新
	 * 
	 * @param RoleRank
	 */
	public void update(RoleRank rr) {
		String sql = "update t_role_rank set " + " update_time=now(), " + " name=?," + " res_id=?," + " attack_rank=?,"
				+ " grade_rank=?," + " battle_rank=?," + " arena_rank=?," + " attack=?," + " grade=?," + " prestige=?,"
				+ " battle_stars=?," + " battle_id=?," + " level_up_last_time=?," + " star_has_last_time=? "
				+ " where role_id=?";
		executeUpdate(sql, rr.getName(), rr.getResId(), rr.getAttackRank(), rr.getGradeRank(), rr.getBattleRank(),
				rr.getArenaRank(), rr.getAttack(), rr.getGrade(), rr.getPrestige(), rr.getBattleStars(),
				rr.getBattleId(), rr.getLevelUpLastTime(), rr.getStarHasLastTime(), rr.getRoleId());
	}

	/**
	 * 查询
	 */
	public RoleRank query(int id) {
		return queryForObject("select * from t_role_rank where role_id=?", RANK_ROW_HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleRank> queryList() {
		return queryForList("select * from t_role_rank", RANK_ROW_HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_rank where role_id=?", id);
	}

	/**
	 * 根据过滤器查询排行列表
	 * 
	 * @param filter
	 * @return
	 */
	public List<RoleRank> queryListByFilter(RankFilter filter) {
		StringBuffer sql = new StringBuffer("select * from t_role_rank where 1=1 ");
		List<Object> val = new ArrayList<Object>();

		addCondition(filter, sql, val);

		// 排序方式
		switch (filter.getOrderType()) {
		case RankFilter.ORDER_BY_ARENA_DESC: {
			sql.append(" order by arena_rank ");
		}
			break;
		case RankFilter.ORDER_BY_ATTACK_DESC: {
			sql.append(" order by attack_rank ");
		}
			break;
		case RankFilter.ORDER_BY_GRADE_DESC: {
			sql.append(" order by grade_rank ");
		}
			break;
		case RankFilter.ORDER_BY_STARS_DESC: {
			sql.append(" order by battle_rank ");
		}
			break;
		}

		sql.append(" limit ");
		sql.append(filter.getStartOfPage());
		sql.append(" ,");
		sql.append(filter.getPageSize());
		return queryForList(sql.toString(), RANK_ROW_HANDLER, val.toArray());
	}

	private void addCondition(RankFilter filter, StringBuffer sql, List<Object> val) {
		if (filter.getStartArenaRank() > 0) {
			sql.append(" and arena_rank>=? ");
			val.add(filter.getStartArenaRank());
		}
		if (filter.getStartAttackRank() > 0) {
			sql.append(" and attack_rank>=? ");
			val.add(filter.getStartAttackRank());
		}
		if (filter.getStartGradeRank() > 0) {
			sql.append(" and grade_rank>=? ");
			val.add(filter.getStartGradeRank());
		}
		if (filter.getStartStarsRank() > 0) {
			sql.append(" and battle_rank>=? ");
			val.add(filter.getStartStarsRank());
		}
	}

	/**
	 * @param type
	 * @param maxRankSize
	 * @return
	 */
	public List<RoleRank> queryListByRank(int type, int maxRankSize) {
		List<RoleRank> res = null;
		switch (type) {
		case RankInfo.TYPE_RANK_竞技场:
			res = queryForList(
					"select role_id,account,name,resId,a.rank as arena_rank,popual from t_role r,t_role_arena a"
							+ " where r.id = a.role_id order by a.rank limit ?",
					ARENA_RANK_ROW_HANDLER, maxRankSize);
			break;
		case RankInfo.TYPE_RANK_副本:
			res = queryForList(
					"select role_id,account,name,resId ,battle_lv_id,battle_total_stars,battle_star_has_last_time from t_role r,t_role_battle b "
							+ "where r.id = b.role_id order by battle_total_stars DESC,battle_star_has_last_time  limit ?",
					BATTLE_RANK_ROW_HANDLER, maxRankSize);
			break;
		default:
			String sql = "select id,name,account,resId,attack,level,level_up_last_time from t_role ";
			switch (type) {
			case RankInfo.TYPE_RANK_战力:
				sql += " order by attack desc";
				break;
			case RankInfo.TYPE_RANK_等级:
				sql += " order by level desc,level_up_last_time";
				break;
			}
			sql += "  limit ?";
			res = queryForList(sql, ROLE_RANK_ROW_HANDLER, maxRankSize);
			break;
		}
		return res;
	}
}