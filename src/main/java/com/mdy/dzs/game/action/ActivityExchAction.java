package com.mdy.dzs.game.action;

import com.mdy.dzs.game.domain.activity.exchange.CActExch;
import com.mdy.dzs.game.domain.activity.exchange.CActExchAward;
import com.mdy.dzs.game.domain.activity.exchange.CActExchList;
import com.mdy.dzs.game.domain.activity.exchange.CActExchRefresh;
import com.mdy.sharp.container.biz.BizException;

public interface ActivityExchAction {

	/**
	 * 进入限时兑换
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	CActExchList list(String acc) throws BizException;

	/**
	 * 奖励预览
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	CActExchAward award(String acc) throws BizException;

	/**
	 * 刷新
	 * 
	 * @param acc
	 * @param exchId
	 * @return
	 * @throws BizException
	 */

	CActExchRefresh refresh(String acc, int exchId) throws BizException;

	/**
	 * 兑换
	 * 
	 * @param acc
	 * @param exchId
	 * @return
	 * @throws BizException
	 */

	CActExch exch(String acc, int exchId) throws BizException;

	/**
	 * 重新load配置
	 * 
	 * @param acc
	 * @param exchId
	 * @return
	 * @throws BizException
	 */
	void reloadActivitys();

}
