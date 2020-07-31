package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.biwu.BiwuShop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 比武商店DAO
 * 
 * @author zhou
 *
 */
public class BiwuShopDAO extends ConnectionResourceDAO implements QueryDateInterface<BiwuShop> {

	private static ResultSetRowHandler<BiwuShop> HANDLER = new ResultSetRowHandler<BiwuShop>() {
		@Override
		public BiwuShop handleRow(ResultSetRow row) throws Exception {
			BiwuShop bs = new BiwuShop();
			bs.setId(row.getInt("id"));
			bs.setType(row.getInt("type"));
			bs.setItem(row.getInt("item"));
			bs.setNum(row.getInt("num"));
			bs.setType1(row.getInt("type1"));
			bs.setNum1(row.getInt("num1"));
			bs.setPrice(row.getInt("price"));
			bs.setLevel(row.getInt("level"));
			return bs;
		}
	};

	/**
	 * 添加
	 * 
	 * @param BiwuShop
	 */
	public void add(BiwuShop bs) {
		String sql = "insert into t_biwu_shop(" + "type," + "item," + "num," + "type1," + "num1," + "price," + "level"
				+ ")values(?,?,?,?,?,?,?)";
		execute(sql, bs.getType(), bs.getItem(), bs.getNum(), bs.getType1(), bs.getNum1(), bs.getPrice(),
				bs.getLevel());
	}

	/**
	 * 更新
	 * 
	 * @param BiwuShop
	 */
	public void update(BiwuShop bs) {
		String sql = "update t_biwu_shop set " + " type=?," + " item=?," + " num=?," + " type1=?," + " num1=?,"
				+ " price=?," + " level=? " + " where id=?";
		executeUpdate(sql, bs.getType(), bs.getItem(), bs.getNum(), bs.getType1(), bs.getNum1(), bs.getPrice(),
				bs.getLevel(), bs.getId());
	}

	/**
	 * 查询
	 */
	public BiwuShop query(int id) {
		return queryForObject("select * from t_biwu_shop where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<BiwuShop> queryList() {
		return queryForList("select * from t_biwu_shop", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_biwu_shop where id=?", HANDLER, id);
	}
}