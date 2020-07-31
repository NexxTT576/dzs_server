/**
 * 
 */
package com.mdy.dzs.game.fight.main.fighttalent;

import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentPropVO;
import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;
import com.mdy.dzs.game.fight.main.FConstants;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.main.fightskill.FightBuff;
import com.mdy.dzs.game.fight.util.FightVOUtil;
import com.mdy.dzs.game.util.Constants;

/**
 * 神通属性加成
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午8:10:44
 */
public class FightTalentProp implements FightTalentEffect{
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
	
	/**计算方式*/
	private int calcType;
	
	/**属性id*/
	private int propId;
	/**属性值*/
	private int propVal;
	
	public FightTalentProp(
			int node,int cond,int condParm,int prob,
			int chooseType,int chooseParam,int calcType,
			int propId,int propVal) {
		this.node = node;
		this.cond = cond;
		this.condParm = condParm;
		this.prob = prob;
		this.chooseType = chooseType;
		this.chooseParam = chooseParam;
		this.calcType = calcType;
		this.propId = propId;
		this.propVal = propVal;
	}
	
	/**
	 * 神通属性效果生效
	 * @param main
	 * @param src    拥有者
	 * @param isTmp  是否临时
	 */
	public boolean effect(FightMain main,Fighter src,FightTalentVO vo ,List<Fighter> defaultTargets,List<Fighter> missTargets){
		boolean res = false;
		do {
			if(src.isBuffEffectExist(FightBuff.EFFECT_封神)) break;
			boolean isNode = false;
			List<FightTalentPropVO> list = vo != null?vo.getProp():null;
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
						 src, chooseType, chooseParam,defaultTargets );
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
					//根据公式计算
					int val = calc(main, tgt ,src);
					if(node != FightTalentNode.永久被动技能){
						if(propId == FConstants.AttInd_Anger){
							if( !tgt.isBuffEffectExist(FightBuff.EFFECT_禁怒) || val<0 ){//禁怒 不可以加怒 但是可以减怒
								tgt.setAngerVal(tgt.getAngerVal()+val);
							}
						}else if(propId == FConstants.AttInd_Life){
								//自身治疗量
								int srcHealV = src.getTmpPropertyByAttInd(FConstants.AttInd_healedV);
								//被治疗武将的被治疗量
								int tgtHealV = tgt.getTmpPropertyByAttInd(FConstants.AttInd_healedV);
								//治疗加成比例
								double srcHealR = src.getTmpPropertyByAttInd(FConstants.AttInd_healedR)*0.0001;
								//被治疗武将的治疗加成比例
								double tgtHealR = tgt.getTmpPropertyByAttInd(FConstants.AttInd_healedR)*0.0001;
								val = (int)Math.round((val+srcHealV+tgtHealV)*(1+srcHealR+tgtHealR));
								if(tgt.isBuffEffectExist(FightBuff.EFFECT_禁疗)){
									val = (int)Math.round(val*Constants.battle_禁疗系数);
								}
								tgt.changeLife(val);
						}else{
							tgt.addTmpPropertyByAttInd(propId, val);
						}
					}else{
						tgt.addPropertyByAttInd(propId, val);
					}
					if(list != null && val != 0) {
						list.add(FightVOUtil.createTalentPropVO(tgt,propId,val));
					}
					res = true;
				}
			}
		} while (false);
		return res;
	}
	
	/**
	 * 计算方式
	 * @param main
	 * @param tgt
	 * @param src
	 * @return
	 */
	private int calc(FightMain main,Fighter tgt,Fighter src){
		int res = 0;
		switch (calcType) {
		case 1:
			res = propVal;
			break;
		case 2:
			res = (int)Math.floor(propVal*0.0001*tgt.getLife());
			break;
		case 3:
			res = (int)Math.floor(propVal*0.0001*tgt.getInitLife());
			break;
		case 4:
			res = (int)Math.floor(propVal*0.0001*tgt.getLife()/tgt.getInitLife());
			break;
		case 5:
			res = (int)Math.floor(propVal*0.0001*src.getTmpAttack());
			break;
		case 6:
			Map<Integer, Fighter> selfSide = (tgt.getSide()==1)?main.getF1():main.getF2();
			int len = getNoDeadSize(selfSide);
			res	= propVal*(6-len);
			break;
		case 7:
			Map<Integer, Fighter> selfSide2 = (tgt.getSide()==1)?main.getF2():main.getF1();
			int len2 = getNoDeadSize(selfSide2);
			res	= propVal*(6-len2);
			break;
		case 8://目标生命上限回复生命值
			res	=(int)Math.floor(propVal*0.0001*tgt.getInitLife());
			break;
		}
		return res;
	}
	
	private int getNoDeadSize(Map<Integer, Fighter> selfSide){
		int i = 0;
		for (Fighter tgt:selfSide.values()) {
			if(tgt!=null && !tgt.isDead()){
				i++;
			}
		}
		return i;
	}
	
	
	
	public int getNode() {
		return node;
	}

	@Override
	public Object getParam() {
		return null;
	}
	
}
