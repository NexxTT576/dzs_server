package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.domain.packet.PacketExtend;

/**
 * 兑换列表
 * @author Administrator
 *
 */
public class CExchangeVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	/**角色id*/
	private int roleId;
	/**物品id*/
	private int itemId;
	/**剩余次数*/
	private int number;
	/**剩余荣誉*/
	private int honor;
	/**元宝*/
	private int gold;
	/**银币*/
	private int silver;
	/**背包检查*/
	private List<PacketExtend> packetOut = new ArrayList<PacketExtend>();
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getHonor() {
		return honor;
	}
	public void setHonor(int honor) {
		this.honor = honor;
	}
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
}
