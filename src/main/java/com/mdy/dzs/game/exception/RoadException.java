package com.mdy.dzs.game.exception;


public class RoadException extends BaseException {
	/**card Id error*/
	public static final ExceptionEntry EXCE_ROAD_CARDID_ERROR= createExceptionEntry(
			2100001, "today has got");
	/**达到最大等级*/
	public static final ExceptionEntry EXCE_ROAD_LEVEL_FULL= createExceptionEntry(
			2100002, "level full");
	/**没有可以领取的礼包*/
	public static final ExceptionEntry EXCE_ROAD_NO_GIFT= createExceptionEntry(
			2100003, "not have gift");
}
