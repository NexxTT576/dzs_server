package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.boss.SelfStateVO;

public class UnionBossPveVO implements Serializable {
	/*{	
		selfStat: 	自身状态
					{  
						num:*, 攻击次数
						curHurt:*, 本次伤害值
						hurt:*, 总伤害
						hurtR:*, 总伤害比例
						rank:*,		当前排名
						hurtAdd:*, 伤害加成
						silverWait:  银两鼓舞等待时间
						battleWait:  攻击等待时间
						nxtLiveGold:*,	下次复活元宝数目
					}
		rstAry:		战斗结果数组[1成功/2失败]
		recAry:  	战斗过程动画数组[recs]
		awardAry:	奖励物品数组,[{type:*, id:*, num:*},...]	
		waitTime:	距本次战斗时间
		isFinish:   是否活动已结束	1-未结束 2-结束
	}*/
	private static final long serialVersionUID = 1L;
	
	private SelfStateVO selfStat;
	private List<Integer> rstAry;
	private List<Map<String, Object>> recAry;
	private List<ProbItem> awardAry;
	private int waitTime;
	private int isFinish;
	
	public UnionBossPveVO(SelfStateVO selfStat,List<Integer> rstAry,List<Map<String, Object>> list, List<ProbItem> awardAry,int waitTime, int isFinish){
		this.selfStat = selfStat;
		this.rstAry   = rstAry;
		this.recAry   = list;
		this.awardAry = awardAry;
		this.waitTime = waitTime;
		this.isFinish = isFinish;
	}
	
	public SelfStateVO getSelfStat() {
		return selfStat;
	}
	public void setSelfStat(SelfStateVO selfStat) {
		this.selfStat = selfStat;
	}
	public List<Integer> getRstAry() {
		return rstAry;
	}
	public void setRstAry(List<Integer> rstAry) {
		this.rstAry = rstAry;
	}
	public List<Map<String, Object>> getRecAry() {
		return recAry;
	}
	public void setRecAry(List<Map<String, Object>> recAry) {
		this.recAry = recAry;
	}
	public List<ProbItem> getAwardAry() {
		return awardAry;
	}
	public void setAwardAry(List<ProbItem> awardAry) {
		this.awardAry = awardAry;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public int getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
	}
	
}
