package com.mdy.dzs.game.domain.mission;

import java.io.Serializable;
import java.util.Date;
/**
 * 任务记录
 * @author fangtong
 *
 */
public class Mission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 任务还没有开始 0
	 */
	public static final int STATUS_READY=0;
	/**
	 * 任务已经开始，没有完成  1
	 */
	public static final int STATUS_STARTED=1;
	/**
	 * 任务已经完成，没有领取奖励 2
	 */
	public static final int STATUS_FINISHED=2;
	
	/**
	 * 任务已经结束 3
	 */
	public static final int STATUS_END=3;
	/**
	 * 任务已过期
	 */
	public static final int STATUS_EXPIRE=4;
	//

	/**所属玩家*/
	private int roleId;
	/**公会id*/
	private int unionId;
	/**接取等级*/
	private int level;
	/**开始时间*/
	private Date startTime;
	/**结束时间*/
	private Date endTime;
	/**完成时间*/
	private Date finishTime;
	/**状态*/
	private int status;
	/** 任务定义ID*/
	private int missionDefineId;
	/**类型*/
	private int missionCategory;
	/**属性*/
	private int missionProperty;
	/**次数*/
	private int missionDetail;

	private int refreshDaily;
	/**
	 * 
	 */
	public Mission() {
		status=STATUS_READY;
	}

	/**
	 * @return the unionId
	 */
	public int getUnionId() {
		return unionId;
	}

	/**
	 * @param unionId the unionId to set
	 */
	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the finishTime
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime the finishTime to set
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the taskDefineId
	 */
	public int getMissionDefineId() {
		return missionDefineId;
	}

	/**
	 * @param taskDefineId the taskDefineId to set
	 */
	public void setMissionDefineId(int missionDefineId) {
		this.missionDefineId = missionDefineId;
	}

	/**
	 * @return the missionCategory
	 */
	public int getMissionCategory() {
		return missionCategory;
	}

	/**
	 * @param missionCategory the missionCategory to set
	 */
	public void setMissionCategory(int missionCategory) {
		this.missionCategory = missionCategory;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public int getMissionDetail() {
		return missionDetail;
	}

	public void setMissionDetail(int missionDetail) {
		this.missionDetail = missionDetail;
	}

	public boolean isEnd(){
		return status == STATUS_END||status==STATUS_FINISHED;
	}

	@Override
	public String toString() {
		return "Mission [roleId=" + roleId + ", endTime=" + endTime
				+ ", finishTime=" + finishTime + ", unionId=" + unionId
				+ ", missionCategory=" + missionCategory
				+ ", missionDefineId=" + missionDefineId + ", missionDetail="
				+ missionDetail + ", startTime=" + startTime + ", status="
				+ status  +"]";
	}

	public int getMissionProperty() {
		return missionProperty;
	}

	public void setMissionProperty(int missionProperty) {
		this.missionProperty = missionProperty;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRefreshDaily() {
		return refreshDaily;
	}

	public void setRefreshDaily(int refreshDaily) {
		this.refreshDaily = refreshDaily;
	}
	
}
