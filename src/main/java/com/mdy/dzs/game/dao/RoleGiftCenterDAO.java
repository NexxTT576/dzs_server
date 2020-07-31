package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.game.domain.giftcenter.RoleGift;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 领奖中心DAO
 * 
 * @author 房曈
 *
 */
public class RoleGiftCenterDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleGift> HANDLER = new ResultSetRowHandler<RoleGift>() {
		@Override
		public RoleGift handleRow(ResultSetRow row) throws Exception {
			RoleGift rgc = new RoleGift();
			rgc.setId(row.getInt("id"));
			rgc.setRoleId(row.getInt("role_id"));
			rgc.setGiftType(row.getInt("gift_type"));
			List<GiftItem> items = JSONUtil.fromJsonList(row.getString("gift_item"), GiftItem.class);
			rgc.setGiftItem(items);
			rgc.setOtherData(row.getString("other_data"));
			rgc.setCreateTime(row.getTimestamp("create_time"));
			return rgc;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleGift
	 */
	public void add(RoleGift rgc) {
		String sql = "insert into t_role_gift_center(" + "role_id," + "gift_type," + "gift_item," + "other_data,"
				+ "create_time" + ")values(?,?,?,?,now())";
		execute(sql, rgc.getRoleId(), rgc.getGiftType(), JSONUtil.toJson(rgc.getGiftItem()), rgc.oldOtherData());
	}

	/**
	 * 查询列表
	 */
	public List<RoleGift> queryList(int roleId) {
		return queryForList("select * from t_role_gift_center where role_id = ?" + " order by create_time desc",
				HANDLER, roleId);
	}

	/**
	 * 查询列表
	 */
	public int queryCount(int roleId) {
		return queryForInteger("select count(*) from t_role_gift_center where role_id = ?", roleId);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_gift_center where id=?", id);
	}

	/**
	 * 删除
	 */
	public void delete(List<Integer> ids) {
		if (ids.size() <= 0) {
			return;
		}
		String str = "";
		for (int i = 0; i < ids.size(); i++) {
			str += ids.get(i) + ",";
		}
		str = str.substring(0, str.length() - 1);
		execute("delete  from t_role_gift_center where id in (" + str + ")");
	}

	public RoleGift query(int id) {
		return queryForObject("select * from t_role_gift_center where id = ?", HANDLER, id);
	}

	/**
	 * @param time
	 */
	public void clearExpiredGift(Date time) {
		execute("delete  from t_role_gift_center where create_time <?", time);
	}
}