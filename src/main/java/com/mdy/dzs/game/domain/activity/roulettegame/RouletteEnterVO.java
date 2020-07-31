package com.mdy.dzs.game.domain.activity.roulettegame;

import java.io.Serializable;
import java.util.List;


public class RouletteEnterVO implements Serializable{
	/*
	roleDataState:
	{	
		startT:	开始时间
		endT：	结束时间
		countDown:  倒计时秒
		credit：	当前积分
		surTimes:	剩余次数
		getBox:		已领取的箱子	[1,2,3]
		freeTimes：	免费次数
		dayAdd；	今日累积
		rouletteTimes：已获得的探宝次数
	}
	rouletteState:
	[
		{
		id:编号
		itemDisplay：显示物品
		}
	]
	activeData:
	{
		type:		活动类型（1-充值返利；2-消费返利；3-直接购买次数）
		price:		单价
		limitcnt；	次数上限
		score：		[]	积分档位
		rewardType1:[]	第一档积分奖励type
		rewardId1:	[]	第一档积分奖励id
		rewardCnt1:	[]	第一档积分奖励cnt
		rewardType2:[]	第二档积分奖励type
		rewardId2:	[]	第二档积分奖励id
		rewardCnt2:	[]	第二档积分奖励cnt
		rewardType3:[]	第三档积分奖励type
		rewardId3:	[]	第三档积分奖励id
		rewardCnt3:	[]	第三档积分奖励cnt
	}
*/
	private static final long serialVersionUID = 1L;
	
	private RoleDataStateVO roleDataState;
	private List<RouletteStateVO> rouletteState;
	private Roulette activeData;
	
	public RouletteEnterVO(RoleDataStateVO roleDataState,List<RouletteStateVO> rouletteState,Roulette activeData){
		this.roleDataState = roleDataState;
		this.rouletteState = rouletteState;
		this.activeData    = activeData;
	}
	
	public RoleDataStateVO getRoleDataState() {
		return roleDataState;
	}
	public void setRoleDataState(RoleDataStateVO roleDataState) {
		this.roleDataState = roleDataState;
	}
	public List<RouletteStateVO> getRouletteState() {
		return rouletteState;
	}
	public void setRouletteState(List<RouletteStateVO> rouletteState) {
		this.rouletteState = rouletteState;
	}
	public Roulette getActiveData() {
		return activeData;
	}
	public void setActiveData(Roulette activeData) {
		this.activeData = activeData;
	}	
}
