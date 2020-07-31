/**
 * 
 */
package com.mdy.dzs.data.action;

import java.util.List;

import com.mdy.dzs.data.domain.reward.RewardCenter;

/**
 * 领奖中心
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年12月4日 下午9:54:28
 */
public interface RewardCenterAction {

	List<RewardCenter> queryListInRewardTime(String serverName);
}
