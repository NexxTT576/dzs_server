package com.mdy.dzs.game.domain.furnace;

import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;

public class ClientFurnVO {
	
	/**获得的物品*/
	private List<ProbItem> rtnAry;
	
	/**变化的卡牌*/
	private List<FurnInfoVO> changeAry;
	
	public List<ProbItem> getRtnAry() {
		return rtnAry;
	}
	public void setRtnAry(List<ProbItem> rtnAry) {
		this.rtnAry = rtnAry;
	}
	public List<FurnInfoVO> getChangeAry() {
		return changeAry;
	}
	public void setChangeAry(List<FurnInfoVO> changeAry) {
		this.changeAry = changeAry;
	}
}
