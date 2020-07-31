package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.role.RoleChannel;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 人物经脉DAO
 * 
 * @author 房曈
 *
 */
public class RoleChannelDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleChannel> HANDLER = new ResultSetRowHandler<RoleChannel>() {
		@Override
		public RoleChannel handleRow(ResultSetRow row) throws Exception {
			RoleChannel rc = new RoleChannel();
			rc.setId(row.getInt("id"));
			rc.setRoleId(row.getInt("role_id"));
			rc.setChanType(row.getInt("chanType"));
			rc.setChanLv(row.getInt("chanLv"));
			rc.setChanPt(row.getInt("chanPt"));
			List<Prop> arr = JSONUtil.fromJsonList(row.getString("chanAttrAry"), Prop.class);
			rc.setChanAttrAry(arr);
			rc.setChanStarCnt(row.getInt("chanStarCnt"));
			rc.setChanReSetCnt(row.getInt("chanReSetCnt"));
			return rc;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleChannel
	 */
	public void add(RoleChannel rc) {
		String sql = "insert into t_role_channel(" + "role_id," + "chanType," + "chanLv," + "chanPt," + "chanAttrAry,"
				+ "chanStarCnt," + "chanReSetCnt" + ")values(?,?,?,?,?,?,?)";
		execute(sql, rc.getRoleId(), rc.getChanType(), rc.getChanLv(), rc.getChanPt(),
				JSONUtil.toJson(rc.getChanAttrAry()), rc.getChanStarCnt(), rc.getChanReSetCnt());
	}

	/**
	 * 更新
	 * 
	 * @param RoleChannel
	 */
	public void update(RoleChannel rc) {
		String sql = "update t_role_channel set " + " role_id=?," + " chanType=?," + " chanLv=?," + " chanPt=?,"
				+ " chanAttrAry=?," + " chanStarCnt=?," + " chanReSetCnt=? " + " where id=?";
		executeUpdate(sql, rc.getRoleId(), rc.getChanType(), rc.getChanLv(), rc.getChanPt(),
				JSONUtil.toJson(rc.getChanAttrAry()), rc.getChanStarCnt(), rc.getChanReSetCnt(), rc.getId());
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_channel where id=?", HANDLER, id);
	}

	public RoleChannel queryByAccount(int roleId) {
		return queryForObject("select * from t_role_channel where role_id=?", HANDLER, roleId);
	}
}