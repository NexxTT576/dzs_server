package com.mdy.dzs.game.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.road.RoleRoad;
import com.mdy.dzs.game.domain.road.RoleRoadCard;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

import flexjson.JSONDeserializer;

/**
 * 名将之路DAO
 * 
 * @author 房曈
 *
 */
public class RoleRoadDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleRoad> HANDLER = new ResultSetRowHandler<RoleRoad>() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public RoleRoad handleRow(ResultSetRow row) throws Exception {
			RoleRoad rr = new RoleRoad();
			rr.setRoleId(row.getInt("role_id"));
			rr.setRoadMainCardId(row.getInt("road_main_card_id"));

			JSONDeserializer deser = new JSONDeserializer().use(null, HashMap.class).use("values", RoleRoadCard.class);
			Map<String, RoleRoadCard> map = (Map<String, RoleRoadCard>) deser.deserialize(row.getString("road_cards"));
			rr.setRoadCards(map);
			rr.setRoadStarCnt(row.getInt("road_star_cnt"));
			rr.setRoadStarAchs(JSONUtil.fromJson(row.getString("road_star_achs"), List.class));
			rr.setCreateTime(row.getTimestamp("create_time"));
			rr.setUpdateTime(row.getTimestamp("update_time"));
			return rr;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleRoad
	 */
	public void add(int roleId) {
		String sql = "insert into t_role_road(" + "role_id," + "create_time," + "update_time"
				+ ")values(?,now(),now())";
		execute(sql, roleId);
	}

	/**
	 * 更新
	 * 
	 * @param RoleRoad
	 */
	public void update(RoleRoad rr) {
		String sql = "update t_role_road set " + " update_time=now(), " + " road_main_card_id=?," + " road_cards=?,"
				+ " road_star_cnt=?," + " road_star_achs=? " + " where role_id=?";
		executeUpdate(sql, rr.getRoadMainCardId(), JSONUtil.toJson(rr.getRoadCards()), rr.getRoadStarCnt(),
				JSONUtil.toJson(rr.getRoadStarAchs()), rr.getRoleId());
	}

	/**
	 * 查询
	 */
	public RoleRoad query(int id) {
		return queryForObject("select * from t_role_road where role_id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleRoad> queryList() {
		return queryForList("select * from t_role_road", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_road where role_id=?", id);
	}
}