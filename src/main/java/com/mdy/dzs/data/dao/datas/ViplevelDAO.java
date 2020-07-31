package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.viplevel.Viplevel;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * vip等级限制，等级礼包DAO
 * 
 * @author 白雪林
 *
 */
public class ViplevelDAO extends ConnectionResourceDAO implements QueryDateInterface<Viplevel> {

	private static ResultSetRowHandler<Viplevel> HANDLER = new ResultSetRowHandler<Viplevel>() {
		@Override
		public Viplevel handleRow(ResultSetRow row) throws Exception {
			Viplevel v = new Viplevel();
			v.setId(row.getInt("id"));
			v.setVip(row.getInt("vip"));
			v.setExp(row.getInt("exp"));
			v.setOpen(row.getInt("open"));
			v.setArrType1(DAOUtil.stringConvIntList(row.getString("arr_type1")));
			v.setArrItem1(DAOUtil.stringConvIntList(row.getString("arr_item1")));
			v.setArrNum1(DAOUtil.stringConvIntList(row.getString("arr_num1")));
			v.setArrType2(DAOUtil.stringConvIntList(row.getString("arr_type2")));
			v.setArrItem2(DAOUtil.stringConvIntList(row.getString("arr_item2")));
			v.setArrNum2(DAOUtil.stringConvIntList(row.getString("arr_num2")));
			v.setOldPrice(row.getInt("old_price"));
			v.setNewPrice(row.getInt("new_price"));
			return v;
		}
	};

	/**
	 * 添加
	 * 
	 * @param viplevel
	 */
	public void add(Viplevel v) {
		String sql = "insert into t_viplevel(" + "vip," + "exp," + "open," + "arr_type1," + "arr_item1," + "arr_num1,"
				+ "arr_type2," + "arr_item2," + "arr_num2" + ")values(?,?,?,?,?,?,?,?,?)";
		execute(sql, v.getVip(), v.getExp(), v.getOpen(), v.getArrType1(), v.getArrItem1(), v.getArrNum1(),
				v.getArrType2(), v.getArrItem2(), v.getArrNum2());
	}

	/**
	 * 更新
	 * 
	 * @param viplevel
	 */
	public void update(Viplevel v) {
		String sql = "update t_viplevel set " + " vip=?," + " exp=?," + " open=?," + " arr_type1=?," + " arr_item1=?,"
				+ " arr_num1=?," + " arr_type2=?," + " arr_item2=?," + " arr_num2=? " + " where id=?";
		executeUpdate(sql, v.getVip(), v.getExp(), v.getOpen(), v.getArrType1(), v.getArrItem1(), v.getArrNum1(),
				v.getArrType2(), v.getArrItem2(), v.getArrNum2(), v.getId());
	}

	/**
	 * 查询
	 */
	public Viplevel query(int id) {
		return queryForObject("select * from t_viplevel where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Viplevel> queryList() {
		return queryForList("select * from t_viplevel", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_viplevel where id=?", HANDLER, id);
	}
}