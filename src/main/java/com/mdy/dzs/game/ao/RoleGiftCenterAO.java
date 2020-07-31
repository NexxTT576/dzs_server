package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.DataAction;
import com.mdy.dzs.data.domain.boss.Boss;
import com.mdy.dzs.data.domain.boss.Bossguwu;
import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.data.domain.gift.IGift;
import com.mdy.dzs.data.domain.gift.LevelGift;
import com.mdy.dzs.data.domain.gift.LoginDayGift;
import com.mdy.dzs.data.domain.gift.OnlineGift;
import com.mdy.dzs.data.domain.gift.SignGift;
import com.mdy.dzs.data.domain.gift.YueKaShouChongGift;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.mission.ActivityMissionProperty;
import com.mdy.dzs.data.domain.reward.RewardCenter;
import com.mdy.dzs.data.domain.union.QingLongBoss;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.domain.giftcenter.RoleGift;
import com.mdy.dzs.game.domain.giftcenter.RoleGiftStatus;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.tournament.RoleTourAwardVO;
import com.mdy.dzs.game.domain.union.UnionBoss;
import com.mdy.dzs.game.domain.vip.RoleFirstRecharge;
import com.mdy.dzs.game.exception.DataException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

/**
 * 领奖中心
 * 
 * @author 房曈
 *
 */
public class RoleGiftCenterAO extends BaseAO {
	//
	protected DataAction dataAction = Container.get().createRemote(DataAction.class,
			DataApplication.CLUSTER_DATA_SYSTEM);
	//

	private final int giftDeleteDiffDay = 14;
	private PacketAO packetAO;
	private MissionAO missionAO;
	private CacheManager cacheManager;

	/** level,gift */
	private Map<Integer, LevelGift> levelGiftMap = new LinkedHashMap<Integer, LevelGift>();
	/** cnt,gift */
	private Map<Integer, OnlineGift> onlineGiftMap = new LinkedHashMap<Integer, OnlineGift>();
	private Map<Integer, SignGift> signGiftMap = new LinkedHashMap<Integer, SignGift>();
	private int maxOnlineCnt = 0;

	/** day,gift */
	private Map<Integer, LoginDayGift> loginDayGiftMap = new LinkedHashMap<Integer, LoginDayGift>();

	public RoleGiftCenterAO(PacketAO packetAO, MissionAO missionAO, CacheManager cacheManager) {
		this.cacheManager = cacheManager;
		this.packetAO = packetAO;
		this.missionAO = missionAO;
	}

	@Override
	public void start() {
		reload();
		super.start();
	}

	public void reload() {
		// leve
		Map<Integer, LevelGift> newlevelGiftMap = new LinkedHashMap<Integer, LevelGift>();
		List<LevelGift> levelGifts = dataAction.queryLevelGiftDatas();
		for (LevelGift levelGift : levelGifts) {
			if (levelGift.getLevel() == 0)
				continue;
			convGift(levelGift);
			newlevelGiftMap.put(levelGift.getLevel(), levelGift);
		}
		levelGiftMap = newlevelGiftMap;
		// online
		onlineGiftMap.clear();
		List<OnlineGift> onlineGifts = dataAction.queryOnlineGiftDatas();
		for (OnlineGift onlineGift : onlineGifts) {
			convGift(onlineGift);
			onlineGiftMap.put(onlineGift.getId(), onlineGift);
			if (onlineGift.getId() > maxOnlineCnt)
				maxOnlineCnt = onlineGift.getId();
		}
		// sign
		signGiftMap.clear();
		List<SignGift> signGifts = dataAction.querySignGiftDatas();
		for (SignGift signGift : signGifts) {
			convGift(signGift);
			signGiftMap.put(signGift.getId(), signGift);
		}
		// loginday
		loginDayGiftMap.clear();
		List<LoginDayGift> loginDayGifts = dataAction.queryLoginDayGiftDatas();
		for (LoginDayGift loginDayGift : loginDayGifts) {
			convGift(loginDayGift);
			loginDayGiftMap.put(loginDayGift.getId(), loginDayGift);
		}
	}

	/**
	 * 清除
	 */
	public void clearExpiredGift() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -giftDeleteDiffDay);
		roleGiftCenterDAO().clearExpiredGift(calendar.getTime());
	}

	/**
	 * 查询列表
	 */
	public List<RoleGift> queryListByRoleId(int roleId) {
		return roleGiftCenterDAO().queryList(roleId);
	}

	public int queryCountByRoleId(int roleId) {
		return roleGiftCenterDAO().queryCount(roleId);
	}

	public RoleGift queryRoleGiftById(int id) {
		if (id == 0)
			return null;
		return roleGiftCenterDAO().query(id);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 获取可领奖玩家
	 * 
	 * @param rc
	 * @return
	 */
	public List<Integer> getRewardRoleIds(RewardCenter rc) {
		List<Integer> res = new ArrayList<Integer>();
		switch (rc.getCondition()) {
		case RewardCenter.CONDITION_竞技场排名区间:
			res = roleArenaDAO().queryRoldIdByRankInterval(rc.getParams().get(0), rc.getParams().get(1));
			break;
		case RewardCenter.CONDITION_等级区间:
			res = roleDAO().queryRoldIdByLevelInterval(rc.getParams().get(0), rc.getParams().get(1));
			break;
		case RewardCenter.CONDITION_等级排名区间:
			res = roleDAO().queryRoldIdByLevelRankInterval(rc.getParams().get(0), rc.getParams().get(1));
			break;
		}
		return res;
	}

	public void sendRewardCenterGift(int roleId, RewardCenter rc) {
		RoleGift rg = new RoleGift();
		rg.setGiftType(RoleGift.GIFT_TYPE_奖励中心);
		rg.setGiftItem(rc.getRewardItem());
		rg.setOtherData(rc.getRewardMsg());
		rg.setRoleId(roleId);
		roleGiftCenterDAO().add(rg);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 发送竞技场奖励到领奖中心
	 * 
	 * @param roleId
	 * @param silver
	 * @param popual
	 * @param rank
	 * @param time
	 */
	public void sendArenaGift(RoleArena arena, Date time) {
		RoleGift rg = new RoleGift();
		rg.setGiftType(RoleGift.GIFT_TYPE_竞技场);
		List<GiftItem> items = new ArrayList<GiftItem>();

		int silvers = missionAO.getActivityMissionCntByProperty(arena.getRoleId(),
				ActivityMissionProperty.竞技场_银币翻倍.value());
		int popuals = missionAO.getActivityMissionCntByProperty(arena.getRoleId(),
				ActivityMissionProperty.竞技场_声望翻倍.value());

		items.add(new GiftItem(7, 2, arena.getAwardSilver() * silvers));
		items.add(new GiftItem(7, 5, arena.getAwardPopual() * popuals));
		rg.setGiftItem(items);
		rg.setOtherData(arena.getRank() + "");
		rg.setRoleId(arena.getRoleId());
		roleGiftCenterDAO().add(rg);
	}

	/**
	 * 发送首充礼包奖励到领奖中心
	 * 
	 * @throws BizException
	 */
	public void sendFirstRechargeGift(RoleFirstRecharge first, Date time) throws BizException {
		RoleGift rg = new RoleGift();
		rg.setGiftType(RoleGift.GIFT_TYPE_首充礼包);
		List<GiftItem> items = new ArrayList<GiftItem>();
		YueKaShouChongGift giftList = cacheManager.getExistValueByKey(YueKaShouChongGift.class, 1);

		List<Integer> type = giftList.getArrType();
		List<Integer> item = giftList.getArrItem();
		List<Integer> num = giftList.getArrNum();

		for (int i = 0; i < type.size(); i++) {
			items.add(new GiftItem(type.get(i), item.get(i), num.get(i)));
		}
		rg.setOtherData("");
		rg.setGiftItem(items);
		rg.setRoleId(first.getRoleId());
		roleGiftCenterDAO().add(rg);
	}

	/** 发送烛龙殿奖励到领奖中心 */
	public void sendBossGift(List<RankList> rankDatas) throws BizException {
		Map<Integer, Boss> bDatas = cacheManager.getValues(Boss.class);// Boss发奖表
		for (Entry<Integer, Boss> entry : bDatas.entrySet()) {
			if (entry.getValue().getMin() > rankDatas.size()) {
				break;
			}
			Boss bossItem = entry.getValue();
			int min = bossItem.getMin();
			int max = bossItem.getMax() > rankDatas.size() ? rankDatas.size() : bossItem.getMax();
			int prob = bossItem.getProb();

			for (int i = min; i <= max; i++) {
				RankList rankData = rankDatas.get(i - 1);
				if (rankData.getTotalHurt() > 0) {// 有伤害
					RoleGift rg = new RoleGift();
					rg.setGiftType(RoleGift.GIFT_TYPE_烛龙殿活动奖励);
					List<GiftItem> items = new ArrayList<GiftItem>();

					int silvers = missionAO.getActivityMissionCntByProperty(rankData.getId(),
							ActivityMissionProperty.世界BOSS_银币翻倍.value());
					int popuals = missionAO.getActivityMissionCntByProperty(rankData.getId(),
							ActivityMissionProperty.世界BOSS_声望翻倍.value());

					int coinNum = (int) Math
							.floor((bossItem.getFix1() - (bossItem.getRatio1() * i)) * rankData.getLv());
					int popualNum = Math.round(bossItem.getFix2() - (bossItem.getRatio2() * i));
					items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_银币, coinNum * silvers));
					items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_声望, popualNum * popuals));
					List<ProbItem> rtnItemAry = null;
					rtnItemAry = cacheManager.probGot(prob);
					for (ProbItem item : rtnItemAry) {
						items.add(new GiftItem(item.getT(), item.getId(), item.getN()));
					}
					rg.setOtherData("");
					rg.setGiftItem(items);
					rg.setRoleId(rankData.getId());
					roleGiftCenterDAO().add(rg);
				}
			}
		}
	}

	/** 发送击杀烛龙奖励到领奖中心 */
	public void sendBossKillerGift(int roleId, int level) {
		RoleGift rg = new RoleGift();
		rg.setGiftType(RoleGift.GIFT_TYPE_击杀烛龙奖励);
		List<GiftItem> items = new ArrayList<GiftItem>();
		Bossguwu guwuData = cacheManager.getValueByKey(Bossguwu.class, 1);// bossguwu表
		items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_银币, guwuData.getYinbi() * level));
		items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_声望, guwuData.getShengwang()));
		List<ProbItem> rtnItemAry = null;
		rtnItemAry = cacheManager.probGot(guwuData.getProb());
		for (ProbItem item : rtnItemAry) {
			items.add(new GiftItem(item.getT(), item.getId(), item.getN()));
		}
		rg.setOtherData("");
		rg.setGiftItem(items);
		rg.setRoleId(roleId);
		roleGiftCenterDAO().add(rg);
	}

	// 发送青龙殿奖励到领奖中心
	public void sendUnionDragonGift(List<RankList> rankDatas, int dragonLevel) {
		Map<Integer, QingLongBoss> bDatas = cacheManager.getValues(QingLongBoss.class);// Boss发奖表
		for (Entry<Integer, QingLongBoss> entry : bDatas.entrySet()) {
			if (entry.getValue().getMin() > rankDatas.size()) {
				break;
			}
			QingLongBoss bossItem = entry.getValue();
			int min = bossItem.getMin();
			int max = bossItem.getMax() > rankDatas.size() ? rankDatas.size() : bossItem.getMax();
			int prob = bossItem.getProb();

			int coinNum = (int) Math.floor(bossItem.getFix1() + dragonLevel * bossItem.getRatio1());
			int popualNum = Math.round(bossItem.getFix2() + dragonLevel * bossItem.getRatio2());
			int bangGong = Math.round(bossItem.getFix3() + dragonLevel * bossItem.getRatio3());

			for (int i = min; i <= max; i++) {
				RankList rankData = rankDatas.get(i - 1);
				if (rankData.getTotalHurt() > 0) {// 有伤害
					RoleGift rg = new RoleGift();
					rg.setGiftType(RoleGift.GIFT_TYPE_青龙堂活动奖励);
					List<GiftItem> items = new ArrayList<GiftItem>();

					items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_银币, coinNum));
					items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_声望, popualNum));
					items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_贡献, bangGong));

					List<ProbItem> rtnItemAry = null;
					rtnItemAry = cacheManager.probGot(prob);
					for (ProbItem item : rtnItemAry) {
						items.add(new GiftItem(item.getT(), item.getId(), item.getN()));
					}
					rg.setOtherData("");
					rg.setGiftItem(items);
					rg.setRoleId(rankData.getId());
					roleGiftCenterDAO().add(rg);
				}
			}
		}

	}

	// 发送青龙殿击杀奖到领奖中心
	public void sendUnionDragonKillerGift(UnionBoss unionDragonData) {
		RoleGift rg = new RoleGift();
		rg.setGiftType(RoleGift.GIFT_TYPE_击败青龙奖励);
		List<GiftItem> items = new ArrayList<GiftItem>();
		Bossguwu guwuData = cacheManager.getValueByKey(Bossguwu.class, 2);// bossguwu表
		items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_银币,
				guwuData.getYinbi() + unionDragonData.getLevel() * guwuData.getRatio()));
		items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_声望, guwuData.getShengwang()));
		items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_贡献, guwuData.getBanggong()));

		List<ProbItem> rtnItemAry = null;
		rtnItemAry = cacheManager.probGot(guwuData.getProb());
		for (ProbItem item : rtnItemAry) {
			items.add(new GiftItem(item.getT(), item.getId(), item.getN()));
		}
		rg.setOtherData("");
		rg.setGiftItem(items);
		rg.setRoleId(unionDragonData.getKiller());
		roleGiftCenterDAO().add(rg);
	}

	/**
	 * 转换奖励
	 * 
	 * @param gift
	 * @return
	 */
	public void convGift(IGift gift) {
		List<GiftItem> res = new ArrayList<GiftItem>();
		int count = gift.getArrType().size();
		for (int i = 0; i < count; i++) {
			int type = gift.getArrType().get(i);
			int id = gift.getArrItem().get(i);
			int num = gift.getArrNum().get(i);
			if (type == 1) {
				Item item = cacheManager.getValueByKey(Item.class, id);
				if (item == null)
					continue;
				type = item.getType();
			} else if (type == 2) {
				type = 8;
			}
			res.add(new GiftItem(type, id, num));
		}
		gift.setItem(res);
	}

	/**
	 * 领奖
	 * 
	 * @param doc
	 * @param list
	 * @throws BizException
	 */
	public void getGift(Role doc, List<RoleGift> list) throws BizException {
		List<Integer> deleteIds = new ArrayList<Integer>();
		for (RoleGift roleGift : list) {
			sendGift(doc, roleGift.getGiftItem(), RoleItemLog.SYS_礼包_领奖中心,
					roleGift.getGiftType() + ":" + roleGift.getOtherData());
			deleteIds.add(roleGift.getId());
		}
		roleGiftCenterDAO().delete(deleteIds);
	}

	/**
	 * 增加奖励
	 * 
	 * @param doc
	 * @param items
	 * @throws BizException
	 */
	public void sendGift(Role doc, List<GiftItem> items, int sys, String comments) throws BizException {
		for (GiftItem item : items) {
			packetAO.addItem(doc, item.getType(), item.getId(), item.getNum(), sys, comments);
		}
	}

	public RoleGiftStatus queryGiftStatus(int roleId) {
		RoleGiftStatus res = roleGiftStatusDAO().query(roleId);
		if (res == null) {
			roleGiftStatusDAO().add(roleId);
			res = roleGiftStatusDAO().query(roleId);
		}
		if (!DateUtil.isToday(res.getGiftDayRefresh())) {
			roleGiftStatusDAO().updateDayRefresh(roleId);
			res = roleGiftStatusDAO().query(roleId);
		}
		return res;
	}

	public Map<Integer, LevelGift> getLevelGiftMap() {
		return levelGiftMap;
	}

	public Map<Integer, OnlineGift> getOnlineGiftMap() {
		return onlineGiftMap;
	}

	public Map<Integer, SignGift> getSignGiftMap() {
		return signGiftMap;
	}

	public Map<Integer, LoginDayGift> getLoginDayGiftMap() {
		return loginDayGiftMap;
	}

	///////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 领取等级礼包奖励
	 * 
	 * @param doc
	 * @param status
	 * @param lv
	 * @throws BizException
	 */
	public void getLevelGift(Role doc, RoleGiftStatus status, int lv) throws BizException {
		LevelGift lvData = levelGiftMap.get(lv);
		if (lvData == null) {
			throw DataException.getException(DataException.EXCE_DATA_IS_NOT_EXIST, LevelGift.class.getName(), lv);
		}
		status.getGiftLvGot().add(lv);
		sendGift(doc, lvData.getItem(), RoleItemLog.SYS_礼包_等级礼包, "");
		roleGiftStatusDAO().updateLevelGift(status);
	}

	/**
	 * 获取可领等级礼包数
	 * 
	 * @param doc
	 * @param status
	 * @return
	 */
	public int getBeGetLevelCount(Role doc, RoleGiftStatus status) {
		int levelGifts = 0;
		List<Integer> giftLvGot = status.getGiftLvGot();
		for (Entry<Integer, LevelGift> entry : levelGiftMap.entrySet()) {
			if (doc.getLevel() >= entry.getKey()) {
				if (giftLvGot.indexOf(entry.getKey()) < 0) {
					levelGifts++;
				}
			}
		}
		return levelGifts;
	}
	///////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 获取可签约天 如果为0则已签到过
	 * 
	 * @param status
	 * @return
	 */
	public int getBeSignDay(RoleGiftStatus status) {
		int day = 0;
		do {
			if (status.getGiftSignTime() == null || status.getGiftSignCnt() == 0) {
				day = 1;
				break;
			}
			if (DateUtil.isToday(status.getGiftSignTime())) {
				break;
			}
			if (DateUtil.isYesterday(status.getGiftSignTime())) {
				day = status.getGiftSignCnt();
				if (day < signGiftMap.size()) {
					day += 1;
				} else {
					day = 7;
				}
			} else {
				day = 1;
			}
		} while (false);
		return day;
	}

	public void getSignGift(Role doc, RoleGiftStatus status, int day) throws BizException {
		SignGift signData = signGiftMap.get(day);
		if (signData == null) {
			throw DataException.getException(DataException.EXCE_DATA_IS_NOT_EXIST, SignGift.class.getName(), day);
		}
		status.setGiftSignCnt(day);
		status.setGiftSignTime(new Date());
		sendGift(doc, signData.getItem(), RoleItemLog.SYS_礼包_签到礼包, "");
		roleGiftStatusDAO().updateSignGift(status);

	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	public void updateOnLineGift(RoleGiftStatus status) {
		roleGiftStatusDAO().updateOnlineGift(status);
	}

	public int getMaxOnlineCnt() {
		return maxOnlineCnt;
	}

	public void getOnlineGift(Role doc, RoleGiftStatus status, OnlineGift onlineGift) throws BizException {
		status.setGiftOnlineGot(onlineGift.getId());
		status.setGiftOnlineTime(new Date());
		status.setGiftOnlineTimeAcc(0);
		sendGift(doc, onlineGift.getItem(), RoleItemLog.SYS_礼包_在线礼包, "");
		roleGiftStatusDAO().updateOnlineGift(status);

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	public void getLoginDayGift(Role doc, RoleGiftStatus status, int day) throws BizException {
		LoginDayGift giftData = loginDayGiftMap.get(day);
		if (giftData == null) {
			throw DataException.getException(DataException.EXCE_DATA_IS_NOT_EXIST, LoginDayGift.class.getName(), day);
		}
		status.getGiftLogindayGot().add(day);
		sendGift(doc, giftData.getItem(), RoleItemLog.SYS_礼包_开服礼包, "");
		roleGiftStatusDAO().updateLoginDayGift(status);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 发送比武奖励到领奖中心
	 * 
	 * @param roleId
	 * @param silver
	 * @param popual
	 * @param rank
	 * @param time
	 */
	public void sendTournamentGift(RoleTourAwardVO roleAward) {
		RoleGift rg = new RoleGift();
		rg.setGiftType(RoleGift.GIFT_TYPE_比武天榜奖励);
		rg.setGiftItem(roleAward.getAwards());
		rg.setOtherData(roleAward.getRank() + "");
		rg.setRoleId(roleAward.getRoleId());
		roleGiftCenterDAO().add(rg);
	}
}