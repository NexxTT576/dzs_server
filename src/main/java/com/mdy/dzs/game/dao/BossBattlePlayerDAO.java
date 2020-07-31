package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.boss.BossBattlePlayer;
import com.mdy.dzs.game.domain.boss.BossBubbleVO;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * boss战参与玩家DAO
 * 
 * @author 白雪林
 *
 */
public class BossBattlePlayerDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<BossBattlePlayer> HANDLER = new ResultSetRowHandler<BossBattlePlayer>() {
		@Override
		public BossBattlePlayer handleRow(ResultSetRow row) throws Exception {
			BossBattlePlayer bbp = new BossBattlePlayer();
			bbp.setRoleId(row.getInt("role_id"));
			bbp.setAcc(row.getString("account"));
			bbp.setRoleName(row.getString("role_name"));
			bbp.setTotalHurt(row.getInt("total_hurt"));
			bbp.setHurtAdd(row.getInt("hurt_add"));
			bbp.setLiveCnt(row.getInt("live_cnt"));
			bbp.setBattleWait(row.getTimestamp("battle_wait"));
			bbp.setSilverWait(row.getTimestamp("silver_wait"));
			bbp.setSilverCnt(row.getInt("silver_cnt"));
			bbp.setBattleCnt(row.getInt("battle_cnt"));
			bbp.setCreateTime(row.getTimestamp("create_time"));
			bbp.setUpdateTime(row.getTimestamp("update_time"));
			return bbp;
		}
	};

	/**
	 * 添加
	 * 
	 * @param BossBattlePlayer
	 */
	public void add(String acc, int roleid, String name) {
		String sql = "insert into t_boss_role(" + "role_id," + "account," + "role_name," + "total_hurt," + "hurt_add,"
				+ "live_cnt," + "silver_cnt," + "create_time," + "battle_wait," + "update_time"
				+ ")values(?,?,?,0,0,0,0,now(),now(),now())";
		execute(sql, roleid, acc, name);
	}

	/**
	 * 更新
	 * 
	 * @param BossBattlePlayer
	 */
	public void update(BossBattlePlayer bbp) {
		String sql = "update t_boss_role set " + " update_time=now(), " + " role_name=?," + " total_hurt=?,"
				+ " hurt_add=?," + " live_cnt=?," + " battle_wait=?," + " silver_wait=?," + " silver_cnt=? "
				+ " where role_id=?";

		executeUpdate(sql, bbp.getRoleName(), bbp.getTotalHurt(), bbp.getHurtAdd(), bbp.getLiveCnt(),
				bbp.getBattleWait(), bbp.getSilverWait(), bbp.getSilverCnt(), bbp.getRoleId());
	}

	// 更新攻击次数
	public void updateBattleCnt(int roleId) {
		String sql = "update t_boss_role set battle_cnt=battle_cnt+1,update_time=now() where role_id=?";
		executeUpdate(sql, roleId);
	}

	/**
	 * 查询
	 */
	public BossBattlePlayer query(int roleId) {
		return queryForObject("select * from t_boss_role where role_id=?", HANDLER, roleId);
	}

	/**
	 * 删除
	 */
	public void delete() {
		execute("truncate table t_boss_role");
	}

	public List<BossTopTenVO> getTopTenList() {
		// TODO 自动生成的方法存根
		return null;
	}

	public List<RankList> getRankList() {
		// TODO 自动生成的方法存根
		return null;
	}

	public List<BossBubbleVO> getBubbleList(int roleId) {
		// TODO 自动生成的方法存根
		return null;
	}

	public int updateRankList(String acc, String name, int curhurt, int lv, int roleId, String faction) {
		// TODO 自动生成的方法存根
		return 0;
	}

	public void updateBubbleList(String name, int hurt, int roleId) {
		// TODO 自动生成的方法存根

	}

	public void deleteBossCache() {
		// TODO 自动生成的方法存根

	}
}