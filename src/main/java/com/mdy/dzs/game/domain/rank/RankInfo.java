package com.mdy.dzs.game.domain.rank;

import java.io.Serializable;

/**
 * 排行榜信息
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年1月29日  下午2:43:50
 */
public class RankInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**竞技场排名*/
	public final static int TYPE_RANK_竞技场=1;
	/**等级排名*/
	public final static int TYPE_RANK_等级=2;
	/**实力排名*/
	public final static int TYPE_RANK_战力=3;
	/**副本星级*/
	public final static int TYPE_RANK_副本=4;
	
	/**玩家id*/
	private int roleId;
	private String account;
	/**玩家名称*/
	private String name;
	/**头像id*/
	private int resId;
	/**阶数*/
	private int cls;
	/**排名类型*/
	private int type;
	/**排名*/
	private int rank;
	/**实力*/
	private int attack;
	/**等级*/
	private int grade;
	/**副本星星*/
	private int battleStars;
	/**声望*/
	private int prestige;
	/**通关副本id*/
	private int battleId;
	/**帮派名*/
	private String faction;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getBattleStars() {
		return battleStars;
	}
	public void setBattleStars(int battleStars) {
		this.battleStars = battleStars;
	}
	public int getPrestige() {
		return prestige;
	}
	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}
	public int getBattleId() {
		return battleId;
	}
	public void setBattleId(int battleId) {
		this.battleId = battleId;
	}
	public String getFaction() {
		return faction;
	}
	public void setFaction(String faction) {
		this.faction = faction;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
	
	
}
