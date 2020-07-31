package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.DataAction;
import com.mdy.dzs.data.domain.arena.Arena;
import com.mdy.dzs.data.domain.arena.ArenaShop;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.arena.RoleArenaShop;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.Container;

/**
 * 竞技场
 * 
 * @author 房曈
 *
 */
public class RoleArenaAO extends BaseAO {

	//
	private List<Arena> awardConfig;
	private int maxAwardRank;
	private List<ArenaShop> arenaShopList;
	private Map<Integer, ArenaShop> arenaShopMap;

	@Override
	public void start() {
		DataAction dataAction = Container.get().createRemote(DataAction.class, DataApplication.CLUSTER_DATA_SYSTEM);
		awardConfig = dataAction.queryArenaDatas();
		for (Arena arena : awardConfig) {
			maxAwardRank = arena.getMax() > maxAwardRank ? arena.getMax() : maxAwardRank;
		}
		arenaShopList = dataAction.queryArenaShopDatas();
		arenaShopMap = new HashMap<Integer, ArenaShop>();
		for (ArenaShop arenaShop : arenaShopList) {
			arenaShopMap.put(arenaShop.getId(), arenaShop);
		}
		super.start();
	}

	/**
	 * 查询 如果没有则生成
	 */
	public RoleArena queryByRoleId(int roleId) {
		RoleArena ra = roleArenaDAO().query(roleId);
		if (ra == null) {
			ra = new RoleArena();
			ra.setRoleId(roleId);
			ra.setAwardIds(new ArrayList<Integer>());
			clacRankAward(ra);
			add(ra);
		}
		if (ra.getAwardIds().size() == 0) {
			clacRankAward(ra);
		}
		return ra;
	}

	/**
	 * 计算排名奖励
	 * 
	 * @param ra
	 */
	private void clacRankAward(RoleArena ra) {
		int rank = ra.getRank();
		if (rank > maxAwardRank)
			return;
		ra.getAwardIds().clear();
		for (int i = 0; i < awardConfig.size(); i++) {
			Arena config = awardConfig.get(i);
			if (rank >= config.getMin() && rank <= config.getMax()) {
				if (config.getType() == 1) {
					ra.setAwardSilver((int) (config.getFix() - rank * config.getRatio()));
				} else if (config.getType() == 2) {
					ra.setAwardPopual((int) (config.getFix() - rank * config.getRatio()));
				}
				ra.getAwardIds().add(config.getId());
			}
		}
		roleArenaDAO().updateAwardInfo(ra);
	}

	/**
	 * 查询列表
	 */
	public List<RoleArena> queryListByRanks(List<Integer> ranks) {
		List<RoleArena> res = roleArenaDAO().queryListByRanks(ranks);
		for (RoleArena ra : res) {
			if (ra.getAwardIds().size() == 0) {
				clacRankAward(ra);
			}
		}
		return res;
	}

	/**
	 * 根据当前排行获取挑战排行列表
	 * 
	 * @param rank
	 * @return
	 */
	public List<Integer> queryChallengeRanks(int rank) {
		int cnt = 8;
		List<Integer> rankAry = new ArrayList<Integer>();
		int maxRank = roleArenaDAO().queryMaxCount();
		if (rank <= 11) {
			rankAry.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
		} else {
			// int dis = 0;
			// if(rank <= 20) dis = 1;
			// else if(rank <= 50) dis = 3;
			// else if(rank <= 100) dis = 5;
			// else if(rank <= 300) dis = 15;
			// else if(rank <= 600) dis = 25;
			// else if(rank <= 1000) dis = 50;
			// else if(rank > 1000) dis = 100;

			int dis = 0;
			if (rank <= 50)
				dis = 1;
			else if (rank <= 100)
				dis = random(1, 2);
			else if (rank <= 300)
				dis = random(1, 5);
			else if (rank <= 600)
				dis = random(1, 15);
			else if (rank <= 3000)
				dis = random(50, 100);
			else if (rank > 3000)
				dis = random(100, 200);

			if (rank + dis >= maxRank)
				cnt++;
			if (rank + dis * 2 >= maxRank)
				cnt++;
			for (int i = 1; i <= cnt; i++) {
				if (rank - i * dis <= 0)
					break;
				rankAry.add(rank - i * dis);
			}
			rankAry.add(rank);
			int addNum = 8 - rankAry.size();
			int minCnt = 2;
			if (addNum > 0)
				minCnt += addNum;
			for (int i = 1; i <= minCnt; i++) {
				rankAry.add(rank + i * dis);
			}
		}
		Collections.sort(rankAry);
		return rankAry;
	}

	/**
	 * 添加
	 * 
	 * @param RoleArena
	 */
	public void add(RoleArena ra) {
		roleArenaDAO().addGenerateKey(ra);
		;
	}

	/**
	 * 更新
	 * 
	 * @param RoleArena
	 */
	public void swapUpdate(RoleArena ra1, RoleArena ra2) {
		roleArenaDAO().swapUpdate(ra1.getRoleId(), ra2.getRoleId());
		ra1 = queryByRoleId(ra1.getRoleId());
		ra2 = queryByRoleId(ra2.getRoleId());
	}

	public RoleArena queryByRank(int rank) {
		RoleArena ra = roleArenaDAO().queryByRank(rank);
		if (ra != null && ra.getAwardIds().size() == 0) {
			clacRankAward(ra);
		}
		return ra;
	}

	public List<RoleArena> queryAwardRank() {
		List<RoleArena> res = roleArenaDAO().queryAwardRank(maxAwardRank);
		for (RoleArena ra : res) {
			if (ra.getAwardIds().size() == 0) {
				clacRankAward(ra);
			}
		}
		return res;
	}

	///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 查询 如果没有则生成
	 */
	public RoleArenaShop queryShopByRoleId(int roleId) {
		RoleArenaShop shop = roleArenaDAO().queryRoleArenaShop(roleId);
		if (shop == null) {
			roleArenaDAO().addShop(roleId);
			shop = roleArenaDAO().queryRoleArenaShop(roleId);
		}
		if (!DateUtil.isToday(shop.getDayRefreshTime())) {
			roleArenaDAO().resetArenaShop(shop);
			shop = roleArenaDAO().queryRoleArenaShop(roleId);
		}
		return shop;
	}

	/**
	 * 更新商城
	 * 
	 * @param shop
	 */
	public void updateShop(RoleArenaShop shop) {
		roleArenaDAO().updateShop(shop);
	}

	public List<ArenaShop> getArenaShops() {
		return arenaShopList;
	}

	public ArenaShop getArenaShopById(int id) {
		return arenaShopMap.get(id);
	}

	public int getPurchased(RoleArenaShop rShop, ArenaShop buyData) {
		Integer perchasedData = null;
		if (buyData.getNum1() != -1) {// 有上限限制
			if (buyData.getType1() == ArenaShop.TYPE_总次数限定) {// 总次数限定
				perchasedData = rShop.getAllPurchased().get("" + buyData.getId());
			} else {// 日次数限定
				perchasedData = rShop.getDayPurchased().get("" + buyData.getId());
			}
		}
		if (perchasedData == null)
			perchasedData = 0;
		return perchasedData;
	}

	public void addPurchased(RoleArenaShop rShop, ArenaShop buyData, int num) {
		String strId = "" + buyData.getId();
		Map<String, Integer> purchasedMap = null;
		if (buyData.getNum1() != -1) {// 有上限限制
			if (buyData.getType1() == ArenaShop.TYPE_总次数限定) {// 总次数限定
				purchasedMap = rShop.getAllPurchased();
			} else {// 日次数限定
				purchasedMap = rShop.getDayPurchased();
			}
		}
		if (purchasedMap != null) {
			Integer cnt = purchasedMap.get(strId);
			if (cnt == null) {
				cnt = num;
			} else {
				cnt += num;
			}
			purchasedMap.put(strId, cnt);
		}
	}

	/**
	 * 清理排行榜
	 */
	public void clearRoleArena() {
		roleArenaDAO().clearRoleArena();
	}

	public int random(int a, int b) {
		if (a == b)
			return a;
		Random rnd = new Random();
		return rnd.nextInt(b + 1 - a) + a;
	}
}