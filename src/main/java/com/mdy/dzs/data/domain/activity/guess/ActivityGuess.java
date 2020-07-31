package com.mdy.dzs.data.domain.activity.guess;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbVal;

/**
 * 活动猜拳模型
 * 
 * @author 房曈
 *
 */
public class ActivityGuess implements Serializable {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	/** 猜拳id */
	private int id;
	/** 等级 */
	private int level;
	/** 物品1 */
	private List<ProbVal> item1;
	/** 物品1概率 */
	private int prob1;
	/** 物品2 */
	private List<ProbVal> item2;
	/** 物品2概率 */
	private int prob2;
	/** 物品3 */
	private List<ProbVal> item3;
	/** 物品概率3 */
	private int prob3;

	private List<Integer> winProb;
	/** 更新时间 */
	private Date updateTime;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<ProbVal> getItem1() {
		return this.item1;
	}

	public void setItem1(List<ProbVal> item1) {
		this.item1 = item1;
	}

	public int getProb1() {
		return this.prob1;
	}

	public void setProb1(int prob1) {
		this.prob1 = prob1;
	}

	public List<ProbVal> getItem2() {
		return this.item2;
	}

	public void setItem2(List<ProbVal> item2) {
		this.item2 = item2;
	}

	public int getProb2() {
		return this.prob2;
	}

	public void setProb2(int prob2) {
		this.prob2 = prob2;
	}

	public List<ProbVal> getItem3() {
		return this.item3;
	}

	public void setItem3(List<ProbVal> item3) {
		this.item3 = item3;
	}

	public int getProb3() {
		return this.prob3;
	}

	public void setProb3(int prob3) {
		this.prob3 = prob3;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Integer> getWinProb() {
		return winProb;
	}

	public void setWinProb(List<Integer> winProb) {
		this.winProb = winProb;
	}
}