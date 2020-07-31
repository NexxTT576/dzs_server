package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.yuan.Soul;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 精元升级DAO
 * 
 * @author 房曈
 *
 */
public class SoulDAO extends ConnectionResourceDAO implements QueryDateInterface<Soul> {

	private static ResultSetRowHandler<Soul> HANDLER = new ResultSetRowHandler<Soul>() {
		@Override
		public Soul handleRow(ResultSetRow row) throws Exception {
			Soul s = new Soul();
			s.setId(row.getInt("id"));
			s.setLevel(row.getInt("level"));
			s.setArrExp(DAOUtil.stringConvIntList(row.getString("arr_exp")));
			s.setArrSumexp(DAOUtil.stringConvIntList(row.getString("arr_sumexp")));
			return s;
		}
	};

	/**
	 * 查询
	 */
	public Soul query(int id) {
		return queryForObject("select * from t_soul where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Soul> queryList() {
		return queryForList("select * from t_soul", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_soul where id=?", HANDLER, id);
	}
}