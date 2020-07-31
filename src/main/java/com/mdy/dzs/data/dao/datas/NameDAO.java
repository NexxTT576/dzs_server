package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.robot.Name;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 创建角色名字库DAO
 * 
 * @author 房曈
 *
 */
public class NameDAO extends ConnectionResourceDAO implements QueryDateInterface<Name> {

	private static ResultSetRowHandler<Name> HANDLER = new ResultSetRowHandler<Name>() {
		@Override
		public Name handleRow(ResultSetRow row) throws Exception {
			Name n = new Name();
			n.setId(row.getInt("id"));
			n.setFirst(row.getString("first"));
			n.setMale(row.getString("male"));
			n.setFemale(row.getString("female"));
			return n;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Name
	 */
	public void add(Name n) {
		String sql = "insert into t_name(" + "first," + "male," + "female" + ")values(?,?,?)";
		execute(sql, n.getFirst(), n.getMale(), n.getFemale());
	}

	/**
	 * 更新
	 * 
	 * @param Name
	 */
	public void update(Name n) {
		String sql = "update t_name set " + " first=?," + " male=?," + " female=? " + " where id=?";
		executeUpdate(sql, n.getFirst(), n.getMale(), n.getFemale(), n.getId());
	}

	/**
	 * 查询
	 */
	public Name query(int id) {
		return queryForObject("select * from t_name where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Name> queryList() {
		return queryForList("select * from t_name", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_name where id=?", HANDLER, id);
	}
}