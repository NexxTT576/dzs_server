package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

import com.mdy.dzs.game.domain.boss.SelfStateVO;

public class UnionBossPayVO implements Serializable {
	/*{	
		selfStat: 自身状态
				{  
					num:*, 			//攻击次数
					hurt:*, 		//总伤害
					hurtR:*, 		//总伤害比例
					rank:*,			//当前排名
					hurtAdd:*, 		//伤害加成
					silverWait:  	//银两鼓舞等待时间
					battleWait:  	//攻击等待时间
					nxtLiveGold:*,	//下次复活元宝数目
				}
		isFinish:	是否活动已结束	1-未结束 2-结束
		goldNum: 	当前元宝数
		silverNum：	当前银币数
		isSuccess: 	是否鼓舞成功    1-不成功 2-成功
	}*/
	private static final long serialVersionUID = 1L;
	
	private SelfStateVO selfStat;
	private int isFinish;
	private int goldNum;
	private int silverNum;
	private int isSuccess;
	
	public UnionBossPayVO(SelfStateVO selfStat, int isFinish, int goldNum, int silverNum, int isSuccess){
		this.selfStat = selfStat;
		this.isFinish = isFinish;
		this.goldNum = goldNum;
		this.silverNum = silverNum;
		this.isSuccess = isSuccess;
	}
	public SelfStateVO getSelfStat() {
		return selfStat;
	}
	public void setSelfStat(SelfStateVO selfStat) {
		this.selfStat = selfStat;
	}
	public int getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
	}
	public int getGoldNum() {
		return goldNum;
	}
	public void setGoldNum(int goldNum) {
		this.goldNum = goldNum;
	}
	public int getSilverNum() {
		return silverNum;
	}
	public void setSilverNum(int silverNum) {
		this.silverNum = silverNum;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	
}
