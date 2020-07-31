package com.mdy.dzs.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mdy.dzs.data.domain.server.ServerInfo;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 服务器信息表DAO
 * 
 * @author yanfeng.wang
 * 
 */
public class ServerInfoDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<ServerInfo> HANDLER = new ResultSetRowHandler<ServerInfo>() {
		@Override
		public ServerInfo handleRow(ResultSetRow row) throws Exception {
			ServerInfo si = new ServerInfo();
			si.setId(row.getString("id"));
			si.setZoneId(row.getInt("zone_id"));
			si.setName(row.getString("name"));
			si.setName1(row.getString("name1"));
			si.setDescription(row.getString("description"));
			si.setManageStatus(row.getInt("manage_status"));
			si.setManageInfo(row.getString("manage_info"));
			si.setMergerType(row.getInt("merger_type"));
			si.setDebugStatus(row.getInt("debug_status"));
			si.setGmKey(row.getString("gm_key"));
			si.setAddr(row.getString("addr"));
			si.setCluster(row.getString("cluster"));
			si.setConfig(row.getString("config"));
			si.setPort(row.getInt("port"));
			si.setLastActiveTime(row.getTimestamp("last_active_time"));
			si.setStartTime(row.getTimestamp("start_time"));
			si.setStatus(row.getInt("status"));
			si.setMaxSessionCount(row.getInt("max_session_count"));
			si.setSessionCount(row.getInt("session_count"));
			si.setDisConnSessionCount(row.getInt("dis_conn_session_count"));
			si.setCreateTime(row.getTimestamp("create_time"));
			si.setUpdateTime(row.getTimestamp("update_time"));
			return si;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ServerInfo
	 */
	public void add(ServerInfo si) {
		String sql = "insert into t_server_info(" + "id," + "name," + "description," + "manage_status," + "manage_info,"
				+ "addr," + "cluster," + "config," + "port," + "last_active_time," + "start_time," + "status,"
				+ "max_session_count," + "session_count," + "dis_conn_session_count," + "gm_key," + "create_time,"
				+ "update_time" + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now())";
		execute(sql, si.getId(), si.getName(), si.getName(), si.getManageStatus(), si.getManageInfo(), si.getAddr(),
				si.getCluster(), "{}", // si.getConfig(),
				si.getPort(), si.getLastActiveTime(), si.getStartTime(), si.getStatus(), si.getMaxSessionCount(),
				si.getSessionCount(), si.getDisConnSessionCount(), UUID.randomUUID().toString().replace("-", ""));
	}

	/**
	 * 更新
	 * 
	 * @param ServerInfo
	 */
	public void update(ServerInfo si) {
		String sql = "update t_server_info set " + " update_time=now(), " + " name=?," + " description=?,"
				+ " manage_status=?," + " manage_info=?," + " addr=?," + " cluster=?," + " config=?," + " port=?,"
				+ " last_active_time=?," + " start_time=?," + " status=?," + " max_session_count=?,"
				+ " session_count=?," + " dis_conn_session_count=?, " + " gm_key=? " + " where id=?";
		executeUpdate(sql, si.getName(), si.getDescription(), si.getManageStatus(), si.getManageInfo(), si.getAddr(),
				si.getCluster(), si.getConfig(), si.getPort(), si.getLastActiveTime(), si.getStartTime(),
				si.getStatus(), si.getMaxSessionCount(), si.getSessionCount(), si.getDisConnSessionCount(),
				UUID.randomUUID().toString().replace("-", ""), si.getId());
	}

	/**
	 * 查询
	 */
	public ServerInfo query(String id) {
		return queryForObject("select * from t_server_info where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ServerInfo> queryList() {
		return queryForList("select * from t_server_info", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_server_info where id=?", HANDLER, id);
	}

	public void batchUpdateServer(String ids, int status) {
		String sql = "update t_server_info set status=? where id=?";
		String[] tmp = ids.split(",");

		List<Object[]> list = new ArrayList<Object[]>();
		for (String id : tmp) {
			list.add(new Object[] { status, id });
		}
		executeBatch(sql, list);
	}
}