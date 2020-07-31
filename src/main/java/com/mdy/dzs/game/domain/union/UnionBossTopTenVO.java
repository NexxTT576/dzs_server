package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.boss.BossTopTenVO;

public class UnionBossTopTenVO implements Serializable {
	/*{	
		topPlayers: 伤害排行榜玩家信息
					[{	rank:1,  //排名
						acc:*, 	 //账号
						name:*,  //名字
						hurt:*   //伤害血量
						lv:*	 //等级
					  },
					  ....
					]		
	}*/
	
	private static final long serialVersionUID = 1L;
	private List<BossTopTenVO> topPlayers;
	
	public UnionBossTopTenVO(){};
	public UnionBossTopTenVO(List<BossTopTenVO> topPlayers){
		this.topPlayers = topPlayers;
	};

	public List<BossTopTenVO> getTopPlayers() {
		return topPlayers;
	}

	public void setTopPlayers(List<BossTopTenVO> topPlayers) {
		this.topPlayers = topPlayers;
	}
}
