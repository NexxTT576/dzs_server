package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

public class ChooseCardsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7127794064813963631L;
	private List<CardsVO> cardsList;
	private int state;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public List<CardsVO> getCardsList() {
		return cardsList;
	}
	public void setCardsList(List<CardsVO> cardsList) {
		this.cardsList = cardsList;
	}
	
}
