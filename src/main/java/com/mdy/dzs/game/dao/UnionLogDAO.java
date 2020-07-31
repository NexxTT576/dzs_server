package com.mdy.dzs.game.dao;

import com.mdy.dzs.game.domain.union.UnionLog;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;


/**
 * 道具日志DAO
 * @author 房曈
 *
 */
public class UnionLogDAO extends ConnectionResourceDAO{

	@SuppressWarnings("unused")
	private static ResultSetRowHandler<UnionLog> HANDLER=
		new ResultSetRowHandler<UnionLog>() {
			@Override
			public UnionLog handleRow(ResultSetRow row) throws Exception {
				UnionLog ril=new UnionLog();
				ril.setId(row.getInt("id"));
				ril.setUnionId(row.getInt("union_id"));
				ril.setUnionLevel(row.getInt("union_level"));
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
	 * @param RoleItemLog
	 */
	public void add(UnionLog unionLog){
		String sql="insert into t_union_log(" +
			"union_id," +
			"union_level," +
			"system_type," +
			"item_pos," +
			"item_id," +
			"item_num," +
			"cur_num," +
			"comments," +
			"create_time" +
			")values(?,?,?,?,?,?,?,?,now())";
		execute(sql,
				unionLog.getUnionId(),
				unionLog.getUnionLevel(),
				unionLog.getSystemType(),
				unionLog.getItemPos(),
				unionLog.getItemId(),
				unionLog.getItemNum(),
				unionLog.getNewNum(),
				unionLog.getComments());
	}
	
}