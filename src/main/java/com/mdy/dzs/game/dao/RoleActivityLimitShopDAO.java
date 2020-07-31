package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.activity.limitshop.LimitShopInfo;
import com.mdy.dzs.game.domain.activity.limitshop.RoleLimitShop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

public class RoleActivityLimitShopDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<RoleLimitShop> HANDLER = new ResultSetRowHandler<RoleLimitShop>() {
		@Override
		public RoleLimitShop handleRow(ResultSetRow row) throws Exception {
			RoleLimitShop rls = new RoleLimitShop();
			rls.setId(row.getInt("id"));
			rls.setRoleId(row.getInt("role_id"));
			List<LimitShopInfo> itemIds = JSONUtil.fromJsonList(row.getString("item_ids"), LimitShopInfo.class);
			rls.setItemIds(itemIds);
			rls.setUpdateTime(row.getTimestamp("update_time"));
			rls.setCreateTime(row.getTimestamp("create_time"));
			rls.setRefreshTime(row.getTimestamp("refresh_time"));
			return rls;
		}
	};

	public void deleteAll() {
		execute("delete from t_role_activity_limitshop");
	}

	/**
	 * 查询单个玩家的兑换信息
	 * 
	 * @param roleId
	 * @return
	 */
	public RoleLimitShop queryShopInfoByRoleId(int roleId) {
		return queryForObject("select * from t_role_activity_limitshop where role_id =?", HANDLER, roleId);
	}

	/**
	 * 创建玩家数据
	 */
	public void createRoleActLimitShop(int roleId, List<LimitShopInfo> shopInfo) {
		String sql = "insert into t_role_activity_limitshop(" + "item_ids," + "role_id," + "update_time, "
				+ "refresh_time, " + "create_time  " + ")values(?,?,now(),now(),now())";
		String paras = JSONUtil.toJson(shopInfo);
		execute(sql, paras, roleId);
	}

	public void updateRoleLShop(int roleId, List<LimitShopInfo> shopInfo, Date rTime) {
		String sql = "update t_role_activity_limitshop set " + " update_time=now(), " + " refresh_time=?, "
				+ " item_ids=? " + " where role_id=?";
		executeUpdate(sql, rTime, JSONUtil.toJson(shopInfo), roleId);

	}
}
