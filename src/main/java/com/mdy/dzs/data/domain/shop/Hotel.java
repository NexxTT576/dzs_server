package com.mdy.dzs.data.domain.shop;

import java.io.Serializable;


/**
 * 酒馆模型
 * @author 房曈
 *
 */
public class Hotel implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**酒馆类型*/
	private int type;
	/**消耗金币*/
	private int coin;
	/**消耗道具id*/
	private int itemid;
	/**消耗道具数量*/
	private int number;
	/**免费1次消耗时长/分钟*/
	private int time;
	/**首次招募随机组id*/
	private int first;
	/**随机组id*/
	private int probid1;
	/**神将10次随机组*/
	private int probid2;

	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getCoin(){
		return this.coin;
	}
	public void setCoin(int coin){
		this.coin=coin;
	}
	public int getItemid(){
		return this.itemid;
	}
	public void setItemid(int itemid){
		this.itemid=itemid;
	}
	public int getNumber(){
		return this.number;
	}
	public void setNumber(int number){
		this.number=number;
	}
	public int getTime(){
		return this.time;
	}
	public void setTime(int time){
		this.time=time;
	}
	public int getFirst(){
		return this.first;
	}
	public void setFirst(int first){
		this.first=first;
	}
	public int getProbid1(){
		return this.probid1;
	}
	public void setProbid1(int probid1){
		this.probid1=probid1;
	}
	public int getProbid2(){
		return this.probid2;
	}
	public void setProbid2(int probid2){
		this.probid2=probid2;
	}
}