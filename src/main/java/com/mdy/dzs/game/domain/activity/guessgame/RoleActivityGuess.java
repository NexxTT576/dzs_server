/**
 * 
 */
package com.mdy.dzs.game.domain.activity.guessgame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.util.Constants;

/**
 * 猜拳活动
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月24日  下午2:37:50
 */
public class RoleActivityGuess implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_石头 	= 1;
	public static final int TYPE_剪刀 	= 2;
	public static final int TYPE_布		= 3;
	
	public static final int MAX_ITEM_CNT = 3;
	
	/**玩家id*/
	private int roleId;
	/**可挑战次数*/
	private int guessCount;
	/**总挑战次数*/
	private int allGuessCount;
	/**可翻牌次数*/
	private int chooseCount;
	/**购买次数*/
	private int buyCount;
	/**道具列表*/
	private List<ProbItem> itemList;
	/**已翻位置*/
	private Map<String,Integer> choosePosList;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	
	private int buyGold = 0;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getChooseCount() {
		return chooseCount;
	}
	public void setChooseCount(int chooseCount) {
		this.chooseCount = chooseCount;
	}
	public List<ProbItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<ProbItem> itemList) {
		this.itemList = itemList;
	}
	
	public int getGuessCount() {
		return guessCount;
	}
	public void setGuessCount(int guessCount) {
		this.guessCount = guessCount;
	}
	public int getAllGuessCount() {
		return allGuessCount;
	}
	public void setAllGuessCount(int allGuessCount) {
		this.allGuessCount = allGuessCount;
	}
	public Map<String, Integer> getChoosePosList() {
		return choosePosList;
	}
	public void setChoosePosList(Map<String, Integer> choosePosList) {
		this.choosePosList = choosePosList;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	
	public int getBuyGold(){
		return Constants.activityGuessBuyGold*(buyCount+1);
	}
	
	
	private long endTime = 0l;
	public void setEndTime(long time) {
		endTime = time;
	}
	public long getEndTime(){
		return endTime;
	}
}
