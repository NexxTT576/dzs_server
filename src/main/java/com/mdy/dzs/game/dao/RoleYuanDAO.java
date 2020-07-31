package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;


/**
 * 精元DAO
 * @author 房曈
 *
 */
public class RoleYuanDAO extends ConnectionResourceDAO{


	private static ResultSetRowHandler<RoleYuan>HANDLER=
		new ResultSetRowHandler<RoleYuan>() {
			@Override
			public RoleYuan handleRow(ResultSetRow row) throws Exception {
				RoleYuan ry=new RoleYuan();
				ry.setId(row.getInt("id"));
				ry.setRoleId(row.getInt("role_id"));
				ry.setPos(row.getInt("pos"));
				ry.setSubpos(row.getInt("subpos"));
				ry.setResId(row.getInt("resId"));
				ry.setQuality(row.getInt("quality"));
				ry.setType(row.getInt("type"));
				ry.setVariety(row.getInt("variety"));
				ry.setLevel(row.getInt("level"));
				ry.setCurExp(row.getInt("curExp"));
				List<Prop> props = JSONUtil.fromJsonList(row.getString("props"),Prop.class);
				ry.setProps(props);
				return ry;
			}
		};
	
	/**
	 * 添加
	 * @param RoleYuan
	 */
	public void addRtKey(RoleYuan ry){
		String sql="insert into t_role_yuan(" +
			"role_id," +
			"pos," +
			"subpos," +
			"resId," +
			"quality," +
			"type," +
			"variety," +
			"level," +
			"curExp," +
			"props" +
			")values(?,?,?,?,?,?,?,?,?,?)";
		int id = executeWithGenKey(sql,
			ry.getRoleId(),
			ry.getPos(),
			ry.getSubpos(),
			ry.getResId(),
			ry.getQuality(),
			ry.getType(),
			ry.getVariety(),
			ry.getLevel(),
			ry.getCurExp(),
			JSONUtil.toJson(ry.getProps()));
		ry.setId(id);
	}
	/**
	 * 更新
	 * @param RoleYuan
	 */
	public void update(RoleYuan ry){
		if(ry == null) return;
		String sql="update t_role_yuan set " +
			" role_id=?," +
			" pos=?," +
			" subpos=?," +
			" resId=?," +
			" quality=?," +
			" type=?," +
			" variety=?," +
			" level=?," +
			" curExp=?," +
			" props=? " +
			" where id=?";
		executeUpdate(sql,
			ry.getRoleId(),
			ry.getPos(),
			ry.getSubpos(),
			ry.getResId(),
			ry.getQuality(),
			ry.getType(),
			ry.getVariety(),
			ry.getLevel(),
			ry.getCurExp(),
			JSONUtil.toJson(ry.getProps()),
			ry.getId());
	}
	/**
	 * 查询
	 */
	public RoleYuan queryByAccItemId(int roleId,int itemId){
		return queryForObject(
			"select * from t_role_yuan where resId=? and role_id = ? order by id",
			HANDLER, itemId,roleId);
	}
	
	/**
	 * 查询
	 */
	public RoleYuan query(int id){
		return queryForObject(
			"select * from t_role_yuan where id=?",
			HANDLER, id);
	}
	
	/**
	 * 查找相同资源内功
	 * @param roleId
	 * @param resId
	 * @return
	 */
	public List<RoleYuan> querySameResYuan(int roleId, int resId) {
		return queryForList("select * from t_role_yuan where role_id=? and resId = ? ORDER BY id",HANDLER,roleId,resId);
	}
	
	/**
	 * 查找相同资源内功根据位置
	 * @param roleId
	 * @param resId
	 * @return
	 */
	public List<RoleYuan> querySameResYuanByPos(int roleId, int resId) {
		return queryForList("select * from t_role_yuan where role_id=? and resId = ? and pos = 0 ORDER BY id",HANDLER,roleId,resId);
	}
	/**
	 * 查询列表
	 */
	public List<RoleYuan> queryListByAccount(int roleId){
		return queryForList("select * from t_role_yuan where role_id = ?",HANDLER,roleId);
	}
	/**
	 * 删除
	 */
	public void delete(RoleYuan ry){
		execute("delete  from t_role_yuan where id=?",ry.getId());
	}
	public int getRoleYuanListNumByAcc(int roleId) {
		return queryForInteger("select count(0) from t_role_yuan where role_id = ?", roleId);
	}

	/**
	 * 查找某一位置所有精元
	 * @param roleId
	 * @param pos
	 * @return
	 */
	public List<RoleYuan> queryListByAccPos(int roleId, int pos) {
		return queryForList(
				"select * from t_role_yuan where role_id=? and pos = ?",
				HANDLER, roleId,pos);
	}

	public void update(RoleYuan updateObj, int oldPos) {
		update(updateObj);
	}
	/**
	 * 查询背包数量
	 * @param roleId
	 * @return
	 */
	public int queryCountByAccount(int roleId) {
		return queryForInteger(
				"select count(0) from t_role_yuan where role_id=?", roleId);
	}
	/**
	 * 查找某一位置某一个格子的真气
	 * @param roleId
	 * @param pos
	 * @return
	 */
	public List<RoleYuan> queryListByAccPos(int roleId, int pos,int subpos) {
		return queryForList(
				"select * from t_role_yuan where role_id=? and pos = ? and subpos =?",
				HANDLER, roleId,pos,subpos);
	}
	
	/**
	 * 查询所有空闲的yuan
	 */
	public List<RoleYuan> queryListBytype(int roleId,int pos,int variety){
		return queryForList("select * from t_role_yuan where role_id = ? and pos = ? and variety <>?",HANDLER,roleId,pos,variety);
	}
}