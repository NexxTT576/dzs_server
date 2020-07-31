package com.mdy.dzs.data.dao.datas;

import com.mdy.dzs.data.domain.touzi.TouZi;
import java.util.List;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 投资计划DAO
 * 
 * @author 白雪林
 *
 */
public class TouZiDAO extends ConnectionResourceDAO implements QueryDateInterface<TouZi> {

	private static ResultSetRowHandler<TouZi> HANDLER = new ResultSetRowHandler<TouZi>() {
		@Override
		public TouZi handleRow(ResultSetRow row) throws Exception {
			TouZi tz = new TouZi();
			tz.setId(row.getInt("id"));
			tz.setLevel(row.getInt("level"));
			tz.setNum(row.getInt("num"));
			tz.setType(row.getInt("type"));
			tz.setItemid(row.getInt("itemid"));
			return tz;
		}
	};

	/**
	 * 查询
	 */
	public TouZi query(int id) {
		return queryForObject("select * from t_touzi where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<TouZi> queryList() {
		return queryForList("select * from t_touzi", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_touzi where id=?", HANDLER, id);
	}
}