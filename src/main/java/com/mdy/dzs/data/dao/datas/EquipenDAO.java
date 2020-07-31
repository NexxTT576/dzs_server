package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.equipment.Equipen;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 装备DAO
 * 
 * @author 房曈
 *
 */
public class EquipenDAO extends ConnectionResourceDAO implements QueryDateInterface<Equipen> {

	private static ResultSetRowHandler<Equipen> HANDLER = new ResultSetRowHandler<Equipen>() {
		@Override
		public Equipen handleRow(ResultSetRow row) throws Exception {
			Equipen e = new Equipen();
			e.setId(row.getInt("id"));
			e.setLv(row.getInt("lv"));
			e.setMlv(row.getInt("mlv"));
			e.setCoin(DAOUtil.stringConvIntList(row.getString("coin")));
			e.setSum(DAOUtil.stringConvIntList(row.getString("sum")));
			return e;
		}
	};

	/**
	 * 查询
	 */
	public Equipen query(int id) {
		return queryForObject("select * from t_equipen where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Equipen> queryList() {
		return queryForList("select * from t_equipen", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_equipen where id=?", HANDLER, id);
	}
}