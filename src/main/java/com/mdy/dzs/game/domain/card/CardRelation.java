package com.mdy.dzs.game.domain.card;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.role.JiBan;

public class CardRelation {

	private Card card;
	private JiBan relation;
	
	public CardRelation(Card cardData, JiBan jbItem) {
		this.card = cardData;
		this.relation = jbItem;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public JiBan getRelation() {
		return relation;
	}

	public void setRelation(JiBan relation) {
		this.relation = relation;
	}
	
}
