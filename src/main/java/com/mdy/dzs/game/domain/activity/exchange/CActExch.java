package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.packet.PacketExtend;

public class CActExch implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;

	private int exchId;
	
	private int exchNum;
	
	private List<PacketExtend> checkBag;
	
	/**兑换公式*/
	private CActExchExp exchExp;

	public int getExchId() {
		return exchId;
	}

	public void setExchId(int exchId) {
		this.exchId = exchId;
	}

	public int getExchNum() {
		return exchNum;
	}

	public void setExchNum(int exchNum) {
		this.exchNum = exchNum;
	}

	public List<PacketExtend> getCheckBag() {
		return checkBag;
	}

	public void setCheckBag(List<PacketExtend> checkBag) {
		this.checkBag = checkBag;
	}

	public CActExchExp getExchExp() {
		return exchExp;
	}

	public void setExchExp(CActExchExp exchExp) {
		this.exchExp = exchExp;
	}
}
