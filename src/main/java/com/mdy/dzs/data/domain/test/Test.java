/**
 * 
 */
package com.mdy.dzs.data.domain.test;

import java.io.Serializable;
import java.util.Date;


/**
 * test模型
 * 
 */
public class Test implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	//
	public static final int STATUS_TEST=0;
	
	//
	/**主键*/
	private int id;
	/** 注册时间 */
	private Date createTime;
	/** 最后修改时间 */
	private Date updateTime;

	//

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
