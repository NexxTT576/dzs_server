/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.activity.limitshop.LimitShopData;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.touzi.TouZi;
import com.mdy.dzs.data.domain.yueqian.YueQian;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleActivityAction;
import com.mdy.dzs.game.domain.activity.ActivityConfig;
import com.mdy.dzs.game.domain.activity.guessgame.ActivityGuessBuyVO;
import com.mdy.dzs.game.domain.activity.guessgame.ActivityGuessChooseVO;
import com.mdy.dzs.game.domain.activity.guessgame.ActivityGuessingGameVO;
import com.mdy.dzs.game.domain.activity.guessgame.RoleActivityGuess;
import com.mdy.dzs.game.domain.activity.happygame.ActivityHappyStatusVO;
import com.mdy.dzs.game.domain.activity.happygame.RoleActivityHappy;
import com.mdy.dzs.game.domain.activity.limitcard.LimitCardVO;
import com.mdy.dzs.game.domain.activity.limitshop.LSExchangeVO;
import com.mdy.dzs.game.domain.activity.limitshop.LimitShop;
import com.mdy.dzs.game.domain.activity.limitshop.LimitShopInfo;
import com.mdy.dzs.game.domain.activity.limitshop.LimitShopVO;
import com.mdy.dzs.game.domain.activity.limitshop.RoleLimitShop;
import com.mdy.dzs.game.domain.activity.limitshop.ToolsVO;
import com.mdy.dzs.game.domain.activity.monthsigngame.MonthSignStatusVO;
import com.mdy.dzs.game.domain.activity.monthsigngame.RoleActivityMonthSign;
import com.mdy.dzs.game.domain.activity.touzigame.RoleActivityTouzi;
import com.mdy.dzs.game.domain.activity.touzigame.TouZiStatusVO;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.prob.RolePseudo;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleStatistics;
import com.mdy.dzs.game.domain.system.SystemStatus;
import com.mdy.dzs.game.exception.ActivityException;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.util.BehaviorLogger;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 活动
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月24日 下午8:55:53
 */
public class RoleActivityActionImpl extends ApplicationAwareAction implements RoleActivityAction {

	@Override
	public List<ActivityConfig> queryActivityConfigs() {
		return roleActivityAO().queryActivityConfigs();
	}

	@Override
	public Map<Integer, Integer> queryActivityStatusByAcc(String acc, Map<Integer, Integer> activityStatus)
			throws BizException {
		if (activityStatus.containsKey(ActivityConfig.ACTIVITY_投资计划)
				&& activityStatus.get(ActivityConfig.ACTIVITY_投资计划) == 1) {
			Role doc = roleAO().queryExistAccount(acc);
			RoleActivityTouzi roleTouziDoc = roleActivityTouziAO().query(doc.getId());

			if (roleTouziDoc != null
					&& roleTouziDoc.getGetLevelList().size() >= cacheManager().getValues(TouZi.class).size()) {
				activityStatus.put(ActivityConfig.ACTIVITY_投资计划, 0);
			}
		}
		return activityStatus;
	}

	@Override
	public void reloadActivitys() {
		roleActivityAO().reloadActivitys();
	}

	/////////////////////////////////////// guess
	/////////////////////////////////////// game////////////////////////////////////////////////

	@Override
	public RoleActivityGuess queryGuessInfos(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Date endData = roleActivityAO().getEndTime(ActivityConfig.ACTIVITY_酒剑仙);
		RoleActivityGuess res = roleActivityAO().queryActivityGuessInfos(doc);
		res.setEndTime(endData != null ? (endData.getTime() - new Date().getTime()) : 0);
		return res;
	}

	@Override
	public ActivityGuessingGameVO guessGame(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityGuess guess = roleActivityAO().queryActivityGuessInfos(doc);
		if (guess.getGuessCount() == 0) {
			// throw BaseException.getGlobalException(
			// "can not guess:no count");
			throw BaseException.getException(ActivityException.EXCE_CAIQUAN_CANNOT_GUESS);
		}
		int win = roleActivityAO().guessGame(doc, guess);
		if (win == 1) {
			guess.setChooseCount(guess.getChooseCount() + 1);
		}
		guess.setGuessCount(guess.getGuessCount() - 1);
		guess.setAllGuessCount(guess.getAllGuessCount() + 1);
		roleActivityAO().updateActivityGuess(guess);

		ActivityGuessingGameVO vo = new ActivityGuessingGameVO();
		vo.setWin(win);
		vo.setChooseCount(guess.getChooseCount());
		vo.setGuessCount(guess.getGuessCount());
		return vo;
	}

	@Override
	public ActivityGuessChooseVO guessChoose(String acc, int pos) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityGuess guess = roleActivityAO().queryActivityGuessInfos(doc);
		if (guess.getChooseCount() == 0) {
			// throw BaseException.getGlobalException(
			// "can not choose:no count");
			throw BaseException.getException(ActivityException.EXCE_CAIQUAN_CANNOT_GUESS);
		}
		if (guess.getChoosePosList().containsKey(pos + "")) {
			// throw BaseException.getGlobalException(
			// "can not select pos:"+pos);
			throw BaseException.getException(ActivityException.EXCE_CAIQUAN_CANNOT_SELECT_POS);
		}
		int itemPos = roleActivityAO().guessChoose(doc, guess);
		guess.getChoosePosList().put(pos + "", itemPos);
		guess.setChooseCount(guess.getChooseCount() - 1);
		ProbItem item = guess.getItemList().get(itemPos);
		packetAO().addItem(doc, item, RoleItemLog.SYS_精彩活动_酒剑仙_翻牌, "");
		roleActivityAO().updateActivityGuess(guess);

		ActivityGuessChooseVO vo = new ActivityGuessChooseVO();
		vo.setPos(pos);
		vo.setItem(item);
		vo.setIndex(itemPos);
		vo.setChooseCnt(guess.getChooseCount());
		return vo;
	}

	@Override
	public ActivityGuessBuyVO guessBuyCount(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityGuess guess = roleActivityAO().queryActivityGuessInfos(doc);
		if (guess.getGuessCount() > 0 || roleActivityAO().getWinCount(guess) >= RoleActivityGuess.MAX_ITEM_CNT) {
			// throw BaseException.getGlobalException("can not buy");
			throw BaseException.getException(ActivityException.EXCE_CAIQUAN_CANNOT_BUY);
		}

		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, guess.getBuyGold(),
				RoleItemLog.SYS_精彩活动_酒剑仙_猜拳, "");
		guess.setGuessCount(1);
		guess.setBuyCount(guess.getBuyCount() + 1);
		roleActivityAO().updateActivityGuess(guess);
		ActivityGuessBuyVO res = new ActivityGuessBuyVO();
		res.setGold(doc.getGold());
		res.setSpend(guess.getBuyGold());
		return res;
	}

	/////////////////////////////////////// 累积登录奖励 7天乐
	/////////////////////////////////////// 天天乐////////////////////////////////////////////////

	// 累积登录礼品列表
	@Override
	public Map<String, Map<String, List<ProbItem>>> happyGift(String acc) throws BizException {
		Map<String, List<ProbItem>> item = roleActivityAO().getHappyItems();
		Map<String, Map<String, List<ProbItem>>> rtnObj = new HashMap<String, Map<String, List<ProbItem>>>();
		rtnObj.put("listObj", item);

		return rtnObj;
	}

	// 累积登录状态
	@Override
	public ActivityHappyStatusVO happyStatus(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc); // 玩家
		RoleActivityHappy happyRole = roleActivityHappyAO().queryAdd(doc.getId()); // 玩家累积登录数据

		List<Integer> hasGet = happyRole.getGetList(); // 已领取天数数组
		int days = happyRole.getLoginCnt(); // 已登录天数
		String[] activeTime = roleActivityAO().getHappyActiveTime(); // 活动时段
		ActivityHappyStatusVO happyStatus = new ActivityHappyStatusVO(hasGet, days, activeTime);

		return happyStatus;
	}

	@Override
	public Map<String, Integer> happyGet(String acc, Integer day) throws BizException {
		Role doc = roleAO().queryExistAccount(acc); // 玩家
		RoleActivityHappy happyRole = roleActivityHappyAO().queryAdd(doc.getId()); // 玩家累积登录数据
		if (happyRole.getGetList().contains(day)) {// 已经领取过
			throw BaseException.getException(ActivityException.EXCE_HAPPY_HAS_GOT, day);
		}
		if (day > happyRole.getLoginCnt()) {// 未累积到天数
			throw BaseException.getException(ActivityException.EXCE_HAPPY_DAYCNT_LACT, day, happyRole.getLoginCnt());
		}

		List<ProbItem> items = roleActivityAO().getDayitems(day);
		if (items == null) {
			throw BaseException.getException(ActivityException.EXCE_HAPPY_NOGIFT_TODAY, day);
		}
		for (int i = 0; i < items.size(); i++) {
			packetAO().addItem(doc, items.get(i).getT(), items.get(i).getId(), items.get(i).getN(),
					RoleItemLog.SYS_精彩活动_累积登录礼包, "" + items.get(i).getId());
		}
		List<Integer> getList = happyRole.getGetList();
		getList.add(day);
		roleActivityHappyAO().updateGetList(getList, doc.getId());

		Map<String, Integer> rtnObj = new HashMap<String, Integer>();
		rtnObj.put("gold", doc.getGold());
		rtnObj.put("silver", doc.getSilver());
		rtnObj.put("result", 1);
		return rtnObj;
	}

	/////////////////////////////////////// 月签
	/////////////////////////////////////// ////////////////////////////////////////////////

	@Override
	public MonthSignStatusVO monthSignStatus(String acc) throws BizException {
		// 没有记录则新建号--新的月份reset玩家新一个月的数据
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityMonthSign mSignRole = roleActivityMonthSignAO().queryAdd(doc.getId()); // 玩家月签数据
		Date now = new Date();
		Map<Integer, YueQian> yueQianMonthDatas = roleActivityMonthSignAO().getYueQianDatas();
		int dayNum = roleActivityMonthSignAO().getMax(mSignRole, yueQianMonthDatas);// 可以领取天数上限

		MonthSignStatusVO rtnObj = new MonthSignStatusVO(DateUtil.getMonth(now), mSignRole.getGetList(), dayNum,
				doc.getVip());

		return rtnObj;
	}

	@Override
	public Map<String, Integer> monthSignGet(String acc, Integer day, Integer month) throws BizException {
		Role doc = roleAO().queryExistAccount(acc); // 玩家
		RoleActivityMonthSign mSignRole = roleActivityMonthSignAO().queryAdd(doc.getId()); // 玩家月签数据
		Map<Integer, YueQian> yueQianMonthDatas = roleActivityMonthSignAO().getYueQianDatas();

		// 检查领取数据
		if (yueQianMonthDatas == null || !yueQianMonthDatas.containsKey(day)) {
			throw BaseException.getException(ActivityException.EXCE_MONTH_SIGN_NO_GIFT, day);
		}
		if (DateUtil.getMonth(new Date()) != month) {// 跨月的需重刷界面
			throw BaseException.getException(ActivityException.EXCE_MONTH_SIGN_CUT_MONTH, day);
		}
		if (mSignRole.getGetList().contains(day)) {// 已经领取过
			throw BaseException.getException(ActivityException.EXCE_MONTH_SIGN_HAS_GOT, day);
		}
		int dayNum = roleActivityMonthSignAO().getMax(mSignRole, yueQianMonthDatas); // 可以领取天数上限
		if (day > dayNum) {// 未累积到天数
			throw BaseException.getException(ActivityException.EXCE_MONTH_SIGN_DAYCNT_LACT, day,
					mSignRole.getLoginCnt());
		}

		YueQian yueQianData = yueQianMonthDatas.get(day);
		int add = 1;
		if (yueQianData.getVip() > 0 && doc.getVip() > 0 && doc.getVip() >= yueQianData.getVip()) {
			add = 2;
		}
		packetAO().addItem(doc, yueQianData.getType(), yueQianData.getItemid(), yueQianData.getNum() * add,
				RoleItemLog.SYS_精彩活动_月签奖励, "" + yueQianData.getItemid());
		// 列表加入
		List<Integer> getList = mSignRole.getGetList();
		getList.add(day);
		if (day < dayNum) {// 兼容旧数据，只push进已领取列表，不更新Refresh_time
			roleActivityMonthSignAO().updateGetListForOldData(getList, doc.getId());
		} else {
			roleActivityMonthSignAO().updateGetList(getList, doc.getId());
		}

		Map<String, Integer> rtnObj = new HashMap<String, Integer>();
		rtnObj.put("gold", doc.getGold());
		rtnObj.put("silver", doc.getSilver());
		rtnObj.put("result", 1);
		return rtnObj;
	}

	@Override
	public void reloadMonthSignData() {
		roleActivityMonthSignAO().reloadMonthSignData();
	}

	@Override
	public TouZiStatusVO investPlanStatus(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityTouzi roleTouziDoc = roleActivityTouziAO().query(doc.getId());
		TouZiStatusVO rtnVO;
		if (roleTouziDoc == null) {
			rtnVO = new TouZiStatusVO(0, doc.getVip(), new ArrayList<Integer>(), doc.getLevel());
		} else {
			rtnVO = new TouZiStatusVO(roleTouziDoc.getHasBuy(), doc.getVip(), roleTouziDoc.getGetLevelList(),
					doc.getLevel());
		}
		return rtnVO;
	}

	@Override
	public Map<String, Integer> investPlanBuy(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityTouzi roleTouziDoc = roleActivityTouziAO().queryAdd(doc.getId());
		// Open openData = cacheManager().getExistValueByKey(Open.class,
		// 38);//key:system

		if (roleTouziDoc.getHasBuy() == 1) {// 已购买
			throw BaseException.getException(ActivityException.EXCE_TOUZI_HAS_BUY);
		}
		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, Constants.touZiCost,
				RoleItemLog.SYS_精彩活动_投资计划, "");
		roleActivityTouziAO().setHasBuy(doc.getId());
		Map<String, Integer> rtnMap = new HashMap<String, Integer>();
		rtnMap.put("gold", doc.getGold());
		rtnMap.put("result", 1);
		return rtnMap;
	}

	@Override
	public Map<String, Integer> investPlanGet(String acc, int lv) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityTouzi roleTouziDoc = roleActivityTouziAO().queryAdd(doc.getId());
		if (roleTouziDoc.getHasBuy() == 0) {// 未购买，不能领取
			throw BaseException.getException(ActivityException.EXCE_TOUZI_NOT_BUY);
		}
		if (doc.getLevel() < lv) {// 等级不足，不能领取
			throw BaseException.getException(ActivityException.EXCE_TOUZI_LEVEL_CONTROL);
		}
		if (roleTouziDoc.getGetLevelList().contains(lv)) {// 已经领取过，不能领取
			throw BaseException.getException(ActivityException.EXCE_TOUZI_HAS_GOT);
		}

		TouZi touziData = cacheManager().getExistValueByKey(TouZi.class, lv);
		packetAO().addItem(doc, touziData.getType(), touziData.getItemid(), touziData.getNum(),
				RoleItemLog.SYS_精彩活动_投资计划, "");

		List<Integer> getList = roleTouziDoc.getGetLevelList();
		getList.add(lv);
		roleActivityTouziAO().updateGetList(getList, doc.getId());

		Map<String, Integer> rtnMap = new HashMap<String, Integer>();
		rtnMap.put("gold", doc.getGold());
		rtnMap.put("result", 1);
		return rtnMap;
	}

	@Override
	public LimitCardVO limitCardGame(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		return roleActivityAO().getActivityConfig(doc);
	}

	@Override
	public LimitCardVO LimitDraw(String acc, String isFree) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);

		List<ProbItem> itemAry = new ArrayList<ProbItem>();
		// 必得五星1
		// 必得主卡0
		// 随意获取
		LimitCardVO clv = roleActivityAO().LimitDraw(doc.getId(), isFree);
		int free = Integer.valueOf(isFree);
		RoleStatistics oldRS = roleStatisticsAO().query(doc.getId());
		if (free == 0) {
			packetAO().removeItemMustEnough(doc, 0, 1, clv.getGold_cost(), RoleItemLog.SYS_限时豪杰, "");
		}
		RoleStatistics newRS = roleStatisticsAO().query(doc.getId());

		ProbItem pib = new ProbItem();
		switch (clv.getType()) {
		case 0:
			List<Object> res = packetAO().addItem(doc, 8, clv.getMainid(), 1, RoleItemLog.SYS_限时豪杰, "");

			Card cardData1 = cacheManager().getExistValueByKey(Card.class, clv.getMainid());
			if (clv.getCardfu() == 1) {
				broadcastAO().activityGetCardBroad(doc, cardData1);
			} else {
				broadcastAO().activityGetLimitCardBroad(doc, cardData1);
			}

			RoleCard last = (RoleCard) res.get(0);
			pib.setId(clv.getMainid());
			pib.setObjId(last.getId());
			break;
		case 1:
			RolePseudo rp = rolePseudoAO().query(doc.getId(), 7);
			List<Object> equips = packetAO().getPacketListByType(doc.getId(), Packet.POS_EQUIP);
			List<Object> cards = packetAO().getPacketListByType(doc.getId(), Packet.POS_CARD);
			List<ProbItem> item = rolePseudoAO().getRewards(rp, true, equips, cards, null);
			itemAry.addAll(item);
			for (ProbItem probItem : itemAry) {
				Card cardData = cacheManager().getExistValueByKey(Card.class, probItem.getId());
				if (cardData.getStar().get(0) == 5) {
					broadcastAO().activityGetCardBroad(doc, cardData);
				}
				List<Object> res1 = packetAO().addItem(doc, 8, cardData.getId(), 1, RoleItemLog.SYS_限时豪杰, "");
				RoleCard last1 = (RoleCard) res1.get(0);
				probItem.setObjId(last1.getId());

				pib.setId(cardData.getId());
				pib.setObjId(last1.getId());
			}
			break;
		case 2:
			RolePseudo rp1 = rolePseudoAO().query(doc.getId(), 7);
			equips = packetAO().getPacketListByType(doc.getId(), Packet.POS_EQUIP);
			cards = packetAO().getPacketListByType(doc.getId(), Packet.POS_CARD);
			List<ProbItem> item1 = rolePseudoAO().getRewards(rp1, false, equips, cards, null);
			itemAry.addAll(item1);
			for (ProbItem probItem : itemAry) {
				Card cardData = cacheManager().getExistValueByKey(Card.class, probItem.getId());
				if (cardData.getStar().get(0) == 5) {
					broadcastAO().wineShopGetCardBroad(doc, cardData);
				}
				List<Object> res2 = packetAO().addItem(doc, 8, cardData.getId(), 1, RoleItemLog.SYS_限时豪杰, "");
				RoleCard last2 = (RoleCard) res2.get(0);
				probItem.setObjId(last2.getId());
				pib.setId(cardData.getId());
				pib.setObjId(last2.getId());
			}
			break;
		}
		if (free != 0)
			BehaviorLogger.log4GoldCost(doc, RoleItemLog.SYS_限时豪杰, oldRS, newRS, itemAry);
		clv.setRlblist(roleActivityAO().getCardListRankList(0, true));
		clv.setProbItem(pib);
		return clv;
	}

	/**
	 * 限时商店
	 * 
	 * @param acc
	 * @return
	 */
	public LimitShopVO enterLimitShop(String acc) throws BizException {
		Role role = roleAO().queryByAccount(acc);
		// 判断是否刷新
		LimitShop limitShop = activityLimitShopAO().queryLimitShopInfo();
		List<LimitShopInfo> shopInfoList = new ArrayList<LimitShopInfo>();
		List<LimitShopInfo> roleshopInfoList = new ArrayList<LimitShopInfo>();
		List<LimitShopData> shopDataList = cacheManager().getLimitShopList();
		Map<Integer, LimitShopData> shopDataMap = cacheManager().getValues(LimitShopData.class);
		List<Integer> idsList = roleActivityAO().getLimitShopIds();
		String activeTime = roleActivityAO().getLimitActiveTime(); // 活动时段
		RoleLimitShop roleLimitShop = roleActivityLimitShopAO().queryShopInfoByRoleId(role.getId());

		if (roleLimitShop == null) {
			roleActivityLimitShopAO().createRoleActLimitShop(role.getId(), new ArrayList<LimitShopInfo>());
		}

		if (limitShop == null) {
			// 创建商店信息
			for (LimitShopData limitShopData : shopDataList) {
				if (idsList.contains(limitShopData.getId())) {
					shopInfoList.add(LimitShopInfo.ValueOf(limitShopData));
				}
			}
			activityLimitShopAO().createLimitShop(shopInfoList);
		}
		if (limitShop != null && !DateUtil.isToday(limitShop.getRefreshTime())) {
			// 更新商店
			shopInfoList = limitShop.getItemInfo();
			for (Iterator<LimitShopInfo> iterator = shopInfoList.iterator(); iterator.hasNext();) {
				LimitShopInfo curInfo = iterator.next();

				LimitShopData shopData = cacheManager().getValueByKey(LimitShopData.class, curInfo.getId());
				// 需要每天刷新
				if (idsList.contains(curInfo.getId()) && shopData.getSale() == LimitShopInfo.DAY_LIMIT) {
					curInfo.setNum(shopData.getSum1());
				}
			}

			activityLimitShopAO().updateLimitShop(shopInfoList);

		}
		;
		if (roleLimitShop != null) {
			roleshopInfoList = roleLimitShop.getItemIds();
		}
		// 更新个人
		if (roleLimitShop != null && !DateUtil.isToday(roleLimitShop.getRefreshTime())) {
			// roleshopInfoList = roleLimitShop.getItemIds();
			for (int i = 0; i < roleshopInfoList.size(); i++) {
				if (idsList.contains(roleshopInfoList.get(i).getId())
						&& shopDataMap.get(roleshopInfoList.get(i).getId()).getSale() == LimitShopInfo.DAY_LIMIT) {
					roleshopInfoList.set(i, LimitShopInfo.ValueOfRole(roleshopInfoList.get(i).getId(), 0));
				}
			}
			roleActivityLimitShopAO().updateRoleLShop(role.getId(), roleshopInfoList, DateUtil.GetNowDateTime());
		}
		LimitShopVO ls = new LimitShopVO();
		ls.setVipLevel(role.getVip());
		List<ToolsVO> toolsList = new ArrayList<ToolsVO>();
		limitShop = activityLimitShopAO().queryLimitShopInfo();
		for (LimitShopInfo limitShopInfo : limitShop.getItemInfo()) {
			LimitShopData shopData = shopDataMap.get(limitShopInfo.getId());
			int num = packetAO().getNumberByTypeId(role, shopData.getType(), shopData.getItemid());
			toolsList.add(ToolsVO.valueOf(limitShopInfo, shopData.getArr_sum2().get(role.getVip()), num));
		}
		for (LimitShopInfo limitShopInfo : roleshopInfoList) {
			for (ToolsVO toolsVO : toolsList) {
				if (limitShopInfo.getId() == toolsVO.getId()) {
					int canb = shopDataMap.get(limitShopInfo.getId()).getArr_sum2().get(role.getVip())
							- limitShopInfo.getNum();
					toolsVO.setCanBuyNum(canb);
				}
			}
		}
		String[] dateStr = activeTime.split("_");
		for (int i = 0; i < dateStr.length; i++) {
			if (i == 0) {
				ls.setsDate(DateUtil.getDayTime(dateStr[i]));
			} else {
				ls.seteDate(DateUtil.getDayTime(dateStr[i]));
			}
		}

		ls.setToolsList(toolsList);
		// 倒计时
		Date endTime = DateUtil.getDateTimeByString(dateStr[1]);
		Long countDown = endTime.getTime() - new Date().getTime();
		ls.setCountDown(countDown > 1000 ? countDown : 0);

		return ls;
	}

	@Override
	public void openActivity(int activityId) {
		roleActivityAO().openActivity(activityId);
	}

	@Override
	public void closeActivity(int activityId) {
		roleActivityAO().closeActivity(activityId);
	}

	@Override
	public List<SystemStatus> querySystemStatusList() {
		return roleActivityAO().querySystemStatusList();
	}

	/**
	 * 限时商店兑换
	 * 
	 */
	@Override
	public LSExchangeVO exchangeLimitGoods(String acc, int id, int count) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		Map<Integer, LimitShopData> shopDataMap = cacheManager().getValues(LimitShopData.class);
		LimitShopData shopData = shopDataMap.get(id);
		LimitShop limitShop = activityLimitShopAO().queryLimitShopInfo(); // 商店数据
		RoleLimitShop roleLimitShop = roleActivityLimitShopAO().queryShopInfoByRoleId(role.getId());
		// 判断背包
		List<Integer> bagAry = new ArrayList<Integer>();
		List<PacketExtend> bageData = new ArrayList<PacketExtend>();

		if (!DateUtil.isToday(limitShop.getRefreshTime())) {
			return new LSExchangeVO(bageData, 1, 0);
		}

		if (shopData.getType() != 8) {
			Item itemData = cacheManager().getExistValueByKey(Item.class, shopData.getItemid());
			bagAry.add(itemData.getBag());
		} else {
			bagAry.add(8);
		}

		bageData = packetAO().checkBag(role, bagAry);
		if (bageData.size() > 0) {
			return new LSExchangeVO(bageData, 1, 1);// 失败 背包满
		}
		// roleLimitShop num 存已经兑换的数量
		// 判断数量是否足够
		LimitShopInfo shopInfo = null;
		for (LimitShopInfo limitShopInfo : limitShop.getItemInfo()) {
			if (limitShopInfo.getId() == id) {
				shopInfo = limitShopInfo;// 被购买的物品数据 {id:*, num:*}
			}
		}
		if (shopInfo != null && shopInfo.getNum() < count) { // 可购买数 < 玩家要购买数
			throw BaseException.getException(ActivityException.EXCE_GOODS_NUM_NOT_ENOUGH);
		}
		// 判断是否还可以兑换该道具
		LimitShopInfo roleshopInfo = null;
		if (roleLimitShop != null) {
			for (LimitShopInfo LimitShopInfo : roleLimitShop.getItemIds()) {
				if (LimitShopInfo.getId() == id) {
					roleshopInfo = LimitShopInfo;
				}
			}
		}
		//
		int canbugNum = shopData.getArr_sum2().get(role.getVip());
		if (roleshopInfo != null && canbugNum - roleshopInfo.getNum() < 1) {
			throw BaseException.getException(ActivityException.EXCE_EXCHANGE_NUM_NOT_ENOUGH);
		}
		// 扣钱 发奖励
		packetAO().removeItemMustEnough(role, Packet.POS_ATTR, shopData.getCointype(), shopData.getPrice() * count,
				RoleItemLog.SYS_精彩活动_限时商店_兑换花费, "");
		packetAO().addItem(role, shopData.getType(), shopData.getItemid(), shopData.getNum() * count,
				RoleItemLog.SYS_精彩活动_限时商店_兑换道具, "");
		for (LimitShopInfo limitShopInfo : limitShop.getItemInfo()) {
			if (limitShopInfo.getId() == id) {
				limitShopInfo.setNum(limitShopInfo.getNum() - count);
			}
		}
		// 更新商店信息
		activityLimitShopAO().updateLimitShop(limitShop.getItemInfo());
		// 更新个人信息
		if (roleLimitShop == null) {
			// 创建
			List<LimitShopInfo> newlist = new ArrayList<LimitShopInfo>();
			newlist.add(LimitShopInfo.ValueOfRole(shopData.getId(), count));
			roleActivityLimitShopAO().createRoleActLimitShop(role.getId(), newlist);
		} else {
			// 更新
			boolean ise = false;
			for (LimitShopInfo LimitShopInfo : roleLimitShop.getItemIds()) {
				if (LimitShopInfo.getId() == id) {
					ise = true;
					LimitShopInfo.setNum(LimitShopInfo.getNum() + count);
				}
			}
			if (ise == false) {
				LimitShopInfo li = new LimitShopInfo();
				li.setId(id);
				li.setNum(count);
				roleLimitShop.getItemIds().add(li);
			}
			roleActivityLimitShopAO().updateRoleLShop(role.getId(), roleLimitShop.getItemIds(),
					roleLimitShop.getRefreshTime());
		}
		return new LSExchangeVO(bageData, 0, 1);
	}

}
