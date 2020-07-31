package com.mdy.dzs.game.domain.activity.limitshop;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.packet.PacketExtend;

public class LSExchangeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4246629389793434457L;

	private List<PacketExtend> checkBagList;
	private int success;
	private int isOtherDay;

	public LSExchangeVO(List<PacketExtend> checkBagList, int success, int isOtherDay) {
		this.checkBagList = checkBagList;
		this.success = success;
		this.isOtherDay = isOtherDay;
	}

	public List<PacketExtend> getCheckBagList() {
		return checkBagList;
	}

	public void setCheckBagList(List<PacketExtend> checkBagList) {
		this.checkBagList = checkBagList;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getIsOtherDay() {
		return isOtherDay;
	}

	public void setIsOtherDay(int isOtherDay) {
		this.isOtherDay = isOtherDay;
	}

}
