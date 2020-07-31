package com.mdy.dzs.data.domain.arena;

import java.io.Serializable;


/**
 * 竞技场模型
 * @author 房曈
 *
 */
public class Arena implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**奖励类型（1-银币，2-声望）*/
	private int type;
	/**名次下限*/
	private int min;
	/**名次上限*/
	private int max;
	/**系数*/
	private float ratio;
	/**固定值（银币=（固定值-名次*系数）*等级，声望=固定值-名次*系数）*/
	private int fix;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getMin(){
		return this.min;
	}
	public void setMin(int min){
		this.min=min;
	}
	public int getMax(){
		return this.max;
	}
	public void setMax(int max){
		this.max=max;
	}
	public float getRatio(){
		return this.ratio;
	}
	public void setRatio(float ratio){
		this.ratio=ratio;
	}
	public int getFix(){
		return this.fix;
	}
	public void setFix(int fix){
		this.fix=fix;
	}
}