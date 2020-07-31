/**
 * 
 */
package com.mdy.dzs.game.ao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.DataAction;
import com.mdy.dzs.data.domain.activity.guess.ActivityGuess;
import com.mdy.dzs.data.domain.activity.limitcard.ActivityLimitCard;
import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.item.ProbVal;
import com.mdy.dzs.game.domain.activity.ActivityConfig;
import com.mdy.dzs.game.domain.activity.guessgame.RoleActivityGuess;
import com.mdy.dzs.game.domain.activity.limitcard.LimitCardBean;
import com.mdy.dzs.game.domain.activity.limitcard.LimitCardConfigBean;
import com.mdy.dzs.game.domain.activity.limitcard.LimitCardVO;
import com.mdy.dzs.game.domain.activity.limitcard.RankListBean;
import com.mdy.dzs.game.domain.activity.mazegame.MazeConfig;
import com.mdy.dzs.game.domain.activity.roulettegame.Roulette;
import com.mdy.dzs.game.domain.giftcenter.RoleGift;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.system.SystemStatus;
import com.mdy.dzs.game.exception.ActivityException;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;
import com.mdy.sharp.util.JSONUtil;

import flexjson.JSONDeserializer;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月24日 下午5:56:07
 */
public class RoleActivityAO extends BaseAO {

	private Map<Integer, ActivityGuess> activityGuessConfigs = new HashMap<Integer, ActivityGuess>();
	private List<ActivityConfig> activitys;
	private Map<Integer, ActivityConfig> mapActivitys;
	private ActivityLimitCard alc;

	// datas
	private Map<String, List<ProbItem>> happyItems;
	LimitCardConfigBean lcbeana = null;
	Roulette rouletteData = null;
	List<Integer> idList = null;
	MazeConfig mazeConfig = null;

	private CacheManager cacheManager;
	private RoleActivityExchAO roleActivityExchAO;
	private RoleActivityRouletteAO roleActivityRouletteAO;

	public RoleActivityAO(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void start() {
		DataAction dataAction = Container.get().createRemote(DataAction.class, DataApplication.CLUSTER_DATA_SYSTEM);
		// loadGuess
		activityGuessConfigs.clear();
		List<ActivityGuess> guessConfigList = dataAction.queryActivityGuessDatas();
		for (ActivityGuess activityGuess : guessConfigList) {
			activityGuessConfigs.put(activityGuess.getLevel(), activityGuess);
		}
		reloadActivitys();

		alc = dataAction.queryActivityLimitCard().get(0);// 当前只配置1条数据
		super.start();
	}

	//////////////////////////////////////// activity////////////////////////////////////////////////////////////

	public void reloadActivitys() {
		activitys = activityConfigDAO().queryList();
		Map<Integer, ActivityConfig> newMapActivitys = new HashMap<Integer, ActivityConfig>();
		Map<Integer, Integer> mapActivityStatus = new HashMap<Integer, Integer>();
		for (ActivityConfig activityConfig : activitys) {

			// if(activityStatus != null
			// && activityStatus.get(activityConfig.getActivityId()) == 0 && isOpen == 1){
			// openActivity(activityConfig.getActivityId());
			// }
			// if(activityStatus != null
			// && activityStatus.get(activityConfig.getActivityId()) == 1 && isOpen == 0){
			// closeActivity(activityConfig.getActivityId());
			// }
			loadActivityData(activityConfig);
			newMapActivitys.put(activityConfig.getActivityId(), activityConfig);
		}
		mapActivitys = newMapActivitys;
	}

	public List<SystemStatus> querySystemStatusList() {
		return activityConfigDAO().querySystemStatusList();
	}

	public List<ActivityConfig> queryActivityConfigs() {
		return activitys;
	}

	/**
	 * 活动开启
	 * 
	 * @param activityId
	 */
	public void openActivity(int activityId) {
		switch (activityId) {
		case ActivityConfig.ACTIVITY_登录累积奖励:
			// 之前未开启，现在开启了，清空玩家参与记录表
			roleActivityHappyDAO().clearHappyActivityDatas();
			break;

		case ActivityConfig.ACTIVITY_LIMITCARD:
			// 清空排名数据
			clearLimitCard();
			break;

		case ActivityConfig.ACTIVITY_皇宫探宝:
			// 清空数据表
			roleActivityRouletteDAO().clearRouletteDatas();
			break;

		case ActivityConfig.ACTIVITY_限时兑换:
			// 清空兑换类型为活动期间内可兑换次数的 数据
			roleActivityExchDAO().deleteAll();
			break;
		case ActivityConfig.ACTIVITY_LIMITSHOP:
			roleActivityLimitShopDAO().deleteAll();
			activityLimitShopDAO().deleteAll();
			break;
		case ActivityConfig.ACTIVITY_MAZE:
			roleActivityMazeDAO().clearMazeDatas();
			break;
		}
	}

	/**
	 * 读取活动配置数据
	 * 
	 * @param activityConfig
	 */
	private void loadActivityData(ActivityConfig activityConfig) {
		switch (activityConfig.getActivityId()) {
		case ActivityConfig.ACTIVITY_LIMITCARD:
			lcbeana = JSONUtil.fromJson(activityConfig.getActivityConfig(), LimitCardConfigBean.class);
			break;
		case ActivityConfig.ACTIVITY_登录累积奖励:
			happyItems = new JSONDeserializer<Map<String, List<ProbItem>>>().use("values", ArrayList.class)
					.use("values.values", ProbItem.class).deserialize(activityConfig.getActivityConfig());
			break;
		case ActivityConfig.ACTIVITY_皇宫探宝:
			rouletteData = JSONUtil.fromJson(activityConfig.getActivityConfig(), Roulette.class);
			break;
		case ActivityConfig.ACTIVITY_限时兑换:
			roleActivityExchAO.reloadActivitys();
			break;
		case ActivityConfig.ACTIVITY_LIMITSHOP:
			idList = JSONUtil.fromJsonList(activityConfig.getActivityConfig(), Integer.class);
			break;
		case ActivityConfig.ACTIVITY_MAZE:
			mazeConfig = JSONUtil.fromJson(activityConfig.getActivityConfig(), MazeConfig.class);
			break;
		}
	}

	/**
	 * 活动关闭
	 * 
	 * @param activityId
	 */
	public void closeActivity(int activityId) {
		switch (activityId) {
		case ActivityConfig.ACTIVITY_LIMITCARD:
			List<RankListBean> rlblist = getCardListRankList(50, false);
			List<RankListBean> scorelist = getCardListRankScoreList();
			// 发奖
			sendRewardRank(rlblist);// 发送排名奖励
			sendRewardScore(scorelist);// 发送积分奖励
			break;
		}
	}

	public static int checkOpen(ActivityConfig config) {
		return checkOpen(config, 0);
	}

	public static int checkOpen(ActivityConfig config, int interval) {
		int res = 0;
		do {
			if (config.getOpen() != 1) {
				break;
			}
			switch (config.getTimeType()) {
			case ActivityConfig.TIME_TYPE_永久:
				res = 1;
				break;
			case ActivityConfig.TIME_TYPE_每日时段:
				String times[] = config.getTimeParam().split("-");
				res = DateUtil.isNowInHourSecond(times[0], times[1]) ? 1 : 0;
				break;
			case ActivityConfig.TIME_TYPE_日期时段:
				String timeDis[] = config.getTimeParam().split(":");
				res = DateUtil.isTodayInDateInterval(timeDis[0], timeDis[1]) ? 1 : 0;
				break;
			case ActivityConfig.TIME_TYPE_精确日期时段:
				if (!config.getTimeParam().equals("")) {
					String timeDis_[] = config.getTimeParam().split("_");
					res = DateUtil.isTodayInDateInterval_(timeDis_[0], timeDis_[1], interval) ? 1 : 0;
				}
				break;
			}
		} while (false);

		return res;
	}

	public Date getEndTime(int activityId) {
		ActivityConfig config = mapActivitys.get(activityId);
		if (config == null)
			return null;
		Date res = null;
		do {
			switch (config.getTimeType()) {
			case ActivityConfig.TIME_TYPE_每日时段:
				String times[] = config.getTimeParam().split("-");
				Date now = new Date();
				String backOrForwardDay = DateUtil.dateFormat.format(now.getTime());
				String endStr = backOrForwardDay + " " + times[1] + ":00";
				try {
					res = DateUtil.dateTimeFormat.parse(endStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			}
		} while (false);
		return res;
	}

	///////////////////////////////////// activity
	///////////////////////////////////// guess/////////////////////////////////////////////////////////
	/**
	 * 获取猜拳信息
	 * 
	 * @param doc
	 * @return
	 * @throws BizException
	 */
	public RoleActivityGuess queryActivityGuessInfos(Role doc) throws BizException {
		int roleId = doc.getId();
		RoleActivityGuess infos = queryActivityGuess(roleId);
		if (infos.getUpdateTime() == null || !DateUtil.isSameDay(new Date(), infos.getUpdateTime())) {
			resetActivityGuess(doc, infos);
		}
		return infos;
	}

	private RoleActivityGuess queryActivityGuess(int roleId) {
		RoleActivityGuess guess = roleActivityGuessDAO().query(roleId);
		if (guess == null) {
			guess = new RoleActivityGuess();
			guess.setRoleId(roleId);
			guess.setGuessCount(Constants.activityGuessCnt);
			guess.setChoosePosList(new HashMap<String, Integer>());
			guess.setItemList(new ArrayList<ProbItem>());
			roleActivityGuessDAO().add(guess);
		}
		return guess;
	}

	/**
	 * 重置猜拳活动
	 * 
	 * @param doc
	 * @param infos
	 * @return
	 * @throws BizException
	 */
	public RoleActivityGuess resetActivityGuess(Role doc, RoleActivityGuess infos) throws BizException {
		ActivityGuess config = activityGuessConfigs.get(doc.getLevel());
		if (config == null) {
			// throw BaseException.getGlobalException(
			// "not guess config! lv:"+doc.getLevel());
			throw BaseException.getException(ActivityException.EXCE_CAIQUAN_NOT_CONFIG, doc.getLevel());
		}
		// todo 创建道具
		List<ProbItem> items = new ArrayList<ProbItem>();
		items.addAll(cacheManager.probGot(config.getItem1(), ProbVal.GOT_TYPE_单个));
		items.addAll(cacheManager.probGot(config.getItem2(), ProbVal.GOT_TYPE_单个));
		items.addAll(cacheManager.probGot(config.getItem3(), ProbVal.GOT_TYPE_单个));
		infos.setItemList(items);
		infos.setChooseCount(0);
		infos.setGuessCount(Constants.activityGuessCnt);
		infos.setBuyCount(0);
		infos.setChoosePosList(new HashMap<String, Integer>());
		roleActivityGuessDAO().update(infos);
		return infos;
	}

	/**
	 * 获得猜拳胜利次数
	 * 
	 * @param guess
	 * @return
	 */
	public int getWinCount(RoleActivityGuess guess) {
		return guess.getChoosePosList().size() + guess.getChooseCount();
	}

	/**
	 * 猜拳返回胜负
	 * 
	 * @param doc
	 * @param guess
	 * @return
	 * @throws BizException
	 */
	public int guessGame(Role doc, RoleActivityGuess guess) throws BizException {
		ActivityGuess config = activityGuessConfigs.get(doc.getLevel());
		if (config == null) {
			// throw BaseException.getGlobalException(
			// "not guess config! lv:"+doc.getLevel());
			throw BaseException.getException(ActivityException.EXCE_CAIQUAN_NOT_CONFIG, doc.getLevel());
		}
		List<Integer> winPorb = config.getWinProb();
		int winCount = getWinCount(guess);
		if (winCount >= winPorb.size()) {
			// throw BaseException.getGlobalException(
			// "win count > config:"+winPorb.size());
			throw BaseException.getException(ActivityException.EXCE_CAIQUAN_WIN_OUT, winPorb.size());
		}
		int prob = winPorb.get(winCount);
		return cacheManager.random(0, 10000) < prob ? 1 : 0;
	}

	/**
	 * 选出奖品位置
	 * 
	 * @param doc
	 * @param guess
	 * @return
	 * @throws BizException
	 */
	public int guessChoose(Role doc, RoleActivityGuess guess) throws BizException {
		int res = -1;
		ActivityGuess config = activityGuessConfigs.get(doc.getLevel());
		if (config == null) {
			// throw BaseException.getGlobalException(
			// "not guess config! lv:"+doc.getLevel());
			throw BaseException.getException(ActivityException.EXCE_CAIQUAN_NOT_CONFIG, doc.getLevel());
		}
		int prob[] = { config.getProb1(), config.getProb2(), config.getProb3() };
		Map<String, Integer> posItemPos = guess.getChoosePosList();
		for (Integer pos : posItemPos.values()) {
			prob[pos] = 0;
		}
		int sum = prob[0] + prob[1] + prob[2];
		int random = cacheManager.random(0, sum);
		if (random < prob[0]) {
			res = 0;
		} else if (random < prob[0] + prob[1]) {
			res = 1;
		} else if (random <= sum) {
			res = 2;
		}
		return res;
	}

	public void updateActivityGuess(RoleActivityGuess guess) {
		roleActivityGuessDAO().update(guess);
	}

	// 获取登录累积礼包礼品列表
	public Map<String, List<ProbItem>> getHappyItems() {
		return happyItems;
	}

	// 活动时段
	public String[] getHappyActiveTime() {
		ActivityConfig configs = mapActivitys.get(ActivityConfig.ACTIVITY_登录累积奖励);
		String timeDis[] = configs.getTimeParam().split(":");
		timeDis[0] = timeDis[0] + " 00:00";
		timeDis[1] = timeDis[1] + " 23:59";
		return timeDis;
	}

	public List<ProbItem> getDayitems(Integer day) {
		return happyItems.get(day + "");
	}

	// 更新累积登录礼包 登录累积天数
	public int checkHappyIsOpen() {
		int open = checkOpen(mapActivitys.get(ActivityConfig.ACTIVITY_登录累积奖励));
		boolean contain = mapActivitys.containsKey(ActivityConfig.ACTIVITY_登录累积奖励);

		if (contain && open == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	// 排行信息[maxplayer 查询几个 isdefault 是否要默认 ]
	public List<RankListBean> getCardListRankList(int maxplayer, boolean isdefault) {
		if (isdefault) {
			maxplayer = alc.getMaxPlayer();
		}
		List<LimitCardBean> lcblist = roleActivityLimitCardDAO().query(maxplayer);
		List<RankListBean> rlblist = new ArrayList<RankListBean>();
		for (int i = 0; i < lcblist.size(); i++) {
			RankListBean rb = new RankListBean();
			rb.setRunkid(i);
			rb.setScore(lcblist.get(i).getScore());
			rb.setUsername(lcblist.get(i).getRolename());
			rb.setRole_id(lcblist.get(i).getRole_id());
			rlblist.add(rb);
		}
		return rlblist;
	}

	// 查询大于积分的玩家
	public List<RankListBean> getCardListRankScoreList() {
		List<LimitCardBean> lcblist = roleActivityLimitCardDAO().queryLimitCardSocre(alc.getScoreCond());
		List<RankListBean> rlblist = new ArrayList<RankListBean>();
		for (int i = 0; i < lcblist.size(); i++) {
			RankListBean rb = new RankListBean();
			rb.setRunkid(i);
			rb.setScore(lcblist.get(i).getScore());
			rb.setUsername(lcblist.get(i).getRolename());
			rb.setRole_id(lcblist.get(i).getRole_id());
			rlblist.add(rb);
		}
		return rlblist;
	}

	public LimitCardVO getActivityConfig(Role roc) throws BizException {

		LimitCardVO lcvo = new LimitCardVO();
		ActivityConfig configs = mapActivitys.get(ActivityConfig.ACTIVITY_LIMITCARD);

		LimitCardBean lcbean = roleActivityLimitCardDAO().queryByRoleId(roc.getId());

		lcvo.setRlblist(getCardListRankList(alc.getMaxPlayer(), false));
		// 奖励信息
		lcvo.setLcb(lcbeana);
		if (lcbean == null) {
			lcvo.setAct_rest_time((int) DateUtil.howLongBetween2Time(DateUtil.getDateString(new Date()),
					configs.getTimeParam().split("_")[1], 4));
			lcvo.setFree_rest_time(0);
			lcvo.setGold_cost(alc.getGoldCost());
			lcvo.setAct_start_time(configs.getTimeParam().split("_")[0]);
			lcvo.setAct_end_time(configs.getTimeParam().split("_")[1]);
			lcvo.setActEndTime_inMS(DateUtil.getLongTime(configs.getTimeParam().split("_")[1]));
			lcvo.setPlayer_rank(10001);// 初始化千名之后
			lcvo.setRest_luck_num(9);

			// 创建一条信息
			lcbean = new LimitCardBean();
			lcbean.setRole_id(roc.getId());
			lcbean.setRolename(roc.getName());
			lcbean.setGetcount(0);
			lcbean.setLucknum(0);
			lcbean.setScore(0);
			lcbean.setProblucknum(0);
			lcbean.setFreegettime(DateUtil.getDateTimeByString("1984-11-30 00:00:00"));
			lcbean.setLastgettime(DateUtil.getDateTimeByString("1984-11-30 00:00:00"));
			roleActivityLimitCardDAO().addLimitCard(lcbean);
			return lcvo;
		}

		// 个人信息及配置信息
		lcvo.setGold_cost(alc.getGoldCost());
		lcvo.setMax_luck_num(alc.getMaxLucky());// 幸运值
		lcvo.setLuck_num(lcbean.getLucknum());
		lcvo.setRest_luck_num(lcbean.getGetcount() == 0 ? 9 : 9 - (lcbean.getGetcount() % 10));
		lcvo.setPlayer_rank(
				queryUserScoreRankService(roc.getId()) == 0 ? 10001 : queryUserScoreRankService(roc.getId()));
		lcvo.setAct_start_time(configs.getTimeParam().split("_")[0]);
		lcvo.setAct_end_time(configs.getTimeParam().split("_")[1]);
		lcvo.setActEndTime_inMS(DateUtil.getLongTime(configs.getTimeParam().split("_")[1]));
		lcvo.setAct_rest_time((int) DateUtil.howLongBetween2Time(DateUtil.getDateString(new Date()),
				configs.getTimeParam().split("_")[1], 4));
		lcvo.setFree_rest_time(DateUtil.getDateString(lcbean.getFreegettime()).equals("1984-11-30 00:00:00") ? 0
				: (int) DateUtil.howLongBetween2Time(DateUtil.getDateString(new Date()),
						DateUtil.addTime(DateUtil.getDateString(lcbean.getFreegettime()), 20), 4));
		lcvo.setPlayer_score(lcbean.getScore());
		return lcvo;
	}

	public LimitCardVO LimitDraw(int roleid, String isFree) throws BizException {
		LimitCardVO lcvo = new LimitCardVO();

		LimitCardBean lcbean = roleActivityLimitCardDAO().queryByRoleId(roleid);

		boolean bole = true;
		if (!isFree.equals("0")) {
			bole = DateUtil.isNowInTimePot(DateUtil.getDateString(lcbean.getFreegettime()),
					DateUtil.addTime(DateUtil.getDateString(lcbean.getFreegettime()), 20));
			if (bole) {
				throw BaseException.getException(ActivityException.FREE_COUNT_FULL);
			}
		}
		ActivityConfig configs = mapActivitys.get(ActivityConfig.ACTIVITY_LIMITCARD);
		int score = 10;
		int problucknum = 0;
		int getcount = 1;
		int lucknum = 0;
		int freetime = 72000;
		int freetimehore = 20;
		Date lastgettime = DateUtil.GetNowDateTime();
		Date freegettime = DateUtil.GetNowDateTime();
		int extluck = alc.getExtLucky(); // ==积分随机一次额外

		lcvo.setGold_cost(alc.getGoldCost());
		lcvo.setPlayer_score(lcbean.getScore() == 0 ? score : lcbean.getScore() + score);
		lcvo.setRest_luck_num(9 - ((lcbean.getGetcount() + 1) % 10));
		lcvo.setMax_luck_num(alc.getMaxLucky());
		lcvo.setActEndTime_inMS(DateUtil.getLongTime(configs.getTimeParam().split("_")[1]));

		if (!bole && isFree.equals("0")) {
			lcvo.setFree_rest_time(0);
		} else {
			lcvo.setFree_rest_time(!bole ? freetime
					: (int) DateUtil.howLongBetween2Time(DateUtil.getDateString(new Date()),
							DateUtil.addTime(DateUtil.getDateString(lcbean.getFreegettime()), freetimehore), 4));
		}
		lcvo.setGet_score(score);
		// 随机一次是否得到主卡
		boolean bol = (lcbean == null ? false
				: getRandomFlag(lcbeana.getRoadm()
						.get(lcbean.getLucknum() >= lcbeana.getRoadm().size() ? lcbeana.getRoadm().size() - 1
								: lcbean.getLucknum())));
		if (bol) {
			// 根据概率出主卡
			// 清空幸运值
			rewardCardConfig_main(score, getcount, lastgettime, roleid, bole);
			lcvo.setMainid(lcbeana.getCard1());
			lcvo.setType(0);
			lcvo.setLuck_num(0);
			lcvo.setPlayer_rank(queryUserScoreRankService(roleid));
			return lcvo;
		}
		// 随机是否得到副卡card2、card3
		if (lcbeana.getCard2() != 0) {
			if (getRandomFlag(alc.getCard2Prop())) {
				boolean bolean = false;
				if (!bole && isFree.equals("1")) {
					bolean = true;
				}
				lucknum = getLuckNum(lucknum, lcbean, score, extluck);
				rewardCardConfig_rondom(score, lucknum, getcount, lastgettime, freegettime, problucknum, roleid,
						bolean);
				lcvo.setMainid(lcbeana.getCard2());
				lcvo.setType(0);
				lcvo.setPlayer_rank(queryUserScoreRankService(roleid));
				lcvo.setLuck_num(lcbean.getLucknum() + lucknum);
				lcvo.setCardfu(1);
				return lcvo;
			}
		}
		if (lcbeana.getCard3() != 0) {
			if (getRandomFlag(alc.getCard3Prop())) {
				boolean bolean = false;
				if (!bole && isFree.equals("1")) {
					bolean = true;
				}
				lucknum = getLuckNum(lucknum, lcbean, score, extluck);
				rewardCardConfig_rondom(score, lucknum, getcount, lastgettime, freegettime, problucknum, roleid,
						bolean);
				lcvo.setMainid(lcbeana.getCard3());
				lcvo.setType(0);
				lcvo.setPlayer_rank(queryUserScoreRankService(roleid));
				lcvo.setLuck_num(lcbean.getLucknum() + lucknum);
				lcvo.setCardfu(1);
				return lcvo;
			}
		}
		// 计算是否随机五星卡牌
		if (lcbean.getGetcount() != 0 && ((lcbean.getGetcount() + 1) % 10) == 0) {
			boolean bolean = false;
			if (!bole && isFree.equals("1")) {
				bolean = true;
			}

			lucknum = getLuckNum(lucknum, lcbean, score, extluck);

			rewardCardConfig_rondom(score, lucknum, getcount, lastgettime, freegettime, problucknum, roleid, bolean);
			lcvo.setType(1);
			lcvo.setLuck_num(lcbean.getLucknum() + lucknum);
			lcvo.setPlayer_rank(queryUserScoreRankService(roleid));
			return lcvo;
		} else {
			boolean bolean = false;
			if (!bole && isFree.equals("1")) {
				bolean = true;
			}

			lucknum = getLuckNum(lucknum, lcbean, score, extluck);

			rewardCardConfig_rondom(score, lucknum, getcount, lastgettime, freegettime, problucknum, roleid, bolean);
			lcvo.setLuck_num(lcbean.getLucknum() + lucknum);
			lcvo.setType(2);
			lcvo.setPlayer_rank(queryUserScoreRankService(roleid));
			return lcvo;
		}
	}

	public int queryUserScoreRankService(int roleid) {
		List<RankListBean> rlblist = getCardListRankList(alc.getMaxRank(), false);

		int rank = 0;
		for (int i = 0; i < rlblist.size(); i++) {
			if (rlblist.get(i).getRole_id() == roleid) {
				rank = i + 1;
				break;
			}
		}
		if (rlblist.size() >= alc.getMaxRank() && rank == 0) {
			rank = alc.getMaxRank() + 1; // 超过客户端最大值
		}
		return rank;
	}

	// 计算幸运值
	public int getLuckNum(int lucknum, LimitCardBean lcbean, int score, int extluck) {
		if ((0 == (lcbean.getScore() + score) % extluck) && lcbean.getScore() != 0) {
			// 进行额外幸运值计算
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			String extprob = lcbeana.getExtLuckyProb();
			String[] prob = extprob.split(",");
			int maxweight = 0;
			for (int i = 0; i < prob.length; i++) {
				maxweight += Integer.valueOf(prob[i].split(";")[1]);
				map.put(Integer.valueOf(prob[i].split(";")[0]), Integer.valueOf(prob[i].split(";")[1]));
			}
			List<Integer> retlist = new ArrayList<Integer>();
			getRandomNum(map, maxweight, 1, retlist);
			lucknum += retlist.get(0);
		}
		// 普通随机一次
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		String extprob = lcbeana.getBaseLuckProb();
		String[] prob = extprob.split(",");
		int maxweight = 0;
		for (int i = 0; i < prob.length; i++) {
			maxweight += Integer.valueOf(prob[i].split(";")[1]);
			map.put(Integer.valueOf(prob[i].split(";")[0]), Integer.valueOf(prob[i].split(";")[1]));
		}
		List<Integer> retlist = new ArrayList<Integer>();
		getRandomNum(map, maxweight, 1, retlist);
		lucknum += retlist.get(0);

		return lucknum;
	}

	// 清空豪杰玩法玩家数据
	public void clearLimitCard() {
		roleActivityLimitCardDAO().clearLimitCard();
	}

	public void sendRewardScore(List<RankListBean> rlblist) {
		String[] scorerewardlist = alc.getScoreReward().split(",");
		for (int i = 0; i < rlblist.size(); i++) {
			RoleGift rg = new RoleGift();
			List<GiftItem> items = new ArrayList<GiftItem>();
			for (int a = 0; a < scorerewardlist.length; a++) {
				String[] reward = scorerewardlist[a].split(";");
				items.add(new GiftItem(Integer.valueOf(reward[0]), Integer.valueOf(reward[1]),
						Integer.valueOf(reward[2])));
			}
			rg.setGiftType(RoleGift.GIFT_TYPE_LIMIT_CARD_SCOR);
			rg.setOtherData(rlblist.get(i).getScore() + "");
			rg.setGiftItem(items);
			rg.setRoleId(rlblist.get(i).getRole_id());
			roleGiftCenterDAO().add(rg);
		}

	}

	public void sendRewardRank(List<RankListBean> rlblist) {
		List<Integer> reward1 = lcbeana.getReward1();
		List<Integer> reward2_3 = lcbeana.getReward2();
		List<Integer> reward4_20 = lcbeana.getReward3();

		// 奖励4单独发奖
		String reward4 = alc.getReward4();
		for (int i = 0; i < rlblist.size(); i++) {
			RoleGift rg = new RoleGift();
			List<GiftItem> items = new ArrayList<GiftItem>();
			if (i == 0) {
				for (int cardid : reward1) {
					items.add(new GiftItem(Packet.POS_CARD, cardid, 1));
				}
			}
			if (i >= 1 && i <= 2) {
				for (int cardid : reward2_3) {
					items.add(new GiftItem(Packet.POS_CARD, cardid, 1));
				}
			}
			if (i >= 3 && i <= 19) {
				for (int cardid : reward4_20) {
					items.add(new GiftItem(Packet.POS_CARD, cardid, 1));
				}
			}
			if (i >= 20 && i <= 49) {
				String[] reward4list = reward4.split(";");
				items.add(new GiftItem(Integer.valueOf(reward4list[0]), Integer.valueOf(reward4list[1]),
						Integer.valueOf(reward4list[2])));
			}

			rg.setGiftType(RoleGift.GIFT_TYPE_LIMIT_CARD_RANK);
			rg.setOtherData(i + 1 + "");
			rg.setGiftItem(items);
			rg.setRoleId(rlblist.get(i).getRole_id());
			roleGiftCenterDAO().add(rg);
		}
	}

	// 随机配置修改
	private void rewardCardConfig_rondom(int score, int lucknum, int getcount, Date lastgettime, Date freegettime,
			int problucknum, int role_id, boolean upfree) {
		LimitCardBean lcb = new LimitCardBean();
		lcb.setScore(score);
		lcb.setProblucknum(problucknum);
		lcb.setGetcount(getcount);
		lcb.setLucknum(lucknum);
		lcb.setLastgettime(lastgettime);
		lcb.setFreegettime(freegettime);
		lcb.setRole_id(role_id);
		roleActivityLimitCardDAO().updateLimitDraw(lcb, false, upfree);
	}

	// 主卡配置修改
	private void rewardCardConfig_main(int score, int getcount, Date lastgettime, int role_id, boolean bole) {
		LimitCardBean lcb = new LimitCardBean();
		// 幸运值满
		// 给主卡
		// 清空幸运值
		lcb.setScore(score);
		lcb.setProblucknum(0);
		lcb.setGetcount(getcount);
		lcb.setLucknum(0);// 要清空
		lcb.setLastgettime(lastgettime);
		lcb.setRole_id(role_id);
		lcb.setFreegettime(DateUtil.GetNowDateTime());
		// 不更新freegettime
		roleActivityLimitCardDAO().updateLimitDraw(lcb, true, bole);// 具体修改
	}

	// 万分之的x的概率下是否可以获取
	public static boolean getRandomFlag(int num) {
		Random random = new Random();
		int value = random.nextInt(10000);
		if (value < num) {
			return true;
		}
		return false;
	}

	// 简单随机方法
	public static List<Integer> getRandomNum(Map<Integer, Integer> map, int maxweight, int size,
			List<Integer> retlist) {
		if (size <= 0) {
			return retlist;
		}
		Random random = new Random();
		int value = random.nextInt(maxweight);
		int total = 0;
		for (int key : map.keySet()) {
			int keyValue = map.get(key);
			total += keyValue;
			if (value < total) {
				retlist.add(key);
				map.remove(key);
				break;
			}
		}
		if (retlist.size() < size && map.size() > 0) {
			int max = 0;
			for (int key : map.keySet()) {
				int keyValue = map.get(key);
				max += keyValue;
			}
			getRandomNum(map, max, size, retlist);
		}
		return retlist;
	}

	public Map<Integer, ActivityConfig> getMapActivitys() {
		return mapActivitys;
	}

	public LimitCardBean queryByRoleId(int roleId) {
		LimitCardBean lcbean = roleActivityLimitCardDAO().queryByRoleId(roleId);
		return lcbean;
	}

	///////////////////////////////////// 皇宫探宝 //////////////////////////////////
	// 皇宫探宝活动时段
	public String getRouletteActiveTime() {
		ActivityConfig configs = mapActivitys.get(ActivityConfig.ACTIVITY_皇宫探宝);
		return configs.getTimeParam();
	}

	// 限时商店时间段
	public String getLimitActiveTime() {
		ActivityConfig configs = mapActivitys.get(ActivityConfig.ACTIVITY_LIMITSHOP);
		return configs.getTimeParam();
	}

	public void setRoleActivityRouletteAO(RoleActivityRouletteAO roleActivityRouletteAO) {
		this.roleActivityRouletteAO = roleActivityRouletteAO;
	}

	public void setRoleActivityExchAO(RoleActivityExchAO roleActivityExchAO) {
		this.roleActivityExchAO = roleActivityExchAO;
	}

	public Roulette getRouletteDatas() {
		return rouletteData;
	}

	///////////////////////////////////// 迷宫寻宝 /////////////////////////////////////
	public MazeConfig getMazeConfig() {
		return mazeConfig;
	}

	// 迷宫寻宝活动时段
	public String getMazeActiveTime() {
		ActivityConfig configs = mapActivitys.get(ActivityConfig.ACTIVITY_MAZE);
		return configs.getTimeParam();
	}

	public List<Integer> getLimitShopIds() {
		return idList;
	}

}
