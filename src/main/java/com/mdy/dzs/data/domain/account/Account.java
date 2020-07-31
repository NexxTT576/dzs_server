package com.mdy.dzs.data.domain.account;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号模型
 * 
 * @author 房曈
 *
 */
public class Account implements Serializable {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	public static final int STATUS_ACTIVITY = 0;
	public static final int STATUS_FORBIDDEN = 1;
	public static final int STATUS_DISABLE_SENDMSG = 2;

	/** 主键 */
	private int id;
	/** 游戏账号 */
	private String account;
	/** 平台id */
	private int pfId;
	/** 平台id */
	private String pfUid;
	/** 平台账号 */
	private String pfAccount;
	/** 状态 */
	private int status;
	/** 最后登录时间 */
	private Date lastLoginTime;
	/** 登录ip */
	private String loginIp;
	/** 注册时间 */
	private Date createTime;
	/** 最后修改时间 */
	private Date updateTime;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPfUid() {
		return this.pfUid;
	}

	public void setPfUid(String pfUid) {
		this.pfUid = pfUid;
	}

	public String getPfAccount() {
		return this.pfAccount;
	}

	public void setPfAccount(String pfAccount) {
		this.pfAccount = pfAccount;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getPfId() {
		return pfId;
	}

	public void setPfId(int pfId) {
		this.pfId = pfId;
	}
}