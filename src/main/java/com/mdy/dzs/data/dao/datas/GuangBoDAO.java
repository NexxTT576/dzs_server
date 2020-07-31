package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.broad.GuangBo;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 广播DAO
 * 
 * @author 房曈
 *
 */
public class GuangBoDAO extends ConnectionResourceDAO implements QueryDateInterface<GuangBo> {

	private static ResultSetRowHandler<GuangBo> HANDLER = new ResultSetRowHandler<GuangBo>() {
		@Override
		public GuangBo handleRow(ResultSetRow row) throws Exception {
			GuangBo gb = new GuangBo();
			gb.setId(row.getInt("id"));
			gb.setType(row.getInt("type"));
			gb.setTtype(row.getInt("ttype"));
			gb.setArrTime(row.getString("arr_time"));
			gb.setArrStime(row.getString("arr_stime"));
			gb.setNum(row.getInt("num"));
			gb.setContent(row.getString("Content"));
			gb.setColor(DAOUtil.stringConvIntList(row.getString("color")));
			gb.setArrColor(DAOUtil.stringConvIntList(row.getString("arr_color")));
			return gb;
		}
	};

	/**
	 * 添加
	 * 
	 * @param GuangBo
	 */
	public void add(GuangBo gb) {
		String sql = "insert into t_guangbo(" + "type," + "ttype," + "arr_time," + "arr_stime," + "num," + "Content,"
				+ "color," + "arr_color" + ")values(?,?,?,?,?,?,?,?)";
		execute(sql, gb.getType(), gb.getTtype(), gb.getArrTime(), gb.getArrStime(), gb.getNum(), gb.getContent(),
				DAOUtil.intListConvString(gb.getColor()), DAOUtil.intListConvString(gb.getArrColor()));
	}

	/**
	 * 更新
	 * 
	 * @param GuangBo
	 */
	public void update(GuangBo gb) {
		String sql = "update t_guangbo set " + " type=?," + " ttype=?," + " arr_time=?," + " arr_stime=?," + " num=?,"
				+ " Content=?," + " color=?," + " arr_color=? " + " where id=?";
		executeUpdate(sql, gb.getType(), gb.getTtype(), gb.getArrTime(), gb.getArrStime(), gb.getNum(), gb.getContent(),
				DAOUtil.intListConvString(gb.getColor()), DAOUtil.intListConvString(gb.getArrColor()), gb.getId());
	}

	/**
	 * 查询
	 */
	public GuangBo query(int id) {
		return queryForObject("select * from t_guangbo where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<GuangBo> queryList() {
		return queryForList("select * from t_guangbo", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_guangbo where id=?", HANDLER, id);
	}
}