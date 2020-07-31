package com.mdy.dzs.game.filter;

import java.util.Date;

// 邮件过滤
public class MailFilter {

	private static final long serialVersionUID = 1L;
	public static final int ORDER_BY_TIME_DESC = 1;
	public static final int ORDER_BY_TIME_ASC = 2;
	
	//已发送id
	private Integer id;
	//查询条数
	private Integer num;
	//查询日期限制
	private Date limitDate;
	//邮件类型
	private Integer type;
	//角色ID
	private Integer roleId;
	//排序规则
	private Integer orderType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	
}
