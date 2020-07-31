package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.card.RoleCardListVO;
import com.mdy.sharp.container.biz.BizException;

public interface RoleCardAction {
	// 获取卡牌列表
	RoleCardListVO queryList(String str) throws BizException;

	// 获取单张卡牌信息
	// m=card&a=msg&acc=*&cid=* / {"m":"card","a":"msg","acc":"*"}
	// {err:'', d:[{base:*,baseRate:*,...}]}

	List<Serializable> msg(String acc, int cid) throws BizException;

	/**
	 * 
	 * clsUp：侠客进阶 client->server: Get: ?a=clsUp&acc=*&id=* Post: {"m":"card",
	 * "a":"clsUp", "acc":"*", "id”:””， “op”:1/2 //1只是显示 2是进阶 }
	 * 
	 * acc: 账号
	 * 
	 * client<-server: {"0":err, "1": bAllow, "1":cardInfoCur, "2":cardInfoNxt,
	 * "3":itemInfo, "4":silver}
	 * 
	 * err: 错误信息 bAllow： 是否允许进阶 1/0 cardInfoCur： 侠客信息 {name名称:*, lv等级:*, cls阶数:*,
	 * star星数:*, base属性数组:[]} cardInfoNxt: 侠客进阶后信息 {name名称:*, lv等级:*, cls阶数:*,
	 * star星数:*, base属性数组:[]} itemInfo: 消耗物品数组[{id:*, t:type, n1:*, n2:*},...]
	 * type:1:装备 2:时装 3:装备碎片 4:内外功 5:武将碎片 6:精元 7:可使用物品8:武将9:内功碎片10：外功碎片11：礼品12：材料
	 * silver: 消耗银币数
	 * 
	 * @param acc
	 * @param id
	 * @param op  1只是显示 2是进阶
	 * @return
	 * @throws BizException
	 */

	List<Serializable> clsUp(String acc, int id, int op) throws BizException;

	/**
	 * levelUp：侠客强化 client->server: Get: ?a=lvUp&acc=*&cids=*&op=1/2 Post:
	 * {"m":"card", "a":"lvUp", "acc":"*", "cids":"mainCard,card1,card2,...",
	 * "op":"1/2" } acc: 账号 cids: 主卡，消耗卡数组 op: 1仅计算/2确认强化
	 * 
	 * client<-server: {"0":err, "1": cardInfo, "2":silver}
	 * 
	 * err: 错误信息 cardInfo： 侠客信息 {name名称:*, lv等级:*, cls阶数:*, star星数:*, base属性数组:[],
	 * add增加属性:[] //op=2时，返回值为空 cost消耗银币:* exp获得经验:* }
	 * 
	 * @param acc
	 * @param cids 主卡，消耗卡数组
	 * @param op   1仅计算/2确认强化
	 * @return
	 * @throws BizException
	 */

	List<Serializable> levelUp(String acc, List<Integer> idAry, int op) throws BizException;

	/**
	 * soulUp：侠客侠魂强化 client->server: Get: ?a=soulUp&acc=*&cid=*&op=1/0 Post:
	 * {"m":"card", "a":"soulUp", "acc":"*", "id":"", "op":"1/2" "n":"1/5" } acc: 账号
	 * id: 强化主卡id op: 1确认强化/2仅计算 n:强化1/5次
	 * 
	 * 
	 * client<-server: {"0":err, "1": cardInfo, "2":silver, "3":endLevel}
	 * 
	 * err: 错误信息 cardInfo： 侠客信息 {name名称:*, lv等级:*, cls阶数:*, star星数:*, base属性数组:[],
	 * add增加属性:[] hun:[*,*] //当前将魂/下级所需将魂 cost消耗银币:* exp获得经验:* }
	 * endLevel:op=1时，返回可升级到的等级
	 * 
	 * @param acc
	 * @param id
	 * @param op
	 * @param n
	 * @return
	 * @throws BizException
	 */

	List<Serializable> soulUp(String acc, int id, int op, int n) throws BizException;

	/**
	 * 
	 * 神通升级 client->server: Get: ?a=shenUp&acc=*&id=*&ind=0/1/2 Post: {"m":"card",
	 * "a":"shenUp", "acc":"*", "id":"", "ind":"", } acc: 账号 id: 神通卡id ind:
	 * 升级id索引(0/1/2)
	 * 
	 * 
	 * client<-server: {"0":err, "1": shenID, "2":shenLv, "3":shenPt}
	 * 
	 * err: 错误信息 shenID： 升级后神通id shenLv： 升级后神通等级 shenPt： 剩余神通点数
	 * 
	 * @param acc 账号
	 * @param id  神通卡id
	 * @param ind 升级id索引(0/1/2)
	 * @return
	 * @throws BizException
	 */

	List<Serializable> shenUp(String acc, int id, int ind) throws BizException;

	/**
	 * 神通重置 client->server: Get: ?a=shenReset&acc=*&id=* Post: {"m":"card",
	 * "a":"shenReset", "acc":"*", "id":"" } acc: 账号 id: 卡id
	 * 
	 * 
	 * client<-server: {"0":err, "1": shenIDAry, "2":shenLvAry, "3":shenPt}
	 * 
	 * err: 错误信息 shenIDAry:神通数组 shenLvAry:神通等级数组 shenPt： 剩余神通点数
	 * 
	 * @param acc
	 * @param id
	 * @return
	 * @throws BizException
	 */

	List<Serializable> shenReset(String acc, int id) throws BizException;

	/**
	 * //出售卡牌 //m=card&a=sell&acc=*&cids="*,*,.." /
	 * {"m":"card","a":"sell","acc":"*","cids":"*,.."} //{err:'',
	 * d:[priceSell,curSilver]}
	 * 
	 * @param acc
	 * @param cids
	 * @return
	 * @throws BizException
	 */

	List<Serializable> sellCard(String acc, List<Integer> idAry) throws BizException;

	/**
	 * 锁定卡片
	 * 
	 * @param acc
	 * @param id
	 * @throws BizException
	 */

	void lockCard(String acc, int id, int lock) throws BizException;
}
