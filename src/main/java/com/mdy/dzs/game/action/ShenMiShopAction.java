package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

public interface ShenMiShopAction {
	/**
	 * list: 兑换物品列表 client->server: Get: ?a=list&acc=*&refresh=* Post:
	 * {"m":"shenmi","a":"list","acc":"*","refresh":*}
	 * 
	 * acc: 账号 refresh:是否刷新 1-刷新 2-获取列表
	 * 
	 * client<-server: {"0":err, "1":listAry,"2":time,"3":hunVal,"4":
	 * refreshNum,"5": refreshType,"6":goldcost,"7":vipFreeTimes,"8":goldRefreshNum}
	 * 
	 * err: 错误信息 listAry： 物品列表数组
	 * [{id:编号,itemId:物品id,upLimit:可兑换次数,type:物品类型,num:数量,money:货币类型,price:价格},...]
	 * 
	 * time: 倒计时 hunVal: 魂玉数 refreshNum： 可刷新次数 1-次数 2-次数 3-总数 refreshType： 可刷新类型
	 * 1-免费 2-刷新令 3-金币 goldcost: 金币刷新花费数 vipFreeTimes: vip免费刷新上限 goldRefreshNum:
	 * 剩余金币刷新次数
	 */

	public List<Serializable> cmdList(String acc, int refresh) throws BizException;

	/**
	 * exchange: 兑换 client->server: Get: ?a=exchange&acc=* Post:
	 * {"m":"shenmi","a":"exchange","acc":"*","id":"*"}
	 * 
	 * acc: 账号 id: 物品编号
	 * 
	 * client<-server: {"0":err, "1":bagStat, "2":hunVal, "3":upLimit, "4":itemAry}
	 * 
	 * err: 错误信息 bagStatAry: 购买物品所在背包状态 //可能为空，即背包未满 [{type:背包类型id, cost:扩充花费,
	 * size:扩充可得空间, curCnt:背包现有物品数 , id:扩充货币类型}] hunVal: 当前魂玉数 upLimit: 可兑换次数
	 * itemAry: 获得物品 [{t:物品类型, id:物品id, n:数量}]
	 */

	public List<Serializable> cmdExchange(String acc, String id) throws BizException;

	/**
	 * verify： 时间校验 client->server: Get: ?a=exchange&acc=* Post:
	 * {"m":"shenmi","a":"verify","acc":"*"}
	 * 
	 * acc: 账号
	 * 
	 * client<-server: {"0":err, "1":time, "2":num}
	 * 
	 * err: 错误信息 time: 时间 num: 免费次数
	 */

	public List<Serializable> cmdVerify(String acc) throws BizException;

}
