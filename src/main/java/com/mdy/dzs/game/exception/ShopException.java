package com.mdy.dzs.game.exception;

public class ShopException extends BaseException {

	public static final ExceptionEntry EXCE_BUY_TYPE_ERROR = createExceptionEntry(
			400000, "type {0} not is {1}");
	public static final ExceptionEntry EXCE_BUY_NUM_ERROR = createExceptionEntry(
			400001, "n {0}  all {1} > limit {2}");
	public static final ExceptionEntry EXCE_BUY_COIN_ERROR = createExceptionEntry(
			400002, "coin {0} not is {1}");
	/**购买的物品不存在*/
	public static final ExceptionEntry EXCE_BUY_NOT_SELL = createExceptionEntry(
			400003, "not for sell");
}
