package com.mdy.dzs.game.exception;


public class RoleYuanException extends BaseException {
	/**精元不存在*/
	public final static ExceptionEntry EXCE_ROLEYUAN_NOT_EXIST = createExceptionEntry(
			2300000, "yuan not exist");
	/**精元已经达到最大等级*/
	public final static ExceptionEntry EXCE_ROLEYUAN_LEVEL_MAX = createExceptionEntry(
			2300001, "reach max Level");
	/**已经达到某个等级*/
	public final static ExceptionEntry EXCE_ROLEYUAN_REACH_LEVEL = createExceptionEntry(
			2300002, "reach role Level {0}");
	/**ids 错误*/
	public final static ExceptionEntry EXCE_ROLEYUAN_IDS_ERROR = createExceptionEntry(
			2300003, "ids error 2");
	/**聚元等级达到最高级*/
	public final static ExceptionEntry EXCE_ROLEYUAN_聚元_LEVEL_ERROR = createExceptionEntry(
			2300004, "curCollLv is full");
	/**精元角色id错误*/
	public final static ExceptionEntry EXCE_ROLEYUAN_ROLEID_ERROR = createExceptionEntry(
			2300005, "yuan {0} not is {1}");
	
}
