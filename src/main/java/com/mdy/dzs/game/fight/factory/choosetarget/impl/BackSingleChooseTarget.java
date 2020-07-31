/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 3	后面一排对应单体
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午5:09:28
 */
public class BackSingleChooseTarget extends DefaultChooseTarget {

	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter,int param) {
		preChoose(main, fighter,param);
		if(!isTargetDead(side2pos)){
			res.add(target.get(side2pos));
		}else{
			int pos = getBackSingle();
			res.add(target.get(pos));
		}
		return endChoose();
	}
	
}
