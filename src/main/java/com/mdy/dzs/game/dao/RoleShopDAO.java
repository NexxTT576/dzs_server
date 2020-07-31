package com.mdy.dzs.game.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.shop.RoleShop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 角色商城状态DAO
 * 
 * @author 房曈
 *
 */
public class RoleShopDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleShop> HANDLER = new ResultSetRowHandler<RoleShop>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleShop handleRow(ResultSetRow row) throws Exception {
			RoleShop rs = new RoleShop();
			rs.setRoleId(row.getInt("role_id"));
			rs.setWineFreeCntAry(JSONUtil.fromJson(row.getString("wine_free_cnt_ary"), List.class));
			rs.setWineFreeNextTimeAry(JSONUtil.fromJson(row.getString("wine_free_next_time_ary"), List.class));
			rs.setWineGotCntAry(JSONUtil.fromJson(row.getString("wine_got_cnt_ary"), List.class));
			rs.setComShopPurchased(JSONUtil.fromJson(row.getString("com_shop_purchased"), Map.class));
			rs.setWineProbCntAry(JSONUtil.fromJson(row.getString("wine_prob_cnt_ary"), List.class));
			rs.setBoxProbCntAry(JSONUtil.fromJson(row.getString("box_prob_cnt_ary"), Map.class));
			rs.setShopDayRefresh(row.getTimestamp("shop_day_refresh"));
			rs.setCreateTime(row.getTimestamp("create_time"));
			rs.setUpdateTime(row.getTimestamp("update_time"));
			return rs;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleShop
	 */
	public void add(int roleId) {
		Date date = new Date();
		String sql = "insert into t_role_shop(" + "role_id," + "wine_free_next_time_ary," + "shop_day_refresh,"
				+ "create_time," + "update_time" + ")values(?,?,now(),now(),now())";
		execute(sql, roleId, JSONUtil.toJson(Arrays.asList(0, 0, (date.getTime() / 1000 + 1800))));
	}

	/**
	 * 更新
	 * 
	 * @param RoleShop
	 */
	public void update(RoleShop rs) {
		String sql = "update t_role_shop set " + " update_time=now(), " + " wine_free_cnt_ary=?,"
				+ " wine_free_next_time_ary=?," + " wine_got_cnt_ary=?," + " com_shop_purchased=?,"
				+ " wine_prob_cnt_ary=?," + " box_prob_cnt_ary=?," + " shop_day_refresh=? " + " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(rs.getWineFreeCntAry()), JSONUtil.toJson(rs.getWineFreeNextTimeAry()),
				JSONUtil.toJson(rs.getWineGotCntAry()), JSONUtil.toJson(rs.getComShopPurchased()),
				JSONUtil.toJson(rs.getWineProbCntAry()), JSONUtil.toJson(rs.getBoxProbCntAry()), rs.getShopDayRefresh(),
				rs.getRoleId());
	}

	/**
	 * 查询
	 */
	public RoleShop query(int id) {
		return queryForObject("select * from t_role_shop where role_id=?", HANDLER, id);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_shop where role_id=?", HANDLER, id);
	}
}