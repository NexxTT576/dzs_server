package com.mdy.dzs.data.domain.boss;

import java.io.Serializable;


/**
 * boss战鼓舞模型
 * @author 白雪林
 *
 */
public class Bossguwu implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**编号*/
	private int id;
	/**鼓舞银币*/
	private int silver;
	/**鼓舞元宝*/
	private int coin;
	/**每次鼓舞提升伤害值（百分之*）*/
	private int add;
	/**鼓舞上限*/
	private int limit;
	/**击杀者获得银币*/
	private int yinbi;
	/**击杀者获得声望*/
	private int shengwang;
	/**击杀者获得帮贡*/
	private int banggong;
	/**概率id*/
	private int prob;
	/**击杀奖系数*/
	private int ratio;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
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
	public int getAdd(){
		return this.add;
	}
	public void setAdd(int add){
		this.add=add;
	}
	public int getLimit(){
		return this.limit;
	}
	public void setLimit(int limit){
		this.limit=limit;
	}
	public int getYinbi() {
		return yinbi;
	}
	public void setYinbi(int yinbi) {
		this.yinbi = yinbi;
	}
	public int getShengwang() {
		return shengwang;
	}
	public void setShengwang(int shengwang) {
		this.shengwang = shengwang;
	}
	public int getProb() {
		return prob;
	}
	public void setProb(int prob) {
		this.prob = prob;
	}
	public int getBanggong() {
		return banggong;
	}
	public void setBanggong(int banggong) {
		this.banggong = banggong;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	
}