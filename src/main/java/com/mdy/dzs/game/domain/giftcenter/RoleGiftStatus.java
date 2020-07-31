package com.mdy.dzs.game.domain.giftcenter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 礼包状态模型
 * @author 房曈
 *
 */
public class RoleGiftStatus implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**角色id*/
	private int roleId;
	/**已领取的等级礼包*/
	private List<Integer> giftLvGot;
	/**在线奖励已领取id*/
	private int giftOnlineGot;
	/**在线奖励领取时间点*/
	private Date giftOnlineTime;
	/**在线累积时间*/
	private int giftOnlineTimeAcc;
	/**签到计数*/
	private int giftSignCnt;
	/**签到时间*/
	private Date giftSignTime;
	/**连续登陆天数*/
	private int giftLogindayCnt;
	/**连续登陆礼包已经领取的天数*/
	private List<Integer> giftLogindayGot;
	/**每日奖励刷新时间*/
	private Date giftDayRefresh;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public List<Integer> getGiftLvGot(){
		return this.giftLvGot;
	}
	public void setGiftLvGot(List<Integer> giftLvGot){
		this.giftLvGot=giftLvGot;
	}
	public int getGiftOnlineGot(){
		return this.giftOnlineGot;
	}
	public void setGiftOnlineGot(int giftOnlineGot){
		this.giftOnlineGot=giftOnlineGot;
	}
	public Date getGiftOnlineTime(){
		return this.giftOnlineTime;
	}
	public void setGiftOnlineTime(Date giftOnlineTime){
		this.giftOnlineTime=giftOnlineTime;
	}
	public int getGiftOnlineTimeAcc(){
		return this.giftOnlineTimeAcc;
	}
	public void setGiftOnlineTimeAcc(int giftOnlineTimeAcc){
		this.giftOnlineTimeAcc=giftOnlineTimeAcc;
	}
	public int getGiftSignCnt(){
		return this.giftSignCnt;
	}
	public void setGiftSignCnt(int giftSignCnt){
		this.giftSignCnt=giftSignCnt;
	}
	public Date getGiftSignTime(){
		return this.giftSignTime;
	}
	public void setGiftSignTime(Date giftSignTime){
		this.giftSignTime=giftSignTime;
	}
	public int getGiftLogindayCnt(){
		return this.giftLogindayCnt;
	}
	public void setGiftLogindayCnt(int giftLogindayCnt){
		this.giftLogindayCnt=giftLogindayCnt;
	}
	public List<Integer> getGiftLogindayGot(){
		return this.giftLogindayGot;
	}
	public void setGiftLogindayGot(List<Integer> giftLogindayGot){
		this.giftLogindayGot=giftLogindayGot;
	}
	public void setGiftDayRefresh(Date giftDayRefresh) {
		this.giftDayRefresh = giftDayRefresh;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getGiftDayRefresh() {
		return giftDayRefresh;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
}