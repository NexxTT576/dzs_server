package com.mdy.dzs.game.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.activity.mazegame.RoleActivityMaze;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

import flexjson.JSONDeserializer;

/**
 * 迷宫寻宝表结构DAO
 * 
 * @author 白雪林
 *
 */
public class RoleActivityMazeDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleActivityMaze> HANDLER = new ResultSetRowHandler<RoleActivityMaze>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleActivityMaze handleRow(ResultSetRow row) throws Exception {
			RoleActivityMaze ram = new RoleActivityMaze();
			ram.setRoleId(row.getInt("role_id"));
			ram.setDigFreeCnt(row.getInt("dig_free_cnt"));
			ram.setDigCnt(row.getInt("dig_cnt"));
			JSONDeserializer deser = new JSONDeserializer().use(null, HashMap.class).use("values", ProbItem.class);
			Map<String, ProbItem> map = (Map<String, ProbItem>) deser.deserialize(row.getString("treasury_map"));
			ram.setTreasuryMap(map);
			ram.setFreeTimes(row.getInt("free_times"));
			ram.setSurGoldTimes(row.getInt("sur_gold_times"));
			ram.setGetTreasuryList(JSONUtil.fromJson(row.getString("get_treasury_list"), List.class));
			ram.setNextLine(row.getInt("next_line"));
			ram.setPositionList(JSONUtil.fromJson(row.getString("position_list"), List.class));
			ram.setProbList(JSONUtil.fromJson(row.getString("prob_list"), List.class));
			ram.setRefreshTime(row.getTimestamp("refresh_time"));
			ram.setCreateTime(row.getTimestamp("create_time"));
			ram.setUpdateTime(row.getTimestamp("update_time"));
			return ram;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleActivityMaze
	 */
	public void add(int roleId) {
		String sql = "insert into t_role_activity_maze(" + "role_id," + "create_time," + "update_time"
				+ ")values(?,now(),now())";
		execute(sql, roleId);
	}

	/**
	 * 查询
	 */
	public RoleActivityMaze query(int roleId) {
		return queryForObject("select * from t_role_activity_maze where role_id=?", HANDLER, roleId);
	}

	// truncate
	public void clearMazeDatas() {
		execute("truncate table t_role_activity_maze");
	}

	// day reset
	public void dayRefresh(RoleActivityMaze player) {
		String sql = "update t_role_activity_maze set " + " update_time=now(), " + " refresh_time=now(), "
				+ " next_line=0, " + " refresh_time=now(), " + " dig_free_cnt=?," + " dig_cnt=?," + " free_times=?,"
				+ " treasury_map=?," + " get_treasury_list=?," + " position_list=?," + " prob_list=?,"
				+ " sur_gold_times=? " + " where role_id=?";
		executeUpdate(sql, player.getDigFreeCnt(), player.getDigCnt(), player.getFreeTimes(),
				JSONUtil.toJson(player.getTreasuryMap()), JSONUtil.toJson(player.getGetTreasuryList()),
				JSONUtil.toJson(new ArrayList<Integer>()), JSONUtil.toJson(new ArrayList<Integer>()),
				player.getSurGoldTimes(), player.getRoleId());
	}

	// 宝库刷新
	public void itemRefresh(RoleActivityMaze player) {
		String sql = "update t_role_activity_maze set " + " update_time=now(), " + " next_line=0, " + " dig_free_cnt=?,"
				+ " dig_cnt=?," + " treasury_map=?," + " get_treasury_list=?," + " position_list=?," + " prob_list=? "
				+ " where role_id=?";
		executeUpdate(sql, player.getDigFreeCnt(), player.getDigCnt(), JSONUtil.toJson(player.getTreasuryMap()),
				JSONUtil.toJson(player.getGetTreasuryList()), JSONUtil.toJson(new ArrayList<Integer>()),
				JSONUtil.toJson(new ArrayList<Integer>()), player.getRoleId());
	}

	// update
	public void update(RoleActivityMaze player) {
		String sql = "update t_role_activity_maze set " + " update_time=now(), " + " next_line=0, " + " dig_free_cnt=?,"
				+ " dig_cnt=?," + " treasury_map=?," + " get_treasury_list=?," + " position_list=?," + " prob_list=?,"
				+ " free_times=?," + " sur_gold_times=?," + " next_line=? " + " where role_id=?";
		executeUpdate(sql, player.getDigFreeCnt(), player.getDigCnt(), JSONUtil.toJson(player.getTreasuryMap()),
				JSONUtil.toJson(player.getGetTreasuryList()), JSONUtil.toJson(player.getPositionList()),
				JSONUtil.toJson(player.getProbList()), player.getFreeTimes(), player.getSurGoldTimes(),
				player.getNextLine(), player.getRoleId());
	}

}