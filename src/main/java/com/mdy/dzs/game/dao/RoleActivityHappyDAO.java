package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.activity.happygame.RoleActivityHappy;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 累积登录礼包DAO
 * 
 * @author 白雪林
 *
 */
public class RoleActivityHappyDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleActivityHappy> HANDLER = new ResultSetRowHandler<RoleActivityHappy>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleActivityHappy handleRow(ResultSetRow row) throws Exception {
			RoleActivityHappy ah = new RoleActivityHappy();
			ah.setRoleId(row.getInt("role_id"));
			ah.setGetList(JSONUtil.fromJson(row.getString("get_list"), List.class));
			ah.setLoginCnt(row.getInt("login_cnt"));
			ah.setCreateTime(row.getTimestamp("create_time"));
			ah.setUpdateTime(row.getTimestamp("update_time"));
			return ah;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleActivityHappy
	 */
	public void add(int roleId) {
		String sql = "insert into t_role_activity_happy(" + "role_id," + "login_cnt," + "create_time," + "update_time"
				+ ")values(?,0,now(),now())";
		execute(sql, roleId);
	}

	/**
	 * 查询
	 */
	public RoleActivityHappy query(int id) {
		return queryForObject("select * from t_role_activity_happy where role_id=?", HANDLER, id);
	}

	/**
	 * 清空累积登录发奖记录表
	 */
	public void clearHappyActivityDatas() {
		execute("truncate table t_role_activity_happy");
	}

	/** 更新已领取列表 */
	public void updateGetList(List<Integer> getList, int roleId) {
		String sql = "update t_role_activity_happy set " + " update_time=now(), " + " get_list=?" + " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(getList), roleId);
	}

	public void updateLoginCnt(int roleId) {
		execute("update t_role_activity_happy set login_cnt=login_cnt+1, update_time=now() where role_id=?", roleId);
	}
}