package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

public class ShowDynamicVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3309243876304016172L;
	/**
	 * 
	 */
	private List<DynamicVO> dynamicList;
	private int jopType;
	
	public List<DynamicVO> getDynamicList() {
		return dynamicList;
	}
	public void setDynamicList(List<DynamicVO> dynamicList) {
		this.dynamicList = dynamicList;
	}
	public int getJopType() {
		return jopType;
	}
	public void setJopType(int jopType) {
		this.jopType = jopType;
	}
	
}
