package com.mdy.dzs.game.exception;


/**
 * 竞技场异常
 */
public class ArenaException extends BaseException{

	
	public final static ExceptionEntry EXCE_ARENA_NOT_CHALLENGE_SELF = createExceptionEntry(
			900001, "arena not challenge self");
	public final static ExceptionEntry EXCE_ARENA_SHOP_LIMIT_LEVEL = createExceptionEntry(
			900002, "level control");
	public final static ExceptionEntry EXCE_ARENA_SHOP_LIMIT_NUM = createExceptionEntry(
			900003, "num exceed upper limit");
	
}
