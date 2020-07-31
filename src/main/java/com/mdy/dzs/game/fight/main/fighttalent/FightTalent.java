/**
 * 
 */
package com.mdy.dzs.game.fight.main.fighttalent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.battle.Buff;
import com.mdy.dzs.data.domain.shentong.Talent;
import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.util.FightVOUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 神通
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午7:02:50
 */
public class FightTalent {

	
	private int id;
	private Talent data;
	private Fighter src;
	

	private Map<Integer, List<FightTalentEffect>> nodeEffects;
	
	public FightTalent(Fighter src,Talent data){
		this.src = src;
		this.setId(data.getId());
		this.data = data;
		nodeEffects = new LinkedHashMap<Integer, List<FightTalentEffect>>();
		initPropEffect();
		initBuffEffect();
		initSkillEffect();
	}

	/**
	 * 添加一个神通效果
	 * @param node
	 * @param effect
	 */
	private void addTalentEffect(int node,FightTalentEffect effect){
		List<FightTalentEffect> effects = nodeEffects.get(node);
		if(effects == null){
			effects = new ArrayList<FightTalentEffect>();
			nodeEffects.put(node, effects);
		}
		effects.add(effect);
	}
	
	/**
	 * 初始化属性效果
	 */
	private void initPropEffect() {
		if(data.getAttCond().size()>0){
			List<Integer> nodeAry = data.getNode();
			List<Integer> condAry = data.getAttCond();
			List<Integer> condParmAry = data.getAttPara();
			List<Integer> chooseTypeAry = data.getAttTarget();
			List<Integer> chooseParmAry = data.getAttSide();
			List<Integer> propIdAry = data.getAttId();
			List<Integer> propValAry = data.getAttCoff();
			List<Integer> calcTypeAry = data.getAttCalc();
			List<Integer> probAry = data.getAttProb();
			for (int i = 0; i < nodeAry.size(); i++) {
				addTalentEffect(nodeAry.get(i),
						new FightTalentProp(nodeAry.get(i), 
						condAry.get(i), (condParmAry.size()<=i)?0:condParmAry.get(i), probAry.get(i),
						chooseTypeAry.get(i), chooseParmAry.get(i), calcTypeAry.get(i),
						propIdAry.get(i), propValAry.get(i)));
			}
		}
	}
	
	/**
	 * 初始化buff效果
	 */
	private void initBuffEffect() {
		if(data.getAttCondB().size()>0){
			List<Integer> nodeAry = data.getNodeB();
			List<Integer> condAry = data.getAttCondB();
			List<Integer> condParmAry = data.getAttParaB();
			List<Integer> chooseTypeAry = data.getAttTargetB();
			List<Integer> chooseParmAry = data.getAttSideB();
			List<Integer> probAry = data.getAttProbB();
			List<Integer> buffIdAry = data.getAttBuff();
			for (int i = 0; i < nodeAry.size(); i++) {
				Buff buffData = FightMain.dataManager.getValueByKey(Buff.class,buffIdAry.get(i));
				if(buffData == null){
					//TODO 
					continue;
				}
				addTalentEffect(nodeAry.get(i),
						new FightTalentBuff(nodeAry.get(i), 
						condAry.get(i), (condParmAry.size()<=i)?0:condParmAry.get(i), probAry.get(i),
						chooseTypeAry.get(i), chooseParmAry.get(i), buffData));
			}
		}
	}
	
	/**
	 * 初始化神通技能效果
	 * @throws BizException 
	 */
	private void initSkillEffect(){
		if(data.getAttCondS().size()>0){
			List<Integer> nodeAry = data.getNodeS();
			List<Integer> condAry = data.getAttCondS();
			List<Integer> condParmAry = data.getAttParaS();
			List<Integer> chooseTypeAry = data.getAttTargetS();
			List<Integer> chooseParmAry = data.getAttSideS();
			List<Integer> probAry = data.getAttProbS();
			List<Integer> skillIdAry = data.getAttSkill();
			for (int i = 0; i < nodeAry.size(); i++) {
				addTalentEffect(nodeAry.get(i),
						new FightTalentSkill(src,nodeAry.get(i), 
						condAry.get(i), (condParmAry.size()<=i)?0:condParmAry.get(i), probAry.get(i),
						chooseTypeAry.get(i), chooseParmAry.get(i), skillIdAry.get(i)));
			}
		}
	}
	
	/**
	 * 根据节点触发神通效果
	 * @param node
	 * @param main
	 * @param skillVO
	 */
	public boolean triggerEffect(int node,FightMain main,List<FightTalentVO> listVO ,List<Fighter> defaultTargets ,List<Fighter> missTargets){
		List<FightTalentEffect> effects = nodeEffects.get(node);
		if(effects == null || effects.size() == 0) return false;
		boolean isEffect = false;
		FightTalentVO vo = listVO != null?FightVOUtil.createTalentVO(this):null;
		for (FightTalentEffect effect : effects) {
			isEffect |= effect.effect(main, src, vo , defaultTargets ,missTargets);
		}
		if(vo != null && !vo.isNoEffect()){
			listVO.add(vo);
		}
		return isEffect;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fighter getSrc() {
		return src;
	}
}
