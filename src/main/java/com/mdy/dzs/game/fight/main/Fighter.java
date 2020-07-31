/**
 * 
 */
package com.mdy.dzs.game.fight.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.battle.Buff;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.shentong.Talent;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.RoleLineupAid.RoleLineupCardAid;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.swordfight.SwordRoleCard;
import com.mdy.dzs.game.fight.domain.fightskill.FightBuffVO;
import com.mdy.dzs.game.fight.domain.fightskill.FightSkillVO;
import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;
import com.mdy.dzs.game.fight.main.fightskill.FightBuff;
import com.mdy.dzs.game.fight.main.fightskill.FightSkill;
import com.mdy.dzs.game.fight.main.fighttalent.FightTalent;
import com.mdy.dzs.game.fight.main.fighttalent.FightTalentNode;
import com.mdy.dzs.game.fight.main.fighttalent.FightTalentRescue;
import com.mdy.dzs.game.fight.main.fighttalent.FightTalentRescueResult;
import com.mdy.dzs.game.fight.util.FightVOUtil;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * 战斗成员
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  下午2:53:28
 */
public class Fighter {
	
	private static final Logger logger = LoggerFactory.get(Logger.class);

	//dataAry,card,side,battleObj, fmtPropAry, posOpt
	/**card resid*/
	private int id;
	/**card数据*/
	private Card data;
	/**阶级*/
	private int cls;
	/**星级 */
	private int star;
	/**sex*/
	private int sex;
	/**阵营*/
	private int side;
	/**位置*/
	private int pos;
	/**缩放*/
	private double scale;
	/**身上神通列表*/
	private List<FightTalent> talents;
	private FightTalentRescue rescueTalent;
	
	/**怒气值*/
	private Integer anger[];
	
	/**初始化生命*/
	private int initLife;
	//属性值
	/**基础属性组（生命；攻击；物防；法防）*/
	private Integer base[];
	/**基础三围（统帅；武力；智力）*/
	private Integer lead[];
	/**基础比例组（生命比例；攻击比例；物防比例；法防比例）*/
	private Integer baseRate[];
	/**修正属性组（命中；闪避；暴击；抗暴；破击；格挡）*/
	private Integer baseMod[];
	/**
	 * 稀有属性组（物理伤害加成；法术伤害加成；中毒伤害加成；火焰伤害加成；
	 * 物理伤害减免；法术伤害减免；中毒伤害减免；火焰伤害减免
	 */
	private Integer rare[];
	/**
	 * 稀有比例组（物理伤害加成；法术伤害加成；中毒伤害加成；火焰伤害加成；
	 * 物理伤害减免；法术伤害减免；中毒伤害减免；火焰伤害减免
	 */
	private Integer rareRate[];
	/**治疗效果组（治疗效果加成数值；治疗效果加成比例；被治疗效果加成数值；被治疗效果加成比例）*/
	private Integer heal[];
	//private int finnal[];
	/**身手值*/
	private int rate;
	/**buff抵抗*/
	private List<Integer> resist;
	private Integer finnal[] = {0,0,0,0,0};


	//state
	private int angerVal;

	/**buff列表*/
	private List<FightBuff> buffs;
	private Map<Integer, Integer> buffEffects;

	/**普攻技能*/
	private FightSkill skill;
	/**怒气技能*/
	private FightSkill angerSkill;
	/**反击技能*/
	private FightSkill backSkill;
	
	/**lifeRate*/
	private int lifeRate = -1;
	/**禁止被上 buff*/
	private boolean forbitBuff = false;
	
	/**超能力（0-无；1-物理免疫；2-法术免疫)*/
	private int superNatural;
	
	//temp
	Map<Integer, Integer> rndAddPropMap = new HashMap<Integer, Integer>();
	Map<Integer, Integer> tmpAddPropMap = new HashMap<Integer, Integer>();
	private Integer ctrlState[]={0,0,0,0,0,0,0,0,0,0};
			
	
	private List<Object> roundMsg;
	public Fighter(RoleCard rcard,List<Prop> prop, int side, int pos) throws BizException {
		CacheManager dataManager = FightMain.dataManager;
		this.data = dataManager.getExistValueByKey(
				Card.class, rcard.getResId());
		this.id = rcard.getResId();
		this.cls = rcard.getCls();
		this.star = rcard.getStar();
		
		List<Integer> shenIdAry = rcard.getShenIDAry();
		List<Integer> shenLvAry = rcard.getShenLvAry();
		talents = new ArrayList<FightTalent>();
		for(int i=0; i<shenIdAry.size(); i++){
			if(shenIdAry.get(i) == 0 || shenLvAry.get(i)<1)	
				continue;	
			AddFightTalent(shenIdAry.get(i));
			
		}

		anger 	= copyInts(anger,data.getAnger());
		lead	= copyInts(lead,data.getLead());
		base	= copyInts(base,data.getBase());
		init(data,prop,side,pos);
	}
	
	public Fighter(SwordRoleCard rcard,List<Prop> prop, int side, int pos) throws BizException {
		CacheManager dataManager = FightMain.dataManager;
		this.data = dataManager.getExistValueByKey(
				Card.class, rcard.getResId());
		this.id = rcard.getResId();
		this.cls = rcard.getCls();
		this.star = rcard.getStar();
		
		List<Integer> shenIdAry = rcard.getShenIDAry();
		List<Integer> shenLvAry = rcard.getShenLvAry();
		talents = new ArrayList<FightTalent>();
		for(int i=0; i<shenIdAry.size(); i++){
			if(shenIdAry.get(i) == 0 || shenLvAry.get(i)<1)	
				continue;	
			AddFightTalent(shenIdAry.get(i));
			
		}

		anger 	= null;
		lifeRate = rcard.getLifeRate();
		this.angerVal = rcard.getAnger();
		lead	= copyInts(lead,data.getLead());
		base	= copyInts(base,data.getBase());
		init(data,prop,side,pos);
	}
	public Fighter(RoleLineupCardAid rcard,List<Prop> prop, int side, int pos) throws BizException {
		CacheManager dataManager = FightMain.dataManager;
		this.data = dataManager.getExistValueByKey(
				Card.class, rcard.getResId());
		this.id = rcard.getResId();
		this.cls = rcard.getCls();
		this.star = rcard.getStar();
		
		List<Integer> shenIdAry = rcard.getShenIDAry();
		List<Integer> shenLvAry = rcard.getShenLvAry();
		talents = new ArrayList<FightTalent>();
		for(int i=0; i<shenIdAry.size(); i++){
			if(shenIdAry.get(i) == 0 || shenLvAry.get(i)<1)	
				continue;	
			AddFightTalent(shenIdAry.get(i));
			
		}

		anger 	= null;
		lifeRate = rcard.getLifeRate();
		this.angerVal = rcard.getAnger();
		lead	= copyInts(lead,data.getLead());
		base	= copyInts(base,data.getBase());
		init(data,prop,side,pos);
	}
	
	public Fighter(Card card, int side, int pos) throws BizException {
		this.data = card;
		this.id = card.getId();
		this.cls = 0;
		this.star = card.getStar().get(0);
		anger	= copyInts(anger,data.getAnger());
		lead	= copyInts(lead,data.getLead());
		base	= copyInts(base,data.getBase());
		
		List<Integer> shenIdAry = card.getTalent();
		talents = new ArrayList<FightTalent>();
		for(int i=0; i<shenIdAry.size(); i++){
			if(shenIdAry.get(i) == 0)	
				continue;	
			AddFightTalent(shenIdAry.get(i));
		}
		
		init(card,null,side,pos);
	}
	
	private void init(Card data,List<Prop> prop, int side, int pos) throws BizException{
		this.side = side;
		this.pos = pos;
		this.sex = data.getSex();
		this.superNatural = data.getSuperNatural();
		
		baseRate = copyInts(baseRate,data.getBaseRate());
		baseMod = copyInts(baseMod,data.getBaseMod());
		rare 	= copyInts(rare		, data.getRare());
		rareRate= copyInts(rareRate	, data.getRareRate());
		heal	= copyInts(heal		, data.getHeal());
		//this.finnal		= [0,0,0,0,0];
		//此属性已加入到fmt里
		this.addProp(prop);
		//
		this.setRate(data.getRate());
		this.resist		= new ArrayList<Integer>(data.getResist());
		addPropertyByAttInd(FConstants.AttInd_FCritR, data.getCrit().get(0));
		addPropertyByAttInd(FConstants.AttInd_FCritDR, data.getCrit().get(1));
		
		scale = 1.0f;
		
		//state
		if(anger != null){
			this.angerVal  	=  anger[cls];
		}
		this.setBuffs(new ArrayList<FightBuff>());			//battleBuff array
		this.buffEffects = new HashMap<Integer, Integer>();
		//this.bTalentAtt = false;		//curRound
		
		//skill
		int skillId = data.getSkill().get(cls);
		skill = FightSkill.createSkill(this,skillId,FightSkill.TYPE_普通攻击);
		int angerSkillId = data.getAngerSkill().get(cls);
		angerSkill = FightSkill.createSkill(this,angerSkillId,FightSkill.TYPE_怒气攻击);
		int backSkillId = data.getBskill().get(cls);
		backSkill = FightSkill.createSkill(this, backSkillId, FightSkill.TYPE_反击攻击);
		
		if(talents == null){
			talents = new ArrayList<FightTalent>();
		}
		
		roundMsg = new ArrayList<Object>();
		
		//talent
		initTemProperty();
		
		calcBase();
	}
	
	/**
	 * 附加prop设置基础属性
	 * @param propAry
	 */
	private void addProp(List<Prop> propAry){
		if(propAry == null || propAry.size()==0)	return;
		for(int i=0; i<propAry.size(); i++){
			Prop p = propAry.get(i);
			addPropertyByAttInd(p.getIdx(), p.getVal());
		}
	};
	

	private Integer[] copyInts(Integer res[],List<Integer> in){
		res = new Integer[in.size()];
		in.toArray(res);
		return res;
	}
	
	private void initTemProperty(){
		initLife = base[FConstants.Att_Life];
		resetTemps();
	}
	/**
	 * 重置持续一回合属性
	 */
	public void resetRounds(){
		rndAddPropMap.clear();
		for (int i =0;i<ctrlState.length;i++) {
			ctrlState[i] = 0;
		}
	}
	/**
	 * 重置临时属性
	 */
	public void resetTemps(){
//		System.arraycopy(base, 0, tbase, 0, base.length);
//		System.arraycopy(baseRate, 0, tbaseRate, 0, baseRate.length);
//		System.arraycopy(baseMod, 0, tbaseMod, 0, baseMod.length);
//		System.arraycopy(rare, 0, trare, 0, rare.length);
//		System.arraycopy(rareRate, 0, trareRate, 0, rareRate.length);
//		System.arraycopy(lead, 0, tlead, 0, lead.length);
//		System.arraycopy(heal, 0, theal, 0, heal.length);
//		System.arraycopy(finnal, 0, tfinnal, 0, finnal.length);
		tmpAddPropMap.clear();
	}
	
	/**
	 * 添加一个神通
	 * @param talentId
	 * @throws BizException
	 */
	public void AddFightTalent(int talentId){
		Talent talentData = FightMain.dataManager.getValueByKey(Talent.class, talentId);
		if(talentData == null){ 
			logger.info("not has talent data:"+talentId);
			return;
		}
		if(talentData.getStType() == Talent.ST_TYPE_援护){
			rescueTalent = new FightTalentRescue(this, talentData);
		}else{
			talents.add(new FightTalent(this, talentData));
		}
	}
	
	/**
	 * 移除神通
	 * @param talentId
	 */
	public void DelFightTalent(int talentId){
		for (int i = 0; i < talents.size(); i++) {
			FightTalent talent = talents.get(i);
			if(talent.getId() == talentId){
				talents.remove(i);
				break;
			}
			
		}
	}
	
	/**
	 * 获取攻击力
	 * @return
	 */
	public int getTmpAttack() {
		int srcAttack = getTmpPropertyByAttInd(FConstants.AttInd_Attack);
		int srcAttackRate = getTmpPropertyByAttInd(FConstants.AttInd_AttackR);
		return (int)(Math.floor(srcAttack*(1.0+srcAttackRate*0.0001)));
	}
	
	/**
	 * 获得魔防
	 * @return
	 */
	public int getTmpDefen(){
		int srcDefense = getTmpPropertyByAttInd(FConstants.AttInd_Defense);
		int srcDefenseRate = getTmpPropertyByAttInd(FConstants.AttInd_DefenseR);
		return (int)(Math.floor(srcDefense*(1.0+srcDefenseRate*0.0001)));
	}
	
	/**
	 * 获得魔防抵抗
	 * @return
	 */
	public int getTmpDefenM(){
		int srcDefense = getTmpPropertyByAttInd(FConstants.AttInd_DefenM);
		int srcDefenseRate = getTmpPropertyByAttInd(FConstants.AttInd_DefenMR);
		return (int)(Math.floor(srcDefense*(1.0+srcDefenseRate*0.0001)));
	}
	
	
	/**
	 * 根据index获取临时属性
	 * @param attInd
	 * @return
	 */
	public int getTmpPropertyByAttInd(int attInd){
		int res = getPropertyByAttInd(attInd);
		Integer rndAdd = rndAddPropMap.get(attInd);
		if(rndAdd != null) res += rndAdd;
		Integer tmpAdd = tmpAddPropMap.get(attInd);
		if(tmpAdd != null) res += tmpAdd;
		return res;
	}
	
	/**
	 * 根据index增加临时属性
	 * @param attInd
	 * @return
	 */
	public void addTmpPropertyByAttInd(int attInd,int val){
		Integer oldVal = tmpAddPropMap.get(attInd);
		if(oldVal != null){
			val +=oldVal;
		}
		tmpAddPropMap.put(attInd, val);
	}
	
	/**
	 * 根据index增加临时属性
	 * @param attInd
	 * @return
	 */
	public void addRndPropertyByAttInd(int attInd,int val){
		Integer oldVal = rndAddPropMap.get(attInd);
		if(oldVal != null){
			val +=oldVal;
		}
		rndAddPropMap.put(attInd, val);
	}
	
	private Integer[] getPropertys(int attInd){
		int type = (int)Math.floor(attInd/10);
		Integer propertys[] = null;
		switch (type) {
		case 1:
			propertys = lead;
			break;
		case 2:
			propertys = base;
			break;
		case 3:
			propertys = baseRate;
			break;
		case 4:
			propertys = rare;
			break;
		case 5:
			propertys = rareRate;
			break;
		case 6:
			propertys = baseMod;
			break;
		case 7:
			if(attInd>=75){
				propertys = finnal;
			}else{
				propertys = heal;
			}
			break;
		}
		return propertys;
	}
	/**
	 * 根据index获取基础属性
	 * @param attInd
	 * @return
	 */
	public int getPropertyByAttInd(int attInd){
		Integer[] propertys = getPropertys(attInd);
		int index = attInd%10-1;
		if(attInd>=75) index-=4;
		return propertys[index];
	}
	/**
	 * 根据index增加永久属性
	 * @param attInd
	 * @return
	 */
	public void addPropertyByAttInd(int attInd, int val) {
		Integer[] propertys = getPropertys(attInd);
		if(propertys == null){
			logger.info("attInd is error:"+attInd+",val:"+val);
			return;
		}
		int index = attInd%10-1;
		if(attInd>=75) index-=4;
		propertys[index]+= val;
	}
	
	/**
	 * 是否死亡
	 * @return
	 */
	public boolean isDead(){
		return base[FConstants.Att_Life] <= 0;
	}
	
	/**
	 * 改变生命
	 * @param demage
	 */
	public void changeLife(int val){
		if(base[FConstants.Att_Life]<=0) return;
		base[FConstants.Att_Life] += val;
		if(base[FConstants.Att_Life]>initLife){
			base[FConstants.Att_Life]=initLife;
		}
		//TODO 如果死亡 检验死亡神通
	}
	
	/**
	 * 获取生命
	 * @return
	 */
	public int getLife(){
		int res = base[FConstants.Att_Life];
		if(res<=0) res = 0;
		return res;
	}
	
	/**
	 * 验证是否抵抗buff
	 * @param buffData
	 * @return
	 */
	public boolean isResistBuff(int effect) {
		return resist.indexOf(effect) != -1;
	}
	
	/**
	 * 移除不共存的buff
	 * @param group
	 * @param coexist
	 * @return
	 */
	public FightBuff removeCoexistBuff(int group ,int coexist) {
		/*int i = buffs.size()-1;
		while(i>=0){
			FightBuff buff = buffs.get(i);
//			group相同 coexist为1 则进行替换
			if(buff.getGroup() == group && coexist == 1){
				buff.removeEffect();
				buffs.remove(i);
				Integer count = buffEffects.get(buff.getEffect());
				if(count != null && count>0){
					count-=1;
				}
				//logger.debug("\nbuff "+buff.getId()+" remove coexist=>"+data.getName());
				return buff;
			}
			i--;
		}
		return null;*/
		int i = 0;
		while(i<=getBuffs().size()-1){
			FightBuff buff = getBuffs().get(i);
//			group相同 coexist为1 则进行替换
			if(buff.getGroup() == group && coexist == 1){
				buff.removeEffect();
				getBuffs().remove(i);
				Integer count = buffEffects.get(buff.getEffect());
				if(count != null && count>0){
					count-=1;
				}
				//logger.debug("\nbuff "+buff.getId()+" remove coexist=>"+data.getName());
				return buff;
			}else if(buff.getGroup() == group && coexist > 1){
				//count数量达到上限coexist 则进行替换
				Integer count = buffEffects.get(buff.getEffect());
				if(count != null && count == coexist){
					buff.removeEffect();
					getBuffs().remove(i);
					return buff;
				}
			}
			i++;
		}
		return null;
	}
	

	/**
	 * 移除没有效果的buff
	 */
	public void rmOverBuff(){
		/*Iterator<FightBuff> it =  buffs.iterator();
		while(it.hasNext()){
			FightBuff buff = it.next();
			if(buff.getRound()<=0){
				buff.removeEffect();
				it.remove();
				Integer count = buffEffects.get(buff.getEffect());
				if(count != null && count>0){
					buffEffects.put(buff.getEffect(), count-1);
				}
				//logger.debug("\nbuff "+buff.getId()+" remove=>"+data.getName());
			}
		}*/
		Iterator<FightBuff> it =  getBuffs().iterator();
		while(it.hasNext()){
			FightBuff buff = it.next();
			if((buff.getRound()<=0) && (buff.getIsNow() == 0)){
				buff.removeEffect();
				it.remove();
				Integer count = buffEffects.get(buff.getGroup());
				if(count != null && count>0){
					buffEffects.put(buff.getGroup(), count-1);
				}
				//logger.debug("\nbuff "+buff.getId()+" remove=>"+data.getName());
			}
		}
	}
	
	/**
	 * 添加一个buff
	 * @param res
	 */
	public void addBuff(FightBuff buff) {
		getBuffs().add(buff);
		Integer count = buffEffects.get(buff.getGroup());
		if(count == null){
			buffEffects.put(buff.getGroup(), 1);
		}else{
			buffEffects.put(buff.getGroup(), count+1);
		}
	}
	
	
	/**
	 * 查实是否存在buff
	 * @param effect
	 * @return
	 */
	public Boolean isBuffEffectExist(int effect){
		for (FightBuff fb : getBuffs()) {
			if( fb.getEffect() == effect ){
				return true;
			}
		}
		return false;
	}
	/**
	 * 获得免疫的buff组
	 * @param effect
	 * @return
	 */
	public List<FightBuff> getBuffArrByEffect(int effect){
		List<FightBuff> buffs = new ArrayList<FightBuff>();
		for (FightBuff fb : getBuffs()) {
			if( fb.getEffect() == effect ){
				buffs.add(fb);
			}
		}
		return buffs;
	}
	
	public List<Object> attack(FightMain main) {
		roundMsg = new ArrayList<Object>();
		do
		{
			int round = main.getBoutCnt();
			resetRounds();
			//攻击方buff/debuff生效
			buffEffect(main,round,false, roundMsg);
			//是否死亡
			if(isDead()) break;
			//选出技能
			FightSkill select = selectSkill();
			if(select != null){
				select.trigger(main);
			}
			//移除无效果的buff
			rmOverBuff();
		}while(false);
		return roundMsg;
	}
	
	/**
	 * 选出技能
	 * @return
	 */
	public FightSkill selectSkill(){
		FightSkill select = null;
		do{
			if(isBuffEffectExist(FightBuff.EFFECT_眩晕)){
				break;
			}
			select = angerSkill;
			if(angerVal < 4 
			|| isBuffEffectExist(FightBuff.EFFECT_封技)
			){
				select = null;
			}
			if(select == null){
				select = this.skill;
				if(isBuffEffectExist(FightBuff.EFFECT_冰冻)
				|| isBuffEffectExist(FightBuff.EFFECT_眩晕)){
					select = null;
				}
			}
		}while(false);
		return select;
	}
	
	/**
	 * 反击
	 * @param main
	 * @param tgt
	 */
	public FightSkillVO fightBack(FightMain main,Fighter tgt,List<Object> roundMsg){
		//TODO
		if(backSkill == null) return null;
		//tgt为格挡反击的默认目标， 计算反击的目标
		List<Fighter> targets = main.chooseTarget(this,backSkill.getData(),Arrays.asList(tgt));
		FightSkillVO skillVO = backSkill.triggerTarget(main, targets, null,roundMsg);
		return skillVO;
	}
	
	/**
	 * 触发神通
	 * @param node
	 * @param main
	 */
	public void triggerTalent(FightMain main,int node,List<FightTalentVO> listVO,List<Fighter> defaultTargets ,List<Fighter> missTargets){
		for (FightTalent talent : talents) {
			talent.triggerEffect(node, main, listVO ,defaultTargets ,missTargets);
		}
	}
	
	public FightTalentRescueResult triggerRescueTalent(FightMain main,Fighter old){
		FightTalentRescueResult result = null;
		if(rescueTalent != null){
			result = rescueTalent.effect(main,old);
		}
		return result;
	}
	
	/**
	 * buff生效
	 * @param main
	 * @param boutCnt
	 * @param isNew 是否是本回合新增的buff
	 */
	public void buffEffect(FightMain main,int boutCnt ,boolean isNew ,List<Object> roundMsgExp){
		boolean notAddLife = isBuffEffectExist(FightBuff.EFFECT_禁疗);
		Iterator<FightBuff> it =  getBuffs().iterator();
		while(it.hasNext()){
			FightBuff buff = it.next();
			Buff buffData = FightMain.dataManager.getValueByKey(Buff.class, buff.getId());
			//
			FightBuffVO buffVO = null;
			if((!isNew) && buff.getRound() == 0 && buffData.getIsNow() == 1) {
				buffVO = FightVOUtil.createBuffInfo(boutCnt,this,buff);
				roundMsgExp.add(buffVO);
				//===============移除buff============//
				buff.removeEffect();
				it.remove();
				Integer count = buffEffects.get(buff.getGroup());
				if(count != null && count>0){
					buffEffects.put(buff.getGroup(), count-1);
				}
				//==================================//
				continue;
			}
//			//禁疗：无法被治疗
//			if(buff.getEffect() == FightBuff.EFFECT_回血 &&notAddLife){
//				continue;
//			}
			//免疫buff
			if( buff.getEffect() == FightBuff.EFFECT_免疫buff ){
				continue;
			}
			//获得buff的初始回合数
			if( isNew ){
				if( !buff.isNew() || buffData.getIsNow() == 0){
					//不是新增的buff 不是及时生效的buff continue
					continue;
				}
			}
			if( buff.getKeys().size() == 0 && isNew){
				continue;
			}
			//增加属性
			for(int j = 0 ; j < buff.getKeys().size();j++){
				int key = buff.getKeys().get(j);
				int val = buff.getVals().get(j);
				if(key == FConstants.AttInd_Life){
					if(notAddLife && val > 0){
						val = (int)Math.round(val*Constants.battle_禁疗系数);
					}
					changeLife(val);
					continue;
				}
				addRndPropertyByAttInd(key, val);
			}
			if(isNew && buff.isNew()){
				buff.setNew(false);
			}
			buff.setRound(buff.getRound()-1);
			//生成message
			if(!isNew ){
				if(!(buff.getRound() == 0 && buffData.getIsNow() == 1)){
					buffVO = FightVOUtil.createBuffInfo(boutCnt,this,buff);
					roundMsgExp.add(buffVO);
				}
			}
			if(this.isDead()) {
				triggerTalent(main,FightTalentNode.自己死亡后,buffVO.getTal() ,Arrays.asList(this) ,null);
				break;
			}
		}
	}
	/**
	 * 替换格挡反击技能
	 * @param tBackSkill
	 */
	public void replaceBackSkill(FightSkill tBackSkill){
		backSkill = tBackSkill;
	}
	/**
	 * 计算生命 攻击 物防 法防等属性
	 */
	public void calcBase(){
		base[0] = (int)Math.round(base[0]*(1+getPropertyByAttInd(FConstants.AttInd_LifeR)*0.0001));
		double val = (50+lead[0])*0.01;
		double value = base[0]*val;
		base[0] = (int)Math.round(value);
		//初始化生命上限
		initLife = base[0];
	}
	public void changeInitLife(){
		initLife = base[0];
		if(lifeRate != -1){
			base[0] = (int)(lifeRate*0.0001*initLife);
		}
		logger.info("\n战斗初始属性: resId:"+getId() +" 位置:" + getPos()
				+" \nbase: "+ base[0]+","+getTmpAttack()+","+getTmpDefen()+","+getTmpDefenM()
				+" \nlead: "+lead[0]+","+lead[1]+","+lead[2]
				+" \nbaseRate: "+baseRate[0]+","+baseRate[1]+","+baseRate[2]+","+baseRate[3]
				+" \nbaseMod: "+baseMod[0]+","+baseMod[1]+","+baseMod[2]+","+baseMod[3]+","+baseMod[4]+","+baseMod[5]
				+" \nrare: "+rare[0]+","+rare[1]+","+rare[2]+","+rare[3]+","+rare[4]+","+rare[5]+","+rare[6]+","+rare[7]
				+" \nrareRate: "+rareRate[0]+","+rareRate[1]+","+rareRate[2]+","+rareRate[3]+","+rareRate[4]+","+rareRate[5]+","+rareRate[6]+","+rareRate[7]
				+" \nheal: "+heal[0]+","+heal[1]+","+heal[2]+","+heal[3]
				+" \nfinnal: "+finnal[0]+","+finnal[1]+","+finnal[2]+","+finnal[3]+","+finnal[3]
						);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public Card getData() {
		return data;
	}
	public void setData(Card data) {
		this.data = data;
	}
	public int getInitLife() {
		return initLife;
	}
	public void setInitLife(int initLife) {
		this.initLife = initLife;
	}
	public int getAngerVal() {
		return angerVal;
	}
	public void setAngerVal(int angerVal) {
		this.angerVal = angerVal;
		if(this.angerVal < 0) this.angerVal = 0;
	}
	public double getScale() {
		return scale;
	}
	public void setScale(double scale) {
		this.scale = scale;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Integer getState(int crtl) {
		return ctrlState[crtl];
	}

	public void setState(int ctrl) {
		this.ctrlState[ctrl] = 1;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	

	public List<Object> getRoundMsg() {
		return roundMsg;
	}

	public void setRoundMsg(List<Object> roundMsg) {
		this.roundMsg = roundMsg;
	}

	public boolean isForbitBuff() {
		return forbitBuff;
	}

	public void setForbitBuff(boolean forbitBuff) {
		this.forbitBuff = forbitBuff;
	}

	public Integer[] getBase() {
		return base;
	}

	public void setBase(Integer[] base) {
		this.base = base;
	}

	public List<FightBuff> getBuffs() {
		return buffs;
	}

	public void setBuffs(List<FightBuff> buffs) {
		this.buffs = buffs;
	}

	public int getSuperNatural() {
		return superNatural;
	}

	public void setSuperNatural(int superNatural) {
		this.superNatural = superNatural;
	}
}
