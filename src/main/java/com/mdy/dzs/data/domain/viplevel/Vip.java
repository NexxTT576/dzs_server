package com.mdy.dzs.data.domain.viplevel;

import java.io.Serializable;


/**
 * vip模型
 * @author 房曈
 *
 */
public class Vip implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	

	public static final int SYSTEM_道具商店					= 1;
	public static final int SYSTEM_神秘商店免费次数上限		= 2;
	public static final int SYSTEM_神秘商店元宝刷新次数上限	= 3;
	public static final int SYSTEM_论剑每日购买次数			= 4;
	public static final int SYSTEM_精英副本购买次数			= 5;
	public static final int SYSTEM_行侠仗义购买次数			= 6;
	public static final int SYSTEM_劫富济贫购买次数			= 7;
	public static final int SYSTEM_比武挑战购买次数			= 8;
	public static final int SYSTEM_除暴安良购买次数			= 9;
	public static final int SYSTEM_黑风寨购买次数			= 10;
	public static final int SYSTEM_狼居胥购买次数			= 11;
	
	
	


	/**id*/
	private int id;
	/**系统（1-道具商店）*/
	private int system;
	/**商店中的物品id）*/
	private int shopid;
	/**vip0的每日购买最大数量*/
	private int vip0;
	/**vip1的每日购买最大数量*/
	private int vip1;
	/**vip2的每日购买最大数量*/
	private int vip2;
	/**vip3的每日购买最大数量*/
	private int vip3;
	/**vip4的每日购买最大数量*/
	private int vip4;
	/**vip5的每日购买最大数量*/
	private int vip5;
	/**vip6的每日购买最大数量*/
	private int vip6;
	/**vip7的每日购买最大数量*/
	private int vip7;
	/**vip8的每日购买最大数量*/
	private int vip8;
	/**vip9的每日购买最大数量*/
	private int vip9;
	/**vip10的每日购买最大数量*/
	private int vip10;
	/**vip11的每日购买最大数量*/
	private int vip11;
	/**vip12的每日购买最大数量*/
	private int vip12;
	/**vip13的每日购买最大数量*/
	private int vip13;
	/**vip14的每日购买最大数量*/
	private int vip14;
	/**vip15的每日购买最大数量*/
	private int vip15;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getSystem(){
		return this.system;
	}
	public void setSystem(int system){
		this.system=system;
	}
	public int getShopid(){
		return this.shopid;
	}
	public void setShopid(int shopid){
		this.shopid=shopid;
	}
	public int getVip0(){
		return this.vip0;
	}
	public void setVip0(int vip0){
		this.vip0=vip0;
	}
	public int getVip1(){
		return this.vip1;
	}
	public void setVip1(int vip1){
		this.vip1=vip1;
	}
	public int getVip2(){
		return this.vip2;
	}
	public void setVip2(int vip2){
		this.vip2=vip2;
	}
	public int getVip3(){
		return this.vip3;
	}
	public void setVip3(int vip3){
		this.vip3=vip3;
	}
	public int getVip4(){
		return this.vip4;
	}
	public void setVip4(int vip4){
		this.vip4=vip4;
	}
	public int getVip5(){
		return this.vip5;
	}
	public void setVip5(int vip5){
		this.vip5=vip5;
	}
	public int getVip6(){
		return this.vip6;
	}
	public void setVip6(int vip6){
		this.vip6=vip6;
	}
	public int getVip7(){
		return this.vip7;
	}
	public void setVip7(int vip7){
		this.vip7=vip7;
	}
	public int getVip8(){
		return this.vip8;
	}
	public void setVip8(int vip8){
		this.vip8=vip8;
	}
	public int getVip9(){
		return this.vip9;
	}
	public void setVip9(int vip9){
		this.vip9=vip9;
	}
	public int getVip10(){
		return this.vip10;
	}
	public void setVip10(int vip10){
		this.vip10=vip10;
	}
	public int getVip11(){
		return this.vip11;
	}
	public void setVip11(int vip11){
		this.vip11=vip11;
	}
	public int getVip12(){
		return this.vip12;
	}
	public void setVip12(int vip12){
		this.vip12=vip12;
	}
	public int getVip13(){
		return this.vip13;
	}
	public void setVip13(int vip13){
		this.vip13=vip13;
	}
	public int getVip14(){
		return this.vip14;
	}
	public void setVip14(int vip14){
		this.vip14=vip14;
	}
	public int getVip15(){
		return this.vip15;
	}
	public void setVip15(int vip15){
		this.vip15=vip15;
	}
	
	
	
	public int getVipByLevel(int i){
		int arr[] = {vip0,
				vip1,vip2,vip3,vip4,vip5,
				vip6,vip7,vip8,vip9,vip10,
				vip11,vip12,vip13,vip14,vip15};
		return arr[i];
	}
}