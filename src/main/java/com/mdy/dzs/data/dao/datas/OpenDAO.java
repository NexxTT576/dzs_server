package com.mdy.dzs.data.dao.datas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 系统开放DAO
 * 
 * @author 房曈
 *
 */
public class OpenDAO extends ConnectionResourceDAO implements QueryDateInterface<Open> {

	private static ResultSetRowHandler<Open> HANDLER = new ResultSetRowHandler<Open>() {
		@Override
		public Open handleRow(ResultSetRow row) throws Exception {
			Open o = new Open();
			o.setId(row.getInt("id"));
			o.setSystem(row.getInt("system"));
			o.setLevel(DAOUtil.stringConvIntList(row.getString("level")));
			o.setPrompt(row.getString("prompt"));
			o.setPrompt1(row.getString("prompt1"));
			return o;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Open
	 */
	public void add(Open o) {
		String sql = "insert into t_open(" + "system," + "level," + "prompt," + "prompt1" + ")values(?,?,?,?)";
		execute(sql, o.getSystem(), DAOUtil.intListConvString(o.getLevel()), o.getPrompt(), o.getPrompt1());
	}

	/**
	 * 更新
	 * 
	 * @param Open
	 */
	public void update(Open o) {
		String sql = "update t_open set " + " system=?," + " level=?," + " prompt=?," + " prompt1=? " + " where id=?";
		executeUpdate(sql, o.getSystem(), DAOUtil.intListConvString(o.getLevel()), o.getPrompt(), o.getPrompt1(),
				o.getId());
	}

	/**
	 * 查询
	 */
	public Open query(int id) {
		return queryForObject("select * from t_open where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Open> queryList() {
		return queryForList("select * from t_open", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_open where id=?", HANDLER, id);
	}

	//////////////////////////////////////////////////////////////////////
	private static ResultSetRowHandler<Open> APP_OPEN_HANDLER = new ResultSetRowHandler<Open>() {
		@Override
		public Open handleRow(ResultSetRow row) throws Exception {
			Open o = new Open();
			o.setId(row.getInt("id"));
			o.setPrompt(row.getString("key"));
			o.setSystem(row.getInt("open"));
			return o;
		}
	};

	public Map<String, Integer> queryAppOpenList() {
		List<Open> tmp = queryForList("select * from t_app_open", APP_OPEN_HANDLER);
		Map<String, Integer> res = new HashMap<String, Integer>();
		for (Open open : tmp) {
			res.put(open.getPrompt(), open.getSystem());
		}
		return res;
	}
}