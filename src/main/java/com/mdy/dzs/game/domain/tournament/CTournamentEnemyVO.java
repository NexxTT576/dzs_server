package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 仇人列表
 * @author Administrator
 *
 */
public class CTournamentEnemyVO implements Serializable{
	public CTournamentEnemyVO(){
		cards = new ArrayList<CTournamentCardVO>();
	}
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/**角色id*/
	private int role_id;
	/**等级*/
	private int level;
	/**名字*/
	private String name;
	/**战力*/
	private int attack;
	/**积分*/
	private int score;
	/**卡牌*/
	private List<CTournamentCardVO> cards;
	
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
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
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<CTournamentCardVO> getCards() {
		return cards;
	}
	public void setCards(List<CTournamentCardVO> cards) {
		this.cards = cards;
	}
	
}
