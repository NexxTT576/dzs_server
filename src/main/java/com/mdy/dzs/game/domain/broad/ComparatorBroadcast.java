package com.mdy.dzs.game.domain.broad;

import java.util.Comparator;

public class ComparatorBroadcast implements Comparator<Broadcast> {

	@Override
	public int compare(Broadcast o1, Broadcast o2) {
		return (int)(o2.getTime().getTime()-o1.getTime().getTime());
	}


}
