package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.role.JingMai;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 经脉DAO
 * 
 * @author 房曈
 *
 */
public class JingMaiDAO extends ConnectionResourceDAO implements QueryDateInterface<JingMai> {

	private static ResultSetRowHandler<JingMai> HANDLER = new ResultSetRowHandler<JingMai>() {
		@Override
		public JingMai handleRow(ResultSetRow row) throws Exception {
			JingMai jm = new JingMai();
			jm.setId(row.getInt("id"));
			jm.setType(row.getInt("type"));
			jm.setName(row.getString("name"));
			jm.setDescribe(row.getString("describe"));
			jm.setOrder(row.getInt("order"));
			jm.setNature(row.getInt("nature"));
			jm.setArrValue(DAOUtil.stringConvIntList(row.getString("arr_value")));
			jm.setArrCoin(DAOUtil.stringConvIntList(row.getString("arr_coin")));
			jm.setArrStar(DAOUtil.stringConvIntList(row.getString("arr_star")));
			return jm;
		}
	};

	/**
	 * 添加
	 * 
	 * @param JingMai
	 */
	public void add(JingMai jm) {
		String sql = "insert into t_jingmai(" + "type," + "name," + "describe," + "order," + "nature," + "arr_value,"
				+ "arr_coin," + "arr_star" + ")values(?,?,?,?,?,?,?,?)";
		execute(sql, jm.getType(), jm.getName(), jm.getDescribe(), jm.getOrder(), jm.getNature(),
				DAOUtil.intListConvString(jm.getArrValue()), DAOUtil.intListConvString(jm.getArrCoin()),
				DAOUtil.intListConvString(jm.getArrStar()));
	}

	/**
	 * 更新
	 * 
	 * @param JingMai
	 */
	public void update(JingMai jm) {
		String sql = "update t_jingmai set " + " type=?," + " name=?," + " describe=?," + " order=?," + " nature=?,"
				+ " arr_value=?," + " arr_coin=?," + " arr_star=? " + " where id=?";
		executeUpdate(sql, jm.getType(), jm.getName(), jm.getDescribe(), jm.getOrder(), jm.getNature(),
				DAOUtil.intListConvString(jm.getArrValue()), DAOUtil.intListConvString(jm.getArrCoin()),
				DAOUtil.intListConvString(jm.getArrStar()), jm.getId());
	}

	/**
	 * 查询
	 */
	public JingMai query(int id) {
		return queryForObject("select * from t_jingmai where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<JingMai> queryList() {
		return queryForList("select * from t_jingmai", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_jingmai where id=?", HANDLER, id);
	}
}