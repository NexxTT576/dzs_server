package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.activity.roulette.RouletteProb;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 皇宫探宝随机表DAO
 * 
 * @author 白雪林
 *
 */
public class RouletteProbDAO extends ConnectionResourceDAO implements QueryDateInterface<RouletteProb> {

	private static ResultSetRowHandler<RouletteProb> HANDLER = new ResultSetRowHandler<RouletteProb>() {
		@Override
		public RouletteProb handleRow(ResultSetRow row) throws Exception {
			RouletteProb rp = new RouletteProb();
			rp.setId(row.getInt("id"));
			rp.setType(row.getInt("type"));
			rp.setMark(row.getString("mark"));
			rp.setIsStart(row.getInt("isStart"));
			rp.setPosition(DAOUtil.stringConvIntList(row.getString("position")));
			rp.setProb(DAOUtil.stringConvIntList(row.getString("prob")));
			rp.setOutput(DAOUtil.stringConvIntList(row.getString("output")));
			rp.setResetID(row.getInt("resetID"));
			rp.setNumber(row.getInt("number"));
			rp.setJumpID(row.getInt("jumpID"));
			return rp;
		}
	};

	/**
	 * 查询
	 */
	public RouletteProb query(int id) {
		return queryForObject("select * from t_rouletteprob where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RouletteProb> queryList() {
		return queryForList("select * from t_rouletteprob", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_rouletteprob where id=?", HANDLER, id);
	}
}