/**
 * 
 */
package com.mdy.dzs.game.fight.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.game.fight.main.FightCfg;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 战斗结果
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月8日  下午1:42:07
 */
public class FightResult {

	/**胜利方*/
	private int win;
	/**战斗过程*/
	private Map<String, Object> msg;
	/**统计数据*/
	private FightStatistics statistic;
	private Integer winState;
	
	
	
	public FightResult() {
		statistic = new FightStatistics();
	}
	
	public void statistic(FightMain main,FightCfg cfg){
		for (int i = 1; i <= 6; i++) {
			Fighter f = main.getF1().get(i);
			int fLife = f == null?0:f.getLife();
			statistic.getF1Lifes().put(i, fLife);
			
			f = main.getF2().get(i);
			fLife = f == null?0:f.getLife();
			statistic.getF2Lifes().put(i, fLife);
		}
		
		if(cfg.isCalcDSum()){
			int dSum = 0;
			for (Fighter fighter : main.getInitF2().values()) {
				if(fighter == null) continue;
				dSum+=fighter.getInitLife()-fighter.getLife();
			}
			statistic.setdSum(dSum);
		}
		if(cfg.isCalcDiePos()){
			Map<Integer, Fighter> idf1 = main.getInitF1();
			List<Integer> diePos1 = statistic.getDiePos()[0];
			for(Entry<Integer, Fighter> entry : idf1.entrySet()){
				Fighter fighter = entry.getValue();
				if(fighter == null || fighter.isDead()){
					diePos1.add(entry.getKey());
				}
			}
			Map<Integer, Fighter> idf2 = main.getInitF2();
			List<Integer> diePos2 = statistic.getDiePos()[1];
			for(Entry<Integer, Fighter> entry : idf2.entrySet()){
				Fighter fighter = entry.getValue();
				if(fighter == null || fighter.isDead()){
					diePos2.add(entry.getKey());
				}
			}
		}
		if(cfg.isCalcDieId()){
			Map<Integer, Fighter> idf1 = main.getInitF1();
			List<Integer> dieId1 = statistic.getDieIds()[0];
			for(Entry<Integer, Fighter> entry : idf1.entrySet()){
				Fighter fighter = entry.getValue();
				if(fighter == null || fighter.isDead()){
					dieId1.add(entry.getValue().getId());
				}
			}
			Map<Integer, Fighter> idf2 = main.getInitF2();
			List<Integer> dieId2 = statistic.getDieIds()[1];
			for(Entry<Integer, Fighter> entry : idf2.entrySet()){
				Fighter fighter = entry.getValue();
				if(fighter == null || fighter.isDead()){
					dieId2.add(entry.getValue().getId());
				}
			}
		}
		if( cfg.isCalcCard() ){
			boolean allDead = false;
			//30回合 仍无胜负
			if( main.getBoutCnt() == 30 && main.getWin() ==0 ){
				allDead = true;
				setWinState(3);
			}
			Map<Integer, Fighter> idf1 = main.getInitF1();
			List<FightEndCardVO> selfList = statistic.getSelfList();
			for(Entry<Integer, Fighter> entry : idf1.entrySet()){
				Fighter fighter = entry.getValue();
				FightEndCardVO selfVO = new FightEndCardVO();
				if(fighter == null || fighter.isDead() || allDead){
					selfVO.setLifeRate(0);
				} else {
					double life = fighter.getLife();
					double initLife = fighter.getInitLife();
					double lifeRate = life*10000/initLife;
					selfVO.setLifeRate((int)lifeRate);
				}
				selfVO.setAnger(fighter.getAngerVal());
				selfVO.setId(fighter.getId());
				selfVO.setPos(fighter.getPos());
				selfList.add(selfVO);
			}
			Map<Integer, Fighter> idf2 = main.getInitF2();
			List<FightEndCardVO> tgtList = statistic.getTgtList();
			for(Entry<Integer, Fighter> entry : idf2.entrySet()){
				Fighter fighter = entry.getValue();
				FightEndCardVO tgtVO = new FightEndCardVO();
				if(fighter == null || fighter.isDead() || allDead){
					tgtVO.setLifeRate(0);
				} else {
					double life = fighter.getLife();
					double initLife = fighter.getInitLife();
					double lifeRate = life*10000/initLife;
					tgtVO.setLifeRate((int)lifeRate);
				}
				tgtVO.setAnger(fighter.getAngerVal());
				tgtVO.setId(fighter.getId());
				tgtVO.setPos(fighter.getPos());
				tgtList.add(tgtVO);
			}
			
		}
	}
	
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		if(win == 0) win = 2;
		this.win = win;
	}
	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(List<Object> msg) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("m", 11);
		res.put("d", msg.toArray());

		res.put("f1", statistic.getF1Lifes());
		res.put("f2", statistic.getF2Lifes());
		this.msg = res;
	}
	public FightStatistics getStatistic() {
		return statistic;
	}
	public void setStatistic(FightStatistics statistic) {
		this.statistic = statistic;
	}

	public Integer getWinState() {
		return winState;
	}

	public void setWinState(Integer winState) {
		this.winState = winState;
	}
	
}
