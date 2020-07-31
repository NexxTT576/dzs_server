package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mdy.dzs.game.cache.MemcachedManager;
import com.mdy.dzs.game.domain.rank.RankInfo;
import com.mdy.dzs.game.domain.rank.RoleRank;
import com.mdy.dzs.game.filter.RankFilter;

/**
 * 排行表
 * 
 * @author fangtong
 *
 */
public class RoleRankAO extends BaseAO {

	//

	public static final int RANK_COUNT = 50;// 前50名
	/** 排行榜排名前？名 */
	public static final String ARENA_RANK_COUNT = "arenaRank" + RANK_COUNT;
	/** 球队等级排名前？名 */
	public static final String GRADE_RANK_COUNT = "gradeRank" + RANK_COUNT;
	/** 战力排名前？名 */
	public static final String ATTACK_RANK_COUNT = "attackRank" + RANK_COUNT;
	/** 副本排名前？名 */
	public static final String BATTLE_RANK_COUNT = "battleRank" + RANK_COUNT;

	public static final int MAX_RANK_SIZE = 2000;// 最多记录前2000名

	/*
	 * 初始化排名信息到缓存
	 */
	@Override
	public void start() {
		addRanksToCache();
	}

	public void addRanksToCache() {
		List<RoleRank> list = roleRankDAO().queryList();
		for (RoleRank tr : list) {
			MemcachedManager.get().add("Rank" + tr.getRoleId(), tr, 7200);
		}
	}

	private String getCacheKey(int type, int size) {
		String key = "";
		switch (type) {
		case RankInfo.TYPE_RANK_竞技场:
			key = ARENA_RANK_COUNT;
			break;
		case RankInfo.TYPE_RANK_等级:
			key = GRADE_RANK_COUNT;
			break;
		case RankInfo.TYPE_RANK_战力:
			key = ATTACK_RANK_COUNT;
			break;
		case RankInfo.TYPE_RANK_副本:
			key = BATTLE_RANK_COUNT;
			break;
		default:
			break;
		}
		return key;
	}

	public void refreshRank(boolean persist) {
		Map<Integer, RoleRank> ranks = new HashMap<Integer, RoleRank>();
		addRank(RankInfo.TYPE_RANK_竞技场, ranks);
		addRank(RankInfo.TYPE_RANK_等级, ranks);
		addRank(RankInfo.TYPE_RANK_战力, ranks);
		addRank(RankInfo.TYPE_RANK_副本, ranks);
		Set<Integer> set = ranks.keySet();
		for (int id : set) {
			RoleRank tr = MemcachedManager.get().get("Rank" + id);
			if (tr == null) {
				RoleRank rank = ranks.get(id);
				MemcachedManager.get().add("Rank" + rank.getRoleId(), rank, 3600);
				if (persist) {
					RoleRank r = roleRankDAO().query(rank.getRoleId());
					if (r == null) {
						roleRankDAO().add(rank);
					} else {
						roleRankDAO().update(rank);
					}
				}
			} else {

				RoleRank rank = ranks.get(id);
				tr.setArenaRank(rank.getArenaRank());
				tr.setAttackRank(rank.getAttackRank());
				tr.setGradeRank(rank.getGradeRank());
				tr.setBattleRank(rank.getBattleRank());
				MemcachedManager.get().replace("Rank" + id, tr, 3600);

				if (persist) {
					roleRankDAO().update(tr);
				}
			}
		}
	}

	//
	/**
	 * 查询
	 */
	public RoleRank queryExisted(int teamId) {
		RoleRank rank = MemcachedManager.get().get("Rank" + teamId);
		if (rank == null) {
			rank = new RoleRank();
		}
		return rank;
	}

	/**
	 * 添加
	 * 
	 * @param TeamRank
	 */
	public void add(RoleRank tr) {
		roleRankDAO().add(tr);
	}

	/**
	 * 更新
	 * 
	 * @param TeamRank
	 */
	public void update(RoleRank tr) {
		roleRankDAO().update(tr);
	}

	/**
	 * 根据排名类型查询排行列表
	 * 
	 * @param type
	 * @return
	 */
	public List<RoleRank> queryListFromDB(int type, int pageSize) {
		int pageIndex = 0;
		RankFilter filter = new RankFilter();
		filter.setPageSize(pageSize);
		filter.setPageIndex(pageIndex);

		if (type == RankInfo.TYPE_RANK_竞技场) {
			filter.setStartArenaRank(1);
			filter.setOrderType(RankFilter.ORDER_BY_ARENA_DESC);
		} else if (type == RankInfo.TYPE_RANK_等级) {
			filter.setStartGradeRank(1);
			filter.setOrderType(RankFilter.ORDER_BY_GRADE_DESC);
		} else if (type == RankInfo.TYPE_RANK_战力) {
			filter.setStartAttackRank(1);
			filter.setOrderType(RankFilter.ORDER_BY_ATTACK_DESC);
		} else if (type == RankInfo.TYPE_RANK_副本) {
			filter.setStartStarsRank(1);
			filter.setOrderType(RankFilter.ORDER_BY_STARS_DESC);
		}
		return roleRankDAO().queryListByFilter(filter);
	}

	/**
	 * 根据排名类型查询前？名
	 * 
	 * @param type
	 * @return
	 */
	public List<RoleRank> queryListByType(int type) {
		String key = getCacheKey(type, RANK_COUNT);
		List<RoleRank> list = MemcachedManager.get().get(key);
		if (list == null) {
			refreshRank(false);
			list = MemcachedManager.get().get(key);
		}

		return list;
	}

	/**
	 * 从数据库查询各排名前1000
	 * 
	 * @param type
	 * @param ranks
	 */
	public void addRank(int type, Map<Integer, RoleRank> ranks) {
		List<RoleRank> top = new ArrayList<RoleRank>();

		List<RoleRank> list = roleRankDAO().queryListByRank(type, MAX_RANK_SIZE);// 按排名查询
		for (int i = 0; i < list.size(); i++) {
			RoleRank tmp = list.get(i);
			RoleRank rank = ranks.get(tmp.getRoleId());
			if (rank == null) {
				rank = new RoleRank();
				rank.setRoleId(tmp.getRoleId());
				rank.setAccount(tmp.getAccount());
				rank.setName(tmp.getName());
				rank.setResId(tmp.getResId());
				ranks.put(rank.getRoleId(), rank);
			}
			switch (type) {
			case RankInfo.TYPE_RANK_竞技场:
				rank.setArenaRank(i + 1);
				rank.setPrestige(tmp.getPrestige());
				break;
			case RankInfo.TYPE_RANK_副本:
				rank.setBattleRank(i + 1);
				rank.setBattleId(tmp.getBattleId());
				rank.setBattleStars(tmp.getBattleStars());
				break;
			case RankInfo.TYPE_RANK_战力:
				rank.setAttackRank(i + 1);
				rank.setAttack(tmp.getAttack());
				break;
			case RankInfo.TYPE_RANK_等级:
				rank.setGradeRank(i + 1);
				rank.setGrade(tmp.getGrade());
				break;
			default:
				break;
			}
			if (i < RANK_COUNT) {
				top.add(rank);
			}
		}
		MemcachedManager.get().replace(getCacheKey(type, RANK_COUNT), top, 3600);
	}
}