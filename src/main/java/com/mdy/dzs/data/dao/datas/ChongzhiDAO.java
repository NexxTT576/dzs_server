package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.chongzhi.Chongzhi;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class ChongzhiDAO extends ConnectionResourceDAO implements QueryDateInterface<Chongzhi> {

	private static ResultSetRowHandler<Chongzhi> HANDLER = new ResultSetRowHandler<Chongzhi>() {
		@Override
		public Chongzhi handleRow(ResultSetRow row) throws Exception {
			Chongzhi b = new Chongzhi();
			b.setId(row.getInt("id"));
			b.setIndex(row.getInt("index"));
			b.setOpen(row.getInt("open"));
			b.setCoinnum(row.getInt("coinnum"));
			b.setBasegold(row.getInt("basegold"));
			b.setFirstgold(row.getInt("firstgold"));
			b.setChixugold(row.getInt("chixugold"));
			b.setMark1(row.getInt("mark1"));
			b.setMark2(row.getInt("mark2"));
			b.setPreIndex(row.getInt("pre_index"));
			return b;
		}
	};

	/**
	 * 查询列表
	 */
	public List<Chongzhi> queryList() {
		return queryForList("select * from t_chongzhi", HANDLER);
	}

}
