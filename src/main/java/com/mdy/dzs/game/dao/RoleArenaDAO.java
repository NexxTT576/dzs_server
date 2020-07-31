package com.mdy.dzs.game.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.arena.RoleArenaShop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 竞技场DAO
 * 
 * @author 房曈
 *
 */
public class RoleArenaDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleArena> HANDLER = new ResultSetRowHandler<RoleArena>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleArena handleRow(ResultSetRow row) throws Exception {
			RoleArena ra = new RoleArena();
			ra.setRank(row.getInt("rank"));
			ra.setRoleId(row.getInt("role_id"));
			ra.setAwardIds(JSONUtil.fromJson(row.getString("award_ids"), List.class));
			ra.setAwardSilver(row.getInt("award_silver"));
			ra.setAwardPopual(row.getInt("award_popual"));
			ra.setCreateTime(row.getTimestamp("create_time"));
			ra.setUpdateTime(row.getTimestamp("update_time"));
			return ra;
		}
	};

	/**
	 * 添加 自动计算排名 不予许回滚
	 * 
	 * @param RoleArena
	 */
	public void addGenerateKey(RoleArena ra) {
		String sql = "insert into t_role_arena(" + "role_id," + "award_ids," + "award_silver," + "award_popual,"
				+ "create_time," + "update_time" + ")values(?,?,?,?,now(),now())";
		int rank = executeWithGenKey(sql, ra.getRoleId(), JSONUtil.toJson(ra.getAwardIds()), ra.getAwardSilver(),
				ra.getAwardPopual());
		ra.setRank(rank);
	}

	/**
	 * 更新
	 * 
	 * @param RoleArena
	 */
	public void swapUpdate(int srcRoleId, int tgtRoleId) {
		String sql = "update t_role_arena a, t_role_arena b" + " set a.role_id = b.role_id, b.role_id = a.role_id,"
				+ " a.update_time = now(),b.update_time = now() "
				+ "where a.role_id = ? and b.role_id = ? and a.rank > b.rank";
		executeUpdate(sql, srcRoleId, tgtRoleId);
	}

	/**
	 * 查询
	 */
	public RoleArena query(int roleId) {
		return queryForObject("select * from t_role_arena where role_id=?", HANDLER, roleId);
	}

	/**
	 * 更新排行奖励信息
	 * 
	 * @param ra
	 */
	public void updateAwardInfo(RoleArena ra) {
		String sql = "update t_role_arena " + "set award_ids = ?," + " award_silver=?," + " award_popual=?"
				+ " where rank= ?";
		executeUpdate(sql, JSONUtil.toJson(ra.getAwardIds()), ra.getAwardSilver(), ra.getAwardPopual(), ra.getRank());
	}

	/**
	 * 查询某些排行数据
	 * 
	 * @param ranks
	 * @return
	 */
	public List<RoleArena> queryListByRanks(List<Integer> ranks) {
		String inRanks = "";
		for (Integer integer : ranks) {
			inRanks += integer + ",";
		}
		inRanks = inRanks.substring(0, inRanks.length() - 1);
		return queryForList("select * from t_role_arena where rank in (" + inRanks + ") order by rank", HANDLER);
	}

	public int queryMaxCount() {
		return queryForInteger("select max(rank) from t_role_arena");
	}

	public RoleArena queryByRank(int rank) {
		return queryForObject("select * from t_role_arena where rank=?", HANDLER, rank);
	}

	public List<RoleArena> queryAwardRank(int maxAwardRank) {
		return queryForList("select * from t_role_arena where rank < ?", HANDLER, maxAwardRank);
	}

	/////////////////////////////////////////////////////////////////////////////
	private static ResultSetRowHandler<RoleArenaShop> SHOP_HANDLER = new ResultSetRowHandler<RoleArenaShop>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleArenaShop handleRow(ResultSetRow row) throws Exception {
			RoleArenaShop ras = new RoleArenaShop();
			ras.setRoleId(row.getInt("role_id"));
			ras.setDayPurchased(JSONUtil.fromJson(row.getString("day_purchased"), Map.class));
			ras.setAllPurchased(JSONUtil.fromJson(row.getString("all_purchased"), Map.class));
			ras.setCreateTime(row.getTimestamp("create_time"));
			ras.setUpdateTime(row.getTimestamp("update_time"));
			ras.setDayRefreshTime(row.getTimestamp("day_refresh_time"));
			return ras;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleArenaShop
	 */
	public void addShop(int roleId) {
		String sql = "insert into t_role_arena_shop(" + "role_id," + "create_time," + "update_time"
				+ ")values(?,now(),now())";
		execute(sql, roleId);
	}

	/**
	 * 更新
	 * 
	 * @param RoleArenaShop
	 */
	public void updateShop(RoleArenaShop ras) {
		String sql = "update t_role_arena_shop set " + " update_time=now(), " + " day_purchased=?,"
				+ " all_purchased=? " + " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(ras.getDayPurchased()), JSONUtil.toJson(ras.getAllPurchased()),
				ras.getRoleId());
	}

	/**
	 * 查询
	 */
	public RoleArenaShop queryRoleArenaShop(int role_id) {
		return queryForObject("select * from t_role_arena_shop where role_id=?", SHOP_HANDLER, role_id);
	}

	/**
	 * 清理排行榜
	 */
	public void clearRoleArena() {
		execute("TRUNCATE TABLE `t_role_arena`");
		execute("ALTER TABLE `t_role_arena` AUTO_INCREMENT=1");
	}

	public void resetArenaShop(RoleArenaShop shop) {
		shop.getDayPurchased().clear();
		String sql = "update t_role_arena_shop set " + "day_purchased =? ," + "update_time = now(), "
				+ "day_refresh_time = now() " + "where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(new HashMap<String, Integer>()), shop.getRoleId());
	}

	//////////////////////////////////////////////////////////////////////////////////////

	private static ResultSetRowHandler<Integer> ROLE_ID_HANDLER = new ResultSetRowHandler<Integer>() {
		@Override
		public Integer handleRow(ResultSetRow row) throws Exception {
			return row.getInt("role_id");
		}
	};

	/**
	 * @param min
	 * @param max
	 * @return
	 */
	public List<Integer> queryRoldIdByRankInterval(int min, int max) {
		// TODO 自动生成的方法存根
		return queryForList("select role_id from t_role_arena where rank>= ? and rank <=?", ROLE_ID_HANDLER, min, max);
	}

}