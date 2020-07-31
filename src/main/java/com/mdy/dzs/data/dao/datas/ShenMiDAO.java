package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.shenmi.ShenMi;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 神秘商店随机物品配置表DAO
 * 
 * @author 白雪林
 *
 */
public class ShenMiDAO extends ConnectionResourceDAO implements QueryDateInterface<ShenMi> {

	private static ResultSetRowHandler<ShenMi> HANDLER = new ResultSetRowHandler<ShenMi>() {
		@Override
		public ShenMi handleRow(ResultSetRow row) throws Exception {
			ShenMi sm = new ShenMi();
			sm.setId(row.getInt("id"));
			sm.setType(row.getInt("type"));
			sm.setItem(DAOUtil.stringConvIntList(row.getString("item")));
			sm.setNum(row.getInt("num"));
			sm.setMoney(row.getInt("money"));
			sm.setPrice(row.getInt("price"));
			sm.setTime(row.getInt("time"));
			sm.setLevel(DAOUtil.stringConvIntList(row.getString("level")));
			sm.setProb1(DAOUtil.stringConvIntList(row.getString("prob1")));
			sm.setProb2(DAOUtil.stringConvIntList(row.getString("prob2")));
			sm.setProb3(DAOUtil.stringConvIntList(row.getString("prob3")));
			sm.setProb4(DAOUtil.stringConvIntList(row.getString("prob4")));
			sm.setProb5(DAOUtil.stringConvIntList(row.getString("prob5")));
			return sm;
		}
	};

	/**
	 * 查询
	 */
	public ShenMi query(int id) {
		return queryForObject("select * from t_shenmi where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ShenMi> queryList() {
		return queryForList("select * from t_shenmi", HANDLER);
	}

}