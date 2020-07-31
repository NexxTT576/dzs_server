package com.mdy.dzs.data.domain.shop;

import java.io.Serializable;
import java.util.List;


/**
 * 抽卡新概率模型
 * @author zhou
 *
 */
public class Pseudo implements Serializable{

	public static final int TYPE_抽卡_江湖新秀 		= 1;
	public static final int TYPE_抽卡_武林高手 		= 2;
	public static final int TYPE_抽卡_名震江湖 		= 3;
	public static final int TYPE_使用道具_金箱子 	= 4;
	public static final int TYPE_使用道具_银箱子 	= 5;
	public static final int TYPE_使用道具_铜箱子 	= 6;
	public static final int TYPE_使用道具_假箱子 	= 8;

	/**序列化id*/
	private static final long serialVersionUID = 1L;

	private int id;
	/**类型*/
	private int type;
	/**是否为起始位置（0-不是；1-是）*/
	private int isStart;
	/**概率*/
	private int prob;
	/**次数重置产出*/
	private List<Integer> output;
	/**重置跳转id*/
	private int resetID;
	/**累积次数*/
	private int number;
	/**次数达到跳转ID*/
	private int jumpID;
	/**第十次prob*/
	private int tenProb;
	
	//互斥id
	private List<ProbMutex> mutex;
	
	public List<ProbMutex> getMutex() {
		return mutex;
	}
	public void setMutex(List<ProbMutex> mutex) {
		this.mutex = mutex;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIsStart() {
		return isStart;
	}
	public void setIsStart(int isStart) {
		this.isStart = isStart;
	}
	public int getProb() {
		return prob;
	}
	public void setProb(int prob) {
		this.prob = prob;
	}
	public List<Integer> getOutput() {
		return output;
	}
	public void setOutput(List<Integer> output) {
		this.output = output;
	}
	public int getResetID() {
		return resetID;
	}
	public void setResetID(int resetID) {
		this.resetID = resetID;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getJumpID() {
		return jumpID;
	}
	public void setJumpID(int jumpID) {
		this.jumpID = jumpID;
	}
	public int getTenProb() {
		return tenProb;
	}
	public void setTenProb(int tenProb) {
		this.tenProb = tenProb;
	}

	
}