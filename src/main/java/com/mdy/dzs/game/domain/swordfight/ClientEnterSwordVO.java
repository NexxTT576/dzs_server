package com.mdy.dzs.game.domain.swordfight;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给客户端的数据VO
 * @author zhouxiaoyan
 *
 */
public class ClientEnterSwordVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**金币*/
	private int gold;
	/**剩余免费重置次数*/
	private int resetTimes;
	/**当前层数*/
	private int curFloor;
	/**剩余元宝重置次数*/
	private int goldResetTimes;
	/**重置元宝数*/
	private int resetGold;
	/**20级以上的侠客列表*/
	private List<ClientSwordCardVO> cards;
	/**敌人列表*/
	private List<ClientSwordRoleVO> enemies;
	/**已经领取的奖励层*/
	private List<Integer> awards;
	/**名字*/
	private String name;
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getResetTimes() {
		return resetTimes;
	}
	public void setResetTimes(int resetTimes) {
		this.resetTimes = resetTimes;
	}
	public int getCurFloor() {
		return curFloor;
	}
	public void setCurFloor(int curFloor) {
		this.curFloor = curFloor;
	}
	public int getGoldResetTimes() {
		return goldResetTimes;
	}
	public void setGoldResetTimes(int goldResetTimes) {
		this.goldResetTimes = goldResetTimes;
	}
	public int getResetGold() {
		return resetGold;
	}
	public void setResetGold(int resetGold) {
		this.resetGold = resetGold;
	}
	public List<ClientSwordCardVO> getCards() {
		return cards;
	}
	public void setCards(List<ClientSwordCardVO> cards) {
		this.cards = cards;
	}
	public List<ClientSwordRoleVO> getEnemies() {
		return enemies;
	}
	public void setEnemies(List<ClientSwordRoleVO> enemies) {
		this.enemies = enemies;
	}
	public List<Integer> getAwards() {
		return awards;
	}
	public void setAwards(List<Integer> awards) {
		this.awards = awards;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
