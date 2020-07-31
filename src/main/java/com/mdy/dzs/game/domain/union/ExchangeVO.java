package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.packet.PacketExtend;

public class ExchangeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4246629389793434457L;

	private List<ShopInfo> shopListA;
	private List<PropInfo> shopListB;
	private List<PacketExtend> checkBagList;
	private int leftNum;
	private int hadNum;
	private int isrefresh;
	private int lastContribute;

	public List<ShopInfo> getShopListA() {
		return shopListA;
	}

	public void setShopListA(List<ShopInfo> shopListA) {
		this.shopListA = shopListA;
	}

	public List<PropInfo> getShopListB() {
		return shopListB;
	}

	public void setShopListB(List<PropInfo> shopListB) {
		this.shopListB = shopListB;
	}

	public List<PacketExtend> getCheckBagList() {
		return checkBagList;
	}

	public void setCheckBagList(List<PacketExtend> checkBagList) {
		this.checkBagList = checkBagList;
	}

	public int getLeftNum() {
		return leftNum;
	}

	public void setLeftNum(int leftNum) {
		this.leftNum = leftNum;
	}

	public int getHadNum() {
		return hadNum;
	}

	public void setHadNum(int hadNum) {
		this.hadNum = hadNum;
	}

	public int getIsrefresh() {
		return isrefresh;
	}

	public void setIsrefresh(int isrefresh) {
		this.isrefresh = isrefresh;
	}

	public int getLastContribute() {
		return lastContribute;
	}

	public void setLastContribute(int lastContribute) {
		this.lastContribute = lastContribute;
	}

}
