package com.mdy.dzs.game.fight.util;

import java.util.Map;

import com.mdy.dzs.game.fight.domain.FightInitCardVO;
import com.mdy.dzs.game.fight.domain.FightInitVO;
import com.mdy.dzs.game.fight.domain.fightskill.FightBuffVO;
import com.mdy.dzs.game.fight.domain.fightskill.FightSkillAngerVO;
import com.mdy.dzs.game.fight.domain.fightskill.FightSkillBuffVO;
import com.mdy.dzs.game.fight.domain.fightskill.FightSkillProcessVO;
import com.mdy.dzs.game.fight.domain.fightskill.FightSkillVO;
import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentPropVO;
import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;
import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.main.fightskill.FightBuff;
import com.mdy.dzs.game.fight.main.fightskill.FightSkill;
import com.mdy.dzs.game.fight.main.fightskill.FightSkillEffectResult;
import com.mdy.dzs.game.fight.main.fightskill.FightSkillState;
import com.mdy.dzs.game.fight.main.fighttalent.FightTalent;
import com.mdy.dzs.game.fight.main.fighttalent.FightTalentRescueResult;

public class FightVOUtil {

	
	/**
	 * 创建buff/debuff效果
	 * @param round
	 * @param fight
	 * @param buff
	 * @return
	 */
	public static FightBuffVO createBuffInfo(
			int round,
			Fighter fight,
			FightBuff buff){
		FightBuffVO vo = new FightBuffVO();
		vo.setN(round);
		vo.setS(fight.getSide());
		vo.setP(fight.getPos());
		vo.setB(buff.getId());
		vo.setK(buff.getKeys());
		vo.setV(buff.getVals());
		vo.setEff(buff.getRound()>0?1:2);
		vo.setL(fight.getLife());
		return vo;
	}
	
	/**
	 * 获取初始化信息
	 * @return
	 */
	public static  FightInitVO createInitVO(
			Map<Integer, Fighter> f1,
			Map<Integer, Fighter> f2){
		//init FightInitVO
		FightInitVO initVO = new FightInitVO();
		
		for (Fighter fighter : f1.values()) {
			FightInitCardVO initCardVO = new FightInitCardVO();
			initCardVO.setId(fighter.getId());
			initCardVO.setLife(fighter.getBase()[0]);
			initCardVO.setInitLife(fighter.getInitLife());
			initCardVO.setPos(fighter.getPos());
			initCardVO.setStar(fighter.getStar());
			initCardVO.setAnger(fighter.getAngerVal());
			initCardVO.setCls(fighter.getCls());
			initCardVO.setScale(fighter.getScale());
			initVO.getF1().add(initCardVO);
		}
		
		for (Fighter fighter : f2.values()) {
			FightInitCardVO initCardVO = new FightInitCardVO();
			initCardVO.setId(fighter.getId());
			initCardVO.setLife(fighter.getBase()[0]);
			initCardVO.setInitLife(fighter.getInitLife());
			initCardVO.setPos(fighter.getPos());
			initCardVO.setStar(fighter.getStar());
			initCardVO.setAnger(fighter.getAngerVal());
			initCardVO.setScale(fighter.getScale());
			initCardVO.setCls(fighter.getCls());
			initVO.getF2().add(initCardVO);
		}
		return initVO;
	}

	/**
	 * 技能添加怒气回复/减少消息
	 * @param vo
	 * @param tgt
	 * @param val
	 */
	public static void fightSkillAddAnger(FightSkillVO vo,Fighter tgt, int val) {
		FightSkillAngerVO angerVO = new FightSkillAngerVO();
		angerVO.setS(tgt.getSide());
		angerVO.setP(tgt.getPos());
		angerVO.setA(val);
		vo.addAngerVO(angerVO);
	}

	/**
	 * 创建skillvo
	 * @param src
	 * @param skill
	 * @param round
	 * @return
	 */
	public static FightSkillVO createSkillVO(
			Fighter src,FightSkill skill,int round){
		FightSkillVO vo = new FightSkillVO();
		vo.setT(3);
		vo.setN(round);
		vo.setS(src.getSide());
		vo.setP(src.getPos());
		vo.setUse(skill.getType());
		vo.setEff(skill.getEffect().getType());
		vo.setSkill(skill.getId());
		return vo;
	}
	
	/**
	 * 创建未使用技能skillvo
	 * @param src
	 * @param skill
	 * @param round
	 * @return
	 */
	public static FightSkillVO createNoSkillVO(
			Fighter src,int round){
		FightSkillVO vo = new FightSkillVO();
		vo.setT(3);
		vo.setN(round);
		vo.setS(src.getSide());
		vo.setP(src.getPos());
		vo.setUse(1);
		vo.setL(src.getLife());
		vo.setA(src.getAngerVal());
		return vo;
	}
	
	/**
	 * 创建技能miss过程
	 * @param target
	 * @return
	 */
	public static FightSkillProcessVO createSkillMissProcessVO(Fighter target ,Integer sAnger) {
		FightSkillProcessVO vo = new FightSkillProcessVO();
		vo.setS(target.getSide());
		vo.setP(target.getPos());
		vo.getSt().add(FightSkillState.Miss);
		vo.setL(target.getLife());
		vo.setA(target.getAngerVal());
		vo.setD(0);
		vo.setSa(sAnger);
		return vo;
	}
	public static FightSkillProcessVO createSkillProcessVO(
			Fighter target,FightSkillEffectResult result,
			boolean isCrit,boolean isBlock,boolean isHeal ,Integer srcAnger ,Integer im
			) {
		FightSkillProcessVO vo = new FightSkillProcessVO();
		vo.setS(target.getSide());
		vo.setP(target.getPos());
		if(isCrit)vo.getSt().add(FightSkillState.Crit);
		if(isBlock)vo.getSt().add(FightSkillState.Block);
		if(isHeal)vo.getSt().add(FightSkillState.Heal);
		vo.setCnt(result.getCnt());
		vo.setD(result.getDamageVal());
		vo.setH(result.getHealVal());
		if(result.getSelfHealVal() != 0 ){
			vo.setH(result.getSelfHealVal());
		}
		vo.setA(target.getAngerVal());
		vo.setL(target.getLife());
		vo.setSa(srcAnger);
		vo.setIm(im);//物理法术免疫
		return vo;
	}

	/**
	 * 创建技能buff vo
	 * @param tgt
	 * @param addBuff
	 * @return
	 */
	public static FightSkillBuffVO createSkillBuffVO(Fighter tgt,
			FightBuff addBuff) {
		FightSkillBuffVO vo = new FightSkillBuffVO();
		vo.setS(tgt.getSide());
		vo.setP(tgt.getPos());
		vo.setB(addBuff.getId());
		vo.setC(addBuff.getRound());
		vo.setReplaceId(addBuff.getReplaceId());
		vo.setIsMian(addBuff.getIsMian());
		return vo;
	}

	/**
	 * 创建神通 vo
	 * @param fightTalent
	 * @param skillVO
	 * @return
	 */
	public static FightTalentVO createTalentVO(FightTalent fightTalent) {
		FightTalentVO vo = new FightTalentVO();
		Fighter src = fightTalent.getSrc();
		vo.setS(src.getSide());
		vo.setP(src.getPos());
		vo.setSid(fightTalent.getId());
		return vo;
	}

	/**
	 * 创建神通属性vo
	 * @param tgt
	 * @param propId
	 * @param val
	 * @return
	 */
	public static FightTalentPropVO createTalentPropVO(Fighter tgt, int propId,
			int val) {
		FightTalentPropVO vo = new FightTalentPropVO();
		vo.setS(tgt.getSide());
		vo.setP(tgt.getPos());
		vo.setIdx(propId);
		vo.setVal(val);
		vo.setL(tgt.getLife());
		return vo;
	}

	public static void createTalentRescueVO(Fighter tgt,
			FightSkillEffectResult result, boolean isCrit, boolean isBlock,
			FightTalentRescueResult rescue, FightSkillVO skillVO ,Integer sAnger) {
		
		FightSkillProcessVO processVO = new FightSkillProcessVO();
		processVO.setS(tgt.getSide());
		processVO.setP(tgt.getPos());
		if(isCrit)processVO.getSt().add(FightSkillState.Crit);
		if(isBlock)processVO.getSt().add(FightSkillState.Block);
		processVO.setMs(rescue.getOldTarget().getSide());
		processVO.setMp(rescue.getOldTarget().getPos());
		processVO.setMtal(rescue.getTalentId());
		processVO.setCnt(result.getCnt());
		processVO.setD(result.getDamageVal());
		processVO.setH(result.getHealVal());
		processVO.setA(tgt.getAngerVal());
		processVO.setL(tgt.getLife());
		processVO.setSa(sAnger);
		skillVO.addProcess(processVO);
		
		FightTalentVO vo = new FightTalentVO();
		vo.setS(tgt.getSide());
		vo.setP(tgt.getPos());
		vo.setSid(rescue.getTalentId());
		skillVO.getTal().add(vo);
	}
	
}
