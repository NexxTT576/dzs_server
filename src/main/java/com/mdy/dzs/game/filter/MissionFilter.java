package com.mdy.dzs.game.filter;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务过滤器
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年12月19日  下午2:11:10
 */
public class MissionFilter extends Filter implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	/**所属人物*/
	private Integer roleId;
	/**所属人物名称*/
	private String roleName;
	/**开始时间*/
	private Date startTime;
	/**结束时间*/
	private Date endTime;
	/**完成时间*/
	private Date finishTime;
	/**状态*/
	private Integer status;
	/** 任务定义ID*/
	private Integer missionDefineId;
	/**类型*/
	private Integer missionCategoryId;
	/**属性*/
	private Integer missionProperty;
	/**任务完成步骤*/
	private Integer step;
	/**次数*/
	private Integer missionDetail;
	/**类型*/
	private Integer missionCategory;
	/**
	 * 任务级别
	 */
	private Integer level;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getMissionDefineId() {
		return missionDefineId;
	}
	public void setMissionDefineId(Integer missionDefineId) {
		this.missionDefineId = missionDefineId;
	}
	public Integer getMissionCategoryId() {
		return missionCategoryId;
	}
	public void setMissionCategoryId(Integer missionCategoryId) {
		this.missionCategoryId = missionCategoryId;
	}
	public Integer getMissionProperty() {
		return missionProperty;
	}
	public void setMissionProperty(Integer missionProperty) {
		this.missionProperty = missionProperty;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public Integer getMissionDetail() {
		return missionDetail;
	}
	public void setMissionDetail(Integer missionDetail) {
		this.missionDetail = missionDetail;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getMissionCategory() {
		return missionCategory;
	}
	public void setMissionCategory(Integer missionCategory) {
		this.missionCategory = missionCategory;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
