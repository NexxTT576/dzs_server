/**
 * 
 */
package com.mdy.dzs.game.fight.main.fighttalent;

import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.main.fightskill.FightBuff;
import com.mdy.dzs.game.fight.main.fightskill.FightSkillState;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午7:15:23
 */
public class FightTalentCondition {
	public static final int 无条件 				= 1;
	public static final int 自身生命高于另一方 		= 2;
	public static final int 自身生命低于另一方 		= 3;
	public static final int 生命低于上限比例 		= 4;
	public static final int 目标怒气低于参数值 		= 5;
	public static final int 目标为男性 			= 6;
	public static final int 目标为女性 			= 7;
	public static final int 攻击方为男性 			= 8;
	public static final int 攻击方为女性 			= 9;
	public static final int 攻击高于另一方 		= 10;
	public static final int 攻击低于另一方 		= 11;
	public static final int 目标处于中毒状态 		= 12;
	public static final int 目标处于封印状态 		= 13;
	public static final int 目标处于麻痹状态 		= 14;
	public static final int 目标处于眩晕状态 		= 15;
	public static final int 目标处于冰冻状态 		= 16;
	public static final int 目标处于灼烧状态 		= 17;
	public static final int 目标处于无敌状态 		= 18;
	public static final int 目标处于魅惑状态 		= 19;
	public static final int 杀死了目标 			= 20;
	public static final int 触发了暴击 			= 21;
	public static final int 闪避了另一方的攻击 		= 22;
	public static final int 我死了 				= 23;
	public static final int 目标生命低于上限比例	= 24;
	public static final int 格挡反击				= 25;
	public static final int 目标处于封神状态				= 28;
	
	/**
	 * 查看条件是否成立
	 * @param src
	 * @param target
	 * @param cond
	 * @param param
	 * @return
	 */
	public static boolean checkCond(Fighter src,Fighter target,int cond,int param){
		if(cond==0)	return true;
		if(cond==1)	return true;
		if(cond==2){
			return src.getLife()>target.getLife();
		}else if(cond==3){
			return src.getLife()<target.getLife();
		}else if(cond==4){
			return ((src.getLife()*1.00)/(src.getInitLife()*1.00)<(param*0.0001));
		}else if(cond==5){
			return (target.getAngerVal()<param);
		}else if(cond==6){
			return (target.getData().getSex()==1);
		}else if(cond==7){
			return (target.getData().getSex()==2);
		}else if(cond==8){
			return (src.getData().getSex()==1);
		}else if(cond==9){
			return (src.getData().getSex()==2);
		}else if(cond==10){
			return (src.getTmpAttack()>target.getTmpAttack());
		}else if(cond==11){
			return (src.getTmpAttack()<target.getTmpAttack());
		}else if(cond==12){
			return target.isBuffEffectExist(FightBuff.EFFECT_中毒掉血);
		}else if(cond==13){
			return target.isBuffEffectExist(FightBuff.EFFECT_封技);
		}else if(cond==14){
			return  target.isBuffEffectExist(FightBuff.EFFECT_禁疗);
		}else if(cond==15){
			return target.isBuffEffectExist(FightBuff.EFFECT_眩晕);	
		}else if(cond==16){
			return target.isBuffEffectExist(FightBuff.EFFECT_冰冻);	
		}else if(cond==17){
			return target.isBuffEffectExist(FightBuff.EFFECT_火烧掉血);		
		}else if(cond==18){
			return target.isBuffEffectExist(FightBuff.EFFECT_无敌);	
		}else if(cond==19){
			return  target.isBuffEffectExist(FightBuff.EFFECT_禁怒); 	
		}else if(cond==20){
			return target.isDead();			
		}else if(cond==21){
			return src.getState(FightSkillState.Crit) != 0;			
		}else if(cond==22){
			return target.getState(FightSkillState.Miss) != 0;				
		}else if(cond==23){
			return (src.isDead());		
		}else if(cond == 24){
			return ((target.getLife()*1.00)/(target.getInitLife()*1.00)<(param*0.0001));
		}else if(cond == 28){
			return  target.isBuffEffectExist(FightBuff.EFFECT_封神); 
		};
		return false;
	};
	

}
