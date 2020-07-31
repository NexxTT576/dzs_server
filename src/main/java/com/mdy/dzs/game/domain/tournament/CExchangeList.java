package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.List;

public class CExchangeList  implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	private int roleId;
	private int honor;
	private List<CExchangeListVO> list;
	public List<CExchangeListVO> getList() {
		return list;
	}
	public void setList(List<CExchangeListVO> list) {
		this.list = list;
	}
	public int getHonor() {
		return honor;
	}
	public void setHonor(int honor) {
		this.honor = honor;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
