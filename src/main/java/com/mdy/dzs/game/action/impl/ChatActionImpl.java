/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.ChatAction;
import com.mdy.dzs.game.ao.RoleActivityAO;
import com.mdy.dzs.game.domain.activity.ActivityConfig;
import com.mdy.dzs.game.domain.activity.limitcard.LimitCardBean;
import com.mdy.dzs.game.domain.activity.limitshop.RoleLimitShop;
import com.mdy.dzs.game.domain.activity.mazegame.RoleActivityMaze;
import com.mdy.dzs.game.domain.activity.roulettegame.RoleActivityRoulette;
import com.mdy.dzs.game.domain.activity.roulettegame.Roulette;
import com.mdy.dzs.game.domain.boss.BossBattle;
import com.mdy.dzs.game.domain.chat.Chat;
import com.mdy.dzs.game.domain.friend.RoleFriend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.union.RoleUnion;
import com.mdy.dzs.game.domain.union.Union;
import com.mdy.dzs.game.domain.union.UnionBoss;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.ChatException;
import com.mdy.dzs.game.exception.RoleFriendException;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 聊天
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月14日 下午6:51:19
 */
public class ChatActionImpl extends ApplicationAwareAction implements ChatAction {

	@Override
	public List<Chat> quertChatList(String acc, int chatType, String para) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		if (chatType > 5 || chatType < 1) {
			throw BaseException.getException(ChatException.EXCE_CHAT_TYPE_ERROR);
		}
		List<Chat> chats = new ArrayList<Chat>();
		switch (chatType) {
			case Chat.TYPE_世界:
				chats = chatAO().queryListByChatType(doc, chatType);
				doc.getChatLastViewTime().set(chatType, new Date().getTime());
				roleAO().updateChatViewTime(doc);
				break;
			case Chat.TYPE_好友:
				Role friendDoc = roleAO().queryExistAccount(para);
				RoleFriend friendData = roleFriendAO().queryOneFriend(doc.getId(), friendDoc.getId());
				chats = chatAO().queryFriendChatList(doc.getId(), friendDoc.getId(), friendData.getSeeChatTime());
				roleFriendAO().updateSeeChatTime(doc.getId(), friendDoc.getId());
				break;
			default:
				;
		}

		return chats;
	}

	@Override
	public List<Serializable> viewChatItem(String sendRoleName, int itemType, int itemId) throws BizException {

		return null;
	}

	@Override
	public Map<String, Object> queryUnReadNum(String acc, int chatType) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Calendar nowTime = Calendar.getInstance();
		// 聊天
		List<Chat> chats = chatAO().queryListByChatType(doc, chatType);
		// 青龙
		int state = 1;// 未开启
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(doc.getId());
		Map<String, Object> rtnObj = new HashMap<String, Object>();
		if (chatType > 4 || chatType < 1) {
			throw BaseException.getException(ChatException.EXCE_CHAT_TYPE_ERROR);
		}
		if (!(roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion)) {// !(无帮派 || 退出帮派)
			UnionBoss lastData = unionBossAO().queryLast(roleUnion.getUnionId());
			Date now = new Date();
			rtnObj.put("unionId", roleUnion.getUnionId());

			if (lastData != null && DateUtil.isSameDay(lastData.getCreateTime(), now)) {// 有boss记录 && 与现在是同一天
				long start = new Date(lastData.getCreateTime().getTime()).getTime();
				if (now.getTime() - start < 15 * 60 * 1000 && lastData.getEndTime() == null) {// 进行中
					state = 2;
				} else {// 已结束
					state = 3;
				}
			}
		}
		// 烛龙殿
		BossBattle boss = bossBattleAO().queryLast();
		// 限时豪杰
		int lcStatus = 0;
		Open openData = cacheManager().getExistValueByKey(Open.class, 41);// key:system

		Map<Integer, ActivityConfig> activityConfig = roleActivityAO().getMapActivitys();
		Integer status = RoleActivityAO.checkOpen(activityConfig.get(ActivityConfig.ACTIVITY_LIMITCARD), 60000);

		if (doc.getLevel() >= openData.getLevel().get(0) && status != null && status != 0) {// 等级达到限制 且活动开启中
			LimitCardBean lc = roleActivityAO().queryByRoleId(doc.getId());

			if (lc != null) {
				lcStatus = nowTime.getTimeInMillis() - lc.getFreegettime().getTime() >= 72000000 ? 1 : 0;
			} else {// 可以参加活动了
				lcStatus = 1;
			}
		}
		// 皇宫探宝活动
		int roulette = 0;// 不显示小图标
		Open rouletteOpenData = cacheManager().getExistValueByKey(Open.class, 46);// key:system
		Integer rouletteStatus = RoleActivityAO.checkOpen(activityConfig.get(ActivityConfig.ACTIVITY_皇宫探宝), 60000);

		if (doc.getLevel() >= rouletteOpenData.getLevel().get(0) && rouletteStatus != null && rouletteStatus != 0) {// 等级达到限制
																													// 且活动开启中

			Roulette activeData = roleActivityRouletteAO().getCurActivityData();// 活动配置
			if (activeData != null) {
				RoleActivityRoulette rRoleDoc = roleActivityRouletteAO().queryAndAdd(doc.getId(), activeData);

				if (activeData.getType() == Roulette.直接购买次数) {
					roulette = rRoleDoc.getFreeTimes() > 0 ? 1 : 0;
				} else {
					roulette = rRoleDoc.getSurTimes() > 0 ? 1 : 0;
				}
			}
		}

		// 是否开启烧烤大会 1是否在帮派，2是否开启3是否领取
		int isOpen = 0;
		if (roleUnion != null && roleUnion.getState() == RoleUnion.roleinUnion) {// 有玩家数据 && 在帮派中
			Union union = unionAO().queryUnionByUnionId(roleUnion.getUnionId());
			if (DateUtil.isToday(union.getBarbecueTime())
					&& DateUtil.GetNowDateTime().getTime() - union.getBarbecueTime().getTime() <= 7200000
					&& !DateUtil.isToday(roleUnion.getBarbecue())) {
				isOpen = 1;
			}
		}

		// 皇宫探宝活动
		int maze = 0;// 不显示小图标
		Open mazeOpenData = cacheManager().getExistValueByKey(Open.class, 47);// key:system
		Integer mazeStatus = RoleActivityAO.checkOpen(activityConfig.get(ActivityConfig.ACTIVITY_MAZE), 60000);

		if (doc.getLevel() >= mazeOpenData.getLevel().get(0) && mazeStatus != null && mazeStatus != 0) {// 等级达到限制 且活动开启中

			RoleActivityMaze mazeDoc = roleActivityMazeAO().query(doc.getId());
			if (mazeDoc == null || mazeDoc.getFreeTimes() > 0) {
				maze = 1;
			}
		}
		// 限时商店
		int limitShopState = 0;
		Open limitShopOpenData = cacheManager().getExistValueByKey(Open.class, 48);// key:system
		Integer limitshopStatus = RoleActivityAO.checkOpen(activityConfig.get(ActivityConfig.ACTIVITY_LIMITSHOP),
				60000);
		RoleLimitShop roleLimitShop = roleActivityLimitShopAO().queryShopInfoByRoleId(doc.getId());
		// 活动开启中 且 （未参加活动 || 今天未进入活动）
		if (doc.getLevel() >= limitShopOpenData.getLevel().get(0) && limitshopStatus != 0
				&& (roleLimitShop == null || !DateUtil.isToday(roleLimitShop.getRefreshTime()))) {
			limitShopState = 1;
		}

		rtnObj.put("worldUnreadNum", chats.size());
		rtnObj.put("unionBossState", state);
		rtnObj.put("sleepState", doc.getSleepLastTime().getTime());// 客栈
		rtnObj.put("bossState",
				boss != null && (DateUtil.isToday(boss.getCreateTime()) && boss.getEndTime() == null) ? 1 : 0);// 烛龙殿
		rtnObj.put("limitCardstate", lcStatus); // 限时豪杰
		rtnObj.put("bbqState", isOpen); // 烧烤大会
		rtnObj.put("rouletteStatus", roulette); // 皇宫探宝
		rtnObj.put("mazeState", maze); // 迷宫寻宝
		rtnObj.put("LimitShopState", limitShopState); // 显示商店
		return rtnObj;
	}

	@Override
	public void sendChat(String acc, String receiveData, int chatType, String msg, String para1, String para2,
			String para3) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);

		switch (chatType) {
			case Chat.TYPE_世界:
				chatAO().addWorldChat(doc, receiveData, msg, para1, para2, para3);
				break;
			case Chat.TYPE_好友:
				Role friendDoc = roleAO().queryExistAccount(receiveData);
				if (roleFriendAO().checkIsFriendRelation(doc.getId(), friendDoc.getId(), null)) {
					throw BaseException.getException(// 已被解除好友关系
							RoleFriendException.EXCE_FRIEND_RELIEVE);
				}
				chatAO().addFriendChat(doc, friendDoc, msg, para1, para2, para3);
				break;
			default:
				;
		}

	}

	@Override
	public void tidyChatTabel() {
		chatAO().tidyChatTable();
	}

}
