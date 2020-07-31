package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.activity.limitshop.LimitShop;
import com.mdy.dzs.game.domain.activity.limitshop.LimitShopInfo;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

public class ActivityLimitShopDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<LimitShop> HANDLER = new ResultSetRowHandler<LimitShop>() {
		@Override
		public LimitShop handleRow(ResultSetRow row) throws Exception {
			LimitShop ls = new LimitShop();
			List<LimitShopInfo> itemInfo = JSONUtil.fromJsonList(row.getString("item_info"), LimitShopInfo.class);
			ls.setId(row.getInt("id"));
			ls.setItemInfo(itemInfo);
			ls.setUpdateTime(row.getTimestamp("update_time"));
			ls.setCreateTime(row.getTimestamp("create_time"));
			ls.setRefreshTime(row.getTimestamp("refresh_time"));
			return ls;
		}
	};

	/**
	 * 查询商品道具信息
	 * 
	 * @return
	 */
	public LimitShop queryLimitShopInfo() {
		return queryForObject("select * from t_activity_limitshop ", HANDLER);
	}

	/**
	 * 清空所有数据
	 */
	public void deleteAll() {
		execute("delete from t_activity_limitshop");
	}

	/**
	 * 更新商店
	 */
	public void updateLimitShop(List<LimitShopInfo> shopInfo) {
		String sql = "update t_activity_limitshop set " + " update_time=now() ," + " refresh_time=now(), "
				+ " item_info =? " + " where id =1";
		executeUpdate(sql, JSONUtil.toJson(shopInfo));
	}

	/**
	 * 创建商店信息
	 * 
	 * @param shopInfo
	 */
	public void createLimitShop(List<LimitShopInfo> shopInfo) {
		String sql = "insert into t_activity_limitshop(" + "id," + "item_info," + "update_time," + "create_time,"
				+ "refresh_time " + ")values(1,?,now(),now(),now())";
		execute(sql, JSONUtil.toJson(shopInfo));

	}
}
