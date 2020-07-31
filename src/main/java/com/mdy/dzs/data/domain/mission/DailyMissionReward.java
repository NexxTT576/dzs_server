package com.mdy.dzs.data.domain.mission;

import java.io.Serializable;
import java.util.List;

/**
 * 每日任务奖励模型
 * 
 * @author 房曈
 *
 */
public class DailyMissionReward implements Serializable {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	/***/
	private int id;
	/***/
	private int jifen;
	/***/
	private List<Integer> rewardIds;
	/***/
	private List<Integer> rewardTypes;
	/***/
	private List<Integer> rewardNums;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJifen() {
		return this.jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	public List<Integer> getRewardIds() {
		return this.rewardIds;
	}

	public void setRewardIds(List<Integer> rewardIds) {
		this.rewardIds = rewardIds;
	}

	public List<Integer> getRewardTypes() {
		return this.rewardTypes;
	}

	public void setRewardTypes(List<Integer> rewardTypes) {
		this.rewardTypes = rewardTypes;
	}

	public List<Integer> getRewardNums() {
		return this.rewardNums;
	}

	public void setRewardNums(List<Integer> rewardNums) {
		this.rewardNums = rewardNums;
	}
}