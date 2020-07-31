package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.shop.Shop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 商城DAO
 * 
 * @author 房曈
 *
 */
public class ShopDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<Shop> HANDLER = new ResultSetRowHandler<Shop>() {
		@Override
		public Shop handleRow(ResultSetRow row) throws Exception {
			Shop s = new Shop();
			s.setId(row.getInt("id"));
			s.setType(row.getInt("type"));
			s.setItemId(row.getInt("itemId"));
			s.setCoinType(row.getInt("coinType"));
			s.setPrice(row.getInt("price"));
			s.setAddPrice(row.getInt("addPrice"));
			s.setOpen(row.getInt("open"));
			s.setServer(row.getInt("server"));
			s.setBegin(row.getTimestamp("begin"));
			s.setStop(row.getTimestamp("stop"));
			s.setMaxN(row.getInt("maxN"));
			return s;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Shop
	 */
	public void add(Shop s) {
		String sql = "insert into t_shop(" + "type," + "itemId," + "coinType," + "price," + "addPrice," + "open,"
				+ "server," + "begin," + "stop," + "maxN" + ")values(?,?,?,?,?,?,?,?,?,?)";
		execute(sql, s.getType(), s.getItemId(), s.getCoinType(), s.getPrice(), s.getAddPrice(), s.getOpen(),
				s.getServer(), s.getBegin(), s.getStop(), s.getMaxN());
	}

	/**
	 * 更新
	 * 
	 * @param Shop
	 */
	public void update(Shop s) {
		String sql = "update t_shop set " + " type=?," + " itemId=?," + " coinType=?," + " price=?," + " addPrice=?,"
				+ " open=?," + " server=?," + " begin=?," + " stop=?," + " maxN=?" + " where id=?";
		executeUpdate(sql, s.getType(), s.getItemId(), s.getCoinType(), s.getPrice(), s.getAddPrice(), s.getOpen(),
				s.getServer(), s.getBegin(), s.getStop(), s.getMaxN(), s.getId());
	}

	/**
	 * 查询
	 */
	public Shop query(int id) {
		return queryForObject("select * from t_shop where id=?", HANDLER, id);
	}

	/**
	 * 查询开放列表
	 */
	public List<Shop> queryOpenListByTime(Date now) {
		return queryForList("select * from t_shop where open = 1 and begin<=? and stop >= ? or stop = 0", HANDLER, now,
				now);
	}

	/**
	 * 查询开放列表
	 */
	public Shop queryOpenItemByTime(Date now, int itemId) {
		return queryForObject(
				"select * from t_shop where itemId = ? and open = 1 and (begin<=? and stop >= ? or stop = 0)", HANDLER,
				itemId, now, now);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_shop where id=?", HANDLER, id);
	}
}