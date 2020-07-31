package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.activity.roulette.RouletteItem;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 皇宫探宝圆盘物品DAO
 * 
 * @author 白雪林
 *
 */
public class RouletteItemDAO extends ConnectionResourceDAO implements QueryDateInterface<RouletteItem> {

	private static ResultSetRowHandler<RouletteItem> HANDLER = new ResultSetRowHandler<RouletteItem>() {
		@Override
		public RouletteItem handleRow(ResultSetRow row) throws Exception {
			RouletteItem ri = new RouletteItem();
			ri.setId(row.getInt("id"));
			ri.setItemDisplay(row.getInt("itemDisplay"));
			ri.setItemType(DAOUtil.stringConvIntList(row.getString("itemType")));
			ri.setItemId(DAOUtil.stringConvIntList(row.getString("itemId")));
			ri.setItemCnt(DAOUtil.stringConvIntList(row.getString("itemCnt")));
			ri.setItemProb(DAOUtil.stringConvIntList(row.getString("itemProb")));
			return ri;
		}
	};

	/**
	 * 查询
	 */
	public RouletteItem query(int id) {
		return queryForObject("select * from t_rouletteitem where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RouletteItem> queryList() {
		return queryForList("select * from t_rouletteitem", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_rouletteitem where id=?", HANDLER, id);
	}
}