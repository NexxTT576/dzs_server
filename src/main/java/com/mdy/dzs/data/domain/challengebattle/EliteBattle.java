package com.mdy.dzs.data.domain.challengebattle;

import java.io.Serializable;
import java.util.List;


/**
 * 精英副本配置模型
 * @author 房曈
 *
 */
public class EliteBattle implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**副本id*/
	private int id;
	/**副本名称*/
	private String name;
	/**前置精英副本*/
	private int preid;
	/**精英副本开启条件普通关卡id*/
	private int prebattle;
	/**一周每日挑战次数组*/
	private List<Integer> arrNum;
	/**第1次价格*/
	private int price;
	/**递增价格*/
	private int addprice;
	/**精英副本boss头像（hero-icon）*/
	private String boss;
	/**精英副本前端显示奖励物品组*/
	private String arrIcon;
	/**奖励概率组id*/
	private int prob;
	/**精英副本银币*/
	private int silver;
	/**精英副本-侠魂*/
	private int xiahun;
	/**阵型id组*/
	private List<Integer> npc;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public int getPreid(){
		return this.preid;
	}
	public void setPreid(int preid){
		this.preid=preid;
	}
	public int getPrebattle(){
		return this.prebattle;
	}
	public void setPrebattle(int prebattle){
		this.prebattle=prebattle;
	}
	public List<Integer> getArrNum(){
		return this.arrNum;
	}
	public void setArrNum(List<Integer> arrNum){
		this.arrNum=arrNum;
	}
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public int getAddprice(){
		return this.addprice;
	}
	public void setAddprice(int addprice){
		this.addprice=addprice;
	}
	public String getBoss(){
		return this.boss;
	}
	public void setBoss(String boss){
		this.boss=boss;
	}
	public String getArrIcon(){
		return this.arrIcon;
	}
	public void setArrIcon(String arrIcon){
		this.arrIcon=arrIcon;
	}
	public int getProb(){
		return this.prob;
	}
	public void setProb(int prob){
		this.prob=prob;
	}
	public int getSilver(){
		return this.silver;
	}
	public void setSilver(int silver){
		this.silver=silver;
	}
	public int getXiahun(){
		return this.xiahun;
	}
	public void setXiahun(int xiahun){
		this.xiahun=xiahun;
	}
	public List<Integer> getNpc(){
		return this.npc;
	}
	public void setNpc(List<Integer> npc){
		this.npc=npc;
	}
	
}