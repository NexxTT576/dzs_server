package com.mdy.dzs.game.exception;


public class RoleEquipException extends BaseException {

	public static final ExceptionEntry EXCE_EQUIP_NOT_POLISH = createExceptionEntry(
			500000, "item not allowed");
	public static final ExceptionEntry EXCE_EQUIP_PROPS_MAX = createExceptionEntry(
			500001, "all props reach limit");
	public static final ExceptionEntry EXCE_EQUIP_PROPS_CNT_ERROR = createExceptionEntry(
			500002, "error para");
	public static final ExceptionEntry EXCE_EQUIP_PROPS_TYPE_ERROR = createExceptionEntry(
			500003, "type error");
	public static final ExceptionEntry EXCE_EQUIP_SELL_NULL = createExceptionEntry(
			500004, "ids not exist2");
	public static final ExceptionEntry EXCE_EQUIP_LEVEL_MAX = createExceptionEntry(
			500005, "reach max Level");
	public static final ExceptionEntry EXCE_EQUIP_NOT_SELF = createExceptionEntry(
			500006, "equip not self");
	public static final ExceptionEntry EXCE_EQUIP_NOT_INLINEUP = createExceptionEntry(
			500007, "equip not in lineup");
	/**等级为0 不能重生*/
	public static final ExceptionEntry EXCE_EQUIP_NOT_REBORN = createExceptionEntry(
			500015, "equip is not reborn");
}
