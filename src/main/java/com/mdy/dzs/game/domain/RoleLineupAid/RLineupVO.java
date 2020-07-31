package com.mdy.dzs.game.domain.RoleLineupAid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.domain.swordfight.SwordCard;

public class RLineupVO  implements Serializable{

	public RLineupVO(){
		cards = new ArrayList<RLineupCardVO>();
	}
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	private List<RLineupCardVO> cards;
	/**当前阵型*/
	private List<SwordCard> fmt;
	
	public List<RLineupCardVO> getCards() {
		return cards;
	}
	public void setCards(List<RLineupCardVO> cards) {
		this.cards = cards;
	}
	public List<SwordCard> getFmt() {
		return fmt;
	}
	public void setFmt(List<SwordCard> fmt) {
		this.fmt = fmt;
	}

}
