package com.mdy.dzs.game.exception;



public class RoleCardException extends BaseException {
	public static final ExceptionEntry EXCE_CARD_NOT_EXIST = createExceptionEntry(
			200000, "card id {0} not exist");
	public static final ExceptionEntry EXCE_CARD_NOT_FOUND = createExceptionEntry(
			200001, "card resid not exist");
	public static final ExceptionEntry EXCE_CARD_NOT_YOUR = createExceptionEntry(
			200002, "card {0} not your {1} card");
	public static final ExceptionEntry EXCE_CARD_IN_LINEUP = createExceptionEntry(
			200003, "card {0} in lineup");
	public static final ExceptionEntry EXCE_CARD_NOTALLOW_SELF = createExceptionEntry(
			200004, "card {0} now allow self");
	public static final ExceptionEntry EXCE_CARD_NOTALLOW_MAIN = createExceptionEntry(
			200005, "main card not allow");
	public static final ExceptionEntry EXCE_CARD_CLSUP_NOTALLOW = createExceptionEntry(
			200006, "card not allow clsup");
	public static final ExceptionEntry EXCE_CARD_CLSUP_MAX = createExceptionEntry(
			200007, "card max clsup");
	public static final ExceptionEntry EXCE_CARD_CLSUP_NOITEM = createExceptionEntry(
			200008, "card clsup noitem");
	public static final ExceptionEntry EXCE_CARD_CLSUP_NOGONG = createExceptionEntry(
			200009, "card clsup noitem");
	public static final ExceptionEntry EXCE_CARD_CLSUP_NOCARD = createExceptionEntry(
			200010, "card clsup noitem");
	
	public static final ExceptionEntry EXCE_CARD_SHENUP_MAXLV = createExceptionEntry(
			200011, "card shen maxlv");
	public static final ExceptionEntry EXCE_CARD_SHENUP_NOTCLS = createExceptionEntry(
			200012, "card shen cls not allow");
	public static final ExceptionEntry EXCE_CARD_SHENUP_NOTPT= createExceptionEntry(
			200013, "shen point not allow");
	
	public static final ExceptionEntry EXCE_CARD_SOULUP_LIMIT= createExceptionEntry(
			200014, "levelLimit error");
	
	public static final ExceptionEntry EXCE_CARD_LEVELUP_MAXLV= createExceptionEntry(
			200015, "shen point not allow");
	public static final ExceptionEntry EXCE_CARD_LEVELUP_MAXROLELV= createExceptionEntry(
			200016, "shen point not allow");
	public static final ExceptionEntry EXCE_CARD_CLSUP_LEVEL_LIMIT = createExceptionEntry(
			200017, "card clsup level limit");
	public static final ExceptionEntry EXCE_CARD_IS_LOCK = createExceptionEntry(
			200027, "{0} card is lock ");
	/**阶数不为0 不能炼化*/
	public static final ExceptionEntry EXCE_CARD_NOT_FURNACE = createExceptionEntry(
			200028, "{0} card cls not 0  ");
	/**等级为1 不能重生*/
	public static final ExceptionEntry EXCE_CARD_NOT_REBORN = createExceptionEntry(
			200029, "{0} card level is 0 ");
}
