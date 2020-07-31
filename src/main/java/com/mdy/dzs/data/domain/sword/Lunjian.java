package com.mdy.dzs.data.domain.sword;

import java.io.Serializable;
import java.util.List;


/**
 * 论剑匹配表模型
 * @author zhou
 *
 */
public class Lunjian implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**关卡id*/
	private int id;
	/**匹配对手战力值上限基数（对手战斗力上限=玩家战斗力基数*该系数）*/
	private List<Float> zhanli;
	/**战斗力偏移常量*/
	private int constant;
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public List<Float> getZhanli() {
		return zhanli;
	}
	public void setZhanli(List<Float> zhanli) {
		this.zhanli = zhanli;
	}
	public int getConstant() {
		return constant;
	}
	public void setConstant(int constant) {
		this.constant = constant;
	}
}