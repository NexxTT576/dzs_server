package com.mdy.dzs.game.domain.snatch;

import java.util.Comparator;

public class ComparatorSnatchItem implements Comparator<SnatchItem> {
	
	
	@Override
	public int compare(SnatchItem item1, SnatchItem item2) {
		
		return item1.getItem().getOrder()-item2.getItem().getOrder();
	}


}
