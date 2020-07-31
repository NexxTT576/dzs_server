package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

public interface LineupAction {

	/**
	 * list：阵容列表 — client->server: Get: ?m=fmt&a=list&acc=*&pos=*[&acc2=*] Post:
	 * {"m":"fmt", "a":"list", "acc":"*", "pos":"*", ["acc2":"*"] } acc: 账号 pos:
	 * 显示位置(1-6，为0时返回全部卡牌) [acc2: 要查看的其他玩家的阵容]
	 * 
	 * 
	 * client<-server: {"0":err, "1": cardAry, "2":equipAry "3":yuanAry}
	 * 
	 * err: 错误信息 cardAry： 上阵侠客信息 [{objId：*, name名称:*, resId资源id:*, level等级:*,
	 * levelLimit等级上限:*, cls阶数:*, star星数:*, base属性数组:[生命，攻击，物防，法防], relation:[*,*]
	 * pos位置:*, //1-6 lead: [体质,力量,悟性] },...]
	 * 
	 * equipAry:[[{ objId：*, resId装备资源ID(item表):* level等级:* pos装备位置：* subpos装备位置：*
	 * },...] //1号位装备数组 [], //2号位装备数组 [],...] //3号位装备数组 yuanAry:[[ind,ind] //1号位精元数组
	 * [], //2号位精元数组 [],...] //3号位精元数组
	 * 
	 * 
	 * @param seeAcc 被查看的账号
	 * @param pos    显示位置(1-6，为0时返回全部卡牌)
	 * @return
	 * @throws BizException
	 */
	List<Serializable> seeLineup(String seeAcc, int pos) throws BizException;

	/**
	 * 验证刷新阵型
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	List<Integer> verifyLineup(String acc) throws BizException;

	/**
	 * embattle：更换/布阵/上阵 -- 将卡牌更换/布置到pos位置/上阵卡牌 client->server: Get:
	 * ?m=fmt&a=embattle&acc=*[&pos=*]&subpos=*[&id=*] Post: { "m":"fmt",
	 * "a":"embattle", "acc":"*", ["pos":*,] "subpos":"0/1/2/3/4/5/6..." ["id":"*"]
	 * } acc: 账号 pos: 位置： 1-6卡牌 [没有时：按策划规定位置默认上阵][2,5,3,4,6,1] subpos:
	 * (卡牌为0)装备1-4，内外功5-6,精元7-14 id: 添加/换上的物品id [无id为卸下该位置的物品]
	 * 
	 * client<-server: 同阵容列表
	 * 
	 * @param acc
	 * @param pos
	 * @param subpos
	 * @param id
	 * @return
	 * @throws BizException
	 */

	List<Serializable> changeLineup(String acc, int pos, int subpos, int id) throws BizException;

	/**
	 * embattle：更换/布阵/上阵 -- 将卡牌更换/布置到pos位置/上阵卡牌 client->server: Get:
	 * ?m=fmt&a=embattle&acc=*[&pos=*]&subpos=*[&id=*] Post: { "m":"fmt",
	 * "a":"embattle", "acc":"*", ["pos":*,] "subpos":"0/1/2/3/4/5/6..." ["id":"*"]
	 * } acc: 账号 pos: 位置： 1-6卡牌 [没有时：按策划规定位置默认上阵][2,5,3,4,6,1]
	 * 
	 * client<-server: 同阵容列表
	 * 
	 * @param acc
	 * @param pos
	 * @param subpos
	 * @param id
	 * @return
	 * @throws BizException
	 */

	List<Serializable> fastChangeEquip(String acc, int pos, int itemId, int type) throws BizException;
}
