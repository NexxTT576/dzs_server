package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.packet.PacketListVO;
import com.mdy.sharp.container.biz.BizException;

public interface PacketAction {
	/*
	 * list：列表 client->server: Get: ?a=list&acc=*&t=* Post:
	 * {"m":"packet","a":"list","acc":"*","t":"*"
	 * 
	 * acc: 账号 t: 背包 1装备/2时装/3装备碎片/4内外功/5武将碎片/6精元/7背包/8武将
	 * 
	 * client<-server: {"0":err, "1":itemAry, "2":member, "3":denominator, "4":cost,
	 * "5":size}
	 * 
	 * err: 错误信息 itemAry： 道具数组 [t==1||t==4||t==6 的Ary中‘cid’为装备到的武将id] member： 携带数分子
	 * denominator： 携带数分母 cost: 扩展花费 size: 扩展空间
	 */
	PacketListVO list(String acc, int t) throws BizException;

	// a=gmAdd&acc=*&t=*&id=*&n=*
	/*
	 * t: 0货币 id： 1:金币2:银币3:体力4:耐力5:声望6:主角经验7:侠魂8:公会贡献 n: 数量
	 * 
	 * t: 1:装备 2:时装 3:装备碎片 4:内外功 5:武将碎片 6:精元 7:背包8:武将9:内功碎片10:外功碎片 id： id n: 数量
	 */
	// return:[err]

	void gmAdd(String acc, int t, int id, int n) throws BizException;

	/**
	 * sell：出售 client->server: Get: ?a=sell&acc=*&ids=*,*,*,*... Post:
	 * {"m":"packet","a":"sell","acc":"*","ids":"*,*,*,*..."}
	 * 
	 * acc: 账号 ids: 出售道具id
	 * 
	 * client<-server: {"0":err, "1":addSilver, "2":allSilver}
	 * 
	 * err:错误信息 addSilver： 出售获得银币数 allSilver: 银币总数
	 * 
	 * @param acc
	 * @param ids
	 * @return
	 * @throws BizException
	 */

	List<Serializable> sell(String acc, List<Integer> ids) throws BizException;

	/**
	 * extend：扩充 client->server: get: a=extend&acc=*&type=* post:{"Body": {"m":
	 * "packet","a": "extend","acc": "*","type":*}} acc: 账号 type:
	 * 1：装备2：时装3：装备碎片4：内外功5：武将碎片6：精元7：背包8：武将9：内功碎片10：外功碎片
	 * 
	 * client<-server:
	 * rtn:{“0”：err,“1”：packetLimit,”2“：priceGold,”3“：curGold,"4":cost,"5":size}
	 * packetLimit: 现在上限 priceGold: 花费金币 curGold: 剩余金币 cost: 下次扩展花费 size： 下次扩展获得空间
	 * 
	 * @param acc
	 * @param type
	 * @return
	 * @throws BizException
	 */

	List<Serializable> extend(String acc, int type) throws BizException;

	/**
	 * use: 使用/碎片合成[侠客,装备] client->server: Get: ?a=use&acc=*&id=*&num=* Post:
	 * {"m":"packet","a":"use","acc":"*","id":*,"num":*}
	 * 
	 * acc: 账号 id: 道具id num: 使用数量
	 * 
	 * client<-server: {"0":err, "1":itemAry, "2":rtnAry ["3":gold, "4":silver]
	 * "isFull":true\false "Obj":{}}
	 * 
	 * err: 错误信息 itemAry： 背包道具列表 rtnAry: 获得物品列表 [t:*,id:*,n:*] t:对应道具表中的type id：物品id
	 * n：数量 [gold: ] 当前金币数[背包使用返回] [silver：] 当前银币数[背包使用返回] isFull: true\false
	 * 背包已满\背包不满 Obj: [{type:1-8,cost:扩充花费,size:扩充空间,id:货币类型},...] 背包已不能再扩充时 ==>
	 * cost:-1, size:0
	 * 
	 * [type = 1装备/2时装/3装备碎片/4内外功/5武将碎片/6精元/7背包/8武将]
	 * 
	 * @param acc
	 * @param id
	 * @param num
	 * @return
	 * @throws BizException
	 */

	List<Serializable> use(String acc, int id, int num) throws BizException;

}
