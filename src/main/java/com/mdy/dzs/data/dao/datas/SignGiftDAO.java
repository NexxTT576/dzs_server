package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.gift.SignGift;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 签到礼包DAO
 * 
 * @author 房曈
 *
 */
public class SignGiftDAO extends ConnectionResourceDAO implements QueryDateInterface<SignGift> {

	private static ResultSetRowHandler<SignGift> HANDLER = new ResultSetRowHandler<SignGift>() {
		@Override
		public SignGift handleRow(ResultSetRow row) throws Exception {
			SignGift sg = new SignGift();
			sg.setId(row.getInt("id"));
			sg.setArrType(DAOUtil.stringConvIntList(row.getString("arr_type")));
			sg.setArrItem(DAOUtil.stringConvIntList(row.getString("arr_item")));
			sg.setArrNum(DAOUtil.stringConvIntList(row.getString("arr_num")));
			return sg;
		}
	};

	/**
	 * 添加
	 * 
	 * @param SignGift
	 */
	public void add(SignGift sg) {
		String sql = "insert into t_qiandao(" + "arr_type," + "arr_item," + "arr_num" + ")values(?,?,?)";
		execute(sql, DAOUtil.intListConvString(sg.getArrType()), DAOUtil.intListConvString(sg.getArrItem()),
				DAOUtil.intListConvString(sg.getArrNum()));
	}

	/**
	 * 更新
	 * 
	 * @param SignGift
	 */
	public void update(SignGift sg) {
		String sql = "update t_qiandao set " + " arr_type=?," + " arr_item=?," + " arr_num=? " + " where id=?";
		executeUpdate(sql, DAOUtil.intListConvString(sg.getArrType()), DAOUtil.intListConvString(sg.getArrItem()),
				DAOUtil.intListConvString(sg.getArrNum()), sg.getId());
	}

	/**
	 * 查询
	 */
	public SignGift query(int id) {
		return queryForObject("select * from t_qiandao where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<SignGift> queryList() {
		return queryForList("select * from t_qiandao", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_qiandao where id=?", HANDLER, id);
	}
}