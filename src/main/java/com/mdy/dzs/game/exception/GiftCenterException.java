package com.mdy.dzs.game.exception;


public class GiftCenterException extends BaseException {
	/**等级不足 未开启*/
	public static final ExceptionEntry EXCE_GIFTCENTER_LEVEL_LIMIT = createExceptionEntry(
			1800001, "level limit:{0}");
	/**领取的等级错误*/
	public static final ExceptionEntry EXCE_GIFTCENTER_LEVEL_ERROR = createExceptionEntry(
			1800002, "level error:{0}");
	/**礼包已经领取*/
	public static final ExceptionEntry EXCE_GIFTCENTER_HAVE_GOT = createExceptionEntry(
			1800003, "gift have got");
	/**已经到达领取的上限*/
	public static final ExceptionEntry EXCE_GIFTCENTER_ID_MAX = createExceptionEntry(
			1800004, "id reach max");
	/**时间不足*/
	public static final ExceptionEntry EXCE_GIFTCENTER_TIME_NOT_ENOUGH = createExceptionEntry(
			1800005, "time not enough");
	/**领取的天数错误*/
	public static final ExceptionEntry EXCE_GIFTCENTER_DAY_ERROR = createExceptionEntry(
			1800006, "day err");
	
	
}
