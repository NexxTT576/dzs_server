package com.mdy.dzs.game.domain.boss;

import java.io.Serializable;

public class SelfStateVO implements Serializable{
	/**
	 * boss战玩家自己状态
	 * {  
			num:*, 攻击次数
			hurt:*, 总伤害
			hurtR:*, 总伤害比例
			rank:*,		当前排名
			hurtAdd:*, 伤害加成
			silverWait:  银两鼓舞等待时间
			nxtLiveGold:*,	下次复活元宝数目
			battleWait:  攻击等待时间
			curHurt:*, 本次伤害值
		}
	 * */
	
	private static final long serialVersionUID = 1L;
	//攻击次数
	private int num;
	//总伤害
	private long hurt;
	//总伤害比例
	private int hurtR;
	//当前排名
	private int rank;
	//伤害加成
	private int hurtAdd;
	//银两鼓舞等待时间
	private int silverWait;
	//下次复活元宝数目
	private int nxtLiveGold;
	//攻击等待时间
	private int battleWait;
	//本次伤害值
	private int curHurt;
	
	//无参数
	public SelfStateVO(){}
	
	//无本次伤害值
	public SelfStateVO(int num,long hurt,int hurtR,int rank,int hurtAdd,int silverWait,int nxtLiveGold,int battleWait){
		this.num 		 = num;
		this.hurt 		 = hurt;
		this.hurtR 		 = hurtR;
		this.rank 		 = rank;
		this.hurtAdd 	 = hurtAdd;
		this.silverWait  = silverWait;
		this.nxtLiveGold = nxtLiveGold;
		this.battleWait  = battleWait;
	}
	//有本次伤害值
	public SelfStateVO(int num,long hurt,int hurtR,int rank,int hurtAdd,int silverWait,int nxtLiveGold,int battleWait,int curHurt){
		this.num 		 = num;
		this.hurt 		 = hurt;
		this.hurtR 		 = hurtR;
		this.rank 		 = rank;
		this.hurtAdd 	 = hurtAdd;
		this.silverWait  = silverWait;
		this.nxtLiveGold = nxtLiveGold;
		this.battleWait  = battleWait;
		this.curHurt 	 = curHurt;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public long getHurt() {
		return hurt;
	}
	public void setHurt(long hurt) {
		this.hurt = hurt;
	}
	public int getHurtR() {
		return hurtR;
	}
	public void setHurtR(int hurtR) {
		this.hurtR = hurtR;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getHurtAdd() {
		return hurtAdd;
	}
	public void setHurtAdd(int hurtAdd) {
		this.hurtAdd = hurtAdd;
	}
	public int getSilverWait() {
		return silverWait;
	}
	public void setSilverWait(int silverWait) {
		this.silverWait = silverWait;
	}
	public int getNxtLiveGold() {
		return nxtLiveGold;
	}
	public void setNxtLiveGold(int nxtLiveGold) {
		this.nxtLiveGold = nxtLiveGold;
	}
	public int getBattleWait() {
		return battleWait;
	}
	public void setBattleWait(int battleWait) {
		this.battleWait = battleWait;
	}
	public int getCurHurt() {
		return curHurt;
	}
	public void setCurHurt(int curHurt) {
		this.curHurt = curHurt;
	}
	
	
	
}
