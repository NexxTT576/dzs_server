package com.mdy.dzs.data.domain.item;

import java.io.Serializable;


/**
 * 货币对应模型
 * @author 房曈
 *
 */
public class Money implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**name*/
	private String name;
	/**icon*/
	private String icon;
	/**对应道具*/
	private int item;

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
	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon=icon;
	}
	public int getItem(){
		return this.item;
	}
	public void setItem(int item){
		this.item=item;
	}
}