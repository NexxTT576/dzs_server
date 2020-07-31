/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 6	一列（前后最多2个）
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午5:47:16
 */
public class ColumnChooseTarget extends DefaultChooseTarget {

	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter,int param) {
		preChoose(main, fighter,param);
		int pos = defaultPos();
		res.add(target.get(pos));
		if(!isTargetDead(pos+3)){
			res.add(target.get(pos+3));
		}
		return res;
	}
}
