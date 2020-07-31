package com.mdy.dzs.data.domain.charge;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值订单模型
 * 
 */
public class ChargeOrder implements Serializable {

	/** 序列化id */
	private static final long serialVersionUID = 1L;
	/** 初始化 */
	public static final int STATUS_CHARGE_INIT = 0;
	/** 通知游戏服务器 */
	public static final int STATUS_CHARGE_NOTIFY = 1;
	/** 充值成功 */
	public static final int STATUS_CHARGE_FINISH = 2;
	/** 错误 */
	public static final int STATUS_CHARGE_NOTIFY_ERROR = 3;
	
	/** 订单ID */
	private int payId;
	/** 订单号 */
	private String token;
	/** 订单用户的account */
	private String account;
	/** 订单用户的IP */
	private String userIp;
	/** 订单用户的来源平台 */
	private String pf;
	/** 订单用户的物品信息 */
	private String payItem;
	/** 订单用户的支付流水号 */
	private String billno;
	/** 订单用户的发货类型 */
	private String provideType;
	/** 订单用户的发货结果 */
	private String provideErrno;
	/** 订单用户的错误信息 */
	private String provideErrmsg;
	/** 合作方ID */
	private int serviceId;
	/** 分区 */
	private String serverId;
	/** 角色ID */
	private int roleId;
	/** 角色名 */
	private String roleName;
	/**平台账号*/
	private String nickName;
	/**充值时的级别*/
	private int roleLevel;
	/**充值时的vip_level*/
	private int roleVipLevel;
	/**设备id*/
	private String roleDeviceUUID;
	/**游戏币数量*/
	private int roleAddGold;
	/**是否首次付费*/
	private int roleFirstCharge;
	/** 付费金额 */
	private float payPrice;
	/** 原始金额 */
	private float oldPrice;
	/** 订单状态 */
	private int payStatus;
	/** 备注 */
	private String summary;
	/** 通知游戏服务器时间 */
	private Date notifyTime;
	/** 服务器成功返回时间 */
	private Date finishTime;
	/** 付费时间 */
	private Date payTime;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	


	public int getPayId() {
		return this.payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getPf() {
		return this.pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getPayItem() {
		return this.payItem;
	}

	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}

	public String getBillno() {
		return this.billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getProvideType() {
		return this.provideType;
	}

	public void setProvideType(String provideType) {
		this.provideType = provideType;
	}

	public String getProvideErrno() {
		return this.provideErrno;
	}

	public void setProvideErrno(String provideErrno) {
		this.provideErrno = provideErrno;
	}

	public String getProvideErrmsg() {
		return this.provideErrmsg;
	}

	public void setProvideErrmsg(String provideErrmsg) {
		this.provideErrmsg = provideErrmsg;
	}
	
	public int getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public float getPayPrice() {
		return this.payPrice;
	}

	public void setPayPrice(float payPrice) {
		this.payPrice = payPrice;
	}

	public int getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getNotifyTime() {
		return this.notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	public Date getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}


	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}

	public int getRoleVipLevel() {
		return roleVipLevel;
	}

	public void setRoleVipLevel(int roleVipLevel) {
		this.roleVipLevel = roleVipLevel;
	}

	public String getRoleDeviceUUID() {
		return roleDeviceUUID;
	}

	public void setRoleDeviceUUID(String roleDeviceUUID) {
		this.roleDeviceUUID = roleDeviceUUID;
	}

	public int getRoleAddGold() {
		return roleAddGold;
	}

	public void setRoleAddGold(int roleAddGold) {
		this.roleAddGold = roleAddGold;
	}

	public int getRoleFirstCharge() {
		return roleFirstCharge;
	}

	public void setRoleFirstCharge(int roleFirstCharge) {
		this.roleFirstCharge = roleFirstCharge;
	}
	
	@Override
	public String toString() {
		return "ChargeOrder [payId=" + payId + ", token=" + token + ", account="
				+ account + ", userIp=" + userIp
				+ ", pf=" + pf + ", payItem=" + payItem
				+ ", billno=" + billno + ", provideType=" + provideType + ", provideErrno="
				+ provideErrno + ", provideErrmsg=" + provideErrmsg 
				+ ", serviceId=" + serviceId + ", serverId="
				+ serverId + ", roleId=" + roleId + ", roleName=" + roleName
				+ ", payPrice=" + payPrice + ", payStatus=" + payStatus
				+ ", summary=" + summary + ", notifyTime=" + notifyTime
				+ ", finishTime=" + finishTime + ", payTime=" + payTime
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
}