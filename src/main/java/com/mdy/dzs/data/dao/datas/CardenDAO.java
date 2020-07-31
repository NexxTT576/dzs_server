package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.card.Carden;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 卡牌升级DAO
 * 
 * @author 房曈
 *
 */
public class CardenDAO extends ConnectionResourceDAO implements QueryDateInterface<Carden> {

	private static ResultSetRowHandler<Carden> HANDLER = new ResultSetRowHandler<Carden>() {
		@Override
		public Carden handleRow(ResultSetRow row) throws Exception {
			Carden c = new Carden();
			c.setId(row.getInt("id"));
			c.setStrengthenlevel(row.getInt("strengthenlevel"));
			c.setLeadlevel(row.getInt("leadlevel"));
			c.setExp(DAOUtil.stringConvIntList(row.getString("exp")));
			c.setSumexp(DAOUtil.stringConvIntList(row.getString("sumexp")));
			return c;
		}
	};

	/**
	 * 查询
	 */
	public Carden query(int id) {
		return queryForObject("select * from t_carden where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Carden> queryList() {
		return queryForList("select * from t_carden", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_carden where id=?", HANDLER, id);
	}
}