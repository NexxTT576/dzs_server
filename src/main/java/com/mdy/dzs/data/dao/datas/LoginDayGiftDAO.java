package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.gift.LoginDayGift;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 开服礼包DAO
 * 
 * @author 房曈
 *
 */
public class LoginDayGiftDAO extends ConnectionResourceDAO implements QueryDateInterface<LoginDayGift> {

	private static ResultSetRowHandler<LoginDayGift> HANDLER = new ResultSetRowHandler<LoginDayGift>() {
		@Override
		public LoginDayGift handleRow(ResultSetRow row) throws Exception {
			LoginDayGift ldg = new LoginDayGift();
			ldg.setId(row.getInt("id"));
			ldg.setArrType(DAOUtil.stringConvIntList(row.getString("arr_type")));
			ldg.setArrItem(DAOUtil.stringConvIntList(row.getString("arr_item")));
			ldg.setArrNum(DAOUtil.stringConvIntList(row.getString("arr_num")));
			return ldg;
		}
	};

	/**
	 * 添加
	 * 
	 * @param LoginDayGift
	 */
	public void add(LoginDayGift ldg) {
		String sql = "insert into t_kaifulibao(" + "arr_type," + "arr_item," + "arr_num" + ")values(?,?,?)";
		execute(sql, DAOUtil.intListConvString(ldg.getArrType()), DAOUtil.intListConvString(ldg.getArrItem()),
				DAOUtil.intListConvString(ldg.getArrNum()));
	}

	/**
	 * 更新
	 * 
	 * @param LoginDayGift
	 */
	public void update(LoginDayGift ldg) {
		String sql = "update t_kaifulibao set " + " arr_type=?," + " arr_item=?," + " arr_num=? " + " where id=?";
		executeUpdate(sql, DAOUtil.intListConvString(ldg.getArrType()), DAOUtil.intListConvString(ldg.getArrItem()),
				DAOUtil.intListConvString(ldg.getArrNum()), ldg.getId());
	}

	/**
	 * 查询
	 */
	public LoginDayGift query(int id) {
		return queryForObject("select * from t_kaifulibao where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<LoginDayGift> queryList() {
		return queryForList("select * from t_kaifulibao", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_kaifulibao where id=?");
	}
}