package com.mdy.dzs.game.fight.factory.choosetarget;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月25日  下午8:42:43
 */
public interface ChooseTarget {
		List<Fighter> chooseTarget(FightMain main,Fighter fighter,int param); 
}
