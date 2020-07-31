package com.mdy.dzs.data.domain.biwu;

import java.io.Serializable;
import java.util.List;

/**
 * 比武奖励模型
 * @author zhou
 *
 */
public class BiwuJiangli implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/**名次下限*/
	private int min;
	/**名次上限*/
	private int max;
	/**系数*/
	private float ratio;
	/**固定值（银币=（固定值-名次*系数）*等级*/
	private int fix;
	/**奖励id*/
	private List<Integer> rewardIds;
	/**奖励类型*/
	private List<Integer> rewardTypes;
	/**奖励数量*/
	private List<Integer> rewardNums;

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
	public List<Integer> getRewardIds() {
		return rewardIds;
	}
	public void setRewardIds(List<Integer> rewardIds) {
		this.rewardIds = rewardIds;
	}
	public List<Integer> getRewardTypes() {
		return rewardTypes;
	}
	public void setRewardTypes(List<Integer> rewardTypes) {
		this.rewardTypes = rewardTypes;
	}
	public List<Integer> getRewardNums() {
		return rewardNums;
	}
	public void setRewardNums(List<Integer> rewardNums) {
		this.rewardNums = rewardNums;
	}
}