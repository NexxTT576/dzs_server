package com.mdy.dzs.game.exception;


/**
 * 任务异常
 * @author fangtong
 *
 */
public class MissionException extends BaseException{

	
	/**
	 *1200001 任务定义不存在 
	 */
	public final static ExceptionEntry EXCE_CAN_NOT_FOUND_TASK_DEFINE = createExceptionEntry(
			1200001, "not found missionDefine.missionId:{0}");
	/**
	 * 1200002 任务还没有完成
	 */
	public final static ExceptionEntry EXCE_MISSION_NOT_FINISH = createExceptionEntry(
			1200002, "roleid:{0} , missionDefineId:{1} not finish.");
	/**
	 *1200003 奖励已领取 
	 */
	public final static ExceptionEntry EXCE_MISSION_IS_REWARDED = createExceptionEntry(
			1200003, "Mission is get reward,roleid is {0},mission define id is {1}");

	/**
	 *1200004 添加任务需要等级不足
	 */
	public final static ExceptionEntry EXCE_MISSION_LEVEL_ERROR = createExceptionEntry(
			1200004,"mission level error.need level:${0}. role grade:${1}");
	
	/**
	 *1200005 没有对应id积分奖励
	 */
	public final static ExceptionEntry EXCE_MISSION_NO_REWARDED = createExceptionEntry(
			1200005, "Mission is no reward,id is {0}");



}
