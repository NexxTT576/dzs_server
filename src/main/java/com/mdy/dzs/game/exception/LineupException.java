package com.mdy.dzs.game.exception;

public class LineupException extends BaseException {

	public static final ExceptionEntry EXCE_SUPPOS_ERROR = createExceptionEntry(
			600001, "subpos error {0}");
	public static final ExceptionEntry EXCE_POS_ERROR = createExceptionEntry(
			600002, "pos error {0}");
	public static final ExceptionEntry EXCE_SYSTEMLIMIT_ERROR = createExceptionEntry(
			600003, "pos reach limit");
	public static final ExceptionEntry EXCE_LINEUP_CARD_NOT_DOWN = createExceptionEntry(
			600004, "card can\'t disboard");
	public static final ExceptionEntry EXCE_LINEUP_CARD_SAME = createExceptionEntry(
			600005, "same card");
	public static final ExceptionEntry EXCE_LINEUP_CHANGEID_ERROR = createExceptionEntry(
			600006, "id error");
	public static final ExceptionEntry EXCE_LINEUP_YUAN_NOT_SAME = createExceptionEntry(
			600007, "new item.variety error");
	public static final ExceptionEntry EXCE_LINEUP_NO_EQUIP_CHANGE = createExceptionEntry(
			600008, "no better equip");
	public static final ExceptionEntry EXCE_LINEUP_NO_YUAN_CHANGE = createExceptionEntry(
			600009, "no better yuan");
}
