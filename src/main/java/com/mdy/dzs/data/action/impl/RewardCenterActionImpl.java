/**
 * 
 */
package com.mdy.dzs.data.action.impl;

import java.util.List;

import com.mdy.dzs.data.ApplicationAwareAction;
import com.mdy.dzs.data.action.RewardCenterAction;
import com.mdy.dzs.data.domain.reward.RewardCenter;

public class RewardCenterActionImpl extends ApplicationAwareAction implements RewardCenterAction {

	@Override
	public List<RewardCenter> queryListInRewardTime(String serverName) {
		return rewardCenterAO().queryListInRewardTime(serverName);
	}

}
