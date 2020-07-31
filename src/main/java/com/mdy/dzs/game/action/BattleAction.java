package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.battle.BattleFieldVO;
import com.mdy.dzs.game.domain.battle.BattleWorldVO;
import com.mdy.sharp.container.biz.BizException;

public interface BattleAction {

	/**
	 * 获取field列表
	 * 
	 * @param acc
	 * @param id  world表副本id
	 * @return {@link BattleWorldVO}
	 * @throws BizException
	 */
	BattleWorldVO queryWorldInfo(String acc, int id) throws BizException;

	/**
	 * 获取关卡列表
	 * 
	 * @param acc
	 * @param id  field表副本id
	 * @return {@link BattleFieldVO}
	 * @throws BizException
	 */
	BattleFieldVO queryFieldInfo(String acc, int id) throws BizException;

	/**
	 * pve
	 * 
	 * @param acc
	 * @param id   level id
	 * @param type 1简单,2中等,3难度
	 * @return
	 * @throws BizException
	 */

	List<Serializable> battle(String acc, int id, int type) throws BizException;

	/**
	 * 连战冷却清除
	 * 
	 * @param acc 账号
	 * @param t   类型:1道具/2元宝
	 * @return 剩余元宝
	 * @throws BizException
	 */

	int clearCD(String acc, int t) throws BizException;

	/**
	 * 副本领取奖励
	 * 
	 * @param acc
	 * @param id  field表副本id
	 * @param t   1简单/2中等/3难度
	 * @return [err, packetAry,numAry]
	 * @throws BizException
	 */

	List<Serializable> award(String acc, int id, int t) throws BizException;

	/**
	 * pve
	 * 
	 * @param acc
	 * @param id   level id
	 * @param n    连战次数
	 * @param type 1简单,2中等,3难度
	 * @return {"0":err, "1":cnt, "2":rstAry, "3":lvAry, "4":curLv, "5":curExp} err:
	 *         错误信息 cnt: 连战次数 rstAry: 连战结果数组 [ [{t:*,id:*,n:*},...], //第1次战斗奖励数组
	 *         [{t:*,id:*,n:*},...], //第2次战斗奖励数组 [{t:*,id:*,n:*},...], //第3次战斗奖励数组
	 *         ... ] lvAry: 连战等级数组 [{lv:*, exp:*, limit:*}, ...] curLv: 当前等级 curExp:
	 *         当前经验值
	 * @throws BizException
	 */
	List<Serializable> battles(String acc, int id, int type, int n) throws BizException;

	/**
	 * 购买副本次数
	 * 
	 * @param acc
	 * @param id
	 * @param act
	 * @return
	 * @throws BizException
	 */
	Map<String, Integer> buyBattleCnt(String acc, int id, int act) throws BizException;
}
