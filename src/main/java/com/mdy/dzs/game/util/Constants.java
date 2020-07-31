package com.mdy.dzs.game.util;

import java.util.Arrays;
import java.util.List;

public final class Constants {
	//
		////////////////////////////////////////
		public static final float battleCalcHit 		= 0.95f;		//命中常量
		public static final int battleCalcAnger 	= 6;		//怒气伤害修正常量
		public static final float battleCalcCrit 		= 0.05f;		//暴击常量
		public static final float battleCalcBlock 	= 0.05f;		//格挡常量
		public static final int battleCalcLucy 		= 20;		//幸运常量
		public static final float battleCalcBack 		= 1.0f;		//反击伤害系数
		public static final int battleCalcForce1	= 0;		//武力常量1
		public static final int battleCalcForce2	= 0;		//武力常量2
		public static final int battleCalcSmart1	= 0;		//智力常量1
		public static final int battleCalcSmart2	= 0;		//智力常量2
		public static final int battleInitWorld 	= 11;		//初始地图id
		public static final int battleInitMap 		= 1101;		//初始地图id
		public static final int battleInitBattle 	= 110101;	//初始战斗
		public static final int battleNHitCDSec		= 3600;		//N次连战，冷却时间
		public static final float battle_禁疗系数		= 0.5f;		//禁疗系数
//		public static final int cardExtDefult		= 3;		//默认开始上阵卡牌数目
//		public static final List<Integer> cardExtend			= Arrays.asList(0,0,0,50,100,200);//卡牌扩展花费
		
//		public static final float equipLvUpCrit		=0.2f;		//装备升级暴击率
		public static final int equipPropItem		= 4011;		//装备洗炼石				
		public static final List<Integer> equipPropCost1		=Arrays.asList(2,0,0);	//普通洗炼[洗炼石头，银币，金币]
		public static final List<Integer> equipPropCost2		=Arrays.asList(1,3000,0);//普通洗炼[洗炼石头，银币，金币]
		public static final List<Integer> equipPropCost3		=Arrays.asList(1,0,5);	//普通洗炼[洗炼石头，银币，金币]	
		public static final List<Float> equipPropCoe		=Arrays.asList(0.03f,0.2f,0.2f,0.2f,0.2f,0.2f);//洗炼系数 [生命,攻击,物防,法防,最终伤害,最终免伤]
		
		public static final int furnacePillId		= 4012;		//炼化重生进阶丹id
		public static final List<Integer> furnaceCListStar	= Arrays.asList(3,4,5);	//炼化炉侠客列表星级选择	
		public static final List<Integer> furnaceEListStar	= Arrays.asList(2,3,4,5);//炼化炉装备列表星级选择	
		public static final List<Integer> RebirthCListStar	= Arrays.asList(3,4,5);	//重生侠客列表星级选择
		public static final List<Integer> rebirthEListStar	= Arrays.asList(4,5);	//重生装备列表星级选择
		
		public static final int jingYuanLvLimit		= 30;		//精元等级上限
		public static final int juYuanItemId		= 56;		//聚元道具
		public static final int juYuanGold			= 50;		//聚元金币花费
		public static final int collTenSilver		= 150000;	//聚元10连抽银币下线				
		
		public static final int snatchResisBill		= 4008;		//夺宝耐力丹id
		public static final int snatchWarFreeLicense= 54;		//夺宝免战牌id
		public static final int snatchWarFreeGold	= 20;		//夺宝金币免战数额
		public static final int snatchWarFreeTime	= 4;		//一次免战延时
		public static final List<Integer> snatchNeiAry		= Arrays.asList(5003,5004);			//永久显示内功id组
		public static final List<Integer> snatchWaiAry		= Arrays.asList(5001,5002);			//永久显示外功id组
		public static final List<Integer> snatchRestAry		= Arrays.asList(23,10);				//夺宝休战时段
		
		public static final int shopWineTen			= 2680;					//商店酒馆招募10次花费
		public static final int roadCardLevel		= 30;					//江湖路卡牌好感度上限
		
		public static final int channelGold			= 50;					//经脉-洗经伐脉金币消耗
		public static final int channelItemId		= 4022;					//经脉-洗经伐脉消耗道具经脉丹id
		
		public static final int onLineTime			= 5;					//heart间隔时间
		public static final int outLineTime			= 10;					//差超10分钟，即认为退出过，用于在线奖励时长累积
		
		public static final String appId				= "115669";	//appId
		public static final String appKey				= "f13b267a8193e2b7c26d26cfa12f1408eb77baaa212f58b3";	//appKey
		public static final List<Integer> eliteCnt			= Arrays.asList(3,3,3,3,3,3,3);
		
		public static final int packetExtendMoneyId = 1;					//货币类型id
		public static final int arenaInitRank		= 201;					//竞技场初始排名 6001
		
		public static final int shenResetGold		= 50;					//神通洗点花费
		
		public static final int swordFreeResetCnt = 1;						//论剑免费购买次数
		
		public static final int swordVipBuyBaseGold = 200;					//论剑够买的基础金币
		
		public static final int activityGuessCnt	= 3;					//猜拳活动次数
		public static final int activityGuessBuyGold = 20;					//猜拳花费
		
		public static final int yuekaCost			= 25;					//月卡售价
		public static final int yuekaGoldGet		= 288;					//月卡购买获得金币
		public static final int yuekaSurplusDays	= 3;					//月卡剩余天数，则可购买
		
		public static final List<Integer> mysteryPropAry = Arrays.asList(1,5,10,50);	//神秘商店刷新列表数组
		public static final int mysteryRefreshCost	= 20;					//神秘商店金币刷新花费
		public static final int CollectMaxLevel = 4;//最大聚元等级
		
		public static final int bossBattleBubbleNum  = 20;								//世界boss每次冒字返回数目
		public static final int bossBattleRefreshDis = 3;								//世界boss冒字刷新时间间隔
		public static final int bossID 				 = 4901;							//boss战怪兽id
		public static final List<Integer> bossBase	 = Arrays.asList(0, 999999, 1, 1);	//boss初始化属性
		public static final List<Integer> bossLead 	 = Arrays.asList(50, 90, 90);	  	//boss三维
		public static final int bossBattleAward		 = 10;								//普通战斗，奖励声望
		public static final int bossLifeMin			 = 20000000;						//boss最低血量
		public static final int bossLifeMax			 = 1000000000;						//boss最高血量
					
		public static final int battleClearCDSpend = 10;//清除cd花费元宝
	
		//普通副本 基础花费10*次数   （强攻令一次消耗1个）
		public static int battleSpendItem	 = 51;
		public static int battleSpendGold	 = 10;
		//精英副本 基础花费10*次数
		public static int eliteBattleSpendGold	 = 10;
		//行侠仗义 基础花费25*次数
		//劫富济贫 基础花费100*次数  （劫富令一次消耗1个）
		public static int[] activityBattleSpendItem = {0,52,0,0,0,0,0,0};
		public static int[] activityBattleSpendGold = {0,100,25,0,0,0,0,0,0};
		
		public static final int touZiCost = 1000;		//投资计划花费
		
		public static final List<Integer> MailRtnLimit = Arrays.asList(5,10);	//邮件每次返回条数
		public static final int MailSaveDay			   = 15;					//邮件保存天数
		public static final int MailSaveNum			   = 100;					//邮件保存数目
		
		public static final int ChatSaveDay			   = 7;						//聊天数据保留天数
		
		public static final int FriendSendNailiNum	   = 1;						//好友赠送一次耐力丹累加耐力值
		public static final int FriendGetNailiMaxLimit = 30;					//每日领取耐力次数上限
		public static final int FriendRecomNum		   = 4;						//默认推荐好友数
		public static final int FriendNumLimit		   = 100;					//好友上限
		public static final int FriendApplyListMaxNum  = 50;					//好友申请列表最多容纳数
		public static final int FriendChatDeleteDay    = 7;						//已查看聊天记录保留天数
		
		public static final int DigAllVipControll	   = 1;		//迷宫寻宝 挖全部VIP等级限制

}
