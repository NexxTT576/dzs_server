package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.gift.LevelGift;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 等级礼包DAO
 * 
 * @author 房曈
 *
 */
public class LevelGiftDAO extends ConnectionResourceDAO implements QueryDateInterface<LevelGift> {

	private static ResultSetRowHandler<LevelGift> HANDLER = new ResultSetRowHandler<LevelGift>() {
		@Override
		public LevelGift handleRow(ResultSetRow row) throws Exception {
			LevelGift lg = new LevelGift();
			lg.setId(row.getInt("id"));
			lg.setLevel(row.getInt("level"));
			lg.setArrType(DAOUtil.stringConvIntList(row.getString("arr_type")));
			lg.setArrItem(DAOUtil.stringConvIntList(row.getString("arr_item")));
			lg.setArrNum(DAOUtil.stringConvIntList(row.getString("arr_num")));
			return lg;
		}
	};

	/**
	 * 添加
	 * 
	 * @param LevelGift
	 */
	public void add(LevelGift lg) {
		String sql = "insert into t_dengji(" + "level," + "arr_type," + "arr_item," + "arr_num" + ")values(?,?,?,?)";
		execute(sql, lg.getLevel(), DAOUtil.intListConvString(lg.getArrType()),
				DAOUtil.intListConvString(lg.getArrItem()), DAOUtil.intListConvString(lg.getArrNum()));
	}

	/**
	 * 更新
	 * 
	 * @param LevelGift
	 */
	public void update(LevelGift lg) {
		String sql = "update t_dengji set " + " level=?," + " arr_type=?," + " arr_item=?," + " arr_num=? "
				+ " where id=?";
		executeUpdate(sql, lg.getLevel(), DAOUtil.intListConvString(lg.getArrType()),
				DAOUtil.intListConvString(lg.getArrItem()), DAOUtil.intListConvString(lg.getArrNum()), lg.getId());
	}

	/**
	 * 查询
	 */
	public LevelGift query(int id) {
		return queryForObject("select * from t_dengji where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<LevelGift> queryList() {
		return queryForList("select * from t_dengji", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_dengji where id=?", HANDLER, id);
	}
}