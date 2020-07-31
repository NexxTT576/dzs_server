package com.mdy.dzs.data.dao;

import java.util.List;

import com.mdy.dzs.data.domain.account.Account;
import com.mdy.dzs.data.domain.account.WhiteAccount;
import com.mdy.dzs.data.domain.account.IOSDeviceInfo;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 账号DAO
 * 
 * @author 房曈
 *
 */
public class AccountDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<Account> HANDLER = new ResultSetRowHandler<Account>() {
		@Override
		public Account handleRow(ResultSetRow row) throws Exception {
			Account a = new Account();
			a.setId(row.getInt("id"));
			a.setAccount(row.getString("account"));
			a.setPfUid(row.getString("pf_uid"));
			a.setPfAccount(row.getString("pf_account"));
			a.setStatus(row.getInt("status"));
			a.setLastLoginTime(row.getTimestamp("last_login_time"));
			a.setLoginIp(row.getString("login_ip"));
			a.setPfId(row.getInt("pf_id"));
			a.setCreateTime(row.getTimestamp("create_time"));
			a.setUpdateTime(row.getTimestamp("update_time"));
			return a;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Account
	 */
	public void add(Account a) {
		String sql = "insert into t_account(" + "account," + "pf_id," + "pf_uid," + "pf_account," + "status,"
				+ "last_login_time," + "login_ip," + "create_time," + "update_time"
				+ ")values(?,?,?,?,?,?,?,now(),now())";
		execute(sql, a.getAccount(), a.getPfId(), a.getPfUid(), a.getPfAccount(), a.getStatus(), a.getLastLoginTime(),
				a.getLoginIp());
	}

	/**
	 * 更新
	 * 
	 * @param Account
	 */
	public void update(Account a) {
		String sql = "update t_account set " + " update_time=now(), " + " status=?," + " last_login_time=?,"
				+ " login_ip=? " + " where id=?";
		executeUpdate(sql, a.getStatus(), a.getLastLoginTime(), a.getLoginIp(), a.getId());
	}

	/**
	 * 查询
	 */
	public Account queryByAccount(String account) {
		return queryForObject("select * from t_account where account=?", HANDLER, account);
	}

	/**
	 * 查询列表
	 */
	public List<Account> queryList() {
		return queryForList("select * from t_account", HANDLER);
	}

	public void addLoginLog(Account account, IOSDeviceInfo device) {
		String table = "t_account_login_log_" + (account.getId() % 10);
		String sql = "insert into " + table + "("
				+ "account,pf_id,device_type,device_version,device_uuid,login_ip,login_time)values(?,?,?,?,?,?,now())";
		execute(sql, account.getAccount(), account.getPfId(), device == null ? "" : device.getDeviceType(),
				device == null ? "" : device.getDeviceVersion(), device == null ? "" : device.getDeviceUUID(),
				account.getLoginIp());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	private static ResultSetRowHandler<WhiteAccount> WHITE_LIST_HANDLER = new ResultSetRowHandler<WhiteAccount>() {
		@Override
		public WhiteAccount handleRow(ResultSetRow row) throws Exception {
			WhiteAccount awl = new WhiteAccount();
			awl.setAccount(row.getString("account"));
			awl.setDesc(row.getString("desc"));
			awl.setCreateTime(row.getTimestamp("create_time"));
			return awl;
		}
	};

	/**
	 * 查询列表
	 */
	public List<WhiteAccount> queryWhiteList() {
		return queryForList("select * from t_account_white_list", WHITE_LIST_HANDLER);
	}
}