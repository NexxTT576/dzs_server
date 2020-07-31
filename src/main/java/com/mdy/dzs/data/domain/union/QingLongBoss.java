package com.mdy.dzs.data.domain.union;

import java.io.Serializable;


/**
 * 帮派青龙堂排名奖励表模型
 * @author 白雪林
 *
 */
public class QingLongBoss implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**名次下限*/
	private int min;
	/**名次上限*/
	private int max;
	/**银币系数*/
	private int ratio1;
	/**银币数值*/
	private int fix1;
	/**声望系数*/
	private int ratio2;
	/**声望数值*/
	private int fix2;
	/**帮贡系数*/
	private int ratio3;
	/**帮贡数值*/
	private int fix3;
	/**物品概率奖励*/
	private int prob;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
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
	public int getRatio1(){
		return this.ratio1;
	}
	public void setRatio1(int ratio1){
		this.ratio1=ratio1;
	}
	public int getFix1(){
		return this.fix1;
	}
	public void setFix1(int fix1){
		this.fix1=fix1;
	}
	public int getRatio2(){
		return this.ratio2;
	}
	public void setRatio2(int ratio2){
		this.ratio2=ratio2;
	}
	public int getFix2(){
		return this.fix2;
	}
	public void setFix2(int fix2){
		this.fix2=fix2;
	}
	public int getRatio3(){
		return this.ratio3;
	}
	public void setRatio3(int ratio3){
		this.ratio3=ratio3;
	}
	public int getFix3(){
		return this.fix3;
	}
	public void setFix3(int fix3){
		this.fix3=fix3;
	}
	public int getProb(){
		return this.prob;
	}
	public void setProb(int prob){
		this.prob=prob;
	}
}