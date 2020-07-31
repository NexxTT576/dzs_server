package com.mdy.dzs.data.domain.dart;

import java.io.Serializable;
import java.util.List;


/**
 * 押镖奖励数值表模型
 * @author 白雪林
 *
 */
public class YabiaoJiangli implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/***/
	private int max;
	/***/
	private List<Integer> rewardIds;
	/***/
	private List<Integer> rewardTypes;
	/***/
	private List<Float> ratio;
	/***/
	private List<Integer> fix;
	/***/
	private int prob1;
	/***/
	private int prob2;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getMax(){
		return this.max;
	}
	public void setMax(int max){
		this.max=max;
	}
	public List<Integer> getRewardIds(){
		return this.rewardIds;
	}
	public void setRewardIds(List<Integer> rewardIds){
		this.rewardIds=rewardIds;
	}
	public List<Integer> getRewardTypes(){
		return this.rewardTypes;
	}
	public void setRewardTypes(List<Integer> rewardTypes){
		this.rewardTypes=rewardTypes;
	}
	public List<Float> getRatio(){
		return this.ratio;
	}
	public void setRatio(List<Float> ratio){
		this.ratio=ratio;
	}
	public List<Integer> getFix(){
		return this.fix;
	}
	public void setFix(List<Integer> fix){
		this.fix=fix;
	}
	public int getProb1(){
		return this.prob1;
	}
	public void setProb1(int prob1){
		this.prob1=prob1;
	}
	public int getProb2(){
		return this.prob2;
	}
	public void setProb2(int prob2){
		this.prob2=prob2;
	}
}