package com.mdy.dzs.game.exception;


public class PacketException extends BaseException {

	public static final ExceptionEntry EXCE_TYPE_NOT_ITEM = createExceptionEntry(
			300000, "type {0} not is item");
	public static final ExceptionEntry EXCE_ITEM_NOT_ENOUGH = createExceptionEntry(
			300001, "item {0} not enough num {1}");
	public static final ExceptionEntry EXCE_CARD_NOT_ENOUGH = createExceptionEntry(
			300002, "card {0} not enough num {1}");
	public static final ExceptionEntry EXCE_YUAN_NOT_ENOUGH = createExceptionEntry(
			300003, "yuan {0} not enough num {1}");
	public static final ExceptionEntry EXCE_EQUIP_NOT_ENOUGH = createExceptionEntry(
			300004, "equip {0} not enough num {1}");
	public static final ExceptionEntry EXCE_GONG_NOT_ENOUGH = createExceptionEntry(
			300005, "gong {0} not enough num {1}");
	
	public static final ExceptionEntry EXCE_NOT_EXTEND = createExceptionEntry(
			300006, "can\'t extend bag {0} ");
	public static final ExceptionEntry EXCE_ITEM_NOT_SELL = createExceptionEntry(
			300007, "item {0} not sell num");
	public static final ExceptionEntry EXCE_PACKET_FULL = createExceptionEntry(
			300008, "packet full");
	
	public static final ExceptionEntry EXCE_EQUIP_PACKET_FULL = createExceptionEntry(
			300022, "equip packet full");
	public static final ExceptionEntry EXCE_GONG_PACKET_FULL = createExceptionEntry(
			300023, "gong packet full");
	public static final ExceptionEntry EXCE_YUAN_PACKET_FULL = createExceptionEntry(
			300024, "yuan packet full");
	public static final ExceptionEntry EXCE_ITEM_PACKET_FULL = createExceptionEntry(
			300025, "item packet full");
	public static final ExceptionEntry EXCE_CARD_PACKET_FULL = createExceptionEntry(
			300026, "card packet full");
	
}
