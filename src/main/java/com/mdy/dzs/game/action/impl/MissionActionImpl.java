package com.mdy.dzs.game.action.impl;

import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.mission.MissionDefine;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.MissionAction;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.mission.Mission;
import com.mdy.dzs.game.domain.mission.MissionListVO;
import com.mdy.dzs.game.domain.mission.RoleDailyMission;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.UpdateInfoVO;
import com.mdy.dzs.game.exception.MissionException;
import com.mdy.sharp.container.biz.BizException;

public class MissionActionImpl extends ApplicationAwareAction implements MissionAction {

	@Override
	public Map<Integer, MissionDefine> queryMissionDefines() {
		return missionAO().queryMissionDefines();
	}

	@Override
	public MissionListVO queryMissionList(String account) throws BizException {
		Role role = roleAO().queryExistAccount(account);
		MissionListVO vo = new MissionListVO();
		vo.setMissions(missionAO().queryMissionList(role.getId()));
		vo.setExtendMissionDefines(missionAO().getExtendsMissionDefines());
		vo.setDailyMission(missionAO().queryRoleDailyMission(role.getId()));
		return vo;
	}

	@Override
	public UpdateInfoVO getMissionReward(String account, int missionDefineId) throws BizException {
		Role role = roleAO().queryExistAccount(account);
		List<ProbItem> res = missionExecAO().getMissionReward(role, missionDefineId);
		List<Mission> newMission = missionExecAO().AcceptBeStartMission(role);
		UpdateInfoVO vo = new UpdateInfoVO(role);
		vo.setProbs(res);
		vo.setAcceptMissions(newMission);
		return vo;
	}

	@Override
	public void reloadMissionDefine() {
		missionAO().reloadMissionDefine();
	}

	@Override
	public void AcceptBeStartMission(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		missionExecAO().AcceptBeStartMission(role);
	}

	@Override
	public UpdateInfoVO getDailyMissionReward(String acc, int id) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleDailyMission rdm = missionAO().queryRoleDailyMission(role.getId());
		if (rdm.getReawrds().indexOf(id) != -1) {
			throw MissionException.getException(MissionException.EXCE_MISSION_IS_REWARDED, role.getId(), id);
		}
		List<ProbItem> res = missionAO().getDailyMissionRewards(id);
		if (res == null) {
			throw MissionException.getException(MissionException.EXCE_MISSION_NO_REWARDED, id);
		}
		packetAO().sendAward(role, res, RoleItemLog.SYS_任务积分_领取奖励, id + "");
		rdm.getReawrds().add(id);
		missionAO().updateRoleDailyMission(rdm);
		UpdateInfoVO vo = new UpdateInfoVO(role);
		vo.setProbs(res);
		return vo;
	}

}
