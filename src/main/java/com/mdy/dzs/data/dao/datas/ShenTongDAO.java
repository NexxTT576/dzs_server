package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.shentong.ShenTong;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 神通升级DAO
 * 
 * @author 房曈
 *
 */
public class ShenTongDAO extends ConnectionResourceDAO implements QueryDateInterface<ShenTong> {

	private static ResultSetRowHandler<ShenTong> HANDLER = new ResultSetRowHandler<ShenTong>() {
		@Override
		public ShenTong handleRow(ResultSetRow row) throws Exception {
			ShenTong st = new ShenTong();
			st.setId(row.getInt("id"));
			st.setArrTalent(DAOUtil.stringConvIntList(row.getString("arr_talent")));
			st.setArrPoint(DAOUtil.stringConvIntList(row.getString("arr_point")));
			st.setArrCond(DAOUtil.stringConvIntList(row.getString("arr_cond")));
			return st;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ShenTong
	 */
	public void add(ShenTong st) {
		String sql = "insert into t_shentong(" + "arr_talent," + "arr_point," + "arr_cond" + ")values(?,?,?)";
		execute(sql, DAOUtil.intListConvString(st.getArrTalent()), DAOUtil.intListConvString(st.getArrPoint()),
				DAOUtil.intListConvString(st.getArrCond()));
	}

	/**
	 * 更新
	 * 
	 * @param ShenTong
	 */
	public void update(ShenTong st) {
		String sql = "update t_shentong set " + " arr_talent=?," + " arr_point=?," + " arr_cond=? " + " where id=?";
		executeUpdate(sql, DAOUtil.intListConvString(st.getArrTalent()), DAOUtil.intListConvString(st.getArrPoint()),
				DAOUtil.intListConvString(st.getArrCond()), st.getId());
	}

	/**
	 * 查询
	 */
	public ShenTong query(int id) {
		return queryForObject("select * from t_shentong where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ShenTong> queryList() {
		return queryForList("select * from t_shentong", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_shentong where id=?", HANDLER, id);
	}
}