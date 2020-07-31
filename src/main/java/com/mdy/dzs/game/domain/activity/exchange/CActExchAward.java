package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CActExchAward implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	public CActExchAward(){
		list = new ArrayList<CActExchAwardItem>();
	}
	private List<CActExchAwardItem> list;

	public List<CActExchAwardItem> getList() {
		return list;
	}

	public void setList(List<CActExchAwardItem> list) {
		this.list = list;
	}

}
