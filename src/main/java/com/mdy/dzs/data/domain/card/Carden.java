package com.mdy.dzs.data.domain.card;

import java.io.Serializable;
import java.util.List;


/**
 * 卡牌升级模型
 * @author 房曈
 *
 */
public class Carden implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**当前强化等级*/
	private int strengthenlevel;
	/**主角最低等级*/
	private int leadlevel;
	/**升级所需经验基础值*/
	private List<Integer> exp;
	/**升级累积经验值*/
	private List<Integer> sumexp;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getStrengthenlevel(){
		return this.strengthenlevel;
	}
	public void setStrengthenlevel(int strengthenlevel){
		this.strengthenlevel=strengthenlevel;
	}
	public int getLeadlevel(){
		return this.leadlevel;
	}
	public void setLeadlevel(int leadlevel){
		this.leadlevel=leadlevel;
	}
	public List<Integer> getExp(){
		return this.exp;
	}
	public void setExp(List<Integer> exp){
		this.exp=exp;
	}
	public List<Integer> getSumexp(){
		return this.sumexp;
	}
	public void setSumexp(List<Integer> sumexp){
		this.sumexp=sumexp;
	}
}