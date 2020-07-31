package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.monthcard.RoleMonthCard;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 角色月卡模型DAO
 * 
 * @author 白雪林
 *
 */
public class RoleMonthCardDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<RoleMonthCard> HANDLER = new ResultSetRowHandler<RoleMonthCard>() {
		@Override
		public RoleMonthCard handleRow(ResultSetRow row) throws Exception {
			RoleMonthCard rmc = new RoleMonthCard();
			rmc.setRoleId(row.getInt("role_id"));
			rmc.setGetTime(row.getTimestamp("get_time"));
			rmc.setOverTime(row.getTimestamp("over_time"));
			rmc.setCreateTime(row.getTimestamp("create_time"));
			rmc.setUpdateTime(row.getTimestamp("update_time"));
			return rmc;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleMonthCard
	 */
	public void add(int roleid) {
		Date now = new Date();
		Date ysDate = DateUtil.getInternalDateByDay(now, +7);

		String sql = "insert into t_role_month_card(" + "role_id," + "get_time," + "over_time," + "create_time,"
				+ "update_time" + ")values(?,0,?,now(),now())";
		execute(sql, roleid, ysDate);
	}

	/**
	 * 更新
	 * 
	 * @param RoleMonthCard
	 */
	public void updateGetTime(RoleMonthCard rmc) {
		String sql = "update t_role_month_card set " + " update_time=now(), " + " get_time=?" + " where role_id=?";
		executeUpdate(sql, rmc.getGetTime(), rmc.getRoleId());
	}

	/**
	 * 查询
	 */
	public RoleMonthCard query(int id) {
		return queryForObject("select * from t_role_month_card where role_id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleMonthCard> queryList() {
		return queryForList("select * from t_role_month_card", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_month_card where role_id=?", HANDLER, id);
	}

	public void updateOverTime(RoleMonthCard rmc) {
		String sql = "update t_role_month_card set " + " update_time=now(), " + " over_time=?" + " where role_id=?";

		executeUpdate(sql, rmc.getOverTime(), rmc.getRoleId());
	}

}