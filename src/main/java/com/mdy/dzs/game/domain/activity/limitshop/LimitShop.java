package com.mdy.dzs.game.domain.activity.limitshop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LimitShop implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7104931210573150932L;
	private int id;
	private List<LimitShopInfo> itemInfo;
	private Date refreshTime;
	private Date createTime;
	private Date updateTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<LimitShopInfo> getItemInfo() {
		return itemInfo;
	}
	public void setItemInfo(List<LimitShopInfo> itemInfo) {
		this.itemInfo = itemInfo;
	}
	public Date getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
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
}
