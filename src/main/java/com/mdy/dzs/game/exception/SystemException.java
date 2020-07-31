package com.mdy.dzs.game.exception;


public class SystemException extends BaseException {

	public static final ExceptionEntry EXCE_SYSTEM_ERROR = createExceptionEntry(
			-1, "{0}");
	public static final ExceptionEntry EXCE_PARAM_ERROR = createExceptionEntry(
			-2, "{0}");
	public static final ExceptionEntry EXCE_SYSTEM_ACTION_LIMIT = createExceptionEntry(
			-3, "{0}");
}
