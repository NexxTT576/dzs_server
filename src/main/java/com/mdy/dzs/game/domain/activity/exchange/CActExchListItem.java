package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;

public class CActExchListItem implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;

	/***/
	private int id;
	/**类型（1-3换1；2-4换1；3-5换1）*/
	private int type;
	/**标签名*/
	private String tagName;
	/**兑换类型（1-每日；2-活动期间内总共）*/
	private int exchType;
	/**可兑换次数*/
	private int exchNum;
	/**兑换公式*/
	private CActExchExp exchExp;
	/**是否能刷新（0-不能；1-能）*/
	private int isRefresh;
	/**刷新消耗初始元宝数*/
	private int refGold;
	/**总兑换次数*/
	private int totalNum;
	/**免费刷新次数*/
	private int refFreeNum;
	
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
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getExchType() {
		return exchType;
	}
	public void setExchType(int exchType) {
		this.exchType = exchType;
	}
	public int getExchNum() {
		return exchNum;
	}
	public void setExchNum(int exchNum) {
		this.exchNum = exchNum;
	}
	public CActExchExp getExchExp() {
		return exchExp;
	}
	public void setExchExp(CActExchExp exchExp) {
		this.exchExp = exchExp;
	}
	public int getIsRefresh() {
		return isRefresh;
	}
	public void setIsRefresh(int isRefresh) {
		this.isRefresh = isRefresh;
	}
	public int getRefGold() {
		return refGold;
	}
	public void setRefGold(int refGold) {
		this.refGold = refGold;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getRefFreeNum() {
		return refFreeNum;
	}
	public void setRefFreeNum(int refFreeNum) {
		this.refFreeNum = refFreeNum;
	}
}
