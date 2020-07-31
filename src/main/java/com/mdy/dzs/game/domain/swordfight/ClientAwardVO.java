package com.mdy.dzs.game.domain.swordfight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.packet.PacketExtend;

public class ClientAwardVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gold;
	private int silver;
	private List<PacketExtend> packetOut = new ArrayList<PacketExtend>();
	private String name;
	private int star;
	private int cls;
	/** 奖励 */
	private List<ProbItem> itemAry = new ArrayList<ProbItem>();

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getSilver() {
		return silver;
	}

	public void setSilver(int silver) {
		this.silver = silver;
	}

	public List<PacketExtend> getPacketOut() {
		return packetOut;
	}

	public void setPacketOut(List<PacketExtend> packetOut) {
		this.packetOut = packetOut;
	}

	public List<ProbItem> getItemAry() {
		return itemAry;
	}

	public void setItemAry(List<ProbItem> itemAry) {
		this.itemAry = itemAry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCls() {
		return cls;
	}

	public void setCls(int cls) {
		this.cls = cls;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}
}
