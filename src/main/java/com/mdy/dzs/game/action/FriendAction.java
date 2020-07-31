package com.mdy.dzs.game.action;

import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.friend.AcceptAllVO;
import com.mdy.dzs.game.domain.friend.ChatListVO;
import com.mdy.dzs.game.domain.friend.GetFriendListVO;
import com.mdy.dzs.game.domain.friend.GetNaiLiAllVO;
import com.mdy.dzs.game.domain.friend.RecommendVO;
import com.mdy.dzs.game.domain.friend.SearchFriendVO;
import com.mdy.sharp.container.biz.BizException;

public interface FriendAction {

	/*
	 * friendList: { { account : 2121212 , name : 小雪, battlepoint: 121212, ---战斗力
	 * charm: 250 --魅力值 level：* --等级 isSendNaili:* 是否已赠送耐力 0-未赠送 1-已赠送 } .... },
	 * 
	 * 
	 * 
	 * nailiList: --领取耐力列表 { { account : 2121212 , name : 小雪, battlepoint: 121212,
	 * ---战斗力 charm: 250, --魅力值 time: 30, --几天前 nailiNum: 1 --不传的话默认为1 } ....
	 * 
	 * },
	 * 
	 * requestList: --申请好友列表 { { account : 2121212 , name : 小雪, battlepoint: 121212,
	 * ---战斗力 charm: 250, --魅力值 isOnline: 1 ,--是否在线 content: "你好，我是小雪小白小臭臭" } .... }
	 * recommendList: --推荐好友列表 { { account : 2121212 , name : 小雪, battlepoint:
	 * 121212, ---战斗力 charm: 250, --魅力值 isOnline: 1 --是否在线 1-在线 0 -离线 level：*
	 * isApply:* --是否已发送 1-已发送 0-未发送 } .... }
	 * 
	 * restNailiNum:30, --今日还可以领多少次耐力
	 */
	GetFriendListVO getFriendList(String acc) throws BizException;

	/*
	 * 发送 { a:removeFriend account: 212121 } 返回 {result:1}
	 */

	Map<String, Integer> removeFriend(String acc, String friendAcc) throws BizException;

	/*
	 * 发送： { a:recommendList, num:10, flag:* } 返回： recommendList: --推荐好友列表 { {
	 * account : 2121212 , name : 小雪, battlepoint: 121212, ---战斗力 charm: 250, --魅力值
	 * isOnline: 1 --是否在线 level：* } .... }
	 */
	List<RecommendVO> recommendList(String acc, int num) throws BizException;

	/*
	 * 发送 { a:applyFriend account : 211212 content：”我是小雪“ } 返回 {result:1}
	 */

	Map<String, Integer> applyFriend(String acc, String applyAcc, String content) throws BizException;

	/*
	 * 发送 { a:searchFriend type:0, --0是ID 1是名称 content : "小雪" searchNum:10 flag:1 }
	 * 返回 searchList: { flag:*, { account : 2121212 , name : 小雪, battlepoint:
	 * 121212, ---战斗力 charm: 250, --魅力值 isOnline: 1 --是否在线 resId:* --资源id cls：* --阶数
	 * } .... }
	 */
	SearchFriendVO searchFriend(String acc, int type, String content, int searchNum, int flag) throws BizException;

	/*
	 * 发送 { a:getNaili account :21121 } 返回 { naliNum:* --领取到的耐力数量 }
	 */

	Map<String, Integer> getNaili(String acc, String account) throws BizException;

	/*
	 * { a:getNailiAll
	 * 
	 * } 返回 { nailiNum:* --领取到的耐力数量 friendList: { { account : 2121212 , name : 小雪,
	 * battlepoint: 121212, ---战斗力 charm: 250 --魅力值 level：* --等级 isSendNaili:*
	 * 是否已赠送耐力 0-未赠送 1-已赠送 resId:* --资源id cls：* --阶数 } .... }, nailiList: --领取耐力列表 {
	 * { account : 2121212 , name : 小雪, battlepoint: 121212, ---战斗力 charm: 250,
	 * --魅力值 time: 30, --几天前 nailiNum: 1 --不传的话默认为1 resId:* --资源id cls：* --阶数 } ....
	 * 
	 * }, }
	 */

	GetNaiLiAllVO getNailiAll(String acc) throws BizException;

	/*
	 * 发送 { a:acceptFriend account:2121212 } 返回 {}
	 */

	Map<String, Integer> acceptFriend(String acc, String account) throws BizException;

	/*
	 * 拒绝某人的好友邀请 发送 { a:rejectFriend account:2121212 } 返回 {}
	 */

	Map<String, Integer> rejectFriend(String acc, String account) throws BizException;

	/*
	 * 同意所有好友申请 发送 { a:acceptAll } 返回 {}
	 */

	AcceptAllVO acceptAll(String acc) throws BizException;

	/*
	 * 发送 { a:rejectAll } 返回 {}
	 */

	Map<String, Integer> rejectAll(String acc) throws BizException;

	/*
	 * 发送 { a:sendNaili account:121212 } 返回 {result：结果 1-成功}
	 */
	Map<String, Integer> sendNaili(String acc, String account) throws BizException;

	/*
	 * 请求聊天在线相关数据,这个接口5秒钟调用一次 发送 { a:updateChatList } 返回 chatList: { { account :
	 * 2121212 , isOnline: 1, --是否在线 isChat : 1 --是否对你说话了 } .... }
	 */
	List<ChatListVO> updateChatList(String acc) throws BizException;

}
