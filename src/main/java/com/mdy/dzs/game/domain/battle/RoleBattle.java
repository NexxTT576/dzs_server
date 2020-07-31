package com.mdy.dzs.game.domain.battle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 副本模型
 * @author 房曈
 *
 */
public class RoleBattle implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int roleId;
	/**世界id*/
	private int battleWorldID;
	/**地图id*/
	private int battleFieldID;
	/**当前最大关卡数*/
	private int battleLvID;
	/**已打剧情关卡ids*/
	private List<Integer> overMBattles;
	/***/
	private List<LvNum> battleHis;
	/**已领宝箱状态(0未领,1简单,2中等,3难度）*/
	private List<LvNum> battleBox;
	/**连击时间*/
	private Date battleNHitTime;
	/**每日战斗次数统计*/
	private List<LvNum> battleDayHitStat;
	/**每日清除累积次数*/
	private int battleDayNHitClearCnt;
	/**累积获得星星数*/
	private int battleTotalStars;
	/**每日购买次数*/
	private int battleDayBuyCnt;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;

	/**每日刷新*/
	private Date battleDayRefresh;
	
	/**星星最后更新时间*/
	private Date battleStarHasLastTime;

	public Date getBattleDayRefresh() {
		return battleDayRefresh;
	}
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getBattleWorldID(){
		return this.battleWorldID;
	}
	public void setBattleWorldID(int battleWorldID){
		this.battleWorldID=battleWorldID;
	}
	public int getBattleFieldID(){
		return this.battleFieldID;
	}
	public void setBattleFieldID(int battleFieldID){
		this.battleFieldID=battleFieldID;
	}
	public int getBattleLvID(){
		return this.battleLvID;
	}
	public void setBattleLvID(int battleLvID){
		this.battleLvID=battleLvID;
	}
	public List<LvNum> getBattleHis(){
		return this.battleHis;
	}
	public void setBattleHis(List<LvNum> battleHis){
		this.battleHis=battleHis;
	}
	public List<LvNum> getBattleBox(){
		return this.battleBox;
	}
	public void setBattleBox(List<LvNum> battleBox){
		this.battleBox=battleBox;
	}
	public Date getBattleNHitTime(){
		return this.battleNHitTime;
	}
	public void setBattleNHitTime(Date battleNHitTime){
		this.battleNHitTime=battleNHitTime;
	}
	public List<LvNum> getBattleDayHitStat(){
		return this.battleDayHitStat;
	}
	public void setBattleDayHitStat(List<LvNum> battleDayHitStat){
		this.battleDayHitStat=battleDayHitStat;
	}
	public int getBattleDayNHitClearCnt(){
		return this.battleDayNHitClearCnt;
	}
	public void setBattleDayNHitClearCnt(int battleDayNHitClearCnt){
		this.battleDayNHitClearCnt=battleDayNHitClearCnt;
	}
	public int getBattleTotalStars(){
		return this.battleTotalStars;
	}
	public void setBattleTotalStars(int battleTotalStars){
		this.battleTotalStars=battleTotalStars;
	}
	public List<Integer> getOverMBattles() {
		return overMBattles;
	}
	public void setOverMBattles(List<Integer> overMBattles) {
		this.overMBattles = overMBattles;
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
	public void setBattleDayRefresh(Date battleDayRefresh) {
		this.battleDayRefresh = battleDayRefresh;
	}
	public int getBattleDayBuyCnt() {
		return battleDayBuyCnt;
	}
	public void setBattleDayBuyCnt(int battleDayBuyCnt) {
		this.battleDayBuyCnt = battleDayBuyCnt;
	}
	public Date getBattleStarHasLastTime() {
		return battleStarHasLastTime;
	}
	public void setBattleStarHasLastTime(Date battleStarHasLastTime) {
		this.battleStarHasLastTime = battleStarHasLastTime;
	}
}