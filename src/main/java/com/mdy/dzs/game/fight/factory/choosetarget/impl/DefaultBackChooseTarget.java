/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 4	主目标身后单体
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午5:11:23
 */
public class DefaultBackChooseTarget extends DefaultChooseTarget {
	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter,int param) {
		preChoose(main, fighter,param);
		int pos = defaultPos();
		if(!isTargetDead(pos+3)){
			res.add(target.get(pos+3));
		}else{
			res.add(target.get(pos));
		}
		return res;
	}
}
