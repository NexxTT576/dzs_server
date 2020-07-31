package com.mdy.dzs.game.domain.dart;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.gift.GiftItem;

public class EnterFaceVO implements Serializable {
	/*
	 * selfState: 自己押镖状态 【1-押镖结束 2-压镖中 3-押镖完成】 getCoin： //获得奖励 [ {type:*, id:*,
	 * num:*} ,... ]奖励货币类型
	 * 
	 * selfDartCar: //自己镖车状态 { roleId： 角色ID name： 名字 lv： 等级 quality： 镖车品质 【1-4
	 * ：1-绿，2-蓝，3-紫，4-金】 arriveTime：倒记到达时间 dartkey: 战斗时发送 } otherDartCar:[{ roleId：
	 * 角色ID name： 名字 lv： 等级 quality： 镖车品质 【1-4 ：1-绿，2-蓝，3-紫，4-金】 arriveTime：倒记到达时间
	 * dartkey: 战斗时发送 }, ... ] refreshTime:*, 刷新倒记秒 speedUpCost:*, 加速运镖花费
	 * lastTime:*, 活动持续时长
	 */

	private static final long serialVersionUID = 1L;

	private int selfState;
	private List<GiftItem> getCoin;
	private DartCarVO selfDartCar;
	private List<DartCarVO> otherDartCar;
	private int refreshTime;
	private int speedUpCost;
	private int lastTime;

	public EnterFaceVO(int selfState, List<GiftItem> getCoin, DartCarVO selfDartCar, List<DartCarVO> otherDartCar,
			int refreshTime, int speedUpCost, int lastTime) {
		this.selfState = selfState;
		this.getCoin = getCoin;
		this.selfDartCar = selfDartCar;
		this.otherDartCar = otherDartCar;
		this.refreshTime = refreshTime;
		this.speedUpCost = speedUpCost;
		this.lastTime = lastTime;
	}

	public int getSelfState() {
		return selfState;
	}

	public void setSelfState(int selfState) {
		this.selfState = selfState;
	}

	public List<GiftItem> getGetCoin() {
		return getCoin;
	}

	public void setGetCoin(List<GiftItem> getCoin) {
		this.getCoin = getCoin;
	}

	public DartCarVO getSelfDartCar() {
		return selfDartCar;
	}

	public void setSelfDartCar(DartCarVO selfDartCar) {
		this.selfDartCar = selfDartCar;
	}

	public List<DartCarVO> getOtherDartCar() {
		return otherDartCar;
	}

	public void setOtherDartCar(List<DartCarVO> otherDartCar) {
		this.otherDartCar = otherDartCar;
	}

	public int getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(int refreshTime) {
		this.refreshTime = refreshTime;
	}

	public int getSpeedUpCost() {
		return speedUpCost;
	}

	public void setSpeedUpCost(int speedUpCost) {
		this.speedUpCost = speedUpCost;
	}

	public int getLastTime() {
		return lastTime;
	}

	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}

}
