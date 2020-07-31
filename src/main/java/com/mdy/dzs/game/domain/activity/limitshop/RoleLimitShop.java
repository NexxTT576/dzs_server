package com.mdy.dzs.game.domain.activity.limitshop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoleLimitShop implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5709589183944850948L;
	private int id;
	private int roleId;
	private List<LimitShopInfo> itemIds;
	private Date updateTime;
	private Date createTime;
	private Date refreshTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public List<LimitShopInfo> getItemIds() {
		return itemIds;
	}
	public void setItemIds(List<LimitShopInfo> itemIds) {
		this.itemIds = itemIds;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}
	
}
