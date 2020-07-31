package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.activity.roulette.RouletteItem;
import com.mdy.dzs.data.domain.activity.roulette.RouletteProb;
import com.mdy.dzs.game.domain.activity.ActivityConfig;
import com.mdy.dzs.game.domain.activity.roulettegame.RoleActivityRoulette;
import com.mdy.dzs.game.domain.activity.roulettegame.PreviewVO;
import com.mdy.dzs.game.domain.activity.roulettegame.RoleDataStateVO;
import com.mdy.dzs.game.domain.activity.roulettegame.Roulette;
import com.mdy.dzs.game.domain.activity.roulettegame.RouletteStateVO;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.DateUtil;

/**
 * 皇宫探宝数据表
 * 
 * @author 白雪林
 *
 */
public class RoleActivityRouletteAO extends BaseAO {
	private CacheManager cacheManager;
	private RoleActivityAO roleActivityAO;

	public RoleActivityRouletteAO(CacheManager cacheManager, RoleActivityAO roleActivityAO) {
		this.cacheManager = cacheManager;
		this.roleActivityAO = roleActivityAO;
	}

	// 查询并添加
	public RoleActivityRoulette queryAndAdd(int roleId, Roulette activeData) {
		RoleActivityRoulette player = roleActivityRouletteDAO().query(roleId);
		if (player == null) {
			roleActivityRouletteDAO().add(roleId);
			player = roleActivityRouletteDAO().query(roleId);
		}
		if (!DateUtil.isToday(player.getRefreshTime())) {// 隔天
			int addFreeTimes = activeData.getFreeCnt();
			if (activeData.getType() == Roulette.直接购买次数) {// 剩余次数不变
				if (player.getRefreshTime() == null) {
					player.setSurTimes(activeData.getLimitCnt());
				}
				if (player.getSurTimes() < addFreeTimes) {
					addFreeTimes = player.getSurTimes();
				}
				roleActivityRouletteDAO().dayRefreshBuy(addFreeTimes, player);

			} else {
				if (player.getFreeTimes() > 0) {
					player.setSurTimes(player.getSurTimes() - player.getFreeTimes());
				}
				roleActivityRouletteDAO().dayRefresh(addFreeTimes, player);
			}
			player = roleActivityRouletteDAO().query(roleId);
		}
		return player;
	}

	// 更新积分奖励list
	public void updateGetBox(RoleActivityRoulette rRoleDoc) {
		roleActivityRouletteDAO().updateGetBox(rRoleDoc);
	}

	// 探宝后更新数据
	public void updateRouletteData(RoleActivityRoulette rRoleDoc) {
		roleActivityRouletteDAO().updateRouletteData(rRoleDoc);
	}

	// 当前使用的活动配置
	public Roulette getCurActivityData() {
		Roulette rouletteData = roleActivityAO.getRouletteDatas();
		if (rouletteData != null) {
			return rouletteData;
		}
		return null;
	}

	// 玩家数据
	public RoleDataStateVO tidyRoleState(RoleActivityRoulette rRoleDoc, Roulette activeData) {
		String activeTime = roleActivityAO.getRouletteActiveTime(); // 活动时段
		Long countDown = getCountDown(activeTime);
		int credit = rRoleDoc.getCredit();
		int surTimes = rRoleDoc.getSurTimes();
		List<Integer> getBox = rRoleDoc.getGetBox();
		int freeTimes = rRoleDoc.getFreeTimes();
		int dayAdd = rRoleDoc.getDayAdd();
		int rouletteTimes = rouletteTimes(rRoleDoc, activeData);

		String timeDis_[] = activeTime.split("_");
		Date startT = DateUtil.getDateTimeByString(timeDis_[0]);
		Date endT = DateUtil.getDateTimeByString(timeDis_[1]);

		return new RoleDataStateVO(startT, endT, countDown, credit, surTimes, getBox, freeTimes, dayAdd, rouletteTimes);
	}

	// 整理剩余次数
	public int rouletteTimes(RoleActivityRoulette rRoleDoc, Roulette activeData) {
		int allRouletteTims = (int) rRoleDoc.getDayAdd() / activeData.getPrice();
		// 上限不为0，则有上限 查上限
		if (activeData.getLimitCnt() != Roulette.已获得次数无上限 && allRouletteTims > activeData.getLimitCnt()) {
			allRouletteTims = activeData.getLimitCnt();
		}
		return allRouletteTims;
	}

	// 据活动结束秒数 > 1000 距离结束时间大于1s
	public Long getCountDown(String activeTime) {
		String timeDis[] = activeTime.split("_");
		Date endTime = DateUtil.getDateTimeByString(timeDis[1]);
		Long countDown = endTime.getTime() - new Date().getTime();

		return countDown > 1000 ? countDown : 0;
	}

	// 圆盘显示数据
	public List<RouletteStateVO> tidyRouletteState() {
		List<RouletteStateVO> rouletteStateVO = new ArrayList<RouletteStateVO>();
		Map<String, Integer> positionMap = roleActivityAO.getRouletteDatas().getPositionMap();

		for (Entry<String, Integer> entry : positionMap.entrySet()) {
			RouletteItem itemLine = cacheManager.getValueByKey(RouletteItem.class, entry.getValue());
			rouletteStateVO.add(new RouletteStateVO(Integer.parseInt(entry.getKey()), itemLine.getItemDisplay()));
		}
		return rouletteStateVO;
	}

	// 获取预览数据
	public PreviewVO getPreviewData(int id) {
		RouletteItem rouletteItemData = new RouletteItem();
		Map<String, Integer> positionMap = roleActivityAO.getRouletteDatas().getPositionMap();

		rouletteItemData = cacheManager.getValueByKey(RouletteItem.class, positionMap.get(id + ""));
		return new PreviewVO(rouletteItemData.getItemType(), rouletteItemData.getItemId(),
				rouletteItemData.getItemCnt());
	}

	// 当前要使用的概率行
	public RouletteProb getProbData(RoleActivityRoulette rRoleDoc) {
		RouletteProb data = null;
		if (rRoleDoc.getNextPropLine() == 0) {// 没有探宝过
			Map<Integer, RouletteProb> RouletteProbMap = cacheManager.getValues(RouletteProb.class);
			for (Entry<Integer, RouletteProb> entry : RouletteProbMap.entrySet()) {
				if (entry.getValue().getIsStart() == RouletteProb.初始位置
						&& entry.getValue().getType() == RouletteProb.ACTIVITY_ROULETTE) {
					data = entry.getValue();
				}
			}
		} else {
			data = cacheManager.getValueByKey(RouletteProb.class, rRoleDoc.getNextPropLine());
		}
		return data;
	}

	// 更新探宝后玩家数据
	public void udpateRouletteRoleDoc(RoleActivityRoulette rRoleDoc, RouletteProb probData, int itemkey,
			Roulette activeData) {
		rRoleDoc.setCredit(rRoleDoc.getCredit() + 1);
		rRoleDoc.setSurTimes(rRoleDoc.getSurTimes() - 1);
		rRoleDoc.setRouletteTimes(rRoleDoc.getRouletteTimes() + 1);
		rRoleDoc.setAllRouletteCnt(rRoleDoc.getAllRouletteCnt() + 1);
		if (rRoleDoc.getFreeTimes() > 0) {
			rRoleDoc.setFreeTimes(rRoleDoc.getFreeTimes() - 1);
		}
		// position_add
		if (probData.getOutput().contains(itemkey)) {// 跳转
			rRoleDoc.setPositionAdd(0);
			rRoleDoc.setNextPropLine(probData.getResetID());
		} else {// 累积次数
			rRoleDoc.setPositionAdd(rRoleDoc.getPositionAdd() + 1);
			if (rRoleDoc.getPositionAdd() >= probData.getNumber()) {
				rRoleDoc.setNextPropLine(probData.getJumpID());
			}
			if (rRoleDoc.getNextPropLine() == 0) {// 初始状态
				rRoleDoc.setNextPropLine(probData.getId());
			}
		}
	}

	// 更新金币消耗或充值累加
	public void updateDayAdd(int gold, Role doc, int activeType) {
		// 活动开启即做累加
		int open = RoleActivityAO.checkOpen(roleActivityAO.getMapActivitys().get(ActivityConfig.ACTIVITY_皇宫探宝));
		if (open == 1) {
			Roulette activeData = getCurActivityData();
			// 获取活动配置数据 活动类型为充值或消费返利
			if (activeData != null && activeData.getType() == activeType) {
				RoleActivityRoulette player = queryAndAdd(doc.getId(), activeData);
				// 上限0，则无上限
				int surGold = (int) player.getDayAdd() % activeData.getPrice();
				int exTimes = (int) player.getDayAdd() / activeData.getPrice();
				int addTimes = (int) (gold + surGold) / activeData.getPrice();
				if (activeData.getLimitCnt() != Roulette.已获得次数无上限 // 有上限
						&& (exTimes + addTimes) > activeData.getLimitCnt()) {
					addTimes = activeData.getLimitCnt() - exTimes > 0 ? activeData.getLimitCnt() - exTimes : 0;
				}
				player.setDayAdd(player.getDayAdd() + gold);
				player.setSurTimes(player.getSurTimes() + addTimes);

				roleActivityRouletteDAO().dayAddUpdate(player);
			}
		}
	}

	public Map<String, Integer> getShowDatas() {
		return roleActivityAO.getRouletteDatas().getPositionMap();
	}

	// 概率获得物品的List
	public int getProbItemInd(RouletteItem itemData) {
		int probNum = cacheManager.random(1, 10000);
		int cnt = 0;
		for (int i = 0; i < itemData.getItemProb().size(); i++) {
			cnt += itemData.getItemProb().get(i);
			if (probNum < cnt) {
				return i;
			}
		}
		return itemData.getItemProb().get(itemData.getItemProb().size() - 1);
	}

}