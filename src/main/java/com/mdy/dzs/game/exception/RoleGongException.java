package com.mdy.dzs.game.exception;


/**
 * 内外功夺宝异常
 */
public class RoleGongException extends BaseException{

	public final static ExceptionEntry EXCE_GONG_LEVEL_NOT_IN_LINEUP = createExceptionEntry(
			1000001, "gong level cailiao error");
	public final static ExceptionEntry EXCE_GONG_LEVEL_MAX = createExceptionEntry(
			1000002, "max level");
	public final static ExceptionEntry EXCE_GONG_SELL_NULL = createExceptionEntry(
			1000003, "ids not exist2");
	public final static ExceptionEntry EXCE_GONG_SELL_NOT_IN_LINEUP = createExceptionEntry(
			1000004, "id not allow sell");
	public final static ExceptionEntry EXCE_GONG_NOT_SELF = createExceptionEntry(
			1000005, "gong {0} not is {1}");
	public final static ExceptionEntry EXCE_GONG_SYNTH_NOT_ENOUGH = createExceptionEntry(
			1000006, "con\'t snatch");
	/**正在被抢夺*/
	public final static ExceptionEntry EXCE_GONG_SYNTH_FIGHT = createExceptionEntry(
			1000007, "is snatching");
	/**抢夺道具已有*/
	public final static ExceptionEntry EXCE_GONG_SYNTH_SELF_HAVE = createExceptionEntry(
			1000008, "self have");
	/**合成对应的碎片内外功数量都为0*/
	public final static ExceptionEntry EXCE_GONG_SYNTH_ITEMS_NULL = createExceptionEntry(
			1000009, "items null");
	public final static ExceptionEntry EXCE_GONG_REFINE_NOTALLOW = createExceptionEntry(
			1000010, "gong {0}:{1} refine not allow");
	public final static ExceptionEntry EXCE_GONG_REFINE_MAX = createExceptionEntry(
			1000011, "gong {0}:{1} refine max");
	/**耐力不足抢夺10次*/
	public final static ExceptionEntry EXCE_GONG_RESIS_NOT_ENOUGH = createExceptionEntry(
			1000012, "resisVal not enough");
}
