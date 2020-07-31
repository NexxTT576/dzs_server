package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

public class EnterWVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8299063615450015208L;
	private int jopType;
	private int unionCurrentMoney;
	private int lastContribute;
	private List<EnterWelfareVO> welfList;
	public List<EnterWelfareVO> getWelfList() {
		return welfList;
	}
	public void setWelfList(List<EnterWelfareVO> welfList) {
		this.welfList = welfList;
	}
	public int getJopType() {
		return jopType;
	}
	public void setJopType(int jopType) {
		this.jopType = jopType;
	}
	public int getUnionCurrentMoney() {
		return unionCurrentMoney;
	}
	public void setUnionCurrentMoney(int unionCurrentMoney) {
		this.unionCurrentMoney = unionCurrentMoney;
	}
	public int getLastContribute() {
		return lastContribute;
	}
	public void setLastContribute(int lastContribute) {
		this.lastContribute = lastContribute;
	}
	
	
}
