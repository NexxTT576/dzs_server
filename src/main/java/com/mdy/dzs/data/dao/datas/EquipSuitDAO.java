package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.equipment.EquipSuit;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 装备套装DAO
 * 
 * @author 房曈
 *
 */
public class EquipSuitDAO extends ConnectionResourceDAO implements QueryDateInterface<EquipSuit> {

	private static ResultSetRowHandler<EquipSuit> HANDLER = new ResultSetRowHandler<EquipSuit>() {
		@Override
		public EquipSuit handleRow(ResultSetRow row) throws Exception {
			EquipSuit es = new EquipSuit();
			es.setId(row.getInt("id"));
			es.setMember(DAOUtil.stringConvIntList(row.getString("member")));
			es.setNature1(DAOUtil.stringConvIntList(row.getString("nature1")));
			es.setNum1(DAOUtil.stringConvIntList(row.getString("num1")));
			es.setNature2(DAOUtil.stringConvIntList(row.getString("nature2")));
			es.setNum2(DAOUtil.stringConvIntList(row.getString("num2")));
			es.setNature3(DAOUtil.stringConvIntList(row.getString("nature3")));
			es.setNum3(DAOUtil.stringConvIntList(row.getString("num3")));
			return es;
		}
	};

	/**
	 * 查询
	 */
	public EquipSuit query(int id) {
		return queryForObject("select * from t_taozhuang where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<EquipSuit> queryList() {
		return queryForList("select * from t_taozhuang", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_taozhuang where id=?", id);
	}
}