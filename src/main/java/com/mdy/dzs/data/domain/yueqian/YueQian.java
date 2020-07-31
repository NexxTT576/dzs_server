package com.mdy.dzs.data.domain.yueqian;

import java.io.Serializable;


/**
 * 月签模型
 * @author 白雪林
 *
 */
public class YueQian implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**月份*/
	private int month;
	/**次数*/
	private int time;
	/**奖励物品类型*/
	private int type;
	/**奖励物品id*/
	private int itemid;
	/**奖励物品数量*/
	private int num;
	/**双倍奖励所需vip等级(0代表不能领取双份）*/
	private int vip;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getMonth(){
		return this.month;
	}
	public void setMonth(int month){
		this.month=month;
	}
	public int getTime(){
		return this.time;
	}
	public void setTime(int time){
		this.time=time;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getItemid(){
		return this.itemid;
	}
	public void setItemid(int itemid){
		this.itemid=itemid;
	}
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num=num;
	}
	public int getVip(){
		return this.vip;
	}
	public void setVip(int vip){
		this.vip=vip;
	}
}