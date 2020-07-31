package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.boss.BossBubbleVO;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.domain.union.UnionBossPlayer;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;


/**
 * 帮派青龙殿DAO
 * @author 白雪林
 *
 */
public class UnionBossPlayerDAO extends ConnectionResourceDAO{


	private static ResultSetRowHandler<UnionBossPlayer>HANDLER=
		new ResultSetRowHandler<UnionBossPlayer>() {
			@Override
			public UnionBossPlayer handleRow(ResultSetRow row) throws Exception {
				UnionBossPlayer ubp=new UnionBossPlayer();
				ubp.setRoleId(row.getInt("role_id"));
				ubp.setAccount(row.getString("account"));
				ubp.setUnionId(row.getInt("union_id"));
				ubp.setRoleName(row.getString("role_name"));
				ubp.setTotalHurt(row.getInt("total_hurt"));
				ubp.setHurtAdd(row.getInt("hurt_add"));
				ubp.setBattleCnt(row.getInt("battle_cnt"));
				ubp.setLiveCnt(row.getInt("live_cnt"));
				ubp.setBattleWait(row.getTimestamp("battle_wait"));
				ubp.setSilverWait(row.getTimestamp("silver_wait"));
				ubp.setSilverCnt(row.getInt("silver_cnt"));
				ubp.setCreateTime(row.getTimestamp("create_time"));
				ubp.setUpdateTime(row.getTimestamp("update_time"));
				return ubp;
			}
		};

	//更新
	public void update(UnionBossPlayer ubp){
		String sql="update t_union_boss_player set " +
			" update_time=now(), " +
			" total_hurt=?," +
			" hurt_add=?," +
			" battle_cnt=?," +
			" live_cnt=?," +
			" battle_wait=?," +
			" silver_wait=?," +
			" silver_cnt=? " +
			" where role_id=? and union_id=? ";
		executeUpdate(sql,
			ubp.getTotalHurt(),
			ubp.getHurtAdd(),
			ubp.getBattleCnt(),
			ubp.getLiveCnt(),
			ubp.getBattleWait(),
			ubp.getSilverWait(),
			ubp.getSilverCnt(),
			ubp.getRoleId(),
			ubp.getUnionId());
	}
	
	/**
	 * 查询列表
	 */
	public List<UnionBossPlayer> queryList(){
		return queryForList("select * from t_union_boss_player",HANDLER);
	}

	public void add(String acc, int roleid, String name, int unionId){
		String sql="insert into t_union_boss_player(" +
			"role_id," +
			"account," +
			"union_id," +
			"role_name," +
			"total_hurt," +
			"hurt_add," +
			"live_cnt," +
			"silver_cnt," +
			"create_time," +
			"battle_wait," +
			"update_time" +
			")values(?,?,?,?,0,0,0,0,now(),now(),now())";
		execute(sql, roleid, acc, unionId, name);
	}
	//取自缓存	topTen
	public List<BossTopTenVO> getTopTenList(int unionId) {
		return null;
	}
	//删除该帮派之前的boss活动参数玩家数据
	public void delete(int unionId){
		execute("delete from t_union_boss_player where union_id=?",unionId);
	}
	//删除一个玩家数据 roleId
	public void deleteOne(int roleId) {
		execute("delete from t_union_boss_player where role_id=?",roleId);
	}
	//清除缓存数据
	public void deleteBossCache(int unionId) {
		//cache override
	}
	//查询一个玩家数据  roleId and unionId
	public UnionBossPlayer query(int roleId, int unionId){
		return queryForObject(
			"select * from t_union_boss_player where role_id=? and union_id=? ",
			HANDLER, roleId, unionId);
	}
	//查询一个玩家，roleId
	public UnionBossPlayer queryOne(int roleId) {
		return queryForObject(
			"select * from t_union_boss_player where role_id=? ",
			HANDLER, roleId);
	}
	//获取排名列表 cache
	public List<RankList> getRankList(int unionId) {
		return null;
	}
	//获取冒字列表
	public List<BossBubbleVO> getBubbleList(int roleId, int unionId, int refreshDis) {
		return null;
	}
	//更新排行
	public int updateRankList(String account, String name, int damageTotal,
			int roleLevel, int roleId, int unionId, String faction) {
		return 0;
	}
	//更新冒泡列表
	public void updateBubbleList(String name, int damageTotal, int id,
			int unionId, int rtnNum, int bubbleDis) {
		
	}
	//更新攻击次数
	public void updateBattleCnt(int roleId, int unionId) {
		String sql = "update t_union_boss_player set battle_cnt=battle_cnt+1,update_time=now() where role_id=? and union_id=? ";
		executeUpdate(sql,roleId,unionId);
	}

}