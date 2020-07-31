package com.mdy.dzs.data.dao.filter;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付订单FilterVO
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月19日  下午4:58:01
 */
public class ChargeOrderFilter extends Filter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;
	private String accountName;
	private Date startTime;
	private Date endTime;
	private Integer payStatus;
	private Integer serviceId;
	private String serverId;
	//
	public ChargeOrderFilter() {
		super();
		token = null;
		accountName = null;
		startTime = null;
		endTime = null;
		payStatus = null;
		serviceId = null;
		setServerId(null);
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	
}
