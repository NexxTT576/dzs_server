package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

/**
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月23日 下午5:00:52
 */
public interface RoleChannelAction {

	/**
	 * main: 界面 client->server: Get: ?m=channel&a=main&acc=* Post: { "m":"channel",
	 * "a":"main", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {"0":err, "1": starCnt, "2":point, "3":gold, "4":silver,
	 * "5":level, "6":type, "7":itemCnt} err: 错误信息 starCnt: 副本星数 point： 下个升级穴位 [0-8]
	 * gold： 金币数 silver： 银币数 level: 升级后等级 [0-10] chanType: 经脉类型 0-无 1-神武 2-太乙 3-归墟
	 * itemCnt: 道具数 chanReSetCnt: 洗经伐脉次数 【point && level 都为0时，表示所有穴位升至满级】
	 * 
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryRoleChannelInfo(String acc) throws BizException;

	/**
	 * lvUp: 升级 client->server: Get: ?m=channel&a=lvUp&acc=*&[&t=*] Post: {
	 * "m":"channel", "a":"lvUp", "acc":"*", ["t":"*"] } acc: 账号 t: 经脉类型 [选定经脉类型时需要]
	 * 
	 * client<-server: {"0":err, "1": starCnt, "2":silver, "3":rtLevel, "4":rtPoint}
	 * err: 错误信息 starCnt: 副本星数 silver： 银币数 rtLevel: 下个升级等级 rtPoint： 下个升级穴位 【rtPoint
	 * && rtLevel 都为0时，表示所有穴位升至满级】
	 * 
	 * @param acc
	 * @param t
	 * @return
	 * @throws BizException
	 */

	List<Serializable> levelUp(String acc, int t) throws BizException;

	/**
	 * reset: 洗经伐脉 client->server: Get: ?m=channel&a=reset&acc=* Post: {
	 * "m":"channel", "a":"reset", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {"0":err, "1": starCnt, "2":gold, "3":itemCnt} err: 错误信息
	 * starCnt: 副本星数 gold: 金币数 itemCnt： 道具数
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	List<Serializable> reset(String acc) throws BizException;
}
