package com.mdy.dzs.data.domain.road;

import java.io.Serializable;
import java.util.List;


/**
 * 名将模型
 * @author 房曈
 *
 */
public class MingJiang implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**当前级别*/
	private int level;
	/**升级所需经验(蓝、紫、金）*/
	private List<Integer> arrExp;

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
	public List<Integer> getArrExp(){
		return this.arrExp;
	}
	public void setArrExp(List<Integer> arrExp){
		this.arrExp=arrExp;
	}
}