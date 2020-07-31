package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.packet.Bag;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 背包扩展费用DAO
 * 
 * @author 房曈
 *
 */
public class BagDAO extends ConnectionResourceDAO implements QueryDateInterface<Bag> {

	private static ResultSetRowHandler<Bag> HANDLER = new ResultSetRowHandler<Bag>() {
		@Override
		public Bag handleRow(ResultSetRow row) throws Exception {
			Bag b = new Bag();
			b.setId(row.getInt("id"));
			b.setNum(row.getInt("num"));
			b.setCost(row.getInt("cost"));
			b.setAdd(row.getInt("add"));
			b.setMax(row.getInt("max"));
			return b;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Bag
	 */
	public void add(Bag b) {
		String sql = "insert into t_bag(" + "num," + "cost," + "add," + "max" + ")values(?,?,?,?)";
		execute(sql, b.getNum(), b.getCost(), b.getAdd(), b.getMax());
	}

	/**
	 * 更新
	 * 
	 * @param Bag
	 */
	public void update(Bag b) {
		String sql = "update t_bag set " + " num=?," + " cost=?," + " add=?," + " max=? " + " where id=?";
		executeUpdate(sql, b.getNum(), b.getCost(), b.getAdd(), b.getMax(), b.getId());
	}

	/**
	 * 查询
	 */
	public Bag query(int id) {
		return queryForObject("select * from t_bag where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Bag> queryList() {
		return queryForList("select * from t_bag", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_bag where id=?", HANDLER, id);
	}
}