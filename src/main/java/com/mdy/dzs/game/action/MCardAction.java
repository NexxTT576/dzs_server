package com.mdy.dzs.game.action;

import java.util.Map;

import com.mdy.dzs.game.domain.monthcard.MCardVO;
import com.mdy.sharp.container.biz.BizException;

public interface MCardAction {

	/**
	 * client->server: Get: /mCard/actPage?acc=* Post: { "m":"mCard", "a":"actPage",
	 * "acc":"*" } acc: 账号
	 * 
	 * client<-server: {err: 错误信息 rtnObj: { isget：是否已领取 days:月卡剩余天数 cost:月卡花费
	 * getGold:立得元宝数量 isCanBuy: 是否可购买月卡 } }
	 */

	public MCardVO actPage(String acc) throws BizException;

	/**
	 * client->server: Get: /mCard/get?acc=* Post: { "m":"mCard", "a":"get",
	 * "acc":"*" } acc: 账号
	 * 
	 * client<-server: {err: 错误信息 rtnObj: { getResult: 领取结果 1-成功 2-失败 } }
	 */

	public Map<String, Object> get(String acc) throws BizException;

}
