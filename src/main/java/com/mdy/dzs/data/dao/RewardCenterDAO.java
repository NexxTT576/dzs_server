package com.mdy.dzs.data.dao;

import java.util.Calendar;
import java.util.List;

import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.data.domain.reward.RewardCenter;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 奖励中心DAO
 * 
 * @author 房曈
 *
 */
public class RewardCenterDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RewardCenter> HANDLER = new ResultSetRowHandler<RewardCenter>() {
		@SuppressWarnings("unchecked")
		@Override
		public RewardCenter handleRow(ResultSetRow row) throws Exception {
			RewardCenter rc = new RewardCenter();
			rc.setId(row.getInt("id"));
			rc.setCondition(row.getInt("condition"));
			rc.setParams(JSONUtil.fromJson(row.getString("params"), List.class));
			List<GiftItem> items = JSONUtil.fromJsonList(row.getString("reward_item"), GiftItem.class);
			rc.setRewardItem(items);
			rc.setRewardMsg(row.getString("reward_msg"));
			rc.setRewardServerId(row.getString("reward_server_id"));
			rc.setDesc(row.getString("desc"));
			rc.setRewardTime(row.getTimestamp("reward_time"));
			rc.setCreateTime(row.getTimestamp("create_time"));
			return rc;
		}
	};

	/**
	 * 查询列表
	 */
	public List<RewardCenter> queryListInRewardTime(String serverName) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		return queryForList(
				"select * from t_reward_center where reward_time = ? "
						+ "and (reward_server_id = ? or reward_server_id = ?)",
				HANDLER, calendar.getTime(), "ALL", serverName);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_reward_center where id=?", id);
	}
}