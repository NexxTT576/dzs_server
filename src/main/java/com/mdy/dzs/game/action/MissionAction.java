package com.mdy.dzs.game.action;

import java.util.Map;

import com.mdy.dzs.data.domain.mission.MissionDefine;
import com.mdy.dzs.game.domain.mission.MissionListVO;
import com.mdy.dzs.game.domain.role.UpdateInfoVO;
import com.mdy.sharp.container.biz.BizException;

/**
 * 任务action
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年12月19日 下午10:47:12
 */
public interface MissionAction {

	/**
	 * 查询所有任务信息
	 * 
	 * @return
	 */
	public Map<Integer, MissionDefine> queryMissionDefines();

	/**
	 * 查询个人任务数据
	 * 
	 * @return
	 */
	MissionListVO queryMissionList(String account) throws BizException;

	/**
	 * 获取任务奖励
	 * 
	 * @param teamId
	 * @param missionDefineId
	 * @throws BizException
	 */

	public UpdateInfoVO getMissionReward(String account, int missionDefineId) throws BizException;

	/**
	 * 刷新任务
	 */

	public void reloadMissionDefine();

	/**
	 * 接受任务
	 */

	public void AcceptBeStartMission(String acc) throws BizException;

	/**
	 * 获得每日任务积分奖励
	 * 
	 * @param acc
	 * @param id
	 * @return
	 * @throws BizException
	 */

	public UpdateInfoVO getDailyMissionReward(String acc, int id) throws BizException;
}
