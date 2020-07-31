package com.mdy.dzs.game.exception;

public class RoleFriendException extends BaseException{
	//不能领取
	public static final ExceptionEntry EXCE_FRIEND_NAILI_CAN_NOT_GET = createExceptionEntry(
			3200001, "can't get");
	//耐力已满
	public static final ExceptionEntry EXCE_FRIEND_NAILI_FULL = createExceptionEntry(
			3200002, "naili full");
	//超出每日可领取上限
	public static final ExceptionEntry EXCE_FRIEND_OVER_GET_LIMIT = createExceptionEntry(
			3200003, "overtop get naili limit");
	//自己好友已达上限
	public static final ExceptionEntry EXCE_FRIEND_OWN_FRIEND_NUM_LIMIT = createExceptionEntry(
			3200004, "own friend num limit");
	//对方好友已达上限
	public static final ExceptionEntry EXCE_FRIEND_OPPOS_FRIEND_NUM_LIMIT = createExceptionEntry(
			3200005, "oppos friend num limit");
	//已经赠送过
	public static final ExceptionEntry EXCE_FRIEND_HAS_SEND = createExceptionEntry(
			3200006, "naili has send");
	//搜所的id已经是好友了
	public static final ExceptionEntry EXCE_FRIEND_SEARCH_ID_HAS_BEEN_FRIEND = createExceptionEntry(
			3200007, "ID in friend id list");
	//已解除好友关系 
	public static final ExceptionEntry EXCE_FRIEND_RELIEVE = createExceptionEntry(
			3200008, "ID in friend id list");
	//不能申请自己为好友
	public static final ExceptionEntry EXCE_FRIEND_CAN_NOT_APPLY_SELF = createExceptionEntry(
			3200009, "can't apply self");
	//此玩家好友申请已满，稍后添加
	public static final ExceptionEntry EXCE_FRIEND_THAT_APPLYLIST_FULL = createExceptionEntry(
			3200010, "other's apply list full");
	//没有可领取的耐力
	public static final ExceptionEntry EXCE_FRIEND_NO_NAILI_FOR_GET = createExceptionEntry(
			3200011, "no naili can get");
	//不能赠送，还不是好友
	public static final ExceptionEntry EXCE_FRIEND_NOT_FRIEND = createExceptionEntry(
			3200012, "not friend");
	//搜索的玩家不存在
	public static final ExceptionEntry EXCE_FRIEND_SEARCH_NOT_EXIST = createExceptionEntry(
			3200105, "search not exixt");
	
	
}
