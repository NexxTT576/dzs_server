package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;

public class WorkShopFinishVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 237421344250872332L;
	private int gold;
	private List<ProbItem> rewardList;
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public List<ProbItem> getRewardList() {
		return rewardList;
	}
	public void setRewardList(List<ProbItem> rewardList) {
		this.rewardList = rewardList;
	}

}
