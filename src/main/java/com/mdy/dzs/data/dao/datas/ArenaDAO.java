package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.arena.Arena;
import com.mdy.dzs.data.domain.arena.ArenaShop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 竞技场DAO
 * 
 * @author 房曈
 *
 */
public class ArenaDAO extends ConnectionResourceDAO implements QueryDateInterface<Arena> {

	private static ResultSetRowHandler<Arena> HANDLER = new ResultSetRowHandler<Arena>() {
		@Override
		public Arena handleRow(ResultSetRow row) throws Exception {
			Arena a = new Arena();
			a.setId(row.getInt("id"));
			a.setType(row.getInt("type"));
			a.setMin(row.getInt("min"));
			a.setMax(row.getInt("max"));
			a.setRatio(row.getFloat("ratio"));
			a.setFix(row.getInt("fix"));
			return a;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Arena
	 */
	public void add(Arena a) {
		String sql = "insert into t_jingjichang(" + "type," + "min," + "max," + "ratio," + "fix" + ")values(?,?,?,?,?)";
		execute(sql, a.getType(), a.getMin(), a.getMax(), a.getRatio(), a.getFix());
	}

	/**
	 * 更新
	 * 
	 * @param Arena
	 */
	public void update(Arena a) {
		String sql = "update t_jingjichang set " + " type=?," + " min=?," + " max=?," + " ratio=?," + " fix=? "
				+ " where id=?";
		executeUpdate(sql, a.getType(), a.getMin(), a.getMax(), a.getRatio(), a.getFix(), a.getId());
	}

	/**
	 * 查询
	 */
	public Arena query(int id) {
		return queryForObject("select * from t_jingjichang where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Arena> queryList() {
		return queryForList("select * from t_jingjichang", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_jingjichang where id=?", HANDLER, id);
	}

	////////////////////////////////////////////////////////////////////////////////////

	private static ResultSetRowHandler<ArenaShop> SHOP_HANDLER = new ResultSetRowHandler<ArenaShop>() {
		@Override
		public ArenaShop handleRow(ResultSetRow row) throws Exception {
			ArenaShop as = new ArenaShop();
			as.setId(row.getInt("id"));
			as.setShoptype(row.getInt("shoptype"));
			as.setType(row.getInt("type"));
			as.setItem(row.getInt("item"));
			as.setNum(row.getInt("num"));
			as.setType1(row.getInt("type1"));
			as.setNum1(row.getInt("num1"));
			as.setPrice(row.getInt("price"));
			as.setLevel(row.getInt("level"));
			return as;
		}
	};

	/**
	 * 查询列表
	 */
	public List<ArenaShop> queryArenaShopList() {
		return queryForList("select * from t_shop_jingjichang", SHOP_HANDLER);
	}

}