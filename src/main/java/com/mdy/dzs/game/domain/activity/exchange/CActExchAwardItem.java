package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;

public class CActExchAwardItem implements Serializable{

	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	public CActExchAwardItem(int id, String tagName ,String view){
		this.id = id;
		this.tagName = tagName;
		this.view = view;
	}
	/***/
	private int id;
	/**标签名*/
	private String tagName;
	/**兑换预览*/
	private String view;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}
