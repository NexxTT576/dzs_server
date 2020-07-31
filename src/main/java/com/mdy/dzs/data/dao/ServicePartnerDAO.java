package com.mdy.dzs.data.dao;

import java.util.List;

import com.mdy.dzs.data.domain.server.ServicePartner;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 合作方DAO
 * 
 * @author 房曈
 *
 */
public class ServicePartnerDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<ServicePartner> HANDLER = new ResultSetRowHandler<ServicePartner>() {
		@Override
		public ServicePartner handleRow(ResultSetRow row) throws Exception {
			ServicePartner sp = new ServicePartner();
			sp.setId(row.getInt("id"));
			sp.setStrChannelName(row.getString("str_channelName"));
			sp.setAccountHead(row.getString("accountHead"));
			sp.setStrPlatform(row.getString("str_platform"));
			sp.setLoginUrl(row.getString("loginUrl"));
			sp.setVersionUrl(row.getString("versionUrl"));
			sp.setAppid(row.getString("appid"));
			sp.setAppkey(row.getString("appkey"));
			sp.setExtendKey1(row.getString("extendKey1"));
			sp.setExtendKey2(row.getString("extendKey2"));
			sp.setUpdateTime(row.getTimestamp("update_time"));
			return sp;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ServicePartner
	 */
	public void add(ServicePartner sp) {
		String sql = "insert into t_serverurl(" + "str_channelName," + "accountHead," + "str_platform," + "loginUrl,"
				+ "versionUrl," + "appid," + "appkey," + "update_time" + ")values(?,?,?,?,?,?,?,now())";
		execute(sql, sp.getStrChannelName(), sp.getAccountHead(), sp.getStrPlatform(), sp.getLoginUrl(),
				sp.getVersionUrl(), sp.getAppid(), sp.getAppkey());
	}

	/**
	 * 更新
	 * 
	 * @param ServicePartner
	 */
	public void update(ServicePartner sp) {
		String sql = "update t_serverurl set " + " update_time=now(), " + " str_channelName=?," + " accountHead=?,"
				+ " str_platform=?," + " loginUrl=?," + " versionUrl=?," + " appid=?," + " appkey=? " + " where id=?";
		executeUpdate(sql, sp.getStrChannelName(), sp.getAccountHead(), sp.getStrPlatform(), sp.getLoginUrl(),
				sp.getVersionUrl(), sp.getAppid(), sp.getAppkey(), sp.getId());
	}

	/**
	 * 查询
	 */
	public ServicePartner query(int id) {
		return queryForObject("select * from t_serverurl where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ServicePartner> queryList() {
		return queryForList("select * from t_serverurl", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_serverurl where id=?", HANDLER, id);
	}
}