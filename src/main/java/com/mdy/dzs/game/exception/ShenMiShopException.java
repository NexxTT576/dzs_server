package com.mdy.dzs.game.exception;


public class ShenMiShopException extends BaseException {
	/**等级未开启*/
	public static final ExceptionEntry EXCE_SHENMI_LEVEL_LIMIT = createExceptionEntry(
			2400001, "level limit: {0}");
	/**购买的物品不存在*/
	public static final ExceptionEntry EXCE_SHENMI_ITEM_NOT_EXIST = createExceptionEntry(
			2400002, "item not exist");
	/**兑换达到上限*/
	public static final ExceptionEntry EXCE_SHENMI_UP_LIMIT = createExceptionEntry(
			2400003, "up limit");
	/**概率错误*/
	public static final ExceptionEntry EXCE_SHENMI_PROB_ERROR = createExceptionEntry(
			2400004, "get Prob error");
}
