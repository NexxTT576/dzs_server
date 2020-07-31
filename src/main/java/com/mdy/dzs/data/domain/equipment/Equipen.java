package com.mdy.dzs.data.domain.equipment;

import java.io.Serializable;
import java.util.List;


/**
 * 装备模型
 * @author 房曈
 *
 */
public class Equipen implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**当前强化等级*/
	private int lv;
	/**主角最低等级*/
	private int mlv;
	/**升级所需银两基础值*/
	private List<Integer> coin;
	/**银两累积和*/
	private List<Integer> sum;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getLv(){
		return this.lv;
	}
	public void setLv(int lv){
		this.lv=lv;
	}
	public int getMlv(){
		return this.mlv;
	}
	public void setMlv(int mlv){
		this.mlv=mlv;
	}
	public List<Integer> getCoin(){
		return this.coin;
	}
	public void setCoin(List<Integer> coin){
		this.coin=coin;
	}
	public List<Integer> getSum(){
		return this.sum;
	}
	public void setSum(List<Integer> sum){
		this.sum=sum;
	}
}