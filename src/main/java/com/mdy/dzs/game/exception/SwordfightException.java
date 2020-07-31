package com.mdy.dzs.game.exception;


public class SwordfightException extends BaseException {
	/**等级未开启*/
	public static final ExceptionEntry EXCE_SWORD_LEVEL_LIMIT = createExceptionEntry(
			1600001, "level limit: {0}");
	/**已经是最新的玩家*/
	public static final ExceptionEntry EXCE_SWORD_RESET_ERROR = createExceptionEntry(
			1600002, "reset err");
	/**可以免费重置 不必元宝重置*/
	public static final ExceptionEntry EXCE_SWORD_FREE_RESET_ERROR = createExceptionEntry(
			1600003, "please free reset");
	/**元宝重置次数已经用完*/
	public static final ExceptionEntry EXCE_SWORD_GOLD_RESET_ERROR = createExceptionEntry(
			1600004, "gold resetcnt err");
	/**元宝数错误*/
	public static final ExceptionEntry EXCE_SWORD_GOLD_NUM_ERROR = createExceptionEntry(
			1600005, "gold num err: {0}");
	/**元宝数不足*/
	public static final ExceptionEntry EXCE_SWORD_GOLD_NOT_ENOUGH = createExceptionEntry(
			1600006, "gold not enough: {0}");
	/**奖励层数错误*/
	public static final ExceptionEntry EXCE_SWORD_AWARD_FLOOR_ERROR= createExceptionEntry(
			1600007, "floor err");
	/**奖励已获取*/
	public static final ExceptionEntry EXCE_SWORD_AWARD_HAVE_GOT= createExceptionEntry(
			1600008, "have got");
	/**攻打的层数错误*/
	public static final ExceptionEntry EXCE_SWORD_FLOOR_ERROR= createExceptionEntry(
			1600009, "floor err");
	/**所有卡牌已经阵亡*/
	public static final ExceptionEntry EXCE_SWORD_CARDS_ALL_DEAD= createExceptionEntry(
			16000010, "cards all dead");
	/**卡牌不足*/
	public static final ExceptionEntry EXCE_SWORD_CARDS_NOT_ENOUGH= createExceptionEntry(
			16000011, "need friends help");
}
