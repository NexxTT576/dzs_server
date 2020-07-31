package com.mdy.dzs.game.domain.dart;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 押镖表结构模型
 * @author 白雪林
 *
 */
public class RoleDart implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;

	//押镖状态 
	public static final int DART_STATE_END   		= 1;	//押镖结束
	public static final int DART_STATE_DARTING 		= 2;	//压镖中 
	public static final int DART_STATE_FINISH 		= 3;	//押镖完成
	
	//镖车品质
	public static final int DART_CAR_QUALITY_GREEN  = 1;	//绿色
	public static final int DART_CAR_QUALITY_BLUE   = 2;	//蓝色
	public static final int DART_CAR_QUALITY_PURPLE = 3;	//紫色
	public static final int DART_CAR_QUALITY_GOLDEN = 4;	//金色
	
	//镖车刷新类型
	public static final int DART_CHOICE_TYPE_NORMAL = 1;	//普通
	public static final int DART_CHOICE_TYPE_CALL   = 2;	//召唤
	
	

	/**角色ID*/
	private int roleId;
	/**当前状态*/
	private int curState;
	/**镖车刷新次数*/
	private int dartRefreshCnt;
	/**今日可押镖次数*/
	private int todayDartNum;
	/**今日可劫镖次数*/
	private int todayRobNum;
	/**押镖开始时间*/
	private Date startTime;
	/**押镖结束时间*/
	private Date endTime;
	/**其他玩家账号*/
	private List<Integer> otherRoleList;
	/**创建时间*/
	private Date createTime;
	/**刷新时间*/
	private Date updateTime;
	/**下次刷新其他镖车时间*/
	private Date nextRefreshTime;
	/**被劫镖次数*/
	private int beRobNum;
	/**镖车品质*/
	private int dartCarQuality;
	/**刷新时间*/
	private Date dayRefreshTime;
	/**展示各区间显示镖车数量*/
	private Map<String, List<Integer>> showMap;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getDartRefreshCnt(){
		return this.dartRefreshCnt;
	}
	public void setDartRefreshCnt(int dartRefreshCnt){
		this.dartRefreshCnt=dartRefreshCnt;
	}
	public int getTodayDartNum(){
		return this.todayDartNum;
	}
	public void setTodayDartNum(int todayDartNum){
		this.todayDartNum=todayDartNum;
	}
	public int getTodayRobNum(){
		return this.todayRobNum;
	}
	public void setTodayRobNum(int todayRobNum){
		this.todayRobNum=todayRobNum;
	}
	public Date getStartTime(){
		return this.startTime;
	}
	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}
	public Date getEndTime(){
		return this.endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}
	public List<Integer> getOtherRoleList(){
		return this.otherRoleList;
	}
	public void setOtherRoleList(List<Integer> otherRoleList){
		this.otherRoleList=otherRoleList;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public int getCurState() {
		return curState;
	}
	public void setCurState(int curState) {
		this.curState = curState;
	}
	public Date getNextRefreshTime() {
		return nextRefreshTime;
	}
	public void setNextRefreshTime(Date nextRefreshTime) {
		this.nextRefreshTime = nextRefreshTime;
	}
	public int getBeRobNum() {
		return beRobNum;
	}
	public void setBeRobNum(int beRobNum) {
		this.beRobNum = beRobNum;
	}
	public int getDartCarQuality() {
		return dartCarQuality;
	}
	public void setDartCarQuality(int dartCarQuality) {
		this.dartCarQuality = dartCarQuality;
	}
	public Date getDayRefreshTime() {
		return dayRefreshTime;
	}
	public void setDayRefreshTime(Date dayRefreshTime) {
		this.dayRefreshTime = dayRefreshTime;
	}
	public Map<String, List<Integer>> getShowMap() {
		return showMap;
	}
	public void setShowMap(Map<String, List<Integer>> showMap) {
		this.showMap = showMap;
	}

}