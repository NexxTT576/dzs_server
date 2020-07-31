package com.mdy.dzs.data.domain.challengebattle;

import java.io.Serializable;
import java.util.List;


/**
 * 活动副本配置模型
 * @author 房曈
 *
 */
public class ActivityBattle implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;

	public static final int AID_劫富济贫		= 1;
	public static final int AID_行侠仗义			= 2;
	public static final int AID_除暴安良			= 3;
	public static final int AID_黑风寨			= 4;
	public static final int AID_狼居胥			= 5;

	/**副本id*/
	private int id;
	/**副本名称*/
	private String name;
	/**活动开启条件等级*/
	private int prebattle;
	/**一周每日挑战次数组*/
	private List<Integer> arrNum;
	/**第1次价格*/
	private int price;
	/**递增价格*/
	private int addprice;
	/**阵型id*/
	private List<Integer> npc;
	/**波数*/
	private int wave;
	/**对应系统产出判断id*/
	private int bagCheckId;
	/**对应系统开放id*/
	private int openSysId;
	/**排列顺序*/
	private int order;
	/**各难度开启等级*/
	private List<Integer> arrPrebattle;
	/**各难度掉落概率组id*/
	private List<Integer> probid;
	/**阵型id*/
	private List<List<Integer>> npcid;
	/**购买次数的数组*/
	private List<Integer> buyPrice;
	/**是否可购买*/
	private int isbuy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrebattle() {
		return prebattle;
	}
	public void setPrebattle(int prebattle) {
		this.prebattle = prebattle;
	}
	public List<Integer> getArrNum() {
		return arrNum;
	}
	public void setArrNum(List<Integer> arrNum) {
		this.arrNum = arrNum;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAddprice() {
		return addprice;
	}
	public void setAddprice(int addprice) {
		this.addprice = addprice;
	}
	public List<Integer> getNpc() {
		return npc;
	}
	public void setNpc(List<Integer> npc) {
		this.npc = npc;
	}
	public int getWave() {
		return wave;
	}
	public void setWave(int wave) {
		this.wave = wave;
	}
	public int getBagCheckId() {
		return bagCheckId;
	}
	public void setBagCheckId(int bagCheckId) {
		this.bagCheckId = bagCheckId;
	}
	public int getOpenSysId() {
		return openSysId;
	}
	public void setOpenSysId(int openSysId) {
		this.openSysId = openSysId;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public List<Integer> getArrPrebattle() {
		return arrPrebattle;
	}
	public void setArrPrebattle(List<Integer> arrPrebattle) {
		this.arrPrebattle = arrPrebattle;
	}
	public List<Integer> getProbid() {
		return probid;
	}
	public void setProbid(List<Integer> probid) {
		this.probid = probid;
	}
	public List<List<Integer>> getNpcid() {
		return npcid;
	}
	public void setNpcid(List<List<Integer>> npcid) {
		this.npcid = npcid;
	}
	public List<Integer> getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(List<Integer> buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getIsbuy() {
		return isbuy;
	}
	public void setIsbuy(int isbuy) {
		this.isbuy = isbuy;
	}
}