package com.mdy.dzs.game.dao;

import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 道具日志DAO
 * 
 * @author 房曈
 *
 */
public class RoleItemLogDAO extends ConnectionResourceDAO {

	/* 表名称 */
	public static final String TABLE_NAME = "t_role_item_log";
	/* 分表数 */
	public static final int TABLE_PART_NUM = 10;

	/* 获取表名 */
	private static String getTableName(int roleId) {
		return TABLE_NAME + "_" + roleId % TABLE_PART_NUM;
	}

	private static ResultSetRowHandler<RoleItemLog> HANDLER = new ResultSetRowHandler<RoleItemLog>() {
		@Override
		public RoleItemLog handleRow(ResultSetRow row) throws Exception {
			RoleItemLog ril = new RoleItemLog();
			ril.setId(row.getInt("id"));
			ril.setBatchNo(row.getString("batch_no"));
			ril.setRoleId(row.getInt("role_id"));
			ril.setRoleLevel(row.getInt("role_level"));
			ril.setSystemType(row.getInt("system_type"));
			ril.setItemPos(row.getInt("item_pos"));
			ril.setItemId(row.getInt("item_id"));
			ril.setItemNum(row.getInt("item_num"));
			ril.setNewNum(row.getInt("cur_num"));
			ril.setComments(row.getString("comments"));
			ril.setCreateTime(row.getTimestamp("create_time"));
			return ril;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleItemLog
	 */
	public void add(RoleItemLog ril) {
		String sql = "insert into " + getTableName(ril.getRoleId()) + "(" + "role_id," + "role_level," + "batch_no,"
				+ "system_type," + "item_pos," + "item_id," + "item_num," + "cur_num," + "comments," + "create_time"
				+ ")values(?,?,?,?,?,?,?,?,?,now())";
		execute(sql, ril.getRoleId(), ril.getRoleLevel(), ril.getBatchNo(), ril.getSystemType(), ril.getItemPos(),
				ril.getItemId(), ril.getItemNum(), ril.getNewNum(), ril.getComments());
	}

}