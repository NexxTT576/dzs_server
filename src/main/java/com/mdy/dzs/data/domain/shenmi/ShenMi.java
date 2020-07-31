package com.mdy.dzs.data.domain.shenmi;

import java.io.Serializable;
import java.util.List;


/**
 * 神秘商店随机物品配置表模型
 * @author 白雪林
 *
 */
public class ShenMi implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**编号*/
	private int id;
	/**物品类型*/
	private int type;
	/**卡牌或道具id范围*/
	private List<Integer> item;
	/**数量*/
	private int num;
	/**货币类型（1-金币，2-银币，10-魂玉）*/
	private int money;
	/**价格*/
	private int price;
	/**可兑换次数*/
	private int time;
	/**等级数组*/
	private List<Integer> level;
	/**各等级概率组1（今日刷新次数≤1）*/
	private List<Integer> prob1;
	/**各等级概率组2（今日刷新次数≤5）*/
	private List<Integer> prob2;
	/**各等级概率组3（今日刷新次数≤10）*/
	private List<Integer> prob3;
	/**各等级概率组4（今日刷新次数≤50）*/
	private List<Integer> prob4;
	/**各等级概率组5（今日刷新次数＞50）*/
	private List<Integer> prob5;

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
	public List<Integer> getItem(){
		return this.item;
	}
	public void setItem(List<Integer> item){
		this.item=item;
	}
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num=num;
	}
	public int getMoney(){
		return this.money;
	}
	public void setMoney(int money){
		this.money=money;
	}
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public int getTime(){
		return this.time;
	}
	public void setTime(int time){
		this.time=time;
	}
	public List<Integer> getLevel(){
		return this.level;
	}
	public void setLevel(List<Integer> level){
		this.level=level;
	}
	public List<Integer> getProb1(){
		return this.prob1;
	}
	public void setProb1(List<Integer> prob1){
		this.prob1=prob1;
	}
	public List<Integer> getProb2(){
		return this.prob2;
	}
	public void setProb2(List<Integer> prob2){
		this.prob2=prob2;
	}
	public List<Integer> getProb3(){
		return this.prob3;
	}
	public void setProb3(List<Integer> prob3){
		this.prob3=prob3;
	}
	public List<Integer> getProb4(){
		return this.prob4;
	}
	public void setProb4(List<Integer> prob4){
		this.prob4=prob4;
	}
	public List<Integer> getProb5(){
		return this.prob5;
	}
	public void setProb5(List<Integer> prob5){
		this.prob5=prob5;
	}
}