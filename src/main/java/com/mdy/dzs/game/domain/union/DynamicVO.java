package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;
import java.util.List;








import com.mdy.dzs.game.domain.role.Role;
import com.mdy.sharp.util.JSONUtil;

public class DynamicVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3857873648269818121L;
	private List<Object> desList;
	private int roleLevel;
	private String roleAcc;
	private int resId;
	private int cls;
	private Date createTime;
	//对应数据库的type,策划表id
	private int id;
	private int unionId;
	private int roleId;
	private List<Object> paras;
	@SuppressWarnings("unchecked")
	public static DynamicVO valueOf(UnionDynamic dynamic,Role role){
		DynamicVO dy = new DynamicVO();
		dy.setDesList(JSONUtil.fromJson(dynamic.getDes(),List.class));
		dy.setCls(role.getCls());
		dy.setRoleLevel(role.getLevel());
		dy.setRoleAcc(role.getAccount());
		dy.setResId(role.getResId());
		dy.setCreateTime(dynamic.getCreateTime());
		dy.setId(dynamic.getType());
		dy.setUnionId(dynamic.getUnionId());
		dy.setRoleId(dynamic.getRoleId());
		return dy;
	}
	
	//青龙堂数据
	@SuppressWarnings("unchecked")
	public static DynamicVO tidyGreenDragonDynamic(UnionDynamic dynamic) {
		DynamicVO dy = new DynamicVO();
		List<Object> paras = JSONUtil.fromJson(dynamic.getParas(), List.class);
		dy.setDesList(JSONUtil.fromJson(dynamic.getDes(),List.class));
		dy.setCls((Integer) paras.get(1));
		dy.setRoleLevel((Integer) paras.get(0));
		dy.setResId(dynamic.getRoleId());
		dy.setCreateTime(dynamic.getCreateTime());
		dy.setId(dynamic.getType());
		dy.setUnionId(dynamic.getUnionId());
		dy.setRoleId(dynamic.getRoleId());		
		return dy;
	}
		
	public List<Object> getDesList() {
		return desList;
	}

	public void setDesList(List<Object> desList) {
		this.desList = desList;
	}

	public int getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}
	public String getRoleAcc() {
		return roleAcc;
	}
	public void setRoleAcc(String roleAcc) {
		this.roleAcc = roleAcc;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUnionId() {
		return unionId;
	}

	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public List<Object> getParas() {
		return paras;
	}

	public void setParas(List<Object> paras) {
		this.paras = paras;
	}

}
