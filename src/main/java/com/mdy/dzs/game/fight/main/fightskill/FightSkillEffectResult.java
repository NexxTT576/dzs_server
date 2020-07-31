package com.mdy.dzs.game.fight.main.fightskill;

import java.util.Map;

import com.mdy.dzs.game.fight.main.Fighter;

public class FightSkillEffectResult {

	/**伤害值*/
	int damageVal = 0;
	/**重复次数*/
	int cnt = 1;
	/**加血值*/
	int healVal = 0;
	/**增减怒气值*/
	Map<Fighter,Integer> angerVals;
	/**给攻击者加血*/
	private int selfHealVal = 0;
	
	public int getDamageVal() {
		return damageVal;
	}
	public int getCnt() {
		return cnt;
	}
	public int getHealVal() {
		return healVal;
	}
	public Map<Fighter, Integer> getAngerVals() {
		return angerVals;
	}
	public int getSelfHealVal() {
		return selfHealVal;
	}
	public void setSelfHealVal(int selfHealVal) {
		this.selfHealVal = selfHealVal;
	}
	
	
}
