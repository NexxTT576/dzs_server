package com.mdy.dzs.data.domain.card;

import java.io.Serializable;
import java.util.List;


/**
 * 卡牌进阶限定模型
 * @author 房曈
 *
 */
public class CardClsLimit implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/***/
	private int advanced;
	/***/
	private List<Integer> level;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getAdvanced(){
		return this.advanced;
	}
	public void setAdvanced(int advanced){
		this.advanced=advanced;
	}
	public  List<Integer> getLevel(){
		return this.level;
	}
	public void setLevel( List<Integer> level){
		this.level=level;
	}
}