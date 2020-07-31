package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.union.BossHistoryVO;
import com.mdy.dzs.game.domain.union.UnionBossResultVO;
import com.mdy.dzs.game.domain.union.UnionBossStatVO;
import com.mdy.dzs.game.domain.union.UnionBossTopTenVO;
import com.mdy.sharp.container.biz.BizException;

public interface UnionBossAction {

	/*
	 * union boss是否开启 client->server: Get: /union/bossHistory?acc=*&unionId=* Post:
	 * { "m": "union", "a": "bossHistory", "acc":"*", "unionId":"*" } acc: 账号
	 * unionId:帮派id
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { state: 1/2/3 今日挑战状态 【1-未开启 2-进行中 3-结束】
	 * jobType:0/1/2/3 玩家职务 【0:普通人员1:长老2:副帮主3:帮主】 templeLevel:* 青龙堂等级
	 * curUnionMoney:* 当前帮派资金 createBossCost:*开启挑战需要资金 } }
	 */
	BossHistoryVO bossHistory(String acc, int unionId) throws BizException;

	/*
	 * union开启挑战 client->server: Get: /union/bossCreate?acc=*&unionId=* Post: { "m":
	 * "union", "a": "bossCreate", "acc":"*", "unionId":"*" } acc: 账号 unionId:帮派id
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { result:1/2 结果 【1-成功开启 2-已被别人开启，可直接进入】
	 * curUnionMoney:* 当前帮派资金 } }
	 */

	Map<String, Integer> bossCreate(String acc, int unionId) throws BizException;

	/*
	 * union boss伤害排行榜 client->server: Get: /union/bossTop?acc=*&unionId=* Post: {
	 * "m":"union", "a":"bossTop", "acc":"*", "unionId":"*" } acc: 账号 unionId:帮派id
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { topPlayers: 伤害排行榜玩家信息 [{ rank:1, //排名
	 * acc:*, //账号 name:*, //名字 hurt:* //伤害血量 lv:* //等级 }, .... ] } }
	 */
	UnionBossTopTenVO bossTop(String acc, int unionId) throws BizException;

	/*
	 * union boss实时状态 client->server: Get: /union/bossState?acc=*&unionId=* Post: {
	 * "m":"union", "a":"bossState", "acc":"*", "unionId":"*" } acc: 账号 unionId:帮派id
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { stateObj: boss战状态 { name:"", //boss 名称
	 * level:*, //boss等级 life:* //伤害血量 lifeTotal:* //当前总血量 endTime:* //结束时间 }
	 * selfStat: 自身状态 { num:*, //攻击次数 hurt:*, //总伤害 hurtR:*, //总伤害比例 rank:*, //当前排名
	 * hurtAdd:*, //伤害加成 silverWait:* //银两鼓舞等待时间 nxtLiveGold:*, //下次复活元宝数目
	 * battleWait:* //攻击等待时间 } playerStat:其他玩家boss战状态 [{ name:"", //玩家昵称 hurt:*,
	 * //玩家攻击boss伤害值 }, .... ] } }
	 */

	UnionBossStatVO bossState(String acc, int unionId) throws BizException;

	/*
	 * union boss鼓舞,复活 client->server: Get: /union/bossPay?acc=*&unionId=*&use=*
	 * Post: { "m":"union", "a":"bossPay", "acc":"*", "use":"1/2/3", "unionId":"*" }
	 * acc: 账号 use: 1银币鼓舞/2元宝鼓舞/元宝复活 unionId:帮派id
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { selfStat: 自身状态 { num:*, //攻击次数 hurt:*,
	 * //总伤害 hurtR:*, //总伤害比例 rank:*, //当前排名 hurtAdd:*, //伤害加成 silverWait:
	 * //银两鼓舞等待时间 battleWait: //攻击等待时间 nxtLiveGold:*, //下次复活元宝数目 } isFinish: 是否活动已结束
	 * 1-未结束 2-结束 goldNum: 当前元宝数 silverNum： 当前银币数 isSuccess: 是否鼓舞成功 1-不成功 2-成功 } }
	 */

	Object bossPay(String acc, int unionId, int use) throws BizException;

	/*
	 * union boss战斗 client->server: Get: /union/bossPve?acc=*&unionId=* Post:
	 * {"m":"union", "a":"bossPve", "acc":"*", "unionId":"*" } acc: 账号 unionId:帮派id
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { selfStat: 自身状态 { num:*, 攻击次数 curHurt:*,
	 * 本次伤害值 hurt:*, 总伤害 hurtR:*, 总伤害比例 rank:*, 当前排名 hurtAdd:*, 伤害加成 silverWait:
	 * 银两鼓舞等待时间 battleWait: 攻击等待时间 nxtLiveGold:*, 下次复活元宝数目 } rstAry: 战斗结果数组[1成功/2失败]
	 * recAry: 战斗过程动画数组[recs] awardAry: 奖励物品数组,[{type:*, id:*, num:*},...] waitTime:
	 * 距本次战斗时间 isFinish: 是否活动已结束 1-未结束 2-结束 } }
	 */

	List<Serializable> bossPve(String acc, int unionId) throws BizException;

	/*
	 * union boss活动结果 client->server: Get: /union/bossResult?acc=*&unionId=* Post:
	 * {"m":"union", "a":"bossResult", "acc":"*", "unionId":"*" } acc: 账号
	 * unionId:帮派id
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { res: 活动结果 { kill:'', //击杀者名称,为空时为未击杀
	 * hurt:*, //总攻击值 rank:*, //伤害排名 endTime:* //结束时间 bossLife：* //boss当前血量
	 * lifeTotal:* //当前总血量 } awardAry: 奖励物品数组,[{type:*, id:*, num:*},...] isFinish:
	 * 是否已经结束 1-未结束 2-结束 } }
	 */

	UnionBossResultVO bossResult(String acc, int unionId) throws BizException;

	/** 青龙堂发奖 */

	void sendAward() throws BizException;

}
