package com.mdy.dzs.data.domain.packet;

import java.io.Serializable;


/**
 * 背包扩展费用模型
 * @author 房曈
 *
 */
public class Bag implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**背包id*/
	private int id;
	/**初始数量*/
	private int num;
	/**加5价格*/
	private int cost;
	/**价格递增*/
	private int add;
	/**最高数量*/
	private int max;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num=num;
	}
	public int getCost(){
		return this.cost;
	}
	public void setCost(int cost){
		this.cost=cost;
	}
	public int getAdd(){
		return this.add;
	}
	public void setAdd(int add){
		this.add=add;
	}
	public int getMax(){
		return this.max;
	}
	public void setMax(int max){
		this.max=max;
	}
}