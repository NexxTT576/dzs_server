package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 内外功DAO
 * 
 * @author 房曈
 *
 */
public class RoleGongDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleGong> HANDLER = new ResultSetRowHandler<RoleGong>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleGong handleRow(ResultSetRow row) throws Exception {
			RoleGong g = new RoleGong();
			g.setId(row.getInt("id"));
			g.setRoleId(row.getInt("role_id"));
			g.setPos(row.getInt("pos"));
			g.setSubpos(row.getInt("subpos"));
			g.setResId(row.getInt("resId"));
			g.setType(row.getInt("type"));
			g.setStar(row.getInt("star"));
			g.setLevel(row.getInt("level"));
			g.setCurExp(row.getInt("curExp"));
			List<Prop> props = JSONUtil.fromJsonList(row.getString("props"), Prop.class);
			g.setProps(props);
			g.setPropsN(row.getInt("propsN"));
			g.setRelation(JSONUtil.fromJson(row.getString("relation"), List.class));
			return g;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Gong
	 */
	public void add(RoleGong g) {
		String sql = "insert into t_role_gong(" + "role_id," + "pos," + "subpos," + "resId," + "type," + "star,"
				+ "level," + "curExp," + "props," + "propsN," + "relation" + ")values(?,?,?,?,?,?,?,?,?,?,?)";
		execute(sql, g.getRoleId(), g.getPos(), g.getSubpos(), g.getResId(), g.getType(), g.getStar(), g.getLevel(),
				g.getCurExp(), JSONUtil.toJson(g.getProps()), g.getPropsN(), JSONUtil.toJson(g.getRelation()));
	}

	/**
	 * @param e
	 */
	public void addRecord(RoleGong g) {
		execute("insert into t_role_gong_record set role_id=?,gong_id=?,cnt=1 " + "on duplicate key update cnt=cnt+1",
				g.getRoleId(), g.getResId());
	}

	/**
	 * 更新
	 * 
	 * @param Gong
	 */
	public void update(RoleGong g) {
		if (g == null)
			return;
		String sql = "update t_role_gong set " + " role_id=?," + " pos=?," + " subpos=?," + " resId=?," + " type=?,"
				+ " star=?," + " level=?," + " curExp=?," + " props=?," + " propsN=?," + " relation=? " + " where id=?";
		executeUpdate(sql, g.getRoleId(), g.getPos(), g.getSubpos(), g.getResId(), g.getType(), g.getStar(),
				g.getLevel(), g.getCurExp(), JSONUtil.toJson(g.getProps()), g.getPropsN(),
				JSONUtil.toJson(g.getRelation()), g.getId());
	}

	/**
	 * 查询
	 */
	public RoleGong query(int id) {
		return queryForObject("select * from t_role_gong where id=?", HANDLER, id);
	}

	/**
	 * 查找相同资源内功
	 * 
	 * @param acc
	 * @param resId
	 * @return
	 */
	public List<RoleGong> querySameResGong(int roleId, int resId) {
		return queryForList("select * from t_role_gong where role_id=? and resId = ? ORDER BY id", HANDLER, roleId,
				resId);
	}

	/**
	 * 查找相同资源内功根据等级
	 * 
	 * @param acc
	 * @param resId
	 * @return
	 */
	public List<RoleGong> querySameResGongLevel(int roleId, int resId) {
		return queryForList(
				"select * from t_role_gong where role_id=? and resId = ? and pos=0 and level = 0 and propsN=0 ORDER BY id",
				HANDLER, roleId, resId);
	}

	/**
	 * 查询列表
	 */
	public List<RoleGong> queryListByAccount(int roleId) {
		return queryForList("select * from t_role_gong where role_id = ?", HANDLER, roleId);
	}

	/**
	 * 删除
	 */
	public void delete(RoleGong rg) {
		execute("delete  from t_role_gong where id=?", rg.getId());
	}

	public Integer queryGongByResIdPos(int roleId, int rid, int pos) {
		return queryForInteger("select id from t_role_gong where role_id=? and pos = ? and resId = ?", roleId, pos,
				rid);
	}

	/**
	 * 查找某一位置所有内外功
	 * 
	 * @param acc
	 * @param pos
	 * @return
	 */
	public List<RoleGong> queryListByAccPos(int roleId, int pos) {
		return queryForList("select * from t_role_gong where role_id=? and pos = ?", HANDLER, roleId, pos);
	}

	public void update(RoleGong updateObj, int oldPos) {
		update(updateObj);
	}

	/**
	 * 查询背包数量
	 * 
	 * @param acc
	 * @return
	 */
	public int queryCountByAccount(int roleId) {
		return queryForInteger("select count(0) from t_role_gong where role_id=?", roleId);
	}

	// 查询同类型道具数量
	public int queryCountByRidRestId(int roleid, int resid) {
		return queryForInteger("select count(0) from t_role_gong where role_id=? and resId = ?", roleid, resid);
	}

	/**
	 * 查询某一位置的功
	 * 
	 * @param acc
	 * @return
	 */
	public List<RoleGong> queryListByAccPosSubpos(int roleId, int pos, int subpos) {
		return queryForList("select * from t_role_gong where role_id=? and pos = ? and subpos = ?", HANDLER, roleId,
				pos, subpos);
	}

	private static ResultSetRowHandler<Integer> ROGONG_HANDLER = new ResultSetRowHandler<Integer>() {
		@Override
		public Integer handleRow(ResultSetRow row) throws Exception {
			return row.getInt("gong_id");
		}
	};

	// 图鉴查询卡牌id列表
	public List<Integer> queryRecordGongId(int roleId) {
		return queryForList("SELECT gong_id FROM t_role_gong_record WHERE role_id = ?", ROGONG_HANDLER, roleId);
	}
}