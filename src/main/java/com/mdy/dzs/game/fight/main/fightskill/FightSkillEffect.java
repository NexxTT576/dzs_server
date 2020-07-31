package com.mdy.dzs.game.fight.main.fightskill;

import java.util.List;

import com.mdy.dzs.data.domain.battle.BattleSkill;
import com.mdy.dzs.game.fight.domain.fightskill.FightSkillVO;
import com.mdy.dzs.game.fight.main.FConstants;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.util.FightCalcUtil;
import com.mdy.dzs.game.fight.util.FightVOUtil;
import com.mdy.dzs.game.util.Constants;

/**
 * 技能效果
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月26日  上午11:39:04
 */
public class FightSkillEffect {
	public static final int TYPE_指定目标的攻击 = 1;
	public static final int TYPE_多次伤害 		= 2;
	public static final int TYPE_吸血攻击		= 3;
	public static final int TYPE_减怒			= 4;
	public static final int TYPE_加怒			= 5;
	public static final int TYPE_加血			= 6;
	public static final int TYPE_锁链伤害		= 7;
	public static final int TYPE_驱散			= 8;

	public static final int STYLE_物理 			= 0;
	public static final int STYLE_法术 			= 1;
	
	public static final int 无物理法术免疫 		= 0;
	public static final int 物理免疫 				= 1;
	public static final int 法术免疫 				= 2;
	
	/**伤害效果类型*/
	private int type;
	/**技能效果类型*/
	private int style;
	/**目标类型*/
	private int chooseType;
	/**目标参数*/
	private int chooseParm;
	/**技能效果参数*/
	private List<Integer> parms;
	/**伤害段数*/
	private int dCount;
	
	
	public FightSkillEffect(BattleSkill data){
		type = data.getDType1();
		List<Integer> vals = data.getDAttr1();
		chooseType = vals.get(1);
		chooseParm = vals.get(0);
		parms = vals;
		dCount = data.getdCount();
		style = data.getStyle();
	}

	/**
	 * 给定目标技能生效
	 * @param fighter
	 * @param target
	 * @param damageVal
	 * @param crit
	 * @return
	 */
	public FightSkillEffectResult effectTarget(
			Fighter fighter,Fighter target,
			int baseDamage,boolean crit,double reduce){
		FightSkillEffectResult result = new FightSkillEffectResult();
		//==============================最后计算 最终伤害加成 最终伤害减免========================================//
		//最终伤害加成
		int srcFHarmV = fighter.getTmpPropertyByAttInd(FConstants.AttInd_FHarmV);
		//最终伤害减免
		int tgtFHarmDV = target.getTmpPropertyByAttInd(FConstants.AttInd_FHarmDV);
				
		//result.cnt = 1;()
		//多段伤害  每种伤害都有
		result.cnt = dCount;
		if(baseDamage<0) baseDamage = 0;
		
		switch (type) {
		case TYPE_指定目标的攻击:
		case TYPE_减怒:
		case TYPE_加怒:
			//目标方（1：己方；2：对方）	目标类型	伤害修正下限	伤害修正上限	概率
			
			result.damageVal = (int)Math.floor(random(parms.get(2),parms.get(3))*0.0001*baseDamage);
			break;
		case TYPE_多次伤害:
			//目标方	目标类型	伤害次数	单个目标总伤害修正下限	单个目标总伤害修正上限
			result.cnt = parms.get(2);
			result.damageVal=(int)Math.round(random(parms.get(3),parms.get(4))*0.0001*baseDamage)*result.cnt;
			break;
		case TYPE_吸血攻击:
			//目标方	目标类型	伤害修正下限	伤害修正上限	吸血占伤害比例
			result.damageVal = (int)Math.floor(random(parms.get(2),parms.get(3))*0.0001*baseDamage);
			int val_吸血 = (int)Math.floor(parms.get(4)*0.0001*result.damageVal);
			if(fighter.isBuffEffectExist(FightBuff.EFFECT_禁疗)){
				val_吸血 = (int)Math.round(val_吸血*Constants.battle_禁疗系数);
			}
			result.setSelfHealVal(val_吸血);
			break;
		case TYPE_加血:
			int val_加血 = FightCalcUtil.calcHealVal(fighter, target, parms.get(2), parms.get(3), crit);
			if(target.isBuffEffectExist(FightBuff.EFFECT_禁疗)){
				val_加血 = (int)Math.round(val_加血*Constants.battle_禁疗系数);
			}
			result.healVal = val_加血; 
			break;
		case TYPE_锁链伤害:
			//TODO
			break;
		case TYPE_驱散:
			//TODO 
			break;
		}
		
		double rom = 0.95+Math.random()*0.1;
		if(result.healVal>0){
			//治疗大于0 就用治疗乘系数 
			result.healVal = (int)Math.floor(result.healVal*rom);
		}else if(result.damageVal>=0){
			//治疗小于0 就用伤害乘以系数
			result.damageVal = (int)Math.floor(result.damageVal*rom*reduce)+ srcFHarmV - tgtFHarmDV;
		}
		if(target.isBuffEffectExist(FightBuff.EFFECT_无敌)){
			result.damageVal = 0;
		}
		//最小伤害为1
		if(result.damageVal<=0 && result.healVal==0){
			result.damageVal = 1;
		}
		if( type == TYPE_加血 ){//治疗
			result.damageVal = 0;
		}
		return result;
		
	}
	
	/**
	 * 自选目标技能效果生效
	 * @param main
	 * @param fighter
	 * @param vo
	 */
	public void effectOptionalTarget(
			FightMain main,Fighter fighter,FightSkillVO vo,List<Fighter> defaultTargets ){
		List<Fighter> aTargets = null;
		switch (type) {
			case TYPE_减怒:{
				aTargets =main.chooseTarget( fighter,parms.get(4), 2 ,defaultTargets);
				double prob = parms.get(6)*0.0001;
				int val = -parms.get(5);
				for (Fighter tgt : aTargets) {
					if(tgt==null || tgt.isDead()) continue;
					if(Math.random()>prob){
						continue;
					}
					tgt.setAngerVal(tgt.getAngerVal()+val);
					FightVOUtil.fightSkillAddAnger(vo,tgt,val);
				}
			}break;
			case TYPE_加怒:{
				aTargets =main.chooseTarget( fighter,parms.get(4), 1 ,defaultTargets);
				double prob = parms.get(6)*0.0001;
				int val = parms.get(5);
				for (Fighter tgt : aTargets) {
					if(Math.random()>prob){
						continue;
					}
					if(!tgt.isBuffEffectExist(FightBuff.EFFECT_禁怒)){
						tgt.setAngerVal(tgt.getAngerVal()+val);
						FightVOUtil.fightSkillAddAnger(vo,tgt,val);
					}
				}
			}break;
		}
	}
	
	private int random(int a,int b){
		return FightCalcUtil.random(a, b);
	}

	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getChooseType() {
		return chooseType;
	}


	public void setChooseType(int chooseType) {
		this.chooseType = chooseType;
	}


	public int getChooseParm() {
		return chooseParm;
	}


	public void setChooseParm(int chooseParm) {
		this.chooseParm = chooseParm;
	}


	public List<Integer> getParms() {
		return parms;
	}


	public void setParms(List<Integer> parms) {
		this.parms = parms;
	}




	public int getStyle() {
		return style;
	}




	public void setStyle(int style) {
		this.style = style;
	}
	
	
}
