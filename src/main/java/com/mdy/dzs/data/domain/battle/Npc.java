package com.mdy.dzs.data.domain.battle;

import java.io.Serializable;
import java.util.List;


/**
 * 战斗的怪物站位模型
 * @author 房曈
 *
 */
public class Npc implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**名称*/
	private String name;
	/**胜利条件（0：敌方全死；1：指定回合内杀光敌方；2：杀光敌方时我方血量比例不低于指定值）*/
	private int condition;
	/**胜利条件参数*/
	private int para;
	/**站位1怪物id*/
	private int npc1;
	/**站位2怪物id*/
	private int npc2;
	/**站位3怪物id*/
	private int npc3;
	/**站位4怪物id*/
	private int npc4;
	/**站位5怪物id*/
	private int npc5;
	/**站位6怪物id*/
	private int npc6;
	/**需放大的位置*/
	private List<Integer> pos;
	/**怪物放大系数*/
	private List<Float> scale;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getCondition(){
		return this.condition;
	}
	public void setCondition(int condition){
		this.condition=condition;
	}
	public int getPara(){
		return this.para;
	}
	public void setPara(int para){
		this.para=para;
	}
	public int getNpc1(){
		return this.npc1;
	}
	public void setNpc1(int npc1){
		this.npc1=npc1;
	}
	public int getNpc2(){
		return this.npc2;
	}
	public void setNpc2(int npc2){
		this.npc2=npc2;
	}
	public int getNpc3(){
		return this.npc3;
	}
	public void setNpc3(int npc3){
		this.npc3=npc3;
	}
	public int getNpc4(){
		return this.npc4;
	}
	public void setNpc4(int npc4){
		this.npc4=npc4;
	}
	public int getNpc5(){
		return this.npc5;
	}
	public void setNpc5(int npc5){
		this.npc5=npc5;
	}
	public int getNpc6(){
		return this.npc6;
	}
	public void setNpc6(int npc6){
		this.npc6=npc6;
	}
	public List<Integer> getPos(){
		return this.pos;
	}
	public void setPos(List<Integer> pos){
		this.pos=pos;
	}
	public List<Float> getScale(){
		return this.scale;
	}
	public void setScale(List<Float> scale){
		this.scale=scale;
	}
}