/**
 * 
 */
package com.mdy.dzs.game.fight.main.fighttalent;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.main.fightskill.FightBuff;
import com.mdy.dzs.game.fight.main.fightskill.FightSkill;

/**
 * 
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月29日  上午10:25:43
 */
public class FightTalentSkill implements FightTalentEffect {

	/**触发节点*/
	private int node;
	
	/**触发条件*/
	private int cond;
	/**触发条件参数*/
	private int condParm;
	
	/**触发概率*/
	private int prob;
	
	/**属性目标*/
	private int chooseType;
	/**属性目标方*/
	private int chooseParam;
	
	private FightSkill skill;
	
	public FightTalentSkill(Fighter src,
			int node,int cond,int condParm,int prob,
			int chooseType,int chooseParam,int skillId){
		this.node = node;
		this.cond = cond;
		this.condParm = condParm;
		this.prob = prob;
		this.chooseType = chooseType;
		this.chooseParam = chooseParam;
		if( node == FightTalentNode.永久被动技能 && cond == FightTalentCondition.格挡反击 ){
			this.skill = FightSkill.createSkill(src, skillId, FightSkill.TYPE_反击攻击);
		}else{
			this.skill = FightSkill.createSkill(src, skillId, FightSkill.TYPE_TALENT);
		}
	}
	
	/**
	 * 神通效果生效
	 * @param main
	 * @param src    拥有者
	 */
	public boolean effect(FightMain main,Fighter src,FightTalentVO vo,List<Fighter> defaultTargets,List<Fighter> missTargets){
		boolean res = false;
		do {
			if(skill == null) return res;
			if(node == FightTalentNode.永久被动技能 && cond == FightTalentCondition.格挡反击){
				//替换初始的格挡反击技能
				src.replaceBackSkill(skill);
				return true;
			}
			if(src.isBuffEffectExist(FightBuff.EFFECT_封神)) break;
			boolean isNode = false;
			List<Fighter> condTargets = new ArrayList<Fighter>();
			//其实一个满足条件则为满足
			int rom = (int)Math.round(Math.random()*10000);
			if( rom <= prob){
				for (Fighter fighter : defaultTargets) {
					if(FightTalentCondition.checkCond(src, fighter, cond, condParm)){
							isNode = true;
							condTargets.add(fighter);
						}
					}
				//闪避的条件
				if(cond == FightTalentCondition.闪避了另一方的攻击){
					for (Fighter fighter : missTargets) {
						if(FightTalentCondition.checkCond(src, fighter, cond, condParm)){
							isNode = true;
							condTargets.add(fighter);
						}
					}
				}
			}
			if(isNode){
//				神通技能的目标读取目标表
				List<Fighter> targets = main.chooseTarget(src,skill.getData() ,condTargets);
				List<Fighter> selects = new ArrayList<Fighter>();
				for (Fighter tgt : targets) {
					if(tgt==null || tgt.isDead())  continue;
					selects.add(tgt);
				}
				if(selects.size()>0){
					skill.triggerTarget(main, selects, vo,null);
					res = true;
				}
			}
		} while (false);
		return res;
	}
	
	public int getNode() {
		return node;
	}

	@Override
	public Object getParam() {
		return null;
	}
}
