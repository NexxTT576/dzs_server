package com.mdy.dzs.game.domain.lineup;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.yuan.RoleYuan;

/**
 * 阵型
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月18日  下午4:18:21
 */
public class RoleLineup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pos;
	private String account;
	private List<RoleCard> cardList;
	/**
	 * 装备内功map 1-7
	 */
	private Map<Integer, List<Object>> equipMap;
	/**
	 * 精元 1-7
	 */
	private Map<Integer, List<RoleYuan>> yuanMap;
	
	public RoleLineup() {
		// TODO 自动生成的构造函数存根
	}
	
	public RoleLineup(String acc,int pos,List<RoleCard> cards,Map<Integer, List<Object>> equips,Map<Integer, List<RoleYuan>> yuans) {
		account = acc;
		this.pos = pos;
		cardList=cards;
		equipMap = equips;
		yuanMap = yuans;
	}
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public List<RoleCard> getCardList() {
		return cardList;
	}
	public void setCardList(List<RoleCard> cardList) {
		this.cardList = cardList;
	}
	public Map<Integer, List<Object>> getEquipMap() {
		return equipMap;
	}
	public void setEquipMap(Map<Integer, List<Object>> equipList) {
		this.equipMap = equipList;
	}
	
	public Map<Integer, List<RoleYuan>> getYuanMap() {
		return yuanMap;
	}
	public void setYuanMap(Map<Integer, List<RoleYuan>> yuanList) {
		this.yuanMap = yuanList;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
}
