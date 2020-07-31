package com.mdy.dzs.game.exception;


/**
 * 
 * 700001
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月31日  下午5:30:38
 */
public class BattleException extends BaseException {

	public static final ExceptionEntry EXCE_BATTLE_FIELD_NOT_OPEN = createExceptionEntry(
			700001, "field not open");
	public static final ExceptionEntry EXCE_BATTLE_LEVEL_NOT_OPEN = createExceptionEntry(
			700002, "level not open");
	public static final ExceptionEntry EXCE_BATTLE_TYPE_NOT_OPEN = createExceptionEntry(
			700003, "type not open");
	public static final ExceptionEntry EXCE_BATTLE_TYPE_ERROR = createExceptionEntry(
			700004, "type error");
	public static final ExceptionEntry EXCE_BATTLE_MOVIEDATA_ERROR = createExceptionEntry(
			700005, "movie battle data error");
	public static final ExceptionEntry EXCE_BATTLE_BOX_AWARD_TYPE_ERROR = createExceptionEntry(
			700006, "award type error");
	public static final ExceptionEntry EXCE_BATTLE_BOX_AWARD_START_NOT_ENOUGH = createExceptionEntry(
			700007, "not enough stars");
	public static final ExceptionEntry EXCE_BATTLE_BOX_AWARD_BEGOT = createExceptionEntry(
			700008, "item has got");
	public static final ExceptionEntry EXCE_BATTLES_TIME_NOTALLOW = createExceptionEntry(
			700009, "time not allow");
	public static final ExceptionEntry EXCE_BATTLES_N_MUST_MAX = createExceptionEntry(
			700010, "not allow !max");
	public static final ExceptionEntry EXCE_BATTLES_CAN_NOT_BUY = createExceptionEntry(
			700011, "can't buy, refresh");
	public static final ExceptionEntry EXCE_BATTLE_NUM_NOTENOUTH = createExceptionEntry(
			700012, "num not enouth, refresh");
}
