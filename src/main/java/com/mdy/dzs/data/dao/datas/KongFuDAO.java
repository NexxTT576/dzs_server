package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.gong.KongFu;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 内外功升级经验DAO
 * 
 * @author 房曈
 *
 */
public class KongFuDAO extends ConnectionResourceDAO implements QueryDateInterface<KongFu> {

	private static ResultSetRowHandler<KongFu> HANDLER = new ResultSetRowHandler<KongFu>() {
		@Override
		public KongFu handleRow(ResultSetRow row) throws Exception {
			KongFu kf = new KongFu();
			kf.setId(row.getInt("id"));
			kf.setStrengthenlevel(row.getInt("strengthenlevel"));
			kf.setLeadlevel(row.getInt("leadlevel"));
			kf.setExp(DAOUtil.stringConvIntList(row.getString("exp")));
			kf.setSumexp(DAOUtil.stringConvIntList(row.getString("sumexp")));
			return kf;
		}
	};

	/**
	 * 查询
	 */
	public KongFu query(int id) {
		return queryForObject("select * from t_kongfu where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<KongFu> queryList() {
		return queryForList("select * from t_kongfu", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_kongfu where id=?", HANDLER, id);
	}
}