package com.mdy.dzs.game.action;

import com.mdy.dzs.game.domain.activity.mazegame.DigVO;
import com.mdy.dzs.game.domain.activity.mazegame.MazeEnterVO;
import com.mdy.dzs.game.domain.activity.mazegame.MazeRefreshVO;
import com.mdy.sharp.container.biz.BizException;

public interface RoleActivityMazeAction {
	/*
	 * 活动界面 client->server: Get: /activity/mazeEnter?acc=* Post: { "m": "activity",
	 * "a": "mazeEnter", "acc":"*" } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { libId: 道具库ID nowTime: 当前时间（millisecond）
	 * startT: 开始时间 endT： 结束时间 surGoldTimes: 剩余元宝次数 freeTimes: 免费次数 digAllGold:
	 * 全部挖取花费 refreshCost： 刷新宝库花费 treasuryAry： 宝库物品 { "1":{type:*,id:*,num:*},
	 * "2":{type:*,id:*,num:*}, ... } hasGetAry: 已领取宝库id [*,*] } }
	 */

	MazeEnterVO mazeEnter(String acc) throws BizException;

	/*
	 * { isOtherDay: 是否要刷新页面 【1-要刷下页面 2-无需】 treasuryMap： 宝库物品 {
	 * "1":{type:*,id:*,num:*}, "2":{type:*,id:*,num:*}, ... } gold: 剩余金币 }
	 */

	MazeRefreshVO mazeRefresh(String acc) throws BizException;

	/*
	 * 挖宝 client->server: Get: /activity/mazeDig?acc=*&type=* Post: { "m":
	 * "activity", "a": "mazeDig", "acc":"*", "type":"*" } acc: 账号 type: 挖宝类型 【1：一次
	 * 2：全部】
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { isOtherDay: 是否要刷新页面 【1-要刷下页面 2-无需】
	 * treasuryMap： 宝库物品 { "1":{type:*,id:*,num:*}, "2":{type:*,id:*,num:*}, ... }
	 * gold: 剩余金币 freeTimes： 免费次数 surGoldTimes: 剩余元宝次数 checkBag： []背包状态 digAllGold:
	 * 全部挖取花费 digOneGold: 挖一个花费 } }
	 */

	DigVO mazeDig(String acc, int type) throws BizException;

}
