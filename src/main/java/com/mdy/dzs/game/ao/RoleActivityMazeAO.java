package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.activity.roulette.RouletteProb;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.activity.mazegame.MazeConfig;
import com.mdy.dzs.game.domain.activity.mazegame.RoleActivityMaze;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.sharp.container.biz.BizException;

/**
 * 迷宫寻宝表结构
 * 
 * @author 白雪林
 *
 */
public class RoleActivityMazeAO extends BaseAO {
	private CacheManager cacheManager;
	private PacketAO packetAO;

	public RoleActivityMazeAO(CacheManager cacheManager, PacketAO packetAO) {
		this.cacheManager = cacheManager;
		this.packetAO = packetAO;
	}

	// query
	public RoleActivityMaze query(int roleId) {
		RoleActivityMaze player = roleActivityMazeDAO().query(roleId);
		return player;
	}

	// query add
	public RoleActivityMaze queryAndAdd(int roleId) {
		RoleActivityMaze player = roleActivityMazeDAO().query(roleId);
		if (player == null) {
			roleActivityMazeDAO().add(roleId);
			player = roleActivityMazeDAO().query(roleId);
		}
		return player;
	}

	// update
	public void update(RoleActivityMaze player) {
		roleActivityMazeDAO().update(player);
	}

	// next day refresh
	public void dayRefresh(RoleActivityMaze player, MazeConfig mazeConfig) {
		player.setFreeTimes(mazeConfig.getDayFreeTimes());
		player.setSurGoldTimes(mazeConfig.getDigLimit());
		player.setGetTreasuryList(new ArrayList<Integer>());
		player.setDigCnt(0);
		player.setTreasuryMap(getTreasuryMap(mazeConfig));
		player.setDigFreeCnt(0);

		roleActivityMazeDAO().dayRefresh(player);
	}

	// creat treasury map
	public Map<String, ProbItem> getTreasuryMap(MazeConfig mazeConfig) {
		Map<String, ProbItem> treasuryMap = new HashMap<String, ProbItem>();
		for (int i = 1; i <= 8; i++) {
			treasuryMap.put(i + "", cacheManager.probGot(mazeConfig.getProbId(i)).get(0));
		}
		return treasuryMap;
	}

	// 挖一次花费
	public int getDigOneGold(RoleActivityMaze player, MazeConfig mazeConfig) {
		int digOneGold = 0;
		if (player.getFreeTimes() <= 0 && player.getGetTreasuryList().size() < player.getTreasuryMap().size()) {// 无免费次数
			int ind = 1;
			if (player.getDigFreeCnt() > 0) {// 当前轮有使用过免费次数
				ind = player.getGetTreasuryList().size() - player.getDigFreeCnt() + 1;
			} else {// 当前
				ind = player.getGetTreasuryList().size() + 1;
			}
			digOneGold = mazeConfig.getInitPrice() + (ind - 1) * mazeConfig.getAddPrice();
		}
		return digOneGold;
	}

	// 挖全部花费
	public int getDigAllGold(RoleActivityMaze player, MazeConfig mazeConfig) {
		int digAllGold = 0;

		if (player.getFreeTimes() > 0) {// 还有免费次数
			// 剩余需花费的次数
			int n = player.getTreasuryMap().size() - player.getFreeTimes() - player.getGetTreasuryList().size();
			if (n == 1) {
				return mazeConfig.getInitPrice();
			}
			if (n <= 0) {
				return 0;
			}
			int endGold = mazeConfig.getInitPrice() + (n - 1) * mazeConfig.getAddPrice();
			digAllGold = (int) ((mazeConfig.getInitPrice() + endGold) * n * 0.5 * 0.8);
		} else {// 已无免费次数可用
				// 已花费次数
			int cnt = player.getGetTreasuryList().size() - player.getDigFreeCnt();
			// 需花费次数
			int n = player.getTreasuryMap().size() - player.getGetTreasuryList().size();
			int fGold = 0;
			if (cnt > 0) {
				fGold = mazeConfig.getInitPrice() + (cnt - 1) * mazeConfig.getAddPrice();
				fGold += mazeConfig.getAddPrice();
			} else {
				fGold += mazeConfig.getInitPrice();
			}

			int endGold = fGold + (n - 1) * mazeConfig.getAddPrice();
			if (n == 1) {
				return endGold;
			}
			digAllGold = (int) ((fGold + endGold) * n * 0.5 * 0.8);
		}
		return digAllGold;
	}

	// 刷新宝库
	public void itemRefresh(RoleActivityMaze player, MazeConfig mazeConfig) {
		player.setGetTreasuryList(new ArrayList<Integer>());
		player.setDigCnt(0);
		player.setTreasuryMap(getTreasuryMap(mazeConfig));
		player.setDigFreeCnt(0);

		roleActivityMazeDAO().itemRefresh(player);
	}

	// 挖宝
	public void dig(RoleActivityMaze player, int num, MazeConfig mazeConfig, Map<String, ProbItem> getMap, Role doc)
			throws BizException {
		if (num == 1) {// 挖一个 || 挖全部 只剩一个
			if (player.getFreeTimes() > 0) {
				player.setFreeTimes(player.getFreeTimes() - 1);
				player.setDigFreeCnt(player.getDigFreeCnt() + 1);
			} else {
				player.setSurGoldTimes(player.getSurGoldTimes() - 1);
				int gold = getDigOneGold(player, mazeConfig);
				packetAO.removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, gold,
						RoleItemLog.SYS_精彩活动_迷宫寻宝_挖宝消耗, "");
			}
			digOne(player, getMap, doc);
		} else {// 挖全部
			int gold = getDigAllGold(player, mazeConfig);
			packetAO.removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, gold, RoleItemLog.SYS_精彩活动_迷宫寻宝_挖宝消耗,
					"");

			digAll(player, getMap, doc);
		}
	}

	// 挖全部
	private void digAll(RoleActivityMaze player, Map<String, ProbItem> getMap, Role doc) throws BizException {
		List<ProbItem> getProbAry = new ArrayList<ProbItem>();
		int cnt = 8 - player.getGetTreasuryList().size(); // 本次要挖几次
		for (int i = 0; i < player.getTreasuryMap().size(); i++) {
			if (!player.getGetTreasuryList().contains(i + 1)) { // 未挖过
				getMap.put((i + 1) + "", player.getTreasuryMap().get((i + 1) + ""));
				getProbAry.add(player.getTreasuryMap().get((i + 1) + ""));
			}
		}
		packetAO.addItem(doc, getProbAry, RoleItemLog.SYS_精彩活动_迷宫寻宝_挖宝获得, "");

		int digFreeCnt = player.getFreeTimes() >= cnt ? cnt : player.getFreeTimes();
		int freeTimes = player.getFreeTimes() - digFreeCnt;

		player.setDigCnt(player.getDigCnt() + cnt);
		player.setDigFreeCnt(digFreeCnt);// 挖取中使用免费次数
		player.setFreeTimes(freeTimes);
		player.setGetTreasuryList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
		player.setNextLine(0);
		player.setPositionList(new ArrayList<Integer>());
		player.setProbList(new ArrayList<Integer>());
		player.setSurGoldTimes(player.getSurGoldTimes() - (cnt - digFreeCnt));
	}

	// 随机挖宝一个
	private void digOne(RoleActivityMaze player, Map<String, ProbItem> getMap, Role doc) throws BizException {
		RouletteProb probData = getProbData(player);

		int sum = probSum(player.getProbList());
		int probNum = cacheManager.random(1, sum);
		int probCnt = 0;
		int itemKey = 0;
		for (int i = 0; i < player.getProbList().size(); i++) {
			probCnt += player.getProbList().get(i);
			if (probNum <= probCnt) {
				itemKey = player.getPositionList().get(i);
				getMap.put(itemKey + "", player.getTreasuryMap().get(itemKey + ""));
				packetAO.addItem(doc, player.getTreasuryMap().get(itemKey + ""), RoleItemLog.SYS_精彩活动_迷宫寻宝_挖宝获得, "");

				player.getGetTreasuryList().add(itemKey);
				player.getPositionList().remove(i);
				player.getProbList().remove(i);
				player.setDigCnt(player.getDigCnt() + 1);
				// position_add
				if (probData.getOutput().contains(itemKey)) {// 跳转
					RouletteProb newdata = cacheManager.getValueByKey(RouletteProb.class, probData.getResetID());
					player.setNextLine(probData.getResetID());
					listCopy(newdata, player);
					resetList(newdata, player);
				} else {// 累积次数
					if (player.getDigCnt() >= probData.getNumber()) {
						RouletteProb newdata = cacheManager.getValueByKey(RouletteProb.class, probData.getResetID());
						player.setNextLine(probData.getJumpID());
						listCopy(newdata, player);
						resetList(newdata, player);
					}
				}
				break;
			}
		}
	}

	// 拷贝list
	private void listCopy(RouletteProb newdata, RoleActivityMaze player) {
		player.setPositionList(new ArrayList<Integer>());
		player.setProbList(new ArrayList<Integer>());

		for (int i = 0; i < newdata.getPosition().size(); i++) {
			player.getPositionList().add(newdata.getPosition().get(i));
		}
		for (int i = 0; i < newdata.getProb().size(); i++) {
			player.getProbList().add(newdata.getProb().get(i));
		}
	}

	// 更新读取行数据
	private void resetList(RouletteProb data, RoleActivityMaze player) {
		for (Iterator<Integer> iterator = player.getPositionList().iterator(); iterator.hasNext();) {
			int curNum = (Integer) iterator.next();
			if (player.getGetTreasuryList().contains(curNum)) {
				int ind = player.getPositionList().indexOf(curNum);
				player.getProbList().remove(ind);
				iterator.remove();
			}
		}
	}

	// list 求和
	private int probSum(List<Integer> probList) {
		int sum = 0;
		for (int i = 0; i < probList.size(); i++) {
			sum += probList.get(i);
		}
		return sum;
	}

	private RouletteProb getProbData(RoleActivityMaze player) {
		RouletteProb data = null;
		if (player.getNextLine() == 0) {// 没有挖宝数据
			Map<Integer, RouletteProb> RouletteProbMap = cacheManager.getValues(RouletteProb.class);
			for (Entry<Integer, RouletteProb> entry : RouletteProbMap.entrySet()) {
				if (entry.getValue().getIsStart() == RouletteProb.初始位置
						&& entry.getValue().getType() == RouletteProb.ACTIVITY_MAZE) {
					data = entry.getValue();
					player.setNextLine(entry.getKey());
					listCopy(entry.getValue(), player);
					break;
				}
			}
		} else {
			data = cacheManager.getValueByKey(RouletteProb.class, player.getNextLine());
		}
		return data;
	}

}