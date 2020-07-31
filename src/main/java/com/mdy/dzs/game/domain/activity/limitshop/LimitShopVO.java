package com.mdy.dzs.game.domain.activity.limitshop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LimitShopVO implements Serializable{

	/**
	 	countDown   倒计时 
		vipLevel  	vip等级
		sDate    	起始时间
		eDate    	截止时间
		toolsList[
					id  表id
					leftNum/总剩余量
					canBuyNum/可购买数量		-1 未购买，其它剩余可购买数量
					hasNum：拥有数量
				]
		
		]
	 */
	private static final long serialVersionUID = -7104931210573150932L;
	
	private long countDown;
	private List<ToolsVO> toolsList;
	private Date leftTime;
	private int vipLevel;
	private Date sDate;
	private Date eDate;
	public List<ToolsVO> getToolsList() {
		return toolsList;
	}
	public void setToolsList(List<ToolsVO> toolsList) {
		this.toolsList = toolsList;
	}
	public Date getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(Date leftTime) {
		this.leftTime = leftTime;
	}
	public int getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public long getCountDown() {
		return countDown;
	}
	public void setCountDown(long countDown) {
		this.countDown = countDown;
	}
}
