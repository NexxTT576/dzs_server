package com.mdy.dzs.data.dao.datas;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.gong.Refine;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 内外功DAO
 * 
 * @author 房曈
 *
 */
public class RefineDAO extends ConnectionResourceDAO implements QueryDateInterface<Refine> {

	private static ResultSetRowHandler<Refine> HANDLER = new ResultSetRowHandler<Refine>() {
		@Override
		public Refine handleRow(ResultSetRow row) throws Exception {
			Refine r = new Refine();
			r.setId(row.getInt("id"));
			r.setKungfu(row.getInt("Kungfu"));
			r.setArrLevel(DAOUtil.stringConvIntList(row.getString("arr_level")));
			r.setArrNature1(DAOUtil.stringConvIntList(row.getString("arr_nature1")));
			r.setArrValue1(DAOUtil.stringConvIntList(row.getString("arr_value1")));
			r.setRefine(row.getInt("Refine"));
			r.setArrSilver(DAOUtil.stringConvIntList(row.getString("arr_silver")));
			r.setArrNature2(DAOUtil.stringConvIntList(row.getString("arr_nature2")));
			r.setArrValue2(DAOUtil.stringConvIntList(row.getString("arr_value2")));
			r.setArrCard(DAOUtil.stringConvIntList(row.getString("arr_card")));
			r.setArrJiban(DAOUtil.stringConvIntList(row.getString("arr_jiban")));

			initConsumeItems(r, row);
			return r;
		}
	};

	private static void initConsumeItems(Refine r, ResultSetRow row) throws Exception {
		if (r.getRefine() == 1) {
			List<List<Integer>> types = new ArrayList<List<Integer>>();
			List<List<Integer>> itemIds = new ArrayList<List<Integer>>();
			List<List<Integer>> itemNums = new ArrayList<List<Integer>>();

			for (int i = 1; i <= 4; i++) {
				types.add(DAOUtil.stringConvIntList(row.getString("arr_type" + i)));
				itemIds.add(DAOUtil.stringConvIntList(row.getString("arr_item" + i)));
				itemNums.add(DAOUtil.stringConvIntList(row.getString("arr_num" + i)));
			}
			List<List<ProbItem>> citems = new ArrayList<List<ProbItem>>();
			int size = types.get(0).size();
			for (int i = 0; i < size; i++) {
				List<ProbItem> items = new ArrayList<ProbItem>();
				for (int j = 0; j < 4; j++) {
					if (i >= types.get(j).size())
						break;
					int type = types.get(j).get(i);
					if (type == 0)
						continue;
					items.add(new ProbItem(types.get(j).get(i), itemIds.get(j).get(i), itemNums.get(j).get(i)));
				}
				citems.add(items);
			}
			r.setArrItem(citems);
		}
	}

	/**
	 * 查询
	 */
	public Refine query(int id) {
		return queryForObject("select * from t_refine where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Refine> queryList() {
		return queryForList("select * from t_refine", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_refine where id=?", HANDLER, id);
	}
}