package com.mdy.dzs.game.domain.activity.roulettegame;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.packet.PacketExtend;

public class GetCreditVO implements Serializable{
	/*{
		getBox:		已领取的箱子	[1,2,3]
		checkBag：	[]背包状态
	}*/
	private static final long serialVersionUID = 1L;

	private List<Integer> getBox;
	private List<PacketExtend> checkBag;
	
	public GetCreditVO(List<Integer> getBox,List<PacketExtend> checkBag){
		this.getBox   = getBox;
		this.checkBag = checkBag;
	}
	
	public List<Integer> getGetBox() {
		return getBox;
	}
	public void setGetBox(List<Integer> getBox) {
		this.getBox = getBox;
	}
	public List<PacketExtend> getCheckBag() {
		return checkBag;
	}
	public void setCheckBag(List<PacketExtend> checkBag) {
		this.checkBag = checkBag;
	}

	
}
