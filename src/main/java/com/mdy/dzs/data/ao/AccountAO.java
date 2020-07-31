package com.mdy.dzs.data.ao;

import java.util.List;

import com.mdy.dzs.data.domain.account.Account;
import com.mdy.dzs.data.domain.account.WhiteAccount;
import com.mdy.dzs.data.domain.account.IOSDeviceInfo;

/**
 * 账号
 * 
 * @author 房曈
 *
 */
public class AccountAO extends BaseAO {
	//
	/**
	 * 查询
	 */
	public Account queryByAccount(String account) {
		return accountDAO().queryByAccount(account);
	}

	/**
	 * 添加
	 * 
	 * @param Account
	 */
	public void add(Account a) {
		accountDAO().add(a);
	}

	/**
	 * 更新
	 * 
	 * @param Account
	 */
	public void login(Account a, IOSDeviceInfo device) {
		accountDAO().update(a);
		accountDAO().addLoginLog(a, device);
	}

	public List<WhiteAccount> queryWhiteList() {
		return accountDAO().queryWhiteList();
	}
}