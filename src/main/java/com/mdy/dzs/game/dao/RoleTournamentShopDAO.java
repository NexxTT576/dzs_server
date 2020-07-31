package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.tournament.RoleTournamentShop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 比武的兑换DAO
 * 
 * @author zhou
 *
 */
public class RoleTournamentShopDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleTournamentShop> HANDLER = new ResultSetRowHandler<RoleTournamentShop>() {
		@Override
		public RoleTournamentShop handleRow(ResultSetRow row) throws Exception {
			RoleTournamentShop rts = new RoleTournamentShop();
			rts.setRoleId(row.getInt("role_id"));
			rts.setItemId(row.getInt("item_id"));
			rts.setType(row.getInt("type"));
			rts.setExchangeTimes(row.getInt("exchange_times"));
			rts.setCreateTime(row.getTimestamp("create_time"));
			rts.setUpdateTime(row.getTimestamp("update_time"));
			return rts;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleTournamentShop
	 */
	public void add(RoleTournamentShop rts) {
		String sql = "insert into t_role_tournament_shop(" + "role_id," + "item_id," + "type," + "exchange_times,"
				+ "create_time," + "update_time" + ")values(?,?,?,?,now(),now())";
		execute(sql, rts.getRoleId(), rts.getItemId(), rts.getType(), rts.getExchangeTimes());
	}

	/**
	 * 更新
	 * 
	 * @param RoleTournamentShop
	 */
	public void update(RoleTournamentShop rts) {
		String sql = "update t_role_tournament_shop set " + " update_time=now(), " + " type=?," + " exchange_times=? "
				+ " where role_id=? and item_id=?";
		executeUpdate(sql, rts.getType(), rts.getExchangeTimes(), rts.getRoleId(), rts.getItemId());
	}

	/**
	 * 查询
	 */
	public RoleTournamentShop query(int id, int itemId) {
		return queryForObject("select * from t_role_tournament_shop where role_id=? and item_id=?", HANDLER, id,
				itemId);
	}

	/**
	 * 查询列表
	 */
	public List<RoleTournamentShop> queryList() {
		return queryForList("select * from t_role_tournament_shop", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_tournament_shop where role_id=?", HANDLER, id);
	}

	/**
	 * 删除所有
	 */
	public void deleteAll(List<Integer> types) {
		String s = "(";
		for (Integer integer : types) {
			s += integer + ",";
		}
		s = s.substring(0, s.length() - 1);
		s += ")";
		execute("delete  from t_role_tournament_shop where type in " + s);
	}

	/**
	 * 查询出物品的id
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleTournamentShop> queryListByRoleId(int roleId) {
		return queryForList("select * from t_role_tournament_shop where role_id=?", HANDLER, roleId);
	}

	/**
	 * 删除一个人的数据
	 * 
	 * @param types
	 * @param roleId
	 */
	public void deleteOne(List<Integer> types, int roleId) {
		String s = "(";
		for (Integer integer : types) {
			s += integer + ",";
		}
		s = s.substring(0, s.length() - 1);
		s += ")";
		execute("delete  from t_role_tournament_shop where role_id = ? and type in " + s, roleId);
	}
}