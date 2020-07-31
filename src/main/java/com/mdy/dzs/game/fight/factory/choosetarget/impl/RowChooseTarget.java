/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.Arrays;
import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 8	一排（一排最多3个）
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午5:50:45
 */
public class RowChooseTarget extends DefaultChooseTarget {

	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter,int param) {
		preChoose(main, fighter,param);
		int pos = defaultPos();
		List<Integer> posAry = null;
		if(pos > 3){
			posAry = Arrays.asList(4,5,6);
		}else{
			posAry = Arrays.asList(1,2,3);
		}
		for (Integer p : posAry) {
			if(!isTargetDead(p)){
				res.add(target.get(p));
			}
		}
		return res;
	}
}
