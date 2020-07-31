package com.mdy.dzs.game.ao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.chat.Chat;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.filter.ChatFilter;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;

/**
 * 聊天
 * 
 * @author 房曈
 *
 */
public class ChatAO extends BaseAO {
	//
	/**
	 * 查询
	 */
	public Chat query(int id) {
		return chatDAO().query(id);
	}

	/**
	 * 查询列表
	 */
	public List<Chat> queryList() {
		return chatDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param Chat
	 */
	public void add(Chat c) {
		chatDAO().add(c);
	}

	/**
	 * 更新
	 * 
	 * @param Chat
	 */
	public void update(Chat c) {
		chatDAO().update(c);
	}

	public List<Chat> queryListByChatType(Role doc, int chatType) {
		Long time = doc.getChatLastViewTime().get(chatType);
		String acc = doc.getAccount();
		ChatFilter filter = new ChatFilter();
		// filter.setPageSize(Integer.MAX_VALUE);
		filter.setOrderType(ChatFilter.ORDER_BY_CRETAE_TIME_ASC);
		filter.setChatType(chatType);
		if (time != null) {
			Date lastSeeTime = new Date(time);
			filter.setLastSeeTime(lastSeeTime);
		}

		switch (chatType) {
		case Chat.TYPE_私聊:
			filter.setSendName(acc);
			filter.setReciveName(acc);
			break;
		case Chat.TYPE_军团:
			break;
		case Chat.TYPE_世界:
			break;
		case Chat.TYPE_GM:
			filter.setSendName(acc);
			filter.setReciveName(acc);
			break;
		}
		return chatDAO().queryListByFilter(filter);
	}

	// 查询好友聊天未读记录
	public List<Chat> queryFriendChatList(int roleId, int friendId, Date seeChatTime) {
		return chatDAO().queryFriendChatData(roleId, friendId, seeChatTime);
	}

	// 添加世界聊天数据
	public void addWorldChat(Role doc, String receiveData, String msg, String para1, String para2, String para3) {
		Chat chat = getChat(Chat.TYPE_世界, doc.getId(), doc.getAccount(), doc.getName(), 0, "", msg, doc.getVip(),
				doc.getSex(), "", "", "", doc.getFaction());
		add(chat);
	}

	// 添加好友聊天数据
	public void addFriendChat(Role doc, Role receiveData, String msg, String para1, String para2, String para3) {
		Chat chat = getChat(Chat.TYPE_好友, doc.getId(), doc.getAccount(), doc.getName(), receiveData.getId(),
				receiveData.getName(), msg, doc.getVip(), doc.getSex(), "", "", "", doc.getFaction());
		add(chat);
	}

	public Chat getChat(int type, int sendId, String account, String sendName, int receiveId, String receiveName,
			String msg, int sendVip, int sendSex, String para1, String para2, String para3, String faction) {
		Chat chat = new Chat();
		chat.setType(type);
		chat.setSendRoleId(sendId);
		chat.setAccount(account);
		chat.setSendRoleName(sendName);
		chat.setReceiveRoleId(receiveId);
		chat.setReceiveRoleName(receiveName);
		chat.setSendMsg(msg);
		chat.setSendRoleVip(sendVip);
		chat.setSendRoleSex(sendSex);
		chat.setSendRoleFaction(faction);
		chat.setSendPara1(para1);
		chat.setSendPara2(para2);
		chat.setSendPara3(para3);
		return chat;
	}

	// 删除7天前世界聊天旧数据
	public void tidyChatTable() {
		Date tidyDate = DateUtil.getDateAfter(DateUtil.getStartTimeOfDay(new Date()), -Constants.ChatSaveDay);
		chatDAO().tidyChatTable(tidyDate);
	}

}