/**
 * 
 */
package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.BuyCntVO;
import com.mdy.dzs.game.domain.challenge.ActDetailVO;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.sharp.container.biz.BizException;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月8日 上午10:52:05
 */
public interface ChallengeBattleAction {

	/**
	 * 精英副本状态
	 * 
	 * @param acc 账号
	 * @return {"0":err, "1": maxLv, "2":dayLeftCnt, "3":nextLvState,
	 *         "4":nextLvRevive, "5":bagData} err: 错误信息 maxLv: 精英副本已通关关卡数
	 *         eliteNxtLvState: 未通关副本状态(0未攻打,1通过副本1, 2通过副本2, 3通过副本3)
	 *         eliteNxtLvRevive:未通关副本，是否需要复活(1/0) dayLeftCnt:今日剩余次数 bagData: 背包状态
	 * @throws BizException
	 */
	List<Serializable> queryEliteBattleStatus(String acc) throws BizException;

	/**
	 * 购买精英副本挑战次数
	 * 
	 * @param acc 账号
	 * @return eliteBuyCnt 购买后剩余次数
	 * @throws BizException
	 */

	BuyCntVO buyEliteBattleCnt(String acc) throws BizException;

	/**
	 * 精英副本战斗
	 * 
	 * @param acc             账号
	 * @param id              精英副本id
	 * @param npc             怪物波(1/2/3)
	 * @param revive是否复活(0/1)
	 * @param clear           可选参数；清除当前关卡状态，重新开始
	 * @return {"0":err, "1":rstAry, "2":recAry, "3":awardAry, "4":coinAry,
	 *         "5":lvStat,"6":curLevel,"7":curExp} err: 错误信息 rstAry: 战斗胜负数组[1/2]
	 *         recAry: 战斗过程数组[recs] awardAry:奖励物品数组 coinAry: [{t:*, id:*,
	 *         n:*},...]奖励货币类型 lvStat: 关卡状态数组 [maxLv, curLvId, eliteNxtLvState,
	 *         eliteNxtLvRevive, dayLeftCnt] maxLv: 精英副本已通关关卡数 curLvId: 当前攻打关卡id
	 *         eliteNxtLvState: 未通关副本状态(0未攻打,1通过副本1, 2通过副本2, 3通过副本3)
	 *         eliteNxtLvRevive:未通关副本，是否需要复活(1/0) dayLeftCnt:今日剩余次数 curLevel: 当前等级
	 *         curExp: 当前等级
	 * @throws BizException
	 */

	List<Serializable> eliteBattle(String acc, int curLvId, int npc, boolean revive, boolean clear) throws BizException;

	/**
	 * 活动副本当日状态列表
	 * 
	 * @param acc 账号
	 * @return {"0":err, "1":leftCntAry, "2":bagData} leftCntAry:剩余免费活动次数数组 bagData:
	 *         背包状态
	 * @throws BizException
	 */
	List<Serializable> queryActivityBattleStatus(String acc) throws BizException;

	/**
	 * 活动副本购买
	 * 
	 * @param acc
	 * @param aid
	 * @param act
	 * @return
	 * @throws BizException
	 */
	BuyCntVO buyActivityBattleCnt(String acc, int aid) throws BizException;

	/**
	 * 活动副本
	 * 
	 * @param acc   账号
	 * @param aid   活动id(1劫富济贫2行侠仗义3除暴安良)
	 * @param npc   怪物波数(1/2/3),摇钱树时为1
	 * @param sysId 系统id
	 * @param npcLv npc难度等级
	 * @param fmt   阵型*
	 * @return {"0":err, "1":rstAry, "2":recAry, "3":awardAry,
	 *         "4":coinAry,"5":lvStat, "6":sumDamage} err: 错误信息 rstAry:
	 *         战斗胜负数组[1/2,1/2,1/2] recAry: 战斗过程数组[recs, recs, recs,..]
	 *         awardAry:奖励物品数组 coinAry: [{t:*, id:*, n:*},...]奖励货币类型 lvStat: 关卡状态数组
	 *         [actNxtLvState, dayLeftCnt] actNxtLvState: 未通关副本状态(0未攻打,1通过副本1,
	 *         2通过副本2, 3通过副本3) dayLeftCnt:今日剩余次数 sumDamage:敌方收到总伤害数
	 * @throws BizException
	 */

	List<Serializable> activityBattle(String acc, int aid, int npc, int sysId, int npcLv, List<SwordCard> fmt)
			throws BizException;

	/**
	 * 活动副本详情
	 * 
	 * @param acc   账号
	 * @param aid   副本id
	 * @param sysId 系统id
	 * @return
	 * @throws BizException
	 */

	ActDetailVO actDetail(String acc, int aid, int sysId) throws BizException;

	/**
	 * 保存阵容
	 * 
	 * @param acc   账号
	 * @param sysId 系统id
	 * @param fmt   阵容
	 * @return
	 * @throws BizException
	 */

	int save(String acc, int sysId, List<SwordCard> fmt) throws BizException;

	/**
	 * 检查背包
	 * 
	 * @param acc
	 * @param aid
	 * @return
	 * @throws BizException
	 */
	List<PacketExtend> check(String acc, int aid) throws BizException;
}
