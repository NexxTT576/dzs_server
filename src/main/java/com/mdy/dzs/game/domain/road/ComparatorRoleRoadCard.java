package com.mdy.dzs.game.domain.road;

import java.util.Comparator;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.game.manager.CacheManager;

public class ComparatorRoleRoadCard implements Comparator<RoleRoadCard> {
	
	private CacheManager cacheManager;
	public ComparatorRoleRoadCard(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	@Override
	public int compare(RoleRoadCard o1, RoleRoadCard o2) {
		Card card1 = cacheManager.getValueByKey(Card.class, o1.getResId());
		Card card2 = cacheManager.getValueByKey(Card.class, o2.getResId());
		int type1 = getRoadCardType(card1);
		int type2 = getRoadCardType(card2);
		return type1 - type2;
	}
	
	
	
	private int getRoadCardType(Card cardData){
		int star = cardData.getStar().get(0);
		if(star == 5 && cardData.getHero() == 1){
			return RoleRoadCard.TYPE_豪杰;
		}else if(star == 5){
			return RoleRoadCard.TYPE_高手;
		}
		return RoleRoadCard.TYPE_新秀;
	}
}
