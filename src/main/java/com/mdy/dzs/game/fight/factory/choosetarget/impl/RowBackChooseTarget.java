/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.Arrays;
import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午5:55:38
 */
public class RowBackChooseTarget extends DefaultChooseTarget {

	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter,int param) {
		preChoose(main, fighter,param);
		List<Integer> posAry = Arrays.asList(4,5,6);
		for (Integer p : posAry) {
			if(!isTargetDead(p)){
				res.add(target.get(p));
			}
		}
		if(res.size() == 0){
			posAry = Arrays.asList(1,2,3);
			for (Integer p : posAry) {
				if(!isTargetDead(p)){
					res.add(target.get(p));
				}
			}
		}
		return res;
	}
}
