package com.mdy.dzs.data.domain.road;

import java.io.Serializable;
import java.util.List;


/**
 * 名将升级模型
 * @author 房曈
 *
 */
public class RoadCardLevel implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**名将id*/
	private int id;
	/**侠客id*/
	private int card;
	/**属性id组（1级;2级…………)*/
	private List<Integer> arrNature;
	/**属性值组（1级;2级…………)*/
	private List<Integer> arrNum;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getCard(){
		return this.card;
	}
	public void setCard(int card){
		this.card=card;
	}
	public List<Integer> getArrNature(){
		return this.arrNature;
	}
	public void setArrNature(List<Integer> arrNature){
		this.arrNature=arrNature;
	}
	public List<Integer> getArrNum(){
		return this.arrNum;
	}
	public void setArrNum(List<Integer> arrNum){
		this.arrNum=arrNum;
	}
}