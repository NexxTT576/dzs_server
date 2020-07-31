package com.mdy.dzs.game.domain.union;

public final class  UnionConfig {
	public static final String downdonateminussilver = "downdonateminussilver";    //低级捐献消耗银币
	public static final String downdonateuserget = "downdonateuserget";    //低级捐献获得贡献
	public static final String downdonateunionget = "downdonateunionget";    //低级捐献增加资金
	public static final String middledonateminusgold = "middledonateminusgold";    //中级捐献消耗元宝
	public static final String middledonateuserget = "middledonateuserget";    //中级捐献获得个人贡献
	public static final String middledonateunionget = "middledonateunionget";    //中级捐献获得帮派资金
	public static final String updonateminusgold = "updonateminusgold";    //高级捐献消耗元宝
	public static final String updonateuserget = "updonateuserget";    //高级捐献获得贡献
	public static final String updonateunionget = "updonateunionget";    //高级捐献增加资金
	public static final String xiahunOpen = "xiahunOpen";    //生产侠魂开启等级
	public static final String yinbiOpen = "yinbiOpen";    //生产银币开启等级
	public static final String voertimegoldnum = "voertimegoldnum";    //加班生产消耗元宝数量
	public static final String buyprodectnum = "buyprodectnum";    //每日额外生产次数上限
	public static final String onebuygoldnum = "onebuygoldnum";    //第一次额外生产消耗元宝
	public static final String twobuygoldnum = "twobuygoldnum";    //第二次额外生产消耗元宝
	public static final String threebuygoldnum = "threebuygoldnum";    //第三次额外生产消耗元宝
	public static final String fastendgoldnum = "fastendgoldnum";    //立即结束消耗元宝数
	public static final String prodectxhstylebyfast = "prodectxhstylebyfast";    //加班侠魂生产数量公式=玩家等级*400+作坊等级*4000
	public static final String prodectybstylebyfast = "prodectybstylebyfast";    //加班银币生产数量公式=玩家等级*1000+作坊等级*10000
	public static final String prodectxhstylebycommon = "prodectxhstylebycommon";    //普通侠魂生产数量公式=玩家等级*200+作坊等级*2000
	public static final String prodectybstylebycommon = "prodectybstylebycommon";    //普通银币生产数量公式=玩家等级*500+作坊等级*5000
	public static final String prodectneedtime = "prodectneedtime"; //每次生产消耗时间H
	public static final String unionshopforlevel = "unionshopforlevel"; //等级对应的帮派商店物品数量
	/**进入帮派最低等级限制*/
	public static final String roleUnionLevel = "roleUnionLevel";
	/**创建帮派消耗元宝*/
	public static final String createGold = "createGold";
	/**帮派申请人数上限*/
	public static final String subNum = "subNum";
	/**领取福利天数限制*/
	public static final String joinUnionTime = "joinUnionTime";
	/**领取福利等级限制*/
	public static final String levelLimit = "levelLimit";
	/**领取福利扣除贡献值数量*/
	public static final String userule = "userule";
	/**领取福利等级限制*/
	public static final String baLimitLel = "baLimitLel";
	/**开启烧烤大会扣除贡献值数量*/
	public static final String baCostUnionMoney = "baCostUnionMoney";
	/**烧烤大会初始增加体力*/
	public static final String initPhysVal = "initPhysVal";
	/**烧烤大会初始增加耐力值*/
	public static final String initResisVal = "initResisVal";
	/**伴随等级额外给予的体力*/
	public static final String addPhysVal = "addPhysVal";
	/**伴随等级额外给予的耐力*/
	public static final String addResisVal = "addResisVal";
	/**每周福利元宝*/
	public static final String weeklyGod = "weeklyGod";
	/**每周福利银币*/
	public static final String weeklySliver = "weeklySliver";
	/**每周福利侠魂*/
	public static final String weeklySoul = "weeklySoul";
	/**每周福利进阶丹*/
	public static final String weeklyAdvanced = "weeklyAdvanced";
	/**帮派大殿升级增加人数*/
	public static final String unionRoleNumAdd = "unionRoleNumAdd";   
	/**青龙殿boss最高血量*/
	public static final String dragonBossLifeMax = "dragonBossLifeMax";
	/**青龙殿boss战怪兽id*/
	public static final String dragonBossID = "dragonBossID";				
	/**青龙殿普通战斗，奖励声望*/
	public static final String dragonBossBattleAward = "dragonBossBattleAward";		
	/**青龙boss每次冒字返回数目*/
	public static final String dragonBossBattleBubbleNum = "dragonBossBattleBubbleNum";						
	/**青龙boss冒字刷新时间间隔*/
	public static final String dragonBossBattleRefreshDis = "dragonBossBattleRefreshDis";		
	/**工坊持续时间/小时*/
	public static final String workShopLastTime = "workShopLastTime";
	/**开启挑战青龙花费帮派资金*/
	public static final String dragonBossCreateMoney = "dragonBossCreateMoney";
	/**申请帮派数量上限*/
	public static final String applyUnionMaxNum = "applyUnionMaxNum";
	/**副帮主数量*/
	public static final String bossnum = "bossnum";
	/**长老数量*/
	public static final String managernum = "managernum";
	/**帮派最终人数上限*/
	public static final String unionmaxNum = "unionmaxNum";
	/**烧烤持续时间*/
	public static final String shaokaotime = "shaokaotime";
	/**低级捐献VIP等级要求*/
	public static final String lowvip = "lowvip";
	/**低级捐献VIP等级要求*/
	public static final String middlevip = "middlevip";
	/**低级捐献VIP等级要求*/
	public static final String seniorvip = "seniorvip";
	/**帮派动态最大保存数量*/
	public static final String unionDynamicNumLimit = "unionDynamicNumLimit";
	/**帮派商店刷新时间*/
	public static final String unionShopTime = "unionShopTime";
	/**个人商店刷新时间*/
	public static final String roleUnionShopTime = "roleUnionShopTime";
	/**帮派副本每日免费攻打次数*/
	public static final String fubenfreenum = "fubenfreenum";
	/**帮派副本起始小时*/
	public static final String fubenstarhour = "fubenstarhour";
	/**帮派副本起始分钟*/
	public static final String fubenstarmin = "fubenstarmin";
	/**帮派副本结束小时*/
	public static final String fubenendhour = "fubenendhour";
	/**帮派副本结束分钟*/
	public static final String fubenendmin = "fubenendmin";
}
