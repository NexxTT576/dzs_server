package com.mdy.dzs.game.domain.dart;

import java.io.Serializable;

public class DartDataRtnVO implements Serializable{
	/*
	{	
			dartState: 查看的镖车状态 	【1-正常 2-已押镖完毕】
			dartData:
			{
				lv:			玩家等级
				name：		玩家名字
				attack：	玩家战力
				quality：	镖车品质
				beRobTimes：被抢次数
				getCoin：	获得奖励
						[
						{type:*, id:*, num:*}
						,...
						]	
				cardData：[
							{resId:*,
							 cls:*
							},...
						]
			}
		}
*/
	private static final long serialVersionUID = 1L;
	
	private int dartState;
	private DartDataVO dartData;
	
	public DartDataRtnVO(int dartState, DartDataVO dartData){
		this.dartState = dartState;
		this.dartData  = dartData;
	}
	
	public int getDartState() {
		return dartState;
	}
	public void setDartState(int dartState) {
		this.dartState = dartState;
	}
	public DartDataVO getDartData() {
		return dartData;
	}
	public void setDartData(DartDataVO dartData) {
		this.dartData = dartData;
	}
	
	
	
}
