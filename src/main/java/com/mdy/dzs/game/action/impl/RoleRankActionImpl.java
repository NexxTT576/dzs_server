package com.mdy.dzs.game.action.impl;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleRankAction;
import com.mdy.dzs.game.domain.battle.RoleBattle;
import com.mdy.dzs.game.domain.rank.RankInfo;
import com.mdy.dzs.game.domain.rank.RoleRank;
import com.mdy.dzs.game.domain.rank.RoleRankInfo;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.sharp.container.biz.BizException;

public class RoleRankActionImpl extends ApplicationAwareAction implements RoleRankAction {

	@Override
	public RoleRankInfo getRoleRankInfo(String acc, int type) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleBattle rb = roleBattleAO().queryByRoleId(role.getId());

		RoleRankInfo res = new RoleRankInfo();
		List<RoleRank> list = roleRankAO().queryListByType(type);
		RoleRank myRank = roleRankAO().queryExisted(role.getId());
		List<RankInfo> infos = new ArrayList<RankInfo>();
		Role doc = null;
		for (RoleRank tr : list) {
			doc = roleAO().queryByAccount(tr.getAccount());
			if (doc == null)
				continue;
			infos.add(createRankInfo(doc, tr, type, 0));
		}
		res.setInfos(infos);
		res.setMyRank(createRankInfo(role, myRank, type, rb.getBattleTotalStars()));
		return res;
	}

	private RankInfo createRankInfo(Role role, RoleRank roleRank, int type, int stars) {
		RankInfo info = new RankInfo();
		info.setRoleId(role.getId());
		info.setAccount(role.getAccount());
		info.setResId(role.getResId());
		info.setName(role.getName());
		info.setFaction(role.getFaction());
		info.setCls(role.getCls());

		info.setAttack(roleRank.getAttack() == 0 ? role.getAttack() : roleRank.getAttack());
		info.setGrade(roleRank.getGrade() == 0 ? role.getLevel() : roleRank.getGrade());
		info.setPrestige(roleRank.getPrestige() == 0 ? role.getPopual() : roleRank.getPrestige());

		info.setBattleStars(roleRank.getBattleStars() == 0 ? stars : roleRank.getBattleStars());
		info.setBattleId(roleRank.getBattleId());

		switch (type) {
		case RankInfo.TYPE_RANK_竞技场:
			info.setRank(roleRank.getArenaRank());
			break;
		case RankInfo.TYPE_RANK_副本:
			info.setRank(roleRank.getBattleRank());
			break;
		case RankInfo.TYPE_RANK_战力:
			info.setRank(roleRank.getAttackRank());
			break;
		case RankInfo.TYPE_RANK_等级:
			info.setRank(roleRank.getGradeRank());
			break;
		default:
			break;
		}
		info.setType(type);
		return info;
	}

	@Override
	public void refreshRank() {
		roleRankAO().refreshRank(false);
	}

	@Override
	public void updateRank() {
		roleRankAO().refreshRank(true);
	}

}
