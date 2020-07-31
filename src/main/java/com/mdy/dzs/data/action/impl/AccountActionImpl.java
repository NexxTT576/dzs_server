/**
 * 
 */
package com.mdy.dzs.data.action.impl;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.ApplicationAwareAction;
import com.mdy.dzs.data.action.AccountAction;
import com.mdy.dzs.data.domain.account.Account;
import com.mdy.dzs.data.domain.account.WhiteAccount;
import com.mdy.dzs.data.domain.account.IOSDeviceInfo;
import com.mdy.dzs.data.exception.AccountException;
import com.mdy.sharp.container.biz.BizException;

/**
 * 账号
 */
public class AccountActionImpl extends ApplicationAwareAction implements AccountAction {

	@Override
	public Account queryAccount(String account) {
		return accountAO().queryByAccount(account);
	}

	@Override
	public Account queryExistedAccount(String account) throws BizException {
		return accountAO().queryByAccount(account);
	}

	@Override
	public void login(String acc, int pf, String uac, String lac, IOSDeviceInfo device, String userIp)
			throws BizException {
		Account account = accountAO().queryByAccount(acc);
		if (account == null) {
			account = new Account();
			account.setAccount(acc);
			account.setPfId(pf);
			account.setPfUid(uac);
			account.setPfAccount(lac);
			accountAO().add(account);
			account = accountAO().queryByAccount(acc);
		}
		if (account.getStatus() == Account.STATUS_FORBIDDEN) {
			throw AccountException.getException(AccountException.EXCE_STATUS_FORBIDDEN);
		}
		account.setLoginIp(userIp);
		account.setLastLoginTime(new Date());
		accountAO().login(account, device);
	}

	@Override
	public List<WhiteAccount> queryAccountWhiteList() {
		return accountAO().queryWhiteList();
	}

}
