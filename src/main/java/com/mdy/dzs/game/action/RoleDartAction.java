package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.game.domain.dart.ChoiceDartVO;
import com.mdy.dzs.game.domain.dart.DartCarVO;
import com.mdy.dzs.game.domain.dart.DartDataRtnVO;
import com.mdy.dzs.game.domain.dart.EnterFaceVO;
import com.mdy.dzs.game.domain.dart.RefreshDartCarVO;
import com.mdy.sharp.container.biz.BizException;

public interface RoleDartAction {
	/*
	 * 押镖界面： client->server: Get: /detainDart/enterFace?acc=* Post: { "m":
	 * "detainDart", "a": "enterFace", "acc":"*" } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { selfState: 自己押镖状态 【1-押镖结束 2-压镖中 3-押镖完成】
	 * getCoin： //获得奖励 [ {type:*, id:*, num:*} ,... ]奖励货币类型
	 * 
	 * selfDartCar: //自己镖车状态 { roleId： 角色ID name： 名字 lv： 等级 quality： 镖车品质 【1-4
	 * ：1-绿，2-蓝，3-紫，4-金】 arriveTime：倒记到达时间 } otherDartCar:[{ roleId： 角色ID name： 名字
	 * lv： 等级 quality： 镖车品质 【1-4 ：1-绿，2-蓝，3-紫，4-金】 arriveTime：倒记到达时间 }, ... ]
	 * refreshTime:*, 刷新倒记秒 speedUpCost:*, 加速运镖花费 lastTime:*, 活动持续时长 } }
	 */

	EnterFaceVO enterFace(String acc) throws BizException;

	/*
	 * 刷新其他镖车/全刷 client->server: Get: /detainDart/refreshOthers?acc=* Post: { "m":
	 * "detainDart", "a": "refreshOthers", "acc":"*" } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { [{ roleId： 角色ID name： 名字 lv： 等级
	 * quality：镖车品质 【1-4 ：1-绿，2-蓝，3-紫，4-金】 arriveTime：倒记到达时间 }, ... ] } }
	 */

	List<DartCarVO> refreshOthers(String acc) throws BizException;

	/*
	 * 刷新其他镖车/补位 client->server: Get: /detainDart/repairOthers?acc=*&repairIds=*
	 * Post: { "m": "detainDart", "a": "repairOthers", "acc":"*", "repairIds":"*" }
	 * acc: 账号 repairIds: 被补位IDs
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { [{ roleId： 角色ID name： 名字 lv： 等级
	 * quality：镖车品质 【1-4 ：1-绿，2-蓝，3-紫，4-金】 arriveTime：倒记到达时间 }, ... ] } }
	 */

	List<DartCarVO> repairOthers(String acc, List<Integer> repairIds) throws BizException;

	/*
	 * 镖车选择界面 client->server: Get: /detainDart/choiceDart?acc=* Post: { "m":
	 * "detainDart", "a": "choiceDart", "acc":"*" } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { detainTimes：今天可押镖次数 robTimes：今天可劫镖次数
	 * refreshCost:刷新花费 refreshTimes:刷新次数 【0-免费 其他】 quality：镖车品质 【1-4
	 * ：2-绿，3-蓝，4-紫，5-金】 lastQuality：上次刷新品质 【1-4】 } }
	 */
	ChoiceDartVO choiceDart(String acc) throws BizException;

	/*
	 * 玩家镖车信息 client->server: Get: /detainDart/dartData?acc=*&roleID=*&dartkey=*
	 * Post: { "m": "detainDart", "a": "dartData", "acc":"*", "roleID":"*",
	 * "dartkey":"*" } acc: 账号 roleID: 要查看的角色id dartkey: 战斗时发送
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { dartState: 查看的镖车状态 【1-正常 2-已押镖完毕】
	 * dartData: { lv: 玩家等级 name： 玩家名字 attack： 玩家战力 quality： 镖车品质 beRobTimes：被抢次数
	 * getCoin： 获得奖励 [ {type:*, id:*, num:*} ,... ] cardData：[ {resId:*, cls:* },...
	 * ] } } }
	 */
	DartDataRtnVO dartData(String acc, int roleID, int dartkey) throws BizException;

	/*
	 * 加速运镖 client->server: Get: /detainDart/speedUp?acc=* Post: { "m":
	 * "detainDart", "a": "speedUp", "acc":"*" } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: [ {type:*, id:*, num:*} ,... ]奖励货币类型 }
	 */

	List<GiftItem> speedUp(String acc) throws BizException;

	/*
	 * 开始押镖 client->server: Get: /detainDart/start?acc=* Post: { "m": "detainDart",
	 * "a": "start", "acc":"*" } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { result： } }
	 */

	Map<String, Integer> start(String acc) throws BizException;

	/*
	 * 抢镖 client->server: Get: /detainDart/robDart?acc=*&otherID=* Post: { "m":
	 * "detainDart", "a": "robDart", "acc":"*", "otherID":"*" } acc: 账号 otherID:对方ID
	 * 
	 * client<-server: {"0":err, "1":[rst], "2":[cor] "3":rtnAry, "4":coinAry}
	 * 
	 * err: 错误信息 rst 战斗结果 cor: 战斗过程 rtnAry: 掉落物品[第一个默认选中获得] 过程 coinAry: [{type:*,
	 * id:*, num:*},...]奖励货币类型
	 */

	List<Serializable> robDart(String acc, int otherID, int dartkey) throws BizException;

	/*
	 * 镖车选择界面--选择 client->server: Get: /detainDart/robDart?acc=*&otherID=*dartkey=*
	 * Post: { "m": "detainDart", "a": "robDart", "acc":"*", "otherID":"*",
	 * "dartkey":"*" } acc: 账号 type: 刷新类型 【1-普通 2-召唤】 dartkey:获取列表时有发送
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { gold:当前金币 refreshTimes:刷新次数 【0-免费 其他】
	 * quality：镖车品质 【1-4 ：1-绿，2-蓝，3-紫，4-金】 } }
	 */

	RefreshDartCarVO refreshDart(String acc, int type) throws BizException;

	/*
	 * 押镖结束领奖： client->server: Get: /detainDart/acceptAward?acc=* Post: { "m":
	 * "detainDart", "a": "acceptAward", "acc":"*" } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { [ {type:*, id:*, num:*} ,... ]奖励货币类型 }
	 * }
	 */

	List<GiftItem> acceptAward(String acc) throws BizException;
}
