/**
 * 
 */
package com.mdy.dzs.game.fight.main.fighttalent;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.battle.Buff;
import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.main.fightskill.FightBuff;

/**
 * 神通buff加成
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午8:10:44
 */
public class FightTalentBuff implements FightTalentEffect {

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
	
	private Buff buffData;
	
	public FightTalentBuff(int node,int cond,int condParm,int prob,int chooseType,int chooseParam,Buff buffData) {
		this.node = node;
		this.cond = cond;
		this.condParm = condParm;
		this.prob = prob;
		this.chooseType = chooseType;
		this.chooseParam = chooseParam;
		this.buffData = buffData;
	}
	
	/**
	 * 神通属性效果生效
	 * @param main
	 * @param src    拥有者
	 */
	public boolean effect(FightMain main,Fighter src,FightTalentVO vo,List<Fighter> defaultTargets ,List<Fighter> missTargets){
		boolean res = false;
		do {
			if(src.isBuffEffectExist(FightBuff.EFFECT_封神)) break;
			boolean isNode = false;
			if( chooseType == 0 || defaultTargets == null){
				isNode = true;
			}else{
				for (Fighter fighter : defaultTargets) {
					if(FightTalentCondition.checkCond(src, fighter, cond, condParm)){
						if(Math.random()*10000 <= prob){
							isNode = true;
							break;
						}
					}
				}
			}
			if(isNode){
				List<Fighter> targets = main.chooseTarget(
						 src, chooseType, chooseParam ,defaultTargets);
				List<Fighter> selects = new ArrayList<Fighter>();
				for (Fighter tgt : targets) {
					if(tgt==null || tgt.isDead()) continue;
					if(chooseType == 0 ){
						if(!FightTalentCondition.checkCond(src, tgt, cond, condParm)){
							continue;
						}
						if(Math.random()*10000 > prob){
							continue;
						}
					}
					selects.add(tgt);
				}
				if(selects.size() > 0){
					if( vo == null ){
						FightBuff.addBuffs(main, buffData, src, selects,prob, null);
					}else{
						FightBuff.addBuffs(main, buffData, src, selects,prob, vo.getBuff());
					}
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
