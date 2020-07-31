package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

public class EnterWelfareVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8299063615450015208L;
	private int id;
	private int isOpen ;
	private int isGet ;
	//剩余时间
	private long leftTime;
	private int addPhy;
	private int addRes;
	private int costNum;
	public EnterWelfareVO(int id ,int isOpen,int isGet,long leftTime,int costNum){
		this.id = id;
		this.isOpen = isOpen;
		this.isGet = isGet;
		this.leftTime = leftTime;
		this.costNum = costNum;
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
	public int getIsGet() {
		return isGet;
	}
	public void setIsGet(int isGet) {
		this.isGet = isGet;
	}
	public long getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(long leftTime) {
		this.leftTime = leftTime;
	}
	public int getAddPhy() {
		return addPhy;
	}
	public void setAddPhy(int addPhy) {
		this.addPhy = addPhy;
	}
	public int getAddRes() {
		return addRes;
	}
	public void setAddRes(int addRes) {
		this.addRes = addRes;
	}
	public int getCostNum() {
		return costNum;
	}
	public void setCostNum(int costNum) {
		this.costNum = costNum;
	}
	
}
