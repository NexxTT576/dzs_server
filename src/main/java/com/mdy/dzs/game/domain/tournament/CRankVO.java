package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.List;

public class CRankVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/**排行列表*/
	private List<CTournamentRankVO> list;
	/**自己的信息*/
	private RoleTournamentSimple self;
	
	public RoleTournamentSimple getSelf() {
		return self;
	}
	public void setSelf(RoleTournamentSimple self) {
		this.self = self;
	}
	public List<CTournamentRankVO> getList() {
		return list;
	}
	public void setList(List<CTournamentRankVO> list) {
		this.list = list;
	}
}
