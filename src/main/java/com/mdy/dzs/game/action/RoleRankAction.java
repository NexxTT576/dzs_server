package com.mdy.dzs.game.action;

import com.mdy.dzs.game.domain.rank.RoleRankInfo;
import com.mdy.sharp.container.biz.BizException;

/**
 * 排行榜Acion接口
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2015年1月29日 下午6:00:40
 */
public interface RoleRankAction {

	/**
	 * 根据 type 查询排行数据
	 * 
	 * @param type
	 * @return
	 */
	RoleRankInfo getRoleRankInfo(String acc, int type) throws BizException;

	/**
	 * 刷新排名
	 */

	void refreshRank();

	/**
	 * 更新到数据库
	 */

	void updateRank();
}
