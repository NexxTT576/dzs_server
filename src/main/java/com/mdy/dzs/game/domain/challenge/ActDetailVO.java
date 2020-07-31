package com.mdy.dzs.game.domain.challenge;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.RoleLineupAid.RLineupCardVO;

public class ActDetailVO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**当前战斗力*/
	private int attrack;
	/**玩家等级*/
	private int level;
	/**当前的卡牌*/
	private List<RLineupCardVO> cards;
	
	public int getAttrack() {
		return attrack;
	}
	public void setAttrack(int attrack) {
		this.attrack = attrack;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<RLineupCardVO> getCards() {
		return cards;
	}
	public void setCards(List<RLineupCardVO> cards) {
		this.cards = cards;
	}
}
