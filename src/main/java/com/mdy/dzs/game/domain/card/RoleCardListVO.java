package com.mdy.dzs.game.domain.card;

import java.io.Serializable;
import java.util.List;

public class RoleCardListVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RoleCard> list;
	private int limit;
	public List<RoleCard> getList() {
		return list;
	}
	public void setList(List<RoleCard> list) {
		this.list = list;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
