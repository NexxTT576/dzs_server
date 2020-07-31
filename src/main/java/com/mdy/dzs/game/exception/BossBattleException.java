package com.mdy.dzs.game.exception;


public class BossBattleException extends BaseException {
	/**尚未复活……*/
	public static final ExceptionEntry EXCE_BOSS_NOT_ALIVE = createExceptionEntry(
			1400, "not alive");
	/**鼓舞成功，伤害加成增加5%*/
	public static final ExceptionEntry EXCE_BOSS_GUWU_SUCCESS = createExceptionEntry(
			1401, "guwu success");
	/**鼓舞失败*/
	public static final ExceptionEntry EXCE_BOSS_GUWU_FAIL = createExceptionEntry(
			1402, "guwu failed");	
	/**鼓舞已经达到上限，无法继续鼓舞*/
	public static final ExceptionEntry EXCE_BOSS_GUWU_LIMIT = createExceptionEntry(
			1403, "guwu reach limit");
	/**鼓舞倒计时中……*/
	public static final ExceptionEntry EXCE_BOSS_GUWU_COUNT = createExceptionEntry(
			1404, "guwu count down");
	/**已经复活，请继续战斗吧*/
	public static final ExceptionEntry EXCE_BOSS_ALIVED = createExceptionEntry(
			1405, "alived");
	/**自动战斗功能VIP8可开启*/
	public static final ExceptionEntry EXCE_BOSS_AUTOFAIT_CONTROLL = createExceptionEntry(
			1406, "auto fight controll");
	/**您的银两不足*/
	public static final ExceptionEntry EXCE_BOSS_MONEY_NOTENOUGH = createExceptionEntry(
			1407, "money not enough");
}
