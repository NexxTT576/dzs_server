/**
 * 
 */
package com.mdy.dzs.game.filter;

import java.util.Date;

/**
 * 聊天过滤器
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月14日  下午7:55:26
 */
public class ChatFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int ORDER_BY_CRETAE_TIME_DESC = 1;
	public static final int ORDER_BY_CRETAE_TIME_ASC = 2;
	
	/**聊天类型*/
	private Integer chatType;
	/**收到方id*/
	private String sendName;
	/**收到方id*/
	private String reciveName;
	/**收到方id*/
	private Integer sendRoleId;
	/**收到方id*/
	private Integer reciveRoleId;
	
	private Date lastSeeTime;
	
	public Integer getChatType() {
		return chatType;
	}
	public void setChatType(Integer chatType) {
		this.chatType = chatType;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getReciveName() {
		return reciveName;
	}
	public void setReciveName(String reciveName) {
		this.reciveName = reciveName;
	}
	public Integer getSendRoleId() {
		return sendRoleId;
	}
	public void setSendRoleId(Integer sendRoleId) {
		this.sendRoleId = sendRoleId;
	}
	public Integer getReciveRoleId() {
		return reciveRoleId;
	}
	public void setReciveRoleId(Integer reciveRoleId) {
		this.reciveRoleId = reciveRoleId;
	}
	public void setLastSeeTime(Date lastSeeTime) {
		this.lastSeeTime = lastSeeTime;
	}
	
	public Date getLastSeeTime() {
		return this.lastSeeTime;
	}
}
