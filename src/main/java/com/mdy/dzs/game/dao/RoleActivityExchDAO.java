package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.activity.exchange.RoleActivityExch;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 玩家限时兑换DAO
 * 
 * @author zhou
 *
 */
public class RoleActivityExchDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleActivityExch> HANDLER = new ResultSetRowHandler<RoleActivityExch>() {
		@Override
		public RoleActivityExch handleRow(ResultSetRow row) throws Exception {
			RoleActivityExch rae = new RoleActivityExch();
			rae.setId(row.getInt("id"));
			rae.setRoleId(row.getInt("roleId"));
			rae.setExpId(row.getInt("expId"));
			rae.setExchId(row.getInt("exchId"));
			rae.setExchNum(row.getInt("exchNum"));
			rae.setExchType(row.getInt("exchType"));
			rae.setRefreshNum(row.getInt("refreshNum"));
			rae.setRefFreeNum(row.getInt("refFreeNum"));
			rae.setRefreshTime(row.getTimestamp("refresh_time"));
			rae.setCreateTime(row.getTimestamp("create_time"));
			rae.setUpdateTime(row.getTimestamp("update_time"));
			return rae;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleActivityExch
	 */
	public void add(RoleActivityExch rae) {
		String sql = "insert into t_role_activity_exch(" + "roleId," + "expId," + "exchId," + "exchNum," + "exchType,"
				+ "refreshNum," + "refFreeNum," + "refresh_time," + "create_time," + "update_time"
				+ ")values(?,?,?,?,?,?,?,?,now(),now())";
		execute(sql, rae.getRoleId(), rae.getExpId(), rae.getExchId(), rae.getExchNum(), rae.getExchType(),
				rae.getRefreshNum(), rae.getRefFreeNum(), rae.getRefreshTime());
	}

	/**
	 * 更新
	 * 
	 * @param RoleActivityExch
	 */
	public void update(RoleActivityExch rae) {
		String sql = "update t_role_activity_exch set " + " update_time=now(), " + " roleId=?," + " expId=?,"
				+ " exchId=?," + " exchNum=?," + " exchType=?," + " refreshNum=?, " + " refFreeNum=?, "
				+ " refresh_time=? " + " where id=?";
		executeUpdate(sql, rae.getRoleId(), rae.getExpId(), rae.getExchId(), rae.getExchNum(), rae.getExchType(),
				rae.getRefreshNum(), rae.getRefFreeNum(), rae.getRefreshTime(), rae.getId());
	}

	/**
	 * 查询
	 */
	public RoleActivityExch query(int id) {
		return queryForObject("select * from t_role_activity_exch where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleActivityExch> queryList() {
		return queryForList("select * from t_role_activity_exch", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_activity_exch where id=?", HANDLER, id);
	}

	// ============================================================================================================//
	/**
	 * @param roleId
	 * @param exchId
	 * @return
	 */
	public RoleActivityExch queryByExchId(int roleId, int exchId) {
		return queryForObject("select * from t_role_activity_exch where roleId=? and exchId=?", HANDLER, roleId,
				exchId);
	}

	/**
	 * 更新公式
	 * 
	 * @param roleId
	 * @param exchId
	 * @param exp
	 */
	public void updateExpId(int id, int refreshNum, int exp, int refFreeNum) {
		String sql = "update t_role_activity_exch set " + " update_time=now(), " + " expId=?," + " refreshNum=?,"
				+ " refFreeNum=?" + " where id=?";
		executeUpdate(sql, exp, refreshNum, refFreeNum, id);
	}

	/**
	 * 更新兑换数量
	 * 
	 * @param id
	 * @param exchNum
	 */
	public void updateExchNum(int id, int exchNum) {
		String sql = "update t_role_activity_exch set " + " update_time=now(), " + " exchNum=?" + " where id=?";
		executeUpdate(sql, exchNum, id);
	}

	/**
	 * 根据type更新数据
	 * 
	 * @param delType
	 */
	public void deleteAll() {
		execute("delete from t_role_activity_exch");
	}

	/**
	 * 更新刷新次数
	 */
	public void updateRefresh(int type, Date date, int roleId) {
		// 刷新兑换类型是每天的
		String sql = "update t_role_activity_exch set " + " update_time=now(), " + " exchNum=0," + " refresh_time=?"
				+ " where exchType=? and roleId=?";
		executeUpdate(sql, date, type, roleId);
		// 刷新所有的刷新次数为0
		String sql1 = "update t_role_activity_exch set " + " update_time=now(), " + " refresh_time=?,"
				+ " refFreeNum=0," + " refreshNum=0 where roleId=?";
		executeUpdate(sql1, date, roleId);
	}
}
