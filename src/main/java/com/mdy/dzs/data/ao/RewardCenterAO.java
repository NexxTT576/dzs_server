package com.mdy.dzs.data.ao;

import java.util.List;

import com.mdy.dzs.data.domain.reward.RewardCenter;

/**
 * 奖励中心
 * 
 * @author 房曈
 *
 */
public class RewardCenterAO extends BaseAO {
	//

	/**
	 * 查询列表
	 */
	public List<RewardCenter> queryListInRewardTime(String serverName) {
		return rewardCenterDAO().queryListInRewardTime(serverName);
	}
}