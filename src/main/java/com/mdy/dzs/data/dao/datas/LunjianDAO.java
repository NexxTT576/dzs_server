package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.sword.Lunjian;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 论剑匹配表DAO
 * 
 * @author zhou
 *
 */
public class LunjianDAO extends ConnectionResourceDAO implements QueryDateInterface<Lunjian> {

	private static ResultSetRowHandler<Lunjian> HANDLER = new ResultSetRowHandler<Lunjian>() {
		@Override
		public Lunjian handleRow(ResultSetRow row) throws Exception {
			Lunjian l = new Lunjian();
			l.setId(row.getInt("id"));
			l.setZhanli(DAOUtil.stringConvFloatList(row.getString("zhanli")));
			return l;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Lunjian
	 */
	public void add(Lunjian l) {
		String sql = "insert into t_lunjian(" + "zhanli" + ")values(?)";
		execute(sql, l.getZhanli());
	}

	/**
	 * 更新
	 * 
	 * @param Lunjian
	 */
	public void update(Lunjian l) {
		String sql = "update t_lunjian set " + " zhanli=? " + " where id=?";
		executeUpdate(sql, l.getZhanli(), l.getId());
	}

	/**
	 * 查询
	 */
	public Lunjian query(int id) {
		return queryForObject("select * from t_lunjian where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Lunjian> queryList() {
		return queryForList("select * from t_lunjian", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_lunjian where id=?", HANDLER, id);
	}
}