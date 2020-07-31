package com.mdy.dzs.data.domain.chongzhi;

import java.io.Serializable;



public class Chongzhi implements Serializable{

	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**编号*/
	private int index;
	/**是否开放充值*/
	private int open;
	/**平台货币数量*/
	private int coinnum;
	/**可兑换基础元宝数*/
	private int basegold;
	/**首次赠送元宝数*/
	private int firstgold;
	/**持续赠送元宝数*/
	private int chixugold;
	/**首次购买是否显示优惠标签*/
	private int mark1;
	/**首次之后是否显示优惠标签*/
	private int mark2;
	/**前置index*/
	private int preIndex;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getIndex(){
		return this.index;
	}
	public void setIndex(int index){
		this.index=index;
	}
	public int getCoinnum(){
		return this.coinnum;
	}
	public void setCoinnum(int coinnum){
		this.coinnum=coinnum;
	}
	public int getBasegold(){
		return this.basegold;
	}
	public void setBasegold(int basegold){
		this.basegold=basegold;
	}
	public int getFirstgold(){
		return this.firstgold;
	}
	public void setFirstgold(int firstgold){
		this.firstgold=firstgold;
	}
	public int getChixugold(){
		return this.chixugold;
	}
	public void setChixugold(int chixugold){
		this.chixugold=chixugold;
	}
	public int getMark1(){
		return this.mark1;
	}
	public void setMark1(int mark1){
		this.mark1=mark1;
	}
	public int getMark2(){
		return this.mark2;
	}
	public void setMark2(int mark2){
		this.mark2=mark2;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public int getPreIndex() {
		return preIndex;
	}
	public void setPreIndex(int preIndex) {
		this.preIndex = preIndex;
	}
}
