package com.mdy.dzs.data.domain.viplevel;

import java.io.Serializable;
import java.util.List;


/**
 * vip等级限制，等级礼包模型
 * @author 白雪林
 *
 */
public class Viplevel implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**编号*/
	private int id;
	/**VIP等级*/
	private int vip;
	/**经验值上限*/
	private int exp;
	/**是否开启*/
	private int open;
	/**升级赠送物品类型*/
	private List<Integer> arrType1;
	/**升级赠送物品id*/
	private List<Integer> arrItem1;
	/**升级赠送物品数量*/
	private List<Integer> arrNum1;
	/**每日赠送物品类型*/
	private List<Integer> arrType2;
	/**每日赠送物品id*/
	private List<Integer> arrItem2;
	/**每日赠送物品数量*/
	private List<Integer> arrNum2;
	/**原价*/
	private int oldPrice;
	/**现价*/
	private int newPrice;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getVip(){
		return this.vip;
	}
	public void setVip(int vip){
		this.vip=vip;
	}
	public int getExp(){
		return this.exp;
	}
	public void setExp(int exp){
		this.exp=exp;
	}
	public int getOpen(){
		return this.open;
	}
	public void setOpen(int open){
		this.open=open;
	}
	public List<Integer> getArrType1(){
		return this.arrType1;
	}
	public void setArrType1(List<Integer> arrType1){
		this.arrType1=arrType1;
	}
	public List<Integer> getArrItem1(){
		return this.arrItem1;
	}
	public void setArrItem1(List<Integer> arrItem1){
		this.arrItem1=arrItem1;
	}
	public List<Integer> getArrNum1(){
		return this.arrNum1;
	}
	public void setArrNum1(List<Integer> arrNum1){
		this.arrNum1=arrNum1;
	}
	public List<Integer> getArrType2(){
		return this.arrType2;
	}
	public void setArrType2(List<Integer> arrType2){
		this.arrType2=arrType2;
	}
	public List<Integer> getArrItem2(){
		return this.arrItem2;
	}
	public void setArrItem2(List<Integer> arrItem2){
		this.arrItem2=arrItem2;
	}
	public List<Integer> getArrNum2(){
		return this.arrNum2;
	}
	public void setArrNum2(List<Integer> arrNum2){
		this.arrNum2=arrNum2;
	}
	public int getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(int oldPrice) {
		this.oldPrice = oldPrice;
	}
	public int getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(int newPrice) {
		this.newPrice = newPrice;
	}
}