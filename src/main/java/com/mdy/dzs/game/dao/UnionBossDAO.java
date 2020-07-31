package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.union.UnionBoss;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;


/**
 * 青龙殿boss战DAO
 * @author 白雪林
 *
 */
public class UnionBossDAO extends ConnectionResourceDAO{


	private static ResultSetRowHandler<UnionBoss>HANDLER=
		new ResultSetRowHandler<UnionBoss>() {
			@Override
			public UnionBoss handleRow(ResultSetRow row) throws Exception {
				UnionBoss ub=new UnionBoss();
				ub.setActId(row.getInt("act_id"));
				ub.setUnionId(row.getInt("union_id"));
				ub.setName(row.getString("name"));
				ub.setLevel(row.getInt("level"));
				ub.setLifeTotal(row.getInt("life_total"));
				ub.setLife(row.getInt("life"));
				ub.setKiller(row.getInt("killer"));
				List<BossTopTenVO> topTen = 
						JSONUtil.fromJsonList(row.getString("top_ten"),BossTopTenVO.class);		
				ub.setTopTen(topTen);
				ub.setEndTime(row.getTimestamp("end_time"));
				ub.setAward(row.getInt("award"));
				ub.setCreateTime(row.getTimestamp("create_time"));
				ub.setUpdateTime(row.getTimestamp("update_time"));
				return ub;
			}
		};
	/**
	 * 添加
	 * @param UnionBoss
	 */
	public void add(UnionBoss ub){
		String sql="insert into t_union_boss(" +
			"union_id," +
			"name," +
			"level," +
			"life_total," +
			"life, " +
			"create_time, " +
			"update_time" +
			")values(?,?,?,?,?,now(),now())";
		execute(sql,
			ub.getUnionId(),
			ub.getName(),
			ub.getLevel(),
			ub.getLifeTotal(),
			ub.getLife());
	}
	/**
	 * 更新
	 * @param UnionBoss
	 */
	public void update(UnionBoss ub){
		String sql="update t_union_boss set " +
			" update_time=now(), " +
			" life_total=?," +
			" life=?," +
			" killer=?," +
			" top_ten=?," +
			" award=? " +
			" where act_id=?";
		String topTen = JSONUtil.toJson(ub.getTopTen());
		executeUpdate(sql,
			ub.getLifeTotal(),
			ub.getLife(),
			ub.getKiller(),
			topTen,
			ub.getAward(),
			ub.getActId());
	}
	//查询某帮派最后一条数据
	public UnionBoss queryLast(int unionId) {
		return queryForObject(
				"select * from t_union_boss where union_id=? order by act_id desc limit 1",
				HANDLER, unionId);
	}
	//更新结束时间
	public void updateEndTime(Date time, int actId) {
		String sql = "update t_union_boss set update_time=now(),end_time=? where act_id=?";
		executeUpdate(sql,time,actId);
	}
	//更新当前血量
	public void updateLife(int damage, int actId) {
		String sql = "update t_union_boss set update_time=now(),life=life-? " + 
				"where act_id=? and killer=0 and end_time is null";
		executeUpdate(sql,damage,actId);
	}
	//更新击杀者
	public void updateKiller(int roleId, int actId) {
		String sql = "update t_union_boss set update_time=now(),killer=? where act_id=? and killer=0";
		executeUpdate(sql,roleId,actId);
	}
	//更新前十名
	public void updateTopTen(List<BossTopTenVO> topTenList, int actId) {
		String sql = "update t_union_boss set update_time=now(),award=1,top_ten=? where act_id=?";
		String topTen = JSONUtil.toJson(topTenList);
		executeUpdate(sql,topTen,actId);
	}
	//查询未发奖列表
	public List<UnionBoss> queryUnawardList(){
		return queryForList("SELECT * from t_union_boss where award = 0 and (TIMESTAMPDIFF(SECOND,create_time,NOW())>=900 or end_time is not null); ",HANDLER);
	}
	/**
	 * 查询
	 */
	public UnionBoss query(int actId){
		return queryForObject(
			"select * from t_union_boss where act_id=?",
			HANDLER, actId);
	}
	/**
	 * 删除
	 */
	public void delete(int id){
		execute("delete  from t_union_boss where id=?",HANDLER,id);
	}
	

	


}