package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;
/**
 * 公式中每个物品对应的数据
 * @author Administrator
 *
 */
public class CActExchExpItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public CActExchExpItem(int type,int id,int num){
		this.type = type;
		if( this.type == 0 ){
			this.type = 7;
		}
		this.id = id;
		this.num = num;
	}
	
	private int type;
	private int id;
	private int num;
	private int had;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getHad() {
		return had;
	}
	public void setHad(int had) {
		this.had = had;
	}
}
