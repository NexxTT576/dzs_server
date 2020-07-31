package com.mdy.dzs.game.fight.factory.choosetarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.battle.BattleSkill;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.ALLChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.AngerChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.BackSingleChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.ColumnChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.DefaultBackChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.DefaultChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.DefaultTargetColumn;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.DefultTargetRow;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.LifeChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.MainChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.OwnMinLifeChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.RandomChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.RowBackChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.RowChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.SelfChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.SexChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.SingleChooseTarget;
import com.mdy.dzs.game.fight.factory.choosetarget.impl.TChooseTarget;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

public class SkillChooseTarget {

	private Map<Integer, ChooseTarget> chooseTargets;
	
	/**
	 * 
	 */
	public SkillChooseTarget() {
		chooseTargets = new HashMap<Integer, ChooseTarget>();
		chooseTargets.put(0, new DefaultChooseTarget());
		chooseTargets.put(1, new SingleChooseTarget());
		chooseTargets.put(2, new SelfChooseTarget());
		chooseTargets.put(3, new BackSingleChooseTarget());
		chooseTargets.put(4, new DefaultBackChooseTarget());
		chooseTargets.put(5, new RandomChooseTarget(1));
		chooseTargets.put(6, new ColumnChooseTarget());
		chooseTargets.put(7, new RandomChooseTarget(2));
		chooseTargets.put(8, new RowChooseTarget());
		chooseTargets.put(9, new RowBackChooseTarget());
		chooseTargets.put(10, new RandomChooseTarget(3));
		chooseTargets.put(11, new TChooseTarget());
		chooseTargets.put(12, new ALLChooseTarget());
		chooseTargets.put(13, new LifeChooseTarget(true));
		chooseTargets.put(14, new LifeChooseTarget(false));
		chooseTargets.put(15, new AngerChooseTarget(true));
		chooseTargets.put(16, new AngerChooseTarget(false));
		chooseTargets.put(17, new MainChooseTarget());
		chooseTargets.put(18, new SexChooseTarget(1));
		chooseTargets.put(19, new SexChooseTarget(2));
		
		chooseTargets.put(24, new OwnMinLifeChooseTarget(3));
		chooseTargets.put(25, new DefultTargetRow());
		chooseTargets.put(26, new DefaultTargetColumn());
	}
	
	
	public List<Fighter> chooseTarget(FightMain main,Fighter fighter,BattleSkill skillData ,List<Fighter> defaultTargets){
		int type = skillData.getDAttr1().get(1);//目标类型
		int param = skillData.getDAttr1().get(0);//防守方、攻击方
		if( type == 0 && defaultTargets != null && defaultTargets.size()>0){//默认目标
			List<Fighter> res = new ArrayList<Fighter>();
			res = getLive(defaultTargets);
			return res;
		}
		ChooseTarget choose = chooseTargets.get(type);
		if(choose == null) choose = chooseTargets.get(0);
		if(type == 25){
			((DefultTargetRow)choose).setDefaultTarget(defaultTargets.get(0));
		}else if(type == 26){
			((DefaultTargetColumn)choose).setDefaultTarget(defaultTargets.get(0));
		}
		return choose.chooseTarget(main, fighter,param);
	}
	
	public Fighter chooseTarget(FightMain main,Fighter fighter,List<Fighter> defaultTargets){
		if( defaultTargets != null && defaultTargets.size()>0){//默认目标
			List<Fighter> res = new ArrayList<Fighter>();
			res = getLive(defaultTargets);
			return res.get(0);
		}
		ChooseTarget choose = chooseTargets.get(0);
		return choose.chooseTarget(main, fighter,0).get(0);
	}
	
	public List<Fighter> chooseTarget(FightMain main,Fighter fighter,int type,int parm,List<Fighter> defaultTargets){
		if( type == 0 && defaultTargets != null && defaultTargets.size()>0){//默认目标
			List<Fighter> res = new ArrayList<Fighter>();
			res = getLive(defaultTargets);
			return res;
		}
		ChooseTarget choose = chooseTargets.get(type);
		return choose.chooseTarget(main, fighter,parm);
	}
	
	/**
	 * 得到活着的目标
	 * @param targets
	 * @return
	 */
	private List<Fighter> getLive(List<Fighter> targets){
		List<Fighter> res = new ArrayList<Fighter>();
		for (Fighter tgt : targets) {
			if(tgt != null && !tgt.isDead()){
				res.add(tgt);
			}
		}
		return res;
	}
}
