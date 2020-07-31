package com.mdy.dzs.game.action;

import com.mdy.dzs.game.domain.activity.roulettegame.GetCreditVO;
import com.mdy.dzs.game.domain.activity.roulettegame.PreviewVO;
import com.mdy.dzs.game.domain.activity.roulettegame.RouletteEnterVO;
import com.mdy.dzs.game.domain.activity.roulettegame.RouletteOpVO;
import com.mdy.sharp.container.biz.BizException;

public interface RoleActivityRouletteAction {

	/*
	 * 进入活动，页面状态 client->server: Get: /activity/rouletteEnter?acc=* Post: { "m":
	 * "activity", "a": "rouletrteEnter", "acc":"*", } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { roleDataState: { startT: 开始时间 endT：
	 * 结束时间 countDown: 倒计时秒 countDown: 倒计时秒 credit： 当前积分 surTimes: 剩余次数 getBox:
	 * 已领取的箱子 [1,2,3] freeTimes： 免费次数 dayAdd； 今日累积 rouletteTimes：已获得的探宝次数 }
	 * rouletteState: [ { id:编号 itemDisplay：显示物品 } ] activeData: { type:
	 * 活动类型（1-充值返利；2-消费返利；3-直接购买次数） price: 单价 limitcnt； 次数上限 score： [] 积分档位
	 * rewardType1:[] 第一档积分奖励type rewardId1: [] 第一档积分奖励id rewardCnt1: [] 第一档积分奖励cnt
	 * rewardType2:[] 第二档积分奖励type rewardId2: [] 第二档积分奖励id rewardCnt2: [] 第二档积分奖励cnt
	 * rewardType3:[] 第三档积分奖励type rewardId3: [] 第三档积分奖励id rewardCnt3: [] 第三档积分奖励cnt
	 * } } }
	 */

	RouletteEnterVO rouletteEnter(String acc) throws BizException;

	/*
	 * 预览物品 client->server: Get: /activity/roulettePreview?acc=*&id=* Post: { "m":
	 * "activity", "a": "roulettePreview", "acc":"*", "id": "*" } acc: 账号 id: 编号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { itemType： [] itemId: [] itemCnt: [] } }
	 */
	PreviewVO roulettePreview(String acc, int id) throws BizException;;

	/*
	 * 探宝 client->server: Get: /activity/rouletteOp?acc=*&num=* Post: { "m":
	 * "activity", "a": "rouletteOp", "acc":"*", "num":"*" } acc: 账号 num: 探宝次数
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { itemAry: [ {type:* id:* num:* } ]
	 * surTimes: 剩余次数 dayAdd； 今日累积 rouletteTimes：已获得的探宝次数 freeTimes： 免费次数 } }
	 */

	RouletteOpVO rouletteOp(String acc, int num) throws BizException;

	/*
	 * 领取积分奖 client->server: Get: /activity/rouletteGetCredit?acc=*&index=* Post: {
	 * "m": "activity", "a": "rouletteGetCredit", "acc":"*", "index":"*" } acc: 账号
	 * index: 领取的箱子编号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { getBox: 已领取的箱子 [1,2,3] } }
	 */

	GetCreditVO rouletteGetCredit(String acc, int index) throws BizException;

}
