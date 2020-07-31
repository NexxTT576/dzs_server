/**
 * 
 */
package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.arena.RoleArenaRoleVO;
import com.mdy.sharp.container.biz.BizException;

/**
 * 竞技场
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月10日 上午10:34:50
 */
public interface ArenaAction {

	/**
	 * 对手列表 @return{"0":err, "1":listAry, "2":rank, "3":popual "4":time "5":tType}
	 * 
	 * err: 错误信息 listAry： 列表数组 [{acc: 账号 level: 等级 name: 名 vip: vip等级 rank: 排名
	 * getSilver: 可获银两 getPopual: 可获声望 card: [{resId：资源id cls：阶 pos:位置 }] }...]
	 * rank: 当前玩家排名 popual: 当前玩家声望 time: 领奖倒计时 tType: 倒计时类型 1-发奖结束倒计时 2-挑战结束倒计时
	 * 
	 * @throws BizException
	 */
	List<Serializable> queryChallengeList(String acc) throws BizException;

	/**
	 * 排名检查
	 * 
	 * @param acc   账号
	 * @param acc2  对手账号
	 * @param rank2 对手排名
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryChallengeCheck(String acc, String acc2, int rank2) throws BizException;

	/**
	 * 挑战
	 * 
	 * @param acc
	 * @param challengeRank @return{"0":err, "1":[rst], "2":[cor] "3":rtnAry,
	 *                      "4":coinAry, "5":obj, "6":isDare, "7":level, "8":exp}
	 * 
	 *                      err: 错误信息 rst 战斗结果 cor: 战斗过程 rtnAry: 掉落物品[第一个默认选中获得] 过程
	 *                      coinAry: [{t:*, id:*, n:*},...]奖励货币类型 obj:
	 *                      {resisVal:耐力值,attack1:对手战斗力,attack2:玩家战斗力,name1:玩家名,name2:对手名,rankType:排名变化,newRank:新排名}
	 *                      isDare: 1-战斗 2-error 3-发奖中 level: 当前等级 exp: 当前经验值
	 * @throws BizException
	 */

	List<Serializable> challenge(String acc, int challengeRank) throws BizException;

	/**
	 * 查询前十排行
	 * 
	 * @return
	 */
	List<RoleArenaRoleVO> queryRankList(String acc) throws BizException;

	/**
	 * 发放排行榜奖励
	 * 
	 * @throws BizException
	 */

	void sendRankAward() throws BizException;

	/**
	 * 查询竞技场商店
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	List<Serializable> queryShopList(String acc, int shoptype) throws BizException;

	/**
	 * 兑换竞技场商店物品
	 * 
	 * @param acc
	 * @param id
	 * @param num
	 * @param shopType
	 * @return
	 * @throws BizException
	 */

	List<Serializable> exchange(String acc, int id, int num, int shoptype) throws BizException;
}
