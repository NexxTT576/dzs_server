package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.item.Prob;
import com.mdy.dzs.data.domain.item.ProbVal;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 概率包DAO
 * 
 * @author 房曈
 *
 */
public class ProbDAO extends ConnectionResourceDAO implements QueryDateInterface<Prob> {

	private static ResultSetRowHandler<Prob> HANDLER = new ResultSetRowHandler<Prob>() {
		@Override
		public Prob handleRow(ResultSetRow row) throws Exception {
			Prob p = new Prob();
			p.setId(row.getInt("id"));
			p.setExplain(row.getString("explain"));
			p.setType(row.getInt("type"));
			p.setIsJson(row.getInt("isJson"));
			String itemsStr = row.getString("items");
			if (p.getIsJson() == 1) {
				List<ProbVal> items = JSONUtil.fromJsonList(itemsStr, ProbVal.class);
				p.setItems(items);
			}
			p.setItem1(DAOUtil.stringConvIntList(row.getString("item1")));
			p.setItem2(DAOUtil.stringConvIntList(row.getString("item2")));
			p.setItem3(DAOUtil.stringConvIntList(row.getString("item3")));
			p.setItem4(DAOUtil.stringConvIntList(row.getString("item4")));
			p.setItem5(DAOUtil.stringConvIntList(row.getString("item5")));
			p.setItem6(DAOUtil.stringConvIntList(row.getString("item6")));
			p.setItem7(DAOUtil.stringConvIntList(row.getString("item7")));
			p.setItem8(DAOUtil.stringConvIntList(row.getString("item8")));

			itemsStr = row.getString("itemExtra");
			if (itemsStr == null || itemsStr.length() == 0) {
				p.setItemExtra(null);
			} else {
				List<ProbVal> itemExtra = JSONUtil.fromJsonList(itemsStr, ProbVal.class);
				p.setItemExtra(itemExtra);
			}
			return p;
		}
	};

	/**
	 * 查询
	 */
	public Prob query(int id) {
		return queryForObject("select * from t_prob where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Prob> queryList() {
		return queryForList("select * from t_prob", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_prob where id=?", HANDLER, id);
	}
}