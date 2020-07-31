package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

public class ShowHurtListVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3444988575879067727L;
	private List<HurtVO> hurtList;
	public List<HurtVO> getHurtList() {
		return hurtList;
	}
	public void setHurtList(List<HurtVO> hurtList) {
		this.hurtList = hurtList;
	}

}
