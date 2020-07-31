package com.mdy.dzs.game.exception;

public class MailException extends BaseException {
	/**无更多邮件*/
	public static final ExceptionEntry EXCE_MAIL_NO_MORE = createExceptionEntry(
			2600001, "no more mail");
	/**等级未开启*/
	public static final ExceptionEntry EXCE_MAIL_LEVEL_LIMIT = createExceptionEntry(
			2600002, "level limit");
}
