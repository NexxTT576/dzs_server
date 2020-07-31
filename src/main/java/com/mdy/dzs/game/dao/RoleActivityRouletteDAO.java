package com.mdy.dzs.game.dao;

import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.activity.roulettegame.RoleActivityRoulette;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 皇宫探宝数据表DAO
 * 
 * @author 白雪林
 *
 */
public class RoleActivityRouletteDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleActivityRoulette> HANDLER = new ResultSetRowHandler<RoleActivityRoulette>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleActivityRoulette handleRow(ResultSetRow row) throws Exception {
			RoleActivityRoulette rar = new RoleActivityRoulette();
			rar.setRoleId(row.getInt("role_id"));
			rar.setCredit(row.getInt("credit"));
			rar.setSurTimes(row.getInt("sur_times"));
			rar.setRouletteTimes(row.getInt("roulette_times"));
			rar.setDayAdd(row.getInt("day_add"));
			rar.setGetBox(JSONUtil.fromJson(row.getString("get_box"), List.class));
			rar.setFreeTimes(row.getInt("free_times"));
			rar.setRefreshTime(row.getTimestamp("refresh_time"));
			rar.setCreateTime(row.getTimestamp("create_time"));
			rar.setUpdateTime(row.getTimestamp("update_time"));
			rar.setAllRouletteCnt(row.getInt("all_roulette_cnt"));
			rar.setPositionAdd(row.getInt("position_add"));
			rar.setNextPropLine(row.getInt("next_prop_line"));
			return rar;
		}
	};

	// 添加
	public void add(int roleId) {
		String sql = "insert into t_role_activity_roulette(" + "role_id," + "create_time," + "update_time"
				+ ")values(?,now(),now())";
		execute(sql, roleId);
	}

	/**
	 * 更新
	 * 
	 * @param RoleActivityRoulette
	 */
	public void update(RoleActivityRoulette rar) {
		String sql = "update t_role_activity_roulette set " + " update_time=now(), " + " credit=?," + " sur_times=?,"
				+ " roulette_times=?," + " day_add=?," + " get_box=?," + " free_times=?," + " refresh_time=? "
				+ " where role_id=?";
		executeUpdate(sql, rar.getCredit(), rar.getSurTimes(), rar.getRouletteTimes(), rar.getDayAdd(), rar.getGetBox(),
				rar.getFreeTimes(), rar.getRefreshTime(), rar.getRoleId());
	}

	// 查询
	public RoleActivityRoulette query(int id) {
		return queryForObject("select * from t_role_activity_roulette where role_id=?", HANDLER, id);
	}

	// 清空表
	public void clearRouletteDatas() {
		execute("truncate table t_role_activity_roulette");
	}

	// 每日重置
	public void dayRefresh(int addFreeTimes, RoleActivityRoulette player) {
		String sql = "update t_role_activity_roulette set " + " update_time=now(), " + " sur_times=?,"
				+ " roulette_times=0," + " day_add=0," + " free_times=?," + " refresh_time=now()" + " where role_id=?";
		executeUpdate(sql, player.getSurTimes() + addFreeTimes, addFreeTimes, player.getRoleId());
	}

	// 更新积分奖励list
	public void updateGetBox(RoleActivityRoulette rRoleDoc) {
		String sql = "update t_role_activity_roulette set " + " update_time=now(), " + " get_box=? "
				+ " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(rRoleDoc.getGetBox()), rRoleDoc.getRoleId());
	}

	// 探宝后更新数据
	public void updateRouletteData(RoleActivityRoulette rar) {
		String sql = "update t_role_activity_roulette set " + " update_time=now(), " + " credit=?," + " sur_times=?,"
				+ " roulette_times=?," + " all_roulette_cnt=?," + " free_times=?," + " position_add=?,"
				+ " next_prop_line=? " + " where role_id=?";

		executeUpdate(sql, rar.getCredit(), rar.getSurTimes(), rar.getRouletteTimes(), rar.getAllRouletteCnt(),
				rar.getFreeTimes(), rar.getPositionAdd(), rar.getNextPropLine(), rar.getRoleId());
	}

	// 每日充值或消耗数据更新
	public void dayAddUpdate(RoleActivityRoulette rar) {
		String sql = "update t_role_activity_roulette set " + " update_time=now(), " + " sur_times=?," + " day_add=?"
				+ " where role_id=?";
		executeUpdate(sql, rar.getSurTimes(), rar.getDayAdd(), rar.getRoleId());
	}

	// 购买次数类型每日重置
	public void dayRefreshBuy(int addFreeTimes, RoleActivityRoulette player) {
		String sql = "update t_role_activity_roulette set " + " update_time=now(), " + " roulette_times=0,"
				+ " free_times=?," + " sur_times=?," + " refresh_time=now()" + " where role_id=?";
		executeUpdate(sql, addFreeTimes, player.getSurTimes(), player.getRoleId());
	}

}