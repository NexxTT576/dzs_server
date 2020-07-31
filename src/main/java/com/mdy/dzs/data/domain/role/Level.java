package com.mdy.dzs.data.domain.role;

import java.io.Serializable;


/**
 * 等级模型
 * @author 房曈
 *
 */
public class Level implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**主角等级*/
	private int level;
	/**升到下一等级所需经验*/
	private int exp;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getExp(){
		return this.exp;
	}
	public void setExp(int exp){
		this.exp=exp;
	}
}