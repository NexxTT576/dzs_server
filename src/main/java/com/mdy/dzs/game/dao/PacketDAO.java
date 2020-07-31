package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.exception.PacketException;
import com.mdy.sharp.container.biz.BizException;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 装备碎片DAO
 * 
 * @author 房曈
 *
 */
public class PacketDAO extends ConnectionResourceDAO {

	// 3,5,7,9,10,11,12
	private String[] typeToTables = { "", "", "t_role_equip_item", "", "t_role_card_item", "", "t_role_item", "",
			"t_role_gong_item", "t_role_gong_item", "t_role_item", "t_role_item" };

	private static ResultSetRowHandler<RoleItem> HANDLER = new ResultSetRowHandler<RoleItem>() {
		@Override
		public RoleItem handleRow(ResultSetRow row) throws Exception {
			RoleItem rei = new RoleItem();
			rei.setId(row.getInt("id"));
			rei.setRoleId(row.getInt("role_id"));
			rei.setItemId(row.getInt("item_id"));
			rei.setItemCnt(row.getInt("item_cnt"));
			rei.setItemLimit(row.getInt("item_limit"));
			return rei;
		}
	};

	private static ResultSetRowHandler<RoleItem> HANDLER_BOXPRO = new ResultSetRowHandler<RoleItem>() {
		@Override
		public RoleItem handleRow(ResultSetRow row) throws Exception {
			RoleItem rei = new RoleItem();
			rei.setId(row.getInt("id"));
			rei.setRoleId(row.getInt("role_id"));
			rei.setItemId(row.getInt("item_id"));
			rei.setItemCnt(row.getInt("item_cnt"));
			rei.setItemLimit(row.getInt("item_limit"));
			rei.setBoxpro(row.getInt("boxpro"));
			return rei;
		}
	};

	private String checkType(int type) throws BizException {
		String tables = typeToTables[type - 1];
		if (tables == "") {
			throw PacketException.getException(PacketException.EXCE_TYPE_NOT_ITEM, type);
		}
		return tables;
	}

	/**
	 * 添加
	 * 
	 * @param RoleItem
	 */
	public int add(RoleItem rei, int type) throws BizException {
		String table = checkType(type);
		String sql = "insert into " + table + "(" + "role_id," + "item_id," + "item_cnt," + "item_limit"
				+ ")values(?,?,?,?)";
		int id = executeWithGenKey(sql, rei.getRoleId(), rei.getItemId(), rei.getItemCnt(), rei.getItemLimit());
		return id;
	}

	// 修改购买的金箱子优质概率
	public void updateBoxPro(int roleid, int pronum, int itemid) {
		String sql = "update t_role_item set " + " boxpro = boxpro + ? where role_id = ? and item_id = ?";
		executeUpdate(sql, pronum, roleid, itemid);
	}

	public void updateBoxProTo(int roleid, int pronum, int itemid) {
		String sql = "update t_role_item set " + " boxpro = ? where role_id = ? and item_id = ?";
		executeUpdate(sql, pronum, roleid, itemid);
	}

	/**
	 * 更新
	 * 
	 * @param RoleItem
	 */
	public void update(RoleItem rei, int type) throws BizException {
		String table = checkType(type);
		String sql = "update " + table + " set " + " role_id=?," + " item_id=?," + " item_cnt=?," + " item_limit=? "
				+ " where id=?";
		executeUpdate(sql, rei.getRoleId(), rei.getItemId(), rei.getItemCnt(), rei.getItemLimit(), rei.getId());
	}

	/**
	 * 查询
	 */
	public RoleItem queryByAccItemId(int roleId, int itemId, int type) throws BizException {
		String table = checkType(type);
		if (table.equals("t_role_item")) {
			return queryForObject("select * from " + table + " where item_id=? and role_id=?", HANDLER_BOXPRO, itemId,
					roleId);
		} else {
			return queryForObject("select * from " + table + " where item_id=? and role_id=?", HANDLER, itemId, roleId);
		}
	}

	// //查询用户武学碎片最大数(互斥使用)
	// public List<RoleItem> getMaxGongItem(String sqls,int roleid)
	// {
	// String sql = "select * from t_role_gong_item where item_id in("+sqls+") and
	// role_id = " + roleid;
	// return queryForList(sql,HANDLER);
	// }

	/**
	 * 查询列表
	 */
	public List<RoleItem> queryListByAccount(int roleId, int type) throws BizException {
		String table = checkType(type);
		return queryForList("select * from " + table + " where role_id = ?", HANDLER, roleId);
	}

	/**
	 * 删除
	 */
	public void delete(RoleItem ri, int type) throws BizException {
		String table = checkType(type);
		execute("delete  from " + table + " where id=?", ri.getId());
	}

	public List<RoleItem> queryRoleItemListByResIds(int roleId, List<Integer> resIds) {

		String ids = "";
		for (Integer integer : resIds) {
			if (integer != 0) {
				ids += integer + ",";
			}
		}
		ids = ids.substring(0, ids.length() - 1);

		return queryForList("select * from t_role_item where role_id = ? and item_id in (" + ids + ")", HANDLER,
				roleId);
	}

	/**
	 * 查询背包数量
	 * 
	 * @param roleId
	 * @return
	 * @throws BizException
	 */
	public int queryCountByAccount(int roleId, int t) throws BizException {
		String table = checkType(t);
		return queryForInteger("select count(0) from " + table + " where role_id=?", roleId);
	}
}