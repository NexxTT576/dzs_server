package com.mdy.dzs.game.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.battle.LvNum;
import com.mdy.dzs.game.domain.battle.RoleBattle;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 副本DAO
 * 
 * @author 房曈
 *
 */
public class RoleBattleDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleBattle> HANDLER = new ResultSetRowHandler<RoleBattle>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleBattle handleRow(ResultSetRow row) throws Exception {
			RoleBattle rb = new RoleBattle();
			rb.setRoleId(row.getInt("role_id"));
			rb.setBattleWorldID(row.getInt("battle_world_id"));
			rb.setBattleFieldID(row.getInt("battle_field_id"));
			rb.setBattleLvID(row.getInt("battle_lv_id"));
			rb.setOverMBattles(JSONUtil.fromJson(row.getString("movie_battle_ids"), List.class));
			List<LvNum> battleHis = JSONUtil.fromJsonList(row.getString("battle_his"), LvNum.class);
			rb.setBattleHis(battleHis);
			List<LvNum> battleBox = JSONUtil.fromJsonList(row.getString("battle_box"), LvNum.class);
			rb.setBattleBox(battleBox);
			rb.setBattleNHitTime(row.getTimestamp("battle_nhit_time"));
			List<LvNum> battleDayHitStat = JSONUtil.fromJsonList(row.getString("battle_day_hit_stat"), LvNum.class);
			rb.setBattleDayHitStat(battleDayHitStat);
			rb.setBattleDayNHitClearCnt(row.getInt("battle_day_nhit_clear_cnt"));
			rb.setBattleTotalStars(row.getInt("battle_total_stars"));
			rb.setBattleDayRefresh(row.getTimestamp("battle_day_refresh"));
			rb.setBattleDayBuyCnt(row.getInt("battle_day_buy_cnt"));
			rb.setCreateTime(row.getTimestamp("create_time"));
			rb.setUpdateTime(row.getTimestamp("update_time"));
			rb.setBattleStarHasLastTime(row.getTimestamp("battle_star_has_last_time"));
			return rb;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleBattle
	 */
	public void add(int roleId) {
		String sql = "insert into t_role_battle(" + "role_id," + "battle_world_id," + "battle_field_id,"
				+ "battle_lv_id," + "battle_his," + "battle_nhit_time," + "create_time," + "update_time"
				+ ")values(?,?,?,?,?,now(),now(),now())";

		execute(sql, roleId, Constants.battleInitWorld, Constants.battleInitMap, Constants.battleInitBattle,
				"[{\"lv\":" + Constants.battleInitBattle + ",\"n\":0}]");
	}

	/**
	 * 更新每日登陆
	 * 
	 * @param Role
	 */
	public void resetBattle(RoleBattle r) {
		r.setBattleDayHitStat(new ArrayList<LvNum>()); // 重置每日副本战斗统计
		r.setBattleDayNHitClearCnt(0);
		r.setBattleDayBuyCnt(0);
		r.setBattleDayRefresh(new Date());
		String sql = "update t_role_battle set " + " update_time=now(), " + " battle_day_hit_stat=?,"
				+ " battle_day_nhit_clear_cnt=?," + " battle_day_buy_cnt = ?," + " battle_day_refresh=now()"
				+ " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(r.getBattleDayHitStat()), r.getBattleDayNHitClearCnt(),
				r.getBattleDayBuyCnt(), r.getRoleId());
	}

	public void updateBattle(RoleBattle doc) {
		String sql = "update t_role_battle set " + "battle_world_id=?," + "battle_field_id =?," + "battle_lv_id=?,"
				+ "battle_his=?," + "battle_box=?," + "battle_nhit_time=?," + "battle_day_hit_stat=?,"
				+ "battle_day_nhit_clear_cnt=?," + "movie_battle_ids=?," + "battle_day_buy_cnt=?,"
				+ "battle_star_has_last_time=?," + "battle_total_stars=?" + " where role_id=?";
		executeUpdate(sql, doc.getBattleWorldID(), doc.getBattleFieldID(), doc.getBattleLvID(),
				JSONUtil.toJson(doc.getBattleHis()), JSONUtil.toJson(doc.getBattleBox()), doc.getBattleNHitTime(),
				JSONUtil.toJson(doc.getBattleDayHitStat()), doc.getBattleDayNHitClearCnt(),
				JSONUtil.toJson(doc.getOverMBattles()), doc.getBattleDayBuyCnt(), doc.getBattleStarHasLastTime(),
				doc.getBattleTotalStars(), doc.getRoleId());
	}

	/**
	 * 查询
	 */
	public RoleBattle query(int id) {
		return queryForObject("select * from t_role_battle where role_id=?", HANDLER, id);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_battle where role_id=?", id);
	}
}