package com.mdy.dzs.game.domain.swordfight;

import java.io.Serializable;

/**
 * 返回的卡牌列表
 * @author zhouxiaoyan
 *
 */
public class ClientSwordCardVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**id*/
	private int id;
	/**卡牌表id*/
	private int cardId;
	/**卡牌等级*/
	private int level;
	/**卡牌阶数*/
	private int cls;
	/**卡牌品质*/
	private int star;
	/**卡牌生命*/
	private int life;
	/**阵上位置*/
	private int pos;
	/**生命的上限*/
	private int initLife;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getInitLife() {
		return initLife;
	}
	public void setInitLife(int initLife) {
		this.initLife = initLife;
	}
}
