package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

public interface HelpAction {

	/**
	 * getGuide: 获取新手引导步骤 client->server: Get: m=help?a=getGuide&acc=* Post:
	 * {"m":"help","a":"getGuide","acc":"*"}
	 * 
	 * acc: 账号 client<-server: {"0":err, "1":guideStep}
	 * 
	 * err: 错误信息 guideStep： 新手引导步骤
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	int getGuide(String acc) throws BizException;

	/**
	 * client->server: Get: m=help?a=setGuide&acc=*&guide=* Post:
	 * {"m":"help","a":"setGuide","acc":"*"}
	 * 
	 * acc: 账号 guide: 存放的新手引导的步骤 client<-server: {"0":err, "1":guideStep}
	 * 
	 * err: 错误信息 guideStep： 新手引导步骤
	 * 
	 * @param acc
	 * @param step
	 * @throws BizException
	 */
	void setGuide(String acc, int step) throws BizException;

	/**
	 * 
	 * @param acc
	 * @param type
	 * @return
	 * @throws BizException
	 */
	Serializable getUserParam(String acc, String type) throws BizException;

	/**
	 * 
	 * @param acc
	 * @param type
	 * @param param
	 * @throws BizException
	 */
	void setUserParam(String acc, String type, List<Integer> param) throws BizException;

	/**
	 * 创建机器人
	 * 
	 * @throws BizException
	 */

	void createRobotRoles() throws BizException;

	/**
	 * 清理机器人
	 * 
	 * @throws BizException
	 */

	void clearRobotRoles() throws BizException;
}
