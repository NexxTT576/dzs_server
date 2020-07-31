package com.mdy.dzs.game.exception;


public class MCardException extends BaseException {
	/**今天的已经领取 */
	public static final ExceptionEntry EXCE_MCARD_HAVE_GOT = createExceptionEntry(
			2000001, "today has got");
	/**天数不足 不能领取 */
	public static final ExceptionEntry EXCE_MCARD_CANNOT_GET = createExceptionEntry(
			2000002, "can't get");
}
