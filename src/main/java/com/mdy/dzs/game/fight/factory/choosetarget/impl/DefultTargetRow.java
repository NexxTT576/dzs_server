package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

public class DefultTargetRow extends DefaultChooseTarget {
	private Fighter defaultTarget;
	
	public DefultTargetRow() {
	}
	
	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter,int param) {
		preChoose(main, fighter,param);
		int pos = getDefaultTarget().getPos();
		if( pos >3 ){
			if(!isTargetDead(4)){
				res.add(target.get(4));
			}
			if(!isTargetDead(5)){
				res.add(target.get(5));
			}
			if(!isTargetDead(6)){
				res.add(target.get(6));
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
		}
		return res;
	}

	public Fighter getDefaultTarget() {
		return defaultTarget;
	}

	public void setDefaultTarget(Fighter defaultTarget) {
		this.defaultTarget = defaultTarget;
	}
}
