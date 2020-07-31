package com.mdy.dzs.data.domain.activity.roulette;

import java.io.Serializable;
import java.util.List;


/**
 * 皇宫探宝随机表模型
 * @author 白雪林
 *
 */
public class RouletteProb implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	public static final int 初始位置	= 1;
	
	public static final int ACTIVITY_ROULETTE	= 1;
	public static final int ACTIVITY_MAZE		= 2;

	/***/
	private int id;
	/***/
	private int type;
	/***/
	private String mark;
	/***/
	private int isStart;
	/***/
	private List<Integer> position;
	/***/
	private List<Integer> prob;
	/***/
	private List<Integer> output;
	/***/
	private int resetID;
	/***/
	private int number;
	/***/
	private int jumpID;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getMark(){
		return this.mark;
	}
	public void setMark(String mark){
		this.mark=mark;
	}
	public int getIsStart(){
		return this.isStart;
	}
	public void setIsStart(int isStart){
		this.isStart=isStart;
	}
	public List<Integer> getPosition(){
		return this.position;
	}
	public void setPosition(List<Integer> position){
		this.position=position;
	}
	public List<Integer> getProb(){
		return this.prob;
	}
	public void setProb(List<Integer> prob){
		this.prob=prob;
	}
	public List<Integer> getOutput(){
		return this.output;
	}
	public void setOutput(List<Integer> output){
		this.output=output;
	}
	public int getResetID(){
		return this.resetID;
	}
	public void setResetID(int resetID){
		this.resetID=resetID;
	}
	public int getNumber(){
		return this.number;
	}
	public void setNumber(int number){
		this.number=number;
	}
	public int getJumpID(){
		return this.jumpID;
	}
	public void setJumpID(int jumpID){
		this.jumpID=jumpID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}