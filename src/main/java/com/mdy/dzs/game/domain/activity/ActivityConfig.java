package com.mdy.dzs.game.domain.activity;

import java.io.Serializable;
import java.util.Date;


/**
 * 活动配置模型
 * @author 房曈
 *
 */
public class ActivityConfig implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;

	public static final int TIME_TYPE_永久		= 1;
	public static final int TIME_TYPE_每日时段	= 2;	
	public static final int TIME_TYPE_日期时段	= 3;
	public static final int TIME_TYPE_精确日期时段	= 4;
	
	public static final int ACTIVITY_首冲		= 1;
	public static final int ACTIVITY_vip福利		= 2;
	public static final int ACTIVITY_月卡		= 3;
	public static final int ACTIVITY_vip升级礼包	= 4;
	public static final int ACTIVITY_客栈		= 5;
	public static final int ACTIVITY_酒剑仙		= 6;
	public static final int ACTIVITY_神秘商店		= 7;
	public static final int ACTIVITY_登录累积奖励	= 8;
	public static final int ACTIVITY_月签		= 9;
	public static final int ACTIVITY_投资计划		= 10;
	public static final int ACTIVITY_LIMITCARD	= 11;//限时豪杰
	public static final int ACTIVITY_限时兑换		= 13;//限时兑换
	public static final int ACTIVITY_福利活动		= 12;//福利活动
	public static final int ACTIVITY_皇宫探宝		= 14;
	public static final int ACTIVITY_MAZE		= 15;
	/**限时商店*/
	public static final int ACTIVITY_LIMITSHOP  = 16;
	public static final int ACTIVITY_充值回馈		= 17;//充值回馈
	

	/**活动id*/
	private int activityId;
	/**活动配置*/
	private String activityConfig;
	/**是否开放*/
	private int open;
	/**开放时间类型 1永久 2每日时段*/
	private int timeType;
	/**时间参数*/
	private String timeParam;
	/**更新时间*/
	private Date updateTime;

	public int getActivityId(){
		return this.activityId;
	}
	public void setActivityId(int activityId){
		this.activityId=activityId;
	}
	public String getActivityConfig(){
		return this.activityConfig;
	}
	public void setActivityConfig(String string){
		this.activityConfig=string;
	}
	public int getOpen(){
		return this.open;
	}
	public void setOpen(int open){
		this.open=open;
	}
	public int getTimeType(){
		return this.timeType;
	}
	public void setTimeType(int timeType){
		this.timeType=timeType;
	}
	public String getTimeParam(){
		return this.timeParam;
	}
	public void setTimeParam(String timeParam){
		this.timeParam=timeParam;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
}