package com.mdy.dzs.game.domain.activity.roulettegame;

import java.io.Serializable;

public class RouletteStateVO implements Serializable{
	/*rouletteState:
	[
		{
		id:编号
		itemDisplay：显示物品
		}
	]*/
	private static final long serialVersionUID = 1L;
	private int id;
	private int itemDisplay;
	
	public RouletteStateVO(int id, int itemDisplay){
		this.id 		 = id;
		this.itemDisplay = itemDisplay;
	}
	
	public int getItemDisplay() {
		return itemDisplay;
	}
	public void setItemDisplay(int itemDisplay) {
		this.itemDisplay = itemDisplay;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
