package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CActExchList implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	private Date start;
	private Date end;
	private Date now;
	
	
	public CActExchList(){
		list = new ArrayList<CActExchListItem>();
	}

	private List<CActExchListItem> list;
	public List<CActExchListItem> getList() {
		return list;
	}

	public void setList(List<CActExchListItem> list) {
		this.list = list;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}
}
