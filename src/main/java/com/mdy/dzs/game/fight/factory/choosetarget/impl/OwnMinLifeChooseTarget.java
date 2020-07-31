/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mdy.dzs.game.fight.factory.comparator.FighterLifeComparator;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 血量最低
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月30日  下午5:45:30
 */
public class OwnMinLifeChooseTarget extends DefaultChooseTarget {

	private int num = 1;
	
	public OwnMinLifeChooseTarget(int num) {
		this.num = num;
	}
	
	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter,int param) {
		preChoose(main, fighter,param);
		List<Fighter> checkList = new ArrayList<Fighter>();
		for (Fighter f : self.values()) {
			if(f != null &&!f.isDead()){
				checkList.add(f);
			}
		}
		FighterLifeComparator compar = new FighterLifeComparator();
		Collections.sort(checkList,compar);
		return checkList.subList(0, checkList.size()>num?num:checkList.size());
	}
	
}
