package com.mdy.dzs.game.dao.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.mdy.dzs.game.dao.RoleTournamentDAO;
import com.mdy.dzs.game.domain.tournament.RoleTourRankVO;
import com.mdy.dzs.game.domain.tournament.RoleTournament;
import com.mdy.dzs.game.domain.tournament.TourRankCompare;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheRoleTournamentDAO extends RoleTournamentDAO {

	private static final String ID_KEY_PREFIX = "TOUR";
	private SafeCache safeCache;

	public CacheRoleTournamentDAO() {
		safeCache = new SafeCache(1800);
	}

	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource(cacheResource);
	}

	// 排行榜key
	private String getRankListKey() {
		return ID_KEY_PREFIX + "Rank";
	}

	@Override
	public int updateScore(int roleId, int score, int challengeTimes, int winTimes, int totalTimes) {
		// 先更新到数据库
		super.updateScore(roleId, score, challengeTimes, winTimes, totalTimes);
		// 然后更新到缓存 并进行排行
		List<RoleTourRankVO> rankList = getRankList();
		RoleTourRankVO roleRank = null;
		// 获得在缓存中的数据
		for (int i = 0; i < rankList.size(); i++) {
			RoleTourRankVO roleTourRankVO = rankList.get(i);
			if (roleTourRankVO.getRoleId() == roleId) {
				roleRank = roleTourRankVO;
			}
		}
		if (roleRank == null && totalTimes != 0) {
			// 缓存中没有
			roleRank = new RoleTourRankVO();
			roleRank.setRoleId(roleId);
			rankList.add(roleRank);
		}
		if (roleRank != null) {
			roleRank.setScore(score);
			roleRank.setTotalTimes(totalTimes);
		}
		// 进行排序 并给排行榜 赋值
		// 重排排行榜
		TourRankCompare compar = new TourRankCompare();
		Collections.sort(rankList, compar);
		int rank = 0;
		for (int i = 0; i < rankList.size(); i++) {
			if (rankList.get(i).getRoleId() == roleId) {// 更新
				rank = i + 1;
				break;
			}
		}
		// 更新积分排行列表
		String rankListkey = getRankListKey();
		safeCache.putToCache(rankListkey, rankList);

		return rank;
	}

	/**
	 * 获取排行列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleTourRankVO> getRankList() {
		String key = getRankListKey();
		List<RoleTourRankVO> rankList = (List<RoleTourRankVO>) safeCache.loadFromCache(key);
		if (rankList == null || rankList.size() == 0) {
			rankList = new ArrayList<RoleTourRankVO>();
			List<RoleTournament> rankListAll = super.queryListByScoreDesc();
			for (int i = 0; i < rankListAll.size(); i++) {
				if (rankListAll.get(i).getTotalTimes() == 0)
					continue;
				RoleTourRankVO roleTourRankVO = new RoleTourRankVO();
				roleTourRankVO.setRank(i + 1);
				roleTourRankVO.setRoleId(rankListAll.get(i).getRoleId());
				roleTourRankVO.setScore(rankListAll.get(i).getScore());
				roleTourRankVO.setTotalTimes(rankListAll.get(i).getTotalTimes());
				rankList.add(roleTourRankVO);
			}
			safeCache.putToCache(key, rankList);
		}
		for (Iterator<RoleTourRankVO> iterator = rankList.iterator(); iterator.hasNext();) {
			RoleTourRankVO roleTourRankVO = (RoleTourRankVO) iterator.next();
			if (roleTourRankVO.getTotalTimes() == 0) {
				iterator.remove();
			}
		}
		return rankList;
	}

	/**
	 * 清空积分 清空缓存
	 */
	@Override
	public void clearScore(int number) {
		super.clearScore(number);
		safeCache.removeFromCache(getRankListKey());
	}
}
