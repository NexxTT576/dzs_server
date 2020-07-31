package com.mdy.dzs.game.domain.dart;

import java.io.Serializable;

public class DartLineUpVO implements Serializable{
	/*{
	 resId:*,
	 cls：*
	}*/
		
	private static final long serialVersionUID = 1L;
	
	private int resId;
	private int cls;
	
	public DartLineUpVO(int resId, int cls){
		this.resId = resId;
		this.cls   = cls;
	}
	
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
}
