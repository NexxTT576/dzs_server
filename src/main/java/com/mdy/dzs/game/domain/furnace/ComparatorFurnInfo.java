package com.mdy.dzs.game.domain.furnace;

import java.util.Comparator;

public class ComparatorFurnInfo implements Comparator<FurnInfoVO> {
	
	
	@Override
	public int compare(FurnInfoVO item1, FurnInfoVO item2) {
		if(item1.getStar()==item2.getStar()){
			return item1.getLevel()-item2.getLevel();
		}else
			return item1.getStar()-item2.getStar();
	}


}
