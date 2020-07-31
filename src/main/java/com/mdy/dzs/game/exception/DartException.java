package com.mdy.dzs.game.exception;

public class DartException extends BaseException {
	/**刷新其他镖车时间未到*/
	public static final ExceptionEntry EXCE_DART_REFRESH_OTHERCAR_TIME_ERROE = createExceptionEntry(
			3400001, "refresh other car time error");
	/**没有压镖中，不能加速押镖*/
	public static final ExceptionEntry EXCE_DART_CAN_NOT_SPEED_UP = createExceptionEntry(
			3400002, "can't speedup");
	/**正在压镖中*/
	public static final ExceptionEntry EXCE_DART_CAN_NOT_RESTART = createExceptionEntry(
			3400003, "can't restart");
	/**自己劫镖数达上限*/
	public static final ExceptionEntry EXCE_DART_SELF_ROB_CONTROLL = createExceptionEntry(
			3400004, "self rob num controll");
	/**对方被劫镖数达上限*/
	public static final ExceptionEntry EXCE_DART_OPPS_ROB_CONTROLL = createExceptionEntry(
			3400005, "other be rob num controll");
	/**自己押镖数达上限*/
	public static final ExceptionEntry EXCE_DART_NUM_CONTROLL = createExceptionEntry(
			3400006, "dart num controll");
	/**抢的镖车已经押送完毕*/
	public static final ExceptionEntry EXCE_DART_END = createExceptionEntry(
			3400007, "dart num controll");
	/**自己镖车未押送完毕，不能领奖*/
	public static final ExceptionEntry EXCE_DART_NOT_END = createExceptionEntry(
			3400008, "can't get award,not end");
	/**等级限制*/
	public static final ExceptionEntry EXCE_DART_LEVEL_CONTROL = createExceptionEntry(
			3400009, "level control");
	/**已经是金品质，无需刷新召唤*/
	public static final ExceptionEntry EXCE_DART_CAR_TOP = createExceptionEntry(
			3400010, "dart car reach top");
}
