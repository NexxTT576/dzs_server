package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.card.RoleCardEffect;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 卡片附加效果DAO
 * 
 * @author 房曈
 *
 */
public class RoleCardEffectDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleCardEffect> HANDLER = new ResultSetRowHandler<RoleCardEffect>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleCardEffect handleRow(ResultSetRow row) throws Exception {
			RoleCardEffect rce = new RoleCardEffect();
			rce.setRoleId(row.getInt("role_id"));
			rce.setCardId(row.getInt("card_id"));
			rce.setEffectCardRelation(JSONUtil.fromJson(row.getString("effect_card_relation"), List.class));
			rce.setCreateTime(row.getTimestamp("create_time"));
			rce.setUpdateTime(row.getTimestamp("update_time"));
			return rce;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleCardEffect
	 */
	public void add(RoleCardEffect rce) {
		String sql = "insert into t_role_card_effect(" + "role_id," + "card_id," + "effect_card_relation,"
				+ "create_time," + "update_time" + ")values(?,?,?,now(),now())";
		execute(sql, rce.getRoleId(), rce.getCardId(), JSONUtil.toJson(rce.getEffectCardRelation()));
	}

	/**
	 * 更新
	 * 
	 * @param RoleCardEffect
	 */
	public void update(RoleCardEffect rce) {
		String sql = "update t_role_card_effect set " + " update_time=now(), " + " effect_card_relation=? "
				+ " where role_id=? and card_id=?";
		executeUpdate(sql, JSONUtil.toJson(rce.getEffectCardRelation()), rce.getRoleId(), rce.getCardId());
	}

	/**
	 * 查询
	 */
	public RoleCardEffect queryByRoleIdCardId(int roleId, int cardId) {
		return queryForObject("select * from t_role_card_effect where role_id=? and card_id=?", HANDLER, roleId,
				cardId);
	}

	/**
	 * 查询列表
	 */
	public List<RoleCardEffect> queryListByRoleId(int roleId) {
		return queryForList("select * from t_role_card_effect where role_id=?", HANDLER, roleId);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_card_effect where id=?", HANDLER, id);
	}
}