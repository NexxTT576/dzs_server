/**
 * 
 */
package com.mdy.dzs.game.domain.arena;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.role.Role;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月10日  上午10:37:04
 */
public class RoleArenaRoleVO implements Serializable {

	private static final int maxCardNum = 4;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String acc;
	private int level;
	private String name;
	private String faction;
	private int vip;
	private int rank;
	private int getSilver;
	private int getPopual;
	private List<RoleArenaCardVO> card;
	
	
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
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
	public String getFaction() {
		return faction;
	}
	public void setFaction(String faction) {
		this.faction = faction;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getGetSilver() {
		return getSilver;
	}
	public void setGetSilver(int getSilver) {
		this.getSilver = getSilver;
	}
	public int getGetPopual() {
		return getPopual;
	}
	public void setGetPopual(int getPopual) {
		this.getPopual = getPopual;
	}
	public List<RoleArenaCardVO> getCard() {
		return card;
	}
	public void setCard(List<RoleArenaCardVO> card) {
		this.card = card;
	}
	
	public RoleArenaRoleVO(Role doc,Role cur,List<RoleCard> fmtCards,RoleArena data) {
		acc = doc.getAccount();
		level = doc.getLevel();
		name = doc.getName();
		faction = doc.getFaction();
		vip = doc.getVip();
		
		rank = data.getRank();
		getSilver = data.getAwardSilver() * cur.getLevel();
		getPopual = data.getAwardPopual();
		
		card = new ArrayList<RoleArenaCardVO>();
		for (RoleCard roleCard : fmtCards) {
			card.add(new RoleArenaCardVO(roleCard));
			if(card.size()>=maxCardNum) break;
		}
	}
	
	
	
}
