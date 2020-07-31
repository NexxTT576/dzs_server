package com.mdy.dzs.game.exception;


public class RoleChannelException extends BaseException {
	/**类型不存在 */
	public final static ExceptionEntry EXCE_ROLECHANNEL_T_NOT_EXIST = createExceptionEntry(
			2200001, "t not exist");
	/**类型错误 */
	public final static ExceptionEntry EXCE_ROLECHANNEL_T_ERROR = createExceptionEntry(
			2200002, "t error");
	/**等级达到上限 */
	public final static ExceptionEntry EXCE_ROLECHANNEL_LEVEL_LIMIT = createExceptionEntry(
			2200003, "level reach limit");
	/**数据不存在 */
	public final static ExceptionEntry EXCE_ROLECHANNEL_DATA_NOT_EXIST = createExceptionEntry(
			2200004, "data not exist");
	/**品质不够 */
	public final static ExceptionEntry EXCE_ROLECHANNEL_STAR_NOT_ENOUGH = createExceptionEntry(
			2200005, "star not enough");
	/**经脉不存在 */
	public final static ExceptionEntry EXCE_ROLECHANNEL_NOT_EXIST = createExceptionEntry(
			2200006, "rolechannel not exist：{0}");
}
