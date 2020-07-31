package com.mdy.dzs.data.action;

import java.util.List;

import com.mdy.dzs.data.domain.account.Account;
import com.mdy.dzs.data.domain.account.WhiteAccount;
import com.mdy.dzs.data.domain.account.IOSDeviceInfo;
import com.mdy.sharp.container.biz.BizException;

/**
 * 帐号Action
 * 
 * @author hairry.zhou 2011-5-24
 */
public interface AccountAction {

	/**
	 * 
	 * @param accountId
	 * @return
	 */
	Account queryAccount(String openId);

	/**
	 * 
	 * @param accountId
	 * @return
	 * @throws BizException
	 */
	Account queryExistedAccount(String openId) throws BizException;

	/**
	 * 登录
	 * 
	 * @param tubi
	 * @param pf
	 * @param userIp
	 * @param serverId
	 * @return
	 * @throws BizException
	 */
	void login(String acc, int pf, String uac, String lac, IOSDeviceInfo device, String userIp) throws BizException;

	/**
	 * 查询账号白名单列表
	 * 
	 * @return
	 */
	List<WhiteAccount> queryAccountWhiteList();
}
