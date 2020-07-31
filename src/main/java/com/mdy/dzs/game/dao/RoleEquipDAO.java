package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

import flexjson.JSONDeserializer;

/**
 * 装备DAO
 * 
 * @author 房曈
 *
 */
public class RoleEquipDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<Equip> HANDLER = new ResultSetRowHandler<Equip>() {
		@SuppressWarnings("unchecked")
		@Override
		public Equip handleRow(ResultSetRow row) throws Exception {
			Equip e = new Equip();
			e.setId(row.getInt("id"));
			e.setRoleId(row.getInt("role_id"));
			e.setPos(row.getInt("pos"));
			e.setSubpos(row.getInt("subpos"));
			e.setResId(row.getInt("resId"));
			e.setType(row.getInt("type"));
			e.setStar(row.getInt("star"));
			e.setLevel(row.getInt("level"));
			e.setCurExp(row.getInt("curExp"));
			e.setSilver(row.getInt("silver"));
			e.setProps(createListProp(row.getString("props")));
			e.setPropsWait(createListProp(row.getString("propsWait")));
			e.setRelation(JSONUtil.fromJson(row.getString("relation"), List.class));

			return e;
		}
	};

	private static List<Prop> createListProp(String str) {
		return new JSONDeserializer<List<Prop>>().use("values", Prop.class).deserialize(str);
	}

	/**
	 * 添加
	 * 
	 * @param Equip
	 */
	public int add(Equip e) {
		String sql = "insert into t_role_equip(" + "role_id," + "pos," + "subpos," + "resId," + "type," + "star,"
				+ "level," + "curExp," + "silver," + "props," + "propsWait," + "relation"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?)";
		int id = executeWithGenKey(sql, e.getRoleId(), e.getPos(), e.getSubpos(), e.getResId(), e.getType(),
				e.getStar(), e.getLevel(), e.getCurExp(), e.getSilver(), JSONUtil.toJson(e.getProps()),
				JSONUtil.toJson(e.getPropsWait()), JSONUtil.toJson(e.getRelation()));
		return id;
	}

	/**
	 * @param e
	 */
	public void addRecord(Equip e) {
		execute("insert into t_role_equip_record set role_id=?,equip_id=?,cnt=1 " + "on duplicate key update cnt=cnt+1",
				e.getRoleId(), e.getResId());
	}

	/**
	 * 更新
	 * 
	 * @param Equip
	 */
	public void update(Equip e) {
		if (e == null)
			return;
		String sql = "update t_role_equip set " + " role_id=?," + " pos=?," + " subpos=?," + " resId=?," + " type=?,"
				+ " star=?," + " level=?," + " curExp=?," + " silver=?," + " props=?," + " propsWait=?,"
				+ " relation=? " + " where id=?";
		executeUpdate(sql, e.getRoleId(), e.getPos(), e.getSubpos(), e.getResId(), e.getType(), e.getStar(),
				e.getLevel(), e.getCurExp(), e.getSilver(), JSONUtil.toJson(e.getProps()),
				JSONUtil.toJson(e.getPropsWait()), JSONUtil.toJson(e.getRelation()), e.getId());
	}

	/**
	 * 查询
	 */
	public Equip query(int id) {
		return queryForObject("select * from t_role_equip where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Equip> queryList() {
		return queryForList("select * from t_role_equip", HANDLER);
	}

	/**
	 * 查询列表
	 */
	public List<Equip> queryListByAccount(int roleId) {
		return queryForList("select * from t_role_equip where role_id = ?", HANDLER, roleId);
	}

	/**
	 * 删除
	 */
	public void delete(Equip e) {
		execute("delete from t_role_equip where id=?", e.getId());
	}

	/**
	 * 查找某个位置上是否装备某种装备
	 * 
	 * @param roleId
	 * @param rid
	 * @param pos
	 * @return
	 */
	public Integer queryEquipByResIdPos(int roleId, int rid, int pos) {
		return queryForInteger("select id from t_role_equip where role_id=? and pos = ? and resId = ?", roleId, pos,
				rid);
	}

	public void updateProps(Equip e) {
		String sql = "update t_role_equip set " + " props=?," + " propsWait=?" + " where id=?";
		executeUpdate(sql, JSONUtil.toJson(e.getProps()), JSONUtil.toJson(e.getPropsWait()), e.getId());
	}

	/**
	 * 查找某一位置所有装备
	 * 
	 * @param roleId
	 * @param pos
	 * @return
	 */
	public List<Equip> queryListByAccPos(int roleId, int pos) {
		return queryForList("select * from t_role_equip where role_id=? and pos = ?", HANDLER, roleId, pos);
	}

	public void update(Equip updateObj, int oldPos) {
		update(updateObj);
	}

	/**
	 * 查询背包数量
	 * 
	 * @param roleId
	 * @return
	 */
	public int queryCountByAccount(int roleId) {
		return queryForInteger("select count(0) from t_role_equip where role_id=?", roleId);
	}

	public Integer querySameResEquipCount(int roleId, int resId) {
		return queryForInteger("select count(0) from t_role_gong where role_id=? and resId = ?", roleId, resId);
	}

	/**
	 * 查找某一卡牌位置某一装备位置是否存在装备
	 * 
	 * @param roleId
	 * @param pos
	 * @return
	 */
	public List<Equip> queryNumByAccPos(int roleId, int pos, int subpos) {
		return queryForList("select * from t_role_equip where role_id=? and pos = ? and subpos =?", HANDLER, roleId,
				pos, subpos);
	}

	/**
	 * 查找某一子位置所有空闲装备
	 * 
	 * @param roleId
	 * @param pos
	 * @return
	 */
	public List<Equip> queryfreelistByAccPos(int roleId, int subpos, int pos) {
		return queryForList("select * from t_role_equip where role_id=? and subpos = ? and pos =0", HANDLER, roleId,
				subpos);
	}

	private static ResultSetRowHandler<Integer> ROEQUIP_HANDLER = new ResultSetRowHandler<Integer>() {
		@Override
		public Integer handleRow(ResultSetRow row) throws Exception {
			return row.getInt("equip_id");
		}
	};

	// 图鉴查询装备id列表
	public List<Integer> queryRecordEquipId(int roleId) {
		return queryForList("SELECT equip_id FROM t_role_equip_record WHERE role_id = ?", ROEQUIP_HANDLER, roleId);
	}

	public List<Equip> querySameResEquip(int roleId, int resId) {
		return queryForList("select * from t_role_equip where role_id=? and resId = ? ORDER BY id", HANDLER, roleId,
				resId);
	}

	public List<Equip> querySameResEquipByLevel(int roleId, int resId) {
		return queryForList(
				"select * from t_role_equip where role_id=? and resId = ? and pos = 0 and level = 0 ORDER BY id",
				HANDLER, roleId, resId);
	}
}