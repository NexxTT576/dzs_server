package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.tournament.CEnemyVO;
import com.mdy.dzs.game.domain.tournament.CExchangeList;
import com.mdy.dzs.game.domain.tournament.CExchangeVO;
import com.mdy.dzs.game.domain.tournament.CRankVO;
import com.mdy.dzs.game.domain.tournament.CTournamentSelfInfoVO;
import com.mdy.dzs.game.domain.tournament.TournamentBuyVO;
import com.mdy.sharp.container.biz.BizException;

public interface TournamentAction {

	// 获得个人信息（包括生成3个对手）
	// {
	// 数据结构（个人信息： 耐力 次数 排名 积分 可挑战次数 距离下次刷新列表的时间）
	// （对手列表3人）（对手列表ids 存放在比武列表中）
	//
	// }
	/**
	 * getInfo：进入比武 client->server: Get: ?a=getInfo&acc=* Post: {"m":"tournament",
	 * "a":"getInfo", "acc":"*", } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { resisVal;耐力值 challengeTimes;挑战次数
	 * rank;排名 nextFleshTime;下次刷新时间 score;积分 cost:下次购买消耗 buy_num：可购买次数
	 * opponents;对手列表 { [roleId;玩家的角色id level;玩家的等级 name;玩家的名字 pos;玩家的位置
	 * quality;玩家的品质 leadId;主角的id]..... cls:阶 } } }
	 */

	CTournamentSelfInfoVO getInfo(String acc) throws BizException;

	/**
	 * refresh：刷新 client->server: Get: ?a=refresh&acc=* Post: {"m":"tournament",
	 * "a":"refresh", "acc":"*", } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { nextFleshTime;下次刷新时间 opponents;对手列表 {
	 * [roleId;玩家的角色id level;玩家的等级 name;玩家的名字 pos;玩家的位置 quality;玩家的品质
	 * leadId;主角的id]..... cls：阶 } } }
	 */

	CTournamentSelfInfoVO refresh(String acc) throws BizException;

	// 挑战
	// {
	// 每次挑战完 进行一次排名
	// }
	// 获得天榜列表（前十名）（是否要包含自己的信息， 如果在前20名可以挑战前十名， 挑战是要进行查询排序 因为可能别人正在打 改变了排行）
	// {
	// 获取比武列表 按照积分排行前十的数据
	// }
	/**
	 * rankList：排行列表 client->server: Get: ?a=rankList&acc=* Post: {"m":"tournament",
	 * "a":"rankList", "acc":"*", } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { list;列表信息 { [ role_id;角色id level;等级
	 * private String name;名字 attack;战力 score;积分 rank;排名 cards;卡牌 { [ cardId;卡牌id
	 * resId;卡牌类型id level;等级 cls;阶数 ] } ]................. } self;自己的信息 { roleId;角色
	 * rank;排名 score;积分 } } }
	 */
	CRankVO rankList(String acc) throws BizException;

	// 重置天榜
	// {
	// 清空
	// }
	// 仇人列表（最多50个人）
	// {
	// 所有攻击过我的玩家都会进入此列表，时间最近者排在最前端
	// }
	/**
	 * enemyList：仇人列表 client->server: Get: ?a=enemyList&acc=* Post:
	 * {"m":"tournament", "a":"enemyList", "acc":"*", } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { [ role_id;角色id level;等级 private String
	 * name;名字 attack;战力 score;积分 cards;卡牌 { [ cardId;卡牌id resId;卡牌类型id level;等级
	 * cls;阶数 ] } ].................
	 * 
	 * } }
	 */
	CEnemyVO enemyList(String acc) throws BizException;

	// 重置仇人列表
	// {
	// 清空
	// }

	// 兑换（相应物品已经兑换过的次数）
	// {
	// 具有相应兑换次数的商品
	// }
	/**
	 * exchangeList：兑换列表 client->server: Get: ?a=exchangeList&acc=* Post:
	 * {"m":"tournament", "a":"exchangeList", "acc":"*", } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { roleId：角色Id honor:荣誉值 list:[
	 * roleId;角色id itemId;物品id number;剩余次数 ].................
	 * 
	 * } }
	 */
	CExchangeList exchangeList(String acc) throws BizException;

	// 兑换
	// {
	// 兑换一次 记录一次
	// }
	/**
	 * exchange：兑换 client->server: Get: ?a=exchange&acc=*&itemId=*&num=* Post:
	 * {"m":"tournament", "a":"exchangeList", "acc":"*", "itemId":"*", "num":"*", }
	 * acc: 账号 itemId: 物品类型id num：兑换的个数
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { roleId;角色id itemId;物品id number;剩余次数
	 * honor:荣誉值 gold:元宝 silver：银币 packetOut：背包已经满了 [ { type; cost; size; id;
	 * curCnt; limit; }.... ] } }
	 */

	CExchangeVO exchange(String acc, int itemId, int num) throws BizException;
	// 重置兑换
	// {
	// 清空
	// }

	/**
	 * buy：购买 client->server: Get: ?a=buy&acc=* Post: {"m":"tournament", "a":"buy",
	 * "acc":"*", } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { roleId;角色id gold;金币 times;剩余挑战次数 } }
	 */

	TournamentBuyVO buy(String acc, int num) throws BizException;

	/**
	 * check：验证排行和背包 client->server: Get: ?a=check&acc=*&roleId=*&type=* Post:
	 * {"m":"tournament", "a":"check", "acc":"*", "roleId":"*", "type":"*" } acc: 账号
	 * roleId: 对手id type： 1 比武 2 复仇 3 挑战排行 client<-server: { 1：正常 2：排行发生变化 3：背包满 }
	 */
	List<Serializable> check(String acc, int roleId, int type) throws BizException;

	/**
	 * challenge：挑战敌人 client->server: Get: ?a=challenge&acc=*&roleId=*&type=* Post:
	 * {"m":"tournament", "a":"challenge", "acc":"*", "roleId":"*", "type":"*" }
	 * acc: 账号 roleId: 对手id type： 1 比武 2 复仇 3 挑战排行 client<-server: { }
	 */

	List<Serializable> challenge(String acc, int roleId, int type) throws BizException;

	void award() throws BizException;

	void resetRank();

	void resetExchange();

	void resetEnemy();

	void insertData();
}
