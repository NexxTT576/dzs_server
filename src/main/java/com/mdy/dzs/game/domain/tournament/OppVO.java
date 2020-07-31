package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
/**
 * 对手存放的VO
 * @author Administrator
 *
 */
public class OppVO implements Serializable {
	public static final int 玩家 = 1;
	public static final int 机器人 = 2;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**位置*/
	private int pos;
	/**品质*/
	private int quality;
	/**角色id*/
	private int roleId;
	/**对手类型*/
	private int type;
	
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
