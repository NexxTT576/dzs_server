package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.boss.BossBubbleVO;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.domain.union.UnionBossPlayer;

/**
 * 帮派青龙殿
 * 
 * @author 白雪林
 *
 */
public class UnionBossPlayerAO extends BaseAO {

	// 获取topTen
	public List<BossTopTenVO> getTopTenList(int unionId) {
		return unionBossPlayerDAO().getTopTenList(unionId);
	}

	// 删除上次帮派参与活动的玩家数据
	public void delete(int unionId) {
		unionBossPlayerDAO().delete(unionId);
	}

	// 清除缓存数据
	public void deleteBossCache(int unionId) {
		unionBossPlayerDAO().deleteBossCache(unionId);
	}

	// 没有新建玩家
	public UnionBossPlayer queryAdd(String acc, int roleId, String name, int unionId) {
		UnionBossPlayer player = unionBossPlayerDAO().query(roleId, unionId);
		if (player == null) {
			UnionBossPlayer playerOld = unionBossPlayerDAO().queryOne(roleId);
			if (playerOld != null) {
				unionBossPlayerDAO().deleteOne(roleId);
			}
			unionBossPlayerDAO().add(acc, roleId, name, unionId);
			player = unionBossPlayerDAO().query(roleId, unionId);
		}
		return player;
	}

	// 排名列表
	public List<RankList> getRankList(int unionId) {
		return unionBossPlayerDAO().getRankList(unionId);
	}

	// 更新玩家数据
	public void update(UnionBossPlayer ubp) {
		unionBossPlayerDAO().update(ubp);
	}

	// 获取冒字列表
	public List<BossBubbleVO> getBubbleList(int roleId, int unionId, int refreshDis) {
		return unionBossPlayerDAO().getBubbleList(roleId, unionId, refreshDis);
	}

	// 更新排行
	public int updateRankList(String account, String name, int damageTotal, int roleLevel, int roleId, int unionId,
			String faction) {
		return unionBossPlayerDAO().updateRankList(account, name, damageTotal, roleLevel, roleId, unionId, faction);
	}

	// 更新冒泡列表
	public void updateBubbleList(String name, int damageTotal, int id, int unionId, int rtnNum, int bubbleDis) {
		unionBossPlayerDAO().updateBubbleList(name, damageTotal, id, unionId, rtnNum, bubbleDis);
	}

	// 更新攻击次数
	public void updateBattleCnt(int roleId, int unionId) {
		unionBossPlayerDAO().updateBattleCnt(roleId, unionId);
	}

}