package com.mdy.dzs.game.domain.activity.mazegame;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.packet.PacketExtend;

public class DigVO implements Serializable{
	/*{
		isOtherDay:		是否要刷新页面	【1-要刷下页面  2-无需】
		treasuryMap：	宝库物品
						{
							"1":{type:*,id:*,num:*},
							"2":{type:*,id:*,num:*},
							...
						}
		gold:			剩余金币	
		freeTimes：		免费次数
		surGoldTimes:	剩余元宝次数
		checkBag：		[]背包状态
		digAllGold:		全部挖取花费 
		digOneGold:		挖一个花费
	}*/
	
	private static final long serialVersionUID = 1L;

	private int isOtherDay;
	private List<PacketExtend> checkBag;
	private Map<String,ProbItem> treasuryMap;
	private int gold;
	private int freeTimes;
	private int surGoldTimes;
	private int digOneGold;
	private int digAllGold;
	
	public DigVO(int isOtherDay,List<PacketExtend> checkBag, Map<String,ProbItem> treasuryMap, int gold, 
			int freeTimes, int surGoldTimes, int digOneGold, int digAllGold){
		this.isOtherDay 	= isOtherDay;
		this.checkBag 		= checkBag;
		this.treasuryMap 	= treasuryMap;
		this.gold 			= gold;
		this.freeTimes 		= freeTimes;
		this.surGoldTimes 	= surGoldTimes;
		this.digOneGold     = digOneGold;
		this.digAllGold		= digAllGold;
	}
	
	public List<PacketExtend> getCheckBag() {
		return checkBag;
	}
	public void setCheckBag(List<PacketExtend> checkBag) {
		this.checkBag = checkBag;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getFreeTimes() {
		return freeTimes;
	}
	public void setFreeTimes(int freeTimes) {
		this.freeTimes = freeTimes;
	}
	public Map<String,ProbItem> getTreasuryMap() {
		return treasuryMap;
	}
	public void setTreasuryMap(Map<String,ProbItem> treasuryMap) {
		this.treasuryMap = treasuryMap;
	}
	public int getSurGoldTimes() {
		return surGoldTimes;
	}
	public void setSurGoldTimes(int surGoldTimes) {
		this.surGoldTimes = surGoldTimes;
	}

	public int getIsOtherDay() {
		return isOtherDay;
	}

	public void setIsOtherDay(int isOtherDay) {
		this.isOtherDay = isOtherDay;
	}

	public int getDigOneGold() {
		return digOneGold;
	}

	public void setDigOneGold(int digOneGold) {
		this.digOneGold = digOneGold;
	}

	public int getDigAllGold() {
		return digAllGold;
	}

	public void setDigAllGold(int digAllGold) {
		this.digAllGold = digAllGold;
	}
	
	
	
}
