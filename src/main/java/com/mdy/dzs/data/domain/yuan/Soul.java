package com.mdy.dzs.data.domain.yuan;

import java.io.Serializable;
import java.util.List;


/**
 * 精元升级模型
 * @author 房曈
 *
 */
public class Soul implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**精元当前等级*/
	private int level;
	/**升级所需基础经验组*/
	private List<Integer> arrExp;
	/**经验累积基础经验组*/
	private List<Integer> arrSumexp;

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
	public List<Integer> getArrSumexp(){
		return this.arrSumexp;
	}
	public void setArrSumexp(List<Integer> arrSumexp){
		this.arrSumexp=arrSumexp;
	}
}