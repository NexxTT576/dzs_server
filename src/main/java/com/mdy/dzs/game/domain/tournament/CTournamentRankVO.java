package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 天榜的列表的个人信息
 * @author Administrator
 *
 */
public class CTournamentRankVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	public CTournamentRankVO(){
		cards = new ArrayList<CTournamentCardVO>();
	}
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
	/**排名*/
	private int rank;
	/**卡牌*/
	private List<CTournamentCardVO> cards;
	/**帮派*/
	private String faction;
	/**账号*/
	private String acc;
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
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public List<CTournamentCardVO> getCards() {
		return cards;
	}
	public void setCards(List<CTournamentCardVO> cards) {
		this.cards = cards;
	}
	public String getFaction() {
		return faction;
	}
	public void setFaction(String faction) {
		this.faction = faction;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
}
