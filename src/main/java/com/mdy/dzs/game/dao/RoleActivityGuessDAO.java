package com.mdy.dzs.game.dao;

import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.activity.guessgame.RoleActivityGuess;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 活动猜拳DAO
 * 
 * @author 房曈
 *
 */
public class RoleActivityGuessDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleActivityGuess> HANDLER = new ResultSetRowHandler<RoleActivityGuess>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleActivityGuess handleRow(ResultSetRow row) throws Exception {
			RoleActivityGuess rag = new RoleActivityGuess();
			rag.setRoleId(row.getInt("role_id"));
			rag.setGuessCount(row.getInt("guess_count"));
			rag.setBuyCount(row.getInt("buy_count"));
			rag.setAllGuessCount(row.getInt("all_guess_count"));
			rag.setChooseCount(row.getInt("choose_count"));
			List<ProbItem> items = JSONUtil.fromJsonList(row.getString("item_list"), ProbItem.class);
			rag.setItemList(items);
			rag.setChoosePosList(JSONUtil.fromJson(row.getString("choose_pos_list"), Map.class));
			rag.setCreateTime(row.getTimestamp("create_time"));
			rag.setUpdateTime(row.getTimestamp("update_time"));
			return rag;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleActivityGuess
	 */
	public void add(RoleActivityGuess rag) {
		String sql = "insert into t_role_activity_guess(" + "role_id," + "guess_count," + "create_time," + "update_time"
				+ ")values(?,?,now(),now())";
		execute(sql, rag.getRoleId(), rag.getGuessCount());
	}

	/**
	 * 更新
	 * 
	 * @param RoleActivityGuess
	 */
	public void update(RoleActivityGuess rag) {
		String sql = "update t_role_activity_guess set " + " update_time=now(), " + " guess_count=?,"
				+ " all_guess_count=?," + " choose_count=?," + " buy_count=?," + " item_list=?," + " choose_pos_list=? "
				+ " where role_id=?";
		executeUpdate(sql, rag.getGuessCount(), rag.getAllGuessCount(), rag.getChooseCount(), rag.getBuyCount(),
				JSONUtil.toJson(rag.getItemList()), JSONUtil.toJson(rag.getChoosePosList()), rag.getRoleId());
	}

	/**
	 * 查询
	 */
	public RoleActivityGuess query(int id) {
		return queryForObject("select * from t_role_activity_guess where role_id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleActivityGuess> queryList() {
		return queryForList("select * from t_role_activity_guess", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_activity_guess where role_id=?", HANDLER, id);
	}
}