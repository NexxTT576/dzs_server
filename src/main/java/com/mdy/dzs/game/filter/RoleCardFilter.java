/**
 * 
 */
package com.mdy.dzs.game.filter;

import java.util.List;

/**
 * 卡片过滤器
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月15日  下午8:47:15
 */
public class RoleCardFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Integer> ids;
	private List<Integer> resIds;
	private Integer roleId;
	private Integer lock;
	
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public List<Integer> getResIds() {
		return resIds;
	}
	public void setResIds(List<Integer> resIds) {
		this.resIds = resIds;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getLock() {
		return lock;
	}
	public void setLock(Integer lock) {
		this.lock = lock;
	}
	
	
}
