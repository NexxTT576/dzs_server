package com.mdy.dzs.game.domain.swordfight;

import java.util.Comparator;

import com.mdy.dzs.game.domain.role.Role;

public class ComparatorSwordRole implements Comparator<Role> {

	@Override
	public int compare(Role o1, Role o2) {
		//战斗力排序 正序
		return o1.getAttack() - o2.getAttack();
	}
}
