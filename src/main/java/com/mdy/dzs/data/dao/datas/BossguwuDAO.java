package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.boss.Bossguwu;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * boss战鼓舞DAO
 * 
 * @author 白雪林
 *
 */
public class BossguwuDAO extends ConnectionResourceDAO implements QueryDateInterface<Bossguwu> {

	private static ResultSetRowHandler<Bossguwu> HANDLER = new ResultSetRowHandler<Bossguwu>() {
		@Override
		public Bossguwu handleRow(ResultSetRow row) throws Exception {
			Bossguwu b = new Bossguwu();
			b.setId(row.getInt("id"));
			b.setSilver(row.getInt("silver"));
			b.setCoin(row.getInt("coin"));
			b.setAdd(row.getInt("add"));
			b.setLimit(row.getInt("limit"));
			b.setYinbi(row.getInt("yinbi"));
			b.setShengwang(row.getInt("shengwang"));
			b.setBanggong(row.getInt("banggong"));
			b.setProb(row.getInt("prob"));
			b.setRatio(row.getInt("ratio"));
			return b;
		}
	};

	/**
	 * 查询
	 */
	public Bossguwu query(int id) {
		return queryForObject("select * from t_bossguwu where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Bossguwu> queryList() {
		return queryForList("select * from t_bossguwu", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_bossguwu where id=?", HANDLER, id);
	}
}