/**
 * 
 */
package com.mdy.dzs.game.fight.main.fightskill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.battle.BattleSkill;
import com.mdy.dzs.data.domain.battle.Buff;
import com.mdy.dzs.game.fight.domain.fightskill.ComparatorFightPos;
import com.mdy.dzs.game.fight.domain.fightskill.FightSkillVO;
import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.main.fighttalent.FightTalentNode;
import com.mdy.dzs.game.fight.main.fighttalent.FightTalentRescueResult;
import com.mdy.dzs.game.fight.util.FightCalcUtil;
import com.mdy.dzs.game.fight.util.FightVOUtil;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月26日 上午10:11:53
 */
public class FightSkill {

	private static final Logger logger = LoggerFactory.get(FightSkill.class);

	public static final int TYPE_普通攻击 = 1;
	public static final int TYPE_怒气攻击 = 2;
	public static final int TYPE_TALENT = 3;
	public static final int TYPE_反击攻击 = 4;

	/** skill id */
	private int id;
	/** 战斗技能基础数据 */
	private BattleSkill data;
	/** 所属卡片 */
	private Fighter src;
	/** 技能类型 */
	private int type;
	/** 技能效果 */
	private FightSkillEffect effect;

	private List<Buff> buffs;

	public void trigger(FightMain main) {
		// 计算防守方
		List<Fighter> targets = main.chooseTarget(src, data, null);
		triggerTarget(main, targets, null, null);
	}

	public FightSkillVO triggerTarget(FightMain main, List<Fighter> targets, FightTalentVO talentVO,
			List<Object> roundMsg) {
		if (targets.size() == 0)
			return null;

		Collections.sort(targets, new ComparatorFightPos());// 正排序
		int round = main.getBoutCnt();
		if (roundMsg == null)
			roundMsg = src.getRoundMsg();
		boolean isSetAnger = false;
		if (type == TYPE_怒气攻击) {
			src.setAngerVal(0);
		}
		FightSkillVO skillVO = FightVOUtil.createSkillVO(src, this, round);
		List<FightTalentVO> talentListVO = skillVO.getTal();
		List<Fighter> blocks = new ArrayList<Fighter>();// 将格挡的目标存起来;
		List<Fighter> missTargets = new ArrayList<Fighter>();// 将闪避的目标存起来;

		boolean hasTargetDead = false;
		if (type == TYPE_普通攻击) {
			triggerTalent(src, main, FightTalentNode.普通攻击之前, talentListVO, targets, missTargets);
		} else if (type == TYPE_怒气攻击) {
			triggerTalent(src, main, FightTalentNode.怒气攻击之前, talentListVO, targets, missTargets);
		}

		for (Fighter tgt : targets) {
			Integer sAnger = null;
			if (type == TYPE_怒气攻击 && !isSetAnger) {
				isSetAnger = true;
				sAnger = src.getAngerVal();
			}
			// 防守方miss
			if (tgt.isDead())
				continue;
			boolean isMiss = !FightCalcUtil.calcNoMiss(src, tgt, effect);
			if (isMiss) {
				// 防守方miss后神通校验
				skillVO.addProcess(FightVOUtil.createSkillMissProcessVO(tgt, sAnger));
				// logger.debug("\n"+src.getData().getName() +"use skill:"+data.getName()+"
				// =>"+tgt.getData().getName()+
				// "=>miss");

				triggerTalent(tgt, main, FightTalentNode.每次躲避掉攻击后, talentListVO, Arrays.asList(src), missTargets);// 对方躲掉闪避
																													// 默认目标为攻击方
				// 存放闪避目标
				missTargets.add(tgt);
				// 重置技能所加的属性值
				tgt.resetTemps();
				continue;
			}
			int superNatural = 0;
			// 物理法术免疫
			if (effect.getStyle() == FightSkillEffect.STYLE_物理 && tgt.getSuperNatural() == FightSkillEffect.物理免疫) {
				// 物理免疫
				superNatural = tgt.getSuperNatural();
			} else if (effect.getStyle() == FightSkillEffect.STYLE_法术
					&& tgt.getSuperNatural() == FightSkillEffect.法术免疫) {
				// 法术免疫
				superNatural = tgt.getSuperNatural();
			}
			// 防守方群组援助神通校验 选出最终的挨打目标
			FightTalentRescueResult rescue = null;
			List<Fighter> rescueList = new ArrayList<Fighter>();
			if (targets.size() == 1 && tgt.getSide() != src.getSide()) {
				Map<Integer, Fighter> firends = tgt.getSide() == 1 ? main.getF1() : main.getF2();
				for (Fighter firend : firends.values()) {
					if (firend == null || firend.isDead() || firend == tgt)
						continue;
					rescueList = main.getReduses();
					for (Fighter fighter : rescueList) {
						if (firend == fighter) {
							firend = null;
							break;
						}
					}
					if (firend == null)
						continue;
					rescue = firend.triggerRescueTalent(main, tgt);
					if (rescue != null) {
						tgt = firend;
						rescueList.add(firend);
						break;
					}
				}
			}
			// 暴击检验
			boolean isCrit = FightCalcUtil.calcCrit(src, tgt);
			// TODO 暴击检验后神通校验
			// 防守方格挡
			boolean isBlock = false;
			if (type != TYPE_反击攻击) {
				isBlock = FightCalcUtil.calcBlock(src, tgt, effect);
			}
			boolean isHeal = false;
			// 防守方被攻击前神通校验 默认目标为攻击方
			if (type == TYPE_普通攻击 && effect.getType() != FightSkillEffect.TYPE_加血) {
				triggerTalent(tgt, main, FightTalentNode.被普攻前, talentListVO, Arrays.asList(src), missTargets);
			} else if (type == TYPE_怒气攻击 && effect.getType() != FightSkillEffect.TYPE_加血) {
				triggerTalent(tgt, main, FightTalentNode.被怒攻前, talentListVO, Arrays.asList(src), missTargets);
			}
			// 防守方挨打 发生伤害
			int baseDamage = 0;
			if (effect.getStyle() == FightSkillEffect.STYLE_物理) {
				baseDamage = FightCalcUtil.calcPhysicFinalDamage(src, tgt, isCrit, isBlock);
			} else {
				baseDamage = FightCalcUtil.calcMagicFinalDamage(src, tgt, isCrit, isBlock);
			}

			/////// 减免
			double reduce = 1.0f;
			if (type == TYPE_反击攻击) {
				reduce = Constants.battleCalcBack;
			}
			if (rescue != null) {
				reduce = rescue.getReduce();
			}
			FightSkillEffectResult result = effect.effectTarget(src, tgt, baseDamage, isCrit, reduce);
			if (superNatural > 0) {
				result.damageVal = 0;
			}
			// 禁怒
			if (data.getAnger() != 0 && !src.isBuffEffectExist(FightBuff.EFFECT_禁怒) && !isMiss && !isSetAnger) {
				src.setAngerVal(src.getAngerVal() + data.getAnger());
				isSetAnger = true;
				sAnger = src.getAngerVal();
			}
			if (rescue != null) {
				tgt.changeLife(result.healVal - result.damageVal);
				if (tgt.isDead())
					isBlock = false;
				sAnger = src.getAngerVal();
				FightVOUtil.createTalentRescueVO(tgt, result, isCrit, isBlock, rescue, skillVO, sAnger);
			} else {
				tgt.changeLife(result.healVal - result.damageVal);
				if (effect.getType() == FightSkillEffect.TYPE_加血) {
					isHeal = true;
				}
				if (tgt.isDead())
					isBlock = false;
				skillVO.addProcess(
						FightVOUtil.createSkillProcessVO(tgt, result, isCrit, isBlock, isHeal, sAnger, superNatural));
				if (result.getSelfHealVal() != 0) {
					FightSkillEffectResult newResult = new FightSkillEffectResult();
					newResult.setSelfHealVal(result.getSelfHealVal());
					isHeal = true;
					skillVO.addProcess(FightVOUtil.createSkillProcessVO(src, newResult, false, false, isHeal, sAnger,
							superNatural));
				}
			}

			if (isBlock && type != TYPE_反击攻击 && !tgt.isDead()) {// 格挡反击
				blocks.add(tgt);
			}
			// logger.debug("\n"+src.getData().getName() +"use skill:"+data.getName()+"
			// =>"+tgt.getData().getName()+
			// "@damage=>"+result.damageVal+",@heal=>"+result.healVal+",crit:"+isCrit+",block:"+isBlock);

			// TODO 反收方被暴击
			// TODO 防守方格挡
			// TODO 防守方格挡后神通校验
			// 防守方自身死亡神通校验 默认目标为攻击方
			if (tgt.isDead()) {
				triggerTalent(tgt, main, FightTalentNode.自己死亡后, talentListVO, Arrays.asList(src), missTargets);
				hasTargetDead = true;
			}

			// 普、怒攻结束
			// 防守方被攻击后神通校验 默认目标为攻击方
			// 普、怒攻结束后神通校验
			if (type == TYPE_普通攻击 && effect.getType() != FightSkillEffect.TYPE_加血) {
				triggerTalent(tgt, main, FightTalentNode.被普攻后, talentListVO, Arrays.asList(src), missTargets);
			} else if (type == TYPE_怒气攻击 && effect.getType() != FightSkillEffect.TYPE_加血) {
				triggerTalent(tgt, main, FightTalentNode.被怒攻后, talentListVO, Arrays.asList(src), missTargets);
			}
			// 重置技能所加的属性值
			tgt.resetTemps();
		}
		// =================加技能附加buff========================//
		List<Fighter> selects = new ArrayList<Fighter>();
		if (buffs.size() > 0) {
			// buff上默认目标
			selects = main.chooseTarget(src, data.getbTarget(), data.getbSide(), targets);
		}
		// 除去miss的目标
		Iterator<Fighter> iterator = selects.iterator();
		while (iterator.hasNext()) {
			Fighter f = iterator.next();
			if (missTargets.indexOf(f) != -1) {
				iterator.remove();
			}
		}
		for (Buff buffData : buffs) {
			if (selects.size() > 0) {
				FightBuff.addBuffs(main, buffData, src, selects, data.getProb(), skillVO.getBuff());
			}
		}

		// 神通和加减怒也要去掉 闪避的目标
		for (Iterator<Fighter> iterator2 = targets.iterator(); iterator2.hasNext();) {
			Fighter fighter = (Fighter) iterator2.next();
			if (missTargets.indexOf(fighter) != -1) {
				iterator2.remove();
			}
		}
		// if( targets.size() > 0 ){
		if (type == TYPE_普通攻击) { // 默认目标为防守方
			triggerTalent(src, main, FightTalentNode.普攻结束后, talentListVO, targets, missTargets);
		} else if (type == TYPE_怒气攻击) {
			triggerTalent(src, main, FightTalentNode.怒攻结束后, talentListVO, targets, missTargets);
		}
		// }
		// 反击
		for (Fighter blocker : blocks) {
			FightSkillVO backSkillVO = blocker.fightBack(main, src, roundMsg);
			skillVO.addFb(backSkillVO);
			// 格挡检验后神通校验 格挡后的默认目标为攻击方
			triggerTalent(blocker, main, FightTalentNode.我格挡后, talentListVO, Arrays.asList(src), missTargets);
		}
		if (targets.size() > 0) {
			// 目标死亡神通校验
			if (hasTargetDead) {// 默认目标为防守方
				triggerTalent(src, main, FightTalentNode.目标死亡, talentListVO, targets, missTargets);
			}
			// anger calc
			effect.effectOptionalTarget(main, src, skillVO, targets);
		}

		skillVO.setL(src.getLife());
		skillVO.setA(src.getAngerVal());
		if (type == TYPE_TALENT) {
			talentVO.getSkill().add(skillVO);
		} else if (type != TYPE_反击攻击) {
			roundMsg.add(skillVO);
		}
		src.resetTemps();

		return skillVO;
	}

	private void triggerTalent(Fighter tgt, FightMain main, int node, List<FightTalentVO> listVO,
			List<Fighter> defaultTargets, List<Fighter> missTargets) {
		if (type == TYPE_TALENT)
			return;
		tgt.triggerTalent(main, node, listVO, defaultTargets, missTargets);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BattleSkill getData() {
		return data;
	}

	public void setData(BattleSkill data) {
		this.data = data;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public FightSkillEffect getEffect() {
		return effect;
	}

	public void setEffect(FightSkillEffect effect) {
		this.effect = effect;
	}

	/**
	 * 创建技能
	 * 
	 * @param fighter
	 * @param skillId
	 * @param type
	 * @return
	 * @throws BizException
	 */
	public static FightSkill createSkill(Fighter fighter, int skillId, int type) {
		CacheManager dataManager = FightMain.dataManager;
		FightSkill skill = new FightSkill();
		skill.id = skillId;
		BattleSkill data = dataManager.getValueByKey(BattleSkill.class, skillId);
		if (data == null) {
			logger.warn("not find BattleSkill data:" + skillId);
			return null;
		}
		skill.data = data;
		skill.type = type;
		skill.effect = new FightSkillEffect(data);
		skill.src = fighter;
		List<Buff> buffs = new ArrayList<Buff>();
		for (Integer buffId : data.getBuff()) {
			if (buffId == 0)
				continue;
			Buff buffData = dataManager.getValueByKey(Buff.class, buffId);
			if (buffData == null) {
				logger.warn("not find buff data:" + buffId);
				continue;
			}
			buffs.add(buffData);
		}
		skill.buffs = buffs;
		return skill;
	}

}
