package com.mdy.dzs.game.fight.main;

/**
 * 战斗配置
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月25日  上午10:55:05
 */
public class FightCfg {

	//战斗最大回合数
	private int maxBout;
	//default:0		双方受到总伤害值
	private boolean calcDSum;
	//default:0		双方阵亡pos
	private boolean calcDiePos;
	
	private boolean calcDieId;
	//计算剩余生命比例 怒气值
	private boolean calcCard;
	//主卡位置
	private int sPos;
	//对方主卡位置
	private int dPos;

	//副本战f2对应上阵id
	private int npcid;
	
	//是否计算战斗力
	private boolean isCaclAttrack;
	
	public FightCfg() {
		maxBout = 30;
		calcDSum = false;
		calcDiePos = false;
		calcDieId = false;
		isCaclAttrack = false;
		sPos = 0;
		dPos = 0;
		npcid = 0;
	}
	
	public int getMaxBout() {
		return maxBout;
	}
	public void setMaxBout(int maxBout) {
		this.maxBout = maxBout;
	}
	public int getsPos() {
		return sPos;
	}
	public void setsPos(int sPos) {
		this.sPos = sPos;
	}
	public int getdPos() {
		return dPos;
	}
	public void setdPos(int dPos) {
		this.dPos = dPos;
	}
	public int getNpcid() {
		return npcid;
	}
	public void setNpcid(int npcid) {
		this.npcid = npcid;
	}		
	public boolean isCalcDSum() {
		return calcDSum;
	}

	public void setCalcDSum(boolean calcDSum) {
		this.calcDSum = calcDSum;
	}

	public boolean isCalcDiePos() {
		return calcDiePos;
	}

	public void setCalcDiePos(boolean calcDiePos) {
		this.calcDiePos = calcDiePos;
	}

	public boolean isCalcDieId() {
		return calcDieId;
	}

	public void setCalcDieId(boolean calcDieId) {
		this.calcDieId = calcDieId;
	}

	public boolean isCalcCard() {
		return calcCard;
	}

	public void setCalcCard(boolean calcCard) {
		this.calcCard = calcCard;
	}

	public boolean isCaclAttrack() {
		return isCaclAttrack;
	}

	public void setCaclAttrack(boolean isCaclAttrack) {
		this.isCaclAttrack = isCaclAttrack;
	}
	
	   
}
