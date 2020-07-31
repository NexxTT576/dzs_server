/**
 * 
 */
package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

/**
 * 名将之路
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月15日 下午2:15:22
 */
public interface RoadAction {

	/**
	 * 查询名将信息
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryRoadInfo(String acc) throws BizException;

	/**
	 * 
	 * @param acc
	 * @param cardId
	 * @param useType
	 * @param itemId
	 * @return
	 * @throws BizException
	 */

	List<Serializable> useGift(String acc, int cardId, int useType, int itemId) throws BizException;
}
