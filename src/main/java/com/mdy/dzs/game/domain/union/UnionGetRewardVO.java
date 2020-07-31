package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;

public class UnionGetRewardVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -366554821538067314L;
	private int gift;
	private List<ProbItem> rewardList;
	public int getGift() {
		return gift;
	}
	public void setGift(int gift) {
		this.gift = gift;
	}
	public List<ProbItem> getRewardList() {
		return rewardList;
	}
	public void setRewardList(List<ProbItem> rewardList) {
		this.rewardList = rewardList;
	}
	
}
