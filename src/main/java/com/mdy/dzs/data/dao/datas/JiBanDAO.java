package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.role.JiBan;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 羁绊DAO
 * 
 * @author 房曈
 *
 */
public class JiBanDAO extends ConnectionResourceDAO implements QueryDateInterface<JiBan> {

	private static ResultSetRowHandler<JiBan> HANDLER = new ResultSetRowHandler<JiBan>() {
		@Override
		public JiBan handleRow(ResultSetRow row) throws Exception {
			JiBan jb = new JiBan();
			jb.setId(row.getInt("id"));
			jb.setOld(row.getString("old"));
			jb.setName(row.getString("name"));
			jb.setType(row.getInt("type"));
			jb.setDescribe(row.getString("describe"));
			jb.setCond1(row.getInt("cond1"));
			jb.setCond2(row.getInt("cond2"));
			jb.setCond3(row.getInt("cond3"));
			jb.setCond4(row.getInt("cond4"));
			jb.setCond5(row.getInt("cond5"));
			jb.setCond6(row.getInt("cond6"));
			jb.setCond7(row.getInt("cond7"));
			jb.setNature1(row.getInt("nature1"));
			jb.setValue1(row.getInt("value1"));
			jb.setArrAdd1(DAOUtil.stringConvIntList(row.getString("arr_add1")));
			jb.setNature2(row.getInt("nature2"));
			jb.setValue2(row.getInt("value2"));
			jb.setArrAdd2(DAOUtil.stringConvIntList(row.getString("arr_add2")));
			jb.setNature3(row.getInt("nature3"));
			jb.setValue3(row.getInt("value3"));
			jb.setArrAdd3(DAOUtil.stringConvIntList(row.getString("arr_add3")));
			return jb;
		}
	};

	/**
	 * 添加
	 * 
	 * @param JiBan
	 */
	public void add(JiBan jb) {
		String sql = "insert into t_jiban(" + "old," + "name," + "type," + "describe," + "cond1," + "cond2," + "cond3,"
				+ "cond4," + "cond5," + "cond6," + "cond7," + "nature1," + "value1," + "arr_add1," + "nature2,"
				+ "value2," + "arr_add2," + "nature3," + "value3," + "arr_add3"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		execute(sql, jb.getOld(), jb.getName(), jb.getType(), jb.getDescribe(), jb.getCond1(), jb.getCond2(),
				jb.getCond3(), jb.getCond4(), jb.getCond5(), jb.getCond6(), jb.getCond7(), jb.getNature1(),
				jb.getValue1(), DAOUtil.intListConvString(jb.getArrAdd1()), jb.getNature2(), jb.getValue2(),
				DAOUtil.intListConvString(jb.getArrAdd2()), jb.getNature3(), jb.getValue3(),
				DAOUtil.intListConvString(jb.getArrAdd3()));
	}

	/**
	 * 更新
	 * 
	 * @param JiBan
	 */
	public void update(JiBan jb) {
		String sql = "update t_jiban set " + " old=?," + " name=?," + " type=?," + " describe=?," + " cond1=?,"
				+ " cond2=?," + " cond3=?," + " cond4=?," + " cond5=?," + " cond6=?," + " cond7=?," + " nature1=?,"
				+ " value1=?," + " arr_add1=?," + " nature2=?," + " value2=?," + " arr_add2=?," + " nature3=?,"
				+ " value3=?," + " arr_add3=? " + " where id=?";
		executeUpdate(sql, jb.getOld(), jb.getName(), jb.getType(), jb.getDescribe(), jb.getCond1(), jb.getCond2(),
				jb.getCond3(), jb.getCond4(), jb.getCond5(), jb.getCond6(), jb.getCond7(), jb.getNature1(),
				jb.getValue1(), DAOUtil.intListConvString(jb.getArrAdd1()), jb.getNature2(), jb.getValue2(),
				DAOUtil.intListConvString(jb.getArrAdd2()), jb.getNature3(), jb.getValue3(),
				DAOUtil.intListConvString(jb.getArrAdd3()), jb.getId());
	}

	/**
	 * 查询
	 */
	public JiBan query(int id) {
		return queryForObject("select * from t_jiban where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<JiBan> queryList() {
		return queryForList("select * from t_jiban", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_jiban where id=?", HANDLER, id);
	}
}