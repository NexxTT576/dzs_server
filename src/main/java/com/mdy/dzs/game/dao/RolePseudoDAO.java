package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.prob.RolePseudo;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 概率DAO
 * 
 * @author 房曈
 *
 */
public class RolePseudoDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RolePseudo> HANDLER = new ResultSetRowHandler<RolePseudo>() {
		@Override
		public RolePseudo handleRow(ResultSetRow row) throws Exception {
			RolePseudo rp = new RolePseudo();
			rp.setRoleId(row.getInt("role_id"));
			rp.setPseudoType(row.getInt("pseudo_type"));
			rp.setPseudoId(row.getInt("pseudo_id"));
			rp.setCount(row.getInt("count"));
			rp.setAllCount(row.getInt("all_count"));
			rp.setCreateTime(row.getTimestamp("create_time"));
			rp.setUpdateTime(row.getTimestamp("update_time"));
			return rp;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RolePseudo
	 */
	public void add(RolePseudo rp) {
		String sql = "insert into t_role_pseudo(" + "role_id," + "pseudo_type," + "pseudo_id," + "count," + "all_count,"
				+ "create_time," + "update_time" + ")values(?,?,?,?,?,now(),now())";
		execute(sql, rp.getRoleId(), rp.getPseudoType(), rp.getPseudoId(), rp.getCount(), rp.getAllCount());
	}

	/**
	 * 更新
	 * 
	 * @param RolePseudo
	 */
	public void update(RolePseudo rp) {
		String sql = "update t_role_pseudo set " + " update_time=now(), " + " pseudo_id=?," + " count=?,"
				+ " all_count=? " + " where role_id=? and pseudo_type=?";
		executeUpdate(sql, rp.getPseudoId(), rp.getCount(), rp.getAllCount(), rp.getRoleId(), rp.getPseudoType());
	}

	/**
	 * 查询
	 */
	public RolePseudo query(int roleId, int pseudoType) {
		return queryForObject("select * from t_role_pseudo where  role_id=? and pseudo_type=?", HANDLER, roleId,
				pseudoType);
	}

	/**
	 * 查询列表
	 */
	public List<RolePseudo> queryList() {
		return queryForList("select * from t_role_pseudo", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int roleId, int pseudoType) {
		execute("delete  from t_role_pseudo where  role_id=? and pseudo_type=?", roleId, pseudoType);
	}
}