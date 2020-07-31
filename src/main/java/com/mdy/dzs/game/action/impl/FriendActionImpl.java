package com.mdy.dzs.game.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.FriendAction;
import com.mdy.dzs.game.domain.friend.AcceptAllVO;
import com.mdy.dzs.game.domain.friend.ChatListVO;
import com.mdy.dzs.game.domain.friend.FriendListVO;
import com.mdy.dzs.game.domain.friend.GetFriendListVO;
import com.mdy.dzs.game.domain.friend.GetNaiLiAllVO;
import com.mdy.dzs.game.domain.friend.NailiListVO;
import com.mdy.dzs.game.domain.friend.RecommendVO;
import com.mdy.dzs.game.domain.friend.RequestListVO;
import com.mdy.dzs.game.domain.friend.RoleFriend;
import com.mdy.dzs.game.domain.friend.SearchFriendVO;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleFriendException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

public class FriendActionImpl  extends ApplicationAwareAction implements 
		FriendAction{

	@Override
	public GetFriendListVO getFriendList(String acc) throws BizException {
		Role doc 					   	= roleAO().queryExistAccount(acc); 
		List<RoleFriend> friendListDoc 	= roleFriendAO().queryFriendList(doc.getId());
		List<RoleFriend> nailiListDoc   = roleFriendAO().queryNailiList(doc.getId());
		List<RoleFriend> applyListDoc 	= roleFriendAO().queryApplyList(doc.getId());
		
		List<FriendListVO> friendList 	= roleFriendAO().tidyFriendList(friendListDoc);
		List<NailiListVO> nailiList		= roleFriendAO().tidyNailiList(nailiListDoc);
		List<RequestListVO> requestList	= roleFriendAO().tidyRequestList(applyListDoc);
		List<RecommendVO> recommendList	= roleFriendAO().getRecommendList(doc,Constants.FriendRecomNum);
		int naiLiNum					= Constants.FriendGetNailiMaxLimit - doc.getGetFriendNailiCnt();
		return new GetFriendListVO( friendList, 
									nailiList,
									requestList, 
									naiLiNum,
									recommendList);
	}
	//断交
	@Override
	public Map<String, Integer> removeFriend(String acc, String friendAcc)
			throws BizException {
		Role doc 	   			= roleAO().queryExistAccount(acc); 
		Role friendDoc 			= roleAO().queryExistAccount(friendAcc); 
		Map<String,Integer> obj = new HashMap<String,Integer>();
		if(roleFriendAO().checkIsFriendRelation(doc.getId(), friendDoc.getId(), null)){
			throw BaseException.getException(//已被解除好友关系
					RoleFriendException.EXCE_FRIEND_RELIEVE);
		}
		
		roleFriendAO().breakFriend(doc.getId(),friendDoc.getId());
		roleFriendAO().clearChatData(doc.getId(),friendDoc.getId());
		
		obj.put("result", RoleFriend.FRIEND_OP_SUCCESS);
		return obj;
	}

	@Override
	public List<RecommendVO> recommendList(String acc, int num) throws BizException {
		Role doc 	   					= roleAO().queryExistAccount(acc); 
		List<RecommendVO> recommendList = roleFriendAO().getRecommendList(doc,num);
		return recommendList;
	}

	@Override
	public Map<String, Integer> applyFriend(String acc, String applyAcc,
			String content) throws BizException{
		Role doc 	   			   = roleAO().queryExistAccount(acc); 
		Role friendDoc 			   = roleAO().queryExistAccount(applyAcc); //对方		
		if(doc.getId() == friendDoc.getId()){
			throw BaseException.getException(//不能申请自己为好友
					RoleFriendException.EXCE_FRIEND_CAN_NOT_APPLY_SELF);
		}
		
		if(roleFriendAO().queryCountByRoelId(doc.getId()) >= Constants.FriendNumLimit){
			throw BaseException.getException(//自己好友列表已达上限
					RoleFriendException.EXCE_FRIEND_OWN_FRIEND_NUM_LIMIT);
		}
		
		List<Integer> friendIdList = roleFriendAO().queryFriendId(friendDoc.getId(),RoleFriend.FRIEND_STATUS_APPLY);
		if(friendIdList.size() >= Constants.FriendApplyListMaxNum){
			throw BaseException.getException(//对方申请列表已满，不能发送申请
					RoleFriendException.EXCE_FRIEND_THAT_APPLYLIST_FULL);
		}
		
		Map<String,Integer> obj	   = new HashMap<String,Integer>();
		if(friendIdList.contains(doc.getId())){
			obj.put("result", 2);//已申请
			return obj;
		}
		//没有记录则添加   有断交记录则修改状态为申请
		RoleFriend curData = roleFriendAO().queryOneFriend(friendDoc.getId(),doc.getId());
		if(curData == null){
			roleFriendAO().add(friendDoc.getId(),doc.getId(),content,RoleFriend.FRIEND_STATUS_APPLY);
		}else{
			roleFriendAO().updateFriendStatus(friendDoc.getId(),doc.getId(),content,RoleFriend.FRIEND_STATUS_APPLY);
		}		
		obj.put("result", RoleFriend.FRIEND_OP_SUCCESS);//申请成功
		return obj;
	}

	@Override
	public SearchFriendVO searchFriend(String acc, int type, String content,
			int searchNum, int flag) throws BizException {
		Role doc = roleAO().queryExistAccount(acc); 		
		if(type == RoleFriend.FRIEND_SEARCH_BY_ID){
			List<Integer> friendIdList = roleFriendAO().queryFriendId(doc.getId(), RoleFriend.FRIEND_STATUS_AGREE);
			if(friendIdList.contains(Integer.parseInt(content))){
				throw BaseException.getException(//搜索的id 已经是好友了
						RoleFriendException.EXCE_FRIEND_SEARCH_ID_HAS_BEEN_FRIEND);
			}
		}		
		
		List<Role> friendList = roleFriendAO().searchFriends(doc,type,content,searchNum,flag);
		if(flag == 0 && friendList.size() == 0){
			throw BaseException.getException(//搜索的玩家不存在
					RoleFriendException.EXCE_FRIEND_SEARCH_NOT_EXIST);
		}
		SearchFriendVO rtn;
		if(friendList.size() == 0){//没有再搜索到还有
			List<RecommendVO> recommendList = roleFriendAO().getRecommendList(doc,Constants.FriendRecomNum);
			rtn = new SearchFriendVO(doc.getId(), recommendList);
		}else{
			rtn = roleFriendAO().tidySearchFriendList(friendList,doc.getId());
		}		
		return rtn;
	}

	@Override
	public Map<String, Integer> getNaili(String acc, String account) throws BizException {
		Role doc 	   			    = roleAO().queryExistAccount(acc); 
		Role friendDoc 				= roleAO().queryExistAccount(account);
		RoleFriend friendData 		= roleFriendAO().queryOneFriend(doc.getId(), friendDoc.getId());
		Map<String,Integer> obj	    = new HashMap<String,Integer>();
				
		if(doc.getResisVal() >= doc.getPropLimitAry().get(1)){
			throw BaseException.getException(//耐力已满
					RoleFriendException.EXCE_FRIEND_NAILI_FULL);
		}
		if(doc.getGetFriendNailiCnt() >= Constants.FriendGetNailiMaxLimit){//消耗完30次领取
			throw BaseException.getException(//超出上限
					RoleFriendException.EXCE_FRIEND_OVER_GET_LIMIT);
		}
		if(friendData.getBeSendNailiTime() == null || friendData.getIsGetNaili() == RoleFriend.FRIEND_NAILI_HAS_GET){
			throw BaseException.getException(
					RoleFriendException.EXCE_FRIEND_NAILI_CAN_NOT_GET);
		}
		
		packetAO().addItem(doc, Packet.POS_ATTR, Packet.ATTR_耐力, Constants.FriendSendNailiNum,
				RoleItemLog.SYS_好友_领取耐力,""+friendDoc.getId());		
		roleFriendAO().getOneNaili(doc,friendData);	
		
		obj.put("nailiNum", Constants.FriendSendNailiNum);
		return obj;
	}

	@Override
	public GetNaiLiAllVO getNailiAll(String acc) throws BizException {
		Role doc 				= roleAO().queryExistAccount(acc); 
		List<RoleFriend> friendDocList 	= roleFriendAO().queryGetAllNailiFriend(doc.getId());
		if(friendDocList.size() == 0){
			throw BaseException.getException(//没有可领取
					RoleFriendException.EXCE_FRIEND_NO_NAILI_FOR_GET);
		}
		if(doc.getGetFriendNailiCnt() >= Constants.FriendGetNailiMaxLimit){//消耗完30次领取
			throw BaseException.getException(//领取次数已满	
					RoleFriendException.EXCE_FRIEND_OVER_GET_LIMIT);
		}
		if(doc.getResisVal() >= doc.getPropLimitAry().get(1)){
			throw BaseException.getException(//耐力已满
					RoleFriendException.EXCE_FRIEND_NAILI_FULL);
		}		
		//返回数据
		int nailiNum 					= roleFriendAO().getAndSendNailiAll(doc,friendDocList);
		int restNailiNum				= Constants.FriendGetNailiMaxLimit - doc.getGetFriendNailiCnt();
		List<RoleFriend> friendListDoc 	= roleFriendAO().queryFriendList(doc.getId());	
		List<RoleFriend> nailiListDoc   = roleFriendAO().queryNailiList(doc.getId());
		
		List<FriendListVO> friendList 	= roleFriendAO().tidyFriendList(friendListDoc);
		List<NailiListVO> nailiList   	= roleFriendAO().tidyNailiList(nailiListDoc);		
		return new GetNaiLiAllVO(friendList,nailiList, nailiNum, restNailiNum>0?restNailiNum:0);
	}
	//同意某人
	@Override
	public Map<String, Integer> acceptFriend(String acc, String account)
			throws BizException {
		Role doc 				= roleAO().queryExistAccount(acc); 
		Role friendDoc  		= roleAO().queryExistAccount(account); 
		int ownNum 				= roleFriendAO().queryCountByRoelId(doc.getId());
		int opposNum 			= roleFriendAO().queryCountByRoelId(friendDoc.getId());
		Map<String,Integer> obj = new HashMap<String,Integer>();
		
		if(ownNum >= Constants.FriendNumLimit){
			throw BaseException.getException(//自己好友数达上限
					RoleFriendException.EXCE_FRIEND_OWN_FRIEND_NUM_LIMIT);
		}
		if(opposNum >= Constants.FriendNumLimit){
			throw BaseException.getException(//对方上限
					RoleFriendException.EXCE_FRIEND_OPPOS_FRIEND_NUM_LIMIT);
		}
		//同意申请
		roleFriendAO().updateFriendStatus(doc.getId(), friendDoc.getId(), "",RoleFriend.FRIEND_STATUS_AGREE);
		//加为对方好友
		RoleFriend friendData = roleFriendAO().queryOneFriend(friendDoc.getId(),doc.getId());
		if(friendData == null){
			roleFriendAO().add(friendDoc.getId(),doc.getId(),"",RoleFriend.FRIEND_STATUS_AGREE);
			friendData = roleFriendAO().queryOneFriend(friendDoc.getId(),doc.getId());
		}else{
			roleFriendAO().updateFriendStatus(friendDoc.getId(),doc.getId(),"",RoleFriend.FRIEND_STATUS_AGREE);
		}
		//自己好友值
		missionExecAO().friendAdd(doc);
		//对方好友值
		missionExecAO().friendAdd(friendDoc);
		int isSendNaili = DateUtil.isToday(friendData.getSendNailiTime())==true?RoleFriend.FRIEND_NAILI_HAS_SEND:RoleFriend.FRIEND_NAILI_NOT_SEND;
		obj.put("result", RoleFriend.FRIEND_OP_SUCCESS);
		obj.put("isSendNaili", isSendNaili);
		return obj;
	}

	@Override
	public Map<String, Integer> rejectFriend(String acc, String account) throws BizException {
		Role doc 	   			= roleAO().queryExistAccount(acc); 
		Role friendDoc 			= roleAO().queryExistAccount(account); 
		Map<String,Integer> obj = new HashMap<String,Integer>();
		//拒绝 设置申请信息状态为已拒绝	
		roleFriendAO().updateFriendStatus(doc.getId(),friendDoc.getId(),"",RoleFriend.FRIEND_STATUS_REFUSE);
		
		obj.put("result", RoleFriend.FRIEND_OP_SUCCESS);
		return obj;
	}

	@Override
	public AcceptAllVO acceptAll(String acc) throws BizException {
		Role doc 	   					= roleAO().queryExistAccount(acc); 
		int ownNum 						= roleFriendAO().queryCountByRoelId(doc.getId());
		List<RoleFriend> appList		= roleFriendAO().queryApplyList(doc.getId());
		//同意
		int firendLimitType 			= roleFriendAO().acceptAllApply(doc, ownNum, appList);
		List<RoleFriend> friendListDoc 	= roleFriendAO().queryFriendList(doc.getId());
		List<RoleFriend> applyListDoc 	= roleFriendAO().queryApplyList(doc.getId());		
		List<FriendListVO> friendList	= roleFriendAO().tidyFriendList(friendListDoc);
		List<RequestListVO> requestList	= roleFriendAO().tidyRequestList(applyListDoc);		
		
		return new AcceptAllVO(friendList,requestList,firendLimitType);
	}

	@Override
	public Map<String, Integer> rejectAll(String acc) throws BizException {
		Role doc 	   			= roleAO().queryExistAccount(acc); 
		List<RoleFriend> appList= roleFriendAO().queryApplyList(doc.getId());
		Map<String,Integer> obj = new HashMap<String,Integer>();
		
		roleFriendAO().rejectAllApply(appList);
		obj.put("result", RoleFriend.FRIEND_OP_SUCCESS);
		return obj;
	}

	@Override
	public Map<String, Integer> sendNaili(String acc, String account) throws BizException {
		Role doc 	   			= roleAO().queryExistAccount(acc); 
		Role friendDoc 	   		= roleAO().queryExistAccount(account); 
		RoleFriend ownData		= roleFriendAO().queryOneFriend(doc.getId(), friendDoc.getId());
		RoleFriend friendData	= roleFriendAO().queryOneFriend(friendDoc.getId(), doc.getId());
		Map<String,Integer> obj = new HashMap<String,Integer>();
		if(ownData == null || friendData == null || ownData.getStatus() != RoleFriend.FRIEND_STATUS_AGREE){
			throw BaseException.getException(//没数据 || 不是好友
					RoleFriendException.EXCE_FRIEND_NOT_FRIEND);
		}
				
		if(DateUtil.isToday(friendData.getBeSendNailiTime())){
			throw BaseException.getException(//今天已经赠送过
					RoleFriendException.EXCE_FRIEND_HAS_SEND);
		}
		roleFriendAO().updateSendNaili(friendData);
		roleFriendAO().updataSendNailiTime(ownData);
		//TASK
		missionExecAO().friendSendGift(doc, 1);
		obj.put("result", RoleFriend.FRIEND_OP_SUCCESS);
		return obj;
	}
	@Override
	public List<ChatListVO> updateChatList(String acc) throws BizException {		
		Role doc 	   				= roleAO().queryExistAccount(acc); 
		List<Integer> friendIdList  = roleFriendAO().queryFriendId(doc.getId(), RoleFriend.FRIEND_STATUS_AGREE);
		List<ChatListVO> chatList	= roleFriendAO().tidyChatList(doc,friendIdList);
		
		return chatList;
	}
}
