package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.yuan.Collect;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 真气聚集概率DAO
 * 
 * @author 房曈
 *
 */
public class CollectDAO extends ConnectionResourceDAO implements QueryDateInterface<Collect> {

	private static ResultSetRowHandler<Collect> HANDLER = new ResultSetRowHandler<Collect>() {
		@Override
		public Collect handleRow(ResultSetRow row) throws Exception {
			Collect c = new Collect();
			c.setId(row.getInt("id"));
			c.setPosition(row.getInt("position"));
			c.setProb(row.getInt("prob"));
			c.setSoul(row.getInt("soul"));
			c.setPrice(row.getInt("price"));
			return c;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Collect
	 */
	public void add(Collect c) {
		String sql = "insert into t_collect(" + "position," + "prob," + "soul," + "price" + ")values(?,?,?,?)";
		execute(sql, c.getPosition(), c.getProb(), c.getSoul(), c.getPrice());
	}

	/**
	 * 更新
	 * 
	 * @param Collect
	 */
	public void update(Collect c) {
		String sql = "update t_collect set " + " position=?," + " prob=?," + " soul=?," + " price=? " + " where id=?";
		executeUpdate(sql, c.getPosition(), c.getProb(), c.getSoul(), c.getPrice(), c.getId());
	}

	/**
	 * 查询
	 */
	public Collect query(int id) {
		return queryForObject("select * from t_collect where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Collect> queryList() {
		return queryForList("select * from t_collect", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_collect where id=?", HANDLER, id);
	}
}