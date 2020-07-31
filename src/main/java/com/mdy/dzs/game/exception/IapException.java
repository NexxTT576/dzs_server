package com.mdy.dzs.game.exception;


public class IapException extends BaseException {
	/**vip礼包已经领取*/
	public static final ExceptionEntry EXCE_IAP_VIP_HAVE_GOT = createExceptionEntry(
			1900001, "vip levelgift has got");
	/**未达到等级 -- 不可以领取*/
	public static final ExceptionEntry EXCE_IAP_NOT_ALLOW= createExceptionEntry(
			1900002, "not allow");
	/**vip等级不足*/
	public static final ExceptionEntry EXCE_IAP_VIP_LEVEL_LIMIT= createExceptionEntry(
			1900003, "vip level not allow");
	/**重复的请求*/
	public static final ExceptionEntry EXCE_IAP_VIP_REPEAT_REQ= createExceptionEntry(
			1900004, "repeat req");
	
}
