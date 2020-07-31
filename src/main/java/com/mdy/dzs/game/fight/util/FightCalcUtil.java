/**
 * 
 */
package com.mdy.dzs.game.fight.util;

import java.util.Random;

import com.mdy.dzs.game.fight.main.FConstants;
import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.main.fightskill.FightSkillEffect;
import com.mdy.dzs.game.fight.main.fightskill.FightSkillState;
import com.mdy.dzs.game.util.Constants;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月26日  下午2:25:07
 */
public class FightCalcUtil {
	
//	/**
//	 * 计算治疗公式
//	 * 治疗值=（攻击力*技能系数1+（装备提升+集魂提升+内功提升+天赋提升+经脉提升+被治疗武将的增加的被治疗量）+技能系数2）*（1+治疗加成比例+被治疗武将的被治疗效果加成比例）*（1+暴击修正）
//	 * 未触发暴击时，暴击修正=1，触发暴击时，暴击修正=max(1.25,1.25+（攻方暴击倍率加成-对方暴击倍率减免）/2)
//	 * @param src
//	 * @param tar
//	 * @param parms
//	 */
//	public static int calcHealVal(Fighter src,Fighter tgt,Integer parm1,Integer parm2,boolean crit){
//		
//		//技能系数1
//		double skillParm1 = parm1*0.0001;
//		double skillParm2 = parm2;
//		//自身治疗量
//		int srcHealV = src.getTmpPropertyByAttInd(FConstants.AttInd_healedV);
//		//被治疗武将的被治疗量
//		int tgtHealV = tgt.getTmpPropertyByAttInd(FConstants.AttInd_healedV);
//		//治疗加成比例
//		double srcHealR = src.getTmpPropertyByAttInd(FConstants.AttInd_healedR)*0.0001;
//		//被治疗武将的治疗加成比例
//		double tgtHealR = tgt.getTmpPropertyByAttInd(FConstants.AttInd_healedR)*0.0001;
//		double critVal = 1.0f;
//		if(crit){
//			double srcCrit = src.getTmpPropertyByAttInd(FConstants.AttInd_Crit)*0.0001;
//			double tgtCritD=tgt.getTmpPropertyByAttInd(FConstants.AttInd_Resist)*0.0001;
//			//触发暴击时，暴击修正=max(1.25,1.25+（攻方暴击倍率加成-对方暴击倍率减免）/2)
//			critVal = Math.max(1.25f, 1.25f+(srcCrit-tgtCritD)*0.5);
//		}
//		int healVal = (int)Math.round((src.getTmpAttack()*skillParm1+
//				srcHealV+tgtHealV+skillParm2)*(1+srcHealR+tgtHealR)*critVal);
//		return healVal;
//	}
	
	/**
	 * 加血暴击修正=max(1.25,1.25+攻方暴击倍率加成/4)
	 * 加血效果乘以三围修正，三围修正=（释放加血技能侠客的智力值+加血常量1）/加血常量2.
	 * 默认加血常量1=20.加血常量2=100
	 * @param src
	 * @param tgt
	 * @param parm1
	 * @param parm2
	 * @param crit
	 * @return
	 */
	public static int calcHealVal(Fighter src,Fighter tgt,Integer parm1,Integer parm2,boolean crit){
	
		//技能系数1
		double skillParm1 = parm1*0.0001;
		double skillParm2 = parm2;
		//自身治疗量
		int srcHealV = src.getTmpPropertyByAttInd(FConstants.AttInd_healV);
		//被治疗武将的被治疗量
		int tgtHealV = tgt.getTmpPropertyByAttInd(FConstants.AttInd_healedV);
		//治疗加成比例
		double srcHealR = src.getTmpPropertyByAttInd(FConstants.AttInd_healR)*0.0001;
		//被治疗武将的治疗加成比例
		double tgtHealR = tgt.getTmpPropertyByAttInd(FConstants.AttInd_healedR)*0.0001;
		double critVal = 1.0f;
		if(crit){
			double srcCrit = src.getTmpPropertyByAttInd(FConstants.AttInd_Crit)*0.0001;
			//触发暴击时，加血暴击修正=max(1.25,1.25+攻方暴击倍率加成/4)
			critVal = Math.max(1.25f, 1.25f+srcCrit*0.25);
		}
		double healVal = (src.getTmpAttack()*skillParm1+
				srcHealV+tgtHealV+skillParm2)*(1+srcHealR+tgtHealR)*critVal;
		healVal = healVal*(src.getTmpPropertyByAttInd(FConstants.AttInd_Smart)+20)*0.01;
		return (int)Math.round(healVal);
	}
	
	/**
	 * 命中计算
	 * 己方命中-对方闪避+命中常量＞rand（0,1）
	 * @param target
	 * @param effect 
	 * @return
	 */
	public static boolean calcNoMiss(Fighter src,Fighter target, FightSkillEffect effect){
		if(effect.getType() == FightSkillEffect.TYPE_加血){
			return true;
		}
		double missVal = (
				src.getTmpPropertyByAttInd(FConstants.AttInd_Hit) * 0.0001
				-target.getTmpPropertyByAttInd(FConstants.AttInd_Miss) * 0.0001
				+Constants.battleCalcHit);
		boolean noMiss = (missVal>Math.random())?true:false;
		if(!noMiss) target.setState(FightSkillState.Miss);
		return noMiss;
	}
	
	/**
	 * 暴击判定：当攻方暴击-对方抗暴+暴击常量③≥rand(0,1)时，触发暴击
	 * @param target
	 * @return
	 */
	public static boolean calcCrit(Fighter src,Fighter tgt){
		double srcCrit = src.getTmpPropertyByAttInd(FConstants.AttInd_Crit)*0.0001;
		double tgtCrit = tgt.getTmpPropertyByAttInd(FConstants.AttInd_Resist)*0.0001;
		return (srcCrit-tgtCrit+Constants.battleCalcCrit)>=Math.random();
	}
	
	/**
	 * 格挡判定：当被攻方格挡-攻方破击+格挡常量④≥rand(0,1)时，触发格挡
	 * @param src
	 * @param tgt
	 * @param effect 
	 * @return
	 */
	public static boolean calcBlock(Fighter src,Fighter tgt, FightSkillEffect effect){
		if(effect.getType() == FightSkillEffect.TYPE_加血){
			return false;
		}
		double srcBlockD = src.getTmpPropertyByAttInd(FConstants.AttInd_Destory)*0.0001;
		double tgtBlock = tgt.getTmpPropertyByAttInd(FConstants.AttInd_Block)*0.0001;
		return (tgtBlock-srcBlockD+Constants.battleCalcBlock)>=Math.random();
	}
	
	/**
	 * 计算物理基础攻击伤害
	 * 基础物理伤害=攻击数值-物防数值+物防数值*e^（1-e^(攻击数值/物防数值））
	 * @param src
	 * @param tgt
	 * @return
	 */
	private static int calcPhysicBaseDamage(Fighter src,Fighter tgt){
		double attack = src.getTmpAttack()*1.0;
		double defense= tgt.getTmpDefen()*1.0;
		int baseDamage = (int)Math.round((attack-defense)+defense*Math.exp(1-Math.exp(attack/defense)));
		return baseDamage;
	}
	
	/**
	 * 最终物理伤害
	 * =（基础物理伤害+（物理伤害加成数值-对方物理伤害减免数值））*（1+物理伤害加成比例-对方物理伤害减免比例）
	 * *技能伤害修正*怒气修正*暴击修正*格挡修正*三围修正*浮动修正
	 * @param src
	 * @param tgt
	 * @return
	 */
	public static int calcPhysicFinalDamage(Fighter src,Fighter tgt,
			boolean isCrit,boolean isBlock){
		
		
		//当攻方怒气≤4时，怒气修正=1
		//当攻方怒气＞4时，怒气修正=1+(攻方怒气-4)/怒气伤害修正常量②
		double angerVal = 1.0f;
		if(src.getAngerVal()>4){
			
			angerVal = 1.0+((src.getAngerVal()-4)*1.0)/(Constants.battleCalcAnger*1.0);
		}
		
		//暴击判定：当攻方暴击-对方抗暴+暴击常量③≥rand(0,1)时，触发暴击
		//未触发暴击时，暴击修正=1，触发暴击时，暴击修正=max(1.5,1.5+攻方暴击倍率加成-对方暴击倍率减免)
		//其中：暴击倍率加成/减免=装备提升+集魂提升+内功提升+天赋提升+经脉提升+技能临时提升+buff临时提升+其他系统永久性提升
		//暴击/抗暴=面板数值（4.56%格式显示）=装备提升+集魂提升+内功提升+天赋提升+经脉提升+技能临时提升（含天赋技能）
		double critVal = 1.0f;
		if(isCrit){	
			double srcFCritR = src.getTmpPropertyByAttInd(FConstants.AttInd_FCritR)*0.0001;
			double tgtFCritDR = tgt.getTmpPropertyByAttInd(FConstants.AttInd_FCritDR)*0.0001;
			critVal = Math.max(1.5f,1.5f+srcFCritR-tgtFCritDR);
			src.setState(FightSkillState.Crit);
		}
		
		//格挡判定：当被攻方格挡-攻方破击+格挡常量④≥rand(0,1)时，触发格挡
		//未触发格挡时，格挡修正=1，触发格挡时，格挡修正=0.5.
		//其中：格挡/破击=面板数值（4.56%格式显示）=装备提升+集魂提升+内功提升+天赋提升+经脉提升+技能临时提升+buff临时提升+其他系统永久性提升
		//注意：触发格挡时，被攻方必触发反击。（反击伤害最下面表格阐述）
		double blockVal = 1.0f;
		if(isBlock){	
				src.setState(FightSkillState.Block);
				blockVal = 0.5;
			}
		//⑥三围修正
		//修正值=（攻击方武力值+武力常量1⑤）/（目标武力值+武力常量2⑥）
		int srcForce = src.getTmpPropertyByAttInd(FConstants.AttInd_Force);
		int tgtForce = tgt.getTmpPropertyByAttInd(FConstants.AttInd_Force);
		double leadVal = ((srcForce+Constants.battleCalcForce1)*1.0)
				/((tgtForce+Constants.battleCalcForce2)*1.0);

		//TODO 技能伤害修正
		double skillVal = 1.0f;
		//TODO 幸运值
		double luckVal = 1.0f;
		
		int baseDamage = calcPhysicBaseDamage(src, tgt);
		//物理伤害加成数值
		int srcPhyDmg = src.getTmpPropertyByAttInd(FConstants.AttInd_Physic);
		//对方物理伤害减免数值
		int tgtPhyDmgD = tgt.getTmpPropertyByAttInd(FConstants.AttInd_PhysicD);
		//物理伤害加成比例
		double srcPhyDmgR = src.getTmpPropertyByAttInd(FConstants.AttInd_PhysicR)*0.0001;
		//对方物理伤害减免比例
		double tgtPhyDmgDR = tgt.getTmpPropertyByAttInd(FConstants.AttInd_PhysicDR)*0.0001;
//		//最终伤害加成
//		int srcFHarmV = src.getTmpPropertyByAttInd(FConstants.AttInd_FHarmV);
//		//最终伤害减免
//		int tgtFHarmDV = tgt.getTmpPropertyByAttInd(FConstants.AttInd_FHarmDV);
		
		int lastDamage = (int)Math.round(
				(baseDamage+srcPhyDmg-tgtPhyDmgD)
				*(1+srcPhyDmgR-tgtPhyDmgDR)
				*angerVal*critVal*blockVal*leadVal*luckVal*skillVal);
		return lastDamage;
	}
	
	
	/**
	 * 计算魔法基础攻击伤害
	 * 基础法术伤害=攻击数值-法防数值+法防数值*e^（1-e^(攻击数值/法防数值））
	 * @param src
	 * @param tgt
	 * @return
	 */
	private static int calcMagicBaseDamage(Fighter src,Fighter tgt){
		double attack = src.getTmpAttack() * 1.0;
		double defense= tgt.getTmpDefenM() * 1.0;
		int baseDamage = (int)Math.round((attack-defense)+defense*Math.exp(1-Math.exp(attack/defense)));
		return baseDamage;
	}
	
	/**
	 * 最终法术伤害
	 * =（基础法术伤害+（法术伤害加成数值-对方法术伤害减免数值））*（1+法术伤害加成比例-对方法术伤害减免比例）
	 * *技能伤害修正*怒气修正*暴击修正*格挡修正*三围修正*浮动修正
	 * @param src
	 * @param tgt
	 * @return
	 */
	public static int calcMagicFinalDamage(Fighter src,Fighter tgt,
			boolean isCrit,boolean isBlock){
		//当攻方怒气≤4时，怒气修正=1
		//当攻方怒气＞4时，怒气修正=1+(攻方怒气-4)/怒气伤害修正常量②
		double angerVal = 1.0f;
		if(src.getAngerVal()>4){
			angerVal = 1.0+((src.getAngerVal()-4)*1.0)/(Constants.battleCalcAnger*1.0);
		}
		
		//暴击判定：当攻方暴击-对方抗暴+暴击常量③≥rand(0,1)时，触发暴击
		//未触发暴击时，暴击修正=1，触发暴击时，暴击修正=max(1.5,1.5+攻方暴击倍率加成-对方暴击倍率减免)
		//其中：暴击倍率加成/减免=装备提升+集魂提升+内功提升+天赋提升+经脉提升+技能临时提升+buff临时提升+其他系统永久性提升
		//暴击/抗暴=面板数值（4.56%格式显示）=装备提升+集魂提升+内功提升+天赋提升+经脉提升+技能临时提升（含天赋技能）
		double critVal = 1.0f;
		if(isCrit){	
			double srcFCritR = src.getTmpPropertyByAttInd(FConstants.AttInd_FCritR)*0.0001;
			double tgtFCritDR = tgt.getTmpPropertyByAttInd(FConstants.AttInd_FCritDR)*0.0001;
			critVal = Math.max(1.5f,1.5f+srcFCritR-tgtFCritDR);
			src.setState(FightSkillState.Crit);
		}
		
		//格挡判定：当被攻方格挡-攻方破击+格挡常量④≥rand(0,1)时，触发格挡
		//未触发格挡时，格挡修正=1，触发格挡时，格挡修正=0.5.
		//其中：格挡/破击=面板数值（4.56%格式显示）=装备提升+集魂提升+内功提升+天赋提升+经脉提升+技能临时提升+buff临时提升+其他系统永久性提升
		//注意：触发格挡时，被攻方必触发反击。（反击伤害最下面表格阐述）
		double blockVal = 1.0f;
		if(isBlock){	
				src.setState(FightSkillState.Block);
				blockVal = 0.5;
			}
		//⑥三围修正
		//修正值=（攻击方智力值+智力常量1⑧）/（目标智力值+智力常量2⑨）
		int srcForce = src.getTmpPropertyByAttInd(FConstants.AttInd_Smart);
		int tgtForce = tgt.getTmpPropertyByAttInd(FConstants.AttInd_Smart);
		double leadVal = ((srcForce+Constants.battleCalcSmart1)*1.0)
				/((tgtForce+Constants.battleCalcSmart2)*1.0);

		//TODO 技能伤害修正
		double skillVal = 1.0f;
		//TODO 幸运值
		double luckVal = 1.0f;
		
		int baseDamage = calcMagicBaseDamage(src, tgt);
		//法术伤害加成数值
		int srcMagicDmg = src.getTmpPropertyByAttInd(FConstants.AttInd_Magic);
		//对方法术伤害减免数值
		int tgtMagicDmgD = tgt.getTmpPropertyByAttInd(FConstants.AttInd_MagicD);
		//法术伤害加成比例
		double srcMagicDmgR = src.getTmpPropertyByAttInd(FConstants.AttInd_MagicR)*0.0001;
		//对方法术伤害减免比例
		double tgtMagicDmgDR = tgt.getTmpPropertyByAttInd(FConstants.AttInd_MagicDR)*0.0001;

		//int srcFHarmV = src.getTmpPropertyByAttInd(FConstants.AttInd_FHarmV);
		//int tgtFHarmDV = tgt.getTmpPropertyByAttInd(FConstants.AttInd_FHarmDV);
		
		int lastDamage = (int)Math.round(
				(baseDamage+srcMagicDmg-tgtMagicDmgD)
				*(1+srcMagicDmgR-tgtMagicDmgDR)
				*angerVal*critVal*blockVal*leadVal*luckVal*skillVal);// + srcFHarmV-tgtFHarmDV;
		return lastDamage;
	}
	
	
	private static Random rnd = new Random();
	public static double random(double a,double b){
		if(a==b) return a;
		return rnd.nextDouble()*(b-a)+a;
	}
	
	public static int random(int a,int b){
		if(a==b) return a;
		return rnd.nextInt(b+1-a)+a;
	}
	
}
