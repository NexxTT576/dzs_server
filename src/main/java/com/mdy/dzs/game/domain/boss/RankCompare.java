package com.mdy.dzs.game.domain.boss;

import java.util.Comparator;

public class RankCompare implements Comparator<RankList>{

	@Override
	public int compare(RankList o1, RankList o2) {
		return o2.getTotalHurt()-o1.getTotalHurt();
	}

}

