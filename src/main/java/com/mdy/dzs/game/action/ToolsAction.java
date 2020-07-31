/**
 * 
 */
package com.mdy.dzs.game.action;

import com.mdy.sharp.container.biz.BizException;

/**
 * 工具接口
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月9日 下午5:13:24
 */
public interface ToolsAction {

	/**
	 * 创建玩家机器人
	 * 
	 * @throws BizException
	 */
	void createRobotByConfig() throws BizException;
}
