package com.mdy.dzs.game.domain.road;

import java.util.Comparator;

public class ComparatorRoleRoadGift implements Comparator<RoleRoadGift> {
	
	@Override
	public int compare(RoleRoadGift o1, RoleRoadGift o2) {
		return o1.getPrice()-o2.getPrice();
	}


}
