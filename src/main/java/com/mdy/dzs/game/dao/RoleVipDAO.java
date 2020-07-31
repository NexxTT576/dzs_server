package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.vip.RoleVip;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * vipDAO
 * 
 * @author 白雪林
 *
 */

public class RoleVipDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleVip> HANDLER = new ResultSetRowHandler<RoleVip>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleVip handleRow(ResultSetRow row) throws Exception {
			RoleVip v = new RoleVip();
			v.setRole_id(row.getInt("role_id"));
			v.setFirstGift(row.getInt("first_gift"));
			v.setBuyCountMap(JSONUtil.fromJson(row.getString("buy_count_map"), Map.class));
			v.setCurExp(row.getInt("cur_exp"));
			v.setVipdaygift(row.getTimestamp("vip_day_gift"));
			v.setGetlevelgift(JSONUtil.fromJson(row.getString("get_level_gift"), List.class));
			return v;
		}
	};

	/**
	 * 查询
	 */
	public RoleVip query(int id) {
		return queryForObject("select * from t_role_vip where role_id=?", HANDLER, id);
	}

	/**
	 * 添加
	 * 
	 * @param RoleVip
	 */
	public void add(int roleId) {
		String sql = "insert into t_role_vip (" + "role_id," + "first_gift," + "buy_count_map," + "cur_exp,"
				+ "get_level_gift," + "vip_day_gift," + "create_time," + "update_time"
				+ ") values (?,0,'{}',0,'[]',0,now(),now())";
		execute(sql, roleId);
	}

	/**
	 * 更新 除每日福利领取时间
	 * 
	 * @param RoleVip
	 */
	public void update(RoleVip r) {
		String sql = "update t_role_vip set " + "first_gift=?," + "buy_count_map=?," + "cur_exp=?,"
				+ "get_level_gift=?," + "update_time=now()" + " where role_id=?";
		executeUpdate(sql, r.getFirstGift(), JSONUtil.toJson(r.getBuyCountMap()), r.getCurExp(),
				JSONUtil.toJson(r.getGetlevelgift()), r.getRole_id());
	}

	/**
	 * 已领取VIP等级礼包List更新
	 */
	public void updateGetlevelgift(RoleVip vip) {
		String sql = "update t_role_vip set " + " get_level_gift=?," + " update_time=now()" + " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(vip.getGetlevelgift()), vip.getRole_id());
	}

	public void updateVipDayGiftTime(RoleVip vip) {
		Date now = new Date();
		String sql = "update t_role_vip set " + " vip_day_gift=?," + " update_time=?" + " where role_id=?";
		executeUpdate(sql, now, now, vip.getRole_id());
	}

	public RoleVip queryByAccount(int id) {
		return queryForObject("select * from t_role_vip where role_id=?", HANDLER, id);
	}

}
