package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

public class DefaultTargetColumn extends DefaultChooseTarget {
	private Fighter defaultTarget;
	
	public DefaultTargetColumn() {
	}
	
	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter,int param) {
		preChoose(main, fighter,param);
		int pos = defaultTarget.getPos();
		res.add(target.get(pos));
		if(!isTargetDead(pos+3)){
			res.add(target.get(pos+3));
		}else if(!isTargetDead(pos-3)){
			res.add(target.get(pos-3));
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
