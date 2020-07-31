package com.mdy.dzs.game.exception;


public class ChatException extends BaseException {
	/**类型不匹配*/
	public static final ExceptionEntry EXCE_CHAT_TYPE_ERROR = createExceptionEntry(
			1700001, "t equal 1,2,3,4");
}
