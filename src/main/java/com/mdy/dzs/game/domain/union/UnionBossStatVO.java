package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.boss.BossBubbleVO;
import com.mdy.dzs.game.domain.boss.BossStateVO;
import com.mdy.dzs.game.domain.boss.SelfStateVO;

public class UnionBossStatVO implements Serializable {
	/*{	
		stateObj:  boss战状态
				{	name:"",     //boss 名称
					level:*, 	 //boss等级
					life:*   	 //伤害血量
					lifeTotal:*  //当前总血量
					endTime:*	 //结束时间
				}
		selfStat: 自身状态
				{  
					num:*, 			//攻击次数
					hurt:*, 		//总伤害
					hurtR:*, 		//总伤害比例
					rank:*,			//当前排名
					hurtAdd:*, 		//伤害加成
					silverWait:*  	//银两鼓舞等待时间
					nxtLiveGold:*,	//下次复活元宝数目
					battleWait:*  	//攻击等待时间
				}
		playerStat:其他玩家boss战状态
				[{
					name:"",		//玩家昵称
					hurt:*,			//玩家攻击boss伤害值
					},
					....
				]
	}*/
	private static final long serialVersionUID = 1L;

	private BossStateVO 		stateObj;
	private SelfStateVO 		selfStat;
	private List<BossBubbleVO>  playerStat;
	
	public UnionBossStatVO(BossStateVO stateObj, SelfStateVO selfStat, List<BossBubbleVO> playerStat){
		this.stateObj 	= stateObj;
		this.selfStat 	= selfStat;
		this.playerStat = playerStat;
	};
	
	public BossStateVO getStateObj() {
		return stateObj;
	}
	public void setStateObj(BossStateVO stateObj) {
		this.stateObj = stateObj;
	}
	public SelfStateVO getSelfStat() {
		return selfStat;
	}
	public void setSelfStat(SelfStateVO selfStat) {
		this.selfStat = selfStat;
	}
	public List<BossBubbleVO> getPlayerStat() {
		return playerStat;
	}
	public void setPlayerStat(List<BossBubbleVO> playerStat) {
		this.playerStat = playerStat;
	}
	
}
