package com.mdy.dzs.data.domain.role;

import java.io.Serializable;


/**
 * 初始值模型
 * @author 房曈
 *
 */
public class ChuShi implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**主角等级*/
	private int level;
	/**vip*/
	private int vip;
	/**银币*/
	private int silver;
	/**金币*/
	private int coin;
	/**经验*/
	private int exp;
	/**体力*/
	private int power;
	/**体力上限*/
	private int pmax;
	/**耐力*/
	private int naili;
	/**耐力上限*/
	private int nmax;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getVip(){
		return this.vip;
	}
	public void setVip(int vip){
		this.vip=vip;
	}
	public int getSilver(){
		return this.silver;
	}
	public void setSilver(int silver){
		this.silver=silver;
	}
	public int getCoin(){
		return this.coin;
	}
	public void setCoin(int coin){
		this.coin=coin;
	}
	public int getExp(){
		return this.exp;
	}
	public void setExp(int exp){
		this.exp=exp;
	}
	public int getPower(){
		return this.power;
	}
	public void setPower(int power){
		this.power=power;
	}
	public int getPmax(){
		return this.pmax;
	}
	public void setPmax(int pmax){
		this.pmax=pmax;
	}
	public int getNaili(){
		return this.naili;
	}
	public void setNaili(int naili){
		this.naili=naili;
	}
	public int getNmax(){
		return this.nmax;
	}
	public void setNmax(int nmax){
		this.nmax=nmax;
	}
}