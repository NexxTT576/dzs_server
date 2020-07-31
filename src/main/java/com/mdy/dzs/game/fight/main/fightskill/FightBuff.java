package com.mdy.dzs.game.fight.main.fightskill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mdy.dzs.data.domain.battle.Buff;
import com.mdy.dzs.game.fight.domain.fightskill.FightSkillBuffVO;
import com.mdy.dzs.game.fight.main.FConstants;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;
import com.mdy.dzs.game.fight.util.FightVOUtil;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * 战斗buff
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月25日 上午11:42:18
 */
public class FightBuff {

	private static final Logger logger = LoggerFactory.get(FightBuff.class);

	public static final int EFFECT_增益 = 1;
	public static final int EFFECT_减益 = 2;
	public static final int EFFECT_回血 = 3;
	public static final int EFFECT_中毒掉血 = 4;
	public static final int EFFECT_火烧掉血 = 5;
	/** 眩晕:无法普攻和怒攻 */
	public static final int EFFECT_眩晕 = 6;
	/** 封技：无法使用怒气技能；则使用普通技能 */
	public static final int EFFECT_封技 = 7;
	/** 冰冻：不能进行普攻； */
	public static final int EFFECT_冰冻 = 8;
	/** 封神：无法触发神通； */
	public static final int EFFECT_封神 = 9;
	/** 无敌：受到的直接伤害=1 */
	public static final int EFFECT_无敌 = 10;
	/** 禁疗：无法被治疗 */
	public static final int EFFECT_禁疗 = 11;
	/** 禁怒：怒气无法增长 */
	public static final int EFFECT_禁怒 = 12;
	public static final int EFFECT_增加神通 = 13;
	/** 免疫buff */
	public static final int EFFECT_免疫buff = 14;

	public static final int 可移除 = 0;
	public static final int 不可移除 = 1;

	/** buff id */
	private int id;
	/** 共存组 */
	private int group;
	/** 公村组 */
	private int coexist;
	/** 效果 */
	private int effect;
	/** 回合数 */
	private int round;
	/** 增减属性key */
	private List<Integer> keys;
	/** 增减属性值 */
	private List<Integer> vals;

	private List<Fighter> talentTargets;
	/** 免疫buffId组 */
	private List<Integer> defBuffId;
	/**
	 * 替换掉的buffid
	 */
	private int replaceId;
	/**
	 * 是否被免疫了
	 */
	private int isMian;
	private Buff buffData;

	private boolean isNew;
	/** 是否为即时生效 */
	private int isNow;

	public int getReplaceId() {
		return replaceId;
	}

	public void setReplaceId(int replaceId) {
		this.replaceId = replaceId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCoexist() {
		return coexist;
	}

	public void setCoexist(int coexist) {
		this.coexist = coexist;
	}

	public int getEffect() {
		return effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public List<Integer> getKeys() {
		return keys;
	}

	public void setKeys(List<Integer> keys) {
		this.keys = keys;
	}

	public List<Integer> getVals() {
		return vals;
	}

	public void setVals(List<Integer> vals) {
		this.vals = vals;
	}

	public FightBuff(Buff buffData) {
		this.id = buffData.getId();
		this.group = buffData.getGroup();
		this.coexist = buffData.getCoexist();
		this.effect = buffData.getEffect();
		this.round = buffData.getRound();
		this.buffData = buffData;
		this.keys = new ArrayList<Integer>();
		this.vals = new ArrayList<Integer>();
		this.defBuffId = buffData.getDefBuffId();
		this.isNew = true;
		this.isNow = buffData.getIsNow();
	}

	public void calculateEffect(Fighter fighet, Fighter target) {
		switch (effect) {
		case EFFECT_增益:
		case EFFECT_减益:
			changeProperty();
			break;
		case EFFECT_回血:
			addLife(fighet);
			break;
		case EFFECT_中毒掉血:
			raraSubLife(fighet, target, FConstants.AttInd_Poison, FConstants.AttInd_PoisonD, FConstants.AttInd_PoisonR,
					FConstants.AttInd_PoisonDR);
			break;
		case EFFECT_火烧掉血:
			raraSubLife(fighet, target, FConstants.AttInd_Fire, FConstants.AttInd_FireD, FConstants.AttInd_FireR,
					FConstants.AttInd_FireDR);
			break;
		case EFFECT_眩晕:
		case EFFECT_封技:
		case EFFECT_冰冻:
		case EFFECT_封神:
		case EFFECT_无敌:
		case EFFECT_禁疗:
		case EFFECT_禁怒:
			break;
		case EFFECT_增加神通:
			addTalent(target);
			break;

		default:
			break;
		}
	}

	private void addTalent(Fighter target) {
		if (target.isDead())
			return;
		if (talentTargets != null) {
			talentTargets.add(target);
		}
		List<Integer> ids = buffData.getParam();
		for (Integer talentId : ids) {
			target.AddFightTalent(talentId);
		}

	}

	public void removeEffect() {
		if (talentTargets != null && talentTargets.size() > 0) {
			List<Integer> ids = buffData.getParam();
			for (Fighter tgt : talentTargets) {
				for (Integer talentId : ids) {
					tgt.DelFightTalent(talentId);
				}
			}
		}
	}

	/**
	 * 稀有属性减血
	 * 
	 * @param buffData
	 * @param fighet
	 * @param target
	 * @param attPoison
	 * @param attPoisond
	 */
	private void raraSubLife(Fighter fighetr, Fighter target, int att, int attd, int attr, int attdr) {
		int para1 = buffData.getParam().get(0);
		int para2 = buffData.getParam().get(1);
		int attack = fighetr.getTmpAttack();
		int attVal = fighetr.getTmpPropertyByAttInd(att);
		int attdVal = target.getTmpPropertyByAttInd(attd);
		int attrVal = fighetr.getTmpPropertyByAttInd(attr);
		int attdrVal = target.getTmpPropertyByAttInd(attdr);
		int val = (int) Math.floor(
				(attack * para1 / 10000 + para2 + attVal - attdVal) * (1 + attrVal * 0.0001 - attdrVal * 0.0001));
		vals.add(-val);
		keys.add(FConstants.AttInd_Life);
	}

	/**
	 * 回复
	 * 
	 * @param buffData
	 * @param fighet
	 */
	private void addLife(Fighter fighet) {
		int para1 = buffData.getParam().get(0);
		int para2 = buffData.getParam().get(1);

		int attack = fighet.getTmpAttack();
		int val = (int) Math.round((para1 * 0.0001 * attack + para2) * (Math.random() * 0.2 + 0.9));
		// console.log('bloodAdd:', fighter.side, fighter.pos, fighter.base[Attr.Life],
		// val);
		vals.add(val);
		keys.add(FConstants.AttPos_base + FConstants.Att_Life);
	}

	/**
	 * 增益 减益 更改属性
	 * 
	 * @param buffData
	 */
	private void changeProperty() {
		List<List<Integer>> buffPropertys = Arrays.asList(buffData.getBase(), buffData.getBaseRate(),
				buffData.getBaseMod(), buffData.getRare(), buffData.getRareRate());
		List<Integer> startPoss = Arrays.asList(FConstants.AttPos_base, FConstants.AttPos_baseRate,
				FConstants.AttPos_baseMod, FConstants.AttPos_rare, FConstants.AttPos_rareRate);
		for (int i = 0; i < buffPropertys.size(); i++) {
			List<Integer> property = buffPropertys.get(i);
			if (property.size() == 0)
				continue;
			for (int ind = 0; ind < property.size(); ind++) {
				if (property.get(ind) != 0) {
					keys.add(startPoss.get(i) + ind);
					vals.add(property.get(ind));
				}
			}
		}

	}

	/**
	 * 上buff
	 * 
	 * @param buffId
	 * @param src
	 * @param target
	 * @return 返回新加buff[0] 不共存buff[1]
	 * @throws BizException
	 */
	private static FightBuff addBuff(Buff buffData, Fighter fighet, Fighter target, int prob) {
		FightBuff res = null;
		do {
			// 验证目标是否死亡
			if (fighet == null || target == null) {
				break;
			}
			if (fighet.isDead() || target.isDead()) {
				break;
			}
			// 验证是否有buff抵抗
			if (target.isResistBuff(buffData.getEffect())) {
				break;
			}
			if (Math.random() > prob * 0.0001) {
				break;
			}
			res = new FightBuff(buffData);
			List<FightBuff> fbs = target.getBuffArrByEffect(FightBuff.EFFECT_免疫buff);
			if (fbs.size() > 0) {
				for (FightBuff fightBuff : fbs) {
					if (fightBuff.getDefBuffId().indexOf(res.getGroup()) != -1) {
						// 此buff被免疫
						res.setIsMian(1);
					}
				}
			}
			if (res.getIsMian() == 0) {
				// 根据coexist验证是否有不共存buff 将其一处
				FightBuff coexistBuff = target.removeCoexistBuff(buffData.getGroup(), buffData.getCoexist());
				// 根据effect 计算buff效果
				res.calculateEffect(fighet, target);
				// 设置替换不同存buffid
				res.setReplaceId(coexistBuff != null ? coexistBuff.getId() : 0);
				// 加入目标buff列表 待下回合生效
				target.addBuff(res);
			}
			// 返回计算后buff结构体
		} while (false);
		return res;
	}

	/**
	 * 上buff
	 * 
	 * @param buffId
	 * @param src
	 * @param target
	 * @return
	 * @throws BizException
	 */
	public static void addBuffs(FightMain main, Buff buffData, Fighter src, List<Fighter> targets, int prob,
			List<FightSkillBuffVO> vo) {
		// if(buffData.getTarget() != 0){
		// targets = SkillChooseTarget.chooseTarget(
		// main, src, buffData.getTarget(), buffData.getSide());
		// }
		for (Fighter target : targets) {
			if (target == null) {
				logger.info("not target:" + buffData.getId());
				continue;
			}
			// 禁止被上buff
			if (target.isForbitBuff())
				continue;
			FightBuff buff = addBuff(buffData, src, target, prob);
			if (buff != null && buff.getEffect() != EFFECT_免疫buff) {
				// logger.debug("\nadd buff "+addBuff.getId()+" effect:"+addBuff.getEffect()+
				// ",round:"+addBuff.getRound()+"=>"+tgt.getData().getName());
				vo.add(FightVOUtil.createSkillBuffVO(target, buff));
			}

		}
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public List<Integer> getDefBuffId() {
		return defBuffId;
	}

	public void setDefBuffId(List<Integer> defBuffId) {
		this.defBuffId = defBuffId;
	}

	public int getIsMian() {
		return isMian;
	}

	public void setIsMian(int isMian) {
		this.isMian = isMian;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public int getIsNow() {
		return isNow;
	}

	public void setIsNow(int isNow) {
		this.isNow = isNow;
	}
}
