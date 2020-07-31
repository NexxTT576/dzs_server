package com.mdy.dzs.data.domain.equipment;

import java.io.Serializable;
import java.util.List;


/**
 * 装备洗练模型
 * @author 房曈
 *
 */
public class Baptize implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/**洗炼类型*/
	private int type;
	/**花费(银币;金币;洗炼石)*/
	private List<Integer> arrSilver;
	/**概率组*/
	private List<Integer> pro;
	/**参考值区间1*/
	private List<Integer> extend1;
	/**参考值区间2*/
	private List<Integer> extend2;
	/**参考值区间3*/
	private List<Integer> extend3;
	/**参考值区间4*/
	private List<Integer> extend4;
	/**参考值区间5*/
	private List<Integer> extend5;
	/**属性系数组（生命、攻击、物防、法防、最终伤害、最终免伤）*/
	private List<Integer> factro;

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
	public List<Integer> getArrSilver(){
		return this.arrSilver;
	}
	public void setArrSilver(List<Integer> arrSilver){
		this.arrSilver=arrSilver;
	}
	public List<Integer> getPro(){
		return this.pro;
	}
	public void setPro(List<Integer> pro){
		this.pro=pro;
	}
	public List<Integer> getExtend1(){
		return this.extend1;
	}
	public void setExtend1(List<Integer> extend1){
		this.extend1=extend1;
	}
	public List<Integer> getExtend2(){
		return this.extend2;
	}
	public void setExtend2(List<Integer> extend2){
		this.extend2=extend2;
	}
	public List<Integer> getExtend3(){
		return this.extend3;
	}
	public void setExtend3(List<Integer> extend3){
		this.extend3=extend3;
	}
	public List<Integer> getExtend4(){
		return this.extend4;
	}
	public void setExtend4(List<Integer> extend4){
		this.extend4=extend4;
	}
	public List<Integer> getExtend5(){
		return this.extend5;
	}
	public void setExtend5(List<Integer> extend5){
		this.extend5=extend5;
	}
	public List<Integer> getFactro(){
		return this.factro;
	}
	public void setFactro(List<Integer> factro){
		this.factro=factro;
	}
	
	public List<Integer> getExtend(int pos){
		switch(pos){
		case 1:
			return extend1;
		case 2:
			return extend2;
		case 3:
			return extend3;
		case 4:
			return extend4;
		case 5:
			return extend5;
		}
		return null;
	}
}