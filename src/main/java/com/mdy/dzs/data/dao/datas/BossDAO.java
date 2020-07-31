package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.boss.Boss;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * boss战奖励DAO
 * 
 * @author 白雪林
 *
 */
public class BossDAO extends ConnectionResourceDAO implements QueryDateInterface<Boss> {

	private static ResultSetRowHandler<Boss> HANDLER = new ResultSetRowHandler<Boss>() {
		@Override
		public Boss handleRow(ResultSetRow row) throws Exception {
			Boss b = new Boss();
			b.setId(row.getInt("id"));
			b.setMin(row.getInt("min"));
			b.setMax(row.getInt("max"));
			b.setRatio1(row.getInt("ratio1"));
			b.setFix1(row.getInt("fix1"));
			b.setRatio2(row.getInt("ratio2"));
			b.setFix2(row.getInt("fix2"));
			b.setProb(row.getInt("prob"));
			return b;
		}
	};

	/**
	 * 查询
	 */
	public Boss query(int id) {
		return queryForObject("select * from t_boss where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Boss> queryList() {
		return queryForList("select * from t_boss", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_boss where id=?", HANDLER, id);
	}
}