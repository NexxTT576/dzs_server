package com.mdy.dzs.game.fight.main.fighttalent;

import com.mdy.dzs.game.fight.main.Fighter;

public class FightTalentRescueResult {

	private int talentId;
	private Fighter oldTarget;
	private Fighter curTarget;
	private double reduce;
	
	public FightTalentRescueResult(Fighter old,Fighter cur,FightTalentRescue talent) {
		talentId = talent.getId();
		oldTarget = old;
		curTarget = cur;
		reduce = talent.getReduceRate()*0.0001;
	}
	
	public int getTalentId() {
		return talentId;
	}
	public Fighter getOldTarget() {
		return oldTarget;
	}
	public Fighter getCurTarget() {
		return curTarget;
	}
	public double getReduce() {
		return reduce;
	}
	
	
}
