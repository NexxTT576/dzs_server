package com.mdy.dzs.game.exception;



/**
 * 活动异常
 */
public class ActivityException extends BaseException{

	/**1500005活动未开启 */
	public final static ExceptionEntry EXCE_ACTIVITYCENTER_活动未开启 = createExceptionEntry(
			1500001, "活动未开启 id={0}");
	
	/**1500007活动不存在 */
	public final static ExceptionEntry EXCE_ACTIVITYCENTER_活动不存在 = createExceptionEntry(
			1500002, "活动不存在 id={0}");

	public static final ExceptionEntry EXCE_SLEEPOP_NOT_ALLOW = createExceptionEntry(
			1500101, "sleepop not allow");
	public static final ExceptionEntry EXCE_SLEEPOP_HAS_GOT = createExceptionEntry(
			1500102, "sleepop has got");
	//==============猜拳==================//
	/**不能猜拳*/
	public static final ExceptionEntry EXCE_CAIQUAN_CANNOT_GUESS= createExceptionEntry(
			1500201, "can not guess:no count");
	/**不能选择位置*/
	public static final ExceptionEntry EXCE_CAIQUAN_CANNOT_SELECT_POS= createExceptionEntry(
			1500202, "can not select pos：{0}");
	/**不能购买*/
	public static final ExceptionEntry EXCE_CAIQUAN_CANNOT_BUY= createExceptionEntry(
			1500203, "can not buy");
	/**配置错误*/
	public static final ExceptionEntry EXCE_CAIQUAN_NOT_CONFIG= createExceptionEntry(
			1500204, "not guess config! lv:{0}");
	/**胜利次数超出上限*/
	public static final ExceptionEntry EXCE_CAIQUAN_WIN_OUT= createExceptionEntry(
			1500205, "win count > config:{0}");
	//==============登录累积 天天乐==========//
	/**已经领取过*/
	public static final ExceptionEntry EXCE_HAPPY_HAS_GOT= createExceptionEntry(
			1500301, "happyDay has got :{0}");
	/**登录累积未到请求领取的天数*/
	public static final ExceptionEntry EXCE_HAPPY_DAYCNT_LACT= createExceptionEntry(
			1500302, "{0}lack day cnt:{1}");
	/**今天没有可领取礼包*/
	public static final ExceptionEntry EXCE_HAPPY_NOGIFT_TODAY= createExceptionEntry(
			1500303, "no gift today:{0}");
	//================  月签   =============//
	/**已经领取过*/
	public static final ExceptionEntry EXCE_MONTH_SIGN_HAS_GOT= createExceptionEntry(
			1500401, "month sign has got :{0}");
	/**登录天数未累积到请求领取的天数*/
	public static final ExceptionEntry EXCE_MONTH_SIGN_DAYCNT_LACT= createExceptionEntry(
			1500402, "{0}lack day cnt:{1}");
	/**没有奖励数据*/
	public static final ExceptionEntry EXCE_MONTH_SIGN_NO_GIFT= createExceptionEntry(
			1500403, "no gift,day:{day}");
	/**跨月了的*/
	public static final ExceptionEntry EXCE_MONTH_SIGN_CUT_MONTH= createExceptionEntry(
			1500404, "month sign cut month :{0}");
	//================  投资计划   =============//
	/**已购买*/
	public static final ExceptionEntry EXCE_TOUZI_HAS_BUY= createExceptionEntry(
			1500501, "hasBuy");
	/**还未购买*/
	public static final ExceptionEntry EXCE_TOUZI_NOT_BUY= createExceptionEntry(
			1500502, "not Buy");
	/**等级不足，不能领取*/
	public static final ExceptionEntry EXCE_TOUZI_LEVEL_CONTROL= createExceptionEntry(
			1500503, "level control");
	/**已经领取过，不能领取*/
	public static final ExceptionEntry EXCE_TOUZI_HAS_GOT= createExceptionEntry(
			1500504, "has got");
	
	/**免费次数已经用过*/
	public static final ExceptionEntry FREE_COUNT_FULL = createExceptionEntry(
			3000001, "free count full");
	
	//================ 限时兑换   =============//
	/**不可刷新*/
	public static final ExceptionEntry EXCE_EXCH_NOT_REFRESH = createExceptionEntry(
			1500601, "can not refresh");
	/**没有兑换数量*/
	public static final ExceptionEntry EXCE_EXCH_NO_EXCHNUM = createExceptionEntry(
			1500602, "no exchange number");
	
	//================  皇宫探宝   =============//
	/**等级不足*/
	public static final ExceptionEntry EXCE_ROULETTE_LEVEL_CONTROL = createExceptionEntry(
			1500701, "roulette level control");
	/**活动未开启*/
	public static final ExceptionEntry EXCE_ROULETTE_NOT_OPEN = createExceptionEntry(
			1500702, "roulette not open");
	/**奖励不存在*/
	public static final ExceptionEntry EXCE_ROULETTE_REWARD_NOT_EXIST = createExceptionEntry(
			1500703, "roulette reward not exist");	
	/**积分不够*/
	public static final ExceptionEntry EXCE_ROULETTE_CREDIT_NOT_ENOUGH = createExceptionEntry(
			1500704, "roulette credit not enough");
	/**已领取过*/
	public static final ExceptionEntry EXCE_ROULETTE_HAS_GOT = createExceptionEntry(
			1500705, "roulette rewards has got");
	/**剩余次数不足*/
	public static final ExceptionEntry EXCE_ROULETTE_TIMES_NOT_ENOUGH = createExceptionEntry(
			1500706, "roulette times not enough");
	/**参数错误*/
	public static final ExceptionEntry EXCE_ROULETTE_PARA_ERROR = createExceptionEntry(
			1500707, "roulette para error");
	/**金币不足*/
	public static final ExceptionEntry EXCE_ROULETTE_GOLD_NOTENOUGH = createExceptionEntry(
			1500708, "roulette gold not enough");
	
	//================  迷宫寻宝   =============//
	/**等级不足*/
	public static final ExceptionEntry EXCE_MAZE_LEVEL_CONTROL = createExceptionEntry(
			1500801, "maze level control");
	/**剩余挖宝不足*/
	public static final ExceptionEntry EXCE_MAZE_SUR_TIMES_CONTROL = createExceptionEntry(
			1500802, "maze sur times control");
	/**宝库中已没有宝物，请刷新宝库*/
	public static final ExceptionEntry EXCE_MAZE_NO_ITEM = createExceptionEntry(
			1500803, "maze no item");
	/**vip等级限制*/
	public static final ExceptionEntry EXCE_MAZE_VIP_LEVEL_CONTROL = createExceptionEntry(
			1500804, "maze VIP level control");

	/**数量不足*/
	public static final ExceptionEntry EXCE_GOODS_NUM_NOT_ENOUGH = createExceptionEntry(
			1500901, "goods num not enough");
	//限时商店
	/**兑换次数不足*/
	public static final ExceptionEntry EXCE_EXCHANGE_NUM_NOT_ENOUGH = createExceptionEntry(
			1500902, "echange num not enough");

}
