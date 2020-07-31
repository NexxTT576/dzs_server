package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.boss.BossBattlePlayer;
import com.mdy.dzs.game.domain.boss.BossBubbleVO;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.sharp.container.biz.BizException;

/**
 * boss战参与玩家
 * 
 * @author 白雪林
 *
 */
public class BossBattlePlayerAO extends BaseAO {

	private MissionExecAO missionExecAO;

	/**
	 * 
	 */
	public BossBattlePlayerAO(MissionExecAO missionExecAO) {
		this.missionExecAO = missionExecAO;
	}

	/**
	 * 查询添加
	 * 
	 * @throws BizException
	 */
	public BossBattlePlayer queryAdd(Role role) throws BizException {
		BossBattlePlayer player = bossBattlePlayerDAO().query(role.getId());
		if (player == null) {
			bossBattlePlayerDAO().add(role.getAccount(), role.getId(), role.getName());
			player = bossBattlePlayerDAO().query(role.getId());
			missionExecAO.enterBoss(role);
		}

		return player;
	}

	/**
	 * 添加
	 * 
	 * @param BossBattlePlayer
	 */
	public void add(String acc, int id, String name) {
		bossBattlePlayerDAO().add(acc, id, name);
	}

	/**
	 * 更新
	 * 
	 * @param BossBattlePlayer
	 */
	public void update(BossBattlePlayer bbp) {
		bossBattlePlayerDAO().update(bbp);
	}

	/** 清空t_boss_role */
	public void delete() {
		bossBattlePlayerDAO().delete();
	}

	/**
	 * 获取topTen
	 */
	public List<BossTopTenVO> getTopTenList() {
		return bossBattlePlayerDAO().getTopTenList();
	}

	public List<RankList> getRankList() {
		return bossBattlePlayerDAO().getRankList();
	}

	public List<BossBubbleVO> getBubbleList(int roleId) {
		return bossBattlePlayerDAO().getBubbleList(roleId);
	}

	public int updateRankList(String string, String string2, int damageTotal, int i, int j, String faction) {
		return bossBattlePlayerDAO().updateRankList(string, string2, damageTotal, i, j, faction);
	}

	public void updateBubbleList(String name, int damageTotal, int roleId) {
		bossBattlePlayerDAO().updateBubbleList(name, damageTotal, roleId);
	}

	public void deleteBossCache() {
		bossBattlePlayerDAO().deleteBossCache();
	}

	public void updateBattleCnt(int roleId) {
		bossBattlePlayerDAO().updateBattleCnt(roleId);
	}

}