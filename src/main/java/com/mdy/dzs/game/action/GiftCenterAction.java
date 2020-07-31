/**
 * 
 */
package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.game.domain.giftcenter.RoleGift;
import com.mdy.sharp.container.biz.BizException;

/**
 * 领奖中心
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月11日 下午5:19:14
 */
public interface GiftCenterAction {

	/**
	 * 领奖中心列表
	 * 
	 * @param acc
	 * @return 账号
	 * @throws BizException
	 */

	List<RoleGift> queryGiftCenterList(String acc) throws BizException;

	/**
	 * 领奖
	 * 
	 * @param acc
	 * @param type
	 * @param giftId
	 * @return
	 * @throws BizException
	 */

	List<Serializable> getGift(String acc, int type, int giftId) throws BizException;

	/**
	 * 等级礼包
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryLevelGiftList(String acc) throws BizException;

	/**
	 * 等级礼包领取
	 * 
	 * @param acc
	 * @param level 领取礼包的等级
	 * @return
	 * @throws BizException
	 */

	List<Serializable> getLevelGift(String acc, int level) throws BizException;

	/**
	 * 在线礼包
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryOnLineGiftList(String acc) throws BizException;

	/**
	 * 在线礼包领取
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	List<Serializable> getOnLineGift(String acc) throws BizException;

	/**
	 * 签到礼包
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	List<Serializable> querySignGiftList(String acc) throws BizException;

	/**
	 * 签到礼包领取
	 * 
	 * @param acc
	 * @param signDay 领取礼包的天数
	 * @return
	 * @throws BizException
	 */

	List<Serializable> getSignGift(String acc, int signDay) throws BizException;

	/**
	 * 在线登陆礼包
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryLoginDayGiftList(String acc) throws BizException;

	/**
	 * 在线登陆礼包领取
	 * 
	 * @param acc
	 * @param day 领取礼包的天数
	 * @return
	 * @throws BizException
	 */

	List<Serializable> getLoginDayGift(String acc, int day) throws BizException;

	List<Integer> sendCDKeyGift(String acc, String cdKey, List<GiftItem> list) throws BizException;

	/**
	 * 清理过期礼包
	 */

	void clearExpiredGift();

	/**
	 * 发放奖励中心奖励
	 * 
	 * @throws BizException
	 */

	void sendRewardCenterGifts(String serverName) throws BizException;
}
