package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.world.Field;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 副本DAO
 * 
 * @author 房曈
 *
 */
public class FieldDAO extends ConnectionResourceDAO implements QueryDateInterface<Field> {

	private static ResultSetRowHandler<Field> HANDLER = new ResultSetRowHandler<Field>() {
		@Override
		public Field handleRow(ResultSetRow row) throws Exception {
			Field f = new Field();
			f.setId(row.getInt("id"));
			f.setName(row.getString("name"));
			f.setIcon(row.getString("icon"));
			f.setWorld(row.getInt("world"));
			f.setXAxis(row.getInt("x_axis"));
			f.setYAxis(row.getInt("y_axis"));
			f.setType(row.getInt("type"));
			f.setPrefield(row.getInt("prefield"));
			f.setStar(row.getInt("star"));
			f.setStar1(row.getInt("star1"));
			f.setArrReward1(DAOUtil.stringConvIntList(row.getString("arr_reward1")));
			f.setArrNum1(DAOUtil.stringConvIntList(row.getString("arr_num1")));
			f.setStar2(row.getInt("star2"));
			f.setArrReward2(DAOUtil.stringConvIntList(row.getString("arr_reward2")));
			f.setArrNum2(DAOUtil.stringConvIntList(row.getString("arr_num2")));
			f.setStar3(row.getInt("star3"));
			f.setArrReward3(DAOUtil.stringConvIntList(row.getString("arr_reward3")));
			f.setArrNum3(DAOUtil.stringConvIntList(row.getString("arr_num3")));
			f.setArrBattle(DAOUtil.stringConvIntList(row.getString("arr_battle")));
			return f;
		}
	};

	/**
	 * 查询
	 */
	public Field query(int id) {
		return queryForObject("select * from t_field where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Field> queryList() {
		return queryForList("select * from t_field", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_field where id=?", HANDLER, id);
	}
}