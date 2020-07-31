package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

public interface BossAction {

	/**
	 * client->server: Get: ?m=bossbattle&a=top&acc=* Post: {"m":"bossbattle",
	 * "a":"top", "acc":"*", } acc: 账号
	 * 
	 * client<-server: {"0":err, "1": topPlayers}
	 * 
	 * err: 错误信息 topPlayers: 伤害排行榜玩家信息(前十) [{ rank:1, //排名 acc:*, //账号 name:*, //名字
	 * hurt:* //伤害血量 lv:* //等级 }, ....] }
	 */
	List<Serializable> cmdtop(String acc) throws BizException;

	/**
	 * client->server: Get: ?m=bossbattle&a=history&acc=* Post: {"m":"bossbattle",
	 * "a":"history", "acc":"*", } acc: 账号
	 * 
	 * client<-server: {"0":err, "1": waitSecs, "2": hisBattle}
	 * 
	 * err: 错误信息 waitSecs:下次开始等待秒数(若战斗进行中为0) hisBattle: 上次boss战结果(若战斗进行中,状态为空) {
	 * name:'', boss名称 level:*, 等级 top3Name:['name1',..], killName:['name'] }
	 */
	List<Serializable> history(String acc) throws BizException;

	/**
	 * client->server: Get: ?m=bossbattle&a=state&acc=* Post: {"m":"bossbattle",
	 * "a":"state", "acc":"*", } acc: 账号
	 * 
	 * client<-server: {"0":err, "1": stateObj, "2":selfStat, "3":playerStat}
	 * 
	 * err: 错误信息 stateObj: boss战状态 { name:1, //boss 名称 level:*, //boss等级 life:*
	 * //伤害血量 lifeTotal:* //当前总血量 endTime:* //结束时间 } selfStat: 自身状态 { num:*, 攻击次数
	 * hurt:*, 总伤害 hurtR:*, 总伤害比例 rank:*, 当前排名 hurtAdd:*, 伤害加成 silverWait: 银两鼓舞等待时间
	 * nxtLiveGold:*, 下次复活元宝数目 battleWait: 攻击等待时间 } playerStat:其他玩家boss战状态 [{
	 * name:'',玩家昵称 hurt:*, 玩家攻击boss伤害值 },....]
	 */

	List<Serializable> state(String acc) throws BizException;

	/**
	 * client->server: Get: ?m=bossbattle&a=pay&acc=* Post: {"m":"bossbattle",
	 * "a":"pay", "acc":"*", "use":"1/2/3" } acc: 账号 use: 1银币鼓舞/2元宝鼓舞/元宝复活
	 * 
	 * client<-server: {"0":err, "1": selfStat} err:
	 * 错误信息（包含鼓舞失败、银币不足、元宝不足、鼓舞上限,时间未到）
	 * 
	 * //本次伤害加成结果
	 * 
	 * selfStat: 自身状态 { num:*, 攻击次数 hurt:*, 总伤害 hurtR:*, 总伤害比例 rank:*, 当前排名
	 * hurtAdd:*, 伤害加成 silverWait: 银两鼓舞等待时间 battleWait: 攻击等待时间 nxtLiveGold:*,
	 * 下次复活元宝数目
	 */

	List<Serializable> pay(String acc, int use) throws BizException;

	/**
	 * client->server: Get: ?m=bossbattle&a=pve&acc=* Post: {"m":"bossbattle",
	 * "a":"pve", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {"0":err, "1":rstAry, "2":recAry, "3":awardAry, "4":selfStat,
	 * "5":waitTime}
	 * 
	 * err: 错误信息 rstAry: 战斗结果数组[1成功/2失败] recAry: 战斗过程动画数组[recs] awardAry:
	 * 奖励物品数组,[{t:*, id:*, n:*},...] selfStat: 自身状态 { num:*, 攻击次数 curHurt:*, 本次伤害值
	 * hurt:*, 总伤害 hurtR:*, 总伤害比例 rank:*, 当前排名 hurtAdd:*, 伤害加成 silverWait: 银两鼓舞等待时间
	 * battleWait: 攻击等待时间 nxtLiveGold:*, 下次复活元宝数目 } waitTime: 距本次战斗时间
	 */

	List<Serializable> pve(String acc) throws BizException;

	/**
	 * client->server: Get: ?m=bossbattle&a=result&acc=* Post: {"m":"bossbattle",
	 * "a":"result", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {"0":err, "1":rstObj, "2":awardAry, "3":isFinish}
	 * 
	 * err: 错误信息 rstObj: boss结果 { kill:'', //击杀者名称,为空时为未击杀 hurt:*, //总攻击值 rank:*,
	 * //伤害排名 endTime:* //结束时间 bossLife：* //boss当前血量 lifeTotal:* //当前总血量 } awardAry:
	 * 奖励物品数组,[{t:*, id:*, n:*},...] isFinish: 是否已经结束 1-未结束 2-结束
	 */

	List<Serializable> result(String acc) throws BizException;

	/** 创建开启新的boss战活动 */

	void createBattle() throws BizException;

	/** 发奖 */

	void sendAward() throws BizException;

}
