package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.union.UnionApply;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class UnionApplyDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<UnionApply> HANDLER = new ResultSetRowHandler<UnionApply>() {
		@Override
		public UnionApply handleRow(ResultSetRow row) throws Exception {
			UnionApply ua = new UnionApply();
			ua.setId(row.getInt("id"));
			ua.setUnionId(row.getInt("union_id"));
			ua.setRoleId(row.getInt("role_id"));
			ua.setCreateTime(row.getTimestamp("create_time"));
			ua.setState(row.getInt("state"));
			ua.setRank(row.getInt("rank"));
			ua.setLevel(row.getInt("level"));
			return ua;
		}
	};
	/**
	 * 创建用户申请
	 * @param unionApply
	 */
	public void createUnionApply(UnionApply unionApply) {
		String sql = "insert into t_union_apply " + "(union_id," + "role_id,"
				+ "create_time," + "state," +"level,"+"rank"+")values(?,?,?,?,?,?)";
		execute(sql, unionApply.getUnionId(), unionApply.getRoleId(),
				unionApply.getCreateTime(), unionApply.getState(),unionApply.getLevel(),unionApply.getRank());
	}

	/**
	 * 查询用户申请列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<UnionApply> queryRoleApplyList(int roleId) {

		return queryForList("SELECT * FROM t_union_apply where role_id = ?",
				HANDLER, roleId);
	}

	/**
	 * 删除用户申请
	 * @param roleId
	 * @param unionId
	 */
	public void deleteRoleApply(int roleId,int unionId){
		
		execute("delete  from t_union_apply where role_id=? and union_id =?",roleId,unionId);
	}
	/**
	 * 查询帮派申请列表
	 * @param unionId
	 * @return
	 */
    public List<UnionApply> queryApplyList(int unionId){
    	
    	return queryForList("SELECT * FROM t_union_apply where union_id = ? order by level desc ",
				HANDLER, unionId);
    	
    }	
    /**
     * 查询某一条申请记录
     * @param roleId
     * @param unionId
     * @return
     */
    public UnionApply queryApply(int roleId,int unionId){
    	return queryForObject(
    			"select * from t_union_apply where role_id=? and union_id=?",
    			HANDLER, roleId,unionId);
    }
    	
}
