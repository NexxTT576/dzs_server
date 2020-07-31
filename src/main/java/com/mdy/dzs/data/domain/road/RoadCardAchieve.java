package com.mdy.dzs.data.domain.road;

import java.io.Serializable;


/**
 * 名将成就模型
 * @author 房曈
 *
 */
public class RoadCardAchieve implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**好感度之和*/
	private int good;
	/**耐力上限提升*/
	private int naili;
	/**成就名称*/
	private String name;
	/**成就条件描述*/
	private String condition;
	/**成就效果描述*/
	private String effect;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getGood(){
		return this.good;
	}
	public void setGood(int good){
		this.good=good;
	}
	public int getNaili(){
		return this.naili;
	}
	public void setNaili(int naili){
		this.naili=naili;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getCondition(){
		return this.condition;
	}
	public void setCondition(String condition){
		this.condition=condition;
	}
	public String getEffect(){
		return this.effect;
	}
	public void setEffect(String effect){
		this.effect=effect;
	}
}