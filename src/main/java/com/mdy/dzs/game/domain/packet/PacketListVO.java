package com.mdy.dzs.game.domain.packet;

import java.io.Serializable;
import java.util.List;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月16日 上午10:25:25
 */
public class PacketListVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 错误信息
	private String err;
	// 道具数组
	private List<Object> itemAry;
	// 携带数分子
	private int member;
	// 携带数分母
	private int denominator;
	// 扩展花费
	private int cost;
	// 扩展空间
	private int size;
	public String cardNameAry[];

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public List<Object> getItemAry() {
		return itemAry;
	}

	public void setItemAry(List<Object> itemAry) {
		this.itemAry = itemAry;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
