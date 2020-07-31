package com.mdy.dzs.data.domain.gong;

import java.io.Serializable;
import java.util.List;


/**
 * 内外功升级经验模型
 * @author 房曈
 *
 */
public class KongFu implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**当前强化等级*/
	private int strengthenlevel;
	/**需求主角等级*/
	private int leadlevel;
	/**经验组*/
	private List<Integer> exp;
	/**累积经验*/
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