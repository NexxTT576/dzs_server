package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 好友表结构模型
 * @author 白雪林
 *
 */
public class RoleFriend implements Serializable{

	/**序列化id*/
	private static final long serialVersionUID    = 1L;
	
	//好友关系状态
	public static final int FRIEND_STATUS_APPLY   = 1;	//申请状态
	public static final int FRIEND_STATUS_AGREE   = 2;	//同意状态 已经是好友
	public static final int FRIEND_STATUS_BREAK   = 3;	//断交状态
	public static final int FRIEND_STATUS_REFUSE  = 4;	//已拒绝
	
	//是否已赠送耐力
	public static final int FRIEND_NAILI_NOT_SEND = 0;	//未赠送耐力		
	public static final int FRIEND_NAILI_HAS_SEND = 1;	//已经赠送耐力
	
	//是否已领取耐力
	public static final int FRIEND_NAILI_NOT_GET  = 2;	//未领取
	public static final int FRIEND_NAILI_HAS_GET  = 1;	//已领取
	public static final int FRIEND_NAILI_DEFAULT  = 0;	//默认初始值

	//是否在线
	public static final int FRIEND_ONLINE		  = 1;	//在线
	public static final int FRIEND_NOT_ONLINE	  = 0;	//离线
	
	//操作结果
	public static final int FRIEND_OP_SUCCESS	  = 1;	//操作成功
	
	//是否已发送好友申请 
	public static final int FRIEND_HAS_APPLY	  = 1;	//已发送
	public static final int FRIEND_NOT_APPLY	  = 0;	//未发送
	
	//推荐好友随机类型
	public static final int FRIEND_PROB_L_TEN	  = 1;	//小10级
	public static final int FRIEND_PROB_L_FIVE	  = 2;	//小5级
	public static final int FRIEND_PROB_B_FIVE	  = 3;	//大5级
	public static final List<Integer> PROBLIST	  = Arrays.asList(FRIEND_PROB_B_FIVE,FRIEND_PROB_L_FIVE,FRIEND_PROB_L_TEN);
	
	//搜索好友类型
	public static final int FRIEND_SEARCH_BY_ID	  = 0;	//通过ID搜索
	public static final int FRIEND_SEARCH_BY_NAME = 1;	//通过name搜索
	
	//是否有说话
	public static final int FRIEND_CHAT			  = 1;	//有说话
	public static final int FRIEND_NOT_CHAT		  = 0;  //没有说话
	
	/**id*/
	private int id;
	/**角色ID*/
	private int roleId;
	/**好友角色ID*/
	private int friendId;
	/**好友状态 1-申请 2-同意*/
	private int status;
	/**好友申请文字*/
	private String content;
	/**赠送耐力时间*/
	private Date sendNailiTime;
	/**被赠送耐力时间*/
	private Date beSendNailiTime;
	/**是否已领取对方送的耐力 0-default 1-已领取 2-未领取*/
	private int isGetNaili;
	/**查看到的聊天时间点*/
	private Date seeChatTime;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getFriendId(){
		return this.friendId;
	}
	public void setFriendId(int friendId){
		this.friendId=friendId;
	}
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int status){
		this.status=status;
	}
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public Date getSendNailiTime(){
		return this.sendNailiTime;
	}
	public void setSendNailiTime(Date sendNailiTime){
		this.sendNailiTime=sendNailiTime;
	}
	public Date getBeSendNailiTime(){
		return this.beSendNailiTime;
	}
	public void setBeSendNailiTime(Date beSendNailiTime){
		this.beSendNailiTime=beSendNailiTime;
	}
	public int getIsGetNaili(){
		return this.isGetNaili;
	}
	public void setIsGetNaili(int isGetNaili){
		this.isGetNaili=isGetNaili;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public Date getSeeChatTime() {
		return seeChatTime;
	}
	public void setSeeChatTime(Date seeChatTime) {
		this.seeChatTime = seeChatTime;
	}
}