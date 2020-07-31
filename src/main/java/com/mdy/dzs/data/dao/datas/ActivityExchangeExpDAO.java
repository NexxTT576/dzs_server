package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.activity.exchange.ActivityExchangeExp;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 限时兑换公式DAO
 * 
 * @author zhou
 *
 */
public class ActivityExchangeExpDAO extends ConnectionResourceDAO implements QueryDateInterface<ActivityExchangeExp> {

	private static ResultSetRowHandler<ActivityExchangeExp> HANDLER = new ResultSetRowHandler<ActivityExchangeExp>() {
		@Override
		public ActivityExchangeExp handleRow(ResultSetRow row) throws Exception {
			ActivityExchangeExp aee = new ActivityExchangeExp();
			aee.setId(row.getInt("id"));
			aee.setMark(row.getString("mark"));
			aee.setExchItemType(DAOUtil.stringConvIntList(row.getString("exchItemType")));
			aee.setExchItemId(DAOUtil.stringConvIntList(row.getString("exchItemId")));
			aee.setExchItemCnt(DAOUtil.stringConvIntList(row.getString("exchItemCnt")));
			aee.setExchRstType(DAOUtil.stringConvIntList(row.getString("exchRstType")));
			aee.setExchRstId(DAOUtil.stringConvIntList(row.getString("exchRstId")));
			aee.setExchRstCnt(DAOUtil.stringConvIntList(row.getString("exchRstCnt")));
			return aee;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ActivityExchangeExp
	 */
	public void add(ActivityExchangeExp aee) {
		String sql = "insert into t_exchangeexp(" + "mark," + "exchItemType," + "exchItemId," + "exchItemCnt,"
				+ "exchRstType," + "exchRstId," + "exchRstCnt" + ")values(?,?,?,?,?,?,?)";
		execute(sql, aee.getMark(), aee.getExchItemType(), aee.getExchItemId(), aee.getExchItemCnt(),
				aee.getExchRstType(), aee.getExchRstId(), aee.getExchRstCnt());
	}

	/**
	 * 更新
	 * 
	 * @param ActivityExchangeExp
	 */
	public void update(ActivityExchangeExp aee) {
		String sql = "update t_exchangeexp set " + " mark=?," + " exchItemType=?," + " exchItemId=?,"
				+ " exchItemCnt=?," + " exchRstType=?," + " exchRstId=?," + " exchRstCnt=? " + " where id=?";
		executeUpdate(sql, aee.getMark(), aee.getExchItemType(), aee.getExchItemId(), aee.getExchItemCnt(),
				aee.getExchRstType(), aee.getExchRstId(), aee.getExchRstCnt(), aee.getId());
	}

	/**
	 * 查询
	 */
	public ActivityExchangeExp query(int id) {
		return queryForObject("select * from t_exchangeexp where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ActivityExchangeExp> queryList() {
		return queryForList("select * from t_exchangeexp", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_exchangeexp where id=?", HANDLER, id);
	}
}