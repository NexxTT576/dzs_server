package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.List;

public class CEnemyVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	/**挑战次数*/
	private int challengeTimes;
	
	private List<CTournamentEnemyVO> list;

	public int getChallengeTimes() {
		return challengeTimes;
	}

	public void setChallengeTimes(int challengeTimes) {
		this.challengeTimes = challengeTimes;
	}

	public List<CTournamentEnemyVO> getList() {
		return list;
	}

	public void setList(List<CTournamentEnemyVO> list) {
		this.list = list;
	}

}
