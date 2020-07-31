package com.mdy.dzs.game.domain.activity.roulettegame;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.game.domain.packet.PacketExtend;

public class RouletteOpVO implements Serializable{
/*	{
		itemAry:
		[
			{type:*
			 id:*
			 num:*
			}
		]
		surTimes:	剩余次数
		position:	位置
		freeTimes：	免费次数
		credit：		当前积分
	}*/
	
	private static final long serialVersionUID = 1L;
	private List<GiftItem> itemAry;
	private int surTimes;
	private int position;
	private int freeTimes;
	private List<PacketExtend> checkBag;
	private int credit;
	
	public RouletteOpVO(List<GiftItem> itemAry,int surTimes,int position,int freeTimes,int credit,List<PacketExtend> checkBag){
		this.itemAry 	= itemAry;
		this.surTimes 	= surTimes;
		this.position 	= position;
		this.freeTimes 	= freeTimes;
		this.checkBag 	= checkBag;
		this.credit		= credit;
	}
	
	public List<GiftItem> getItemAry() {
		return itemAry;
	}
	public void setItemAry(List<GiftItem> itemAry) {
		this.itemAry = itemAry;
	}
	public int getSurTimes() {
		return surTimes;
	}
	public void setSurTimes(int surTimes) {
		this.surTimes = surTimes;
	}	
	public int getFreeTimes() {
		return freeTimes;
	}
	public void setFreeTimes(int freeTimes) {
		this.freeTimes = freeTimes;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	public List<PacketExtend> getCheckBag() {
		return checkBag;
	}

	public void setCheckBag(List<PacketExtend> checkBag) {
		this.checkBag = checkBag;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
}
