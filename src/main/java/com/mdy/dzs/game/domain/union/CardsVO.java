package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

import com.mdy.dzs.game.domain.card.RoleCard;

public class CardsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1926632422296933503L;
	/** 卡牌表id */
	private int cardId;
	/** 卡牌类型id */
	private int resId;
	/** 卡牌等级 */
	private int level;
	/** 卡牌阶数 */
	private int cls;
	/** 卡牌品质 */
	private int star;
	/** 卡牌生命 */
	private int life;
	/** 阵上位置 */
	private int pos;
	/** 生命的上限 */
	private int initLife;
	/** 是否使用过 */
	private int isUse;

	public static CardsVO ValueOf(RoleCard roleCard, int inUse) {
		CardsVO card = new CardsVO();
		card.cardId = roleCard.getId();
		card.resId = roleCard.getResId();
		card.level = roleCard.getLevel();
		card.cls = roleCard.getCls();
		card.star = roleCard.getStar();
		// card.life = roleCard.get
		card.pos = roleCard.getPos();
		// card.initLife = roleCard.get
		card.isUse = inUse;
		return card;
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

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}
}
