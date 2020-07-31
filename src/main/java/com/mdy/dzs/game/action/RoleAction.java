package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.account.IOSDeviceInfo;
import com.mdy.dzs.data.domain.charge.ChargeOrder;
import com.mdy.dzs.game.domain.card.UserInfoVO;
import com.mdy.dzs.game.domain.role.RoleIdName;
import com.mdy.dzs.game.domain.role.RoleLoginInfoVO;
import com.mdy.sharp.container.biz.BizException;

public interface RoleAction {

	/*
	 * login 第三方登录 client->server: Get:
	 * ?m=usr&a=login&SessionId=*&acc=*&platformID=* Post: {"m":"usr", "a":"login",
	 * "SessionId":"*", "acc":"*", "platformID":"*" }
	 * 
	 * SessionId: 用户登录SessionId acc: 用户第三方账号 platformID: 用户标识 '91' '00'
	 * 
	 * 
	 * client<-server: {"0":err "1":baseData "2":ary "3":status}
	 * 
	 * err: 执行成功时，返回值为空 baseData: 基础数值 ary： 活动信息 status： 1-无角色，需新建 2-已有角色
	 */

	RoleLoginInfoVO roleLogin(String accountName, String serverId, IOSDeviceInfo device, String serverKey)
			throws BizException;

	RoleLoginInfoVO roleLogin(int uid) throws BizException;

	RoleLoginInfoVO roleLogin(String acc) throws BizException;

	/*
	 * playerInfo: 刷新主界面 client->server: Get: ?a=playerInfo&acc=* Post: {"m":"usr",
	 * "a":"playerInfo", "acc":"*" } acc: 账号
	 * 
	 * client<-server:{"0":err, "1": dataObj, "2":checkArr, "3":isBuy}
	 * 
	 * err: 错误信息，为空时执行成功 dataObj： {level:等级, cls:阶数, vip:VIP等级, gold:金币, silver:银币,
	 * physVal：[体力, 体力上限],attack:战斗力 resisVal：[耐力, 耐力上限]exp：[经验值，经验值上限]} checkArr: [
	 * 商店--免费抽卡次数 ， 精英副本的次数 ，活动副本的次数 ，签到领奖次数 ,[开服礼包是否显示,开服礼包数] ,[等级礼包显示 ,等级礼包数]
	 * ,[领奖中心显示 ，领奖中心数]] isBuy: 是否有过充值操作 0-没有 1-有
	 */

	List<Serializable> getPlayerInfo(String accountName) throws BizException;

	/*
	 * reg 注册新用户 client->server: Get:
	 * ?m=usr&a=reg&SessionId=*&acc=*&platformID=*&name=*&rid=* Post: {"m":"usr",
	 * "a":"reg", "SessionId":"*", "acc":"*", "platformID":"*" "name":"*", "rid":"*"
	 * }
	 * 
	 * SessionId: 用户登录SessionId acc: 用户第三方账号 platformID: 用户标识 name: 角色名 rid: 主角id
	 * 
	 * client<-server: {"0":err "1":baseData "2":ary}
	 * 
	 * err: 执行成功时，返回值为空 baseData: 基础数值 ary： 活动信息
	 */

	RoleLoginInfoVO register(String accountName, String nickName, String name, String platformID, int rid,
			IOSDeviceInfo deviceInfo, String serverId) throws BizException;

	/*
	 * sleep：客栈状态 client->server: Get: ?m=usr&a=sleep&acc=*&id=* Post: {"m":"usr",
	 * "a":"sleep", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {"0":err, "1": bAllow} err: 错误信息，为空时执行成功 bAllow: 0/1,是否允许睡觉
	 */
	int sleep(String accountName) throws BizException;

	/*
	 * sleepAct：客栈休息 client->server: Get: ?m=usr&a=sleepOp&acc=*&id=* Post:
	 * {"m":"usr", "a":"sleepOp", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {"0":err, "1":physVal} err: 错误信息，为空时执行成功 physVal:最新的体力值
	 */

	int sleepOp(String acc) throws BizException;

	/*
	 * heart: 刷新广播列表 client->server: Get: ?a=heart&acc=* Post: {"m":"usr",
	 * "a":"heart", "acc":"*" } acc: 账号
	 * 
	 * client<-server: {"0":err, "1":
	 * topBroadAry,"2":midBroadAry,"3":playreInfoAry,"4":mailMap,"5":otherObj}
	 * 
	 * err: 错误信息，为空时执行成功 msgAry:[{id:1/2/3,type:2 time:0, color:*, string:*,
	 * data:[{value:玩家名,star:阶数,type:*},{value:武侠/装备/内外功的名字, star:品质,type:*},...]},
	 * {id:*,type:1 time:*, color:*, string:*, data:"*"},...] midBroadAry:
	 * playreInfoAry:[体力，耐力]
	 * mailMap:{"battle":战斗新邮件数,"friend":好友新邮件数,"system":系统新邮件数}
	 * 
	 * otherObj:{ serverTime:*, //服务器时间戳 friend:*, //好友是否有未处理信息 聊天||领取耐力||好友申请 0-无
	 * 1-有 }
	 */

	Map<String, Object> heart(String acc) throws BizException;

	/*
	 * 玩家角色信息 client->server: Get: ?a=uinfo&acc=* Post: {"m":"card", "a":"uinfo",
	 * "acc":"*", } acc: 账号
	 * 
	 * 
	 * client<-server: {"0":err, "1": uInfoObj}
	 * 
	 * err: 错误信息 uInfoObj:{ fmtCnt:"n1,n2", //上阵武将,可上阵武将 gold:*, //元宝数 silver:*,
	 * //银币 soul:*, //侠魂 hunYuVal:*, //魂玉 physVal:*, //体力值 physValLimit:*, //体力上限
	 * physValTime:"t1,t2",//体力回复时间秒数，体力回满时间秒数 resisVal:*, //耐力值 resisValLimit:*,
	 * //耐力上限 resisValTime:"t1,t2"//耐力回复时间秒数，耐力回满时间秒数 }
	 */
	UserInfoVO uinfo(String acc) throws BizException;

	RoleIdName queryIdNameByAcc(String acc) throws BizException;

	/**
	 * 充值成功
	 * 
	 * @param chargeOrder
	 */

	void chargeSuccess(ChargeOrder chargeOrder) throws BizException;

	String getServerKey(String acc);

	// 清理广播表

	void tidyBroadcastTable();

	/**
	 * @param acc
	 * @return
	 */

	String getVersion(String acc);

	/**
	 * 踢掉所有人
	 */

	void kickRoles();

	void updateRoleAttacks() throws BizException;

	void updateRoleAttack(String acc) throws BizException;
}
