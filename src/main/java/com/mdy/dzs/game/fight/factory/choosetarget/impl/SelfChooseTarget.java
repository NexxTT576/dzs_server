package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.Arrays;
import java.util.List;

import com.mdy.dzs.game.fight.factory.choosetarget.ChooseTarget;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 自己
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月25日  下午10:01:05
 */
public class SelfChooseTarget implements ChooseTarget {

	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter, int param) {
		return Arrays.asList(fighter);
	}

}
