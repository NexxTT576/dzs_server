package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;

public class CheckWorkTimeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1324488649253860250L;
	private int isOver;
	private long leftTime;
	private List<ProbItem> rewardList;
	public int getIsOver() {
		return isOver;
	}
	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}
	public long getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(long leftTime) {
		this.leftTime = leftTime;
	}
	public List<ProbItem> getRewardList() {
		return rewardList;
	}
	public void setRewardList(List<ProbItem> rewardList) {
		this.rewardList = rewardList;
	}

}
