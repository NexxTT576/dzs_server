package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;

/**
 * 匹配的对手的信息
 * @author Administrator
 *
 */
public class COpponentInfo implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/**玩家的角色id*/
	private int roleId;
	/**玩家的等级*/
	private int level;
	/**玩家的名字*/
	private String name;
	/**玩家的位置*/
	private int pos;
	/**玩家的品质*/
	private int quality;
	/**主角的id*/
	private int leadId;
	/**主角阶*/
	private int cls;
	/**帮派*/
	private String faction;
	/**账号*/
	private String acc;
	
	private int type;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getLeadId() {
		return leadId;
	}
	public void setLeadId(int leadId) {
		this.leadId = leadId;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getFaction() {
		return faction;
	}
	public void setFaction(String faction) {
		this.faction = faction;
	}
}
