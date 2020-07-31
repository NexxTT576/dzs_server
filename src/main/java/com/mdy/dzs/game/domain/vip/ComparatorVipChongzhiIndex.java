package com.mdy.dzs.game.domain.vip;

import java.util.Comparator;


public class ComparatorVipChongzhiIndex implements Comparator<VipListVO>{

	/**按index排序*/
	@Override
	public int compare(VipListVO data1, VipListVO data2) {
		return data1.getIndex()-data2.getIndex();
	}
}
