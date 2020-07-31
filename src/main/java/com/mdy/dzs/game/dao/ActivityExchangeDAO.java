package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.dzs.game.domain.activity.exchange.ActivityExchange;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 限时兑换DAO
 * 
 * @author zhou
 *
 */
public class ActivityExchangeDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<ActivityExchange> HANDLER = new ResultSetRowHandler<ActivityExchange>() {
		@Override
		public ActivityExchange handleRow(ResultSetRow row) throws Exception {
			ActivityExchange ae = new ActivityExchange();
			ae.setId(row.getInt("id"));
			ae.setType(row.getInt("type"));
			ae.setIsOpen(row.getInt("isOpen"));
			ae.setTagName(row.getString("tagName"));
			ae.setExchType(row.getInt("exchType"));
			ae.setExchNum(row.getInt("exchNum"));
			ae.setExchExpId(DAOUtil.stringConvIntList(row.getString("exchExpId")));
			ae.setExchExpProb(DAOUtil.stringConvIntList(row.getString("exchExpProb")));
			ae.setIsRefresh(row.getInt("isRefresh"));
			ae.setRefGold(row.getInt("refGold"));
			ae.setRefType(row.getInt("refType"));
			ae.setAccumNum(row.getInt("accumNum"));
			ae.setAccumLimit(row.getInt("accumLimit"));
			ae.setView(row.getString("view"));
			ae.setRefFree(row.getInt("refFree"));
			return ae;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ActivityExchange
	 */
	public void add(ActivityExchange ae) {
		String sql = "insert into t_exchange(" + "type," + "isOpen," + "tagName," + "exchType," + "exchNum,"
				+ "exchExpId," + "exchExpProb," + "isRefresh," + "refGold," + "refType," + "accumNum," + "accumLimit,"
				+ "view" + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		execute(sql, ae.getType(), ae.getIsOpen(), ae.getTagName(), ae.getExchType(), ae.getExchNum(),
				ae.getExchExpId(), ae.getExchExpProb(), ae.getIsRefresh(), ae.getRefGold(), ae.getRefType(),
				ae.getAccumNum(), ae.getAccumLimit(), ae.getView());
	}

	/**
	 * 更新
	 * 
	 * @param ActivityExchange
	 */
	public void update(ActivityExchange ae) {
		String sql = "update t_exchange set " + " type=?," + " isOpen=?," + " tagName=?," + " exchType=?,"
				+ " exchNum=?," + " exchExpId=?," + " exchExpProb=?," + " isRefresh=?," + " refGold=?," + " refType=?,"
				+ " accumNum=?," + " accumLimit=?," + " view=? " + " where id=?";
		executeUpdate(sql, ae.getType(), ae.getIsOpen(), ae.getTagName(), ae.getExchType(), ae.getExchNum(),
				ae.getExchExpId(), ae.getExchExpProb(), ae.getIsRefresh(), ae.getRefGold(), ae.getRefType(),
				ae.getAccumNum(), ae.getAccumLimit(), ae.getView(), ae.getId());
	}

	/**
	 * 查询
	 */
	public ActivityExchange query(int id) {
		return queryForObject("select * from t_exchange where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ActivityExchange> queryList() {
		return queryForList("select * from t_exchange where isOpen=1", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_exchange where id=?", HANDLER, id);
	}
}