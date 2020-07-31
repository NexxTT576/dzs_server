package com.mdy.dzs.data.domain.role;

import java.io.Serializable;
import java.util.List;


/**
 * 经脉模型
 * @author 房曈
 *
 */
public class JingMai implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**类型（1-神武，2-太乙，3-归墟）*/
	private int type;
	/**名称*/
	private String name;
	/**描述*/
	private String describe;
	/**顺序*/
	private int order;
	/**属性id*/
	private int nature;
	/**属性值数组*/
	private  List<Integer> arrValue;
	/**消耗银币数组（各等*/
	private  List<Integer> arrCoin;
	/**消耗星星数组（各等级）*/
	private  List<Integer> arrStar;

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
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getDescribe(){
		return this.describe;
	}
	public void setDescribe(String describe){
		this.describe=describe;
	}
	public int getOrder(){
		return this.order;
	}
	public void setOrder(int order){
		this.order=order;
	}
	public int getNature(){
		return this.nature;
	}
	public void setNature(int nature){
		this.nature=nature;
	}
	public  List<Integer> getArrValue(){
		return this.arrValue;
	}
	public void setArrValue( List<Integer> arrValue){
		this.arrValue=arrValue;
	}
	public  List<Integer> getArrCoin(){
		return this.arrCoin;
	}
	public void setArrCoin( List<Integer> arrCoin){
		this.arrCoin=arrCoin;
	}
	public  List<Integer> getArrStar(){
		return this.arrStar;
	}
	public void setArrStar( List<Integer> arrStar){
		this.arrStar=arrStar;
	}
}