/**
 * 
 */
package com.mdy.dzs.game.domain.role;

import java.io.Serializable;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月21日  上午11:43:23
 */
public class RoleIdName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int roleId;
	private String roleName;
	private String account;
	private String nickName;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
