package com.mdy.dzs.game.domain.broad;

import java.io.Serializable;
import java.util.List;

public class BroadData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String value;
	private int star;
	private int type;
	private List<Integer> color;
	public BroadData(){
	}

	public BroadData(String v,int s,int t){
		value = v;
		star = s;
		type = t;
	}
	
	public BroadData(String v,int s,List<Integer> c){
		value = v;
		star = s;
		setColor(c);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Integer> getColor() {
		return color;
	}

	public void setColor(List<Integer> color) {
		this.color = color;
	}
}
