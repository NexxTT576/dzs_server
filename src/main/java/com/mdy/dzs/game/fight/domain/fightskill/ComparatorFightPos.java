package com.mdy.dzs.game.fight.domain.fightskill;

import java.util.Comparator;

import com.mdy.dzs.game.fight.main.Fighter;

public class ComparatorFightPos implements Comparator<Fighter> {
	@Override
	public int compare(Fighter o1, Fighter o2) {
		//根据id排序 正序
		return o1.getPos()-o2.getPos();
	}
}
