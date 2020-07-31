package com.mdy.dzs.game.exception;

public class RoleException extends BaseException {

	public static final ExceptionEntry EXCE_ACCOUNT_IS_EXIST = createExceptionEntry(
			100000, "acc exist");
	public static final ExceptionEntry EXCE_NAME_IS_EXIST = createExceptionEntry(
			100001, "name exist");
	public static final ExceptionEntry EXCE_RID_ERROR = createExceptionEntry(
			100002, "error rid");
	
	public static final ExceptionEntry EXCE_ACCOUNT_NOT_EXIST = createExceptionEntry(
			100003, "acc not exist");
	public static final ExceptionEntry EXCE_GOLD_NOT_ENOUGH = createExceptionEntry(
			100004, "not enough money {0} < {1}");
	public static final ExceptionEntry EXCE_SILVER_NOT_ENOUGH = createExceptionEntry(
			100005, "not enough silver {0} < {1}");
	public static final ExceptionEntry EXCE_PHYVAL_NOT_ENOUGH = createExceptionEntry(
			100007, "not enough phyval {0} < {1}");
	public static final ExceptionEntry EXCE_RESISVAL_NOT_ENOUGH = createExceptionEntry(
			100006, "not enough resisVal {0} < {1}");
	public static final ExceptionEntry EXCE_SOUL_NOT_ENOUGH = createExceptionEntry(
			100008, "not enough soul {0} < {1}");
	public static final ExceptionEntry EXCE_POPUAL_NOT_ENOUGH = createExceptionEntry(
			100009, "not enough popual {0} < {1}");
	public static final ExceptionEntry EXCE_HUNYU_NOT_ENOUGH = createExceptionEntry(
			100010, "not enough hunyu {0} < {1}");
	public static final ExceptionEntry EXCE_REPEAT_LOGIN = createExceptionEntry(
			100011, "repeat login");
	public static final ExceptionEntry EXCE_ACCOUNT_FORMAT_NOT_MATCH = createExceptionEntry(
			100012, "account format not match");
	public static final ExceptionEntry EXCE_NAME_NOT_HAVE_EMOJI = createExceptionEntry(
			100013, "name not have emoji");
	public static final ExceptionEntry EXCE_VERSION_ERROR = createExceptionEntry(
			100014, "version error");
	public static final ExceptionEntry EXCE_LINGSHI_NOT_ENOUGH = createExceptionEntry(
			100015, "not enough lingShi {0} < {1}");
	public static final ExceptionEntry EXCE_CHARM_NOT_ENOUGH = createExceptionEntry(
			100016, "not enough charm {0} < {1}");
	public static final ExceptionEntry EXCE_HONOR_NOT_ENOUGH = createExceptionEntry(
			100017, "not enough honor {0} < {1}");
	
}
