package com.mdy.dzs.game.domain.snatch;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 角色的夺宝状态模型
 * @author zhou
 *
 */
public class RoleSnatch implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**角色id*/
	private int roleId;
	/**状态*/
	private int state;
	/**夺宝 0-未抢夺过 1-有过抢夺操作 */
	private int snatched;
	/**抢夺免战截止时间*/
	private int warFreeTime;
	/**抢夺各品质失败计数列表【白，绿，蓝，紫，金】*/
	private List<Integer> snatchFailList;
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
	public int getState(){
		return this.state;
	}
	public void setState(int state){
		this.state=state;
	}
	public int getSnatched(){
		return this.snatched;
	}
	public void setSnatched(int snatched){
		this.snatched=snatched;
	}
	public int getWarFreeTime(){
		return this.warFreeTime;
	}
	public void setWarFreeTime(int warFreeTime){
		this.warFreeTime=warFreeTime;
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
	public List<Integer> getSnatchFailList() {
		return snatchFailList;
	}
	public void setSnatchFailList(List<Integer> snatchFailList) {
		this.snatchFailList = snatchFailList;
	}
}