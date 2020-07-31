package com.mdy.dzs.game.exception;



public class ChallengeBattleException extends BaseException {

	public static final ExceptionEntry EXCE_ELITE_BATTLE_NOT_EXIST = createExceptionEntry(
			800001, "elite battle not exist: {0}");
	public static final ExceptionEntry EXCE_ELITE_BATTLE_ERROR_ID = createExceptionEntry(
			800002, "error id {0}");
	public static final ExceptionEntry EXCE_ELITE_BATTLE_ERROR_NPC = createExceptionEntry(
			800003, "not allow npc id {0}");
	public static final ExceptionEntry EXCE_ELITE_BATTLE_CNT_NOTENOUGH = createExceptionEntry(
			800004, "not enough cnt");
	public static final ExceptionEntry EXCE_ACTIVITY_BATTLE_NOT_EXIST = createExceptionEntry(
			800005, "activity battle not exist: {0}");
	public static final ExceptionEntry EXCE_ACTIVITY_BATTLE_ERROR_NPC = createExceptionEntry(
			800006, "now allow fight npcInd: {0}");
	public static final ExceptionEntry EXCE_ACTIVITY_BATTLE_CNT_NOTENOUGH = createExceptionEntry(
			800007, "not enough cnt");
	public static final ExceptionEntry EXCE_ACTIVITY_BATTLE_NOT_BUY_CNT = createExceptionEntry(
			800008, "activity not buy");
	public static final ExceptionEntry EXCE_ELITE_BATTLE_NOT_BUY_CNT = createExceptionEntry(
			800009, "elite not buy");
	public static final ExceptionEntry EXCE_ACTIVITY_CAN_NOT_BUY = createExceptionEntry(
			800014, "can not buy");
	public static final ExceptionEntry EXCE_ACTIVITY_LEVEL_NOT_ENOUGH = createExceptionEntry(
			800015, "level not enough");
	public static final ExceptionEntry EXCE_ACTIVITY_NO_PERSON = createExceptionEntry(
			800016, "no person");
}
