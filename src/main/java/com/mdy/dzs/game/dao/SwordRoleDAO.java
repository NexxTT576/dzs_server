package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.swordfight.SwordRole;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 论剑角色DAO
 * 
 * @author zhou
 *
 */
public class SwordRoleDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<SwordRole> HANDLER = new ResultSetRowHandler<SwordRole>() {
		@SuppressWarnings("unchecked")
		@Override
		public SwordRole handleRow(ResultSetRow row) throws Exception {
			SwordRole sr = new SwordRole();
			sr.setId(row.getInt("id"));
			sr.setSelfAccount(row.getString("self_account"));
			sr.setEnemyAccount(row.getString("enemy_account"));
			sr.setResId(row.getInt("resId"));
			sr.setFloor(row.getInt("floor"));
			sr.setCombat(row.getInt("combat"));
			sr.setResetCnt(row.getInt("reset_cnt"));
			sr.setGoldResetCnt(row.getInt("gold_reset_cnt"));
			sr.setCreateTime(row.getTimestamp("createTime"));
			sr.setUpdateTime(row.getTimestamp("updateTime"));
			sr.setEnterTime(row.getTimestamp("enterTime"));
			sr.setAwards(JSONUtil.fromJson(row.getString("awards"), List.class));
			sr.setName(row.getString("name"));
			sr.setScale(row.getFloat("scale"));
			return sr;
		}
	};

	/**
	 * 添加
	 * 
	 * @param SwordRole
	 */
	public void add(SwordRole sr) {
		String sql = "insert into t_sword_role(" + "self_account," + "enemy_account," + "resId," + "floor," + "combat,"
				+ "reset_cnt," + "gold_reset_cnt," + "enterTime," + "awards," + "name," + "createTime," + "updateTime"
				+ ")values(?,?,?,?,?,?,?,?,?,?,now(),now())";
		int id = executeWithGenKey(sql, sr.getSelfAccount(), sr.getEnemyAccount(), sr.getResId(), sr.getFloor(),
				sr.getCombat(), sr.getResetCnt(), sr.getGoldResetCnt(), sr.getEnterTime(),
				JSONUtil.toJson(sr.getAwards()), sr.getName());
		sr.setId(id);
	}

	/**
	 * 更新
	 * 
	 * @param SwordRole
	 */
	public void update(SwordRole sr) {
		String sql = "update t_sword_role set " + " self_account=?," + " enemy_account=?," + " resId=?," + " floor=?,"
				+ " combat=?," + " reset_cnt=?," + " gold_reset_cnt=?," + " enterTime=?, " + " updateTime=now() ,"
				+ " awards=?, " + " name=?, " + " scale=? " + " where id=?";
		execute(sql, sr.getSelfAccount(), sr.getEnemyAccount(), sr.getResId(), sr.getFloor(), sr.getCombat(),
				sr.getResetCnt(), sr.getGoldResetCnt(), sr.getEnterTime(), JSONUtil.toJson(sr.getAwards()),
				sr.getName(), sr.getScale(), sr.getId());
	}

	/**
	 * 查询
	 */
	public SwordRole query(int id) {
		return queryForObject("select * from t_sword_role where id=?", HANDLER, id);
	}

	/**
	 * 删除
	 */
	public void delete(SwordRole srVO) {
		execute("delete  from t_sword_role where id=?", srVO.getId());
	}

	/**
	 * 查找属于自己在论剑中的位置
	 * 
	 * @param selfAcc
	 * @param enemyAcc
	 * @return SwordRole
	 */
	public SwordRole queryBySelfAccount(String selfAcc, String enemyAcc) {
		return queryForObject("select * from t_sword_role where self_account=? and enemy_account=?", HANDLER, selfAcc,
				enemyAcc);
	}

	/**
	 * 根据自己和塔层 找到敌人的数据
	 * 
	 * @param selfAcc
	 * @param Floor
	 * @return
	 */
	public SwordRole queryByFloor(String selfAcc, int floor) {
		return queryForObject("select * from t_sword_role where self_account=? and floor=? and enemy_account !=?",
				HANDLER, selfAcc, floor, selfAcc);
	}

	public List<SwordRole> queryEmemies(String selfAcc) {
		return queryForList("select * from t_sword_role where self_account=? and enemy_account !=?", HANDLER, selfAcc,
				selfAcc);
	}

	/**
	 * 更新层数
	 * 
	 * @param SwordRoleCard
	 */
	public void updateFloor(SwordRole sr) {
		String sql = "update t_sword_role set " + " floor=?," + " updateTime=now() " + " where id=?";
		execute(sql, sr.getFloor(), sr.getId());
	}

	/**
	 * 更新已经领取的奖励层
	 * 
	 * @param SwordRoleCard
	 */
	public void updateAwards(SwordRole sr) {
		String sql = "update t_sword_role set " + " awards=?," + " updateTime=now() " + " where id=?";
		execute(sql, JSONUtil.toJson(sr.getAwards()), sr.getId());
	}
}