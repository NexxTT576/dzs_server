package com.mdy.dzs.game.domain.snatch;

import java.util.Comparator;

public class ComparatorSnatchCard implements Comparator<BeSnatchCard> {
	
	
	@Override
	public int compare(BeSnatchCard item1, BeSnatchCard item2) {
		
		return item2.getStar()-item1.getStar();
	}


}
