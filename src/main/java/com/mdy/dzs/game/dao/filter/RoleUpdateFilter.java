/**
 * 
 */
package com.mdy.dzs.game.dao.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月16日  下午3:20:00
 */
public class RoleUpdateFilter {

	private Map<String, Object> fieldValue = new HashMap<String, Object>();

	private String account;
	private int roleId;
	
	public Map<String, Object> getFieldValue() {
		return fieldValue;
	}

	public void addFieldValue(String field,Object value) {
		this.fieldValue.put(field, value);
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
	
}
