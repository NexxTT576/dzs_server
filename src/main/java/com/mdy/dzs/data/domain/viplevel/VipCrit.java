package com.mdy.dzs.data.domain.viplevel;

import java.io.Serializable;
import java.util.List;


/**
 * 强化暴击概率模型
 * @author 房曈
 *
 */
public class VipCrit implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**vip等级*/
	private int vip;
	/**暴击概率（不暴击；2倍暴击；3倍暴击；4倍暴击；5倍暴击）*/
	private List<Integer> nocrit;

	public int getVip(){
		return this.vip;
	}
	public void setVip(int vip){
		this.vip=vip;
	}
	public List<Integer> getNocrit(){
		return this.nocrit;
	}
	public void setNocrit(List<Integer> nocrit){
		this.nocrit=nocrit;
	}
}