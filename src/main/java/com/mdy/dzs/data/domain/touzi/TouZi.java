package com.mdy.dzs.data.domain.touzi;

import java.io.Serializable;


/**
 * 投资计划模型
 * @author 白雪林
 *
 */
public class TouZi implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**返还等级*/
	private int level;
	/**类型*/
	private int type;
	/**id*/
	private int itemid;
	/**返还元宝数*/
	private int num;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}