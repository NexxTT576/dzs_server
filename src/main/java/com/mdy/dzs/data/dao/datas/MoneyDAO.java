package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.item.Money;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 货币对应DAO
 * 
 * @author 房曈
 *
 */
public class MoneyDAO extends ConnectionResourceDAO implements QueryDateInterface<Money> {

	private static ResultSetRowHandler<Money> HANDLER = new ResultSetRowHandler<Money>() {
		@Override
		public Money handleRow(ResultSetRow row) throws Exception {
			Money m = new Money();
			m.setId(row.getInt("id"));
			m.setName(row.getString("name"));
			m.setIcon(row.getString("icon"));
			m.setItem(row.getInt("item"));
			return m;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Money
	 */
	public void add(Money m) {
		String sql = "insert into t_money(" + "name," + "icon," + "item" + ")values(?,?,?)";
		execute(sql, m.getName(), m.getIcon(), m.getItem());
	}

	/**
	 * 更新
	 * 
	 * @param Money
	 */
	public void update(Money m) {
		String sql = "update t_money set " + " name=?," + " icon=?," + " item=? " + " where id=?";
		executeUpdate(sql, m.getName(), m.getIcon(), m.getItem(), m.getId());
	}

	/**
	 * 查询
	 */
	public Money query(int id) {
		return queryForObject("select * from t_money where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Money> queryList() {
		return queryForList("select * from t_money", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_money where id=?", HANDLER, id);
	}
}