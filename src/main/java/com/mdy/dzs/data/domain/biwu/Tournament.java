package com.mdy.dzs.data.domain.biwu;

import java.io.Serializable;
import java.util.List;


/**
 * 比武选人模型
 * @author zhou
 *
 */
public class Tournament implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**位置*/
	private int position;
	/**积分系数1*/
	private List<Integer> score1;
	/**积分系数2*/
	private List<Integer> score2;
	/**积分系数3*/
	private List<Integer> score3;
	/**战斗力系数1*/
	private List<Integer> attack1;
	/**战斗力系数2*/
	private List<Integer> attack2;
	/**战斗力系数3*/
	private List<Integer> attack3;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getPosition(){
		return this.position;
	}
	public void setPosition(int position){
		this.position=position;
	}
	public List<Integer> getScore1() {
		return score1;
	}
	public void setScore1(List<Integer> score1) {
		this.score1 = score1;
	}
	public List<Integer> getScore2() {
		return score2;
	}
	public void setScore2(List<Integer> score2) {
		this.score2 = score2;
	}
	public List<Integer> getScore3() {
		return score3;
	}
	public void setScore3(List<Integer> score3) {
		this.score3 = score3;
	}
	public List<Integer> getAttack1() {
		return attack1;
	}
	public void setAttack1(List<Integer> attack1) {
		this.attack1 = attack1;
	}
	public List<Integer> getAttack2() {
		return attack2;
	}
	public void setAttack2(List<Integer> attack2) {
		this.attack2 = attack2;
	}
	public List<Integer> getAttack3() {
		return attack3;
	}
	public void setAttack3(List<Integer> attack3) {
		this.attack3 = attack3;
	}
}
