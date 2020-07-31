package com.mdy.dzs.game.domain.swordfight;

import java.util.Comparator;

import com.mdy.dzs.data.domain.sword.Lunjian;

public class ComparatorLunjian implements Comparator<Lunjian>{

	@Override
	public int compare(Lunjian o1, Lunjian o2) {
		//根据id排序 倒序
		return o2.getId()-o1.getId();
	}
}
