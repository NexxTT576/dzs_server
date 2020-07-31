/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 18,19
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月26日  下午8:49:55
 */
public class SexChooseTarget extends DefaultChooseTarget {

	private int sex;
	
	public SexChooseTarget(int sex) {
		this.sex = sex;
	}
	
	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter, int param) {
		preChoose(main, fighter,param);
		for (Fighter tgt : target.values()) {
			if(tgt != null
			&&!tgt.isDead()
			&&tgt.getSex() == sex){
				res.add(tgt);
			}
		}
		return endChoose();
	}
}
