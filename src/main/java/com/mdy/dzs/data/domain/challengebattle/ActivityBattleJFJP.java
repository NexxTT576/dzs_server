package com.mdy.dzs.data.domain.challengebattle;

import java.io.Serializable;


/**
 * 劫富济贫活动模型
 * @author 房曈
 *
 */
public class ActivityBattleJFJP implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**伤害值*/
	private int damage;
	/**额外增加银币*/
	private int silver;
	/**累积银币*/
	private int sumsilver;
	/**额外每点伤害换算银币*/
	private float per;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getDamage(){
		return this.damage;
	}
	public void setDamage(int damage){
		this.damage=damage;
	}
	public int getSilver(){
		return this.silver;
	}
	public void setSilver(int silver){
		this.silver=silver;
	}
	public int getSumsilver(){
		return this.sumsilver;
	}
	public void setSumsilver(int sumsilver){
		this.sumsilver=sumsilver;
	}
	public float getPer(){
		return this.per;
	}
	public void setPer(float per){
		this.per=per;
	}
}