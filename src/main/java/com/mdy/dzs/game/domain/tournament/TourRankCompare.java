package com.mdy.dzs.game.domain.tournament;

import java.util.Comparator;

/**
 * 比武积分排行
 * @author Administrator
 *
 */
public class TourRankCompare implements Comparator<RoleTourRankVO> {
	
	@Override
	public int compare(RoleTourRankVO o1, RoleTourRankVO o2) {
		return o2.getScore()-o1.getScore();
	}
}
