package com.mdy.dzs.game.domain.broad;

import java.io.Serializable;
import java.util.List;

/**
 * 广播信息
 * @author Administrator
 *
 */
public class BroadInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private int type;
	private List<Integer> color;
	private int time;
	private String string;
	private List<BroadData> data;
	private int broadNum;
	
	public BroadInfo(Broadcast msg,int t,String str,List<BroadData> d,int num){
		id = msg.getStrId();
		type = msg.getType();
		color = msg.getColor();
		time = t;
		string = str;
		data = d;
		broadNum = num;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public List<BroadData> getData() {
		return data;
	}
	public void setData(List<BroadData> data) {
		this.data = data;
	}

	public int getBroadNum() {
		return broadNum;
	}

	public void setBroadNum(int broadNum) {
		this.broadNum = broadNum;
	}
	
}
