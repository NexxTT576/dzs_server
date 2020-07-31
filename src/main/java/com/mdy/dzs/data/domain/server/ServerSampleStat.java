package com.mdy.dzs.data.domain.server;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * server统计数据
 */
public class ServerSampleStat implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private int maxCount;
	private int sumCount;
	private int dataCount;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public int getDataCount() {
		return dataCount;
	}
}
