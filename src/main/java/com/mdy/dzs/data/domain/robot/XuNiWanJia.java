package com.mdy.dzs.data.domain.robot;

import java.io.Serializable;
import java.util.List;


/**
 * 虚拟玩家模型
 * @author 房曈
 *
 */
public class XuNiWanJia implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**等级*/
	private List<Integer> level;
	/**主角*/
	private List<Integer> lead;
	/**玩家数*/
	private int num1;
	/**卡牌数*/
	private int num2;
	/**卡牌id范围1*/
	private List<Integer> range1;
	/**概率1*/
	private int prob1;
	/**卡牌id范围2*/
	private List<Integer> range2;
	/**概率2*/
	private int prob2;
	/**卡牌id范围3*/
	private List<Integer> range3;
	/**概率3*/
	private int prob3;
	/**卡牌id范围4*/
	private List<Integer> range4;
	/**概率4*/
	private int prob4;
	/**装备数*/
	private int num3;
	/**武器id范围*/
	private List<Integer> range5;
	/**护甲id范围*/
	private List<Integer> range6;
	/**头盔id范围*/
	private List<Integer> range7;
	/**项链id范围*/
	private List<Integer> range8;
	/**装备等级*/
	private List<Integer> leve2;
	/**内外功数*/
	private int num4;
	/**内功id范围*/
	private List<Integer> range9;
	/**外功id范围*/
	private List<Integer> range10;
	/**内外功等级*/
	private List<Integer> leve3;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public List<Integer> getLevel(){
		return this.level;
	}
	public void setLevel(List<Integer> level){
		this.level=level;
	}
	public List<Integer> getLead(){
		return this.lead;
	}
	public void setLead(List<Integer> lead){
		this.lead=lead;
	}
	public int getNum1(){
		return this.num1;
	}
	public void setNum1(int num1){
		this.num1=num1;
	}
	public int getNum2(){
		return this.num2;
	}
	public void setNum2(int num2){
		this.num2=num2;
	}
	public List<Integer> getRange1(){
		return this.range1;
	}
	public void setRange1(List<Integer> range1){
		this.range1=range1;
	}
	public int getProb1(){
		return this.prob1;
	}
	public void setProb1(int prob1){
		this.prob1=prob1;
	}
	public List<Integer> getRange2(){
		return this.range2;
	}
	public void setRange2(List<Integer> range2){
		this.range2=range2;
	}
	public int getProb2(){
		return this.prob2;
	}
	public void setProb2(int prob2){
		this.prob2=prob2;
	}
	public List<Integer> getRange3(){
		return this.range3;
	}
	public void setRange3(List<Integer> range3){
		this.range3=range3;
	}
	public int getProb3(){
		return this.prob3;
	}
	public void setProb3(int prob3){
		this.prob3=prob3;
	}
	public List<Integer> getRange4(){
		return this.range4;
	}
	public void setRange4(List<Integer> range4){
		this.range4=range4;
	}
	public int getProb4(){
		return this.prob4;
	}
	public void setProb4(int prob4){
		this.prob4=prob4;
	}
	public int getNum3(){
		return this.num3;
	}
	public void setNum3(int num3){
		this.num3=num3;
	}
	public List<Integer> getRange5(){
		return this.range5;
	}
	public void setRange5(List<Integer> range5){
		this.range5=range5;
	}
	public List<Integer> getRange6(){
		return this.range6;
	}
	public void setRange6(List<Integer> range6){
		this.range6=range6;
	}
	public List<Integer> getRange7(){
		return this.range7;
	}
	public void setRange7(List<Integer> range7){
		this.range7=range7;
	}
	public List<Integer> getRange8(){
		return this.range8;
	}
	public void setRange8(List<Integer> range8){
		this.range8=range8;
	}
	public List<Integer> getLeve2(){
		return this.leve2;
	}
	public void setLeve2(List<Integer> leve2){
		this.leve2=leve2;
	}
	public int getNum4(){
		return this.num4;
	}
	public void setNum4(int num4){
		this.num4=num4;
	}
	public List<Integer> getRange9(){
		return this.range9;
	}
	public void setRange9(List<Integer> range9){
		this.range9=range9;
	}
	public List<Integer> getRange10(){
		return this.range10;
	}
	public void setRange10(List<Integer> range10){
		this.range10=range10;
	}
	public List<Integer> getLeve3(){
		return this.leve3;
	}
	public void setLeve3(List<Integer> leve3){
		this.leve3=leve3;
	}
}