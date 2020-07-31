package com.mdy.dzs.game.exception;

public class UnionException extends BaseException
{
	/**帮派人数已满,无法申请加入*/
	public static final ExceptionEntry EXCE_UNION_IS_FULL = createExceptionEntry(
			2900001, "union is full");
	/**玩家申请数量已到上限*/
	public static final ExceptionEntry EXCE_UNION_APPLY_IS_FULL= createExceptionEntry(
			2900002, "union apply is full"); 
	/**玩家元宝不足*/
	public static final ExceptionEntry EXCE_ROLE_ONT_ENOUGH_GOLD= createExceptionEntry(
			2900004, "gold not enouht"); 
	/**帮派名称已存在*/
	public static final ExceptionEntry EXCE_UNION_NAME_IS_EXIST = createExceptionEntry(
			2900005, "union name exist"); 
	/**你搜索的帮派不存在！*/
	public static final ExceptionEntry EXCE_SEARCH_UNION_IS_NOT_EXIST= createExceptionEntry(
			2900008, "union is not exist");
	/**副帮主不存在*/
	public static final ExceptionEntry EXCE_VICE_NOT_EXIST = createExceptionEntry(
			2900010, "vice not exist"); 
	/**未过七天*/
	public static final ExceptionEntry EXCE_LEADER_LEAVE_NOT_IN_SDAYS = createExceptionEntry(
			2900011, "leader leave not enough seven days"); 
	/**玩家等级不到40级*/
	public static final ExceptionEntry EXCE_ROLE_LEVEL_IN_FORTY = createExceptionEntry(
			2900012, "role levle less 40"); 
	/**加入帮派时间不足五天*/
	public static final ExceptionEntry EXCE_ROLE_IN_UNION_NOT_FIVEDAYS = createExceptionEntry(
			2900013, "role not enough five days"); 
	/**帮派资金不足，无法开启烧烤大会*/
	public static final ExceptionEntry EXCE_UNION_MONEY_NOT_ENOUGH_OPEN_BAR= createExceptionEntry(
			2900015, "union money is not enough");
	/**玩家退出帮派未超过24小时*/
	public static final ExceptionEntry EXCE_ROLE_ONT_ENOUGH_24HOURS= createExceptionEntry(
			2900016, "exit Union in 24hours"); 
	/**当前帮派人数已满！*/
	public static final ExceptionEntry EXCE_UNION_IS_FULL_FOR_AGREE= createExceptionEntry(
			2900020, "money is not enough");
	public static final ExceptionEntry EXCE_UNION_LEVEL_MAX= createExceptionEntry(
			2900021, "union level is max"); //帮派等级已经是最大,大殿
	/**帮派捐献银币不足*/
	public static final ExceptionEntry EXCE_UNION_SLIVER_NOTENOUGH= createExceptionEntry(
			2900022, "sliver is not enough"); 
	public static final ExceptionEntry EXCE_UNION_DONATE_NOTENOUGH= createExceptionEntry(
			2900023, "union donate money not enough"); //元宝不足
	/**加入帮派不足24小时，无法捐献*/
	public static final ExceptionEntry EXCE_JOIN_UNION_NOT_ENOUGH_24HOUTS= createExceptionEntry(
			2900024, "join in union in 24hours"); 
	/**此建筑等级达到上限，请先提升大殿等级*/
	public static final ExceptionEntry EXCE_UNION_BUILD_TYPE_LEVEL_LIMIT= createExceptionEntry(
			2900026, "build level limit");
	public static final ExceptionEntry EXCE_UNION_PRODUCTNUM_NOTENOUGH= createExceptionEntry(
			2900027, "union workshop product num not enough"); //生产次数不足
	/**帮派资金不足，无法升级帮派商店*/
	public static final ExceptionEntry EXCE_UP_SHOP_MONEY_NOT_ENOUGH= createExceptionEntry(
			2900028, "money is not enough");
	/**该商品每人每天只可兑换1次！*/
	public static final ExceptionEntry EXCE_SHOP_GOODS_ONLY_EXCHANGE_ONE= createExceptionEntry(
			2900029, "only can exchange one");
	/**玩家贡献不足*/
	public static final ExceptionEntry EXCE_ROLE_CONTRBUTE_NOT_ENOUGH = createExceptionEntry(
			2900030, "role contrbute not enough");
	/**帮派商店等级不足，无法兑换！*/
	public static final ExceptionEntry EXCE_SHOP_LEVEL_NOT_ENOUGH= createExceptionEntry(
			2900031, "shop level is not enough");
	/**帮派资金不足，无法升级帮派作坊*/
	public static final ExceptionEntry EXCE_UP_WORKERSHOP_MONEY_NOT_ENOUGH= createExceptionEntry(
			2900032, "money is not enough");
	/**帮派资金不足，无法升级帮派大殿*/
	public static final ExceptionEntry EXCE_UNION_MONEY_NOTENOUGH= createExceptionEntry(
			2900033, "union money not enough"); 
	/**帮派资金不足，无法升级青龙堂*/
	public static final ExceptionEntry EXCE_UP_DRAGON_MONEY_NOT_ENOUGH= createExceptionEntry(
			2900034, "money is not enough");
	/**玩家已加入其他帮派*/
	public static final ExceptionEntry EXCE_ROLE_IN_OTHER_UNION= createExceptionEntry(
			2900037, "role in other union"); 
	/**帮派申请人数已到上限*/
	public static final ExceptionEntry EXCE_UNION_NUM_IS_FULL= createExceptionEntry(
			2900044, "union member is full"); 
	public static final ExceptionEntry EXCE_UNION_WORKSHOP_PRODUCT_XH= createExceptionEntry(
			2900045, "union workshop product xh level not enough"); //生产侠魂等级不够
	public static final ExceptionEntry EXCE_UNION_WORKSHOP_PRODUCT_YB= createExceptionEntry(
			2900046, "union workshop product yb level not enough"); //生产银币等级不够
	public static final ExceptionEntry EXCE_UNION_OVERTIMEGOLD_NOTENOUGH= createExceptionEntry(
			2900047, "union workshop overtime gold not enough"); //加班生产元宝不足
	/**生产没结束*/
	public static final ExceptionEntry EXCE_UNION_PRODUCT_NOTOVER= createExceptionEntry(
			2900048, "union workshop product not over"); 
	public static final ExceptionEntry EXCE_UNION_PRODUCT_NOTEND= createExceptionEntry(
			2900049, "EXCE_UNION_PRODUCT_NOTEND"); //生产时间未到
	public static final ExceptionEntry EXCE_UNION_BUYPRODECT_GOLDNOTENOUGH= createExceptionEntry(
			2900050, "EXCE_UNION_BUYPRODECT_GOLDNOTENOUGH"); //购买生产次数元宝不足
	public static final ExceptionEntry EXCE_UNION_FASTEND_GOLDNOTENOUGH= createExceptionEntry(
			2900051, "EXCE_UNION_FASTEND_GOLDNOTENOUGH"); //立即结束元宝不足
	/** 帮派商店配置错误*/
	public static final ExceptionEntry EXCE_UNION_SHOPCONFIG_ERROR= createExceptionEntry(
			2900052, "EXCE_UNION_SHOPCONFIG_ERROR"); 
	/** 玩家进入帮派等级不足*/
	public static final ExceptionEntry EXCE_ROLE_LEVEL_NOT_ENOUGH= createExceptionEntry(
			2900053, "role level not enough"); 
	/**玩家在帮派中*/
	public static final ExceptionEntry EXCE_ROLE_IN_UNION= createExceptionEntry(
			2900054, "role in union"); 
	/**已申请过该帮派*/
	public static final ExceptionEntry EXCE_UNION_APPLY_ISEXIST= createExceptionEntry(
			2900055, "apply is exist"); 
	/**权限不足*/
	public static final ExceptionEntry EXCE_ROLE_JUR_lESS= createExceptionEntry(
			2900056, "role jur is not enough"); 
	/**帮派不存在*/
	public static final ExceptionEntry EXCE_UNION_IS_NOT_EXIST= createExceptionEntry(
			2900057, "union is not exist"); 
	/**玩家福利已领取*/
	public static final ExceptionEntry EXCE_ROLE_HAVE_RECEIVED = createExceptionEntry(
			2900058, "role have  received"); 
	/**玩家职务与当前相同*/
	public static final ExceptionEntry EXCE_ROLE_JOPTYPE_IS_SAME= createExceptionEntry(
			2900059, "role joptype is same "); 
	/**烧烤活动已开启*/
	public static final ExceptionEntry EXCE_UNION_BARBECUE_IS_OPEN = createExceptionEntry(
			2900060, "babercue is open"); 
	/**烧烤活动未开启*/
	public static final ExceptionEntry EXCE_UNION_BARBECUE_IS_CLOSE = createExceptionEntry(
			2900061, "babercue is NOT open"); 
	/**被挑战者不存在*/
	public static final ExceptionEntry EXCE_FIGHTER_IS_NOT_EXIST = createExceptionEntry(
			2900062, "figheter is not exist"); 
	/**玩家不在帮派中*/
	public static final ExceptionEntry EXCE_ROLE_NOT_IN_UNION= createExceptionEntry(
			2900063, "role not in union"); 
	/**副帮主已存在*/
	public static final ExceptionEntry EXCE_UNION_VICE_IS_EXIST= createExceptionEntry(
			2900065, "vice is exist "); 
	/**长老人数已达上限*/
	public static final ExceptionEntry EXCE_UNION_ELDER_IS_FULL= createExceptionEntry(
			2900066, "elder is full "); 
	/**挑战次数已用完*/
	public static final ExceptionEntry EXCE_ROLE_DEKARONNUM_IS_NULL= createExceptionEntry(
			2900067, "dekaronNum is zero "); 
	/**被挑战次数已用完*/
	public static final ExceptionEntry EXCE_ROLE_DEFENSENUM_IS_NULL= createExceptionEntry(
			2900068, "defenseNum is zero "); 
	/**主建筑二级时开启*/
	public static final ExceptionEntry EXCE_UNION_LEVLE_IS_NOT_ENOUGH= createExceptionEntry(
			2900070, "unon level is not enough"); 
	/**玩家不存在*/
	public static final ExceptionEntry EXCE_ROLE_NOT_EXIST = createExceptionEntry(
			2900071, "role not exist"); 
	/**玩家未申请该帮派*/
	public static final ExceptionEntry EXCE_UNION_APPLY_IS_NOT_EXIST= createExceptionEntry(
			2900072, "apply is not exist"); 
	/**玩家帮派贡献值错误*/
	public static final ExceptionEntry EXCE_UNION_ROLE_TOTALCONTRIBUTE_NOT_ENOUGH= createExceptionEntry(
			2900073, "use's totalContribute not enough");
	/**今日已捐献*/
	public static final ExceptionEntry EXCE_ROLE_HAS_CONTRIBUTE= createExceptionEntry(
			2900074, "role  contribute onece");
	/**帮派资金不足，无法开启挑战*/
	public static final ExceptionEntry EXCE_UNION_NOT_ENOUGH_MONEY_FOR_CREATE_DRAGON= createExceptionEntry(
			2900076, "union not enough money for create dragon");
	/**青龙堂等级小于1级不能开启挑战*/
	public static final ExceptionEntry EXCE_GREEN_DRAGON_TEMPLE_LEVEL_CONTROL= createExceptionEntry(
			2900077, "green dragon temple level control");
	/**生产已结束*/
	public static final ExceptionEntry EXCE_WORK_SHOP_IS_OVER= createExceptionEntry(
			2900078, "work is finished");
	/**当前可兑换次数不足*/
	public static final ExceptionEntry EXCE_SHOP_GOODS_IS_NOT_ENOUGH= createExceptionEntry(
			2900079, "goods not enough");
	/**当前vip等级不足*/
	public static final ExceptionEntry EXCE_ROLE_VIP_NOT_ENOUGH= createExceptionEntry(
			2900080, "role vip not enough");
	/**今日捐献数量已达上限*/
	public static final ExceptionEntry EXCE_TODAY_CON_IS_FULL= createExceptionEntry(
			2900081, "today con is full");
	/**今日已兑换过该商品*/
	public static final ExceptionEntry EXCE_TODAY_HAS_BUY= createExceptionEntry(
			2900082, "today has buy");
	/**当前可兑换数量不足*/
	public static final ExceptionEntry EXCE_TODAY_BUY_NOT_ENOUGH= createExceptionEntry(
			2900085, "today buy num not enough");
	/**所兑换的商品不存在*/
	public static final ExceptionEntry EXCE_GOODS_NOT_EXIST= createExceptionEntry(
			2900086, "today goods not exist");
	/**帮派副本未通关，不可领取奖励*/
	public static final ExceptionEntry EXCE_UNION_FB_IS_NOT_PASS= createExceptionEntry(
			2900091, "union fb is not pass");
	/**通关奖励已领取*/
	public static final ExceptionEntry EXCE_UNION_FB_REWARD_HAS_GET= createExceptionEntry(
			2900092, "reward has get");
	/**帮派资金不足，无法升级帮派副本*/
	public static final ExceptionEntry EXCE_UP_UNIONFB_MONEY_NOT_ENOUGH= createExceptionEntry(
			2900093, "money is not enough");

}