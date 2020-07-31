package com.mdy.dzs.game.domain.activity.mazegame;

import java.io.Serializable;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;



public class MazeRefreshVO implements Serializable{
	/*{
	isOtherDay:		是否要刷新页面	【1-要刷下页面  2-无需】
	treasuryMap：	宝库物品
					{
						"1":{type:*,id:*,num:*},
						"2":{type:*,id:*,num:*},
						...
					}
	gold:			剩余金币	
	digAllGold:		全部挖取花费 
	digOneGold:		挖一个花费
	}*/
	private static final long serialVersionUID = 1L;
	
	private int isOtherDay;
	private Map<String,ProbItem> treasuryMap;
	private int gold;
	private int digAllGold;
	private int digOneGold;
	
	public MazeRefreshVO(int isOtherDay, Map<String,ProbItem> treasuryMap, int gold, int digAllGold, int digOneGold){
		this.isOtherDay  = isOtherDay;
		this.treasuryMap = treasuryMap;
		this.gold 		 = gold;
		this.digAllGold	 = digAllGold;
		this.digOneGold  = digOneGold;
	}
	
	public Map<String,ProbItem> getTreasuryMap() {
		return treasuryMap;
	}
	public void setTreasuryMap(Map<String,ProbItem> treasuryMap) {
		this.treasuryMap = treasuryMap;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getIsOtherDay() {
		return isOtherDay;
	}

	public void setIsOtherDay(int isOtherDay) {
		this.isOtherDay = isOtherDay;
	}

	public int getDigAllGold() {
		return digAllGold;
	}

	public void setDigAllGold(int digAllGold) {
		this.digAllGold = digAllGold;
	}

	public int getDigOneGold() {
		return digOneGold;
	}

	public void setDigOneGold(int digOneGold) {
		this.digOneGold = digOneGold;
	}
}
