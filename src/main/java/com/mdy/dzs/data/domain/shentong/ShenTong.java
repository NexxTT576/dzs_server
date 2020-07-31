package com.mdy.dzs.data.domain.shentong;

import java.io.Serializable;
import java.util.List;


/**
 * 神通升级模型
 * @author 房曈
 *
 */
public class ShenTong implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**神通组id*/
	private int id;
	/**神通各级id组*/
	private List<Integer> arrTalent;
	/**神通升级所需点数*/
	private List<Integer> arrPoint;
	/**神通升级的阶级条件*/
	private List<Integer> arrCond;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public List<Integer> getArrTalent(){
		return this.arrTalent;
	}
	public void setArrTalent(List<Integer> arrTalent){
		this.arrTalent=arrTalent;
	}
	public List<Integer> getArrPoint(){
		return this.arrPoint;
	}
	public void setArrPoint(List<Integer> arrPoint){
		this.arrPoint=arrPoint;
	}
	public List<Integer> getArrCond(){
		return this.arrCond;
	}
	public void setArrCond(List<Integer> arrCond){
		this.arrCond=arrCond;
	}
}