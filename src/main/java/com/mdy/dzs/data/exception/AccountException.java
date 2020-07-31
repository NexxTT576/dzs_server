package com.mdy.dzs.data.exception;

/**
 *
 */
public class AccountException extends BaseException{
	
	/**
	 * 帐户被锁定
	 */
	public static final ExceptionEntry EXCE_STATUS_FORBIDDEN = createExceptionEntry(
			110000,	"user is forbidden to login.");
	
	/**
	 * 帐户被禁言
	 */
	public static final ExceptionEntry EXCE_STATUS_DISABLE_SENDMSG = createExceptionEntry(
			110001,	"user is forbidden to send msg.open id is {0}");
	
	/**
	 * 账号登陆的服务器不存在
	 */
	public static final ExceptionEntry EXCE_SERVER_NOT_EXISTS = createExceptionEntry(
			100002,	"server not existed.serverId={0}");
}
	