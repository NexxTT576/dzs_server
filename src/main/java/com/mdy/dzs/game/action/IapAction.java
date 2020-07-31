package com.mdy.dzs.game.action;

import java.util.Map;

import com.mdy.dzs.game.domain.vip.VipInfoVO;
import com.mdy.sharp.container.biz.BizException;

public interface IapAction {

	/**
	 * 充值界面 main:VIP充值界面 client->server: Get: ?m=iap&a=main&acc=* Post: { "m":"iap",
	 * "a":"main", "acc":"*" } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { list:[{ index:编号 cnt:充值次数 }]
	 * 
	 * vipData:{ level:当前等级 curExp:当前经验值累计 curExpLimit：当前经验值上限 } yueData:{ cost:月卡售价
	 * goldget:立得金币 } } }
	 */
	VipInfoVO iap(String acc) throws BizException;

	/**
	 * vip豪华礼包/vip等级礼包
	 * 
	 * 礼包列表界面： client->server: Get: /iap/vipLvGiftList?acc=* Post: { "m":"iap",
	 * "a":"vipLvGiftList", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {err: 错误信息 rtnObj: { getLevelGiftAry:[] //已经购买过的VIP等级礼包
	 * curVipLevel:当前VIP等级 } }
	 */
	Map<String, Object> vipLvGiftList(String acc) throws BizException;

	/**
	 * vip等级礼包购买： client->server: Get: /iap/vipLvGiftGet?acc=*&vipLv=* Post: {
	 * "m":"iap", "a":"vipLvGiftGet", "acc":"*" "vipLv":"*" } acc: 账号 vipLv:
	 * 购买礼包的VIP等级
	 * 
	 * client<-server: {err: 错误信息 rtnObj: { result：购买结果 //1-成功 2-失败 gold: 金币数
	 * silver: 银币 } }
	 */

	Map<String, Object> vipLvGiftGet(String acc, int vipLv) throws BizException;

	/**
	 * vip每日福利
	 * 
	 * client->server: Get: /iap/vipDayGift?acc=* Post: { "m":"iap",
	 * "a":"vipDayGift", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {err: 错误信息 rtnObj: { isGet: 是否已领取 // 1-已领取 2-未领取
	 * curVipLevel:当前VIP等级 } }
	 */
	Map<String, Object> vipDayGift(String acc) throws BizException;

	/**
	 * 每日福利领取： client->server: Get: /iap/getVipDayGift?acc=* Post: { "m":"iap",
	 * "a":"getVipDayGift", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {err: 错误信息 rtnObj: { result: 领取结果 //1-成功 2-失败 } }
	 * 
	 */

	Map<String, Object> getVipDayGift(String acc) throws BizException;

	void test(String acc, String index) throws BizException;

}