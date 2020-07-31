package com.mdy.dzs.game.domain.RoleLineupAid;

import java.io.Serializable;

public class RLineupCardVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	public RLineupCardVO(int cardId ,int resId ,int cls ,int level ,int  star){
		this.cardId = cardId;
		this.resId = resId;
		this.cls = cls;
		this.level = level;
		this.star = star;
	}
	
	/**卡牌唯一id*/
	private int cardId;
	/**卡牌类型id*/
	private int resId;
	/**卡牌品质*/
	private int star;
	/**卡牌生命*/
	private int life;
	/**怒气值*/
	private int anger;
	/**卡牌的位置*/
	private int pos;
	/**生命总值*/
	private int initLife;
	/**阶*/
	private int cls;
	/**卡牌等级*/
	private int level;
	/**顺序*/
	private int order;
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getAnger() {
		return anger;
	}
	public void setAnger(int anger) {
		this.anger = anger;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}
