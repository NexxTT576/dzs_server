/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**11
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月26日  下午8:53:58
 */
public class TChooseTarget extends DefaultChooseTarget {
	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter, int param) {
		preChoose(main, fighter,param);
		int defpos = defaultPos();
		if( defpos >3 ){
			if(!isTargetDead(4)){
				res.add(target.get(4));
			}
			if(!isTargetDead(5)){
				res.add(target.get(5));
			}
			if(!isTargetDead(6)){
				res.add(target.get(6));
			}
			if(!isTargetDead(defpos-3)){
				res.add(target.get(defpos-3));
			}
		}else{
			if(!isTargetDead(1)){
				res.add(target.get(1));
			}
			if(!isTargetDead(2)){
				res.add(target.get(2));
			}
			if(!isTargetDead(3)){
				res.add(target.get(3));
			}
			if(!isTargetDead(defpos+3)){
				res.add(target.get(defpos+3));
			}
		}
		return endChoose();
	}
}
