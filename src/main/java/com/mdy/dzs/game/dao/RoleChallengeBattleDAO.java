package com.mdy.dzs.game.dao;

import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.challenge.RoleActivityBattle;
import com.mdy.dzs.game.domain.challenge.RoleEliteBattle;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 挑战战斗DAO
 * 
 * @author 房曈
 *
 */
public class RoleChallengeBattleDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleEliteBattle> ELITE_HANDLER = new ResultSetRowHandler<RoleEliteBattle>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleEliteBattle handleRow(ResultSetRow row) throws Exception {
			RoleEliteBattle r = new RoleEliteBattle();
			r.setRoleId(row.getInt("role_id"));
			r.setEliteBattleLv(row.getInt("elite_battle_lv"));
			r.setEliteNxtLv(row.getInt("elite_nxt_lv"));
			r.setEliteNxtLvState(row.getInt("elite_nxt_lv_state"));
			r.setEliteNxtLvRevive(row.getInt("elite_nxt_lv_revive"));
			r.setEliteNxtLvDPos(JSONUtil.fromJson(row.getString("elite_nxt_lv_dpos"), List.class));
			r.setEliteTodayCnt(row.getInt("elite_today_cnt"));
			r.setEliteBuyCnt(row.getInt("elite_buy_cnt"));
			r.setEliteDayRefresh(row.getTimestamp("elite_day_refresh"));
			r.setCreateTime(row.getTimestamp("create_time"));
			r.setUpdateTime(row.getTimestamp("update_time"));
			return r;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Role
	 */
	public void addEliteBattle(Role r) {
		String sql = "insert into t_role_elite_battle(" + "role_id,elite_day_refresh,create_time,update_time"
				+ ")values(?,now(),now(),now())";
		execute(sql, r.getId());
	}

	/**
	 * 更新精英副本(不会更新当日相关次数)
	 * 
	 * @param Role
	 */
	public void updateEliteBattle(RoleEliteBattle r) {
		String sql = "update t_role_elite_battle set " + " elite_battle_lv=?," + " elite_nxt_lv=?,"
				+ " elite_nxt_lv_state=?," + " elite_nxt_lv_revive=?," + " elite_nxt_lv_dpos=?," + " elite_buy_cnt=?,"
				+ " update_time=now() " + " where role_id=?";
		executeUpdate(sql, r.getEliteBattleLv(), r.getEliteNxtLv(), r.getEliteNxtLvState(), r.getEliteNxtLvRevive(),
				JSONUtil.toJson(r.getEliteNxtLvDPos()), r.getEliteBuyCnt(), r.getRoleId());
	}

	/**
	 * 查询
	 * 
	 * @param roleId
	 * @return
	 */
	public RoleEliteBattle queryEliteBattleByRoleId(int roleId) {
		return queryForObject("select * from t_role_elite_battle where role_id=?", ELITE_HANDLER, roleId);
	}

	/**
	 * 重置精英副本 0点
	 * 
	 * @param roldId
	 */
	public void resetEliteBattle(int roleId) {
		String sql = "update t_role_elite_battle set " + " elite_today_cnt=0," + " elite_buy_cnt=0,"
				+ " elite_day_refresh=now()," + " update_time=now()  where role_id=?";
		executeUpdate(sql, roleId);
	}

	/**
	 * 进行一次精英副本 0点刷新
	 * 
	 * @param elite
	 */
	public void useEliteBattleCnt(RoleEliteBattle elite) {
		executeUpdate("update t_role_elite_battle set " + "elite_today_cnt = elite_today_cnt+1, " + " update_time=now()"
				+ " where role_id=?", elite.getRoleId());
	}

	/**
	 * 购买一次精英副本次数 0点刷新
	 * 
	 * @param elite
	 */
	public void buyEliteBattleCnt(RoleEliteBattle elite) {
		executeUpdate("update t_role_elite_battle set " + "elite_buy_cnt = elite_buy_cnt+1, " + " update_time=now()"
				+ " where role_id=?", elite.getRoleId());
	}

	////////////////////////////////////////////////////////////////////////////

	private static ResultSetRowHandler<RoleActivityBattle> ACTIVITY_HANDLER = new ResultSetRowHandler<RoleActivityBattle>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleActivityBattle handleRow(ResultSetRow row) throws Exception {
			RoleActivityBattle r = new RoleActivityBattle();
			r.setRoleId(row.getInt("role_id"));
			r.setActBuyCnts(JSONUtil.fromJson(row.getString("act_buy_cnts"), Map.class));
			r.setActPveCnts(JSONUtil.fromJson(row.getString("act_pve_cnts"), Map.class));
			r.setActPveSteps(JSONUtil.fromJson(row.getString("act_pve_steps"), Map.class));
			r.setActNxtLvDPos(JSONUtil.fromJson(row.getString("act_nxt_lv_dpos"), List.class));
			r.setActUseItemCnts(JSONUtil.fromJson(row.getString("act_useitem_cnts"), Map.class));
			r.setActDayRefresh(row.getTimestamp("act_day_refresh"));
			r.setCreateTime(row.getTimestamp("create_time"));
			r.setUpdateTime(row.getTimestamp("update_time"));
			return r;
		}
	};

	/**
	 * 查询
	 * 
	 * @param roleId
	 * @return
	 */
	public RoleActivityBattle queryActBattle(int roleId) {
		return queryForObject("select * from t_role_activity_battle where role_id=?", ACTIVITY_HANDLER, roleId);
	}

	/**
	 * 重置活动副本 0点
	 * 
	 * @param roldId
	 */
	public void resetActivityBattle(int roleId) {
		String sql = "update t_role_activity_battle set " + " act_pve_cnts='{}'," + " act_buy_cnts='{}',"
				+ " act_useitem_cnts='{}'," + " act_day_refresh=now()," + " update_time=now() where role_id=?";
		executeUpdate(sql, roleId);
	}

	/**
	 * 更新活动副本(不会更新当日相关次数)
	 * 
	 * @param Role
	 */
	public void updateActivityBattle(RoleActivityBattle r) {
		String sql = "update t_role_activity_battle set " + " act_pve_cnts=?," + " act_useitem_cnts=?,"
				+ " act_buy_cnts=?," + " act_pve_steps=?," + " act_nxt_lv_dpos=?," + " update_time=now() "
				+ " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(r.getActPveCnts()), JSONUtil.toJson(r.getActUseItemCnts()),
				JSONUtil.toJson(r.getActBuyCnts()), JSONUtil.toJson(r.getActPveSteps()),
				JSONUtil.toJson(r.getActNxtLvDPos()), r.getRoleId());
	}

	/**
	 * 添加
	 * 
	 * @param Role
	 */
	public void addActivityBattle(Role r) {
		String sql = "insert into t_role_activity_battle(" + "role_id,act_day_refresh,create_time,update_time"
				+ ")values(?,now(),now(),now())";
		execute(sql, r.getId());
	}
}