package com.mdy.dzs.game.domain.mission;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 每日任务记录模型
 * @author 房曈
 *
 */
public class RoleDailyMission implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**玩家id*/
	private int roleId;
	/**当前积分值*/
	private int jifen;
	/**已领取奖励*/
	private List<Integer> reawrds;
	/**每日刷新时间   */
	private Date dayRefresh;
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
	public int getJifen(){
		return this.jifen;
	}
	public void setJifen(int jifen){
		this.jifen=jifen;
	}
	public List<Integer> getReawrds(){
		return this.reawrds;
	}
	public void setReawrds(List<Integer> reawrds){
		this.reawrds=reawrds;
	}
	public Date getDayRefresh(){
		return this.dayRefresh;
	}
	public void setDayRefresh(Date dayRefresh){
		this.dayRefresh=dayRefresh;
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
}