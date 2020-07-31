/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月25日  下午9:51:54
 */
public class SingleChooseTarget extends DefaultChooseTarget  {

	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter, int param) {
		preChoose(main, fighter,param);
//		if(!isTargetDead(param)){
//			res.add(target.get(param));
//		}
		return endChoose();
	}
}
