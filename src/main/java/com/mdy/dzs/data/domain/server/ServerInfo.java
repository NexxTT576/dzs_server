package com.mdy.dzs.data.domain.server;

import java.io.Serializable;
import java.util.Date;

/**
 * ServerInfo类表示一个服务器的信息
 *
 */
public class ServerInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//
	/**准备*/
	public static final int RUN_STATUS_READY=0;
	/**启动*/
	public static final int RUN_STATUS_STARTED=1;
	/**停止 */
	public static final int RUN_STATUS_STOPPED=2;
	/** 未知*/
	public static final int RUN_STATUS_NA = 3;
	
	
	public static final int MERGER_TYPE_独服1 = 0; 
	public static final int MERGER_TYPE_独服2 = 1; 
	public static final int MERGER_TYPE_混服 = 2; 

	/**爆满= */
	public static final int MANAGE_STATUS_爆满=1;
	/**忙碌 */
	public static final int MANAGE_STATUS_忙碌=2;
	/**正常*/
	public static final int MANAGE_STATUS_正常=3;
	/**维护*/
	public static final int MANAGE_STATUS_维护=4;
	
	/**测试区*/
	public static final int DEBUG_STATUS_测试=0;
	/**正式区*/
	public static final int DEBUG_STATUS_正式=1;
	/**渠道区*/
	public static final int DEBUG_STATUS_渠道=2;

	//

	private int zoneId;
	
	/**唯一标示这台服务器的id*/
	private String id;
	
	/**服务器的名称*/
	private String name;
	
	private String name1;
	
	/**描述*/
	private String description="";
	
	/**是否激活 0没激活 1激活*/
	private int manageStatus;
	
	/**合服类型*/
	private int mergerType;
	
	/***/
	private int debugStatus;
	
	/***/
	private String manageInfo;
	
	/**ip地址*/
	private String addr;
	
	/**集群*/
	private String cluster;
	
	/**配置*/
	private String config="";
	
	/**端口*/
	private int port;
	
	/**最后活动时间*/
	private Date lastActiveTime;
	
	/**启动时间*/
	private Date startTime;
	
	/**状态 0:ready 1:started 2:stop*/
	private int status;
	
	/**最大会话数量*/
	private int maxSessionCount;
	
	/**会话数量*/
	private int sessionCount;
	
	/**掉线会话数量*/
	private int disConnSessionCount;
	
	/**gm操作key*/
	private String gmKey;
	
	/**创建时间*/
	private Date createTime;
	
	/**更新时间*/
	private Date updateTime;
	


	//
	public ServerInfo(){
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getManageStatus() {
		return manageStatus;
	}


	public void setManageStatus(int manageStatus) {
		this.manageStatus = manageStatus;
	}


	public String getManageInfo() {
		return manageInfo;
	}


	public void setManageInfo(String manageInfo) {
		this.manageInfo = manageInfo;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getCluster() {
		return cluster;
	}


	public void setCluster(String cluster) {
		this.cluster = cluster;
	}


	public String getConfig() {
		return config;
	}


	public void setConfig(String config) {
		this.config = config;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public Date getLastActiveTime() {
		return lastActiveTime;
	}


	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getMaxSessionCount() {
		return maxSessionCount;
	}


	public void setMaxSessionCount(int maxSessionCount) {
		this.maxSessionCount = maxSessionCount;
	}


	public int getSessionCount() {
		return sessionCount;
	}


	public void setSessionCount(int sessionCount) {
		this.sessionCount = sessionCount;
	}


	public int getDisConnSessionCount() {
		return disConnSessionCount;
	}


	public void setDisConnSessionCount(int disConnSessionCount) {
		this.disConnSessionCount = disConnSessionCount;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getDebugStatus() {
		return debugStatus;
	}


	public void setDebugStatus(int debugStatus) {
		this.debugStatus = debugStatus;
	}


	public String getGmKey() {
		return gmKey;
	}


	public void setGmKey(String gmKey) {
		this.gmKey = gmKey;
	}


	public int getMergerType() {
		return mergerType;
	}


	public void setMergerType(int mergerType) {
		this.mergerType = mergerType;
	}


	public String getName1() {
		return name1;
	}


	public void setName1(String name1) {
		this.name1 = name1;
	}


	public int getZoneId() {
		return zoneId;
	}


	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

}
