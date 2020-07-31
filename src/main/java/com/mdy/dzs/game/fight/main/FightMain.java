/**
 * 
 */
package com.mdy.dzs.game.fight.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.battle.BattleSkill;
import com.mdy.dzs.data.domain.battle.Npc;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.RoleLineupAid.RoleLineupCardAid;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.swordfight.SwordRoleCard;
import com.mdy.dzs.game.fight.domain.FightEndVO;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.factory.choosetarget.SkillChooseTarget;
import com.mdy.dzs.game.fight.main.fighttalent.FightTalentNode;
import com.mdy.dzs.game.fight.util.FightVOUtil;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月25日  上午10:42:20
 */
public class FightMain {
	
	final static Logger logger=LoggerFactory.get(FightMain.class);
	
//	[{'idx':61,'val':1000},{'idx':62 ,'val':1000}]
	/** 发起方战斗位置映射map*/
	private Map<Integer, Fighter> f1;
	/** 发起方战斗位置映射idmap*/
	private Map<Integer, Fighter> initf1;
	/** 接受方战斗位置映射map*/
	private Map<Integer, Fighter> f2;
	/** 接受方战斗位置映射idmap*/
	private Map<Integer, Fighter> initf2;
	/**战斗配置*/
	private FightCfg fightCfg;
	/** 发起方主卡位置*/
	private int f1MainPos = 0;
	/** 接受方主卡位置*/
	private int f2MainPos = 0;
	/** 回合数*/
	private int boutCnt = 0;
	
	private List<Fighter> reduses;

	/**返回的消息*/
	private List<Object> msg;
	/** 优先攻击方*/
	private int attackSide;
	private int win = 0;
	/**攻击方战斗力*/
	private int srcAttrack;
	/**被攻击方战斗力*/
	private int tgtAttrack;
	
	public static CacheManager dataManager;
	
	public SkillChooseTarget chooseTarget;
	/**
	 * 玩家打npc
	 * @param srcCards 6位卡片组 无卡片null占位
	 * @param dstCards
	 * @param srcFmt 6位加成组
	 * @param fightCfg
	 * @throws BizException 
	 */
	public FightMain(
			List<Object> srcCards,
			List<Object> dstCards,
			List<List<Prop>> srcProps,
			FightCfg fightCfg
			) throws BizException {
		List<Fighter> srcFighter = new ArrayList<Fighter>();
		for (int i = 0; i < 6; i++) {
			Object rcard = srcCards.get(i);
			if(rcard == null) continue;
			if(rcard instanceof RoleCard){
				srcFighter.add(new Fighter((RoleCard)rcard,srcProps.get(i),1,i+1));
			}else{
				srcFighter.add(new Fighter((Card)rcard,1,i+1));
			}
		}
		List<Fighter> dstFighter = new ArrayList<Fighter>();
		for (int i = 0; i < 6; i++) {
			Object card = dstCards.get(i);
			if(card == null) continue;
			if(card instanceof RoleCard){
				dstFighter.add(new Fighter((RoleCard)card,null,2,i+1));
			}else{
				dstFighter.add(new Fighter((Card)card,2,i+1));
			}
			
		}
		init(srcFighter, dstFighter, fightCfg==null?new FightCfg():fightCfg);
	}
	
//	
//	public FightMain(
//			List<Card> srcCards,
//			List<Card> dstCards,
//			FightCfg fightCfg
//			) throws BizException {
//		List<Fighter> srcFighter = new ArrayList<Fighter>();
//		for (int i = 0; i < 6; i++) {
//			Card card = dstCards.get(i);
//			if(card == null) continue;
//			srcFighter.add(new Fighter(card,2,i));
//		}
//		List<Fighter> dstFighter = new ArrayList<Fighter>();
//		for (int i = 0; i < 6; i++) {
//			Card card = dstCards.get(i);
//			if(card == null) continue;
//			dstFighter.add(new Fighter(card,2,i));
//		}
//		init(srcFighter, dstFighter, fightCfg==null?new FightCfg():fightCfg);
//	}
//	
	/**
	 * 玩家打玩家
	 * @param srcCards
	 * @param fmts1
	 * @param props1
	 * @param dstCards
	 * @param fmts2
	 * @param props2
	 * @param cfg
	 * @throws BizException
	 */
	public FightMain(
			List<RoleCard> srcCards,List<List<Prop>> props1,
			List<RoleCard> dstCards,List<List<Prop>> props2,
			 FightCfg cfg)  throws BizException {
			List<Fighter> srcFighter = new ArrayList<Fighter>();
			for (int i = 0; i < 6; i++) {
				RoleCard rcard = srcCards.get(i);
				if(rcard == null) continue;
				srcFighter.add(new Fighter(rcard,props1.get(i),1,i+1));
			}
			List<Fighter> dstFighter = new ArrayList<Fighter>();
			for (int i = 0; i < 6; i++) {
				RoleCard rcard = dstCards.get(i);
				if(rcard == null) continue;
				dstFighter.add(new Fighter(rcard,props2.get(i),2,i+1));
				
			}
			init(srcFighter, dstFighter, fightCfg==null?new FightCfg():fightCfg);
	}
	/**
	 * 玩家夺宝
	 * @param srcInfo
	 * @param tgtInfo
	 * @param fightCfg
	 * @throws BizException
	 */
	public FightMain(
			FighterInfo srcInfo,
			FighterInfo tgtInfo,
			FightCfg fightCfg
			) throws BizException {
		srcAttrack = srcInfo.getAttrack();
		tgtAttrack = tgtInfo.getAttrack();
		List<Fighter> srcFighter = new ArrayList<Fighter>();
		for (int i = 0; i < 6; i++) {
			Object rcard = srcInfo.getCards().get(i);
			if(rcard == null) continue;
			Fighter sFighter;
			if(rcard instanceof RoleCard){
				sFighter = new Fighter((RoleCard)rcard,srcInfo.getProps().get(i),1,i+1);
				sFighter.setForbitBuff(srcInfo.isForbitBuff());
			}else if(rcard instanceof SwordRoleCard){
				sFighter = new Fighter((SwordRoleCard)rcard,srcInfo.getProps().get(i),1,i+1);
			}else if(rcard instanceof RoleLineupCardAid){
				sFighter = new Fighter((RoleLineupCardAid)rcard,srcInfo.getProps().get(i),1,i+1);
			}else{
				sFighter = new Fighter((Card)rcard,1,i+1);
				sFighter.setForbitBuff(srcInfo.isForbitBuff());
			}
			srcFighter.add(sFighter);
		}
		List<Fighter> dstFighter = new ArrayList<Fighter>();
		for (int i = 0; i < 6; i++) {
			Object rcard = tgtInfo.getCards().get(i);
			if(rcard == null) continue;
			Fighter dFighter;
			if(rcard instanceof RoleCard){
				dFighter = new Fighter((RoleCard)rcard,tgtInfo.getProps().get(i),2,i+1);
				dFighter.setForbitBuff(tgtInfo.isForbitBuff());
			}else if(rcard instanceof SwordRoleCard){
				dFighter = new Fighter((SwordRoleCard)rcard,tgtInfo.getProps().get(i),2,i+1);
			}else{
				dFighter = new Fighter((Card)rcard,2,i+1);
				dFighter.setForbitBuff(tgtInfo.isForbitBuff());
			}
			dstFighter.add(dFighter);
		}
		fightCfg = fightCfg == null?new FightCfg():fightCfg;
		fightCfg.setsPos(srcInfo.getPos());
		fightCfg.setdPos(tgtInfo.getPos());
		
		init(srcFighter, dstFighter, fightCfg);
	}

	public FightMain(FighterInfo srcInfo,
			List<Object> dstCards,
			FightCfg fightCfg) throws BizException{

			List<Fighter> srcFighter = new ArrayList<Fighter>();
			for (int i = 0; i < 6; i++) {
				Object rcard = srcInfo.getCards().get(i);
				if(rcard == null) continue;
				if(rcard instanceof RoleCard){
					srcFighter.add(new Fighter((RoleCard)rcard,srcInfo.getProps().get(i),1,i+1));
				}else{
					srcFighter.add(new Fighter((Card)rcard,1,i+1));
				}
			}
			List<Fighter> dstFighter = new ArrayList<Fighter>();
			for (int i = 0; i < 6; i++) {
				Object card = dstCards.get(i);
				if(card == null) continue;
				if(card instanceof RoleCard){
					dstFighter.add(new Fighter((RoleCard)card,null,2,i+1));
				}else{
					dstFighter.add(new Fighter((Card)card,2,i+1));
				}
				
			}
			fightCfg = fightCfg == null?new FightCfg():fightCfg;
			fightCfg.setsPos(srcInfo.getPos());
			
			init(srcFighter, dstFighter, fightCfg);
		}

	public void init(List<Fighter> srcFighter,List<Fighter> dstFighter,FightCfg fightCfg){
		//init pos=>fighter
		this.f1 = new HashMap<Integer, Fighter>();
		this.initf1 = new HashMap<Integer, Fighter>();
		for (Fighter fighter : srcFighter) {
			f1.put(fighter.getPos(), fighter);
			initf1.put(fighter.getPos(), fighter);
		}
		this.f2 = new HashMap<Integer, Fighter>();
		this.initf2 = new HashMap<Integer, Fighter>();
		for (Fighter fighter : dstFighter) {
			f2.put(fighter.getPos(), fighter);
			initf2.put(fighter.getPos(), fighter);
		}
		
		//init battleCfg
		this.fightCfg = fightCfg;
		//配置pos必须经过构造初始化好
		this.f1MainPos = fightCfg.getsPos();
		this.f2MainPos = fightCfg.getdPos();	
		
		if(fightCfg.getNpcid() != 0){
			Npc npcData = dataManager.getValueByKey(Npc.class, fightCfg.getNpcid());
			if(npcData != null){
				for(int i=0; i<npcData.getPos().size(); i++){
					int pos = npcData.getPos().get(i);
					float scale = npcData.getScale().get(i);
					Fighter fighter = f2.get(pos);
					if(fighter != null) fighter.setScale(scale);
				}
			}
		}
		chooseTarget = new SkillChooseTarget();
		//init msg
		msg = new ArrayList<Object>();
	}
		
	
	
	FightEndVO getFightEndVO(){
		FightEndVO vo = new FightEndVO();
		vo.setN(boutCnt);
		vo.setWin(getWin());
		return vo;
	}
	
	public FightResult fight(){
		
		//上神通被动永久buff
		for (Fighter f : f1.values()) {
			if(f!=null){
				f.triggerTalent(this, FightTalentNode.永久被动技能, null ,null ,null);
				//添加神通属性之前 再次修改生命上限
				f.changeInitLife();
//				f.triggerTalent(this, FightTalentNode.我格挡后, null,null);
				f.resetTemps();
			}	
		}
		for (Fighter f : f2.values()) { 
			if(f!=null){ 
				f.triggerTalent(this, FightTalentNode.永久被动技能, null,null,null);
				//添加神通属性之前 再次修改生命上限
				f.changeInitLife();
//				f.triggerTalent(this, FightTalentNode.我格挡后, null,null);
				f.resetTemps();
			}
		}
		//战斗初始化
		msg.add(FightVOUtil.createInitVO(f1,f2));
		//根据先手值计算出手方
		calcAttackSide();
		//开始最大maxbout回合战斗
		for(int i=0;i<fightCfg.getMaxBout();i++){
			this.boutCnt++;
			reduses = new ArrayList<Fighter>();
			//计算出手队列
//			List<Fighter> queue = calcAttackQueue();
//			for (Fighter fighter : queue) {
//				if(fighter.isDead()) continue;
//				msg.addAll(fighter.attack(this));
//				//一方全部死亡
//				win = checkEnd();
//				if(win != 0) {
//					break;
//				}
//			}
			int f1cnt = 1;
			int f2cnt = 1;
			int side = attackSide;
			Fighter fighter = null;
			while(f1cnt<=6 || f2cnt <=6){
				fighter = null;
				if(side == 1){
					while(true){
						if(f1cnt> 6) break;
						fighter = f1.get(f1cnt);
						f1cnt++;
						if(!(fighter == null || fighter.isDead())){
							break;
						}
					}
				}else{
					while(true){
						if(f2cnt> 6) break;
						fighter = f2.get(f2cnt);
						f2cnt++;
						if(!(fighter == null || fighter.isDead())){
							break;
						}
					}
				}
				if(fighter != null){
					List<Object> roundMsg = fighter.attack(this);
					//=====================================buff生效
					//本回合新加的buff 进行生效
					Fighter fighter1 = null;
					Fighter fighter2 = null;
					int fightCnt1 = 1;
					int fightCnt2 = 1;
					while(true){
						if(fightCnt1> 6) break;
						fighter1 = f1.get(fightCnt1);
						fightCnt1++;
						if(fighter1 == null || fighter1.isDead()){
							continue;
						}
						if( fighter1.getBuffs().size() >0 ){
							fighter1.buffEffect(this,boutCnt ,true ,roundMsg);
						}
					}
					
					while(true){
						if(fightCnt2> 6) break;
						fighter2 = f2.get(fightCnt2);
						fightCnt2++;
						if(fighter2 == null || fighter2.isDead()){
							continue;
						}
						if( fighter2.getBuffs().size()>0 ){
							fighter2.buffEffect(this,boutCnt ,true ,roundMsg);
						}
					}
					//==========================================
					msg.addAll(roundMsg);
					//一方全部死亡
					setWin(checkEnd());
					if(getWin() != 0) {
						break;
					}
				}
				side = side == 1?2:1;
			}
			if(getWin()  != 0) break;
		}
			//生成结果
		msg.add(getFightEndVO());
		
		FightResult result = new FightResult();
		result.setWin(getWin());
		result.setMsg(msg);
		result.statistic(this, fightCfg);
		return result;
	}
	
	private void calcAttackSide(){
		if(fightCfg.isCaclAttrack()){
			//计算战斗力
			if( srcAttrack >= tgtAttrack){
				attackSide =  1;
			}
			else{
				attackSide =  2;
			}
		}else{
			attackSide =  1;//speed1>=speed2?1:2;
		}
	}
	
	private List<Fighter> calcAttackQueue(){
		//一个插一个
		List<Fighter> res = new ArrayList<Fighter>();
		Map<Integer, Fighter> attack1 = attackSide==1?f1:f2;
		Map<Integer, Fighter> attack2 = attackSide==1?f2:f1;
		Fighter tmp = null;
		int f1cnt = 1;
		int f2cnt = 1;
		for(int i = 1;i<=6;i++){
			do{
				tmp = attack1.get(f1cnt);
				if(tmp != null && !tmp.isDead()){
					res.add(tmp);
				}
				f1cnt++;
			}while(f1cnt<=6 &&(tmp == null || tmp.isDead()));
			do{
				tmp = attack2.get(f2cnt);
				if(tmp != null && !tmp.isDead()){
					res.add(tmp);
				}
				f2cnt++;
			}while(f2cnt<=6 &&(tmp == null || tmp.isDead()));
		}
		
		return res;
	}
	
	/**
	 * 检查是否已经一方死亡 并移除已死掉的对象
	 * @return 返回胜利方
	 */
	private int checkEnd(){
		boolean f1Live = false;
		boolean f2Live = false;
		Iterator<Fighter> it1 = f1.values().iterator();
		while(it1.hasNext()) {
		   Fighter fighter = it1.next();
		   if(fighter == null) continue;
		   if(!fighter.isDead()){
			   f1Live = true;
		   }else{
			   it1.remove();
		   }
		}
		Iterator<Fighter> it2 = f2.values().iterator();
		while(it2.hasNext()) {
		   Fighter fighter = it2.next();
		   if(fighter == null) continue;
		   if(!fighter.isDead()){
			   f2Live = true;
		   }else{
			   it2.remove();
		   }
		}
		if(f2Live == false){
			return 1;
		}
		if(f1Live ==false){
			return 2;
		}
		return 0;
	}
	
	
	public int getBoutCnt() {
		return boutCnt;
	}



	public void setBoutCnt(int boutCnt) {
		this.boutCnt = boutCnt;
	}



	public Map<Integer, Fighter> getF1() {
		return f1;
	}



	public void setF1(Map<Integer, Fighter> f1) {
		this.f1 = f1;
	}



	public Map<Integer, Fighter> getF2() {
		return f2;
	}



	public void setF2(Map<Integer, Fighter> f2) {
		this.f2 = f2;
	}



	public int getF1MainPos() {
		return f1MainPos;
	}



	public void setF1MainPos(int f1MainPos) {
		this.f1MainPos = f1MainPos;
	}



	public int getF2MainPos() {
		return f2MainPos;
	}



	public void setF2MainPos(int f2MainPos) {
		this.f2MainPos = f2MainPos;
	}
	
	
	public Map<Integer, Fighter> getInitF1() {
		return initf1;
	}

	public void setInitF1(Map<Integer, Fighter> initf1) {
		this.initf1 = initf1;
	}

	public Map<Integer, Fighter> getInitF2() {
		return initf2;
	}

	public void setInitF2(Map<Integer, Fighter> initf2) {
		this.initf2 = initf2;
	}

	public static void main(String[] args) {


//		FightInitVO initVO = new FightInitVO();
//		
//		FightInitCardVO initCardVO1 = new FightInitCardVO();
//		initCardVO1.setId(1);
//		initCardVO1.setLife(100);
//		initCardVO1.setPos(2);
//		initCardVO1.setStar(3);
//		initCardVO1.setAnger(2);
//		initCardVO1.setScale(1.0f);
//		initVO.getF1().add(initCardVO1);
//		
//		FightInitCardVO initCardVO2 = new FightInitCardVO();
//		initCardVO2.setId(2);
//		initCardVO2.setLife(100);
//		initCardVO2.setPos(2);
//		initCardVO2.setStar(3);
//		initCardVO2.setAnger(2);
//		initCardVO2.setScale(1.0f);
//		initVO.getF2().add(initCardVO2);
//		rst.add(initVO);
//		
//		int n = 20;
//		for(int i = 0;i<n;i++){
//			FightSkillVO skillVO1 = new FightSkillVO();
//			skillVO1.setN(i/2+1);
//			skillVO1.setS(i%2==0?1:2);
//			skillVO1.setP(2);
//			skillVO1.setUse(1);
//			skillVO1.setSkill(1090+i);
//			
//			FightSkillProcessVO processVO1 = new FightSkillProcessVO();
//			processVO1.setS(i%2==0?2:1);
//			processVO1.setP(2);
//			processVO1.setD(10);
//			processVO1.setL(10);
//			skillVO1.getTr().add(processVO1);
//			
//			FightSkillBuffVO skillBuffVO = new FightSkillBuffVO();
//			skillBuffVO.setS(i%2==0?2:1);
//			skillBuffVO.setP(2);
//			skillBuffVO.setB(i+1);
//			skillVO1.getBuff().add(skillBuffVO);
//			rst.add(skillVO1);
//			
//			FightBuffVO buffVO = new FightBuffVO();
//			buffVO.setS(i%2==0?2:1);
//			buffVO.setN(1);
//			buffVO.setP(2);
//			buffVO.setB(i%2+1);
//			buffVO.getK().add(FConstants.Att_Attack);
//			buffVO.getV().add(100+i);
//			buffVO.setEff(1);
//			rst.add(buffVO);
//		}
//		
//		FightEndVO endVO = new FightEndVO();
//		endVO.setN((n-1)/2+1);
//		endVO.setWin(FightSide.发起方.code);
//		rst.add(endVO);
		
		

//		List<Card> c1 = new ArrayList<Card>();
//		List<Card> c2 = new ArrayList<Card>();
//		Map<Integer, Card> cardMap= cacheManager().getValues(Card.class);
//		for(int i=0;i<6;i++){
//			c1.add(cardMap.get(1));
//			c2.add(cardMap.get(2));
//		}
//		FightMain main = new FightMain(c1, c2, null);
//		rst = main.fight();
			
		
	}

	////////////////////////////////////////////////////////////////////////////////////
	public List<Fighter> chooseTarget(Fighter fighter,BattleSkill skillData ,List<Fighter> defaultTargets){
		return chooseTarget.chooseTarget(this, fighter, skillData, defaultTargets);
	}
	
	public Fighter chooseTarget(Fighter fighter,List<Fighter> defaultTargets){
		return chooseTarget.chooseTarget(this, fighter, defaultTargets);
	}
	
	public List<Fighter> chooseTarget(Fighter fighter,int type,int parm,List<Fighter> defaultTargets){
		return chooseTarget.chooseTarget(this, fighter, type,parm, defaultTargets);
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	public  List<Fighter> getReduses() {
		return reduses;
	}

	public void setReduses(List<Fighter> reduses) {
		this.reduses = reduses;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}
}
