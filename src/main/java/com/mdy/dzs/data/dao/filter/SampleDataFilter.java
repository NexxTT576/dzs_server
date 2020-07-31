/**
 * 
 */
package com.mdy.dzs.data.dao.filter;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 服务器数据
 */
public class SampleDataFilter extends Filter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date startTime;
	private Date endTime;
	private String serverId;
	private String type;

	public SampleDataFilter() {
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
}
