package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;

public class UnionBossResultVO implements Serializable {
	/*{	
		res:  	活动结果
				{	kill:'',    //击杀者名称,为空时为未击杀
					hurt:*, 	//总攻击值
					rank:*,  	//伤害排名
					endTime:*	//结束时间
					bossLife：*	//boss当前血量
					lifeTotal:* //当前总血量
				}
		awardAry:	奖励物品数组,[{type:*, id:*, num:*},...]
		isFinish:	是否已经结束  1-未结束 2-结束
	}*/
	private static final long serialVersionUID = 1L;
	
	private UnionBossPveLastResultVO res;
	private List<ProbItem> awardAry;
	private int isFinish;
	
	public UnionBossResultVO(UnionBossPveLastResultVO res, List<ProbItem> awardAry, int isFinish){
		this.res 	  = res;
		this.awardAry = awardAry;
		this.isFinish = isFinish;
	}
	
	
	public UnionBossPveLastResultVO getRes() {
		return res;
	}
	public void setRes(UnionBossPveLastResultVO res) {
		this.res = res;
	}
	public List<ProbItem> getAwardAry() {
		return awardAry;
	}
	public void setAwardAry(List<ProbItem> awardAry) {
		this.awardAry = awardAry;
	}
	public int getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
	}
}
