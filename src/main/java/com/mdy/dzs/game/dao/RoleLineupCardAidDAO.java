package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.RoleLineupAid.RoleLineupCardAid;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 用户辅助阵型卡牌DAO
 * 
 * @author zhou
 *
 */
public class RoleLineupCardAidDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleLineupCardAid> HANDLER = new ResultSetRowHandler<RoleLineupCardAid>() {
		@Override
		public RoleLineupCardAid handleRow(ResultSetRow row) throws Exception {
			RoleLineupCardAid rlca = new RoleLineupCardAid();
			rlca.setId(row.getInt("id"));
			rlca.setRoleId(row.getInt("roleId"));
			rlca.setSysId(row.getInt("sysId"));
			rlca.setCardId(row.getInt("cardId"));
			rlca.setResId(row.getInt("resId"));
			rlca.setLifeRate(row.getInt("life_rate"));
			rlca.setAnger(row.getInt("anger"));
			rlca.setPos(row.getInt("pos"));
			rlca.setCreateTime(row.getTimestamp("create_time"));
			rlca.setUpdateTime(row.getTimestamp("update_time"));
			return rlca;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleLineupCardAid
	 */
	public void add(RoleLineupCardAid rlca) {
		String sql = "insert into t_role_lineup_card(" + "roleId," + "sysId," + "cardId," + "resId," + "life_rate,"
				+ "anger," + "pos," + "create_time," + "update_time" + ")values(?,?,?,?,?,?,?,now(),now())";
		execute(sql, rlca.getRoleId(), rlca.getSysId(), rlca.getCardId(), rlca.getResId(), rlca.getLifeRate(),
				rlca.getAnger(), rlca.getPos());
	}

	/**
	 * 更新
	 * 
	 * @param RoleLineupCardAid
	 */
	public void update(RoleLineupCardAid rlca) {
		String sql = "update t_role_lineup_card set " + " update_time=now(), " + " roleId=?," + " sysId=?,"
				+ " cardId=?," + " resId=?," + " life_rate=?," + " anger=?," + " pos=? " + " where id=?";
		executeUpdate(sql, rlca.getRoleId(), rlca.getSysId(), rlca.getCardId(), rlca.getResId(), rlca.getLifeRate(),
				rlca.getAnger(), rlca.getPos(), rlca.getId());
	}

	/**
	 * 查询
	 */
	public RoleLineupCardAid query(int id) {
		return queryForObject("select * from t_role_lineup_card where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleLineupCardAid> queryList() {
		return queryForList("select * from t_role_lineup_card", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_lineup_card where id=?", HANDLER, id);
	}

	// ==================================================保存位置=============================================//
	/**
	 * 根据阵型id查询列表
	 * 
	 * @return
	 */
	public List<RoleLineupCardAid> queryListByRoleId_SysId(int roleId, int sysId) {
		return queryForList("select * from t_role_lineup_card where roleId=? and sysId=?", HANDLER, roleId, sysId);
	}

	/**
	 * 根据角色id 系统id 卡牌id 查询
	 * 
	 * @param roleId
	 * @param sysId
	 * @param cardId
	 * @return
	 */
	public RoleLineupCardAid queryByRoleId_SysId_CardId(int roleId, int sysId, int cardId) {
		return queryForObject("select * from t_role_lineup_card where roleId=? and sysId=? and cardId=?", HANDLER,
				roleId, sysId, cardId);
	}

	/**
	 * 只更新位置
	 * 
	 * @param id
	 * @param pos
	 */
	public void updatePos(int id, int pos) {
		String sql = "update t_role_lineup_card set " + " update_time=now(), " + " pos=? " + " where id=?";
		executeUpdate(sql, pos, id);
	}

	public void deleteByRoleId_SysId(int roleId, int sysId) {
		execute("delete  from t_role_lineup_card where roleId=? and sysId=?", HANDLER, roleId, sysId);
	}
}