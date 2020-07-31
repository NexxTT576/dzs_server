package com.mdy.dzs.data.domain.sword;

import java.io.Serializable;

/**
 * 论剑奖励表模型
 * @author zhou
 *
 */
public class LunjianProb implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**等级*/
	private int level;
	/**第一关奖励*/
	private int prob1;
	/**第二关奖励*/
	private int prob2;
	/**第三关奖励*/
	private int prob3;
	/**第四关奖励*/
	private int prob4;
	/**第五关奖励*/
	private int prob5;
	/**第六关奖励*/
	private int prob6;
	/**第七关奖励*/
	private int prob7;
	/**第八关奖励*/
	private int prob8;
	/**第九关奖励*/
	private int prob9;
	/**第十关奖励*/
	private int prob10;
	/**第十一关奖励*/
	private int prob11;
	/**第十二关奖励*/
	private int prob12;
	/**第十三关奖励*/
	private int prob13;
	/**第十四关奖励*/
	private int prob14;
	/**第十五关奖励*/
	private int prob15;

	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getProb1(){
		return this.prob1;
	}
	public void setProb1(int prob1){
		this.prob1=prob1;
	}
	public int getProb2(){
		return this.prob2;
	}
	public void setProb2(int prob2){
		this.prob2=prob2;
	}
	public int getProb3(){
		return this.prob3;
	}
	public void setProb3(int prob3){
		this.prob3=prob3;
	}
	public int getProb4(){
		return this.prob4;
	}
	public void setProb4(int prob4){
		this.prob4=prob4;
	}
	public int getProb5(){
		return this.prob5;
	}
	public void setProb5(int prob5){
		this.prob5=prob5;
	}
	public int getProb6(){
		return this.prob6;
	}
	public void setProb6(int prob6){
		this.prob6=prob6;
	}
	public int getProb7(){
		return this.prob7;
	}
	public void setProb7(int prob7){
		this.prob7=prob7;
	}
	public int getProb8(){
		return this.prob8;
	}
	public void setProb8(int prob8){
		this.prob8=prob8;
	}
	public int getProb9(){
		return this.prob9;
	}
	public void setProb9(int prob9){
		this.prob9=prob9;
	}
	public int getProb10(){
		return this.prob10;
	}
	public void setProb10(int prob10){
		this.prob10=prob10;
	}
	public int getProb11(){
		return this.prob11;
	}
	public void setProb11(int prob11){
		this.prob11=prob11;
	}
	public int getProb12(){
		return this.prob12;
	}
	public void setProb12(int prob12){
		this.prob12=prob12;
	}
	public int getProb13(){
		return this.prob13;
	}
	public void setProb13(int prob13){
		this.prob13=prob13;
	}
	public int getProb14(){
		return this.prob14;
	}
	public void setProb14(int prob14){
		this.prob14=prob14;
	}
	public int getProb15(){
		return this.prob15;
	}
	public void setProb15(int prob15){
		this.prob15=prob15;
	}
	
	public int getProbId(int i){
		int arr[] = {prob1,
				prob1,prob2,prob3,prob4,prob5,
				prob6,prob7,prob8,prob9,prob10,
				prob11,prob12,prob13,prob14,prob15};
		return arr[i];
	}
}