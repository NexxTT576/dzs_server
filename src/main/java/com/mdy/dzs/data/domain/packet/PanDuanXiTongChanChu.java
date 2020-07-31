package com.mdy.dzs.data.domain.packet;

import java.io.Serializable;
import java.util.List;


/**
 * 系统产出模型
 * @author 房曈
 *
 */
public class PanDuanXiTongChanChu implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**系统名称*/
	private String system;
	/**是否判断（1.判断；2.不判断）*/
	private int decide;
	/**判断节点*/
	private String cond;
	/**判断所产出的背包*/
	private List<Integer> bag;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getSystem(){
		return this.system;
	}
	public void setSystem(String system){
		this.system=system;
	}
	public int getDecide(){
		return this.decide;
	}
	public void setDecide(int decide){
		this.decide=decide;
	}
	public String getCond(){
		return this.cond;
	}
	public void setCond(String cond){
		this.cond=cond;
	}
	public List<Integer> getBag(){
		return this.bag;
	}
	public void setBag(List<Integer> bag){
		this.bag=bag;
	}
}