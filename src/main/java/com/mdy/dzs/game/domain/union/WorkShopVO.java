package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

import com.mdy.dzs.data.domain.union.UnionWorkShop;

public class WorkShopVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5442654519484204666L;
	private int id;
	private int isOpen;
	private int normalNum;
	private int fastNum;
	private int itemId;
	private int itemType;
	private long surplusTime;
	public static WorkShopVO valueOf(UnionWorkShop ws,int isOpen,int norNum,int extNum,long surplusTime){
		WorkShopVO workShop = new WorkShopVO();
		workShop.id = ws.getId();
		workShop.isOpen = isOpen;
		workShop.normalNum = norNum;
		workShop.fastNum = extNum;
		workShop.itemId = ws.getItemId();
		workShop.itemType = ws.getType();
		workShop.surplusTime = surplusTime;
		return workShop;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	public int getNormalNum() {
		return normalNum;
	}
	public void setNormalNum(int normalNum) {
		this.normalNum = normalNum;
	}
	public int getFastNum() {
		return fastNum;
	}
	public void setFastNum(int fastNum) {
		this.fastNum = fastNum;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public long getSurplusTime() {
		return surplusTime;
	}
	public void setSurplusTime(long surplusTime) {
		this.surplusTime = surplusTime;
	}
	
}
