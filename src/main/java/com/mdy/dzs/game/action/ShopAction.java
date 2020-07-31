package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

public interface ShopAction {

	/**
	 * stat：招募列表 client->server: Get: ?a=stat&acc=* Post:
	 * {"m":"shop","a":"stat","acc":"*"}
	 * 
	 * acc: 账号
	 * 
	 * client<-server: {"0":err, "1":{n:*}, [t2,t3], cnt, ary}
	 * 
	 * err: 错误信息 n： 携带道具数 t2： 下次底额招募时间 t3： 下次高额招募时间 cnt: '名震江湖'下次抽取5星卡次数 ary：
	 * [江湖新秀，武林高手，名镇江湖 ]抽取次数
	 * 
	 * @param acc
	 * @param cid
	 * @return
	 * @throws BizException
	 */
	List<Serializable> wineShopState(String acc) throws BizException;

	/**
	 * wine：招募 client->server: Get: ?a=wine&acc=*&t=1/2/3&n=1/10 Post:
	 * {"m":"shop","a":"wine","acc":"*","t":1/2/3,"n":1/10}
	 * 
	 * acc: 账号 t: 招募类别 1-江湖新秀 2-武林高手 3-名震江湖 n: 招募数量
	 * 
	 * client<-server: {"0":err, "1":[{t:2,id:*,n:*,objId:*},...], time, goldNum}
	 * 
	 * err: 错误信息 t：2 从card表读取卡片数据 id: 卡牌id n: 卡片数量 objId: 卡牌objId time: 距下次免费招募的时间差
	 * s goledNum： 剩余金币
	 * 
	 * @param acc
	 * @param t
	 * @param n
	 * @return
	 * @throws BizException
	 */

	List<Serializable> wineShop(String acc, int t, int n) throws BizException;

	/**
	 * list：道具列表 client->server: Get: ?a=list&acc=* Post:
	 * {"m":"shop","a":"list":"*","acc":"*"}
	 * 
	 * acc: 账号
	 * 
	 * client<-server: {"0":err, "1":itemsAry, "2":purchasedAry}
	 * 
	 * itemsAry: 道具数组 purchasedAry： [{id:商品id, cnt:可购买数 ,
	 * max:购买上限,hadCnt:已有数量,hadBuy:已购买数},...] 【max == -1,无上限】
	 * 
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryShopList(String acc) throws BizException;

	/**
	 * oList: 查看一个道具的可购买状态 client->server: Get: ?a=oList&acc=*&id=* Post:
	 * {"m":"shop","a":"oList","acc":"*","id":"*"}
	 * 
	 * acc: 账号 id: 道具id
	 * 
	 * client<-server: {"0":err, "1":itemData "2":itemNum}
	 * 
	 * err: 错误信息 itemData： {id:商品id, cnt:可购买数量,coin:花费, coinType:货币类型, gold:金币,
	 * silver:银币} itemNum: 道具数量
	 * 
	 * @param acc
	 * @param id
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryShopItem(String acc, int id) throws BizException;

	/**
	 * buy：道具购买 client->server: Get: ?a=buy&acc=*&id=*&n=*&coinType=*&coin=* Post:
	 * {"m":"shop","a":"buy":"*","acc":"*","id":"*","n":"*","coinType":"*","coin":"*"[&auto=1]}
	 * 
	 * acc: 账号 id: 道具表id n: 购买数量k coinType： 货币类型 coin： 花费数额 auto: 战斗时自动购买使用耐力体力
	 * 
	 * client<-server: {"0":err, "1":rtnObj,"2":gold, "3":silver}
	 * 
	 * rtnObj: {id:商品id, price:下次购买花费, hadBuy:已购买数} gold: 当前金币 silver： 当前银币
	 * 
	 * @param acc
	 * @param id
	 * @param n
	 * @param coinType
	 * @param coin
	 * @param auto
	 * @return
	 * @throws BizException
	 */
	List<Serializable> buyShopItem(String acc, int id, int n, int coinType, int coin, int auto) throws BizException;
}
