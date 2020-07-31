package com.mdy.dzs.game.domain.vip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VipInfoVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	{	list:[{	index:编号
				cnt:充值次数
				type:消费类型
				}]
		vipData:{
				level:当前等级
				curExp:当前经验值累计
				curExpLimit：当前经验值上限
				}
		yueData:{
				cost:月卡售价
				goldget:立得金币
				type:消费类型
				isCanBuy: 是否可购买月卡	1-是 2-否
				days:月卡剩余天数
				}
		curGold:当前金币数
	}
	*/
	
	private YueDataVO yueData;
	private VipDataVO vipData;
	private List<VipListVO> list = new ArrayList<VipListVO>();
	private int curGold;
	private Object extend;
	
	public YueDataVO getYueData() {
		return yueData;
	}
	public void setYueData(YueDataVO yueData) {
		this.yueData = yueData;
	}
	public VipDataVO getVipData() {
		return vipData;
	}
	public void setVipData(VipDataVO vipData) {
		this.vipData = vipData;
	}
	public List<VipListVO> getList() {
		return list;
	}
	public void setList(List<VipListVO> list) {
		this.list = list;
	}
	public int getCurGold() {
		return curGold;
	}
	public void setCurGold(int curGold) {
		this.curGold = curGold;
	}
	public Object getExtend() {
		return extend;
	}
	public void setExtend(Object extend) {
		this.extend = extend;
	}

}
