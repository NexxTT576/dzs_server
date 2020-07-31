package com.mdy.dzs.data.domain.server;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 服务器采样数据抽象
 */
public class ServerSampleData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 这次采样的唯一id
	 */
	private int id;
	/**服务器id*/
	private String serverId;
	/**
	 * 会话数量
	 */
	private int sessionCount;
	/**
	 * 掉线空闲的会话数量
	 */
	private int disConnSessionCount;
	/**
	 * 采样时间
	 */
	private Date sampleTime;

	public ServerSampleData() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the sampleTime
	 */
	public Date getSampleTime() {
		return sampleTime;
	}

	/**
	 * @param sampleTime
	 *            the sampleTime to set
	 */
	public void setSampleTime(Date sampleTime) {
		this.sampleTime = sampleTime;
	}

	/**
	 * @return the sessionCount
	 */
	public int getSessionCount() {
		return sessionCount;
	}

	/**
	 * @param sessionCount
	 *            the sessionCount to set
	 */
	public void setSessionCount(int sessionCount) {
		this.sessionCount = sessionCount;
	}

	/**
	 * @return the disConnSessionCount
	 */
	public int getDisConnSessionCount() {
		return disConnSessionCount;
	}

	/**
	 * @param disConnSessionCount
	 *            the disConnSessionCount to set
	 */
	public void setDisConnSessionCount(int disConnSessionCount) {
		this.disConnSessionCount = disConnSessionCount;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	
}
