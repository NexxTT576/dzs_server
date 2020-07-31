package com.mdy.dzs.game.domain.swordfight;

import java.io.Serializable;
import java.util.List;

/**
 * 返回的角色列表
 * @author zhouxiaoyan
 *
 */
public class ClientSwordRoleVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**敌人id*/
	private int id;
	/**所属层*/
	private int floor;
	/**战斗力*/
	private int combat;
	/**显示id*/
	private int showId;
	/**阵上卡牌*/
	private List<ClientSwordCardVO> cards;
	/**名字*/
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getCombat() {
		return combat;
	}
	public void setCombat(int combat) {
		this.combat = combat;
	}
	public List<ClientSwordCardVO> getCards() {
		return cards;
	}
	public void setCards(List<ClientSwordCardVO> cards) {
		this.cards = cards;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	
}
