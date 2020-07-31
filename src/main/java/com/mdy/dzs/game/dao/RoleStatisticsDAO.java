package com.mdy.dzs.game.dao;

import java.util.Date;

import com.mdy.dzs.game.domain.role.RoleStatistics;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 玩家统计DAO
 * 
 * @author 房曈
 *
 */
public class RoleStatisticsDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleStatistics> HANDLER = new ResultSetRowHandler<RoleStatistics>() {
		@Override
		public RoleStatistics handleRow(ResultSetRow row) throws Exception {
			RoleStatistics rs = new RoleStatistics();
			rs.setRoleId(row.getInt("role_id"));
			rs.setCurGold(row.getInt("cur_gold"));
			rs.setCurDonate(row.getInt("cur_donate"));
			rs.setCurBind(row.getInt("cur_bind"));
			rs.setTotalGold(row.getInt("total_gold"));
			rs.setTotalDonate(row.getInt("total_donate"));
			rs.setTotalBind(row.getInt("total_bind"));
			return rs;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleStatistics
	 */
	public void add(RoleStatistics rs) {
		String sql = "insert into t_role_statistics(" + "role_id," + "account," + "create_time," + "update_time"
				+ ")values(?,?,now(),now())";
		execute(sql, rs.getRoleId(), rs.getAccount());
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_statistics where role_id=?", id);
	}

	/**
	 * @param role
	 * @param chargeOrder
	 * @param data
	 */
	public void updateChargeInfo(int id, float price) {
		String sql = "update t_role_statistics set " + " update_time=now(), " + " charge_price=charge_price+?,"
				+ " last_charge_time=now()," + " charge_cnt=charge_cnt+? " + " where role_id=?";
		executeUpdate(sql, price, 1, id);
	}

	/**
	 * 获取最后一次充值时间
	 * 
	 * @param roleId
	 * @return
	 */
	public Date queryLastChargeTime(int roleId) {
		return queryForDate("select last_charge_time from t_role_statistics where role_id = ?", roleId);
	}

	/**
	 * @param id
	 * @param cnt
	 */
	public void addBind(int id, int cnt) {
		String sql = "update t_role_statistics set " + " update_time=now(), " + " total_bind=total_bind+?,"
				+ " cur_bind=cur_bind+?" + " where role_id=?";
		executeUpdate(sql, cnt, cnt, id);
	}

	/**
	 * @param id
	 * @param cnt
	 */
	public void addGold(int id, int cnt) {
		String sql = "update t_role_statistics set " + " update_time=now(), " + " total_gold=total_gold+?,"
				+ " cur_gold=cur_gold+?" + " where role_id=?";
		executeUpdate(sql, cnt, cnt, id);
	}

	/**
	 * @param id
	 * @param cnt
	 */
	public void addDonate(int id, int cnt) {
		String sql = "update t_role_statistics set " + " update_time=now(), " + " total_donate=total_donate+?,"
				+ " cur_donate=cur_donate+?" + " where role_id=?";
		executeUpdate(sql, cnt, cnt, id);
	}

	/**
	 * @param id
	 * @return
	 */
	public RoleStatistics queryById(int id) {
		return queryForObject("select * from t_role_statistics where role_id=?", HANDLER, id);
	}

	/**
	 * @param rs
	 */
	public void updateGolds(RoleStatistics rs) {
		String sql = "update t_role_statistics set " + " update_time=now(), " + " cur_gold=?," + " cur_donate=?,"
				+ " cur_bind=?" + " where role_id=?";
		executeUpdate(sql, rs.getCurGold(), rs.getCurDonate(), rs.getCurBind(), rs.getRoleId());
	}

}