package com.mdy.dzs.game.action;

import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.activity.ActivityConfig;
import com.mdy.dzs.game.domain.activity.guessgame.ActivityGuessBuyVO;
import com.mdy.dzs.game.domain.activity.guessgame.ActivityGuessChooseVO;
import com.mdy.dzs.game.domain.activity.guessgame.ActivityGuessingGameVO;
import com.mdy.dzs.game.domain.activity.guessgame.RoleActivityGuess;
import com.mdy.dzs.game.domain.activity.happygame.ActivityHappyStatusVO;
import com.mdy.dzs.game.domain.activity.limitcard.LimitCardVO;
import com.mdy.dzs.game.domain.activity.limitshop.LSExchangeVO;
import com.mdy.dzs.game.domain.activity.limitshop.LimitShopVO;
import com.mdy.dzs.game.domain.activity.monthsigngame.MonthSignStatusVO;
import com.mdy.dzs.game.domain.activity.touzigame.TouZiStatusVO;
import com.mdy.dzs.game.domain.system.SystemStatus;
import com.mdy.sharp.container.biz.BizException;

public interface RoleActivityAction {

	/**
	 * 获取活动状态
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	Map<Integer, Integer> queryActivityStatusByAcc(String acc, Map<Integer, Integer> activityStatus)
			throws BizException;

	List<SystemStatus> querySystemStatusList();

	/**
	 * 获取活动状态
	 * 
	 * @return
	 * @throws BizException
	 */
	List<ActivityConfig> queryActivityConfigs();

	/**
	 * 重读活动
	 */
	void reloadActivitys();

	/////////////////////////////////////////////////////////////////////
	/**
	 * 查询猜拳信息
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	RoleActivityGuess queryGuessInfos(String acc) throws BizException;

	/**
	 * 猜拳
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	ActivityGuessingGameVO guessGame(String acc) throws BizException;

	/**
	 * 选择抽奖
	 * 
	 * @param acc
	 * @param pos
	 * @return
	 * @throws BizException
	 */

	ActivityGuessChooseVO guessChoose(String acc, int pos) throws BizException;

	/**
	 * 购买猜拳次数
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	ActivityGuessBuyVO guessBuyCount(String acc) throws BizException;

	/**
	 * 累积登录礼包列表
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	Map<String, Map<String, List<ProbItem>>> happyGift(String acc) throws BizException;

	/**
	 * 累积登录奖状态
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	ActivityHappyStatusVO happyStatus(String acc) throws BizException;

	/**
	 * 累积登录领奖
	 * 
	 * @param acc
	 * @param day
	 * @return
	 * @throws BizException
	 */

	Map<String, Integer> happyGet(String acc, Integer day) throws BizException;

	/**
	 * 月卡领奖奖状态
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	MonthSignStatusVO monthSignStatus(String acc) throws BizException;

	/**
	 * 月签领奖
	 * 
	 * @param acc
	 * @param day
	 * @return
	 * @throws BizException
	 */

	Map<String, Integer> monthSignGet(String acc, Integer day, Integer month) throws BizException;

	// 更新月签数据
	void reloadMonthSignData();

	// 获取投资计划状态
	TouZiStatusVO investPlanStatus(String acc) throws BizException;

	// 领取投资计划

	Map<String, Integer> investPlanGet(String acc, int lv) throws BizException;

	// 购买投资计划

	Map<String, Integer> investPlanBuy(String acc) throws BizException;

	// 限时豪杰玩法入口
	LimitCardVO limitCardGame(String acc) throws BizException;

	// 限时豪杰抽卡

	LimitCardVO LimitDraw(String acc, String isFree) throws BizException;

	void openActivity(int activityId);

	void closeActivity(int activityId);

	/**
	 * 进入限时商店
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	LimitShopVO enterLimitShop(String acc) throws BizException;

	/**
	 * 限时商店兑换
	 * 
	 * @param acc
	 * @param id
	 * @param count
	 * @return
	 * @throws BizException
	 */

	LSExchangeVO exchangeLimitGoods(String acc, int id, int count) throws BizException;
}
