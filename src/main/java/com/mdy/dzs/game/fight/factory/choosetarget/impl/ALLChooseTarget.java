/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**12
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月26日  下午8:53:58
 */
public class ALLChooseTarget extends DefaultChooseTarget {

	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter, int param) {
		preChoose(main, fighter,param);
		for (Fighter tgt : target.values()) {
			if(tgt != null && !tgt.isDead()){
				res.add(tgt);
			}
		}
		return endChoose();
	}
}
