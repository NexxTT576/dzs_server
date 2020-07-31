package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.boss.BossBattle;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * boss表结构DAO
 * 
 * @author 白雪林
 *
 */
public class BossBattleDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<BossBattle> HANDLER = new ResultSetRowHandler<BossBattle>() {
		@Override
		public BossBattle handleRow(ResultSetRow row) throws Exception {
			BossBattle bb = new BossBattle();
			bb.setActId(row.getInt("act_id"));
			bb.setName(row.getString("name"));
			bb.setLevel(row.getInt("level"));
			bb.setLifeTotal(row.getInt("life_total"));
			bb.setLife(row.getInt("life"));
			bb.setKillPlayer(row.getInt("kill_player"));
			List<BossTopTenVO> topTen = JSONUtil.fromJsonList(row.getString("top_ten"), BossTopTenVO.class);
			bb.setTopTen(topTen);
			bb.setAward(row.getInt("award"));
			bb.setEndTime(row.getTimestamp("end_time"));
			bb.setCreateTime(row.getTimestamp("create_time"));
			bb.setUpdateTime(row.getTimestamp("update_time"));
			return bb;
		}
	};

	/**
	 * 添加
	 * 
	 * @param BossBattle
	 */
	public void add(BossBattle bb) {
		String sql = "insert into t_act_boss(" +
		// "act_id," +
				"name," + "level," + "life_total," + "life," + "kill_player," + "top_ten," + "create_time,"
				+ "update_time" + ")values(?,?,?,?,0,'[]',now(),now())";

		execute(sql,
				// bb.getActId(),
				bb.getName(), bb.getLevel(), bb.getLifeTotal(), bb.getLife());
	}

	/**
	 * 更新
	 * 
	 * @param BossBattle
	 */
	public void update(BossBattle bb) {
		String sql = "update t_act_boss set " + " update_time=now(), " + " act_id=?," + " name=?," + " level=?,"
				+ " life_total=?," + " end_time=?," + " top_ten=?" + " where act_id=?";

		String topTen = JSONUtil.toJson(bb.getTopTen());
		executeUpdate(sql, bb.getActId(), bb.getName(), bb.getLevel(), bb.getLifeTotal(), bb.getEndTime(), topTen,
				bb.getActId());
	}

	/**
	 * 查询
	 */
	public BossBattle query(int id) {
		return queryForObject("select * from t_act_boss where id=?", HANDLER, id);
	}

	/**
	 * 查询最后一条数据
	 */
	public BossBattle queryLast() {
		return queryForObject("select * from t_act_boss order by act_id desc limit 1", HANDLER);
	}

	/** 更新endTime */
	public void updateEndTime(Date time, int actId) {
		String sql = "update t_act_boss set update_time=now(),end_time=? where act_id=?";
		executeUpdate(sql, time, actId);
	}

	/** 更新kill_player */
	public void updateKiller(int killerId, int actId) {
		String sql = "update t_act_boss set update_time=now(),kill_player=? where act_id=? and kill_player=0";
		executeUpdate(sql, killerId, actId);
	}

	/** 更新life */
	public void updateLife(int damage, int actId) {
		String sql = "update t_act_boss set update_time=now(),life=life-? "
				+ "where act_id=? and kill_player=0 and end_time is null";
		executeUpdate(sql, damage, actId);
	}

	/** 更新topTen */
	public void updateTopTen(List<BossTopTenVO> topTenList, int actId) {
		String sql = "update t_act_boss set update_time=now(),award=1,top_ten=? where act_id=?";
		String topTen = JSONUtil.toJson(topTenList);
		executeUpdate(sql, topTen, actId);
	}

}