package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.gift.OnlineGift;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 在线礼包DAO
 * 
 * @author 房曈
 *
 */
public class OnlineGiftDAO extends ConnectionResourceDAO implements QueryDateInterface<OnlineGift> {

	private static ResultSetRowHandler<OnlineGift> HANDLER = new ResultSetRowHandler<OnlineGift>() {
		@Override
		public OnlineGift handleRow(ResultSetRow row) throws Exception {
			OnlineGift og = new OnlineGift();
			og.setId(row.getInt("id"));
			og.setTime(row.getInt("time"));
			og.setArrType(DAOUtil.stringConvIntList(row.getString("arr_type")));
			og.setArrItem(DAOUtil.stringConvIntList(row.getString("arr_item")));
			og.setArrNum(DAOUtil.stringConvIntList(row.getString("arr_num")));
			return og;
		}
	};

	/**
	 * 添加
	 * 
	 * @param OnlineGift
	 */
	public void add(OnlineGift og) {
		String sql = "insert into t_zaixian(" + "time," + "arr_type," + "arr_item," + "arr_num" + ")values(?,?,?,?)";
		execute(sql, og.getTime(), DAOUtil.intListConvString(og.getArrType()),
				DAOUtil.intListConvString(og.getArrItem()), DAOUtil.intListConvString(og.getArrNum()));
	}

	/**
	 * 更新
	 * 
	 * @param OnlineGift
	 */
	public void update(OnlineGift og) {
		String sql = "update t_zaixian set " + " time=?," + " arr_type=?," + " arr_item=?," + " arr_num=? "
				+ " where id=?";
		executeUpdate(sql, og.getTime(), DAOUtil.intListConvString(og.getArrType()),
				DAOUtil.intListConvString(og.getArrItem()), DAOUtil.intListConvString(og.getArrNum()), og.getId());
	}

	/**
	 * 查询
	 */
	public OnlineGift query(int id) {
		return queryForObject("select * from t_zaixian where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<OnlineGift> queryList() {
		return queryForList("select * from t_zaixian", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_zaixian where id=?", HANDLER, id);
	}
}