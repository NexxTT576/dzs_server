/**
 * 
 */
package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

/**
 * 炼化接口
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月23日 下午8:43:41
 */
public interface FurnaceAction {

	/**
	 * flist: 炼化列表 client->server: Get: ?a=flist&acc=* Post:
	 * {"m":"furnace","a":"flist","acc":"*"}
	 * 
	 * acc: 账号
	 * 
	 * client<-server: {"0":err, "1":cAry, "2":eAry}
	 * 
	 * err: 错误信息 cAry 武将组
	 * [id:武将组内id,resId:武将id,level:等级,star:星级,cls:进阶,curExp:当前经验值,[{id:itemid,num:itemNum}...}]]
	 * eAry: 装备组
	 * [id:装备组内id,resId:装备id,level:等级,star:星级,base:[生命,攻击,物防,法防,最终伤害,最终免伤],sliver:银币
	 * ,[{id:itemid,num:itemNum}...}]]
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryFurnaceList(String acc) throws BizException;

	/**
	 * furnace: 炼化 client->server: Get: ?a=furnace&acc=*type=1/2&ids=[id,id,*] Post:
	 * {"m":"furnace","a":"furnace","acc":"*","type":1/2,"ids":[id,id,...]}
	 * 
	 * acc: 账号 type: 1:武将 2:装备 ids： 炼化物品id
	 * 
	 * client<-server: {"0":err, "1":Ary ,2:cards}
	 * 
	 * err: 错误信息 Ary： 获得物品id和数量 cards: 删除的卡组
	 * 
	 * @param acc
	 * @param type
	 * @param ids
	 * @return
	 * @throws BizException
	 */

	List<Serializable> furnace(String acc, int type, List<Integer> ids) throws BizException;

	/**
	 * reborn: 重生 client->server: Get: ?a=reborn&acc=*type=1/2&ids=[id,id,*] Post:
	 * {"m":"furnace","a":"reborn","acc":"*","type":1/2,"ids":[id,id,...]}
	 * 
	 * acc: 账号 type: 1:武将 2:装备 ids： 重生物品id
	 * 
	 * client<-server: {"0":err, "1":Ary ,2:cards}
	 * 
	 * err: 错误信息 Ary： 获得物品id和数量 cards: 重生后改变的卡组
	 * 
	 * @param acc
	 * @param type
	 * @param ids
	 * @return
	 * @throws BizException
	 */

	List<Serializable> reborn(String acc, int type, int id) throws BizException;
}
