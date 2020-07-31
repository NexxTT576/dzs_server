package com.mdy.dzs.game.domain.rank;

import java.io.Serializable;
import java.util.Date;


/**
 * 玩家排行
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年1月28日  下午8:44:34
 */
public class RoleRank implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**玩家id*/
	private int roleId;
	private String account;
	/**玩家名称*/
	private String name;
	/**头像id*/
	private int resId;
	
	
	/**战斗力排行*/
	private int attackRank;
	/**等级排名*/
	private int gradeRank;
	/**副本排名*/
	private int battleRank;
	/**竞技场排名*/
	private int arenaRank;
	
	/**实力*/
	private int attack;
	/**等级*/
	private int grade;
	/**通关副本id*/
	private int battleId;
	/**副本星星*/
	private int battleStars;
	/**声望*/
	private int prestige;
	
	/**升级时间*/
	private Date levelUpLastTime;
	/**最后一次获得星时间*/
	private Date starHasLastTime;
	
	/**创建时间*/
	private Date createTime;
	/**最后更新时间*/
	private Date updateTime;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public int getAttackRank() {
		return attackRank;
	}

	public void setAttackRank(int attackRank) {
		this.attackRank = attackRank;
	}

	public int getGradeRank() {
		return gradeRank;
	}

	public void setGradeRank(int gradeRank) {
		this.gradeRank = gradeRank;
	}

	public int getBattleRank() {
		return battleRank;
	}

	public void setBattleRank(int battleRank) {
		this.battleRank = battleRank;
	}

	public int getArenaRank() {
		return arenaRank;
	}

	public void setArenaRank(int arenaRank) {
		this.arenaRank = arenaRank;
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

	public int getBattleId() {
		return battleId;
	}

	public void setBattleId(int battleId) {
		this.battleId = battleId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	
	public Date getLevelUpLastTime() {
		return levelUpLastTime;
	}

	public void setLevelUpLastTime(Date levelUpLastTime) {
		this.levelUpLastTime = levelUpLastTime;
	}

	public Date getStarHasLastTime() {
		return starHasLastTime;
	}

	public void setStarHasLastTime(Date starHasLastTime) {
		this.starHasLastTime = starHasLastTime;
	}

	@Override
	public String toString() {
		return "RoleRank [roleId=" + roleId + ", name=" + name
				+ ", resId=" + resId + ", attackRank=" + attackRank
				+ ", gradeRank=" + gradeRank + ", battleRank=" + battleRank
				+ ", arenaRank=" + arenaRank + ", attack=" + attack + ", grade="
				+ grade + ", battleId=" + battleId + ", battleStars=" + battleStars
				+ ", prestige=" + prestige + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}