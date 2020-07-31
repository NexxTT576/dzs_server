package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CActExchExp implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;

	public CActExchExp(int id ){
		this.id = id;
		this.exchItem = new ArrayList<CActExchExpItem>();
		this.exchRst = new ArrayList<CActExchExpItem>();
	}
	/***/
	private int id;
	/**兑换材料*/
	private List<CActExchExpItem> exchItem;
	/**兑换结果*/
	private List<CActExchExpItem> exchRst;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public List<CActExchExpItem> getExchItem() {
		return exchItem;
	}
	public void setExchItem(List<CActExchExpItem> exchItem) {
		this.exchItem = exchItem;
	}
	public List<CActExchExpItem> getExchRst() {
		return exchRst;
	}
	public void setExchRst(List<CActExchExpItem> exchRst) {
		this.exchRst = exchRst;
	}
}
