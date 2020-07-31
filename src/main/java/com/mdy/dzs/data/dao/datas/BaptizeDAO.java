package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.equipment.Baptize;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 装备洗练DAO
 * 
 * @author 房曈
 *
 */
public class BaptizeDAO extends ConnectionResourceDAO implements QueryDateInterface<Baptize> {

	private static ResultSetRowHandler<Baptize> HANDLER = new ResultSetRowHandler<Baptize>() {
		@Override
		public Baptize handleRow(ResultSetRow row) throws Exception {
			Baptize b = new Baptize();
			b.setId(row.getInt("id"));
			b.setType(row.getInt("type"));
			b.setArrSilver(DAOUtil.stringConvIntList(row.getString("arr_silver")));
			b.setPro(DAOUtil.stringConvIntList(row.getString("pro")));
			b.setExtend1(DAOUtil.stringConvIntList(row.getString("extend1")));
			b.setExtend2(DAOUtil.stringConvIntList(row.getString("extend2")));
			b.setExtend3(DAOUtil.stringConvIntList(row.getString("extend3")));
			b.setExtend4(DAOUtil.stringConvIntList(row.getString("extend4")));
			b.setExtend5(DAOUtil.stringConvIntList(row.getString("extend5")));
			b.setFactro(DAOUtil.stringConvIntList(row.getString("factro")));
			return b;
		}
	};

	/**
	 * 查询
	 */
	public Baptize query(int id) {
		return queryForObject("select * from t_baptize where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Baptize> queryList() {
		return queryForList("select * from t_baptize", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_baptize where id=?", HANDLER, id);
	}
}