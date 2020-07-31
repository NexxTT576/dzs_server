package com.mdy.dzs.data.domain.yuan;

import java.io.Serializable;


/**
 * 真气聚集概率模型
 * @author 房曈
 *
 */
public class Collect implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**当前位置*/
	private int position;
	/**升级概率*/
	private int prob;
	/**产出魂包*/
	private int soul;
	/**点击价格*/
	private int price;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getPosition(){
		return this.position;
	}
	public void setPosition(int position){
		this.position=position;
	}
	public int getProb(){
		return this.prob;
	}
	public void setProb(int prob){
		this.prob=prob;
	}
	public int getSoul(){
		return this.soul;
	}
	public void setSoul(int soul){
		this.soul=soul;
	}
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price=price;
	}
}