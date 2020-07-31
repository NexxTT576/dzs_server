/**
 * 
 */
package com.mdy.dzs.game.fight.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 战斗统计数据
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月8日  下午1:43:17
 */
public class FightStatistics {

	/**对方伤害总计*/
	private int dSum;
	/**死亡位置*/
	private List<Integer> diePos[];
	/**死亡id*/
	private List<Integer> dieIds[];
	/**己方剩余的卡的血量等数据*/
	private List<FightEndCardVO> selfList;
	/**己方剩余的卡的血量等数据*/
	private List<FightEndCardVO> tgtList;

	private Map<Integer,Integer> f1Lifes;
	private Map<Integer, Integer> f2Lifes;

	@SuppressWarnings("unchecked")
	public FightStatistics() {
		diePos = new ArrayList[2];
		diePos[0] = new ArrayList<Integer>();
		diePos[1] = new ArrayList<Integer>();
		dieIds = new ArrayList[2];
		dieIds[0] = new ArrayList<Integer>();
		dieIds[1] = new ArrayList<Integer>();
		selfList = new ArrayList<FightEndCardVO>();
		tgtList = new ArrayList<FightEndCardVO>();
		f1Lifes = new HashMap<Integer, Integer>();
		f2Lifes= new HashMap<Integer, Integer>();
	}

	public int getdSum() {
		return dSum;
	}

	public void setdSum(int dSum) {
		this.dSum = dSum;
	}
	
	public List<Integer>[] getDiePos() {
		return diePos;
	}

	public void setdSum(List<Integer> diePos[]) {
		this.diePos = diePos;
	}
	
	
	public List<Integer>[] getDieIds() {
		return dieIds;
	}

	public void setDieIds(List<Integer>[] dieIds) {
		this.dieIds = dieIds;
	}

	public List<FightEndCardVO> getSelfList() {
		return selfList;
	}

	public void setSelfList(List<FightEndCardVO> selfList) {
		this.selfList = selfList;
	}

	public List<FightEndCardVO> getTgtList() {
		return tgtList;
	}

	public void setTgtList(List<FightEndCardVO> tgtList) {
		this.tgtList = tgtList;
	}

	public Map<Integer,Integer> getF1Lifes() {
		return f1Lifes;
	}
	public Map<Integer,Integer> getF2Lifes() {
		return f2Lifes;
	}
}
