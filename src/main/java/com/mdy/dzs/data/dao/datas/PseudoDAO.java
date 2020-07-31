package com.mdy.dzs.data.dao.datas;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.shop.ProbMutex;
import com.mdy.dzs.data.domain.shop.Pseudo;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 抽卡新概率DAO
 * 
 * @author zhou
 *
 */
public class PseudoDAO extends ConnectionResourceDAO implements QueryDateInterface<Pseudo> {

	private static ResultSetRowHandler<Pseudo> HANDLER = new ResultSetRowHandler<Pseudo>() {
		@Override
		public Pseudo handleRow(ResultSetRow row) throws Exception {
			Pseudo p = new Pseudo();
			p.setId(row.getInt("id"));
			p.setType(row.getInt("type"));
			p.setProb(row.getInt("prob"));
			p.setOutput(DAOUtil.stringConvIntList(row.getString("output")));
			p.setNumber(row.getInt("number"));
			p.setTenProb(row.getInt("ten_prob"));
			p.setResetID(row.getInt("resetID"));
			p.setJumpID(row.getInt("jumpID"));
			p.setIsStart(row.getInt("isStart"));

			String str = row.getString("mutex");
			List<ProbMutex> mutexs = new ArrayList<ProbMutex>();
			if (str != null) {
				String mutex[] = str.split(",");
				for (int i = 0; i < mutex.length; i++) {
					mutexs.add(new ProbMutex(mutex[i]));
				}
			}
			p.setMutex(mutexs);
			return p;
		}
	};

	/**
	 * 查询列表
	 */
	public List<Pseudo> queryList() {
		return queryForList("select * from t_npseudo", HANDLER);
	}
}