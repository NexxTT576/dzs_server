package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.giftcenter.RoleGiftStatus;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 礼包状态DAO
 * 
 * @author 房曈
 *
 */
public class RoleGiftStatusDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleGiftStatus> HANDLER = new ResultSetRowHandler<RoleGiftStatus>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleGiftStatus handleRow(ResultSetRow row) throws Exception {
			RoleGiftStatus rgs = new RoleGiftStatus();
			rgs.setRoleId(row.getInt("role_id"));
			rgs.setGiftLvGot(JSONUtil.fromJson(row.getString("gift_lv_got"), List.class));
			rgs.setGiftOnlineGot(row.getInt("gift_online_got"));
			rgs.setGiftOnlineTime(row.getTimestamp("gift_online_time"));
			rgs.setGiftOnlineTimeAcc(row.getInt("gift_online_time_acc"));
			rgs.setGiftSignCnt(row.getInt("gift_sign_cnt"));
			rgs.setGiftSignTime(row.getTimestamp("gift_sign_time"));
			rgs.setGiftLogindayCnt(row.getInt("gift_loginday_cnt"));
			rgs.setGiftLogindayGot(JSONUtil.fromJson(row.getString("gift_loginday_got"), List.class));
			rgs.setGiftDayRefresh(row.getTimestamp("gift_day_refresh"));
			rgs.setCreateTime(row.getTimestamp("create_time"));
			rgs.setUpdateTime(row.getTimestamp("update_time"));
			return rgs;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleGiftStatus
	 */
	public void add(int roleId) {
		String sql = "insert into t_role_gift_status(" + "role_id,gift_day_refresh,create_time,update_time"
				+ ")values(?,now(),now(),now())";
		execute(sql, roleId);
	}

	/**
	 * 更新
	 * 
	 * @param RoleGiftStatus
	 */
	public void updateLevelGift(RoleGiftStatus rgs) {
		String sql = "update t_role_gift_status set " + " update_time=now()," + " gift_lv_got=?" + " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(rgs.getGiftLvGot()), rgs.getRoleId());
	}

	/**
	 * 更新
	 * 
	 * @param RoleGiftStatus
	 */
	public void updateOnlineGift(RoleGiftStatus rgs) {
		String sql = "update t_role_gift_status set " + " update_time=now()," + " gift_online_got=?,"
				+ " gift_online_time=?," + " gift_online_time_acc=?" + " where role_id=?";
		executeUpdate(sql, rgs.getGiftOnlineGot(), rgs.getGiftOnlineTime(), rgs.getGiftOnlineTimeAcc(),
				rgs.getRoleId());
	}

	/**
	 * 更新
	 * 
	 * @param RoleGiftStatus
	 */
	public void updateSignGift(RoleGiftStatus rgs) {
		String sql = "update t_role_gift_status set " + " update_time=now()," + " gift_sign_cnt=?,"
				+ " gift_sign_time=?" + " where role_id=?";
		executeUpdate(sql, rgs.getGiftSignCnt(), rgs.getGiftSignTime(), rgs.getRoleId());
	}

	/**
	 * 更新
	 * 
	 * @param RoleGiftStatus
	 */
	public void updateLoginDayGift(RoleGiftStatus rgs) {
		String sql = "update t_role_gift_status set " + " update_time=now()," + " gift_loginday_got=?"
				+ " where role_id=?";
		executeUpdate(sql, JSONUtil.toJson(rgs.getGiftLogindayGot()), rgs.getRoleId());
	}

	public void updateDayRefresh(int roleId) {
		String sql = "update t_role_gift_status set " + " update_time=now(),"
				+ " gift_loginday_cnt=gift_loginday_cnt+1," + " gift_day_refresh=now()" + " where role_id=?";
		executeUpdate(sql, roleId);
	}

	/**
	 * 查询
	 */
	public RoleGiftStatus query(int roleId) {
		return queryForObject("select * from t_role_gift_status where role_id=?", HANDLER, roleId);
	}

	/**
	 * 查询列表
	 */
	public List<RoleGiftStatus> queryList() {
		return queryForList("select * from t_role_gift_status", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_gift_status where role_id=?");
	}
}