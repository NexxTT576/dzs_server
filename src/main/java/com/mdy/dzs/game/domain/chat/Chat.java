package com.mdy.dzs.game.domain.chat;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 聊天模型
 * 
 * @author 房曈
 *
 */
public class Chat implements Serializable {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	public static final int TYPE_世界 = 1;
	public static final int TYPE_私聊 = 2;
	public static final int TYPE_军团 = 3;
	public static final int TYPE_GM = 4;
	public static final int TYPE_好友 = 5;

	/***/
	private int id;
	/** 聊天类型(1世界2私聊3军团4GM) */
	private int type;
	/** 发送者id */
	private int sendRoleId;
	/** 接受方id */
	private int receiveRoleId;
	/** 发送方account */
	private String account;
	/** 发送方姓名 */
	private String sendRoleName;
	/** 接受方姓名 */
	private String receiveRoleName;
	/** vip */
	private int sendRoleVip;
	/** 性别(1男2女) */
	private int sendRoleSex;
	/** 发送人帮派 */
	private String sendRoleFaction;
	/** 聊天内容 */
	private String sendMsg;
	/***/
	private String sendPara1;
	/***/
	private String sendPara2;
	/***/
	private String sendPara3;
	/***/
	private Date createTime;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSendRoleId() {
		return this.sendRoleId;
	}

	public void setSendRoleId(int sendRoleId) {
		this.sendRoleId = sendRoleId;
	}

	public int getReceiveRoleId() {
		return this.receiveRoleId;
	}

	public void setReceiveRoleId(int receiveRoleId) {
		this.receiveRoleId = receiveRoleId;
	}

	@JsonProperty(value = "name")
	public String getSendRoleName() {
		return this.sendRoleName;
	}

	public void setSendRoleName(String sendRoleName) {
		this.sendRoleName = sendRoleName;
	}

	@JsonProperty(value = "recname")
	public String getReceiveRoleName() {
		return this.receiveRoleName;
	}

	public void setReceiveRoleName(String receiveRoleName) {
		this.receiveRoleName = receiveRoleName;
	}

	@JsonProperty(value = "vip")
	public int getSendRoleVip() {
		return this.sendRoleVip;
	}

	public void setSendRoleVip(int sendRoleVip) {
		this.sendRoleVip = sendRoleVip;
	}

	@JsonProperty(value = "sex")
	public int getSendRoleSex() {
		return this.sendRoleSex;
	}

	public void setSendRoleSex(int sendRoleSex) {
		this.sendRoleSex = sendRoleSex;
	}

	@JsonProperty(value = "msg")
	public String getSendMsg() {
		return this.sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}

	@JsonProperty(value = "para1")
	public String getSendPara1() {
		return this.sendPara1;
	}

	public void setSendPara1(String sendPara1) {
		this.sendPara1 = sendPara1;
	}

	@JsonProperty(value = "para2")
	public String getSendPara2() {
		return this.sendPara2;
	}

	public void setSendPara2(String sendPara2) {
		this.sendPara2 = sendPara2;
	}

	@JsonProperty(value = "para3")
	public String getSendPara3() {
		return this.sendPara3;
	}

	public void setSendPara3(String sendPara3) {
		this.sendPara3 = sendPara3;
	}

	@JsonProperty(value = "t")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSendRoleFaction() {
		return sendRoleFaction;
	}

	public void setSendRoleFaction(String sendRoleFaction) {
		this.sendRoleFaction = sendRoleFaction;
	}
}