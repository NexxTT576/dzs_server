package com.mdy.dzs.game.domain.activity.mazegame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;


public class MazeEnterVO implements Serializable{
	/*{		
		libId:			道具库ID
		nowTime:		当前时间（millisecond）	
		startT:			开始时间
		endT：			结束时间
		surGoldTimes:	剩余元宝次数
		freeTimes:		免费次数
		digAllGold:		全部挖取花费
		digOneGold:		挖一个花费
		refreshCost：	刷新宝库花费
		treasuryMap：	宝库物品
						{
							"1":{type:*,id:*,num:*},
							"2":{type:*,id:*,num:*},
							...
						}
		hasGetAry:		已领取宝库id
						[*,*]
	}*/
	private static final long serialVersionUID = 1L;
		
	private int libId;
	private Long nowTime;
	private Date startT;
	private Date endT;
	private int surGoldTimes;
	private int freeTimes;
	private int digAllGold;
	private int refreshCost;
	private List<Integer> hasGetAry;
	private Map<String,ProbItem> treasuryMap;
	private int digOneGold;
	
	public MazeEnterVO(int libId,Long nowTime,Date startT,Date endT,int surGoldTimes,
			int freeTimes,int digAllGold,int refreshCost,
			List<Integer> hasGetAry,Map<String,ProbItem> treasuryMap,int digOneGold){
		this.nowTime 		= nowTime;
		this.startT			= startT;
		this.endT			= endT;
		this.surGoldTimes 	= surGoldTimes;
		this.freeTimes 		= freeTimes;
		this.digAllGold		= digAllGold;
		this.refreshCost 	= refreshCost;
		this.hasGetAry 		= hasGetAry;
		this.treasuryMap	= treasuryMap;
		this.digOneGold		= digOneGold;
		this.libId			= libId;
	}
	public Long getnowTime() {
		return nowTime;
	}
	public void setnowTime(Long nowTime) {
		this.nowTime = nowTime;
	}
	public Date getStartT() {
		return startT;
	}
	public void setStartT(Date startT) {
		this.startT = startT;
	}
	public int getSurGoldTimes() {
		return surGoldTimes;
	}
	public void setSurGoldTimes(int surGoldTimes) {
		this.surGoldTimes = surGoldTimes;
	}
	public int getFreeTimes() {
		return freeTimes;
	}
	public void setFreeTimes(int freeTimes) {
		this.freeTimes = freeTimes;
	}
	public int getDigAllGold() {
		return digAllGold;
	}
	public void setDigAllGold(int digAllGold) {
		this.digAllGold = digAllGold;
	}
	public int getRefreshCost() {
		return refreshCost;
	}
	public void setRefreshCost(int refreshCost) {
		this.refreshCost = refreshCost;
	}
	public List<Integer> getHasGetAry() {
		return hasGetAry;
	}
	public void setHasGetAry(List<Integer> hasGetAry) {
		this.hasGetAry = hasGetAry;
	}
	public Map<String,ProbItem> getTreasuryMap() {
		return treasuryMap;
	}
	public void setTreasuryMap(Map<String,ProbItem> treasuryMap) {
		this.treasuryMap = treasuryMap;
	}
	public int getDigOneGold() {
		return digOneGold;
	}
	public void setDigOneGold(int digOneGold) {
		this.digOneGold = digOneGold;
	}
	public int getLibId() {
		return libId;
	}
	public void setLibId(int libId) {
		this.libId = libId;
	}
	public Date getEndT() {
		return endT;
	}
	public void setEndT(Date endT) {
		this.endT = endT;
	}
	
}
