/**
 * 
 */
package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.snatch.BeSnatchRole;
import com.mdy.dzs.game.domain.snatch.SnatchTenVO;
import com.mdy.sharp.container.biz.BizException;

/**
 * 夺宝
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月13日 下午2:16:49
 */
public interface SnatchAction {

	/**
	 * 查询可夺宝的碎片
	 * 
	 * @param acc 账号
	 * @return
	 * @throws BizException
	 */
	List<Serializable> querySnatchItems(String acc) throws BizException;

	/**
	 * 使用免战牌
	 * 
	 * @param acc  账号
	 * @param type 类型 1：免战牌 2：金币
	 * @return
	 * @throws BizException
	 */

	List<Serializable> userItem(String acc, int type) throws BizException;

	/**
	 * 合成内外功
	 * 
	 * @param acc  账号
	 * @param id   道具id
	 * @param type 类型 1：合成 2：批量合成
	 * @return
	 * @throws BizException
	 */

	List<Serializable> synthGong(String acc, int id, int type) throws BizException;

	/**
	 * 可抢夺列表
	 * 
	 * @param acc    账号
	 * @param itemId 碎片id
	 * @return
	 * @throws BizException
	 */

	List<Serializable> querySnatchRoles(String acc, int itemId) throws BizException;

	/**
	 * 抢夺碎片
	 * 
	 * @param acc
	 * @param role
	 * @param itemId
	 * @return
	 * @throws BizException
	 */

	List<Serializable> snatch(String acc, BeSnatchRole role, int itemId) throws BizException;

	/*
	 * 夺十次 client->server: Get: /snatch/snatchTen?acc=*&data=*&id=* Post: { "m":
	 * "snatch", "a": "snatchTen", "acc":"*", "data":"*", "id": "*" } acc: 账号 data:
	 * 被抢夺数据 {lv: 等级 name: 名 type: 1/2 真实玩家/NPC warFreeTime: 免战剩余时间 ids: 头像id vip:
	 * vip等级 card: [{name:名 resId：资源id star：品质}] } id: 碎片id
	 * 
	 * client<-server: { err: 错误信息 rtnObj: {isGetItem: 是否夺到碎片 【1-夺到 2-未夺到】 getIndex:
	 * 第*次夺得碎片 level: 当前等级 exp: 当前经验值 snatchMap：{ 1：{ probItem： 掉落物品
	 * {type:*,id:*,num:*} coinAry: 奖励货币类型 [ {t:*, id:*, n:*} ... ] } } } }
	 */

	SnatchTenVO snatchTen(String acc, BeSnatchRole role, int id) throws BizException;
}
