/**
 * 
 */
package com.mdy.dzs.game.domain.snatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 被挑战用户
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月13日  下午2:36:23
 */
public class BeSnatchRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_ROLE 	= 1;
	public static final int TYPE_NPC	= 2;
	
	private String acc;
	private int lv;
	private String name;
	private int type;
	private int warFreeTime;
	private int vip;
	private int easyType;
	private List<BeSnatchCard> card;
	
	public BeSnatchRole() {
		card = new ArrayList<BeSnatchCard>();
	}
	
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getWarFreeTime() {
		return warFreeTime;
	}
	public void setWarFreeTime(int warFreeTime) {
		this.warFreeTime = warFreeTime;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getEasyType() {
		return easyType;
	}
	public void setEasyType(int easyType) {
		this.easyType = easyType;
	}
	public List<BeSnatchCard> getCard() {
		return card;
	}
	public void setCard(List<BeSnatchCard> card) {
		this.card = card;
	}
	
	
}
