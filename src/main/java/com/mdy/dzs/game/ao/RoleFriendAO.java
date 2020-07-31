package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.mdy.dzs.game.domain.friend.ChatListVO;
import com.mdy.dzs.game.domain.friend.FriendListVO;
import com.mdy.dzs.game.domain.friend.NailiListVO;
import com.mdy.dzs.game.domain.friend.RecommendVO;
import com.mdy.dzs.game.domain.friend.RequestListVO;
import com.mdy.dzs.game.domain.friend.RoleFriend;
import com.mdy.dzs.game.domain.friend.SearchFriendVO;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 好友
 * 
 * @author 白雪林
 *
 */
public class RoleFriendAO extends BaseAO {
	private RoleAO roleAO;
	private PacketAO packetAO;
	private MissionExecAO missionExecAO;
	private CacheManager cacheManager;

	public RoleFriendAO(RoleAO roleAO, PacketAO packetAO, MissionExecAO missionExecAO, CacheManager cacheManager) {
		this.roleAO = roleAO;
		this.packetAO = packetAO;
		this.cacheManager = cacheManager;
		this.missionExecAO = missionExecAO;
	}

	// 统计好友数量
	public int queryCountByRoelId(int roleId) {
		return roleFriendDAO().queryCountByRoelId(roleId);
	}

	// 查询某人所有好友数据
	public List<RoleFriend> queryFriendList(int roleId) {
		return roleFriendDAO().queryFriendList(roleId);
	}

	// 查询待领取耐力列表
	public List<RoleFriend> queryNailiList(int roleId) {
		return roleFriendDAO().queryNailiList(roleId);
	}

	// 查询一条好友数据
	public RoleFriend queryOneFriend(int roleId, int friendId) {
		return roleFriendDAO().queryOneFriend(roleId, friendId);
	}

	// 查询领取全部耐力好友列表 含limit限制
	public List<RoleFriend> queryGetAllNailiFriend(int roleId) {
		return roleFriendDAO().queryGetAllNailiFriend(roleId);
	}

	// 查询好友id列表
	public List<Integer> queryFriendId(int roleId, int status) {
		return roleFriendDAO().queryFriendId(roleId, status);
	}

	// 查询已发送过申请的id列表
	public List<Integer> queryApplyId(int roleId, int status) {
		return roleFriendDAO().queryApplyId(roleId, status);
	}

	// 查询申请列表
	public List<RoleFriend> queryApplyList(int roleId) {
		return roleFriendDAO().queryApplyList(roleId);
	}

	// 推荐好友
	public List<Role> queryRecomFriend(Role doc, int num, List<Integer> levelLimit, List<Integer> allFriendId) {
		allFriendId.add(doc.getId());
		List<Role> friendList = roleDAO().queryRecomFriend(doc, num, levelLimit, allFriendId);
		return friendList;
	}

	// 添加记录
	public void add(int roleId, int friendId, String content, int status) {
		roleFriendDAO().add(roleId, friendId, content, status);
	}

	// 搜索好友
	public List<Role> searchFriends(Role doc, int type, String content, int searchNum, int flag) {
		List<Role> friendList = roleDAO().querySearchFriend(doc, type, content, searchNum, flag);
		return friendList;
	}

	// 更新领取耐力状态为已领取
	public void updateIsGetNaili(int id, int status) {
		roleFriendDAO().updateIsGetNaili(id, status);
	}

	// 更新数据为已赠送耐力
	public void updateSendNaili(RoleFriend friendDoc) {
		roleFriendDAO().updateSendNaili(friendDoc);
	}

	// 更新赠送时间
	public void updataSendNailiTime(RoleFriend curData) {
		roleFriendDAO().updataSendNailiTime(curData);
	}

	// 更新关系状态-断交，申请，好友
	public void updateFriendStatus(int roleId, int friendId, String content, int status) {
		roleFriendDAO().updateFriendStatus(roleId, friendId, content, status);
	}

	// 更新查看到聊天的时间
	public void updateSeeChatTime(int roleId, int friendId) {
		roleFriendDAO().updateSeeChatTime(roleId, friendId);
	}

	// 整理好友列表
	public List<FriendListVO> tidyFriendList(List<RoleFriend> friendListDoc) {
		List<FriendListVO> friendList = new ArrayList<FriendListVO>();
		for (int i = 0; i < friendListDoc.size(); i++) {
			RoleFriend curFriend = friendListDoc.get(i);
			Role friendDoc = roleAO.queryById(curFriend.getFriendId());
			int isSendNaili = RoleFriend.FRIEND_NAILI_NOT_SEND; // 未赠送
			if (DateUtil.isToday(curFriend.getSendNailiTime())) {
				isSendNaili = RoleFriend.FRIEND_NAILI_HAS_SEND;
			}
			friendList.add(new FriendListVO(friendDoc.getAccount(), friendDoc.getName(), friendDoc.getAttack(),
					friendDoc.getCharm(), friendDoc.getLevel(), isSendNaili, friendDoc.getResId(), friendDoc.getCls()));
		}
		return friendList;
	}

	// 领取耐力列表整理结构
	public List<NailiListVO> tidyNailiList(List<RoleFriend> nailiListDoc) {
		List<NailiListVO> nailiList = new ArrayList<NailiListVO>();
		for (int i = 0; i < nailiListDoc.size(); i++) {
			RoleFriend curFriend = nailiListDoc.get(i);
			Role friendDoc = roleAO.queryById(curFriend.getFriendId());
			// 耐力是否领取 ！=初始值0 && ！= 已领取1
			if (curFriend.getIsGetNaili() != RoleFriend.FRIEND_NAILI_DEFAULT
					&& curFriend.getIsGetNaili() != RoleFriend.FRIEND_NAILI_HAS_GET) {
				int time = DateUtil.getDateDiff(curFriend.getBeSendNailiTime());
				nailiList.add(new NailiListVO(friendDoc.getAccount(), friendDoc.getName(), friendDoc.getAttack(),
						friendDoc.getCharm(), time, Constants.FriendSendNailiNum, friendDoc.getResId(),
						friendDoc.getCls(), friendDoc.getLevel()));
			}
		}
		return nailiList;
	}

	// 整理申请好友列表
	public List<RequestListVO> tidyRequestList(List<RoleFriend> applyListDoc) {
		List<RequestListVO> requestList = new ArrayList<RequestListVO>();
		for (int i = 0; i < applyListDoc.size(); i++) {
			RoleFriend curFriend = applyListDoc.get(i);
			Role friendDoc = roleAO.queryById(curFriend.getFriendId());
			// 不用再显示，字段暂留，默认为不在线
			int isOnline = RoleFriend.FRIEND_NOT_ONLINE;// getIsOnline(friendDoc.getHeartLastTime());
			requestList.add(new RequestListVO(friendDoc.getAccount(), friendDoc.getName(), friendDoc.getAttack(),
					friendDoc.getCharm(), isOnline, curFriend.getContent(), friendDoc.getResId(), friendDoc.getCls(),
					friendDoc.getLevel()));
		}
		return requestList;
	}

	// 获取等级区间
	public List<Integer> getLevelProb(int level, int probRandom) {
		int min = 1;
		int max = 1;
		if (probRandom == RoleFriend.FRIEND_PROB_B_FIVE) {// 大5级
			min = level;
			max = level + 5;
		} else if (probRandom == RoleFriend.FRIEND_PROB_L_FIVE) {// 小5级
			min = level - 5;
			max = level;
		} else if (probRandom == RoleFriend.FRIEND_PROB_L_TEN) {// 小10级
			min = level - 10;
			max = level - 5;
		}
		if (min <= 0) {
			min = 1;
		}
		return Arrays.asList(min, max);
	}

	//
	private List<Integer> getProbList() {
		List<Integer> probList = RoleFriend.PROBLIST;
		Collections.swap(probList, cacheManager.random(0, probList.size() - 1),
				cacheManager.random(0, probList.size() - 1));
		return probList;
	}

	// 整理推荐好友列表
	public List<RecommendVO> tidyRecommList(List<Role> docList, int roleId) {
		List<Integer> applyIdList = queryApplyId(roleId, RoleFriend.FRIEND_STATUS_APPLY);
		List<RecommendVO> recommendList = new ArrayList<RecommendVO>();
		for (int i = 0; i < docList.size(); i++) {
			Role curDoc = docList.get(i);
			int isApply = RoleFriend.FRIEND_NOT_APPLY;
			// 前端列表不再显示，设置为不在线，字段暂留
			int isOnline = RoleFriend.FRIEND_NOT_ONLINE;// getIsOnline(curDoc.getHeartLastTime());
			if (applyIdList.contains(curDoc.getId())) {
				isApply = RoleFriend.FRIEND_HAS_APPLY;
			}
			recommendList.add(new RecommendVO(curDoc.getAccount(), curDoc.getName(), curDoc.getAttack(),
					curDoc.getCharm(), isOnline, curDoc.getLevel(), isApply, curDoc.getResId(), curDoc.getCls()));
		}
		return recommendList;
	}

	// 是否在线 FRIEND_ONLINE= 1; //在线
	public int getIsOnline(int heartTime) {
		Date now = new Date();
		int nowsecond = (int) (now.getTime() / 1000);
		int diffTime = nowsecond - heartTime;
		if (diffTime > Constants.outLineTime * 60) {
			return RoleFriend.FRIEND_NOT_ONLINE;
		} else {
			return RoleFriend.FRIEND_ONLINE;
		}
	}

	// 整理搜索好友列表
	public SearchFriendVO tidySearchFriendList(List<Role> friendList, int roleId) {
		List<Integer> friendIdList = queryApplyId(roleId, RoleFriend.FRIEND_STATUS_APPLY);
		List<RecommendVO> searchList = new ArrayList<RecommendVO>();
		int flag = 0;
		for (int i = 0; i < friendList.size(); i++) {
			Role curDoc = friendList.get(i);
			// 前端列表不再显示，设置为不在线，字段暂留
			int isOnline = RoleFriend.FRIEND_NOT_ONLINE;// getIsOnline(curDoc.getHeartLastTime());
			int isApply = RoleFriend.FRIEND_NOT_APPLY;
			if (friendIdList.contains(curDoc.getId())) {
				isApply = RoleFriend.FRIEND_HAS_APPLY;
			}
			searchList.add(new RecommendVO(curDoc.getAccount(), curDoc.getName(), curDoc.getAttack(), curDoc.getCharm(),
					isOnline, curDoc.getLevel(), isApply, curDoc.getResId(), curDoc.getCls()));
		}
		if (friendList.size() > 0) {
			flag = friendList.get(friendList.size() - 1).getId();
		}
		return new SearchFriendVO(flag, searchList);
	}

	// 领取单个耐力数据更新
	public void getOneNaili(Role doc, RoleFriend friendData) {
		doc.setGetFriendNailiCnt(doc.getGetFriendNailiCnt() + 1);
		roleDAO().updateGetFriendNailiCnt(doc); // 每天领取次数
		updateIsGetNaili(friendData.getId(), RoleFriend.FRIEND_NAILI_HAS_GET);
	}

	// 领取全部耐力并赠送
	public int getAndSendNailiAll(Role doc, List<RoleFriend> friendList) throws BizException {
		int nailiNum = 0;
		int count = 0;
		for (int i = 0; i < friendList.size(); i++) {
			if (doc.getResisVal() >= doc.getPropLimitAry().get(1)) {
				break;
			}
			if (doc.getGetFriendNailiCnt() >= Constants.FriendGetNailiMaxLimit) {
				break;
			}
			// 更新该条数据
			RoleFriend curData = friendList.get(i);
			updateIsGetNaili(curData.getId(), RoleFriend.FRIEND_NAILI_HAS_GET);
			// 赠送耐力
			if (!DateUtil.isToday(curData.getSendNailiTime()) && !checkIsFriendRelation(0, 0, curData)) {// 非今天&&还是好友关系就可以送
				RoleFriend friendDoc = queryOneFriend(curData.getFriendId(), curData.getRoleId());
				updateSendNaili(friendDoc);
				updataSendNailiTime(curData);
				count++;
			}
			doc.setGetFriendNailiCnt(doc.getGetFriendNailiCnt() + 1);
			roleDAO().updateGetFriendNailiCnt(doc); // 剩余领取次数

			nailiNum += Constants.FriendSendNailiNum;
		}
		packetAO.addItem(doc, Packet.POS_ATTR, Packet.ATTR_耐力, nailiNum, RoleItemLog.SYS_好友_领取耐力_全部好友, "");

		missionExecAO.friendSendGift(doc, count);
		return nailiNum;
	}

	// 同意所有申请
	public int acceptAllApply(Role doc, int ownNum, List<RoleFriend> appList) throws BizException {
		int firendLimitType = 0;
		int ownFriendNum = ownNum;
		List<Integer> friendIdList = queryFriendId(doc.getId(), RoleFriend.FRIEND_STATUS_AGREE);
		for (int i = 0; i < appList.size(); i++) {
			if (ownFriendNum >= Constants.FriendNumLimit) {
				firendLimitType = 1;
				break;
			}
			RoleFriend applyDoc = appList.get(i);
			int opposNum = queryCountByRoelId(applyDoc.getFriendId());
			if (opposNum >= Constants.FriendNumLimit) {// 对方已满
				firendLimitType = 2;
				continue;
			}

			updateFriendStatus(doc.getId(), applyDoc.getFriendId(), "", RoleFriend.FRIEND_STATUS_AGREE);// 同意申请
			RoleFriend friendData = queryOneFriend(applyDoc.getFriendId(), doc.getId());
			if (friendData == null) {
				add(applyDoc.getFriendId(), doc.getId(), "", RoleFriend.FRIEND_STATUS_AGREE);// 加为对方好友
			} else {
				updateFriendStatus(applyDoc.getFriendId(), doc.getId(), "", RoleFriend.FRIEND_STATUS_AGREE);
			}

			ownFriendNum++;
			friendIdList.add(applyDoc.getFriendId());
			missionExecAO.friendAdd(roleAO.queryById(applyDoc.getFriendId()));
		}
		missionExecAO.friendAdd(doc);
		return firendLimitType;
	}

	// 拒绝所有申请
	public void rejectAllApply(List<RoleFriend> appList) {
		for (int i = 0; i < appList.size(); i++) {
			updateFriendStatus(appList.get(i).getRoleId(), appList.get(i).getFriendId(), "",
					RoleFriend.FRIEND_STATUS_REFUSE);
		}
	}

	// 获取推荐好友列表
	public List<RecommendVO> getRecommendList(Role doc, int num) {
		List<RecommendVO> recommList = new ArrayList<RecommendVO>();
		List<Integer> problist = getProbList();
		for (int i = 0; i < problist.size(); i++) {
			List<Integer> levelLimit = getLevelProb(doc.getLevel(), RoleFriend.PROBLIST.get(i));
			List<Integer> allFriendId = queryFriendId(doc.getId(), RoleFriend.FRIEND_STATUS_AGREE);
			List<Role> docList = queryRecomFriend(doc, num, levelLimit, allFriendId);
			if (docList.size() > 0) {
				recommList = tidyRecommList(docList, doc.getId());
				break;
			}
		}

		return recommList;
	}

	// 断交
	public void breakFriend(int roleId, int friendId) {
		updateFriendStatus(roleId, friendId, "", RoleFriend.FRIEND_STATUS_BREAK);
		updateFriendStatus(friendId, roleId, "", RoleFriend.FRIEND_STATUS_BREAK);
	}

	//
	public List<ChatListVO> tidyChatList(Role doc, List<Integer> friendIdList) {
		List<ChatListVO> chatList = new ArrayList<ChatListVO>();
		List<Role> roleFriendList = roleDAO().queryIdAndHeartTime(friendIdList);

		for (int i = 0; i < roleFriendList.size(); i++) {
			Role curRole = roleFriendList.get(i);
			RoleFriend curData = queryOneFriend(doc.getId(), curRole.getId());
			int isOnline = getIsOnline(curRole.getHeartLastTime());
			int isChat = getIsChat(curData.getSeeChatTime(), doc.getId(), curRole.getId());

			if (isOnline == RoleFriend.FRIEND_ONLINE || isChat == RoleFriend.FRIEND_CHAT) {
				chatList.add(new ChatListVO(curRole.getAccount(), isOnline, isChat));
			}
		}
		return chatList;
	}

	// 查看friendId是否对roleId说话了
	private int getIsChat(Date seeTime, int roleId, int friendId) {
		int chatCnt = chatDAO().checkNewFriendChat(seeTime, roleId, friendId);
		if (chatCnt > 0) {
			return RoleFriend.FRIEND_CHAT;
		} else {
			return RoleFriend.FRIEND_NOT_CHAT;
		}
	}

	// 清除断交好友的聊天记录
	public void clearChatData(int roleId, int friendId) {
		chatDAO().clearChatData(roleId, friendId);
	}

	// 查看是否为好友状态 true==非好友 false==好友
	public boolean checkIsFriendRelation(int roleId, int friendId, RoleFriend friendData) {
		if (friendData != null) {
			if (friendData.getStatus() != RoleFriend.FRIEND_STATUS_AGREE) {
				return true;
			}
		} else {
			RoleFriend friend = queryOneFriend(roleId, friendId);
			if (friend == null || friend.getStatus() != RoleFriend.FRIEND_STATUS_AGREE) {
				return true;
			}
		}
		return false;
	}

	// 删除当前角色已看过 且超过7天的记录
	public void deletechatDataByDay(Role role) {
		List<RoleFriend> friendList = queryFriendList(role.getId());
		for (int i = 0; i < friendList.size(); i++) {
			RoleFriend curData = friendList.get(i);
			Date seeDate = curData.getSeeChatTime();
			Date deleDate = DateUtil.getDateAfter(DateUtil.getStartTimeOfDay(new Date()),
					-Constants.FriendChatDeleteDay);
			Date paraDate = DateUtil.compare_date(seeDate, deleDate) == true ? deleDate : seeDate;
			chatDAO().clearFriendChatSevenData(paraDate);
		}
	}

	// 有无新数据 小红点 0-无 1-有
	public int checkHasNewData(Role doc) {
		// 聊天 未领取耐力数 申请列表 queryNailiList
		if (queryNailiList(doc.getId()).size() > 0) {// naili
			return 1;
		} else if (queryFriendId(doc.getId(), RoleFriend.FRIEND_STATUS_APPLY).size() > 0) {// apply
			return 1;
		}
		// chat
		int isChat = 0;
		List<Integer> friendIdList = queryFriendId(doc.getId(), RoleFriend.FRIEND_STATUS_AGREE);
		for (int i = 0; i < friendIdList.size(); i++) {
			RoleFriend curData = queryOneFriend(doc.getId(), friendIdList.get(i));
			if (getIsChat(curData.getSeeChatTime(), doc.getId(), friendIdList.get(i)) == RoleFriend.FRIEND_CHAT) {
				isChat = 1;
				break;
			}
		}
		return isChat;
	}

}