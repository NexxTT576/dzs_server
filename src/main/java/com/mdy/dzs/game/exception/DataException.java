package com.mdy.dzs.game.exception;

public class DataException extends BaseException {

	public static final ExceptionEntry EXCE_DATAS_IS_NOT_EXIST = createExceptionEntry(
			10000, "data {0} is not exist");
	public static final ExceptionEntry EXCE_DATA_IS_NOT_EXIST = createExceptionEntry(
			10001, "data {0}:{1} is not exist");
	
	
}
