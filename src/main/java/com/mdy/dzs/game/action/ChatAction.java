/**
 * 
 */
package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.chat.Chat;
import com.mdy.sharp.container.biz.BizException;

/**
 * 聊天
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月9日 下午1:49:37
 */
public interface ChatAction {

	/**
	 * 获取聊天信息列表
	 * 
	 * @param acc
	 * @param chatType 聊天类型(1世界/2私聊/3军团/4GM)
	 * @return
	 * @throws BizException
	 */
	List<Chat> quertChatList(String acc, int chatType, String para) throws BizException;

	/**
	 * 查看聊天物品信息
	 * 
	 * @param sendRoleId 发送消息用户id TODO
	 * @param itemType   物品类型(8卡牌/1装备)
	 * @param itemId
	 * @return
	 * @throws BizException
	 */
	List<Serializable> viewChatItem(String sendRoleName, int itemType, int itemId) throws BizException;

	/**
	 * 
	 * @param acc
	 * @param chatType
	 * @return
	 * @throws BizException
	 */
	Map<String, Object> queryUnReadNum(String acc, int chatType) throws BizException;

	/**
	 * 发送聊天信息
	 * 
	 * @param acc       账号
	 * @param reveiceId 接收用户 TODO
	 * @param chatType  聊天类型(1世界/2私聊/3军团/4GM)
	 * @param msg       文字聊天信息
	 * @param para1     物品类型(8卡牌/1装备) 物品类型(8卡牌/1装备) 咨询类型(1咨询/2bug/3投诉/4建议)
	 * @param para2     物品id 物品id 空
	 * @param para3     空 对方账号 空
	 * @throws BizException
	 */

	void sendChat(String acc, String reveiceName, int chatType, String msg, String para1, String para2, String para3)
			throws BizException;

	void tidyChatTabel();
}
