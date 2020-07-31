package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.RoleLineupAid.RoleLineupAid;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 用户辅助阵型DAO
 * 
 * @author zhou
 *
 */
public class RoleLineupAidDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleLineupAid> HANDLER = new ResultSetRowHandler<RoleLineupAid>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleLineupAid handleRow(ResultSetRow row) throws Exception {
			RoleLineupAid rla = new RoleLineupAid();
			rla.setId(row.getInt("id"));
			rla.setRoleId(row.getInt("roleId"));
			rla.setSysId(row.getInt("sysId"));
			rla.setCurLineup(JSONUtil.fromJson(row.getString("curLineup"), List.class));
			rla.setRefreshTime(row.getTimestamp("refreshTime"));
			rla.setCreateTime(row.getTimestamp("create_time"));
			rla.setUpdateTime(row.getTimestamp("update_time"));
			return rla;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleLineupAid
	 */
	public void add(RoleLineupAid rla) {
		String sql = "insert into t_role_lineup(" + "roleId," + "sysId," + "curLineup," + "refreshTime,"
				+ "create_time," + "update_time" + ")values(?,?,?,?,now(),now())";
		String cur = JSONUtil.toJson(rla.getCurLineup());
		int id = executeWithGenKey(sql, rla.getRoleId(), rla.getSysId(), cur, rla.getRefreshTime());
		rla.setId(id);
	}

	/**
	 * 更新
	 * 
	 * @param RoleLineupAid
	 */
	public void update(RoleLineupAid rla) {
		String sql = "update t_role_lineup set " + " update_time=now(), " + " roleId=?," + " sysId=?," + " curLineup=?,"
				+ " refreshTime=? " + " where id=?";
		executeUpdate(sql, rla.getRoleId(), rla.getSysId(), JSONUtil.toJson(rla.getCurLineup()), rla.getRefreshTime(),
				rla.getId());
	}

	/**
	 * 查询
	 */
	public RoleLineupAid query(int id) {
		return queryForObject("select * from t_role_lineup where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleLineupAid> queryList() {
		return queryForList("select * from t_role_lineup", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_lineup where id=?", HANDLER, id);
	}

	// ==========================================新增===========================================//
	/**
	 * 根据角色id系统id
	 * 
	 * @param roleId
	 * @param sysId
	 * @return
	 */
	public RoleLineupAid queryByRoleIdSysId(int roleId, int sysId) {
		return queryForObject("select * from t_role_lineup where roleId=? and sysId=?", HANDLER, roleId, sysId);
	}
}
